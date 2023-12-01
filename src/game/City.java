package game;

import java.util.Random;

import static common.CommonConstants.*;

public class City {
    private final int x;
    private final int y;
    private String imgPath;
    private boolean isDistory = false;
    public City() {
        // 随机生成城市的x坐标
        Random random = new Random();
        this.x = random.nextInt(100,500);
        this.y = CITY_Y;
        imgPath = CITY_IMG_PATH;
    }

    // 城市被摧毁
    public void distory() {
        imgPath = CITY_BURN_IMG_PATH;
        isDistory = true;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getImgPath() {
        return imgPath;
    }
    public boolean isDistory() {
        return isDistory;
    }
}
