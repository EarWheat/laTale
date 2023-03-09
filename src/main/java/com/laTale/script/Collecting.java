package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.common.Mouse;
import com.laTale.model.ButtonEnum;
import com.laTale.model.Location;
import com.laTale.model.TargetEnum;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/3/7 8:41 PM
 * @Version: 1.initial version; 2023/3/7 8:41 PM
 */
public class Collecting {

    private Robot robot;

    public Collecting(Robot robot) {
        this.robot = robot;
    }

    /**
     * 开始执行times次
     *
     * @param times
     */
    public void start(Integer times) {
        Mouse mouse = new Mouse(robot);
        int missTime = 0;
        int loop = 0;
        while (loop++ < times) {
            try {
                mouse.click(990, 700);
                int i = 0;
                while (i++ < 10) {
                    mouse.click(ButtonEnum.LIFE_SKILL);
                }
                missTime++;
                // 循环40次补给药品
                if (loop % 40 == 0) {
                    fillActivity(mouse);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void start() {
        this.start(Integer.MAX_VALUE);
    }


    /**
     * 补充活力药剂
     */
    public void fillActivity(Mouse mouse) {
        // 取消钓鱼
        mouse.click(ButtonEnum.CANCEL_FISH);
        // 点开背包
        mouse.click(ButtonEnum.OPEN_PACKAGE);
        // 补充活力药剂
        mouse.click(ButtonEnum.ACTIVITY);
        // 点击使用
        mouse.click(ButtonEnum.USE_ITEM);
        // 补充活力药剂,第二次位置不一样
        mouse.click(ButtonEnum.ACTIVITY);
        // 点击使用
        mouse.click(ButtonEnum.USE_ITEM);
        // 关闭背包
        mouse.click(ButtonEnum.CLOSE_PACKAGE);

    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Robot robot = new Robot();
        Collecting collecting = new Collecting(robot);
        collecting.start();

    }
}
