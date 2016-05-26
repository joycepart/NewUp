package com.news.sph.common.http;

import android.content.Context;

import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.home.dto.CuringDTO;
import com.news.sph.home.dto.TranDTO;
import com.news.sph.home.entity.CuringResult;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.home.entity.TranResult;
import com.news.sph.information.entity.InformationResult;
import com.news.sph.issue.dto.DetailsDTO;
import com.news.sph.issue.dto.PicDTO;
import com.news.sph.issue.dto.SettlementDTO;
import com.news.sph.issue.dto.ToAnnounceDTO;
import com.news.sph.issue.entity.AdvertisingResult;
import com.news.sph.issue.entity.IndianaListResult;
import com.news.sph.issue.entity.IssDetailsResult;
import com.news.sph.issue.entity.PicResult;
import com.news.sph.issue.entity.SettlementResult;
import com.news.sph.issue.entity.ToAnnounceResult;
import com.news.sph.issue.entity.WinningResult;
import com.news.sph.me.dto.CuringOrderListDTO;
import com.news.sph.me.dto.LoginDTO;
import com.news.sph.me.dto.ModifyDTO;
import com.news.sph.me.dto.RecordIndianaDTO;
import com.news.sph.me.dto.UserPicDTO;
import com.news.sph.me.dto.WithdrawalsDTO;
import com.news.sph.me.entity.CuringOrderListResult;
import com.news.sph.me.entity.LoginResult;
import com.news.sph.me.entity.ModifyResult;
import com.news.sph.me.entity.MyCouponResult;
import com.news.sph.me.entity.MyIncomeResult;
import com.news.sph.me.entity.RecordIndianaResult;
import com.news.sph.me.entity.RecordsResult;
import com.news.sph.me.entity.TransactionResult;
import com.news.sph.me.entity.UserPicResult;
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
        post(context, getAbsoluteUrl("/API/ WsMemberCoupon.asmx/ResBrwMemberCouponApi"), dto,
                asyncCallBack);
    }
    /**
     * 修改用户图像
     * @param context
     * @param dto
     * @param callback
     */
    public static void userPic(Context context, UserPicDTO
            dto, CallBack<UserPicResult> callback) {
        AsyncCallBack<UserPicResult> asyncCallBack = new AsyncCallBack<>(
                context, callback, UserPicResult.class);
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ ResEdtMemberHeadImApi"), dto,
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
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ ResTransactionDetailApi"), dto,
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
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ ResEdtMemberNameApi"), dto,
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
     * 结算
     * @param context
     * @param dto
     * @param callback
     */
    public static void settlement(Context context, SettlementDTO
            dto, CallBack<SettlementResult> callback) {
        AsyncCallBack<SettlementResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,SettlementResult.class);
        post(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/BookOrders"), dto,
                asyncCallBack);
    }

    /**
     * 个人夺宝记录
     * @param context
     * @param dto
     * @param callback
     */
    public static void records(Context context, BaseDTO
            dto, CallBack<RecordsResult> callback) {
        AsyncCallBack<RecordsResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,RecordsResult.class);
        post(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOrderRecord"), dto,
                asyncCallBack);
    }


    /**
     * 往期揭晓
     * @param context
     * @param dto
     * @param callback
     */
    public static void toAnnounce(Context context, ToAnnounceDTO
            dto, CallBack<ToAnnounceResult> callback) {
        AsyncCallBack<ToAnnounceResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,ToAnnounceResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOldTimeSnatch"), dto,
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
     * 夺宝图片
     * @param context
     * @param dto
     * @param callback
     */
    public static void pic(Context context, PicDTO
            dto, CallBack<PicResult> callback) {
        AsyncCallBack<PicResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,PicResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetPicsBySnaCode"), dto,
                asyncCallBack);
    }

    /**
     * 往期详情
     * @param context
     * @param dto
     * @param callback
     */
    public static void tranDetails(Context context, TranDTO
            dto, CallBack<TranResult> callback) {
        AsyncCallBack<TranResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,TranResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOldSnatchComm"), dto,
                asyncCallBack);
    }

    /**
     * 夺宝详情
     * @param context
     * @param dto
     * @param callback
     */
    public static void issDetails(Context context, DetailsDTO
            dto, CallBack<IssDetailsResult> callback) {
        AsyncCallBack<IssDetailsResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,IssDetailsResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnatchComm"), dto,
                asyncCallBack);
    }

    /**
     * 夺宝记录之夺宝详情
     * @param context
     * @param dto
     * @param callback
     */
    public static void recordsDetails(Context context, RecordIndianaDTO
            dto, CallBack<RecordIndianaResult> callback) {
        AsyncCallBack<RecordIndianaResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,RecordIndianaResult.class);
        get(context, getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnaRecordByBatCode"), dto,
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
     * 专业养护
     * @param context
     * @param dto
     * @param callback
     */
    public static void curing(Context context, CuringDTO
            dto, CallBack<CuringResult> callback) {
        AsyncCallBack<CuringResult> asyncCallBack = new AsyncCallBack<>(
                context, callback,CuringResult.class);
        post(context, getAbsoluteUrl("/webservice/Parts.asmx/GetSellServer"), dto,
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
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ ResMemberMoneyNotCashAmountApi"), dto,
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
        post(context, getAbsoluteUrl("/API/WsMember.asmx/ ResPresentApplicationApi"), dto,
                asyncCallBack);
    }

}
