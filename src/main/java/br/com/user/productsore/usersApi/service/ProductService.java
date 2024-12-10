package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.dto.ProductDTO;
import br.com.user.productsore.usersApi.dto.ProductEditDTO;
import br.com.user.productsore.usersApi.exception.ProductNotFoundException;
import br.com.user.productsore.usersApi.exception.ProductOutOfStockException;
import br.com.user.productsore.usersApi.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);

        productRepository.save(product);
    }

    public void editProduct(UUID id, ProductEditDTO productDTO) {
       Product product = findProductById(id);
       BeanUtils.copyProperties(productDTO, product);

       productRepository.save(product);
    }

    public void removeProduct(UUID id) {
        Product product = findProductById(id);

        productRepository.delete(product);
    }

    public List<Product> getAll(Integer min, Integer max) {
        if(min != null && max != null) {
            return productRepository.findByPriceBetween(min, max);
        }
        else if(min != null) {
            return productRepository.findByPriceGreaterThan(min);
        }
        else if(max != null) {
            return productRepository.findByPriceLessThan(max);
        }
        else {
            return productRepository.findAll();
        }
    }

    public List<Product> runningOutProducts() {
        return productRepository.productsRunningOutOfStock();
    }

    public Product findProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
