import com.sun.xml.internal.ws.server.DefaultResourceInjector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
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
 * Created by johnny on 15/8/5.
 */
public class AndroidGameTest {

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
            capabilities.setCapability("appPackage", "com.maxleap.test.payment");
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
//        driver.removeApp("as.leap.GameTest");
//        driver.quit();
        }

//    @Test
//    public void test() throws InterruptedException {
//        System.out.println(driver.isAppInstalled("as.leap.test.payment"));
//        AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) driver;
//        System.out.println(androidDriver.currentActivity());
//        androidDriver.removeApp("as.leap.test.payment");
//    }

    @Test
    public void firstUserTest() throws InterruptedException{
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
        buyCoinsNum = 1000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
            expenseTimes++;
        }
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 10;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 1;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 2;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;


        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 3;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        buyCoinsNum = 1000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            expenseTimes++;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
        }
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 10;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = 4;
        useSecondGoodNum = 6;
        useThirdGoodNum = 6;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡5的冲关时间
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPASS).click();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户1的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void secondUserTest() throws  InterruptedException{
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
        buyCoinsNum = 1000;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            expenseTimes++;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
        }
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        coinsStay += 100;
        presentCoinsSum += 100;

        useFirstGoodNum = 1;
        useSecondGoodNum = 0;
        useThirdGoodNum = 0;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 2;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
        }
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 10;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
            goodsBuySum += goodsBuyNum;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 2;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 5;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡5的第一次冲关时间
        test.failStageAndOneMore();
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 3;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡5的第二次冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void thirdUserTest() throws InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum +=3;

        useFirstGoodNum = 1;
        useSecondGoodNum = useThirdGoodNum = 0;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;

        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
        }
        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
        }
        buyFirstGoodNum = 3;
        buySecondGoodNum = 3;
        buyThirdGoodNum = 2;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
        }
        Thread.sleep(1000);//此处设置关卡4的第一次冲关时间
        test.failStageAndOneMore();
        buyCoinsNum = 200;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
            expenseTimes++;
        }
        buyFirstGoodNum = 6;
        buySecondGoodNum = 4;
        buyThirdGoodNum = 2;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = 4;
        useSecondGoodNum = 3;
        useThirdGoodNum = 1;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第二次冲关时间
        test.passStageAndNext();

        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡5的第一次冲关时间
        test.failStageAndOneMore();
        buyCoinsNum = 200;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
            expenseTimes++;
        }
        buyFirstGoodNum = 6;
        buySecondGoodNum = 4;
        buyThirdGoodNum = 2;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = 4;
        useSecondGoodNum = 3;
        useThirdGoodNum = 1;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡5的第二次冲关时间
        test.failStageAndOneMore();
        buyCoinsNum = 200;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
            expenseTimes++;
        }
        buyFirstGoodNum = 6;
        buySecondGoodNum = 4;
        buyThirdGoodNum = 2;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = 6;
        useSecondGoodNum = 5;
        useThirdGoodNum = 3;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡5的第三次冲关时间
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPASS).click();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户3的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void fourthUserTest() throws InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第一次冲关时间
        test.failStageAndOneMore();
        buyFirstGoodNum = 3;
        buySecondGoodNum = 3;
        buyThirdGoodNum = 2;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第二次冲关时间
        test.failStageAndOneMore();
        buyCoinsNum = 300;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
            expenseTimes++;
        }
        buyFirstGoodNum = 9;
        buySecondGoodNum = 6;
        buyThirdGoodNum = 3;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        useFirstGoodNum = 3;
        useSecondGoodNum = 2;
        useThirdGoodNum = 1;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第三次冲关时间
        test.failStageAndOneMore();
        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第四次冲关时间
        test.failStageAndOneMore();
        buyCoinsNum = 300;
        expenseNum = test.enterAndBuyCoins(buyCoinsNum);
        if (expenseNum != 0){
            expenseSum += expenseNum;
            coinsSum += buyCoinsNum;
            coinsStay += buyCoinsNum;
            expenseTimes++;
        }
        buyFirstGoodNum = 9;
        buySecondGoodNum = 6;
        buyThirdGoodNum = 3;
        goodsBuyNum = (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
        expenseCoins = test.enterAndBuyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            goodsBuySum += goodsBuyNum;
            coinsStay -= expenseCoins;
            expenseCoinsSum += expenseCoins;
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
        }
        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第五次冲关时间
        test.passStageAndNext();

        Thread.sleep(1000);//此处设置关卡5的冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户4的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void fifthUserTest() throws  InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        Thread.sleep(1000);//此处设置关卡5的冲关时间
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPASS).click();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户5的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void sixthUserTest() throws  InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡2的第二次冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;

        Thread.sleep(1000);//此处设置关卡3的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡3的第二次冲关时间
        test.failStageAndOneMore();
        useFirstGoodNum = 1;
        useSecondGoodNum = useThirdGoodNum = 0;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡3的第三次冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        Thread.sleep(1000);//此处设置关卡4的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡4的第二次冲关时间
        test.failStageAndOneMore();
        useSecondGoodNum = 1;
        useFirstGoodNum = useThirdGoodNum = 0;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第三次冲关时间
        test.failStageAndOneMore();
        useThirdGoodNum = 1;
        useFirstGoodNum = useSecondGoodNum = 0;
        goodsUseNum = test.enterAndUseGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (goodsUseNum != 0){
            goodsUseSum += goodsUseNum;
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第四次冲关时间
        test.failStageAndOneMore();
        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的第五次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡4的第六次冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户6的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void seventhUserTest() throws  InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        Thread.sleep(1000);//此处设置关卡4的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡4的第二次冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户7的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void eighthUserTest() throws  InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        presentCoinsSum += 100;
        coinsStay += 100;

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        presentCoinsSum += 50;
        presentGoodsSum++;
        driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        goodsUseNum = test.enterAndUseGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (goodsUseNum != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += goodsUseNum;
        }
        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        Thread.sleep(1000);//此处设置关卡5的冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户8的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void ninthUserTest() throws InterruptedException{
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
        test.passStageAndNext();
        firstGoodStay++;secondGoodStay++;thirdGoodStay++;
        presentGoodsSum += 3;

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     消费次数：" + expenseTimes + "     金币总购买量：" + coinsSum + "     金币总赠送量：" + presentCoinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币的数量：" + coinsStay);
        System.out.println("物品的总购买量：" + goodsBuySum + "     物品总的赠送量：" + presentGoodsSum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户9的冲关总时间为：" + ((endTime-startTime)/1000));
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



