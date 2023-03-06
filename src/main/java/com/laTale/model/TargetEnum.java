package com.laTale.model;

/**
 * @Desc: 目标枚举
 * @Author: 泽露
 * @Date: 2023/3/6 5:26 PM
 * @Version: 1.initial version; 2023/3/6 5:26 PM
 */
public enum TargetEnum {
    FISH(250, 250, 250),
    FISH_GOUZI(255, 25, 56),
    DRILL_ZUANTOU(250,250,250);

    private Integer r;
    private Integer g;
    private Integer b;

    TargetEnum(Integer r, Integer g, Integer b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Integer getR() {
        return r;
    }

    public Integer getG() {
        return g;
    }

    public Integer getB() {
        return b;
    }

    public int[] getRGB() {
        return new int[]{r, g, b};
    }
}
