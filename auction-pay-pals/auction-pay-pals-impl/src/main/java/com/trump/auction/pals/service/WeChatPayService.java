package com.trump.auction.pals.service;

import com.trump.auction.pals.api.model.wechat.WeChatPayBackRequest;
import com.trump.auction.pals.api.model.wechat.WeChatPayBackResponse;
import com.trump.auction.pals.api.model.wechat.WeChatPayQueryRequest;
import com.trump.auction.pals.api.model.wechat.WeChatPayQueryResponse;
import com.trump.auction.pals.api.model.wechat.WeChatPayRequest;
import com.trump.auction.pals.api.model.wechat.WeChatPayResponse;

/**
 * 微信支付
 */
public interface WeChatPayService {

    WeChatPayResponse toWeChatPay(WeChatPayRequest apr);

    WeChatPayBackResponse toWeChatPayBack(WeChatPayBackRequest abr);

	WeChatPayQueryResponse queryWeChatPay(WeChatPayQueryRequest wcp);

	String queryBatchNoByPrePayId(String prepayId);
}
