package com.ajio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResults {

    WebDriver driver;
    WebDriverWait wait;

    private By firstProduct = By.cssSelector(".rilrtl-products-list__item");

    public SearchResults(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstProduct));
        
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        
        // Scroll into view for safety
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
        
        product.click();
    }
}
