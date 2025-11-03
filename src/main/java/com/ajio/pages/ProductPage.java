package com.ajio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void selectSize(String size) {
        By sizeOption = By.xpath("//span[text()='" + size + "']");
        WebElement sizeBtn = wait.until(ExpectedConditions.elementToBeClickable(sizeOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sizeBtn);
        sizeBtn.click();
    }

    public void addToCart() {
        By addToBagBtn = By.xpath("//span[text()='ADD TO BAG']");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public void addToWishlist() {
        By wishlistIcon = By.cssSelector("a[href*='wishlist']");
        WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", icon);
        try {
            icon.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", icon);
        }
    }
}
