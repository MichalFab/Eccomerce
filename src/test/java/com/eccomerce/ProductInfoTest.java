package com.eccomerce;

import com.eccomerce.pom.ProductInfoPOM;
import org.fluentlenium.adapter.FluentTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Michał on 24.01.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EccomerceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8081")
public class ProductInfoTest extends FluentTest {

    private PhantomJSDriver webDriver = new PhantomJSDriver();
    private ProductInfoPOM productInfoPOM = new ProductInfoPOM(webDriver);
    @BeforeClass
    public static void init() {

        System.setProperty("phantomjs.binary.path", "C:\\Users\\Michał\\Phantom\\bin\\phantomjs.exe");
    }
    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @Test
    public void testPhantomJsPath() {
        System.out.println(System.getProperty("phantomjs.binary.path"));
    }

    @Test
    public void testProductInfoCartButton() {

        for(int i=1; i<5; i++){
            webDriver.get("http://localhost:8080/product/" + i );
            assertThat(productInfoPOM.getBasketButton());
        }



    }
}
