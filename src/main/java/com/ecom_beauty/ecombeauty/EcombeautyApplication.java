package com.ecom_beauty.ecombeauty;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.ecom_beauty.ecombeauty.categories.Category;
import com.ecom_beauty.ecombeauty.categories.CategoryRepository;

@SpringBootApplication
public class EcombeautyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcombeautyApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner initDatabase(CategoryRepository categoryRepository) {
		return args -> {
			boolean rebuildDb = Boolean.getBoolean("rebuildDb");
			
			if (rebuildDb) {
				System.out.println("Rebuilding database...");
				categoryRepository.deleteAll();
			}
			
			System.out.println("Initializing database with dummy data...");
			categoryRepository.saveAll(List.of(
				new Category("Skincare"),
				new Category("Cosmetics"),
				new Category("Fragrance")
			));
			
			System.out.println("Database initialization complete.");
		};
	}
}
