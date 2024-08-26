package com.ecom_beauty.ecombeauty.products;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsByMinimumRating(BigDecimal rating) {
        return productRepository.findByRatingGreaterThanEqual(rating);
    }

    @Override
    public List<Product> getProductsInStock(Integer minStock) {
        return productRepository.findByStockGreaterThan(minStock);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    @Override
    public List<Product> getTopRatedProducts() {
        return productRepository.findTopRatedProducts();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Integer id, Product productDetails) {
        return productRepository.save(productDetails);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));
    }

    @Override
    public Product getProductByCategoryId(Integer categoryId) {
        return productRepository.findFirstByCategory_Id(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with category ID: " + categoryId));
    }
}