package game;

import static common.CommonConstants.*;
import static utils.Utils.sleep;
public class UFO {
    private int x;
    private final int y;
    private int moveSpeed;  // �ƶ��ٶ�
    private int bulletTime;  // �ӵ�������
    private String imgPath;
    private Bullet bullet = null;
    private int lastShootTime = (int) System.currentTimeMillis();   // �ϴη����ӵ���ʱ��
    public UFO(int x, int y, int type) {
        this.x = x;
        this.y = y;
        // ���ݲ�ͬ���������ò�ͬ���ƶ��ٶȺ��ӵ�������
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
        // �����߳̿����Զ������ƶ�
        new Thread(() -> {
            while (true) {
                sleep(REPAINT_INTERVAL);
                this.x += moveSpeed;
                if (this.x < 10 || this.x > FRAME_WIDTH - 50) {
                    moveSpeed = -moveSpeed;
                }
                // �����ӵ������������ӵ��ķ���
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