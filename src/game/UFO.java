package game;

import static common.CommonConstants.*;
import static utils.Utils.sleep;
public class UFO {
    private int x;
    private final int y;
    private int moveSpeed;  // 移动速度
    private int bulletTime;  // 子弹发射间隔
    private String imgPath;
    private Bullet bullet = null;
    private int lastShootTime = (int) System.currentTimeMillis();   // 上次发射子弹的时间
    public UFO(int x, int y, int type) {
        this.x = x;
        this.y = y;
        // 根据不同的类型设置不同的移动速度和子弹发射间隔
        switch (type) {
            case 1:
                moveSpeed = 5;
                bulletTime = 1;
                imgPath = UFO1_IMG_PATH;
                break;
            case 2:
                moveSpeed = 7;
                bulletTime = 3;
                imgPath = UFO2_IMG_PATH;
                break;
            case 3:
                moveSpeed = 10;
                bulletTime = 4;
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
                // 根据子弹发射间隔控制子弹的发射
                if((int)System.currentTimeMillis() - lastShootTime > bulletTime *ENEMY_FIRE_INTERVAL) {
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