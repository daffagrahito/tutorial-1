package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sample Product");
        product.setProductQuantity(10);

        given(service.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).willReturn(product);
        given(service.findAll()).willReturn(Arrays.asList(product));
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product(); // Consider adding details to the product
        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).create(product);
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product()); // Consider initializing products with
        // details
        given(service.findAll()).willReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListProduct"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", products));
    }

    @Test
    void testDeleteProductPost() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6"; // Example product ID
        mockMvc.perform(post("/product/delete/" + productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).deleteById(productId);
    }

    @Test
    void testEditProductPage() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6"; // Example product ID
        Product product = new Product(); // Consider setting product details
        given(service.findById(productId)).willReturn(product);

        mockMvc.perform(get("/product/edit/" + productId))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void testEditProductPost() throws Exception {
        Product updatedProduct = new Product(); // Consider setting updated product details
        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", updatedProduct))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).edit(updatedProduct);
    }
}
