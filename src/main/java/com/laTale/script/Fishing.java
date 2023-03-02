package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.common.Mouse;
import com.laTale.model.Location;
import com.laTale.util.ImageUtil;
import org.omg.PortableInterceptor.INACTIVE;

import javax.imageio.ImageIO;
import java.awt.*;
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
     * 点击开始挖矿位置
     */
    public Location startMining;

    public Robot robot;

    public Fishing(Robot robot, Location fishAreaLeftTop, Location fishAreaRightBottom) {
        this.robot = robot;
        this.fishAreaLeftTop = fishAreaLeftTop;
        this.fishAreaRightBottom = fishAreaRightBottom;
        this.fishAreaWidth = fishAreaRightBottom.getX() - fishAreaLeftTop.getX();
        this.fishAreaHeight = fishAreaRightBottom.getY() - fishAreaLeftTop.getY();
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
        BufferedImage fishImg = ImageIO.read(new File(FISH_IMG_PATH));
        BufferedImage gouZiImg = ImageIO.read(new File(GOU_IMG_PATH));
//        while (true) {
            BufferedImage fishArea = robot.createScreenCapture(new Rectangle(fishAreaLeftTop.getX(), fishAreaLeftTop.getY(), fishAreaWidth, fishAreaHeight));
            ImageUtil.saveBfImage(fishArea);
            // 第二步：找到钻头和鱼的位置
            try {
//                Location location = FindPicLocation.findImg(fishImg, fishArea, fishAreaLeftTop);
                Location colorLocation = FindPicLocation.findColor(new int[]{250,250,250}, fishArea, fishAreaLeftTop);
                mouse.move(colorLocation);
            } catch (Exception e){
                e.printStackTrace();
            }
//            Thread.sleep(5000);
//        }
        // 第三步：找到矿石区域
        // 第四步：匹配是否挖矿
        // =================
        // 点击挖矿
    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Robot robot = new Robot();
        // 目标图
        Fishing fishing = new Fishing(robot, new Location(470,328), new Location(858,356));
        fishing.start();
    }
}
