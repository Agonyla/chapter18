package com.hspedu.tankgame3;

import javax.swing.*;

/**
 * @Author Agony
 * @Create 2023/2/15 20:44
 * @Version 1.0
 */
public class AgonyTankGame03 extends JFrame {

    public static void main(String[] args) {
        new AgonyTankGame03();
    }

    public AgonyTankGame03() {

        MyPanel myPanel = new MyPanel();
        new Thread(myPanel).start();
        this.add(myPanel);
        this.addKeyListener(myPanel);

        this.setSize(1000, 750);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
