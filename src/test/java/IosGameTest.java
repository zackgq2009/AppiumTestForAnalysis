/*
 +Copyright 2014 Appium contributors
 +Copyright 2014 Software Freedom Conservancy
 +
 +Licensed under the Apache License, Version 2.0 (the "License");
 +you may not use this file except in compliance with the License.
 +You may obtain a copy of the License at
 +
 +     http://www.apache.org/licenses/LICENSE-2.0
 +
 +Unless required by applicable law or agreed to in writing, software
 +distributed under the License is distributed on an "AS IS" BASIS,
 +WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 +See the License for the specific language governing permissions and
 +limitations under the License.
 + */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


import oracle.jrockit.jfr.jdkevents.ThrowableTracer;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
//import org.openqa.selenium.ScreenOrientation;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
//import java.util.Iterator;
//import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.lang.System;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

/**
 * Test Mobile Driver features
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IosGameTest {

    private AppiumDriver<MobileElement> driver;
    private String sessionId;
    private byte stageNum;

    @Before
    public void setup() throws Exception {
        File appDir = new File("/Users/johnny/Documents/workspace/xcode/GameTest");
        File app = new File(appDir, "GameTest.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 (8.1 Simulator) [F26F3E2A-95FC-4C24-9AE0-318616F526BC]");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        sessionId = driver.getSessionId().toString();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(sessionId);
        driver.runAppInBackground(10);
        driver.quit();
//        driver.removeApp("as.leap.GameTest");
//        driver.quit();
    }


    @Test
    public void firstUserTest() throws InterruptedException {
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;


        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 1000;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 10;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 1;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡时间
        test.passStageAndNext();
        //第一关卡的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 2;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡时间
        test.passStageAndNext();
        //第二关卡的奖励
        coinsStay = test.secondStageReward(coinsStay);

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 3;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡时间
        test.passStageAndNext();


        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        //第三关卡的奖励需要进入到‘使用物品’的页面上，才能查看，要不就会报错
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        //我们直接用firstGoodStay,secondGoodStay,thirdGoodStay等，是直接把剩余的物品全部用完
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡时间
        test.passStageAndNext();

        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 1000;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 10;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 4;
        useSecondGoodNum = 6;
        useThirdGoodNum = 6;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡时间
        test.passFinalStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户1的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void secondUserTest() throws  InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;


        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 1000;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);//此处可以设置关卡1的通关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 1;
        useSecondGoodNum = 0;
        useThirdGoodNum = 0;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 2;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = buySecondGoodNum = buyThirdGoodNum = 10;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 2;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 5;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡5的第一次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = useSecondGoodNum = useThirdGoodNum = 3;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡5第二次冲关时间
        test.passFinalStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }


    @Test
    public void thirdUserTest() throws InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 1;
        useSecondGoodNum = 0;
        useThirdGoodNum = 0;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处是关卡2的冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处是关卡3的冲关时间
        test.passStageAndNext();
        //关卡3的奖励
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");

        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 3;
        buySecondGoodNum = 3;
        buyThirdGoodNum = 2;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处是关卡4第一次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 200;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 6;
        buySecondGoodNum = 4;
        buyThirdGoodNum = 2;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 4;
        useSecondGoodNum = 3;
        useThirdGoodNum = 1;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4第二次冲关的时间
        test.passStageAndNext();

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处是关卡5第一次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 200;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 6;
        buySecondGoodNum = 4;
        buyThirdGoodNum = 2;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 4;
        useSecondGoodNum = 3;
        useThirdGoodNum = 1;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处是关卡5第二次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 200;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 6;
        buySecondGoodNum = 4;
        buyThirdGoodNum = 2;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 6;
        useSecondGoodNum = 5;
        useThirdGoodNum = 3;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处是关卡5第三次冲关时间
        test.passFinalStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));

    }

    @Test
    public void fouthUserTest() throws  InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        //关卡3的奖励
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4第一次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 3;
        buySecondGoodNum = 3;
        buyThirdGoodNum = 2;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4第二次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 300;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 9;
        buySecondGoodNum = 6;
        buyThirdGoodNum = 3;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 3;
        useSecondGoodNum = 2;
        useThirdGoodNum = 1;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4第三次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4第四次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        buyCoinsNum = 300;
        expenseSum += test.buyCoins(buyCoinsNum);
        coinsSum += buyCoinsNum;
        coinsStay += buyCoinsNum;
        driver.findElementByXPath(IosPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_BUYGOODS).click();
        Thread.sleep(1000);
        buyFirstGoodNum = 9;
        buySecondGoodNum = 6;
        buyThirdGoodNum = 3;
        expenseCoins = test.buyGoods(buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum);
        if (expenseCoins != 0){
            firstGoodStay += buyFirstGoodNum;
            secondGoodStay += buySecondGoodNum;
            thirdGoodStay += buyThirdGoodNum;
            goodsBuySum += (buyFirstGoodNum + buySecondGoodNum + buyThirdGoodNum);
            expenseCoinsSum += expenseCoins;
            coinsStay -= expenseCoins;
        }
        driver.findElementByXPath(IosPageElements.BUYGOODS_BACK).click();
        Thread.sleep(1000);
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4第五次冲关时间
        test.passStageAndNext();

        Thread.sleep(1000);//此处设置关卡5冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void fifthUserTest() throws InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        //关卡3的奖励
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);

        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        Thread.sleep(1000);//此处设置关卡5的冲关时间
        test.passFinalStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void sixthUserTest() throws InterruptedException {
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡2的第二次冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        Thread.sleep(1000);//此处设置关卡3的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡3的第二次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 1;
        useSecondGoodNum = 0;
        useThirdGoodNum = 0;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡3的第三次冲关时间
        test.passStageAndNext();
        //关卡3的奖励
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();

        Thread.sleep(1000);//此处设置关卡4的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡4的第二次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 0;
        useSecondGoodNum = 1;
        useThirdGoodNum = 0;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4的第三次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        useFirstGoodNum = 0;
        useSecondGoodNum = 0;
        useThirdGoodNum = 1;
        expenseGoods = test.useGoods(useFirstGoodNum,useSecondGoodNum,useThirdGoodNum);
        if (expenseGoods != 0){
            firstGoodStay -= useFirstGoodNum;
            secondGoodStay -= useSecondGoodNum;
            thirdGoodStay -= useThirdGoodNum;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4的第四次冲关时间
        test.failStageAndOneMore();
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4的第五次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡4的第六次冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void seventhUserTest() throws  InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        //关卡3的奖励
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();

        Thread.sleep(1000);//此处设置关卡4的第一次冲关时间
        test.failStageAndOneMore();
        Thread.sleep(1000);//此处设置关卡5的第二次冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void eighthUserTest() throws InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.passStageAndNext();
        //关卡2的奖励
        coinsStay = test.secondStageReward(coinsStay);

        Thread.sleep(1000);//此处设置关卡3的冲关时间
        test.passStageAndNext();
        //关卡3的奖励
        driver.findElementByXPath(IosPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);
        m = test.thirdStageReward(firstGoodStay,secondGoodStay,thirdGoodStay,coinsStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");
        coinsStay = m.get("coinsStay");

        expenseGoods = test.useGoods(firstGoodStay,secondGoodStay,thirdGoodStay);
        if (expenseGoods != 0){
            firstGoodStay -= firstGoodStay;
            secondGoodStay -= secondGoodStay;
            thirdGoodStay -= thirdGoodStay;
            goodsUseSum += expenseGoods;
        }
        driver.findElementByXPath(IosPageElements.USEGOODS_BACK).click();
        Thread.sleep(1000);//此处设置关卡4的冲关时间
        test.passStageAndNext();

        Thread.sleep(1000);//此处设置关卡5的冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void ninthUserTest() throws  InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        Thread.sleep(1000);//此处设置关卡1的冲关时间
        test.passStageAndNext();
        //关卡1的奖励
        m = test.firstStageReward(firstGoodStay,secondGoodStay,thirdGoodStay);
        firstGoodStay = m.get("firstGoodStay");
        secondGoodStay = m.get("secondGoodStay");
        thirdGoodStay = m.get("thirdGoodStay");

        Thread.sleep(1000);//此处设置关卡2的冲关时间
        test.failStageAndBack();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

    @Test
    public void tenthUserTest() throws InterruptedException{
        Map<String,Integer> m = new HashMap();
        long startTime = System.currentTimeMillis();
        long endTime;

        float expenseSum = 0;//购买金币需要的金额
        int expenseCoins = 0;//一次 购买物品需要的金币数量
        int expenseCoinsSum = 0;//金币的使用总量，由于金币有赠送，所以金币购买总量减去金币剩余量并不等于金币使用总量
        int expenseGoods = 0;//一次 使用物品的总数

        int coinsSum = 0;//累计购买金币的总数
        int coinsStay = 0;//累计购买金币的剩余
        int goodsBuySum = 0;//累计购买物品的总数
        int goodsUseSum = 0;//累计使用物品的总数

        int buyCoinsNum = 0;//需要购买金币的数量
        int buyFirstGoodNum,buySecondGoodNum,buyThirdGoodNum;//需要购买物品的数量
        int useFirstGoodNum,useSecondGoodNum,useThirdGoodNum;//需要使用物品的数量

        int firstGoodStay = 0;
        int secondGoodStay = 0;
        int thirdGoodStay = 0;//统计物品的剩余数量

        IosGameFunctions test = new IosGameFunctions(driver);
        test.login();
        test.selectStage(1);
        driver.findElementByXPath(IosPageElements.DETAILS_EXIT).click();

        endTime = System.currentTimeMillis();

        System.out.println("总消费金额：" + expenseSum + "     金币总购买量：" + coinsSum + "     金币的使用总量：" + expenseCoinsSum + "     账户剩余金币数量：" + coinsStay + "     物品总的购买量：" + goodsBuySum + "     物品总的使用量：" + goodsUseSum);
        System.out.println("物品1的数量为：" + firstGoodStay + "     物品2的数量为：" + secondGoodStay + "     物品3的数量为：" + thirdGoodStay);
        System.out.println("用户2的冲关总时间为：" + ((endTime-startTime)/1000));
    }

}























