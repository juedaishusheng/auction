package com.trump.auction.web.service;

import com.trump.auction.activity.model.UserSignModel;
import com.trump.auction.web.util.HandleResult;

/**
 * Created by songruihuan on 2017/12/21.
 */
public interface SignService {
    HandleResult userSign(Integer userId);
    HandleResult checkIsSigned(Integer userId);
    UserSignModel findUserSignByUserId(Integer userId);

}
