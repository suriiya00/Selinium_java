package com.ajio.pages;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
 
public class LoginPage {
 
    WebDriver driver;
 
    // Locators

    private By usernameField = By.name("username");

    private By passwordField = By.name("password");

    private By submitButton = By.xpath("//button[@type='submit']");

    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
 
    // Constructor

    public LoginPage(WebDriver driver) {

        this.driver = driver;

    }
 
    // Actions

    public void enterUsername(String username) {

        driver.findElement(usernameField).sendKeys(username);

    }
 
    public void enterPassword(String password) {

        driver.findElement(passwordField).sendKeys(password);

    }
 
    public void clickOnSubmit() {

        driver.findElement(submitButton).click();

    }
 
    public boolean isDashboardDisplayed() {

        return driver.findElement(dashboardHeader).isDisplayed();

    }

}
 
