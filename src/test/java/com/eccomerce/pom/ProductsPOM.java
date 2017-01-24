package com.eccomerce.pom;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by Michał on 17.01.2017.
 */
public class ProductsPOM {
    private PhantomJSDriver phantomJSDriver;

    public ProductsPOM(PhantomJSDriver phantomJSDriver) {
        this.phantomJSDriver = phantomJSDriver;
    }

    public long getNumberOfProducts() {
        return phantomJSDriver.findElementsById("product").size();
    }

}
