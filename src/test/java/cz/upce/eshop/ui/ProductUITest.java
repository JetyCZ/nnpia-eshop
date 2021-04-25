package cz.upce.eshop.ui;

import cz.upce.eshop.datafactory.Creator;
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

        String chromedriverPath = ProductUITest.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        String circleCIChromedriverPath = "/usr/local/bin/chromedriver";
        if (new File(circleCIChromedriverPath).exists()) {
            System.setProperty("webdriver.chrome.driver", circleCIChromedriverPath);
        }
    }

    @BeforeEach
    public void setup() throws MalformedURLException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);

        /*

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()
            );

         */

/*
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "c:\\Users\\zuzka\\IdeaProjects\\nnpia-eshop-clone\\nnpia-eshop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        caps.setCapability("takesScreenshot", true);
        PhantomJSDriver driver = new PhantomJSDriver(caps);
*/
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
        driver.findElement(By.id("productName")).sendKeys("nůžky");
        driver.findElement(By.id("image")).sendKeys("c:\\Users\\zuzka\\Pictures\\stažený soubor.png");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Product list']")).size());

        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='nůžky']")).size());
    }
}