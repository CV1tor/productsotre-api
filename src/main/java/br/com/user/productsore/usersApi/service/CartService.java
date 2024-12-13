package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.cart.CartProduct;
import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.domain.wallet.Wallet;
import br.com.user.productsore.usersApi.dto.*;
import br.com.user.productsore.usersApi.exception.BalanceNotEnoughException;
import br.com.user.productsore.usersApi.exception.CartNotFoundException;
import br.com.user.productsore.usersApi.exception.DecreasedProductNotOnCartException;
import br.com.user.productsore.usersApi.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartProductService cartProductService;
    private final AuthenticationService authenticationService;
    private final WalletService walletService;

    public CartService(CartRepository cartRepository, ProductService productService, CartProductService cartProductService, AuthenticationService authenticationService, WalletService walletService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartProductService = cartProductService;
        this.authenticationService = authenticationService;
        this.walletService = walletService;
    }


    public void addProductToCart(ProductToCartDTO productDTO) {
        User currentUser = authenticationService.currentUser();
        Product product = productService.findProductById(productDTO.productId());
        Cart cart = cartRepository.findByUserId(currentUser.getId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(currentUser);
            cartRepository.save(newCart);
            return newCart;
        });

        CartProductDTO cartProductDTO = new CartProductDTO(product, cart, productDTO.quantity());
        cartProductService.addCartProduct(cartProductDTO);
        cartRepository.save(cart);
    }

    public void delete() {
        User currentUser = authenticationService.currentUser();
        Cart cart = findCartByUserId(currentUser.getId());

        cartRepository.delete(cart);
    }

    public Cart findCartById(UUID cartId) {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart findCartByUserId(UUID id) {
        return cartRepository.findByUserId(id).orElseThrow(CartNotFoundException::new);
    }

    public void decreaseProductQuantity(UUID productId) {
        Product product = productService.findProductById(productId);
        User currentUser = authenticationService.currentUser();
        Cart cart = findCartByUserId(currentUser.getId());
        cartProductService.decreaseCartProduct(new ProductOnCartEditDTO(product, cart));
        if (cart.getProducts().isEmpty()) {
            delete();
        }
    }

    public void removeProductFromCart(UUID productId) {
        Product product = productService.findProductById(productId);
        User currentUser = authenticationService.currentUser();
        Cart cart = findCartByUserId(currentUser.getId());
        CartProduct cartProduct = cartProductService.findCartProductByProductAndCart(product, cart);

        cartProductService.removeCartProduct(cartProduct.getId());
        if (cart.getProducts().isEmpty()) {
            delete();
        }
    }

    public void buyProductsOnCart() {

        User currentUser = authenticationService.currentUser();
        Integer cartPrice = cartTotalPrice();

        walletService.buymentUpdateBalance(cartPrice);

        Cart cart = findCartByUserId(currentUser.getId());
        cartRepository.delete(cart);
    }

    public Integer cartTotalPrice() {
        User currentUser = authenticationService.currentUser();
        Cart cart = findCartByUserId(currentUser.getId());
        return cartRepository.cartTotalPrice(cart.getId());
    }

    public List<ProductOnCartDTO> productsOnCart() {
        User currentUser = authenticationService.currentUser();
        Cart cart = findCartByUserId(currentUser.getId());

        List<ProductOnCartDTO> products = cart.getProducts().stream().map(product -> {
            CartProduct cartProduct = cartProductService.findCartProductByProductAndCart(product, cart);
            return new ProductOnCartDTO(product.getId(), product.getName(), product.getPrice(), cartProduct.getQuantity());
        }).collect(Collectors.toList());


        return products;
    }
}
