package com.ecom_beauty.ecombeauty;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom_beauty.ecombeauty.categories.Category;
import com.ecom_beauty.ecombeauty.categories.CategoryRepository;

@SpringBootApplication
public class EcombeautyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcombeautyApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(CategoryRepository categoryRepository) {
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
				categoryRepository.saveAll(List.of(
					new Category("Skincare"),
					new Category("Cosmetics"),
					new Category("Fragrance")
				));
				
				System.out.println("Database initialization complete.");
			}
		};
	}
}
