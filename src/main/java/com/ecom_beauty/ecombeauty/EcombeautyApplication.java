package com.ecom_beauty.ecombeauty;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom_beauty.ecombeauty.categories.Category;
import com.ecom_beauty.ecombeauty.categories.CategoryRepository;
import com.ecom_beauty.ecombeauty.deliveryMethods.DeliveryMethod;
import com.ecom_beauty.ecombeauty.deliveryMethods.DeliveryMethodRepository;
import com.ecom_beauty.ecombeauty.favorites.Favorite;
import com.ecom_beauty.ecombeauty.favorites.FavoriteRepository;
import com.ecom_beauty.ecombeauty.orders.Order;
import com.ecom_beauty.ecombeauty.orders.OrderRepository;
import com.ecom_beauty.ecombeauty.orders.OrderStatus;
import com.ecom_beauty.ecombeauty.orders.PaymentMethod;
import com.ecom_beauty.ecombeauty.productReviews.ProductReview;
import com.ecom_beauty.ecombeauty.productReviews.ProductReviewRepository;
import com.ecom_beauty.ecombeauty.productReviews.ProductReviewService;
import com.ecom_beauty.ecombeauty.products.Product;
import com.ecom_beauty.ecombeauty.products.ProductRepository;
import com.ecom_beauty.ecombeauty.users.User;
import com.ecom_beauty.ecombeauty.users.UserRepository;

@SpringBootApplication
public class EcombeautyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcombeautyApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository, FavoriteRepository favoriteRepository, ProductReviewRepository productReviewRepository, ProductReviewService productReviewService, OrderRepository orderRepository, DeliveryMethodRepository deliveryMethodRepository) {
		return args -> {
			boolean isDev = Arrays.asList(args).contains("--profile=dev");
			boolean rebuildDb = Arrays.asList(args).contains("--rebuild-db");
			
			if (isDev) {
				System.out.println("Running in development mode...");
				if (rebuildDb) {
					System.out.println("Rebuilding database...");
					categoryRepository.deleteAll();
				}
				
				System.out.println("Initializing database with dummy data...");
				List<Category> categories = categoryRepository.saveAll(List.of(
					new Category("Skincare"),
					new Category("Cosmetics"),
					new Category("Fragrance")
				));

				// Products
				List<Product> products = productRepository.saveAll(List.of(
					new Product("Radiant Glow Serum", "Illuminate your skin with this vitamin C-infused serum", BigDecimal.valueOf(49.99), 100, categories.get(0)),
					new Product("Velvet Matte Lipstick", "Long-lasting, creamy matte lipstick in 'Ruby Passion'", BigDecimal.valueOf(22.50), 200, categories.get(1)),
					new Product("Silk Smooth Shampoo", "Nourishing shampoo with argan oil for silky, smooth hair", BigDecimal.valueOf(18.99), 150, categories.get(2)),
					new Product("Enchanted Forest Eau de Parfum", "A mystical blend of cedar, bergamot, and wild berries", BigDecimal.valueOf(85.00), 50, categories.get(0)),
					new Product("Coconut Dream Body Butter", "Ultra-hydrating body butter with tropical coconut scent", BigDecimal.valueOf(24.99), 75, categories.get(1)),
					new Product("Hydra-Boost Moisturizer", "24-hour hydration with hyaluronic acid and ceramides", BigDecimal.valueOf(38.50), 120, categories.get(0)),
					new Product("Cat Eye Liquid Liner", "Precision tip for the perfect winged eyeliner look", BigDecimal.valueOf(17.99), 180, categories.get(1)),
					new Product("Curl Defining Cream", "Frizz-fighting cream for bouncy, defined curls", BigDecimal.valueOf(28.50), 90, categories.get(2)),
					new Product("Ocean Breeze Body Mist", "Refreshing sea salt and citrus body spray", BigDecimal.valueOf(15.99), 100, categories.get(2)),
					new Product("Midnight Jasmine Perfume Oil", "Intoxicating blend of jasmine, vanilla, and musk", BigDecimal.valueOf(55.00), 60, categories.get(0))
				));

				List<User> users = userRepository.saveAll(List.of(
					new User("John", "Doe", "john.doe@example.com", "password", "https://example.com/profile.jpg"),
					new User("Jane", "Doe", "jane.doe@example.com", "password", "https://example.com/profile.jpg"),
					new User("Alice", "Smith", "alice.smith@example.com", "password", "https://example.com/profile.jpg"),
					new User("Bob", "Johnson", "bob.johnson@example.com", "password", "https://example.com/profile.jpg")
				));

				productReviewService.saveProductReviewAndUpdateRating(
					new ProductReview(products.get(0), users.get(0), 5, "Amazing product!")
				);
				productReviewService.saveProductReviewAndUpdateRating(
					new ProductReview(products.get(1), users.get(1), 4, "Great quality!")
				);

				favoriteRepository.saveAll(List.of(
					new Favorite(users.get(0), products.get(0)),
					new Favorite(users.get(1), products.get(1)),
					new Favorite(users.get(2), products.get(2)),
					new Favorite(users.get(3), products.get(3))
				));

				deliveryMethodRepository.saveAll(List.of(
					new DeliveryMethod("Pickup", BigDecimal.valueOf(0.00)),
					new DeliveryMethod("Standard", BigDecimal.valueOf(5.99))						
				));

				orderRepository.saveAll(List.of(
					new Order(users.get(0), OrderStatus.PENDING, deliveryMethodRepository.findByName("Pickup").get(), PaymentMethod.CREDIT_CARD, users.get(0).getAddress(), BigDecimal.valueOf(100.00)),
					new Order(users.get(1), OrderStatus.PENDING, deliveryMethodRepository.findByName("Standard").get(), PaymentMethod.CREDIT_CARD, users.get(1).getAddress(), BigDecimal.valueOf(100.00)),
					new Order(users.get(2), OrderStatus.PENDING, deliveryMethodRepository.findByName("Standard").get(), PaymentMethod.CREDIT_CARD, users.get(2).getAddress(), BigDecimal.valueOf(100.00)),
					new Order(users.get(3), OrderStatus.PENDING, deliveryMethodRepository.findByName("Standard").get(), PaymentMethod.CREDIT_CARD, users.get(3).getAddress(), BigDecimal.valueOf(100.00)),
					new Order(users.get(0), OrderStatus.PENDING, deliveryMethodRepository.findByName("Standard").get(), PaymentMethod.CREDIT_CARD, users.get(0).getAddress(), BigDecimal.valueOf(100.00))
				));
				
				System.out.println("Database initialization complete.");
			}
		};
	}
}
