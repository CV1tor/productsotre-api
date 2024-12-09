package br.com.user.productsore.usersApi.repository;

import br.com.user.productsore.usersApi.domain.product.Product;
import br.com.user.productsore.usersApi.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    public List<Product> findByName(String name);

    public List<Product> findByCategory(ProductCategory category);

    @Query("SELECT p FROM Product p WHERE p.price >= :price")
    public List<Product> findByPriceGreaterThan(Integer price);

    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    public List<Product> findByPriceLessThan(Integer price);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    public List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

    @Query("SELECT p FROM Product p WHERE p.quantity <= 5")
    public List<Product> productsRunningOutOfStock();
}
