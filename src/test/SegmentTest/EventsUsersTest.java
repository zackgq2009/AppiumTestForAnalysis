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
 * Created by johnny on 15/8/21.
 */
public class EventsUsersTest {
    /*
    购买货币时会触发三种EVENTS，
    分别是25000购买时--》event 'ASDFASDF',key 'name', value 'guoqing'
    8000购买时--》event 'asdfasdf',key 'age', value '31'
    3000购买时--》event 'qwerqwer',key 'height', value '184'
     */

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

    //先设定name event触发10次
    @Test
    public void NameEventUserTest() throws InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量
        int coinsSum = 0;//购买金币的总量
        int buyCoinsTime = 0;//购买金币的次数

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 250000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            buyCoinsTime++;
        }
        System.out.println("用户通过" + buyCoinsTime + "次，购买了" + coinsSum + "个金币，并且消费了" + expenseSum + "美元");
    }

    //设定age event触发8次
    @Test
    public void AgeEventUserTest() throws  InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量
        int coinsSum = 0;//购买金币的总量
        int buyCoinsTime = 0;//购买金币的次数

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 16000;
        for (int i=0;i<4;i++){
            expenseNum = test.enterAndBuyCoins(buyCoinsNum);
            if (expenseNum != 0){
                expenseSum += expenseNum;
                coinsSum += buyCoinsNum;
                buyCoinsTime++;
            }
        }
        System.out.println("用户通过" + buyCoinsTime + "次，购买了" + coinsSum + "个金币，并且消费了" + expenseSum + "美元");
    }

    //设定height event触发6次
    @Test
    public void HeightEventUserTest() throws InterruptedException{
        float expenseSum = 0;//累计购买金币需要的总金额
        float expenseNum = 0;//一次购买金币的金额数
        int buyCoinsNum = 0;//需要购买金币的数量
        int coinsSum = 0;//购买金币的总量
        int buyCoinsTime = 0;//购买金币的次数

        AndroidGameFunctions test = new AndroidGameFunctions(driver);
        test.login();
        test.selectStage(1);
        buyCoinsNum = 6000;
        for (int i=0;i<3;i++){
            expenseNum = test.enterAndBuyCoins(buyCoinsNum);
            if (expenseNum != 0){
                expenseSum += expenseNum;
                coinsSum += buyCoinsNum;
                buyCoinsTime++;
            }
        }
        System.out.println("用户通过" + buyCoinsTime + "次，购买了" + coinsSum + "个金币，并且消费了" + expenseSum + "美元");
    }
}
