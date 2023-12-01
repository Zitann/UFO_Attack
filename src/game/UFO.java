package game;

import static common.CommonConstants.*;
import static utils.Utils.sleep;
public class UFO {
    private int x;
    private int y;
    private int moveSpeed;
    private int bulletSpeed;
    private String imgPath;
    private Bullet bullet = null;
    private int lastShootTime = (int) System.currentTimeMillis();
    public UFO(int x, int y, int type) {
        this.x = x;
        this.y = y;
        switch (type) {
            case 1:
                moveSpeed = 5;
                bulletSpeed = 1;
                imgPath = UFO1_IMG_PATH;
                break;
            case 2:
                moveSpeed = 7;
                bulletSpeed = 3;
                imgPath = UFO2_IMG_PATH;
                break;
            case 3:
                moveSpeed = 10;
                bulletSpeed = 4;
                imgPath = UFO3_IMG_PATH;
                break;
        }
        // 启动线程控制自动左右移动
        new Thread(() -> {
            while (true) {
                sleep(REPAINT_INTERVAL);
                this.x += moveSpeed;
                if (this.x < 10 || this.x > FRAME_WIDTH - 50) {
                    moveSpeed = -moveSpeed;
                }
                if((int)System.currentTimeMillis() - lastShootTime > bulletSpeed*ENEMY_FIRE_INTERVAL) {
                    lastShootTime = (int)System.currentTimeMillis();
                    bullet = new Bullet(this.x, this.y + 50);
                }
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
    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}