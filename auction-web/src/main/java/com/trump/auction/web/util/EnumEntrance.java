package com.trump.auction.web.util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqingqiang
 *         分享活动入口类型
 */
public enum EnumEntrance {

    INDEX(1, "首页"), PRODUCT_DETAIL(2, "商品详情"), MY_INFO(3, "个人中心");

    @Getter
    private Integer code;

    @Getter
    private String desc;

    EnumEntrance(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<Integer, EnumEntrance> codeToEnums = new HashMap<Integer, EnumEntrance>();

    static {
        for (EnumEntrance e : values()) {
            codeToEnums.put(e.code, e);
        }
    }

    public static EnumEntrance of(Integer code) {
        EnumEntrance e = codeToEnums.get(code);
        if (e == null) {
            throw new IllegalArgumentException("No such enums code: " + code);
        }
        return e;
    }

    public static Map<Integer, EnumEntrance> getMap() {
        return codeToEnums;
    }
}
