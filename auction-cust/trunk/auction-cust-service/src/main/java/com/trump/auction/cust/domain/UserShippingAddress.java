package com.trump.auction.cust.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户地址实体
 * @author wangbo
 */
@Data
@ToString
public class UserShippingAddress {
    private Integer id;
    private Integer userId;
    private String userName;
    private String userPhone;
    private Integer provinceCode;
    private Integer cityCode;
    private Integer districtCode;
    private Integer townCode;
    private String address;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String townName;
    private Integer addressType;
    private String postCode;
    private Date createTime;
    private Date updateTime;
    private Integer status;

}