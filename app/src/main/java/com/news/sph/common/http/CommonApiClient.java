package com.news.sph.common.http;

import android.content.Context;

import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.home.dto.LoginDTO;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.me.entity.LoginResult;
import com.news.sph.information.entity.InformationResult;
import com.news.sph.issue.entity.AdvertisingResult;
import com.news.sph.issue.entity.IndianaListResult;
import com.news.sph.issue.entity.WinningResult;
import com.news.sph.me.dto.CuringOrderListDTO;
import com.news.sph.me.dto.ModifyDTO;
import com.news.sph.me.dto.WithdrawalsDTO;
import com.news.sph.me.entity.CuringOrderListResult;
import com.news.sph.me.entity.ModifyResult;
import com.news.sph.me.entity.MyCouponResult;
import com.news.sph.me.entity.MyIncomeResult;
import com.news.sph.me.entity.TransactionResult;
import com.news.sph.unused.entity.HotTopResult;

public class CommonApiClient extends BaseApiClient {


    /**
     * 获取短信验证码
     * @param context
     * @param dto
     * @param callback
     */
    public static void getVerify(Context context, BaseDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                context, callback, BaseEntity.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResSendSmsCodeApi"), dto,
                asyncCallBack);
    }
    /**
     * 优惠劵
     * @param context
     * @param dto
     * @param callback
     */
    public static void getCoupon(Context context, BaseDTO
            dto, CallBack<MyCouponResult> callback) {
        AsyncCallBack<MyCouponResult> asyncCallBack = new AsyncCallBack<>(
                context, callback, MyCouponResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResSendSmsCodeApi"), dto,
                asyncCallBack);
    }
    /**
     * 交易明细
     * @param context
     * @param dto
     * @param callback
     */
    public static void getTransaction(Context context, BaseDTO
            dto, CallBack<TransactionResult> callback) {
        AsyncCallBack<TransactionResult> asyncCallBack = new AsyncCallBack<>(
                context, callback, TransactionResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResSendSmsCodeApi"), dto,
                asyncCallBack);
    }

    /**
     * 修改用户名
     * @param context
     * @param dto
     * @param callback
     */
    public static void modifyUser(Context context, ModifyDTO
            dto, CallBack<ModifyResult> callback) {
        AsyncCallBack<ModifyResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,ModifyResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }
    /**
     * 获取新闻
    * @param context
    * @param dto
    * @param callback
    */
    public static void getNews(Context context, BaseDTO
            dto, CallBack<InformationResult> callback) {
        AsyncCallBack<InformationResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,InformationResult.class);
        get(context, getAbsoluteUrl("/webservice/Parts.asmx/GetAllNews"), dto,
                asyncCallBack);
    }
    /**
     * 热门专题
     * @param context
     * @param dto
     * @param callback
     */
    public static void hotTopics(Context context, BaseDTO
            dto, CallBack<HotTopResult> callback) {
        AsyncCallBack<HotTopResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,HotTopResult.class);
        get(context, getAbsoluteUrl("/webservice/Parts.asmx/GetAllSpecial"), dto,
                asyncCallBack);
    }
    /**
     * 夺宝中奖轮播
     * @param context
     * @param dto
     * @param callback
     */
    public static void winning(Context context, BaseDTO
            dto, CallBack<WinningResult> callback) {
        AsyncCallBack<WinningResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,WinningResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetWinCarousel"), dto,
                asyncCallBack);
    }
    /**
     * 首页推荐图片
     * @param context
     * @param dto
     * @param callback
     */
    public static void homeRecommend(Context context, BaseDTO
            dto, CallBack<HomeRecomendResult> callback) {
        AsyncCallBack<HomeRecomendResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,HomeRecomendResult.class);
        get(context, getAbsoluteUrl("/webservice/Parts.asmx/GetSixSnaRecComm"), dto,
                asyncCallBack);
    }
    /**
     * 夺宝列表
     * @param context
     * @param dto
     * @param callback
     */
    public static void indianaList(Context context, BaseDTO
            dto, CallBack<IndianaListResult> callback) {
        AsyncCallBack<IndianaListResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,IndianaListResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetAllSnatchComm"), dto,
                asyncCallBack);
    }
    /**
     * 夺宝广告图片
     * @param context
     * @param dto
     * @param callback
     */
    public static void adcertising(Context context, BaseDTO
            dto, CallBack<AdvertisingResult> callback) {
        AsyncCallBack<AdvertisingResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,AdvertisingResult.class);
        post(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnaPic"), dto,
                asyncCallBack);
    }
    /**
     * 首页专题
     * @param context
     * @param dto
     * @param callback
     */
    public static void homeSpecial(Context context, BaseDTO
            dto, CallBack<HomeSpecialResult> callback) {
        AsyncCallBack<HomeSpecialResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,HomeSpecialResult.class);
        post(context, getAbsoluteUrl("/webservice/Parts.asmx/GetSpecial"), dto,
                asyncCallBack);
    }
    /**
     * 首页广告图片
     * @param context
     * @param dto
     * @param callback
     */
    public static void homeAdcer(Context context, BaseDTO
            dto, CallBack<HomeAdcerResult> callback) {
        AsyncCallBack<HomeAdcerResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,HomeAdcerResult.class);
        post(context, getAbsoluteUrl("/webservice/Parts.asmx/GetAd"), dto,
                asyncCallBack);
    }
    /**
     * 登录
     * @param context
     * @param dto
     * @param callback
     */
    public static void login(Context context, LoginDTO
            dto, CallBack<LoginResult> callback) {
        AsyncCallBack<LoginResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,LoginResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }
    /**
     * 我的收入
     * @param context
     * @param dto
     * @param callback
     */
    public static void myIncome(Context context, BaseDTO
            dto, CallBack<MyIncomeResult> callback) {
        AsyncCallBack<MyIncomeResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,MyIncomeResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }
    /**
     * 养护订单
     * @param context
     * @param dto
     * @param callback
     */
    public static void CuringOrderList(Context context, CuringOrderListDTO
            dto, CallBack<CuringOrderListResult> callback) {
        AsyncCallBack<CuringOrderListResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,CuringOrderListResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }
    /**
     * 我的收入提现
     * @param context
     * @param dto
     * @param callback
     */
    public static void IncomeWith(Context context, WithdrawalsDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                context, callback,BaseEntity.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }

}
