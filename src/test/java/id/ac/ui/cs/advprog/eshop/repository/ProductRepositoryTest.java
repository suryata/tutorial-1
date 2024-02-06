package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){

    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductID(), savedProduct.getProductID());
        assertEquals("Sampo Cap Bambang", savedProduct.getProductName());
        assertEquals(100, savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductID("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductID(), savedProduct.getProductID());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductID(), savedProduct.getProductID());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductPositive() {
        // Arrange
        Product product = new Product();
        product.setProductName("Original Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductID(product.getProductID()); // Same ID
        updatedProduct.setProductName("Edited Product");
        updatedProduct.setProductQuantity(200);

        // Act
        Product result = productRepository.edit(updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Edited Product", result.getProductName());
        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testEditProductNegative() {
        // Arrange
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductName("Non-existent Product");
        nonExistentProduct.setProductQuantity(100);

        // Act
        Product result = productRepository.edit(nonExistentProduct);

        // Assert
        assertNull(result);
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setProductName("ProductToDelete");
        product.setProductQuantity(56);
        productRepository.create(product);

        // Act
        productRepository.delete(product.getProductID());

        // Assert
        Iterator<Product> iterator = productRepository.findAll();
        boolean productExists = false;
        while(iterator.hasNext()) {
            if(iterator.next().getProductID().equals(product.getProductID())) {
                productExists = true;
                break;
            }
        }
        assertFalse(productExists);
    }

    @Test
    public void testDeleteNonExistingProduct() {
        // Arrange
        String nonExistingProductId = "UUID.randomUUID";

        // Act
        productRepository.delete(nonExistingProductId);

        // Assert
        Iterator<Product> iterator = productRepository.findAll();
        boolean productExists = false;
        while(iterator.hasNext()) {
            if(iterator.next().getProductID().equals(nonExistingProductId)) {
                productExists = true;
                break;
            }
        }
        assertFalse(productExists, "Non-existing product should not exist");
    }
}
