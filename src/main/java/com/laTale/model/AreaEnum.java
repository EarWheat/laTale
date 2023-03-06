package com.laTale.model;

/**
 * @Desc: 区域枚举
 * @Author: 泽露
 * @Date: 2023/3/6 5:20 PM
 * @Version: 1.initial version; 2023/3/6 5:20 PM
 */
public enum AreaEnum {
    FISHING_FISH("钓鱼鱼区", new Location(600, 460), new Location(979, 465)),
    FISHING_GOUZI("钓鱼钩子区", new Location(600, 423), new Location(979, 425)),
    DRILL_ZUANTOU("挖矿钻头", new Location(600, 460), new Location(979, 465)),
    DRILL_ORE("挖矿矿区", new Location(600, 460), new Location(979, 465));

    public String name;
    public Location leftTop;
    public Location bottomRight;

    AreaEnum(String name, Location leftTop, Location bottomRight) {
        this.name = name;
        this.leftTop = leftTop;
        this.bottomRight = bottomRight;
    }

    public Location getLeftTop() {
        return leftTop;
    }

    public Location getBottomRight() {
        return bottomRight;
    }
}
