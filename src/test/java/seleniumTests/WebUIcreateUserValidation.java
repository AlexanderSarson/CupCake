package seleniumTests;

/*
author madsbrandt
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import presentation.TestBase;

import static org.junit.Assert.assertEquals;

public class WebUIcreateUserValidation extends TestBase {


    public WebUIcreateUserValidation(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void WebUI_Should_DisplayError_When_NameFieldIsEmpty() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.clear();
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Please fill out the name field";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_NameFieldContainsNumbers() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("12");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Name can only contain letters and spaces";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_NameFieldContainsNonAlphabeticCharacters() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("%");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Name can only contain letters and spaces";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_EmailIsEmpty() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Please fill out the email field";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_EmailHasNoAt() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads.com");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Please provide a valid email address with a '@' and at least one '.'";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_EmailHasNoDot() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads@");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Please provide a valid email address with a '@' and at least one '.'";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_PasswordIsEmpty() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads@mads.com");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Please fill out the password field";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_PasswordLessThan8Characters() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads@mads.com");
        passwordField.sendKeys("12");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Password has to be at least 8 characters long";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_ConfirmPasswordIsEmpty() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads@mads.com");
        passwordField.sendKeys("12345678");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Please confirm the password";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

    @Test
    public void WebUI_Should_DisplayError_When_PasswordsDontMatch() {
        WebDriver driver = getDriver();
        driver.get("http://134.209.233.32/CupCakeDev/jsp/user/createUser.jsp");
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        WebElement errorMessageContainer = driver.findElement(By.id("errorMessage"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));
        String urlBefore = driver.getCurrentUrl();
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads@mads.com");
        passwordField.sendKeys("12345678");
        confirmPassword.sendKeys("87654321");
        submitButton.click();
        String urlAfter = driver.getCurrentUrl();

        String expectedError = "Passwords do not not match";
        String actualError = errorMessageContainer.getText();

        assertEquals(urlBefore, urlAfter);
        assertEquals(expectedError, actualError);
    }

}
