package id.ac.ui.cs.advprog.eshop.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
               .andExpect(status().isOk())
               .andExpect(view().name("createProduct"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create").flashAttr("product", new Product()))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:list"));
        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    public void testProductListPage() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(new Product()));
        mockMvc.perform(get("/product/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("productList"))
               .andExpect(model().attributeExists("products"));
    }

    @Test
    public void testEditProductPage() throws Exception {
        mockMvc.perform(get("/product/edit/{productId}", "1"))
               .andExpect(status().isOk())
               .andExpect(view().name("editProduct"))
               .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/update").flashAttr("product", new Product()))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/product/list"));
        verify(service, times(1)).edit(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/{productId}", "1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/product/list"));
        verify(service, times(1)).delete("1");
    }
}

