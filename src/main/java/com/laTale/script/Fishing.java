package com.laTale.script;

import com.alibaba.fastjson.JSONObject;
import com.laTale.common.FindPicLocation;
import com.laTale.common.Mouse;
import com.laTale.model.AreaEnum;
import com.laTale.model.ButtonEnum;
import com.laTale.model.Location;
import com.laTale.model.TargetEnum;
import com.laTale.util.ImageUtil;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Desc: 钓鱼
 * @Author: 泽露
 * @Date: 2023/2/14 7:17 PM
 * @Version: 1.initial version; 2023/2/14 7:17 PM
 */
public class Fishing {

    /**
     * 钓鱼界面左上角
     */
    public Location fishAreaLeftTop;

    /**
     * 钓鱼界面右下角
     */
    public Location fishAreaRightBottom;

    /**
     * 钓鱼界面宽
     */
    public Integer fishAreaWidth;

    /**
     * 钓鱼界面高
     */
    public Integer fishAreaHeight;

    /**
     * 钩子界面左上角
     */
    public Location gouZiAreaLeftTop;

    /**
     * 钩子界面右下角
     */
    public Location gouZiAreaRightBottom;

    /**
     * 钩子界面宽
     */
    public Integer gouZiAreaWidth;

    /**
     * 钩子界面高
     */
    public Integer gouZiAreaHeight;

    public Robot robot;

    public Fishing(Robot robot, Location fishAreaLeftTop, Location fishAreaRightBottom, Location gouZiAreaLeftTop, Location gouZiAreaRightBottom) {
        this.robot = robot;
        this.fishAreaLeftTop = fishAreaLeftTop;
        this.fishAreaRightBottom = fishAreaRightBottom;
        this.fishAreaWidth = fishAreaRightBottom.getX() - fishAreaLeftTop.getX();
        this.fishAreaHeight = fishAreaRightBottom.getY() - fishAreaLeftTop.getY();
        this.gouZiAreaLeftTop = gouZiAreaLeftTop;
        this.gouZiAreaRightBottom = gouZiAreaRightBottom;
        this.gouZiAreaWidth = gouZiAreaRightBottom.getX() - gouZiAreaLeftTop.getX();
        this.gouZiAreaHeight = gouZiAreaRightBottom.getY() - gouZiAreaLeftTop.getY();
    }

    public Fishing(Robot robot, Location fishAreaLeftTop, Integer fishAreaWidth, Integer fishAreaHeight) {
        this.robot = robot;
        this.fishAreaLeftTop = fishAreaLeftTop;
        this.fishAreaWidth = fishAreaWidth;
        this.fishAreaHeight = fishAreaHeight;
        this.fishAreaRightBottom = new Location(fishAreaLeftTop.getX() + fishAreaWidth, fishAreaLeftTop.getY() + fishAreaHeight);
    }

    /**
     * 开始执行times次
     *
     * @param times
     */
    public void start(Integer times) {
        Mouse mouse = new Mouse(robot);
        // 丢失钓鱼区域次数，用于判断是否补充活力药剂
        int missTime = 0;
        int loop = 0;
        while (true) {
            // 钓鱼区域
            BufferedImage fishArea = robot.createScreenCapture(new Rectangle(fishAreaLeftTop.getX(), fishAreaLeftTop.getY(), fishAreaWidth, fishAreaHeight));
            // 吊钩区域
            BufferedImage gouZiArea = robot.createScreenCapture(new Rectangle(gouZiAreaLeftTop.getX(), gouZiAreaLeftTop.getY(), gouZiAreaWidth, gouZiAreaHeight));
            try {
                // 第二步：找到吊钩和鱼的位置
                Location gouZiLocation = FindPicLocation.findColor(TargetEnum.FISH_GOUZI.getRGB(), gouZiArea, gouZiAreaLeftTop);
                Location fishLocation = FindPicLocation.findColor(TargetEnum.FISH.getRGB(), fishArea, fishAreaLeftTop);
                if (!gouZiLocation.isFind() && !fishLocation.isFind()) {
                    if (missTime >= 20) {
                        fillActivity(mouse);
                        missTime = 0;
                    }
                    mouse.click(ButtonEnum.START_LIFE_SKILL);
                    mouse.click(ButtonEnum.LIFE_SKILL);
                    missTime++;
                    continue;
                }
                missTime = 0;
                // 移动到钓鱼操作按钮
                mouse.move(ButtonEnum.LIFE_SKILL);
                if (gouZiLocation.getX() > fishLocation.getX()) {
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                } else {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        // 目标图
        Fishing fishing = new Fishing(robot,
                new Location(600, 430), new Location(979, 433),
                new Location(600, 392), new Location(979, 394));
        fishing.start(2000);
    }
}