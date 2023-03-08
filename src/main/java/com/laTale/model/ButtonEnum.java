package com.laTale.model;

/**
 * @Desc: 按钮枚举
 * @Author: 泽露
 * @Date: 2023/3/6 3:47 PM
 * @Version: 1.initial version; 2023/3/6 3:47 PM
 */
public enum ButtonEnum {
    CANCEL_FISH("取消钓鱼", 1200,700),
    FISHING("开始钓鱼", 1260, 826),
    OPEN_PACKAGE("打开背包",1240,470),
    CLOSE_PACKAGE("关闭背包",1280, 330),
    USE_ITEM("使用物品",920,400),
    ACTIVITY("活力药剂",1144, 696)
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
