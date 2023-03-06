package com.laTale.model;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/3/6 3:47 PM
 * @Version: 1.initial version; 2023/3/6 3:47 PM
 */
public enum ButtonEnum {
    CANCEL_FISH("取消钓鱼", 1200,700),
    OPEN_PACKAGE("打开背包",1240,470),
    CLOSE_PACKAGE("关闭背包",1280, 330),
    USE_ITEM("使用物品",900,440)
    ;
    ButtonEnum(String name, Integer x, Integer y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    private String name;
    private Integer x;
    private Integer y;

    public String getName() {
        return name;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
