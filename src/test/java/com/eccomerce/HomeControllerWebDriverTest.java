package com.eccomerce;

import com.eccomerce.pom.HomePOM;
import org.fluentlenium.adapter.FluentTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by Michał on 17.01.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EccomerceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8081")
public class HomeControllerWebDriverTest extends FluentTest {
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private PhantomJSDriver webDriver = new PhantomJSDriver();

    @BeforeClass
    public static void init() {
        //C:\Users\Michał\Phantom
        System.setProperty("phantomjs.binary.path", "C:\\Users\\Michał\\Phantom\\bin\\phantomjs.exe");
    }

    private static final String BASE_URL = "http://localhost:8080";



    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }
    @Test
    public void testHome() throws InterruptedException{
        webDriver.get(BASE_URL);
        TimeUnit.SECONDS.sleep(1);
        List<WebElement> infoButtons = webDriver.findElements(By.id("info"));
        assertEquals(8, infoButtons.size());
        List<WebElement> basketButtons = webDriver.findElements(By.id("basketbutton"));
        assertEquals(8, basketButtons.size());
        WebElement title = webDriver.findElement(By.className("project-title"));
        assertEquals("All4Man", title.getText());
        List<WebElement> pages = webDriver.findElements(By.className("page"));
        assertEquals(5, pages.size());
        List<WebElement> products = webDriver.findElements(By.id("product"));
        assertEquals(8, products.size());
    }
}
