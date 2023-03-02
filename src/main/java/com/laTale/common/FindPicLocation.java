package com.laTale.common;

import com.laTale.model.Location;

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
public class FindPicLocation {


    public static final Double MATCH_RATE = 0.1;

    public static final Integer RGB_RATE = 5;

    /**
     * 从sourceImage中匹配targetImg
     *
     * @param targetImg
     * @param sourceImage
     * @return
     */
    public static Location findImg(BufferedImage targetImg, BufferedImage sourceImage, Location sourceLeftTop) {
        Location targetLocation = new Location(sourceLeftTop.getX(), sourceLeftTop.getY());
        int sourceImgHeight = sourceImage.getHeight();
        int sourceShotImgWidth = sourceImage.getWidth();
        int targetImgHeight = targetImg.getHeight();
        int targetImgWidth = targetImg.getWidth();
        int[][] targetImageRGBData = getImageRGB(targetImg);
        int[][] sourceImageRGBData = getImageRGB(sourceImage);
        //遍历屏幕截图像素点数据
        for (int y = 0; y < sourceImgHeight - targetImgHeight; y++) {
            for (int x = 0; x < sourceShotImgWidth - targetImgWidth; x++) {
//                if ((targetImageRGBData[0][0] ^ sourceImageRGBData[y][x]) == 0
//                        && (targetImageRGBData[0][targetImgWidth - 1] ^ sourceImageRGBData[y][x + targetImgWidth - 1]) == 0
//                        && (targetImageRGBData[targetImgHeight - 1][targetImgWidth - 1] ^ sourceImageRGBData[y + targetImgHeight - 1][x + targetImgWidth - 1]) == 0
//                        && (targetImageRGBData[targetImgHeight - 1][0] ^ sourceImageRGBData[y + targetImgHeight - 1][x]) == 0) {

                boolean isFinded = isMatchAll(y, x, targetImgHeight, targetImgWidth, sourceImgHeight, sourceShotImgWidth, targetImageRGBData, sourceImageRGBData);
                //如果比较结果完全相同，则说明图片找到，填充查找到的位置坐标数据到查找结果数组。
                if (isFinded) {
                    //0
                    int maxY = y + targetImgHeight;
                    targetLocation.setY(targetLocation.getY() + ((y + maxY) / 2));
//                        targetLocation.setY((y + maxY) / 2);
                    //1
                    int maxX = x + targetImgWidth;
                    targetLocation.setX(targetLocation.getX() + ((x + maxX) / 2));
//                        targetLocation.setX((x + maxX) / 2);
                    break;
                }
//                }
            }
        }
        return targetLocation;
    }

    /**
     * 目标区域寻找颜色
     *
     * @return
     */
    public static Location findColor(int[] rgb, BufferedImage image, Location leftTop) {
        if (rgb.length < 3) {
            return new Location();
        }
        Location result = new Location();
        int width = image.getWidth();
        int height = image.getHeight();
        int[] temp = new int[3];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                int pixel = image.getRGB(w, h);
                temp[0] = (pixel & 0xff0000) >> 16;
                temp[1] = (pixel & 0xff00) >> 8;
                temp[2] = (pixel & 0xff);
                if (Math.abs(rgb[0] - temp[0]) <= RGB_RATE
                        && Math.abs(rgb[1] - temp[1]) <= RGB_RATE
                        && Math.abs(rgb[2] - temp[2]) <= RGB_RATE) {
                    result.setX(leftTop.getX() + w);
                    result.setY(leftTop.getY() + h);
                    return result;
                }
            }
        }
        return result;
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


    /**
     * 根据图片IO获取RGB
     *
     * @param bfImage 图片IO
     * @return int[][]
     * @author liutong
     * @date 2022/4/23 15:27
     */
    public static int[][] getImageRGB(BufferedImage bfImage) {
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
     * 是否全部匹配
     *
     * @param y
     * @param x
     * @return boolean
     * @author liutong
     * @date 2022/4/23 15:27
     */
    public static boolean isMatchAll(int y, int x, int targetImgHeight, int targetImgWidth, int sourceImgHeight, int sourceImgWidth, int[][] targetImgRGBData, int[][] sourceImgRGBData) {
        int biggerY = 0;
        int biggerX = 0;
        int xor = 0;
        int matchs = 0;
        for (int smallerY = 0; smallerY < targetImgHeight; smallerY++) {
            biggerY = y + smallerY;
            for (int smallerX = 0; smallerX < targetImgWidth; smallerX++) {
                biggerX = x + smallerX;
                if (biggerY >= sourceImgHeight || biggerX >= sourceImgWidth) {
                    return false;
                }
                xor = targetImgRGBData[smallerY][smallerX] ^ sourceImgRGBData[biggerY][biggerX];
                if (xor == 0) {
                    matchs++;
                    if (matchs > (targetImgHeight * targetImgWidth * MATCH_RATE)) {
                        return true;
                    }
                }
            }
        }
        return matchs > (targetImgHeight * targetImgWidth * MATCH_RATE);
    }

}
