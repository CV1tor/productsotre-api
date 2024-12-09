package br.com.user.productsore.usersApi.controller;

import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.dto.ProductDTO;
import br.com.user.productsore.usersApi.dto.ProductEditDTO;
import br.com.user.productsore.usersApi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer minPrice, @RequestParam(required = false) Integer maxPrice) {
        List<Product> products = productService.getAll(minPrice, maxPrice);


        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {

        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/runningOut")
    public ResponseEntity<List<Product>> getRunningOutProducts() {
        List<Product> products = productService.runningOutProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        productService.addProduct(productDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductEditDTO productDTO) {
        productService.editProduct(id, productDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.removeProduct(id);

        return ResponseEntity.ok().build();
    }
}
