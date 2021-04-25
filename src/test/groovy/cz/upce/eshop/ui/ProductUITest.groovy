package cz.upce.eshop.ui;

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
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.net.MalformedURLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Creator.class)
public class ProductUITest {

    @LocalServerPort
    private int localServerPort;

    private WebDriver driver;

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
    public void addProductTest() {

        singleProductAddTest();

    }

    @Test
    public void addProductTest2() {

        singleProductAddTest();

    }


    private void singleProductAddTest() {
        driver.get("http://localhost:" + localServerPort + "/product-form");
        driver.findElement(By.id("productName")).sendKeys("Hvězda");
        def starPath = this.getClass().getResource("/img/star.png").getFile()
        try {
            driver.findElement(By.id("image")).sendKeys(starPath);
        } catch (Exception e) {
            // On Windows, starPath is like /C:/somedirectory/... which cannot be set into file input
            // So let's remove leading '/'
            driver.findElement(By.id("image")).sendKeys(starPath.substring(1));
        }
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Product list']")).size());

        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='Hvězda']")).size());
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