package com.laTale.model;

/**
 * @Desc: 按钮枚举
 * @Author: 泽露
 * @Date: 2023/3/6 3:47 PM
 * @Version: 1.initial version; 2023/3/6 3:47 PM
 */
public enum ButtonEnum {
    START_LIFE_SKILL("开始生活技能", 1227, 721),
    CANCEL_FISH("取消钓鱼", 1178, 642),
    LIFE_SKILL("开始钓鱼", 1048, 637),
    OPEN_PACKAGE("打开背包", 1210, 480),
    CLOSE_PACKAGE("关闭背包", 1246, 394),
    USE_ITEM("使用物品", 986, 464),
    ACTIVITY("活力药剂", 1134, 665),
    LEVEL_UP("强化",1120,730),
    CONFIRM("确认",960,640),






    XIANLING("仙灵店铺", 923, 728),
    CLOSE_XIANLING("关闭仙灵店铺",600, 247),
    BUY_FLAG_LOCATION("仙灵店铺购买旗帜", 512, 280),
    BUY_SURE("仙灵店铺购买确认",590,565),
    //    ITEM_NUM_ADD("商品数量增加",0,0),
    BUY_ITEM("购买物品", 0, 0),
    OPEN_PACKET("打开背包", 0, 0),
    WAREHOUSE_PAGE_START("仓库页码起始位置", 0,0),
    WAREHOUSE_ITEM_START("仓库物品起始位置",0,0)
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
