package br.com.user.productsore.usersApi.controller;

import br.com.user.productsore.usersApi.domain.cart.Cart;
import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.dto.ProductOnCartDTO;
import br.com.user.productsore.usersApi.dto.ProductToCartDTO;
import br.com.user.productsore.usersApi.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOnCartDTO>> getProductsOnCart() {
        List<ProductOnCartDTO> products = cartService.productsOnCart();

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestBody @Valid ProductToCartDTO dto) {
        cartService.addProductToCart(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeCart() {
        cartService.delete();

        return ResponseEntity.ok().build();
    }
}
