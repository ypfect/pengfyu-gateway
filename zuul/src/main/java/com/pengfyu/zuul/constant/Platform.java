package com.pengfyu.zuul.constant;

/**
 * @author luis
 * 2018/7/16
 * @description
 */
public enum Platform {
    OPERATER("101", "运营端"),
    GUIDER("102", "导购端"),
    SHOP("103", "店铺平台"),
    SELLER("104", "商家后台"),
    WORKER("105", "工人端"),
    CONSUMER("106", "用户端");

    private String status;
    private String desc;

    Platform(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
