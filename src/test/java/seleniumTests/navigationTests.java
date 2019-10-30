//package seleniumTests;
///*
//author madsbrandt
//*/
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.Assert.assertEquals;
//
//public class navigationTests {
//    private static WebDriver driver;
//
//    @BeforeClass
//    public static void browserOpen () {
//        System.setProperty("webdriver.chrome.driver", "/Users/madsbrandt/Desktop/Datamatiker/lib/selenium/chromedriver");
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @AfterClass
//    public static void closeBrowser() {
//        if(driver != null)
//            driver.close();
//    }
//
//    @Test
//    public void Should_ForwardToLogin_When_EditUserIsAccessedWithoutLogin () {
//    }
//
//    @Test
//    public void Should_GoToCreateUser_WhenCreateUserLinkIsClicked () {
//        driver.get("http://localhost:8080/CupCake/jsp/user/login.jsp");
//        WebElement registerLink = driver.findElement(By.id("registerLink"));
//        registerLink.click();
//        String expectedUrl = "http://localhost:8080/CupCake/jsp/user/createUser.jsp";
//        String actualUrl = driver.getCurrentUrl();
//        assertEquals(expectedUrl, actualUrl);
//    }
//}
