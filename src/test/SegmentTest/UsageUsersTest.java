import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by johnny on 15/8/19.
 */
public class UsageUsersTest {

    private AppiumDriver<MobileElement> driver;
    private String sessionId;

    @Before
    public void setup() throws Exception {
        File appDir = new File("/Users/johnny/Documents/WorkDocuments/GameAnalysisApp/test-payment/app/build/outputs/apk");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "5.0.1 Lollipop");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "as.leap.test.payment");
        capabilities.setCapability("appActivity", ".activities.MainActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        sessionId = driver.getSessionId().toString();
    }

    @After
    public void tearDown() throws Exception {
//        System.out.println(sessionId);
        driver.quit();
    }

    //用户产生15次session
    @Test
    public void heavyUsageUserTest() throws  InterruptedException{
        AndroidGameFunctions test  = new AndroidGameFunctions(driver);
        test.login();
//        System.out.println(driver.getSessionId().toString());
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生了14次session
    @Test
    public void heavyUsageLowerBoundaryTest() throws  InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生13次session
    @Test
    public void mediumUsageUpperBoundaryTest() throws  InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生8次session
    @Test
    public void mediumUsageLowerBoundaryTest() throws InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生了7次session
    @Test
    public void mediumUsageUserTest()  throws  InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生了6次session
    @Test
    public void lowUsageUpperBoundaryTest() throws InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生了3次session
    @Test
    public void lowUsageUserTest() throws InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生了2次session
    @Test
    public void lowUsageLowerBoundaryTest() throws InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(60000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }

    //用户产生了1次session
    @Test
    public void infrequentUsageUserTest() throws InterruptedException{
        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        driver.findElementByXPath(AndroidPageElements.SESSION_OUT).click();
        Thread.sleep(10000);
    }
}
