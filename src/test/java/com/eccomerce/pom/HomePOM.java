package com.eccomerce.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by Michał on 20.01.2017.
 */
public class HomePOM {
    private PhantomJSDriver phantomjs;
    public HomePOM(PhantomJSDriver phantomjs){
        this.phantomjs = phantomjs;
    }

    public void selectProduct(int id){
        this.phantomjs.findElementByXPath("/product/"+id).getText();
    }

    public void basketButton(){
        this.phantomjs.findElement(By.id("basketbutton")).click();
    }

    public Boolean isPageLoaded() {
        if( this.phantomjs.findElement(By.id("basketbutton")).isDisplayed()){
            return true;
        }
        else return false;
    }
}
