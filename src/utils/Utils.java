package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * �������ڴ��һЩ���߷���
 */
public class Utils {
    // ����ͼƬ·����ȡͼƬ
    public static Image getImage(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("File does not exist: " + path);
            return null;
        }
        // ����Ƿ��ܶ�ȡ��ͼƬ
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // �ȴ�һ��ʱ��
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ��������֮��ľ���
    public static double distanse(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
