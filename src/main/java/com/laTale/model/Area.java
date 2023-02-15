package com.laTale.model;

import com.laTale.constants.PointLocationConstants;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 区块，不能点扫描，需要区块扫描
 * @Author: 泽露
 * @Date: 2023/2/14 7:49 PM
 * @Version: 1.initial version; 2023/2/14 7:49 PM
 */
@Data
public class Area implements Serializable {

    public Robot robot;
    /**
     * 中心点
     */
    public Location center;

    /**
     * 距中心高，注意上下需*2
     */
    public Integer high = 10;

    /**
     * 距中心宽，注意左右需*2
     */
    public Integer width = 10;

    /**
     * 需要匹配的点
     */
    public Integer needMatchPoint = 3;

    public Area(Robot robot) {
        this.robot = robot;
    }

    /**
     * icon是否匹配
     *
     * @param featurePoint
     * @return
     */
    public Location matchIcon(List<Color> featurePoint) {
        int matchPoints = 0;
        int centerX = center.getX();
        int centerY = center.getY();
        // 抓取中心内所有点
        int startX = centerX - width;
        int startY = centerY - high;
        int endX = centerY + width;
        int endY = centerY + high;
        for (int i = startX; i < endX; i += PointLocationConstants.scanStep) {
            for (int j = startY; j < endY; j += PointLocationConstants.scanStep) {
                Color pixelColor = robot.getPixelColor(i, j);
                System.out.println("i:" + i + "----j:" + j + "=====" + pixelColor.getRGB());
                // 匹配
                if (featurePoint.contains(pixelColor)) {
                    matchPoints++;
                    if (matchPoints > needMatchPoint) {
                        return center;
                    }
                }
            }
        }
        System.out.println("match:" + matchPoints);
        return null;
    }

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Area area = new Area(robot);
        area.setCenter(new Location(754,796));
        List<Color> featurePoint = new ArrayList<>();
        Color color1 = new Color(92,92,92);
        Color color2 = new Color(126,126,126);
        Color color3 = new Color(160,160,160);
        Color color4 = new Color(100,100,100);
        System.out.println(color1.getRGB());
        System.out.println(color2.getRGB());
        System.out.println(color3.getRGB());
        System.out.println(color4.getRGB());
        featurePoint.add(color1);
        featurePoint.add(color2);
        featurePoint.add(color3);
        featurePoint.add(color4);
        area.matchIcon(featurePoint);
    }
}
