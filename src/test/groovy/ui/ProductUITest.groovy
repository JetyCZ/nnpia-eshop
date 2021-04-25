package ui

import cz.upce.eshop.EshopApplication;
import cz.upce.eshop.datafactory.Creator;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = EshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Creator.class)
public class ProductUITest {

    private WebDriver driver;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromedriverPath =  ProductUITest.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        productRepository.deleteAll();

    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void productList() {

        creator.saveEntities(
                new Product(productName: "product1"),
                new Product(productName: "product2"),
                new Product(productName: "product3"),
        )

        driver.get("http://localhost:" + localServerPort + "/");

        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='product1']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='product2']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='product3']")).size());



    }

}