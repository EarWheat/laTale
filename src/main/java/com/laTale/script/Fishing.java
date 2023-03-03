package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.common.Mouse;
import com.laTale.model.Location;
import com.laTale.util.ImageUtil;
import org.omg.PortableInterceptor.INACTIVE;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Desc: 钓鱼
 * @Author: 泽露
 * @Date: 2023/2/14 7:17 PM
 * @Version: 1.initial version; 2023/2/14 7:17 PM
 */
public class Fishing {
    public static final String FISH_IMG_PATH = "/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/fishing/fish.png";
    public static final String GOU_IMG_PATH = "/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/fishing/gouzi.png";

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

    public void start() throws IOException, InterruptedException {
        Mouse mouse = new Mouse(robot);
        // 外层循环
        // 点击开始挖矿
//        Mouse.click(robot, startMining.getX(), startMining.getY());
        // ================
        // 第一步：截图挖矿区域
        while (true) {
            BufferedImage fishArea = robot.createScreenCapture(new Rectangle(fishAreaLeftTop.getX(), fishAreaLeftTop.getY(), fishAreaWidth, fishAreaHeight));
            BufferedImage gouZiArea = robot.createScreenCapture(new Rectangle(gouZiAreaLeftTop.getX(), gouZiAreaLeftTop.getY(), gouZiAreaWidth, gouZiAreaHeight));
            ImageUtil.saveBfImage(gouZiArea);
            // 第二步：找到钻头和鱼的位置
            try {
                Location gouZiLocation = FindPicLocation.findColor(new int[]{255,25,56}, gouZiArea, gouZiAreaLeftTop);
                Location fishLocation = FindPicLocation.findColor(new int[]{250,250,250}, fishArea, fishAreaLeftTop);
                if(!gouZiLocation.isFind() && !fishLocation.isFind()){
                    mouse.click(990,700);
                    mouse.click(1260,826);
                    continue;
                }
                // 移动到钓鱼操作按钮
                mouse.move(1260,826);
                if(gouZiLocation.getX() > fishLocation.getX()){
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                } else {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // 第三步：找到矿石区域
        // 第四步：匹配是否挖矿
        // =================
        // 点击挖矿
    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Robot robot = new Robot();
        // 目标图
        Fishing fishing = new Fishing(robot,
                new Location(600,460), new Location(979,465),
                new Location(600,423), new Location(979,425));
        fishing.start();
    }
}
