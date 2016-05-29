package com.news.sph.common.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.home.dto.CuringDTO;
import com.news.sph.home.dto.PayDTO;
import com.news.sph.home.dto.TranDTO;
import com.news.sph.home.entity.CuringResult;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.home.entity.PayResult;
import com.news.sph.home.entity.TranResult;
import com.news.sph.information.entity.InformationResult;
import com.news.sph.issue.dto.AnnouncedDTO;
import com.news.sph.issue.dto.CalcutionDTO;
import com.news.sph.issue.dto.DetailsDTO;
import com.news.sph.issue.dto.LanderInDTO;
import com.news.sph.issue.dto.PicDTO;
import com.news.sph.issue.dto.SettlementDTO;
import com.news.sph.issue.dto.ToAnnounceDTO;
import com.news.sph.issue.entity.AdvertisingResult;
import com.news.sph.issue.entity.AnnouncedResult;
import com.news.sph.issue.entity.CalculationResult;
import com.news.sph.issue.entity.IndianaListResult;
import com.news.sph.issue.entity.IssDetailsResult;
import com.news.sph.issue.entity.LanderInResult;
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
     * @param dto
     * @param callback
     */
    public static void getVerify(Activity act, BaseDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResSendSmsCodeApi"), dto,
                asyncCallBack);
    }
    /**
     * 优惠劵
     * @param dto
     * @param callback
     */
    public static void getCoupon(Fragment fragment, BaseDTO
            dto, CallBack<MyCouponResult> callback) {
        AsyncCallBack<MyCouponResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback, MyCouponResult.class);
        post(getAbsoluteUrl("/API/WsMemberCoupon.asmx/ResBrwMemberCouponApi"), dto,
                asyncCallBack);
    }
    /**
     * 修改用户图像
     * @param dto
     * @param callback
     */
    public static void userPic(Activity act, UserPicDTO
            dto, CallBack<UserPicResult> callback) {
        AsyncCallBack<UserPicResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, UserPicResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResEdtMemberHeadImApi"), dto,
                asyncCallBack);
    }
    /**
     * 交易明细
     * @param dto
     * @param callback
     */
    public static void getTransaction(Activity act, BaseDTO
            dto, CallBack<TransactionResult> callback) {
        AsyncCallBack<TransactionResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, TransactionResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResTransactionDetailApi"), dto,
                asyncCallBack);
    }

    /**
     * 修改用户名
     * @param dto
     * @param callback
     */
    public static void modifyUser(Activity act, ModifyDTO
            dto, CallBack<ModifyResult> callback) {
        AsyncCallBack<ModifyResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,ModifyResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResEdtMemberNameApi"), dto,
                asyncCallBack);
    }
    /**
     * 获取新闻
    * @param dto
    * @param callback
    */
    public static void getNews(Fragment fragment, BaseDTO
            dto, CallBack<InformationResult> callback) {
        AsyncCallBack<InformationResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,InformationResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetAllNews"), dto,
                asyncCallBack);
    }

    /**
     * 结算
     * @param dto
     * @param callback
     */
    public static void settlement(Activity act, SettlementDTO
            dto, CallBack<SettlementResult> callback) {
        AsyncCallBack<SettlementResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,SettlementResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/BookOrders"), dto,
                asyncCallBack);
    }

    /**
     * 个人夺宝记录
     * @param dto
     * @param callback
     */
    public static void records(Fragment fragment, BaseDTO
            dto, CallBack<RecordsResult> callback) {
        AsyncCallBack<RecordsResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,RecordsResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOrderRecord"), dto,
                asyncCallBack);
    }


    /**
     * 往期揭晓
     * @param dto
     * @param callback
     */
    public static void toAnnounce(Fragment fragment, ToAnnounceDTO
            dto, CallBack<ToAnnounceResult> callback) {
        AsyncCallBack<ToAnnounceResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,ToAnnounceResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOldTimeSnatch"), dto,
                asyncCallBack);
    }
    /**
     * 热门专题
     * @param dto
     * @param callback
     */
    public static void hotTopics(Fragment fragment, BaseDTO
            dto, CallBack<HotTopResult> callback) {
        AsyncCallBack<HotTopResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,HotTopResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetAllSpecial"), dto,
                asyncCallBack);
    }
    /**
     * 夺宝中奖轮播
     * @param dto
     * @param callback
     */
    public static void winning(Fragment fragment, BaseDTO
            dto, CallBack<WinningResult> callback) {
        AsyncCallBack<WinningResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,WinningResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetWinCarousel"), dto,
                asyncCallBack);
    }

    /**
     * 夺宝图片
     * @param dto
     * @param callback
     */
    public static void pic(Fragment fragment, PicDTO
            dto, CallBack<PicResult> callback) {
        AsyncCallBack<PicResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,PicResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetPicsBySnaCode"), dto,
                asyncCallBack);
    }

    /**
     * 往期详情
     * @param dto
     * @param callback
     */
    public static void tranDetails(Fragment fragment, TranDTO
            dto, CallBack<TranResult> callback) {
        AsyncCallBack<TranResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,TranResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOldSnatchComm"), dto,
                asyncCallBack);
    }

    /**
     * 夺宝详情
     * @param dto
     * @param callback
     */
    public static void issDetails(Fragment fragment, DetailsDTO
            dto, CallBack<IssDetailsResult> callback) {
        AsyncCallBack<IssDetailsResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,IssDetailsResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnatchComm"), dto,
                asyncCallBack);
    }

    /**
     * 夺宝记录之夺宝详情
     * @param dto
     * @param callback
     */
    public static void recordsDetails(Activity act, RecordIndianaDTO
            dto, CallBack<RecordIndianaResult> callback) {
        AsyncCallBack<RecordIndianaResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,RecordIndianaResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnaRecordByBatCode"), dto,
                asyncCallBack);
    }

    /**
     * 登陆者参与的次数
     * @param fragment
     * @param dto
     * @param callback
     */
    public static void landerIn(Fragment fragment, LanderInDTO
            dto, CallBack<LanderInResult> callback) {
        AsyncCallBack<LanderInResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,LanderInResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetParticipateSnaCount"), dto,
                asyncCallBack);
    }

    /**
     * 根据批次获取50个时间之和
     * @param fragment
     * @param dto
     * @param callback
     */
    public static void calculation(Fragment fragment, CalcutionDTO
            dto, CallBack<CalculationResult> callback) {
        AsyncCallBack<CalculationResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,CalculationResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetCalcByBatCode"), dto,
                asyncCallBack);
    }

    /**
     * 揭晓信息
     * @param fragment
     * @param dto
     * @param callback
     */
    public static void announced(Fragment fragment, AnnouncedDTO
            dto, CallBack<AnnouncedResult> callback) {
        AsyncCallBack<AnnouncedResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,AnnouncedResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetRecordDetailOfXYNums"), dto,
                asyncCallBack);
    }

    /**
     * 首页推荐图片
     * @param dto
     * @param callback
     */
    public static void homeRecommend(Fragment fragment, BaseDTO
            dto, CallBack<HomeRecomendResult> callback) {
        AsyncCallBack<HomeRecomendResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,HomeRecomendResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetSixSnaRecComm"), dto,
                asyncCallBack);
    }

    /**
     * 专业养护
     * @param dto
     * @param callback
     */
    public static void curing(Fragment fragment, CuringDTO
            dto, CallBack<CuringResult> callback) {
        AsyncCallBack<CuringResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,CuringResult.class);
        post( getAbsoluteUrl("/webservice/Parts.asmx/GetSellServer"), dto,
                asyncCallBack);
    }
    /**
     * 夺宝列表
     * @param dto
     * @param callback
     */
    public static void indianaList(Fragment fragment, BaseDTO
            dto, CallBack<IndianaListResult> callback) {
        AsyncCallBack<IndianaListResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,IndianaListResult.class);
        get( getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetAllSnatchComm"), dto,
                asyncCallBack);
    }
    /**
     * 夺宝广告图片
     * @param dto
     * @param callback
     */
    public static void adcertising(Fragment fragment, BaseDTO
            dto, CallBack<AdvertisingResult> callback) {
        AsyncCallBack<AdvertisingResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,AdvertisingResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnaPic"), dto,
                asyncCallBack);
    }

    /**
     * 提交订单之去支付
     * @param act
     * @param dto
     * @param callback
     */
    public static void pay(Activity act, PayDTO
            dto, CallBack<PayResult> callback) {
        AsyncCallBack<PayResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,PayResult.class);
        post(getAbsoluteUrl("/API/WsPlaceOrder.asmx/AddJsonOrderCommServiceApplyApi"), dto,
                asyncCallBack);
    }
    /**
     * 首页专题
     * @param dto
     * @param callback
     */
    public static void homeSpecial(Fragment fragment, BaseDTO
            dto, CallBack<HomeSpecialResult> callback) {
        AsyncCallBack<HomeSpecialResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,HomeSpecialResult.class);
        post(getAbsoluteUrl("/webservice/Parts.asmx/GetSpecial"), dto,
                asyncCallBack);
    }
    /**
     * 首页广告图片
     * @param dto
     * @param callback
     */
    public static void homeAdcer(Fragment fragment, BaseDTO
            dto, CallBack<HomeAdcerResult> callback) {
        AsyncCallBack<HomeAdcerResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,HomeAdcerResult.class);
        post(getAbsoluteUrl("/webservice/Parts.asmx/GetAd"), dto,
                asyncCallBack);
    }
    /**
     * 登录
     * @param dto
     * @param callback
     */
    public static void login(Activity act, LoginDTO
            dto, CallBack<LoginResult> callback) {
        AsyncCallBack<LoginResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,LoginResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }
    /**
     * 我的收入
     * @param dto
     * @param callback
     */
    public static void myIncome(Activity act, BaseDTO
            dto, CallBack<MyIncomeResult> callback) {
        AsyncCallBack<MyIncomeResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,MyIncomeResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResMemberMoneyNotCashAmountApi"), dto,
                asyncCallBack);
    }
    /**
     * 养护订单
     * @param dto
     * @param callback
     */
    public static void CuringOrderList(Fragment fragment, CuringOrderListDTO
            dto, CallBack<CuringOrderListResult> callback) {
        AsyncCallBack<CuringOrderListResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,CuringOrderListResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResMemberLoginApi"), dto,
                asyncCallBack);
    }
    /**
     * 我的收入提现
     * @param dto
     * @param callback
     */
    public static void IncomeWith(Activity act, WithdrawalsDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback,BaseEntity.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResPresentApplicationApi"), dto,
                asyncCallBack);
    }

}
