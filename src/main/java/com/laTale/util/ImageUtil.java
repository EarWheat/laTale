package com.laTale.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/3/2 7:27 PM
 * @Version: 1.initial version; 2023/3/2 7:27 PM
 */
public class ImageUtil {

    public static void saveBfImage(BufferedImage bufferedImage) {
        try {
            ImageIO.write(bufferedImage, "png", new File("/Users/liuzhaolu/IdeaProjects/laTale/src/main/resources/images/screen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveBfImage(BufferedImage bufferedImage, String path) {
        try {
            ImageIO.write(bufferedImage, "png", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
