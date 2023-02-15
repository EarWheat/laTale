package com.laTale.script;

import com.alibaba.fastjson.JSONObject;
import com.laTale.constants.PointLocationConstants;
import com.laTale.model.Location;

import java.awt.*;


/**
 * @Desc: 收藏喜欢
 * @Author: 泽露
 * @Date: 2023/2/14 7:32 PM
 * @Version: 1.initial version; 2023/2/14 7:32 PM
 */
public class Like {

    public Location findLikeIcon(Robot robot){
        Location start = new Location(400, 850);
        int moveY = PointLocationConstants.GAME_LEFT_BOTTOM.getY() - start.getY();
        int moveX = PointLocationConstants.GAME_RIGHT_BOTTOM.getX() - start.getX();
        // 从上往下扫
        Location temp = start;
        for (int i = 0; i < moveX; i++) {
            for (int j = 0; j < moveY; j++) {
                temp.setX(start.getX() + i);
                temp.setY(start.getY() + j);
                Color pixelColor = robot.getPixelColor(temp.getX(), temp.getY());
                System.out.println(JSONObject.toJSONString(pixelColor));
            }
        }
        return null;
    }
}
