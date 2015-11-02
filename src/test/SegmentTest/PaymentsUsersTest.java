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
 * Created by johnny on 15/8/18.
 */
public class PaymentsUsersTest {

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
        sessionId = driver.getSessionId().toString();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(sessionId);
        Thread.sleep(10);
        driver.quit();
    }

    @Test
    public void HighPaymentsLowerBoundaryUserTest() throws  InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 25100;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }

    @Test
    public void HighPaymentsUserTest() throws  InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 125100;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }

    @Test
    public void MediumPaymentsUpperBoundaryUserTest() throws  InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 25000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }

    @Test
    public void MediumPaymentsUserTest() throws InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 8000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }

    @Test
    public void MediumPaymentsLowerBoundaryUserTest() throws  InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 1100;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }

    @Test
    public void LightPaymentsUpperBoundaryUserTest() throws InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 1000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }

    @Test
    public void LightPaymentsUserTest() throws InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 500;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
        }
        System.out.println("购买了" + buyCoinsNum + "个金币，并且消费了" + expenseSum + "美元");
    }
}
