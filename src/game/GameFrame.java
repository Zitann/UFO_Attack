package game;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static common.CommonConstants.*;

public class GameFrame extends Frame implements Runnable{
    //��Ϸ״̬
    private static int gameState;
    //�˵�ָ��
    private static int menuIndex;
    //�������ĸ߶�
    public static int titleBarH;
    //1������һ�ź���Ļ��Сһ�µ�ͼƬ
    private BufferedImage bufImg = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
    //����ͼƬ
    private Image backgroundImg = null;
    private static int score = 0;
    private static int shotsLeft = 15;
    private static int citiesLeft = 4;
    private static int shieldLeft = 2;
    private static int enemyCount = 0;
    private UFO[] ufos = new UFO[ENEMY_MAX_COUNT];
    private City[] cities = new City[4];
    private static int lastEnemyBornTime = 0;
    private static final Random random = new Random();
    public GameFrame() {
        initFrame();
        initEventListener();
        //��������ˢ�´��ڵ��߳�
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    Utils.sleep(REPAINT_INTERVAL);
                }
            }
        }).start();
    }

    //��ʼ������
    private void initFrame() {
        //���ñ���
        setTitle(GAME_TITLE);
        //���ô��ڴ�С
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //���ô��ڵ����Ͻǵ�����
        setLocation(FRAME_X, FRAME_Y);
        //���ô��ڴ�С���ɸı�
        setResizable(false);
        //���ô��ڿɼ�
        setVisible(true);

        //��������ĸ߶�
        titleBarH = getInsets().top;
        // ��ʼ��random
        random.setSeed(System.currentTimeMillis());
    }

    //��Ӱ��������¼�
    private void initEventListener() {
        //ע������¼�
        addWindowListener(new WindowAdapter() {
            //����رհ�ť��ʱ�򣬷����ᱻ�Զ�����
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //��Ӱ��������¼�
        addKeyListener(new KeyAdapter() {
            //���������µ�ʱ�򱻻ص��ķ���
            @Override
            public void keyPressed(KeyEvent e) {
                //��ñ����¼��ļ�ֵ
                int keyCode = e.getKeyCode();
                //��ͬ����Ϸ״̬��������ͬ�Ĵ���ķ�����
                switch (gameState) {
                    case STATE_MENU:
                        keyPressedEventMenu(keyCode);
                        break;
                    case STATE_HELP:
                        keyPressedEventHelp(keyCode);
                        break;
                    case STATE_ABOUT:
                        keyPressedEventAbout(keyCode);
                        break;
                    case STATE_RUN:
                        keyPressedEventRun(keyCode);
                        break;
                    case STATE_LOST:
                        keyPressedEventLost(keyCode);
                        break;
                    case STATE_WIN:
                        keyPressedEventWin(keyCode);
                        break;
                }
            }

        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameState == STATE_RUN) {
                    shotsLeft--;
                    for (int i = 0; i < ENEMY_MAX_COUNT; i++) {
                        if (ufos[i] != null && Utils.distanse(e.getX(), e.getY(), ufos[i].getX(), ufos[i].getY()) < 200) {
                            ufos[i] = null;
                            score += DISROTY_UFO_SCORE;
                            break;
                        }
                    }
                }
            }
        });
    }


    /**
     * ���Ʋ˵�״̬�µ�����
     *
     * @param g ���ʶ���ϵͳ�ṩ��
     */
    private void drawMenu(Graphics g) {
        if(backgroundImg == null){
            backgroundImg = Utils.getImage(BACKGROUND_IMG_PATH);
            // ���ŵ����ڴ�С
            backgroundImg = backgroundImg.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_DEFAULT);
        }
        g.drawImage(backgroundImg, 0, 0, null);

        final int STR_WIDTH = 76;
        int x = FRAME_WIDTH - STR_WIDTH >> 1;
        int y = FRAME_HEIGHT / 3;
        //�м��
        final int DIS = 50;

        for (int i = 0; i < MENUS.length; i++) {
            if (i == menuIndex) {//ѡ�еĲ˵������ɫ����Ϊ��ɫ��
                g.setColor(Color.RED);
            } else {//������Ϊ��ɫ
                g.setColor(Color.WHITE);
            }
            g.drawString(MENUS[i], x, y + DIS * i);
        }
    }

    private void drawHelp(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, null);
        g.setFont(GAME_FONT);
        g.setColor(Color.WHITE);
        final int STR_WIDTH = 76;
        int x = FRAME_WIDTH - STR_WIDTH >> 1;
        g.drawString("��Ϸ����", x, 100);
        g.drawString("��ϷĿ�꣺��������UFO���������в����ݻ�", 100, 150);
        g.drawString("����˵����ʹ����������ֵ�UFO ��һ����", 100, 200);
        g.drawString("��ĳ��UFO ��������١�", 225, 250);
        g.setColor(Color.RED);
        g.drawString("������������˵�", 310, 500);
    }

    private void drawAbout(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, null);
        g.setFont(GAME_FONT);
        g.setColor(Color.WHITE);
        final int STR_WIDTH = 76;
        int x = FRAME_WIDTH - STR_WIDTH >> 1;
        g.drawString("��Ϸ����", x, 100);
        g.drawString("��Ϸ���ƣ�������ս", 100, 150);
        g.drawString("��Ϸ�汾��1.0", 100, 200);
        g.drawString("��Ϸ���ߣ�����̴-2022141460178", 100, 250);
        g.setColor(Color.RED);
        g.drawString("������������˵�", 310, 500);

    }

    private void drawRun(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, null);
        g.setFont(GAME_FONT);
        g.setColor(Color.WHITE);
        g.drawString("�÷֣�" + score, 20, 70);
        g.drawString("ʣ����У�" + citiesLeft, 600, 70);
        g.drawString("ʣ���ӵ���" + shotsLeft, 20, 100);
        g.drawString("ʣ�໤�ܣ�" + shieldLeft, 600, 100);
        for (int i = 0; i < cities.length; i++) {
            if (cities[i] == null) {
                cities[i] = new City();
                System.out.println("city " + cities[i].getX() + " created");
            }else{
                for(int j = 0; j < ENEMY_MAX_COUNT; j++) {
                    if (!cities[i].isDistory() && ufos[j] != null && ufos[j].getBullet() != null && Utils.distanse(cities[i].getX(), cities[i].getY(), ufos[j].getBullet().getX(), ufos[j].getBullet().getY()) < DISROTY_CITY_DIS) {
                        if(shieldLeft > 0) {
                            shieldLeft--;
                        }else{
                            cities[i].distory();
                            citiesLeft--;
                            score += DISROTY_CITY_SCORE;
                        }
                        ufos[j].setBullet(null);
                        break;
                    }
                }
                g.drawImage(Utils.getImage(cities[i].getImgPath()), cities[i].getX(), cities[i].getY(), null);
            }
        }
        //�ں�̨�߳���ÿ��5��������һ��UFO
        if (enemyCount < ENEMY_MAX_COUNT && (lastEnemyBornTime == 0 || (int)System.currentTimeMillis() - lastEnemyBornTime > ENEMY_BORN_INTERVAL)) {
            ufos[enemyCount++] = new UFO(random.nextInt(100,600), random.nextInt(100,400), random.nextInt(3) + 1);
            lastEnemyBornTime = (int) System.currentTimeMillis();
        }
        for (int i = 0; i < ENEMY_MAX_COUNT; i++) {
            if (ufos[i] != null) {
                g.drawImage(Utils.getImage(ufos[i].getImgPath()), ufos[i].getX(), ufos[i].getY(), null);
                if(ufos[i].getBullet() != null) {
                    g.drawImage(Utils.getImage(ufos[i].getBullet().getImgPath()), ufos[i].getBullet().getX(), ufos[i].getBullet().getY(), null);
                }
            }
        }
        if(shotsLeft <= 0 || citiesLeft <= 0) {
            setGameState(STATE_LOST);
        }else if(enemyCount == ENEMY_MAX_COUNT && citiesLeft > 0) {
            boolean win = true;
            for (int i = 0; i < ENEMY_MAX_COUNT; i++) {
                if (ufos[i] != null) {
                    win = false;
                    break;
                }
            }
            if(win) {
                setGameState(STATE_WIN);
            }
        }
    }
    private void drawLost(Graphics g) {
        g.drawImage(Utils.getImage(EARTHB_IMG_PATH), 0, 0, null);
        g.setFont(GAME_FONT);
        g.setColor(Color.RED);
        g.drawString("�ػ�����ʧ��", 320, 200);
        //��Ӱ�����ʾ��Ϣ
        g.setColor(Color.WHITE);
        g.drawString(OVER_STR0,10,FRAME_HEIGHT-20);
        g.drawString(OVER_STR1,FRAME_WIDTH-200,FRAME_HEIGHT-20);
    }
    private void drawWin(Graphics g){
        g.drawImage(Utils.getImage(EARTHA_IMG_PATH), 0, 0, null);
        g.setFont(GAME_FONT);
        g.setColor(Color.RED);
        g.drawString("�ػ�����ɹ�", 320, 200);
        g.drawString("�÷֣�" + score, 320, 350);
        //��Ӱ�����ʾ��Ϣ
        g.setColor(Color.WHITE);
        g.drawString(OVER_STR0,10,FRAME_HEIGHT-20);
        g.drawString(OVER_STR1,FRAME_WIDTH-200,FRAME_HEIGHT-20);
    }
    //�˵�״̬�µİ����Ĵ���
    private void keyPressedEventMenu(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (--menuIndex < 0) {
                    menuIndex = MENUS.length - 1;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if(++menuIndex > MENUS.length -1){
                    menuIndex = 0;
                }
                break;
            case KeyEvent.VK_ENTER:
                switch(menuIndex){
                    case 0:
                        startGame();
                        break;
                    case 1:
                        setGameState(STATE_HELP);
                        break;
                    case 2:
                        setGameState(STATE_ABOUT);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                break;
        }
    }
    //����״̬�µİ����Ĵ���
    private void keyPressedEventHelp(int keyCode) {
        setGameState(STATE_MENU);
    }
    //����״̬�µİ����Ĵ���
    private void keyPressedEventAbout(int keyCode) {
        setGameState(STATE_MENU);
    }

    //��Ϸ�����еİ���������
    private void keyPressedEventRun(int keyCode) {
        if(keyCode == KeyEvent.VK_ESCAPE){
            setGameState(STATE_MENU);
        }
    }

    //��Ϸ�����İ����Ĵ���
    private void keyPressedEventLost(int keyCode) {
        //������Ϸ
        if(keyCode == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }else if(keyCode == KeyEvent.VK_ENTER){
            setGameState(STATE_MENU);
        }
    }

    //��Ϸͨ�صİ�������
    private void keyPressedEventWin(int keyCode) {
        keyPressedEventLost(keyCode);
    }

    /**
     * ��Frame ��ķ������̳������ķ�����
     * �÷������������еĻ��Ƶ����ݣ�������Ҫ����Ļ����ʽ��
     * ���ݣ���Ҫ�ڸ÷����ڵ��á��÷��������������á�����ͨ������
     * repaint(); ȥ�ص��÷�����
     *
     * @param g1 ϵͳ�ṩ�Ļ��ʣ�ϵͳ���г�ʼ��
     */
    public void update(Graphics g1) {
        //2���õ�ͼƬ�Ļ���
        Graphics g = bufImg.getGraphics();
        //3: ʹ��ͼƬ���ʽ����е����ݻ�֪��ͼƬ��
        g.setFont(GAME_FONT);
        switch (gameState) {
            case STATE_MENU:
                drawMenu(g);
                break;
            case STATE_HELP:
                drawHelp(g);
                break;
            case STATE_ABOUT:
                drawAbout(g);
                break;
            case STATE_RUN:
                drawRun(g);
                break;
            case STATE_LOST:
                drawLost(g);
                break;
            case STATE_WIN:
                drawWin(g);
                break;
        }

        //4:ʹ��ϵͳ���ʣ���ͼƬ���Ƶ�frame����
        g1.drawImage(bufImg,0,0,null);
    }

    private void startGame() {
        setGameState(STATE_RUN);
        score = 0;
        shotsLeft = 15;
        citiesLeft = 4;
        shieldLeft = 2;
        enemyCount = 0;
        for (int i = 0; i < ENEMY_MAX_COUNT; i++) {
            ufos[i] = null;
        }
    }

    //�����Ϸ״̬���޸���Ϸ״̬
    public static void setGameState(int gameState) {
        GameFrame.gameState = gameState;
    }


    @Override
    public void run() {
        while(true){
            //�ڴ˵���repaint,�ص�update
            repaint();
            Utils.sleep(REPAINT_INTERVAL);
        }
    }
}
