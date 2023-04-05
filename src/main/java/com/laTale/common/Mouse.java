package com.laTale.common;

import com.laTale.model.ButtonEnum;
import com.laTale.model.Location;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/2/16 3:09 PM
 * @Version: 1.initial version; 2023/2/16 3:09 PM
 */
@AllArgsConstructor
public class Mouse {

    public Robot robot;


    /**
     * 鼠标移动并点击
     *
     * @param x
     * @param y
     */
    public void click(Integer x, Integer y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.setAutoDelay(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * 鼠标直接点击
     */
    public void click() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.setAutoDelay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * 点击某个按钮
     *
     * @param buttonEnum
     */
    public void click(ButtonEnum buttonEnum) {
        robot.mouseMove(buttonEnum.getX(), buttonEnum.getY());
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.setAutoDelay(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * 点击某个按钮
     *
     * @param buttonEnum
     */
    public void click3(ButtonEnum buttonEnum) {
        robot.mouseMove(buttonEnum.getX(), buttonEnum.getY());
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.setAutoDelay(50);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }

    public void click3(Integer x, Integer y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.setAutoDelay(50);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }

    /**
     * 移动鼠标
     *
     * @param x
     * @param y
     */
    public void move(Integer x, Integer y) {
        robot.mouseMove(x, y);
    }

    /**
     * 移动鼠标
     *
     * @param location
     */
    public void move(Location location) {
        robot.mouseMove(location.getX(), location.getY());
    }

    public void move(ButtonEnum buttonEnum) {
        robot.mouseMove(buttonEnum.getX(), buttonEnum.getY());
    }
}
