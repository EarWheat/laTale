package com.laTale.constants;

import com.laTale.model.Location;

/**
 * @Desc: 关键坐标点
 * @Author: 泽露
 * @Date: 2023/2/14 7:24 PM
 * @Version: 1.initial version; 2023/2/14 7:24 PM
 */
public class PointLocationConstants {

    /**
     * 左上角坐标
     */
    public static final Location GAME_LEFT_HEAD = new Location(400, 240);

    /**
     * 左下角坐标
     */
    public static final Location GAME_LEFT_BOTTOM = new Location(400, 900);


    /**
     * 右上角坐标
     */
    public static final Location GAME_RIGHT_HEAD = new Location(1400,240);

    /**
     * 右下角坐标
     */
    public static final Location GAME_RIGHT_BOTTOM = new Location(1400,900);

    /**
     * 每次扫描的步长，太短了的话太慢了
     */
    public static Integer scanStep = 1;
}
