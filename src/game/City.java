package game;

import java.util.Random;

import static common.CommonConstants.*;

public class City {
    private int x;
    private int y;
    private String imgPath;
    private boolean isDistory = false;
    public City() {
        Random random = new Random();
        this.x = random.nextInt(100,500);
        this.y = CITY_Y;
        imgPath = CITY_IMG_PATH;
    }
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
