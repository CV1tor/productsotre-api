package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.cart.CartProduct;
import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.dto.CartProductDTO;
import br.com.user.productsore.usersApi.repository.CartProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CartProductService {
    final CartProductRepository cartProductRepository;

    public CartProductService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    public void saveCartProduct(CartProductDTO cartProductDTO) {
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

    public CartProduct findCartProductByProductAndCart(Product product, Cart cart) {
        return cartProductRepository.findCartProductByProductAndCart(product, cart);
    }
}
