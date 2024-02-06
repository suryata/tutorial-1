package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
    void testCreateProduct(WebDriver driver) throws Exception {
        // Navigate to Create Product page
        driver.get(baseUrl + "/product/create");

        // Fill in the form
        driver.findElement(By.id("nameInput")).sendKeys("New Product");
        driver.findElement(By.id("quantityInput")).sendKeys("10");
        
        // Submit the form
        driver.findElement(By.id("submit")).click();

        // Navigate to Product List page
        driver.get(baseUrl + "/product/list");
        
        // Verify the new product is in the list
        WebElement productList = driver.findElement(By.id("productList"));
        assertTrue(productList.getText().contains("New Product"));
    }
}
