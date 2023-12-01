package game;

import static common.CommonConstants.BULLET_IMG_PATH;
import static utils.Utils.sleep;

public class Bullet {
    private int x;
    private int y;
    private String imgPath;
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.imgPath = BULLET_IMG_PATH;
        new Thread(() -> {
            while (true) {
                sleep(30);
                this.y += 10;
            }
        }).start();
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
}
