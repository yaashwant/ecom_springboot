package com.ecom.project.repository;

import com.ecom.project.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    private productRepo productRepo; // Inject repository for testing

    @Test
    public void testSaveProduct() throws Exception {
        // Arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date releaseDate = dateFormat.parse("11-11-1999");

        Product product = Product.builder()
                .name("Stanley")
                .description("Holds liquid")
                .brand("Stanley Bottle")
                .price(BigDecimal.valueOf(100.00))
                .category("Bottles")
                .releaseDate(releaseDate)
                .status(true)
                .stock(100)
                .build();

        // Act
        Product savedProduct = productRepo.save(product);

        // Assert
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals("Stanley", savedProduct.getName());
    }

    @Test
    public void testFindByName() {
        // Arrange
        Product product = Product.builder()
                .name("Thermos")
                .description("Insulated bottle")
                .brand("Thermos Brand")
                .price(BigDecimal.valueOf(80.00))
                .category("Bottles")
                .status(true)
                .stock(200)
                .build();

        productRepo.save(product);

        // Act
        Optional<Product> foundProduct = productRepo.findByName("Thermos");

        // Assert
        Assertions.assertTrue(foundProduct.isPresent());
        Assertions.assertEquals("Thermos", foundProduct.get().getName());
    }

    @Test
    public void testFindAll() {

        //Arrange
        Product product = Product.builder()
                .name("Thermos")
                .description("Insulated bottle")
                .brand("Thermos Brand")
                .price(BigDecimal.valueOf(80.00))
                .category("Bottles")
                .status(true)
                .stock(200)
                .build();

        Product product2 = Product.builder()
                .name("Stanley")
                .description("Holds liquid")
                .brand("Stanley Bottle")
                .price(BigDecimal.valueOf(100.00))
                .category("Bottles")
                .status(true)
                .stock(100)
                .build();
        productRepo.save(product);
        productRepo.save(product2);

        //Act
        List<Product> products = productRepo.findAll();

        //Assert
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Stanley Bottle", products.get(1).getBrand());
    }

    @Test
    public void testDeleteProduct() {
        //Arrange
        Product product = Product.builder()
                .name("Thermos")
                .description("Insulated bottle")
                .brand("Thermos Brand")
                .price(BigDecimal.valueOf(80.00))
                .category("Bottles")
                .status(true)
                .stock(200)
                .build();
        productRepo.save(product);

        //Act
        productRepo.deleteById(product.getId());
        Optional<Product> foundProduct = productRepo.findById(product.getId());

        //Assert
        Assertions.assertFalse(foundProduct.isPresent());

    }
}

