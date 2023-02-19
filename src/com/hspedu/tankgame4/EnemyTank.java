package com.hspedu.tankgame4;

import java.util.Vector;

/**
 * @Author Agony
 * @Create 2023/2/16 14:35
 * @Version 1.0
 */
public class EnemyTank extends Tank implements Runnable {

    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {

            if (isLive && shots.size() <= 3) {
                Shot shot = switch (getDirection()) {
                    case 0 -> new Shot(getX() + 20, getY(), 0);
                    case 1 -> new Shot(getX() + 60, getY() + 20, 1);
                    case 2 -> new Shot(getX() + 20, getY() + 60, 2);
                    case 3 -> new Shot(getX(), getY() + 20, 3);
                    default -> null;
                };
                shots.add(shot);
                new Thread(shot).start();

            }
            switch (getDirection()) {
                case 0 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getY() > 0)
                            moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 1 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getX() + 60 < 1000)
                            moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 2 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getY() + 60 < 750)
                            moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 3 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getX() > 0)
                            moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }


            setDirection((int) (Math.random() * 4));

            if (!isLive) {
                return;
            }
        }
    }
}
