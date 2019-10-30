package presentation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
/**
 *
 * @author Alexander
 */
@RunWith(ParallelParameterized.class)
public abstract class TestBase {

    public DesiredCapabilities capabilities;
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public TestBase(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getBrowserCapabilities() {
         Object[][] data = new Object[][] { { DesiredCapabilities.chrome()}, { DesiredCapabilities.firefox()}};
    return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception {
        RemoteWebDriver webDriver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), capabilities);
        //webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.set(webDriver);
    }

    @After
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    public static void remove() {
        driver.remove();
    }

}
