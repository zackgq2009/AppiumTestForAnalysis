import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.xpath.operations.And;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johnny on 15/8/5.
 */
public class AndroidGameFunctions {
    private AppiumDriver<MobileElement> driver;

    public AndroidGameFunctions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void login() throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.LOGIN_PLAY).click();
        Thread.sleep(1000);
    }

    //获取到有几个关卡
    public int getStageQuantity(){
        List<MobileElement> stageList = driver.findElementsByXPath(AndroidPageElements.SELECT_GAMESTAGELIST);
        return stageList.size();
    }

    //根据输入的关卡序列号码，进入相应的关卡
    public void selectStage(int stageNum) throws InterruptedException{
        int stageQuantity = this.getStageQuantity();
        if (stageNum > 0 && stageNum <= stageQuantity){
            driver.findElementByName("关卡" + stageNum).click();
        }
        Thread.sleep(1000);
    }

    //通过购买金币的数量来判断出购买金币需要的金额数，并且把金额数返回出来
    public float getExpenseValue(int coinsNum){
        String expenseValue = "";
        float expenseMoney = 0;
        if (coinsNum >= 25000){
            expenseValue = driver.findElementByXPath(AndroidPageElements.BUYCOINS_TWENTYFIVETHOUSANDMONEY).getAttribute("text");
        } else if (coinsNum >= 8000 && coinsNum < 25000){
            expenseValue = driver.findElementByXPath(AndroidPageElements.BUYCOINS_EIGHTTHOUSANDMONEY).getAttribute("text");
        } else if (coinsNum >= 3000 && coinsNum < 8000){
            expenseValue = driver.findElementByXPath(AndroidPageElements.BUYCOINS_THREETHOUSANDMONEY).getAttribute("text");
        } else if (coinsNum >= 1000 && coinsNum < 3000){
            expenseValue = driver.findElementByXPath(AndroidPageElements.BUYCOINS_ONETHOUSANDMONEY).getAttribute("text");
        } else if (coinsNum >= 100 && coinsNum < 1000){
            expenseValue = driver.findElementByXPath(AndroidPageElements.BUYCOINS_ONEHUNDREDMONEY).getAttribute("text");
        } else {
            System.out.println("购买金币的数量最少100个，请更改您购买金币的数量以100为基数");
        }
        if (expenseValue.startsWith("$")){
            expenseMoney = Float.parseFloat(expenseValue.substring(1));
            return expenseMoney;
        } else {
            return expenseMoney;
        }
    }

    //获取到三种物品的数量，通过传入物品的序列号，返回该物品的数量
    public int getBuyGoodsQuantity(int whichGood){
        String goodQuantity = "";
        if (whichGood >= 1 && whichGood <= 3){
            switch (whichGood){
                case 1: goodQuantity = driver.findElementByXPath(AndroidPageElements.BUYGOODS_GOODONEQUANTITY).getAttribute("text");break;
                case 2: goodQuantity = driver.findElementByXPath(AndroidPageElements.BUYGOODS_GOODTWOQUANTITY).getAttribute("text");break;
                case 3: goodQuantity = driver.findElementByXPath(AndroidPageElements.BUYGOODS_GOODTHREEQUANTITY).getAttribute("text");break;
            }
            return Integer.parseInt(goodQuantity);
        } else {
            System.out.println("物品只有三种，请输入合法的物品序列号1、2、3");
            return 0;
        }
    }

    //获取使用物品页面中物品的数量
    public int getUseGoodsQuantity(int whichGood){
        String goodQuantity = "";
        if (whichGood >= 1 && whichGood <= 3){
            switch (whichGood){
                case 1: goodQuantity = driver.findElementByXPath(AndroidPageElements.USEGOODS_GOODONEQUANTITY).getAttribute("text");break;
                case 2: goodQuantity = driver.findElementByXPath(AndroidPageElements.USEGOODS_GOODTWOQUANTITY).getAttribute("text");break;
                case 3: goodQuantity = driver.findElementByXPath(AndroidPageElements.USEGOODS_GOODTHREEQUANTITY).getAttribute("text");break;
            }
            return Integer.parseInt(goodQuantity);
        } else {
            System.out.println("物品只有三种，请输入合法的物品序列号1、2、3");
            return 0;
        }
    }

    //直接获取到关卡详情页中金币的数量
    public int getCoinsQuantity(){
        String coinsQuantity;
        coinsQuantity = driver.findElementByXPath(AndroidPageElements.DETAILS_COINSQUANTITY).getAttribute("text");
        return Integer.parseInt(coinsQuantity);
    }

    //购买金币的全过程，用户只需要在关卡详情页中就能实现购买金币过程，进入退出购买金币操作也一并完成,最终也会返回花费了多少银子
    public float enterAndBuyCoins(int coinsNum) throws  InterruptedException{
        float expenseSum = 0;

        driver.findElementByXPath(AndroidPageElements.DETAILS_BUYCOINS).click();
        Thread.sleep(1000);
        expenseSum = this.buyCoins(coinsNum);
        driver.findElementByXPath(AndroidPageElements.BUYCOINS_BACK).click();
        Thread.sleep(1000);
        return expenseSum;
    }

    //购买金币方法，购买金币的最小数量级为100个，当有小于100个的时候，则不进行购买，直接返回为0，最终返回的是购买这次金币花费了多少银子
    public float buyCoins(int coinsNum) throws InterruptedException {
        float expenseSum = 0;//消费总金额
        float expenseMoney = 0;//消费金额

        //判断购买金币的数量
        if ((coinsNum >= 100) && (coinsNum%100 == 0)){
            if (coinsNum >= 25000) {
                expenseMoney = this.getExpenseValue(coinsNum);
                if (coinsNum % 25000 == 0){
                    while (coinsNum >= 25000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_TWENTYFIVETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 25000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 25000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_TWENTYFIVETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 25000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum+this.buyCoins(coinsNum);
                }
            } else if (coinsNum >= 8000 && coinsNum < 25000) {
                expenseMoney = this.getExpenseValue(coinsNum);
                if (coinsNum % 8000 == 0){
                    while (coinsNum >= 8000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_EIGHTTHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 8000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 8000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_EIGHTTHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 8000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum+this.buyCoins(coinsNum);
                }
            } else if (coinsNum >= 3000 && coinsNum < 8000){
                expenseMoney = this.getExpenseValue(coinsNum);
                if (coinsNum % 3000 == 0){
                    while (coinsNum >= 3000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_THREETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 3000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 3000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_THREETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 3000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum+this.buyCoins(coinsNum);
                }
            } else if (coinsNum >= 1000 && coinsNum < 3000){
                expenseMoney = this.getExpenseValue(coinsNum);
                if (coinsNum % 1000 == 0){
                    while (coinsNum >= 1000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_ONETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 1000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 1000){
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_ONETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                        Thread.sleep(1000);
                        coinsNum -= 1000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum+this.buyCoins(coinsNum);
                }
            } else if (coinsNum >= 100 && coinsNum < 1000){
                expenseMoney = this.getExpenseValue(coinsNum);
                while (coinsNum >= 100){
                    driver.findElementByXPath(AndroidPageElements.BUYCOINS_ONEHUNDREDLINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(AndroidPageElements.BUYCOINS_OK).click();
                    Thread.sleep(1000);
                    coinsNum -= 100;
                    expenseSum += expenseMoney;
                }
                return expenseSum;
            } else {
                System.out.println("购买金币的数量最少100个，请更改您购买金币的数量以100为基数");
                return 0;
            }
        } else {
            System.out.println("购买金币的数量最少100个，请更改您购买金币的数量以100为基数");
            return 0;
        }
    }

    //购买物品，需要传入购买物品的数量，以及系统从关卡详情页中获取到的金币数量，最终返回购买物品所需的所有金币总数
    public int enterAndBuyGoods(int buyFirstGoodNum,int buySecondGoodNum,int buyThirdGoodNum) throws InterruptedException{
        int coinsStay = this.getCoinsQuantity();
        int useCoinsCount = 0;

        if (buyFirstGoodNum >0 || buySecondGoodNum > 0 || buyThirdGoodNum > 0){
            useCoinsCount = buyFirstGoodNum*10 + buySecondGoodNum*20 + buyThirdGoodNum*30;
            if (useCoinsCount > coinsStay){
                System.out.println("购买这次物品需要更多的金币，您可以购买更多的金币或者减少物品的购买数量");
                return 0;
            } else {
                driver.findElementByXPath(AndroidPageElements.DETAILS_BUYGOODS).click();
                Thread.sleep(1000);
                if (buyFirstGoodNum != 0) {
                    while (buyFirstGoodNum != 0){
                        driver.findElementByXPath(AndroidPageElements.BUYGOODS_GOODONELINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYGOODS_OK).click();
                        Thread.sleep(1000);
                        buyFirstGoodNum--;
                    }
                }
                if (buySecondGoodNum != 0) {
                    while (buySecondGoodNum != 0){
                        driver.findElementByXPath(AndroidPageElements.BUYGOODS_GOODTWOLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYGOODS_OK).click();
                        Thread.sleep(1000);
                        buySecondGoodNum--;
                    }
                }
                if (buyThirdGoodNum != 0) {
                    while (buyThirdGoodNum != 0){
                        driver.findElementByXPath(AndroidPageElements.BUYGOODS_GOODTHREELINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.BUYGOODS_OK).click();
                        Thread.sleep(1000);
                        buyThirdGoodNum--;
                    }
                }
                driver.findElementByXPath(AndroidPageElements.BUYGOODS_BACK).click();
                Thread.sleep(1000);
                return useCoinsCount;
            }
        } else return useCoinsCount;
    }

    //使用物品，需要传入使用物品的数量，当需要使用物品的数量超过现有物品的数量，则不使用任何物品,返回的是使用物品的总数
    public int enterAndUseGoods(int useFirstGoodNum,int useSecondGoodNum,int useThirdGoodNum) throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_USEGOODS).click();
        Thread.sleep(1000);

        int useGoodsCount = 0;
        int firstGoodsCount = this.getUseGoodsQuantity(1);
        int secondGoodsCount = this.getUseGoodsQuantity(2);
        int thirdGoodsCount = this.getUseGoodsQuantity(3);

        if (useFirstGoodNum > 0 || useSecondGoodNum > 0 || useThirdGoodNum > 0){
            if (useFirstGoodNum > firstGoodsCount || useSecondGoodNum > secondGoodsCount || useThirdGoodNum > thirdGoodsCount){
                System.out.println("您使用的物品数中已经有超过该物品的拥有量，请购买更多的物品或者减少这次物品的使用量");
                driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
                Thread.sleep(1000);
                return 0;
            } else {
                useGoodsCount = (useFirstGoodNum + useSecondGoodNum + useThirdGoodNum);
                if (useFirstGoodNum != 0){
                    while (useFirstGoodNum != 0){
                        driver.findElementByXPath(AndroidPageElements.USEGOODS_GOODONELINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.USEGOODS_OK).click();
                        Thread.sleep(1000);
                        useFirstGoodNum--;
                    }
                }
                if (useSecondGoodNum != 0){
                    while (useSecondGoodNum != 0){
                        driver.findElementByXPath(AndroidPageElements.USEGOODS_GOODTWOLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.USEGOODS_OK).click();
                        Thread.sleep(1000);
                        useSecondGoodNum--;
                    }
                }
                if (useThirdGoodNum != 0){
                    while (useThirdGoodNum != 0){
                        driver.findElementByXPath(AndroidPageElements.USEGOODS_GOODTHREELINK).click();
                        Thread.sleep(1000);
                        driver.findElementByXPath(AndroidPageElements.USEGOODS_OK).click();
                        Thread.sleep(1000);
                        useThirdGoodNum--;
                    }
                }
                driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
                Thread.sleep(1000);
                return useGoodsCount;
            }
        } else {
            driver.findElementByXPath(AndroidPageElements.USEGOODS_BACK).click();
            Thread.sleep(1000);
            return useGoodsCount;
        }
    }

    //冲关成功并下一关
    public void passStageAndNext() throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPASS).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.STAGEPASS_NEXT).click();
        Thread.sleep(1000);
    }

    //冲关成功并重玩
    public void passStageAndOneMore() throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPASS).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.STAGEPASS_ONEMORE).click();
        Thread.sleep(1000);
    }

    //最后一关冲关成功并重玩
    public void passFinalStageAndOneMore() throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPASS).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.FINALSTAGEPASS_ONEMORE).click();
        Thread.sleep(1000);
    }

    //冲关失败并返回
    public void failStageAndBack() throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEFAIL).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.STAGEFAIL_BACK).click();
        Thread.sleep(1000);
    }

    //冲关失败并重玩
    public void failStageAndOneMore() throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEFAIL).click();
        Thread.sleep(1000);
        driver.findElementByXPath(AndroidPageElements.STAGEFAIL_ONEMORE).click();
        Thread.sleep(1000);
    }

    //暂停冲关，并输入暂停时间
    public void pauseStage(int pauseTime) throws InterruptedException{
        driver.findElementByXPath(AndroidPageElements.DETAILS_STAGEPAUSE).click();
        Thread.sleep(pauseTime);
        driver.findElementByXPath(AndroidPageElements.STAGEPAUSE_OK).click();
        Thread.sleep(1000);
    }

    //第一关的奖励
    public Map<String, Integer> firstStageReward(int firstGoodStay,int secondGoodStay,int thirdGoodStay){
        Map m = new HashMap();
        firstGoodStay += 1;
        secondGoodStay += 1;
        thirdGoodStay += 1;
        m.put("firstGoodStay",firstGoodStay);
        m.put("secondGoodStay",secondGoodStay);
        m.put("thirdGoodStay",thirdGoodStay);

        return m;
    }

    //第二关卡的奖励
    public int secondStageReward(int coinsStay){
        coinsStay += 100;
        return coinsStay;
    }

    //第三关卡的奖励
    public Map<String,Integer> thirdStageReward(int firstGoodStay,int secondGoodStay,int thirdGoodStay,int coinsStay){
        Map m = new HashMap();
        if (firstGoodStay != this.getUseGoodsQuantity(1)){
            firstGoodStay += 1;
        } else if (secondGoodStay != this.getUseGoodsQuantity(2)){
            secondGoodStay += 1;
        } else if (thirdGoodStay != this.getUseGoodsQuantity(3)){
            thirdGoodStay += 1;
        } else {
            System.out.println("系统奖励出现问题");
        }
        coinsStay += 50;
        m.put("firstGoodStay",firstGoodStay);
        m.put("secondGoodStay",secondGoodStay);
        m.put("thirdGoodStay",thirdGoodStay);
        m.put("coinsStay",coinsStay);

        return m;
    }
}
