package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductName("Test Product");
        when(productRepository.create(product)).thenReturn(product);
        
        Product createdProduct = productService.create(product);
        
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        Product product2 = new Product();
        product2.setProductName("Product 2");
        
        List<Product> productList = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(productList.iterator());
        
        List<Product> allProducts = productService.findAll();
        
        assertEquals(2, allProducts.size());
        assertTrue(allProducts.contains(product1) && allProducts.contains(product2));
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductName("Original Name");
        
        when(productRepository.edit(product)).thenReturn(product);
        
        Product editedProduct = productService.edit(product);
        
        assertEquals("Original Name", editedProduct.getProductName());
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDelete() {
        String productId = "someProductId";
        
        doNothing().when(productRepository).delete(productId);
        
        productService.delete(productId);
        
        verify(productRepository, times(1)).delete(productId);
    }
}
