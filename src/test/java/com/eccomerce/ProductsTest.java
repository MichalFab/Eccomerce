package com.eccomerce;

import com.eccomerce.models.Product;
import com.eccomerce.pom.ProductsPOM;
import com.eccomerce.repositiories.ProductRepository;
import com.eccomerce.services.ProductServiceJpaImpl;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.Fluent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Created by Michał on 17.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EccomerceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:9999")
public class ProductsTest extends FluentTest {
    private PhantomJSDriver webDriver = new PhantomJSDriver();
    private ProductsPOM productsPOM = new ProductsPOM(webDriver);
    @Autowired
    private ProductServiceJpaImpl productServiceJpa;

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
    public void testNumberOfProducts() {
        webDriver.get("http://localhost:8080/");

        long count = productServiceJpa.findAll().stream().count();
        assertThat(productsPOM.getNumberOfProducts()).isEqualTo(count);
    }

    @Test
    public void testIfProductsAreCorrect(){
        webDriver.get("http://localhost:8080/");
        List<Product> allProducts = productServiceJpa.findAll();
        allProducts.forEach(Assert :: assertNotNull );
        allProducts.forEach(product -> {
            assertNotNull(product.getId());
            assertNotNull(product.getCategory());
            assertNotNull(product.getDescription());
            assertNotNull(product.getPrice());
            assertNotNull(product.getShortDesc());
            assertNotNull(product.getTitle());
        });

    }

}
