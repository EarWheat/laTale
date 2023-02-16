package com.laTale.common;

import com.laTale.constants.PointLocationConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Desc: 查找目标图片位置
 * @Author: 泽露
 * @Date: 2023/2/15 11:19 AM
 * @Version: 1.initial version; 2023/2/15 11:19 AM
 */
public class FindPicPoint {
    /**
     * 目标图片
     */
    BufferedImage keyImage;
    /**
     * 当前屏幕宽度
     */
    int scrShotImgWidth;
    /**
     * 当前屏幕高度
     */
    int scrShotImgHeight;
    /**
     * 目标图片宽度
     */
    int keyImgWidth;
    /**
     * 目标图片高度
     */
    int keyImgHeight;
    /**
     * 当前屏幕RGB数据
     */
    int[][] screenShotImageRGBData;
    /**
     * 目标图片RGB数据
     */
    int[][] keyImageRGBData;
    /**
     * 结果X
     */
    int findX;
    /**
     * 结果Y
     */
    int findY;

    /**
     * 屏幕截屏
     */
    public static BufferedImage screenShotImage = null;

    Robot robot;

    public FindPicPoint(Robot robot, String keyImagePath) {
        this.robot = robot;
        //读取目标图片
        keyImage = this.getBfImageFromPath(keyImagePath);
        //获取图片长宽
        keyImgWidth = keyImage.getWidth();
        keyImgHeight = keyImage.getHeight();
        // 获取屏幕截屏
        screenShotImage = getBfImageFromScreenShotImage(this.robot);
        //获取截屏RGB
        screenShotImageRGBData = this.getImageGRB(screenShotImage);
        //获取图片RGB
        keyImageRGBData = this.getImageGRB(keyImage);
        //获取截屏长宽
        scrShotImgWidth = screenShotImage.getWidth();
        scrShotImgHeight = screenShotImage.getHeight();
        //开始查找
        this.findImageXY();
    }


    /**
     * 根据图片获取IO
     *
     * @param keyImagePath 路径
     * @return java.awt.image.BufferedImage
     * @author liutong
     * @date 2022/4/23 15:25
     */
    public BufferedImage getBfImageFromPath(String keyImagePath) {
        BufferedImage bfImage = null;
        try {
            bfImage = ImageIO.read(new File(keyImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    public void saveBfImage(BufferedImage bufferedImage, String savePath){
        try {
            ImageIO.write(bufferedImage, "png", new File(savePath));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取屏幕截图
     *
     * @param robot
     * @return
     */
    public BufferedImage getBfImageFromScreenShotImage(Robot robot) {
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(0, 0, width, height));
//        saveBfImage(screenCapture, "/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/screen.png");
        return screenCapture;
    }

    /**
     * 根据图片IO获取RGB
     *
     * @param bfImage 图片IO
     * @return int[][]
     * @author liutong
     * @date 2022/4/23 15:27
     */
    public int[][] getImageGRB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
        }
        return result;
    }

    /**
     * 根据像素点定位
     *
     * @return null
     * @author liutong
     * @date 2022/4/23 15:12
     */
    public void findImageXY() {
        //遍历屏幕截图像素点数据
        for (int y = 0; y < scrShotImgHeight - keyImgHeight; y++) {
            for (int x = 0; x < scrShotImgWidth - keyImgWidth; x++) {
                if ((keyImageRGBData[0][0] ^ screenShotImageRGBData[y][x]) == 0
                        && (keyImageRGBData[0][keyImgWidth - 1] ^ screenShotImageRGBData[y][x + keyImgWidth - 1]) == 0
                        && (keyImageRGBData[keyImgHeight - 1][keyImgWidth - 1] ^ screenShotImageRGBData[y + keyImgHeight - 1][x + keyImgWidth - 1]) == 0
                        && (keyImageRGBData[keyImgHeight - 1][0] ^ screenShotImageRGBData[y + keyImgHeight - 1][x]) == 0) {

                    boolean isFinded = isMatchAll(y, x);
                    //如果比较结果完全相同，则说明图片找到，填充查找到的位置坐标数据到查找结果数组。
                    if (isFinded) {
                        //0
                        int minY = y;
                        int maxY = y + keyImgHeight;
                        findY = ((minY + maxY) / 2);
                        //1
                        int minX = x;
                        int maxX = x + keyImgWidth;
                        findX = ((minX + maxX) / 2);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 是否全部匹配
     *
     * @param y
     * @param x
     * @return boolean
     * @author liutong
     * @date 2022/4/23 15:27
     */
    public boolean isMatchAll(int y, int x) {
        int biggerY = 0;
        int biggerX = 0;
        int xor = 0;
        int matchs = 0;
        for (int smallerY = 0; smallerY < keyImgHeight; smallerY++) {
            biggerY = y + smallerY;
            for (int smallerX = 0; smallerX < keyImgWidth; smallerX++) {
                biggerX = x + smallerX;
                if (biggerY >= scrShotImgHeight || biggerX >= scrShotImgWidth) {
                    return false;
                }
                xor = keyImageRGBData[smallerY][smallerX] ^ screenShotImageRGBData[biggerY][biggerX];
                if (xor == 0) {
                    matchs++;
                }
            }
        }
        return matchs > (keyImgHeight * keyImgWidth * 0.5);
    }

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        String imgPath = "/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/like.png";
        FindPicPoint findPicPoint = new FindPicPoint(robot, imgPath);
        System.out.println(findPicPoint.findX);
        System.out.println(findPicPoint.findY);
        robot.mouseMove(findPicPoint.findX, findPicPoint.findY);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.setAutoDelay(500);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
