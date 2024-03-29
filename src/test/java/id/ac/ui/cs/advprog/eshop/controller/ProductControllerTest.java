package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ProductControllerTests {
    @Mock
    ProductService productService;
    @Mock
    private Model model;
    @InjectMocks
    ProductController productController;

    @Test
    void testCreateProductPage() {
        String result = productController.createProductPage(model);
        assertEquals("CreateProduct", result);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);
        String result = productController.createProductPost(product, model);
        assertEquals("redirect:list", result);
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);
        String result = productController.productListPage(model);
        assertEquals("ListProduct", result);
        verify(model, times(1)).addAttribute("products", products);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById(product.getId())).thenReturn(product);
        String result = productController.editProductPage(product.getId(), model);
        assertEquals("EditProduct", result);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testDeleteProductPost() {
        String productId = "testId";
        doNothing().when(productService).deleteById(productId);
        String result = productController.deleteProductPost(productId);
        verify(productService, times(1)).deleteById(productId);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testEditProductPost() {
        Product updatedProduct = new Product();
        updatedProduct.setId("123");
        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);

            assertEquals("123", arg0);
            assertEquals(updatedProduct, arg1);

            return null;
        }).when(productService).edit(anyString(), any(Product.class));

        String result = productController.editProductPost(updatedProduct, model);
        assertEquals("redirect:list", result);
    }
}