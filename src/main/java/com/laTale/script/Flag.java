package com.laTale.script;

import com.laTale.common.KeyPress;
import com.laTale.common.Mouse;
import com.laTale.model.ButtonEnum;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Desc: 梦幻西游插旗子
 * @Author: 泽露
 * @Date: 2023/3/27 8:39 PM
 * @Version: 1.initial version; 2023/3/27 8:39 PM
 */
public class Flag {

    private Robot robot;

    private Mouse mouse;

    private KeyPress keyPress;

    public Flag() throws AWTException {
        this.robot = new Robot();
        this.mouse = new Mouse(this.robot);
        this.keyPress = new KeyPress(this.robot);
    }

    private void flag() throws InterruptedException {
        mouse.click(540, 365);
        Thread.sleep(2000);
        // 打开背包
        mouse.click(ButtonEnum.OPEN_PACKAGE);
        // 双击导标棋子
    }

    /**
     * 购买棋子
     *
     * @throws InterruptedException
     */
    private void buyFlag() throws InterruptedException {
        mouse.click(540, 365);
        // 打开仙灵店铺
        Thread.sleep(2000);
        keyPress.keyOn(KeyEvent.VK_F8);
        mouse.click(ButtonEnum.BUY_FLAG_LOCATION);
        for (int i = 0; i < 5; i++) {
            keyPress.keyOn(KeyEvent.VK_9);
        }
        mouse.move(ButtonEnum.BUY_SURE);
//        mouse.click(ButtonEnum.CLOSE_XIANLING);
    }

    /**
     * 合成导标棋
     *
     * @throws InterruptedException
     */
    private void mergeFlag() throws InterruptedException {
        // 锁定游戏
        mouse.click(1475, 1075);

        int startPageX = ButtonEnum.WAREHOUSE_PAGE_START.getX();
        int startPageY = ButtonEnum.WAREHOUSE_PAGE_START.getY();
        int step = 20;
        for (int i = 0; i < 7; i++) {
            // 选中页码
            mouse.click(startPageX + (step * i), startPageY);
            // 等待响应
            Thread.sleep(500);
            // 取出物品
            mouse.click3(ButtonEnum.WAREHOUSE_ITEM_START.getX(), ButtonEnum.WAREHOUSE_ITEM_START.getY());
        }
    }

    /**
     * 合成旗帜位置定位测试
     *
     * @throws InterruptedException
     */
    private void mergeFlagTest() throws InterruptedException {
        mouse.move(ButtonEnum.WAREHOUSE_PAGE_START);
        Thread.sleep(2000);
        mouse.move(ButtonEnum.WAREHOUSE_ITEM_START);
    }


    public static void main(String[] args) throws AWTException, InterruptedException {
        Flag flag = new Flag();
        flag.mergeFlag();

    }
}
