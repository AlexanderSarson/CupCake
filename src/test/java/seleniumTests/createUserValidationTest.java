package seleniumTests;
/*
author madsbrandt
*/

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
@Ignore
public class createUserValidationTest {

    private static WebDriver driver;

    private WebElement nameField;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement confirmPassword;
    private WebElement errorMessageContainer;
    private WebElement submitButton;


    @BeforeClass
    public static void browserOpen () {
        System.setProperty("webdriver.chrome.driver", "/Users/madsbrandt/Desktop/Datamatiker/lib/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

    @Before
    public void setup() throws InterruptedException {
        driver.get("http://localhost:8080/CupCake/jsp/user/createUser.jsp");

        nameField = driver.findElement(By.id("name"));
        emailField = driver.findElement(By.id("email"));
        passwordField = driver.findElement(By.id("password"));
        confirmPassword = driver.findElement(By.id("confirmPassword"));
        errorMessageContainer = driver.findElement(By.id("errorMessage"));
        submitButton = driver.findElement(By.id("submitBtn"));
    }

    @Test
    public void Should_DisplayError_When_NameFieldIsEmpty () {
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
    public void Should_DisplayError_When_NameFieldContainsNumbers () {
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
    public void Should_DisplayError_When_NameFieldContainsNonAlphabeticCharacters () {
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
    public void Should_DisplayError_When_EmailIsEmpty () {
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
    public void Should_DisplayError_When_EmailHasNoAt () {
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
    public void Should_DisplayError_When_EmailHasNoDot () {
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
    public void Should_DisplayError_When_PasswordIsEmpty () {
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
    public void Should_DisplayError_When_PasswordLessThan8Characters () {
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
    public void Should_DisplayError_When_ConfirmPasswordIsEmpty () {
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
    public void Should_DisplayError_When_PasswordsDontMatch () {
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

    @Test
    public void Should_GoToFrontController_When_LoginSuccessful () {
        nameField.sendKeys("Mads");
        emailField.sendKeys("mads@mads.com");
        passwordField.sendKeys("12345678");
        confirmPassword.sendKeys("12345678");
        submitButton.click();

        String expectedUrl = "http://localhost:8080/CupCake/jsp/user/FrontController";
        String actualUrl = driver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }


}
