import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johnny on 15/7/30.
 */
public class IosGameFunctions {
    private AppiumDriver<MobileElement> driver;

    public IosGameFunctions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

//    public IosGameFunctions() {
//    }

    public void login() throws InterruptedException{
        driver.findElementByXPath(IosPageElements.LOGIN_PLAY).click();
        Thread.sleep(1000);
    }

    //获取到有几个关卡
    public int getStageQuantity(){
        List<MobileElement> stageList = driver.findElementsByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell");
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

    //通过购买金币的数量来判断出购买金币需要的金额数
    public float getExpenseValue(int coinsNum){
        String expenseValue = "";
        float expenseMoney = 0;
        if (coinsNum >= 25000){
            expenseValue = driver.findElementByXPath(IosPageElements.BUYCOINS_TWENTYFIVETHOUSANDMONEY).getAttribute("value");
        } else if (coinsNum >= 8000 && coinsNum < 25000){
            expenseValue = driver.findElementByXPath(IosPageElements.BUYCOINS_EIGHTTHOUSANDMONEY).getAttribute("value");
        } else if (coinsNum >= 3000 && coinsNum < 8000){
            expenseValue = driver.findElementByXPath(IosPageElements.BUYCOINS_THREETHOUSANDMONEY).getAttribute("value");
        } else if (coinsNum >= 1000 && coinsNum < 3000){
            expenseValue = driver.findElementByXPath(IosPageElements.BUYCOINS_ONETHOUSANDMONEY).getAttribute("value");
        } else if (coinsNum >= 100 && coinsNum < 1000){
            expenseValue = driver.findElementByXPath(IosPageElements.BUYCOINS_ONEHUNDREDMONEY).getAttribute("value");
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

    //在购买物品页面中获取到各个物品需要的金币数量
    public int getCoinsNeed(int whichGood){
        String coinsNeed = "";
        Pattern p = Pattern.compile("\\d+");
        if (whichGood >= 1 && whichGood <= 3){
            switch (whichGood){
                case 1: coinsNeed = driver.findElementByXPath(IosPageElements.BUYGOODS_GOODONEEXPENSE).getAttribute("value");break;
                case 2: coinsNeed = driver.findElementByXPath(IosPageElements.BUYGOODS_GOODTWOEXPENSE).getAttribute("value");break;
                case 3: coinsNeed = driver.findElementByXPath(IosPageElements.BUYGOODS_GOODTHREEEXPENSE).getAttribute("value");break;
            }
            Matcher matcher = p.matcher(coinsNeed);
            while (matcher.find()){
                return Integer.parseInt(matcher.group());
            }
            return 0;
        } else {
            System.out.println("物品只有三种，请输入合法的物品序列号1、2、3");
            return 0;
        }
    }

    //获取到三种物品的数量，通过传入物品的序列号，返回该物品的数量
    public int getBuyGoodsQuantity(int whichGood){
        String goodQuantity = "";
        if (whichGood >= 1 && whichGood <= 3){
            switch (whichGood){
                case 1: goodQuantity = driver.findElementByXPath(IosPageElements.BUYGOODS_GOODONEQUANTITY).getAttribute("value");break;
                case 2: goodQuantity = driver.findElementByXPath(IosPageElements.BUYGOODS_GOODTWOQUANTITY).getAttribute("value");break;
                case 3: goodQuantity = driver.findElementByXPath(IosPageElements.BUYGOODS_GOODTHREEQUANTITY).getAttribute("value");break;
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
                case 1: goodQuantity = driver.findElementByXPath(IosPageElements.USEGOODS_GOODONEQUANTITY).getAttribute("value");break;
                case 2: goodQuantity = driver.findElementByXPath(IosPageElements.USEGOODS_GOODTWOQUANTITY).getAttribute("value");break;
                case 3: goodQuantity = driver.findElementByXPath(IosPageElements.USEGOODS_GOODTHREEQUANTITY).getAttribute("value");break;
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
        coinsQuantity = driver.findElementByXPath(IosPageElements.DETAILS_COINSQUANTITY).getAttribute("value");
        return Integer.parseInt(coinsQuantity);
    }

    //直接获取到购买金币、购买物品、使用物品等页面中的金币数量
    public int getCoinsStay(){
        String coinsStay;
        //直接获取到页面元素
        coinsStay = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]").getAttribute("value");
        //制定好我的正则
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher;
        if (coinsStay.startsWith("coins")){
            matcher = p.matcher(coinsStay);
            while (matcher.find()){
//                System.out.println(matcher.group());
                return Integer.parseInt(matcher.group());
            }
        } else {
            return Integer.parseInt(coinsStay);
        }
        return 0;
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
                        driver.findElementByXPath(IosPageElements.BUYCOINS_TWENTYFIVETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
                        Thread.sleep(1000);
                        coinsNum -= 25000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 25000){
                        driver.findElementByXPath(IosPageElements.BUYCOINS_TWENTYFIVETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
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
                        driver.findElementByXPath(IosPageElements.BUYCOINS_EIGHTTHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
                        Thread.sleep(1000);
                        coinsNum -= 8000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 8000){
                        driver.findElementByXPath(IosPageElements.BUYCOINS_EIGHTTHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
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
                        driver.findElementByXPath(IosPageElements.BUYCOINS_THREETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
                        Thread.sleep(1000);
                        coinsNum -= 3000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 3000){
                        driver.findElementByXPath(IosPageElements.BUYCOINS_THREETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
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
                        driver.findElementByXPath(IosPageElements.BUYCOINS_ONETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
                        Thread.sleep(1000);
                        coinsNum -= 1000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum;
                } else {
                    while (coinsNum >= 1000){
                        driver.findElementByXPath(IosPageElements.BUYCOINS_ONETHOUSANDLINK).click();
                        Thread.sleep(1000);
                        driver.findElementByName("成功").click();
                        Thread.sleep(1000);
                        coinsNum -= 1000;
                        expenseSum += expenseMoney;
                    }
                    return expenseSum+this.buyCoins(coinsNum);
                }
            } else if (coinsNum >= 100 && coinsNum < 1000){
                expenseMoney = this.getExpenseValue(coinsNum);
                while (coinsNum >= 100){
                    driver.findElementByXPath(IosPageElements.BUYCOINS_ONEHUNDREDLINK).click();
                    Thread.sleep(1000);
                    driver.findElementByName("成功").click();
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

    //购买物品，需要传入购买物品的数量，返回购买物品所需的所有金币总数
    public int buyGoods(int firstGoodNum,int secondGoodNum,int thirdGoodNum) throws InterruptedException{
        int useCoinsCount = 0;
        if (firstGoodNum >0 || secondGoodNum > 0 || thirdGoodNum > 0){
            useCoinsCount = firstGoodNum*this.getCoinsNeed(1) + secondGoodNum*this.getCoinsNeed(2) + thirdGoodNum*this.getCoinsNeed(3);
            if (useCoinsCount > this.getCoinsStay()){
                System.out.println("购买这次物品需要更多的金币，您可以购买更多的金币或者减少物品的购买数量");
                return 0;
            } else {
                if (firstGoodNum != 0) {
                    driver.findElementByXPath(IosPageElements.BUYGOODS_GOODONELINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(IosPageElements.BUYGOODS_INPUT).clear();
                    driver.findElementByXPath(IosPageElements.BUYGOODS_INPUT).sendKeys(Integer.toString(firstGoodNum));
                    driver.findElementByName("购买").click();
                    Thread.sleep(1000);
                }
                if (secondGoodNum != 0) {
                    driver.findElementByXPath(IosPageElements.BUYGOODS_GOODTWOLINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(IosPageElements.BUYGOODS_INPUT).clear();
                    driver.findElementByXPath(IosPageElements.BUYGOODS_INPUT).sendKeys(Integer.toString(secondGoodNum));
                    driver.findElementByName("购买").click();
                    Thread.sleep(1000);
                }
                if (thirdGoodNum != 0) {
                    driver.findElementByXPath(IosPageElements.BUYGOODS_GOODTHREELINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(IosPageElements.BUYGOODS_INPUT).clear();
                    driver.findElementByXPath(IosPageElements.BUYGOODS_INPUT).sendKeys(Integer.toString(thirdGoodNum));
                    driver.findElementByName("购买").click();
                    Thread.sleep(1000);
                }
                return useCoinsCount;
            }
        } else return 0;
    }

    //使用物品，需要传入使用物品的数量，当需要使用物品的数量超过现有物品的数量，则不使用任何物品,返回的是使用物品的总数
    public int useGoods(int firstGoodNum,int secondGoodNum,int thirdGoodNum) throws InterruptedException{
        int useGoodsCount = 0;
        int useFirstGoodsCount = this.getUseGoodsQuantity(1);
        int useSecondGoodsCount = this.getUseGoodsQuantity(2);
        int useThirdGoodsCount = this.getUseGoodsQuantity(3);
        if (firstGoodNum > 0 || secondGoodNum > 0 || thirdGoodNum > 0){
            useGoodsCount = firstGoodNum + secondGoodNum + thirdGoodNum;
            if (firstGoodNum > useFirstGoodsCount && secondGoodNum > useSecondGoodsCount && thirdGoodNum > useThirdGoodsCount){
                System.out.println("您使用的物品数已经超过拥有的物品量，请购买更多的物品或者减少这次物品的使用量");
                return 0;
            } else {
                if (firstGoodNum != 0){
                    driver.findElementByXPath(IosPageElements.USEGOODS_GOODONELINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(IosPageElements.USEGOODS_INPUT).clear();
                    driver.findElementByXPath(IosPageElements.USEGOODS_INPUT).sendKeys(Integer.toString(firstGoodNum));
                    driver.findElementByXPath(IosPageElements.USEGOODS_USE).click();
                    Thread.sleep(1000);
                }
                if (secondGoodNum != 0){
                    driver.findElementByXPath(IosPageElements.USEGOODS_GOODTWOLINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(IosPageElements.USEGOODS_INPUT).clear();
                    driver.findElementByXPath(IosPageElements.USEGOODS_INPUT).sendKeys(Integer.toString(secondGoodNum));
                    driver.findElementByXPath(IosPageElements.USEGOODS_USE).click();
                    Thread.sleep(1000);
                }
                if (thirdGoodNum != 0){
                    driver.findElementByXPath(IosPageElements.USEGOODS_GOODTHREELINK).click();
                    Thread.sleep(1000);
                    driver.findElementByXPath(IosPageElements.USEGOODS_INPUT).clear();
                    driver.findElementByXPath(IosPageElements.USEGOODS_INPUT).sendKeys(Integer.toString(thirdGoodNum));
                    driver.findElementByXPath(IosPageElements.USEGOODS_USE).click();
                    Thread.sleep(1000);
                }
                return useGoodsCount;
            }
        } else return 0;
    }

    //冲关成功并下一关
    public void passStageAndNext() throws InterruptedException{
        driver.findElementByName("冲关成功").click();
        Thread.sleep(1000);
        driver.findElementByName("下一关").click();
        Thread.sleep(1000);
    }

    //冲关成功并重玩
    public void passStageAndOneMore() throws InterruptedException{
        driver.findElementByName("冲关成功").click();
        Thread.sleep(1000);
        driver.findElementByName("重玩").click();
        Thread.sleep(1000);
    }

    //冲关成功并返回
    public void passStageAndBack() throws InterruptedException{
        driver.findElementByName("冲关成功").click();
        Thread.sleep(1000);
        driver.findElementByName("返回").click();
        Thread.sleep(1000);
    }

    //最后一关冲关成功并返回
    public void passFinalStageAndBack() throws  InterruptedException{
        driver.findElementByName("冲关成功").click();
        Thread.sleep(1000);
        driver.findElementByName("返回").click();
        Thread.sleep(1000);
    }

    //最后一关冲关成功并重玩
    public void passFinalStageAndOneMore() throws InterruptedException{
        driver.findElementByName("冲关成功").click();
        Thread.sleep(1000);
        driver.findElementByName("重玩").click();
        Thread.sleep(1000);
    }

    //冲关失败并返回
    public void failStageAndBack() throws InterruptedException{
        driver.findElementByName("冲关失败").click();
        Thread.sleep(1000);
        driver.findElementByName("返回").click();
        Thread.sleep(1000);
    }

    //冲关失败并重玩
    public void failStageAndOneMore() throws InterruptedException{
        driver.findElementByName("冲关失败").click();
        Thread.sleep(1000);
        driver.findElementByName("重玩").click();
        Thread.sleep(1000);
    }

    //暂停冲关，并输入暂停时间
    public void pauseStage(int pauseTime) throws InterruptedException{
        driver.findElementByName("暂停冲关").click();
        Thread.sleep(pauseTime);
        driver.findElementByName("OK").click();
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






























