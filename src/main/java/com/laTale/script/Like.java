package com.laTale.script;

import com.laTale.common.FindPicLocation;
import com.laTale.model.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * @Desc: 收藏喜欢
 * @Author: 泽露
 * @Date: 2023/2/14 7:32 PM
 * @Version: 1.initial version; 2023/2/14 7:32 PM
 */
public class Like {

    public Location findLike(Robot robot) {
        try {
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            BufferedImage targetImage = ImageIO.read(new File("/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/like.png"));
            BufferedImage sourceImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
            FindPicLocation.saveBfImage(sourceImage);
            return FindPicLocation.find(targetImage, sourceImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
