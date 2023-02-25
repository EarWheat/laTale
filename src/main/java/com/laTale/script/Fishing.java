package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.common.Mouse;
import com.laTale.model.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * 钻头位置
     */
    public Location drill;

    /**
     * 矿石位置
     */
    public Location mineral;

    /**
     * 钓鱼界面左上角
     */
    public Location fishAreaLeftTop;

    /**
     * 挖矿界面右下角
     */
    public Location fishAreaRightBottom;

    /**
     * 点击开始挖矿位置
     */
    public Location startMining;

    public Robot robot;

    public Fishing(Robot robot) {
        this.robot = robot;
    }

    public void start() throws IOException, InterruptedException {
        Mouse mouse = new Mouse(robot);
        // 外层循环
        // 点击开始挖矿
//        Mouse.click(robot, startMining.getX(), startMining.getY());
        // ================
        // 第一步：截图挖矿区域
        fishAreaLeftTop = new Location(150,140);
        BufferedImage fishImg = ImageIO.read(new File(FISH_IMG_PATH));
        BufferedImage gouZiImg = ImageIO.read(new File(GOU_IMG_PATH));
        while (true) {
            BufferedImage fishArea = robot.createScreenCapture(new Rectangle(fishAreaLeftTop.getX(), fishAreaLeftTop.getY(), 1282, 800));
            FindPicLocation.saveBfImage(fishArea);
            // 第二步：找到钻头位置
            try {
                Location location = FindPicLocation.find(fishImg, fishArea, fishAreaLeftTop);
                mouse.move(location);
            } catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(5000);
        }
        // 第三步：找到矿石区域
        // 第四步：匹配是否挖矿
        // =================
        // 点击挖矿
    }

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Robot robot = new Robot();
        // 目标图
//        BufferedImage drill = robot.createScreenCapture(new Rectangle(963, 396, 5, 5));
//        FindPicLocation.saveBfImage(drill, FISH_IMG_PATH);
//        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
////        Fishing fishing = new Fishing(robot, new Location(0, 0), new Location(width,height));
        Fishing fishing = new Fishing(robot);
        fishing.start();
    }
}
