package com.hspedu.tankgame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @Author Agony
 * @Create 2023/2/15 20:41
 * @Version 1.0
 * 坦克大战绘图区域
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {

    MyTank myTank = null;

    Vector<EnemyTank> enemyTanks = new Vector<>();

    int enemyTankSize = 3;

    public MyPanel() {
        myTank = new MyTank(100, 100);
        myTank.setSpeed(8);

        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (1 + i), 0);
            enemyTank.setDirection(2);
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);

        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        drawShot(myTank, g);

        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
        }


    }

    /**
     * @param x         坦克左上角x坐标
     * @param y         坦克左上角y坐标
     * @param g         画笔
     * @param direction 坦克朝向(上下左右)
     * @param type      坦克种类
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            // 自己的坦克
            case 0 -> g.setColor(Color.cyan);
            // 敌人的坦克
            case 1 -> g.setColor(Color.yellow);
        }

        // 0 -> 向上  1 -> 向右  2 -> 向下  3 -> 向左
        switch (direction) {
            // 向上朝向
            case 0 -> {
                g.fill3DRect(x, y, 10, 60, false); // 左边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间主体
                g.fill3DRect(x + 30, y, 10, 60, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x + 20, y); // 杆
            }
            //
            case 1 -> {
                g.fill3DRect(x - 10, y + 10, 60, 10, false); // 左边轮子
                g.fill3DRect(x, y + 20, 40, 20, false); // 中间主体
                g.fill3DRect(x - 10, y + 40, 60, 10, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x + 50, y + 30); // 杆
            }
            case 2 -> {
                g.fill3DRect(x, y, 10, 60, false); // 左边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间主体
                g.fill3DRect(x + 30, y, 10, 60, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 杆
            }
            case 3 -> {
                g.fill3DRect(x - 10, y + 10, 60, 10, false); // 左边轮子
                g.fill3DRect(x, y + 20, 40, 20, false); // 中间主体
                g.fill3DRect(x - 10, y + 40, 60, 10, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x - 10, y + 30); // 杆
            }
        }


    }

    /**
     * @param myTank 我方坦克
     * @param g      画笔
     */
    public void drawShot(MyTank myTank, Graphics g) {
        // g.setColor(Color.cyan);
        //
        // switch (myTank.getDirection()) {
        //     case 0 -> g.fillOval(myTank.getX() + 20, myTank.getY(), 10, 10);
        //     case 1 -> g.fillOval(myTank.getX() + 50, myTank.getY() + 30, 10, 10);
        //     case 2 -> g.fillOval(myTank.getX() + 20, myTank.getY() + 60, 10, 10);
        //     case 3 -> g.fillOval(myTank.getX() - 10, myTank.getY() + 30, 10, 10);
        // }

        if (myTank.shot != null && myTank.shot.isLive) {

            Shot shot = myTank.shot;
            g.setColor(Color.cyan);
            g.fillOval(shot.x - 2, shot.y - 2, 4, 4);
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                myTank.setDirection(0);
                myTank.moveUp();
            }
            case KeyEvent.VK_D -> {
                myTank.setDirection(1);
                myTank.moveRight();
            }
            case KeyEvent.VK_S -> {
                myTank.setDirection(2);
                myTank.moveDown();
            }
            case KeyEvent.VK_A -> {
                myTank.setDirection(3);
                myTank.moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            myTank.shotTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
