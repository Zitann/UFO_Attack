package common;

import java.awt.*;

/**
 * 游戏中的常量都在该类中维护，方便后期的管理
 */
public class CommonConstants {
    /******************游戏窗口相关******************/
    public static final String GAME_TITLE = "地球保卫战";
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    //动态的获得系统屏幕的宽高
    public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int FRAME_X = SCREEN_W-FRAME_WIDTH>>1;
    public static final int FRAME_Y = SCREEN_H-FRAME_HEIGHT>>1;
    /*********************游戏菜单相关*********************/
    public static final int STATE_MENU = 0;
    public static final int STATE_HELP = 1;
    public static final int STATE_ABOUT = 2;
    public static final int STATE_RUN = 3;
    public static final int STATE_LOST = 4;
    public static final int STATE_WIN = 5;

    public  static final String[] MENUS = {
            "开始游戏",
            "游戏帮助",
            "游戏关于",
            "退出游戏"
    };

    public static final String OVER_STR0 = "ESC键退出游戏";
    public static final String OVER_STR1 = "ENTER键回主菜单";

    //字体
    public static final Font GAME_FONT = new Font("宋体",Font.BOLD,24);
    /*********************游戏常量相关*********************/
    //游戏帧数
    public static final int REPAINT_INTERVAL = 30;
    //最大敌人数量
    public static final int ENEMY_MAX_COUNT = 10;
    //敌人出生间隔
    public static final int ENEMY_BORN_INTERVAL = 3000;
    //敌人开火间隔
    public static final double ENEMY_FIRE_INTERVAL = 1000;
    //城市数量
    public static final int CITY_COUNT = 4;
    //城市的y坐标
    public static final int CITY_Y = 560;
    //分数
    public static final int DISROTY_UFO_SCORE = 200;
    public static final int DISROTY_CITY_SCORE = -100;
    //城市被摧毁的距离
    public static final int DISROTY_CITY_DIS = 50;
    //UFO被摧毁的距离
    public static final int DISROTY_UFO_DIS = 200;
    public static final int ZERO = 0;
    //初始子弹数量
    public static final int INIT_BULLET_COUNT = 15;
    //初始护盾数量
    public static final int INIT_SHIELD_COUNT = 2;
    /*********************游戏资源相关*********************/
    public static final String BACKGROUND_IMG_PATH = "res/Background.bmp";
    public static final String CITY_IMG_PATH = "res/City.gif";
    public static final String CITY_BURN_IMG_PATH = "res/CityBurn.gif";
    public static final String UFO1_IMG_PATH = "res/Ufo1.gif";
    public static final String UFO2_IMG_PATH = "res/Ufo2.gif";
    public static final String UFO3_IMG_PATH = "res/Ufo3.gif";
    public static final String BULLET_IMG_PATH = "res/Bullet.png";
    public static final String EARTHA_IMG_PATH = "res/EarthA.jpg";
    public static final String EARTHB_IMG_PATH = "res/EarthB.jpg";

}
