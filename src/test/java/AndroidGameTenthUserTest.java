import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * Created by johnny on 15/8/13.
 */
public class AndroidGameTenthUserTest{

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
        public void tenthUserTest() throws  InterruptedException{
            Map<String,Integer> m = new HashMap();
            long startTime,endTime,costTimeSum,firstStageCostTime,secondStageCostTime,thirdStageCostTime,fourthStageCostTime,fifthStageCostTime;

            float expenseSum = 0;//累计购买金币需要的总金额
            float expenseNum = 0;//一次购买金币的金额数
            int expenseTimes = 0;//购买金币的消费次数

            int expenseCoins = 0;//购买物品需要的金币数量
            int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量

            int presentCoinsSum = 0;//赠送金币的总量
            int presentGoodsSum = 0;//赠送物品的总量

            int coinsSum = 0;//累计购买金币的总数
            int coinsStay = 0;//累计购买金币的剩余
            int goodsBuySum = 0;//累计购买物品的总数
            int goodsBuyNum = 0;//一次购买物品的数量
            int goodsUseSum = 0;//累计使用物品的总数
            int goodsUseNum = 0;//一次使用物品的数量

            int buyCoinsNum = 0;//需要购买金币的数量
            int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
            int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

            int firstGoodStay = 0;
            int secondGoodStay = 0;
            int thirdGoodStay = 0;//统计物品的剩余数量

            AndroidGameFunctions test = new AndroidGameFunctions(driver);
            test.login();
            startTime = System.currentTimeMillis();
            test.selectStage(1);
            coinsStay = test.getCoinsQuantity();
            Thread.sleep(1000);//此处设置关卡1的冲关时间
            driver.findElementByXPath(AndroidPageElements.DETAILS_BACK).click();

            endTime = System.currentTimeMillis();

            System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
            System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
            System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
            System.out.println("用户10的冲关总时间为：" + ((endTime-startTime)/1000));
        }
}
