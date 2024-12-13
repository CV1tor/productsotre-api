package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.cart.CartProduct;
import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.dto.CartProductDTO;
import br.com.user.productsore.usersApi.dto.ProductOnCartEditDTO;
import br.com.user.productsore.usersApi.exception.CartProductNotFoundException;
import br.com.user.productsore.usersApi.exception.DecreasedProductNotOnCartException;
import br.com.user.productsore.usersApi.repository.CartProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartProductService {
    final CartProductRepository cartProductRepository;

    public CartProductService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    public void addCartProduct(CartProductDTO cartProductDTO) {
        CartProduct cartProduct = findCartProductByProductAndCart(cartProductDTO.product(), cartProductDTO.cart());
        if (cartProduct == null) {
            cartProduct = new CartProduct();
            BeanUtils.copyProperties(cartProductDTO, cartProduct);
        }
        else {
            cartProduct.setQuantity(cartProduct.getQuantity() + cartProductDTO.quantity());
        }


        cartProductRepository.save(cartProduct);
    }

    public void decreaseCartProduct(ProductOnCartEditDTO cartProductDTO) {
        CartProduct cartProduct = findCartProductByProductAndCart(cartProductDTO.product(), cartProductDTO.cart());
        if(cartProduct == null) {
            throw new CartProductNotFoundException();
        }
        int decreasedQuantity = cartProduct.getQuantity() - 1;

        if(decreasedQuantity < 0) {
            throw new DecreasedProductNotOnCartException();
        }

        if(decreasedQuantity == 0) {
            cartProductRepository.delete(cartProduct);
            return;
        }

        cartProduct.setQuantity(cartProduct.getQuantity() - 1);
        cartProductRepository.save(cartProduct);
    }

    public void removeCartProduct(UUID cartProductId) {
        CartProduct cartProduct = findCartProductById(cartProductId);
        cartProductRepository.delete(cartProduct);
    }

    public CartProduct findCartProductByProductAndCart(Product product, Cart cart) {
        return cartProductRepository.findCartProductByProductAndCart(product, cart);
    }

    public CartProduct findCartProductById(UUID cartProductId) {
        return cartProductRepository.findById(cartProductId).orElseThrow(CartProductNotFoundException::new);
    }
}
