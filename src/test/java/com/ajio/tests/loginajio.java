package com.ajio.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ajio.base.BaseTest;
import com.aventstack.extentreports.Status;
import com.ajio.utilities.ExtentManager;

public class loginajio extends BaseTest {

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

    @BeforeMethod
    public void setup() {
        super.setup();
        navigateUrl("https://www.ajio.com");
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void selectProductTest() {
        test = extent.createTest("Select Product Test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder*='Search']")
            ));
            wait.until(ExpectedConditions.elementToBeClickable(searchInput));

            searchInput.sendKeys("Shoes");
            searchInput.sendKeys(Keys.ENTER);
            test.pass("Searched for 'Shoes'");

            // Wait for results container
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-test-id='productTile']")));  // adjust as per site
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-test-id='productTile']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);
            test.pass("Clicked on first product");

            // After click, switch tab if needed or wait for detail page
            switchToNewTab();

            WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("button[data-test-id='addToBag']")  // adjust selector
            ));
            Assert.assertTrue(addToCart.isDisplayed(), "Product detail page not loaded properly");

        } catch (TimeoutException e) {
            Assert.fail("Product search or selection failed: " + e.getMessage());
        }
    }

    @Test
    public void addToCartTest() {
        test = extent.createTest("Add to Cart Test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder*='Search']")
            ));
            searchInput.sendKeys("Shoes");
            searchInput.sendKeys(Keys.ENTER);
            test.pass("Searched for 'Shoes'");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-test-id='productTile']")));
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-test-id='productTile']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            switchToNewTab();

            // Wait for size selection
            WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='6']")  // adjust for actual size selector
            ));
            sizeOption.click();

            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[data-test-id='addToBag']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

            test.pass("Added product to cart");

        } catch (TimeoutException e) {
            Assert.fail("Add to cart test failed: " + e.getMessage());
        }
    }

    @Test
    public void proceedToCheckoutTest() {
        test = extent.createTest("Proceed to Checkout Test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'PROCEED TO CHECKOUT')]")
            ));
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);
            test.pass("Clicked on Proceed to Checkout");

            wait.until(ExpectedConditions.urlContains("/checkout"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/checkout"),
                    "Did not navigate to checkout page");

        } catch (TimeoutException e) {
            Assert.fail("Proceed to checkout button not clickable: " + e.getMessage());
        }
    }

    @Test
    public void addToWishlistTest() {
        test = extent.createTest("Add to Wishlist Test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // First search product and navigate, similar to above
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder*='Search']")
            ));
            searchInput.sendKeys("Shoes");
            searchInput.sendKeys(Keys.ENTER);
            test.pass("Searched for 'Shoes'");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-test-id='productTile']")));
            WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-test-id='productTile']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productElement);

            // Hover or scroll to make wishlist button visible
            WebElement wishlistButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("button[data-test-id='wishlistButton']")  // adjust selector
            ));
            wait.until(ExpectedConditions.elementToBeClickable(wishlistButton));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishlistButton);
            test.pass("Clicked on wishlist button");

            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".wishlist-confirmation")  // adjust selector
            ));
            Assert.assertTrue(confirmation.isDisplayed(), "Wishlist confirmation not visible");

        } catch (TimeoutException e) {
            Assert.fail("Wishlist button not clickable after waiting: " + e.getMessage());
        }
    }

    private void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d -> driver.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
