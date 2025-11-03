package com.ajio.tests;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ajio.base.BaseTest;
import com.ajio.pages.CartPage;
import com.ajio.pages.HomePage;
import com.ajio.pages.ProductPage;
import com.ajio.pages.SearchResults;
import com.ajio.utilities.ExtentManager;
import com.aventstack.extentreports.Status;

public class loginajio extends BaseTest {

    private WebDriverWait wait;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (driver != null) driver.quit();
    }

    @Test(enabled = false)
    public void searchProductTest() {
        test = extent.createTest("Search Product Test");
        HomePage home = new HomePage(driver);

        home.searchProduct("Shoes");
        test.pass("Searched for 'Shoes'");
    }

    @Test(enabled = true)
    public void selectProductTest() {
        test = extent.createTest("Select Product Test");
        HomePage home = new HomePage(driver);
        SearchResults results = new SearchResults(driver);

        home.searchProduct("Shoes");
        test.pass("Searched for 'Shoes'");

        results.waitForResultsToLoad();
        results.clickFirstProduct();
        test.pass("Clicked on first product");
    }

    @Test
    public void selectSizeTest() {
        test = extent.createTest("Select Size Test");
        HomePage home = new HomePage(driver);
        SearchResults results = new SearchResults(driver);
        ProductPage product = new ProductPage(driver);

        home.searchProduct("Shoes");
        results.waitForResultsToLoad();
        results.clickFirstProduct();

        switchToNewTab();

        product.waitForSizeOption("6");
        product.selectSize("6");
        test.pass("Selected size 6");
    }

    @Test
    public void addToCartTest() {
        test = extent.createTest("Add to Cart Test");
        HomePage home = new HomePage(driver);
        SearchResults results = new SearchResults(driver);
        ProductPage product = new ProductPage(driver);

        home.searchProduct("Shoes");
        results.waitForResultsToLoad();
        results.clickFirstProduct();

        switchToNewTab();

        product.waitForSizeOption("6");
        product.selectSize("6");
        product.addToCart();
        test.pass("Added product to cart");
    }

    @Test
    public void proceedToCheckoutTest() {
        test = extent.createTest("Proceed to Checkout Test");
        HomePage home = new HomePage(driver);
        SearchResults results = new SearchResults(driver);
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);

        home.searchProduct("Shoes");
        results.waitForResultsToLoad();
        results.clickFirstProduct();

        switchToNewTab();

        product.waitForSizeOption("6");
        product.selectSize("6");
        product.addToCart();

        cart.goToBag();
        cart.waitForCheckoutButton();
        cart.proceedToCheckout();
        test.pass("Proceeded to checkout");
    }

    @Test
    public void addToWishlistTest() {
        test = extent.createTest("Add to Wishlist Test");
        HomePage home = new HomePage(driver);
        SearchResults results = new SearchResults(driver);
        ProductPage product = new ProductPage(driver);

        home.searchProduct("Shoes");
        results.waitForResultsToLoad();
        results.clickFirstProduct();

        switchToNewTab();

        product.waitForWishlistButton();
        product.addToWishlist();
        test.pass("Added product to wishlist");
    }

    private void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        wait.until(d -> driver.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
