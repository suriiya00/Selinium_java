package com.ajio.pages;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
 
public class ProductPage {

    WebDriver driver;

    WebDriverWait wait;
 
    public ProductPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
 
    public void selectSize(String size) {

        WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(

            By.xpath("//span[text()='" + size + "']")));

        sizeOption.click();

    }
 
    public void addToCart() {

        WebElement addToBagBtn = wait.until(ExpectedConditions.elementToBeClickable(

            By.xpath("//span[text()='ADD TO BAG']")));

        addToBagBtn.click();

    }
 
    public void addToWishlist() {

        WebElement wishlistIcon = wait.until(ExpectedConditions.elementToBeClickable(

            By.cssSelector("a[href*='wishlist']")));

        wishlistIcon.click();

    }

}
 
