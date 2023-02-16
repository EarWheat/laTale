package com.laTale.script;

import com.laTale.common.FindPicPoint;
import com.laTale.common.Mouse;
import com.laTale.model.Location;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Desc: 挖矿脚本
 * @Author: 泽露
 * @Date: 2023/2/16 2:48 PM
 * @Version: 1.initial version; 2023/2/16 2:48 PM
 */
public class Mining {

    public static final String DRILL_IMG_PATH = "/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/drill.png";
    /**
     * 钻头位置
     */
    public Location drill;

    /**
     * 矿石位置
     */
    public Location mineral;

    /**
     * 挖矿界面左上角
     */
    public Location minerAreaLeftTop;

    /**
     * 挖矿界面右下角
     */
    public Location minerAreaRightBottom;

    /**
     * 点击开始挖矿位置
     */
    public Location startMining;


    public Robot robot;

    public Mining(Robot robot) {
        this.robot = robot;
    }

    public void min(){
        // 外层循环
        // 点击开始挖矿
//        Mouse.click(robot, startMining.getX(), startMining.getY());
        // ================
        // 第一步：截图挖矿区域
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(minerAreaLeftTop.getX(), minerAreaLeftTop.getY(), minerAreaRightBottom.getX(), minerAreaRightBottom.getY()));
        // 第二步：找到钻头位置
        String drillImgPath = DRILL_IMG_PATH;
        FindPicPoint drill = new FindPicPoint(robot, drillImgPath);
        // 第三步：找到矿石区域
        // 第四步：匹配是否挖矿
        // =================
        // 点击挖矿
    }

    public static void startMining(Robot robot){

    }
}
