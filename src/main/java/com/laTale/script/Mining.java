package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.common.FindPicPoint;
import com.laTale.common.Mouse;
import com.laTale.model.Location;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

/**
 * @Desc: 挖矿脚本
 * @Author: 泽露
 * @Date: 2023/2/16 2:48 PM
 * @Version: 1.initial version; 2023/2/16 2:48 PM
 */
@Data
public class Mining implements Serializable {

    public static final String DRILL_IMG_PATH = "/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/mining/drill.png";
    private static final long serialVersionUID = 3500613666762357310L;
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

    public void start(){
        Mouse mouse = new Mouse(robot);
        // 外层循环
        // 点击开始挖矿
//        Mouse.click(robot, startMining.getX(), startMining.getY());
        // ================
        // 第一步：截图挖矿区域
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        BufferedImage minerArea = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        FindPicLocation.saveBfImage(minerArea);
        // 第二步：找到钻头位置
        try {
            BufferedImage drillImg = ImageIO.read(new File(DRILL_IMG_PATH));
            Location location = FindPicLocation.find(drillImg, minerArea);
            mouse.move(location);
        } catch (Exception e){
            e.printStackTrace();
        }
        // 第三步：找到矿石区域
        // 第四步：匹配是否挖矿
        // =================
        // 点击挖矿
    }

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(255, 300, 10, 20));
        FindPicLocation.saveBfImage(screenCapture, DRILL_IMG_PATH);
        Mining mining = new Mining(robot);
        // 设置挖矿区域
        Location leftTop = new Location(0,75);
        Location rightBottom = new Location(650,400);
        mining.setMinerAreaLeftTop(leftTop);
        mining.setMinerAreaRightBottom(rightBottom);
        mining.start();
    }
}
