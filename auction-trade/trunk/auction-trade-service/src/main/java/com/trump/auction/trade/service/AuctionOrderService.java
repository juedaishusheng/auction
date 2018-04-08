package com.trump.auction.trade.service;

import com.cf.common.util.page.Paging;
import com.trump.auction.trade.domain.AuctionProductRecord;
import com.trump.auction.trade.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 竞拍订单信息service
 * @author Administrator
 * @date 2018/1/6
 */
public interface AuctionOrderService {


    /**
     * 查询竞拍订单信息分页列表
     * @param paramVo
     * @return
     */
    Paging<AuctionOrderVo> findAuctionOrder(ParamVo paramVo);


    /**
     * 获取最新的出价记录
     * @param paramVo
     * @return
     */
    List<BidVo> getLastBidRecord(ParamVo paramVo);


    /**
     * 获取动态数据
     * @param paramVo
     * @return
     */
    AuctionOrderVo getDynamicData(ParamVo paramVo);

    /**
     * 获取列表上的价格和倒计时动态数据
     * @param paramVo
     * @return
     */
    AuctionOrderForListVo getDynamicDataForList(ParamVo paramVo);

    /**
     * 获取拍品基础数据
     * @param paramVo
     * @return
     */
    AuctionOrderVo getAuctionBaseData(ParamVo paramVo);


    /**
     * 通过拍品期数ID查询记录
     * @param id
     * @return
     */
    AuctionProductRecordVo getRecordByAuctionId(Integer id);

    /**
     * 分页查询出价记录
     * @param paramVo
     * @return
     */
    Paging<BidVo> getBidRecordPage(ParamVo paramVo);

    /**
     * 往期订单信息分页列表
     * @param paramVo
     * @return
     */
    Paging<AuctionOrderVo> findPastOrder(ParamVo paramVo);

    /**
     * 获取往期订单信息
     * @param auctionId
     * @return
     */
    AuctionOrderVo getPastOrder(Integer auctionId);


    /**
     * 获取指定用户的拍中订单,userId,auctionIdList
     * @param paramVos
     * @return
     */
    List<AuctionVo> getUserOrder(List<ParamVo> paramVos) throws Exception;


}
