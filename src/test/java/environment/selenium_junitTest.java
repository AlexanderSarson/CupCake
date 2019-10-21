package environment;
/*
author madsbrandt
*/

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class selenium_junitTest {

    static WebDriver driver;

    @BeforeClass
    public static void browserOpen () {
        System.setProperty("webdriver.chrome.driver", "/Users/madsbrandt/Desktop/Datamatiker/lib/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    public void Should_DisplayError_When_NameIsEmpty () {


        driver.get("http://localhost:8080/CupCake/jsp/user/createUser.jsp");
        driver.findElement(By.id("email")).sendKeys("mads.");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("errorMessage"))

        // boolean errorMessageMatches = driver.findElement(By.id("errorMessage")).getText().equals("Please provide a valid email address with a '@' and at least one '.'");

    }

    @AfterClass
    public static void closeBrowser()
    {
        driver.close();
    }


}
