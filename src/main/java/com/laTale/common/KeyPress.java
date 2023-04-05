package com.laTale.common;

import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/4/2 1:54 PM
 * @Version: 1.initial version; 2023/4/2 1:54 PM
 */
@AllArgsConstructor
public class KeyPress {

    public Robot robot;

    /**
     * 键盘操作
     *
     * @param keyEvent
     */
    public void keyOn(Integer keyEvent) {
        robot.keyPress(keyEvent);
        robot.setAutoDelay(50);
        robot.keyRelease(keyEvent);
    }

    public void openPacket(){
        robot.keyPress(KeyEvent.VK_ALT);
        robot.setAutoDelay(50);
        robot.keyPress(KeyEvent.VK_E);
        robot.setAutoDelay(50);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

}
