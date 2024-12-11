package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.cart.CartProduct;
import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.dto.CartProductDTO;
import br.com.user.productsore.usersApi.dto.ProductDTO;
import br.com.user.productsore.usersApi.dto.ProductOnCartDTO;
import br.com.user.productsore.usersApi.dto.ProductToCartDTO;
import br.com.user.productsore.usersApi.exception.CartNotFoundException;
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

    public CartService(CartRepository cartRepository, ProductService productService, CartProductService cartProductService, AuthenticationService authenticationService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartProductService = cartProductService;
        this.authenticationService = authenticationService;
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
        cartProductService.saveCartProduct(cartProductDTO);
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

    public List<ProductOnCartDTO> productsOnCart() {
        User currentUser = authenticationService.currentUser();
        Cart cart = findCartByUserId(currentUser.getId());

        List<ProductOnCartDTO> products = cart.getProducts().stream().map(product -> {
           CartProduct cartProduct = cartProductService.findCartProductByProductAndCart(product, cart);
            return new ProductOnCartDTO(product.getName(), product.getPrice(), cartProduct.getQuantity());
        }).collect(Collectors.toList());


        return products;
    }
}
