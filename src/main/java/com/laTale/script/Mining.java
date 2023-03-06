package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.common.Mouse;
import com.laTale.model.AreaEnum;
import com.laTale.model.ButtonEnum;
import com.laTale.model.Location;
import com.laTale.model.TargetEnum;
import com.laTale.util.ImageUtil;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
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
     * 钻头界面左上角
     */
    public Location drillAreaLeftTop;

    /**
     * 钻头界面右下角
     */
    public Location drillAreaRightBottom;

    /**
     * 矿区左上角
     */
    public Location mineralAreaLeftTop;

    /**
     * 矿区右下角
     */
    public Location mineralAreaRightBottom;

    /**
     * 钻头界面宽
     */
    public Integer drillAreaWidth;

    /**
     * 钻头界面高
     */
    public Integer drillAreaHeight;

    public Robot robot;

    public Mining(Robot robot, Location drillAreaLeftTop, Location drillAreaRightBottom, Location mineralAreaLeftTop, Location mineralAreaRightBottom) {
        this.robot = robot;
        this.drillAreaLeftTop = drillAreaLeftTop;
        this.drillAreaRightBottom = drillAreaRightBottom;
        this.mineralAreaLeftTop = mineralAreaLeftTop;
        this.mineralAreaRightBottom = mineralAreaRightBottom;
        this.drillAreaWidth = drillAreaRightBottom.getX() - drillAreaLeftTop.getX();
        this.drillAreaHeight = drillAreaRightBottom.getY() - drillAreaLeftTop.getY();
    }

    public void start(Integer times){
        Mouse mouse = new Mouse(robot);
        // 丢失钓鱼区域次数，用于判断是否补充活力药剂
        int missTime = 0;
        int loop = 0;
        while (loop++ < times) {
            // 挖矿区域
            BufferedImage oreArea = robot.createScreenCapture(new Rectangle(AreaEnum.DRILL_ORE.getX(), AreaEnum.DRILL_ORE.getY(), AreaEnum.DRILL_ORE.getWidth(), AreaEnum.DRILL_ORE.getHigh()));
            // 钻头区域
            BufferedImage zuanTouArea = robot.createScreenCapture(new Rectangle(AreaEnum.DRILL_ZUANTOU.getX(), AreaEnum.DRILL_ZUANTOU.getY(), AreaEnum.FISHING_GOUZI.getWidth(), AreaEnum.DRILL_ZUANTOU.getHigh()));
            try {
                // 第二步：找到吊钩和鱼的位置
                Location zuanTouLocation = FindPicLocation.findColor(TargetEnum.DRILL_ZUANTOU.getRGB(), zuanTouArea, AreaEnum.DRILL_ZUANTOU.getLeftTop());
//                Location oreLocation = FindPicLocation.findColor(TargetEnum.DRILL_O.getRGB(), fishArea, fishAreaLeftTop);
//                if (!gouZiLocation.isFind() && !fishLocation.isFind()) {
//                    if (missTime >= 20) {
//                        fillActivity(mouse);
//                        missTime = 0;
//                    }
//                    mouse.click(990, 700);
//                    mouse.click(ButtonEnum.FISHING);
//                    missTime++;
//                    continue;
//                }
//                missTime = 0;
//                // 移动到钓鱼操作按钮
//                mouse.move(ButtonEnum.FISHING);
//                if (gouZiLocation.getX() > fishLocation.getX()) {
//                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
//                } else {
//                    robot.mousePress(InputEvent.BUTTON1_MASK);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        // 目标图
//        Mining mining = new Mining(robot, new Location(0, 0), new Location(width,height));
//        mining.start();
    }
}