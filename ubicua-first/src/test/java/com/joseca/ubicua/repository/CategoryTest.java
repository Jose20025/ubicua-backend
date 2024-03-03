package com.joseca.ubicua.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.joseca.ubicua.entity.Category;
import com.joseca.ubicua.entity.Product;

@SpringBootTest
public class CategoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void createCategoryTest() {
        Category category = new Category();

        category.setName("Libros");
        category.setDescription("Libros digitales");

        // Creamos el producto
        Product product1 = new Product();
        product1.setName("Java Advanced");
        product1.setDescription("Java API REST y GraphQL");
        product1.setPrice(new BigDecimal(1200));
        product1.setSku("JAVA0001");
        product1.setActive(true);
        product1.setImageUrl("javamax.png");
        product1.setCategory(category);

        // Creamos el segundo producto
        Product product2 = new Product();
        product2.setName("Java Core");
        product2.setDescription("Java Core, The Book");
        product2.setPrice(new BigDecimal(1300));
        product2.setSku("JAVACORE0001");
        product2.setActive(true);
        product2.setImageUrl("javacore.png");
        product2.setCategory(category);

        category.getProducts().add(product1);
        category.getProducts().add(product2);

        categoryRepository.save(category);
    }
}
