package com.trump.auction.cust.api;

import com.trump.auction.cust.model.PromotionChannelModel;

/**
 * 推广渠道信息相关
 * @author wangbo 2018/2/5.
 */
public interface PromotionChannelStubService {
    /**
     * 根据渠道名称查询渠道信息
     * @param channelName 渠道名称
     * @return 渠道信息
     */
    PromotionChannelModel findChannelByName(String channelName);

    /**
     * 根据渠道Id查询渠道信息
     * @param channelId 渠道id
     * @return 渠道信息
     */
    PromotionChannelModel findByChannelId(String channelId);
}
