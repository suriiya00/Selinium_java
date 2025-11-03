package com.ajio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void goToBag() {
        WebElement bagIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='cart']")));
        bagIcon.click();
    }

    public void proceedToCheckout() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='PROCEED TO CHECKOUT']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
        checkoutBtn.click();
    }
}
