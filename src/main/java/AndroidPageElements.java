/**
 * Created by johnny on 15/8/5.
 */
public interface AndroidPageElements {

    String LOGIN_PLAY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.Button[1]";

    String SESSION_OUT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.Button[1]";
    String SESSION_OUT_BACK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.Button[1]";

    String SELECT_GAMESTAGELIST = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView";
    String SELECT_GAMESTAGEFIRST = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[1]";
    String SELECT_GAMESTAGESECOND = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[2]";
    String SELECT_GAMESTAGETHIRD = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[3]";
    String SELECT_GAMESTAGEFOURTH = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[4]";
    String SELECT_GAMESTAGEFIFTH = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[5]";

    String DETAILS_BUYCOINS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]";
    //DETAILS页面中，金币的数量是通过‘text’属性获取到的
    String DETAILS_COINSQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]";
    String DETAILS_BUYGOODS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]";
    String DETAILS_USEGOODS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]";
    String DETAILS_STAGEPASS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[4]";
    String DETAILS_STAGEFAIL = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]";
    String DETAILS_STAGEPAUSE = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[6]";
    String DETAILS_BACK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]";

    String BUYCOINS_ONEHUNDREDLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]";
    String BUYCOINS_ONEHUNDREDCOINS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]";
    String BUYCOINS_ONEHUNDREDMONEY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]";
    String BUYCOINS_ONETHOUSANDLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]";
    String BUYCOINS_ONETHOUSANDCOINS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]";
    String BUYCOINS_ONETHOUSANDMONEY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.TextView[2]";
    String BUYCOINS_THREETHOUSANDLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]";
    String BUYCOINS_THREETHOUSANDCOINS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]/android.widget.TextView[1]";
    String BUYCOINS_THREETHOUSANDMONEY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]/android.widget.TextView[2]";
    String BUYCOINS_EIGHTTHOUSANDLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[4]";
    String BUYCOINS_EIGHTTHOUSANDCOINS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[4]/android.widget.TextView[1]";
    String BUYCOINS_EIGHTTHOUSANDMONEY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[4]/android.widget.TextView[2]";
    String BUYCOINS_TWENTYFIVETHOUSANDLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]";
    String BUYCOINS_TWENTYFIVETHOUSANDCOINS = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]/android.widget.TextView[1]";
    String BUYCOINS_TWENTYFIVETHOUSANDMONEY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]/android.widget.TextView[2]";
    String BUYCOINS_OK = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.Button[1]";
    String BUYCOINS_BACK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]";

    String BUYGOODS_GOODONELINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]";
    String BUYGOODS_GOODONEQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]";
    String BUYGOODS_GOODTWOLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]";
    String BUYGOODS_GOODTWOQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.TextView[2]";
    String BUYGOODS_GOODTHREELINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]";
    String BUYGOODS_GOODTHREEQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]/android.widget.TextView[2]";
    String BUYGOODS_OK = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.Button[1]";
    String BUYGOODS_BACK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]";

    String USEGOODS_GOODONELINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]";
    String USEGOODS_GOODONEQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]";
    String USEGOODS_GOODTWOLINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]";
    String USEGOODS_GOODTWOQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.TextView[2]";
    String USEGOODS_GOODTHREELINK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]";
    String USEGOODS_GOODTHREEQUANTITY = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]/android.widget.TextView[2]";
    String USEGOODS_OK = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.Button[1]";
    String USEGOODS_BACK = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]";

    String STAGEPASS_ONEMORE = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.Button[1]";
    String STAGEPASS_NEXT = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.Button[2]";
    String FINALSTAGEPASS_ONEMORE = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.Button[1]";

    String STAGEFAIL_ONEMORE = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]";
    String STAGEFAIL_BACK = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[2]";

    String STAGEPAUSE_OK = "//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]";
}
