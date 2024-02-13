package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        // Klik button "Create Product"
        WebElement createProductButton = driver.findElement(By.linkText("Create Product"));
        createProductButton.click();

        // Isi detail produk
        WebElement productNameInput = driver.findElement(By.name("productName"));
        productNameInput.sendKeys("Product 1");

        WebElement productQuantityInput = driver.findElement(By.name("productQuantity"));
        productQuantityInput.sendKeys("10");

        // Submit form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Verify product tersebut telah dibuat
        driver.get(baseUrl + "/product/list");
        WebElement productName = driver.findElement(By.xpath("//td[contains(text(), 'Product 1')]"));
        WebElement productQuantity = driver.findElement(By.xpath("//td[contains(text(), '10')]"));

        assertEquals("Product 1", productName.getText());
        assertEquals("10", productQuantity.getText());
    }
}