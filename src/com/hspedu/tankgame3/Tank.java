package com.hspedu.tankgame3;

/**
 * @Author Agony
 * @Create 2023/2/15 20:40
 * @Version 1.0
 */
public class Tank {

    private int x;
    private int y;

    private int direction; // 0 -> 上   1 -> 右  2 -> 下  3 -> 左

    private int speed = 5;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }
}
