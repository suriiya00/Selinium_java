package com.ajio.pages;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
 
public class SearchResults {

    WebDriver driver;

    WebDriverWait wait;
 
    public SearchResults(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
 
    public void clickFirstProduct() {

        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(

            By.cssSelector(".rilrtl-products-list__item")));

        firstProduct.click();

    }

}
 
