package com.joseca.ubicua.repository;

import com.joseca.ubicua.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductsTest {
    // Inyectar el repositorio
    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProductTest() {
        // Crear el producto
        Product product = new Product();

        product.setName("Bollo de Santa Cruz");
        product.setSku("ABCD124");
        product.setDescription("Bollango");
        product.setPrice(new BigDecimal("500.00"));
        product.setActive(true);

        // Guardar el producto
        Product savedProduct = productRepository.save(product);

        System.out.println(savedProduct.getId());
        System.out.println(savedProduct.getName());
    }

    @Test
    void updateProductTest() {
        // Buscar el producto
        java.util.Optional<Product> search = productRepository.findById(1L);

        if (search.isEmpty()) {
            System.out.println("No se encontró el producto");
            return;
        }

        Product product = search.get();

        product.setName("Bollo");
        product.setDescription("Bollo de pan");

        // Guardar el producto
        productRepository.save(product);
    }

    @Test
    void create3Products() {
        // Crear el producto
        Product product = new Product();

        product.setName("Empanada de Pata");
        product.setSku("ABCD124");
        product.setDescription("Una simple pero económica empanada de pata");
        product.setPrice(new BigDecimal("3023.00"));
        product.setImageUrl("empanada.png");
        product.setActive(true);

        productRepository.save(product);

        // Crear el producto
        Product product2 = new Product();

        product2.setName("Chorizo de Orangutan");
        product2.setSku("ABdCD124");
        product2.setDescription("un chorizo de un orangutan");
        product2.setPrice(new BigDecimal("2324.00"));
        product2.setImageUrl("chorizo.png");
        product2.setActive(true);

        productRepository.save(product2);

        // Crear el producto
        Product product3 = new Product();

        product3.setName("Majadito");
        product3.setSku("ABCDw124");
        product3.setDescription("Majadito de carne de res con arroz y plátano maduro");
        product3.setPrice(new BigDecimal("500.00"));
        product3.setImageUrl("majadito.png");
        product3.setActive(true);

        productRepository.save(product3);
    }

    @Test
    void findAllProductsTest() {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            System.out.println(product.getName());
        }
    }

    @Test
    void deleteProductTest() {
        Long id = 1L;

        productRepository.deleteById(id);
    }
}