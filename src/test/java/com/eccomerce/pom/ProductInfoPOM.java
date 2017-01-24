package com.eccomerce.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by Michał on 24.01.2017.
 */
public class ProductInfoPOM {

    private PhantomJSDriver phantomJSDriver;

    public ProductInfoPOM(PhantomJSDriver phantomJSDriver) {
        this.phantomJSDriver = phantomJSDriver;
    }

    public boolean getBasketButton() {
        return phantomJSDriver.findElement(By.id("cart")).isEnabled();
}
}
