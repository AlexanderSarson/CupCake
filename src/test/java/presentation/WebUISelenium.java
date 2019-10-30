//package presentation;
//
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
///**
// *
// * @author alex
// */
//public class WebUISelenium extends TestBase {
//
//    public WebUISelenium(DesiredCapabilities capabilities) {
//        super(capabilities);
//    }
//    
//    @Test
//    public void WebUI_Login_User() throws InterruptedException {
//        WebDriver driver = getDriver();
//        driver.get("http://134.209.233.32/CupCakeDev");
//        //login
//        Actions actions = new Actions(driver);
//        WebElement loginMenu = driver.findElement(By.id("userNav"));
//        actions.moveToElement(loginMenu).perform();
//        WebElement loginMenuEmail = driver.findElement(By.id("loginEmail"));
//        loginMenuEmail.sendKeys("Selenium@mail.com");
//        WebElement loginMenuPassword = driver.findElement(By.id("loginPassword"));
//        loginMenuPassword.sendKeys("Password");
//        WebElement loginMenuButton = driver.findElement(By.xpath("//button[text()='Login']"));
//        loginMenuButton.click();
//        
//        //go to shop
//        WebElement shop = driver.findElement(By.xpath("//*[contains(text(), 'Shop') ]"));
//        shop.click();
//    }
//
//}
