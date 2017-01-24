package com.eccomerce.pom;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Michał on 24.01.2017.
 */
public class CategoriesPOM {
    private PhantomJSDriver phantomJSDriver;


    public CategoriesPOM(PhantomJSDriver phantomJSDriver) {
        this.phantomJSDriver = phantomJSDriver;
    }

    public long getNumberOfCategories() {
        return phantomJSDriver.findElementsById("category").size();
    }
}
