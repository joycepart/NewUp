package com.qluxstory.qingshe.common.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.entity.BaseEntity;
import com.qluxstory.qingshe.home.dto.AddAddressDTO;
import com.qluxstory.qingshe.home.dto.CuringDTO;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.dto.ProductCommentDTO;
import com.qluxstory.qingshe.home.dto.PurchaseGoodsDTO;
import com.qluxstory.qingshe.home.dto.SKProduct;
import com.qluxstory.qingshe.home.dto.SelectDTO;
import com.qluxstory.qingshe.home.dto.SendDTO;
import com.qluxstory.qingshe.home.dto.TakeDTO;
import com.qluxstory.qingshe.home.dto.TranDTO;
import com.qluxstory.qingshe.home.dto.VouchersDTO;
import com.qluxstory.qingshe.home.entity.AddAddressResult;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.home.entity.CuringResult;
import com.qluxstory.qingshe.home.entity.HomeAdcerResult;
import com.qluxstory.qingshe.home.entity.HomeRecomendResult;
import com.qluxstory.qingshe.home.entity.HomeSpecialResult;
import com.qluxstory.qingshe.home.entity.PaypayResult;
import com.qluxstory.qingshe.home.entity.ProductCommentResult;
import com.qluxstory.qingshe.home.entity.PurchaseGoodsResult;
import com.qluxstory.qingshe.home.entity.PurchaseResult;
import com.qluxstory.qingshe.home.entity.SKResult;
import com.qluxstory.qingshe.home.entity.SelectResult;
import com.qluxstory.qingshe.home.entity.SendResult;
import com.qluxstory.qingshe.home.entity.SplashResult;
import com.qluxstory.qingshe.home.entity.StoreResult;
import com.qluxstory.qingshe.home.entity.TakeResult;
import com.qluxstory.qingshe.home.entity.TimeResult;
import com.qluxstory.qingshe.home.entity.TranResult;
import com.qluxstory.qingshe.home.entity.VouchersResult;
import com.qluxstory.qingshe.information.dto.DeleteDTO;
import com.qluxstory.qingshe.information.dto.InformationDTO;
import com.qluxstory.qingshe.information.entity.InformationResult;
import com.qluxstory.qingshe.issue.dto.AnnouncedDTO;
import com.qluxstory.qingshe.issue.dto.CalcutionDTO;
import com.qluxstory.qingshe.issue.dto.DetailsDTO;
import com.qluxstory.qingshe.issue.dto.IssueIndianaDTO;
import com.qluxstory.qingshe.issue.dto.LanderInDTO;
import com.qluxstory.qingshe.issue.dto.PicDTO;
import com.qluxstory.qingshe.issue.dto.SettlementDTO;
import com.qluxstory.qingshe.issue.dto.ToAnnounceDTO;
import com.qluxstory.qingshe.issue.entity.AdvertisingResult;
import com.qluxstory.qingshe.issue.entity.AnnouncedResult;
import com.qluxstory.qingshe.issue.entity.CalculationResult;
import com.qluxstory.qingshe.issue.entity.IndianaListResult;
import com.qluxstory.qingshe.issue.entity.IssDetailsResult;
import com.qluxstory.qingshe.issue.entity.LanderInResult;
import com.qluxstory.qingshe.issue.entity.PicResult;
import com.qluxstory.qingshe.issue.entity.SettlementResult;
import com.qluxstory.qingshe.issue.entity.ToAnnounceResult;
import com.qluxstory.qingshe.issue.entity.WinningResult;
import com.qluxstory.qingshe.me.dto.CancelDTO;
import com.qluxstory.qingshe.me.dto.CommentDTO;
import com.qluxstory.qingshe.me.dto.ConfirmDTO;
import com.qluxstory.qingshe.me.dto.CuringOrderDetailsDTO;
import com.qluxstory.qingshe.me.dto.CuringOrderListDTO;
import com.qluxstory.qingshe.me.dto.ExchangeVoucherDTO;
import com.qluxstory.qingshe.me.dto.FristDTO;
import com.qluxstory.qingshe.me.dto.IndianaRecordsDTO;
import com.qluxstory.qingshe.me.dto.KdDTO;
import com.qluxstory.qingshe.me.dto.LoginDTO;
import com.qluxstory.qingshe.me.dto.ModifyDTO;
import com.qluxstory.qingshe.me.dto.NumDTO;
import com.qluxstory.qingshe.me.dto.ObtainDTO;
import com.qluxstory.qingshe.me.dto.ObtainIntegralDTO;
import com.qluxstory.qingshe.me.dto.UpDTO;
import com.qluxstory.qingshe.me.dto.UpInformationDTO;
import com.qluxstory.qingshe.me.dto.UserPicDTO;
import com.qluxstory.qingshe.me.dto.WithdrawalsDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderDetailsResult;
import com.qluxstory.qingshe.me.entity.CuringOrderListResult;
import com.qluxstory.qingshe.me.entity.ExchangeVoucherResult;
import com.qluxstory.qingshe.me.entity.IntegralDetailResult;
import com.qluxstory.qingshe.me.entity.IntegralResult;
import com.qluxstory.qingshe.me.entity.LoginResult;
import com.qluxstory.qingshe.me.entity.ModifyResult;
import com.qluxstory.qingshe.me.entity.MyCouponResult;
import com.qluxstory.qingshe.me.entity.MyIncomeResult;
import com.qluxstory.qingshe.me.entity.NumResult;
import com.qluxstory.qingshe.me.entity.ObtainIntegralResult;
import com.qluxstory.qingshe.me.entity.PaymentOrderResult;
import com.qluxstory.qingshe.me.entity.RecordIndianaResult;
import com.qluxstory.qingshe.me.entity.RecordsResult;
import com.qluxstory.qingshe.me.entity.SignResult;
import com.qluxstory.qingshe.me.entity.TransactionResult;
import com.qluxstory.qingshe.me.entity.UpDataResult;
import com.qluxstory.qingshe.me.entity.UserPicResult;
import com.qluxstory.qingshe.special.entity.HotTopResult;

public class CommonApiClient extends BaseApiClient {

    /**
     * 获取短信验证码
     * @param dto
     * @param callback
     */
    public static void getVerify(Activity act, ObtainDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResSendSmsCodeApi"), dto,
                asyncCallBack);
    }

    /**
     * 获取启动页图片
     * @param dto
     * @param callback
     */
    public static void getImgs(Activity act, BaseDTO
            dto, CallBack<SplashResult> callback) {
        AsyncCallBack<SplashResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, SplashResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResBrwPicHrefApi"), dto,
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
     * 兑换优惠劵
     * @param dto
     * @param callback
     */
    public static void exchangeVoucher(Fragment fragment, ExchangeVoucherDTO
            dto, CallBack<ExchangeVoucherResult> callback) {
        AsyncCallBack<ExchangeVoucherResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback, ExchangeVoucherResult.class);
        post(getAbsoluteUrl("/API/WsMemberCoupon.asmx/ResAddMemberConsigeeApi"), dto,
                asyncCallBack);
    }

    /**
     * 配送地址
     * @param dto
     * @param callback
     */
    public static void send(Fragment fragment, SendDTO
            dto, CallBack<SendResult> callback) {
        AsyncCallBack<SendResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback, SendResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetAddressByType"), dto,
                asyncCallBack);
    }

    /**
     * 门店地址
     * @param dto
     * @param callback
     */
    public static void store(Fragment fragment, BaseDTO
            dto, CallBack<StoreResult> callback) {
        AsyncCallBack<StoreResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback, StoreResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetAddressFromInfo"), dto,
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
     * 积分列表
     * @param dto
     * @param callback
     */
    public static void integralList(Activity act, BaseDTO
            dto, CallBack<IntegralDetailResult> callback) {
        AsyncCallBack<IntegralDetailResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, IntegralDetailResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResPointsIntegralListApi"), dto,
                asyncCallBack);
    }

    /**
     * 代金劵
     * @param dto
     * @param callback
     */
    public static void vouchers(Activity act, VouchersDTO
            dto, CallBack<VouchersResult> callback) {
        AsyncCallBack<VouchersResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, VouchersResult.class);
        post(getAbsoluteUrl("/API/WsMemberCoupon.asmx/ResJsonVerMemberCouponApi"), dto,
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
    public static void getNews(Fragment fragment, InformationDTO
            dto, CallBack<InformationResult> callback) {
        AsyncCallBack<InformationResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,InformationResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetAllNews"), dto,
                asyncCallBack);
    }

    /**
     * 删除新闻
     * @param dto
     * @param callback
     */
    public static void delete(Fragment fragment, DeleteDTO
            dto, CallBack<InformationResult> callback) {
        AsyncCallBack<InformationResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,InformationResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/DeleteAllNews"), dto,
                asyncCallBack);
    }

    /**
     * 阅读新闻
     * @param dto
     * @param callback
     */
    public static void read(Fragment fragment, DeleteDTO
            dto, CallBack<InformationResult> callback) {
        AsyncCallBack<InformationResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,InformationResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/ReadAllNews"), dto,
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
    public static void records(Fragment fragment, IndianaRecordsDTO
            dto, CallBack<RecordsResult> callback) {
        AsyncCallBack<RecordsResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,RecordsResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOrderRecord"), dto,
                asyncCallBack);
    }

    /**
     * 养护之商品详情之评论
     * @param dto
     * @param callback
     */
    public static void productComment(Fragment fragment, ProductCommentDTO
            dto, CallBack<ProductCommentResult> callback) {
        AsyncCallBack<ProductCommentResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,ProductCommentResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetAllReview"), dto,
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
     * 往期揭晓之个人
     * @param dto
     * @param callback
     */
    public static void announce(Activity act, ToAnnounceDTO
            dto, CallBack<ToAnnounceResult> callback) {
        AsyncCallBack<ToAnnounceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,ToAnnounceResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetOldTimeSnatch"), dto,
                asyncCallBack);
    }

    /**
     * 更新收货人信息
     * @param dto
     * @param callback
     */
    public static void upData(Activity act, UpDTO
            dto, CallBack<UpDataResult> callback) {
        AsyncCallBack<UpDataResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,UpDataResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetConAndOrderInfo"), dto,
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
    public static void recordsDetails(Activity act, IssueIndianaDTO
            dto, CallBack<RecordIndianaResult> callback) {
        AsyncCallBack<RecordIndianaResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,RecordIndianaResult.class);
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetSnaRecordByBatCode"), dto,
                asyncCallBack);
    }



    /**
     * 参与信息
     * @param dto
     * @param callback
     */
    public static void num(Activity act, NumDTO
            dto, CallBack<NumResult> callback) {
        AsyncCallBack<NumResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,NumResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetRecordDetailOfSnaNums"), dto,
                asyncCallBack);
    }

    /**
     * 确认收货地址
     * @param dto
     * @param callback
     */
    public static void comfirm(Activity act, ConfirmDTO
            dto, CallBack<NumResult> callback) {
        AsyncCallBack<NumResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,NumResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/ReceiveConsigneeCode"), dto,
                asyncCallBack);
    }

    /**
     * 更新中奖人信息
     * @param dto
     * @param callback
     */
    public static void upInformation(Activity act, UpInformationDTO
            dto, CallBack<NumResult> callback) {
        AsyncCallBack<NumResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,NumResult.class);
        post(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/UpdateRecState"), dto,
                asyncCallBack);
    }

    /**
     * 查询收货地址
     * @param dto
     * @param callback
     */
    public static void selcet(Activity act, SelectDTO
            dto, CallBack<SelectResult> callback) {
        AsyncCallBack<SelectResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,SelectResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResDtByJsonBrwMemberConsigeeApi"), dto,
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
        post(getAbsoluteUrl("/webservice/Parts.asmx/GetSellServer"), dto,
                asyncCallBack);
    }

    /**
     * 添加收货地址(新增地址)
     * @param dto
     * @param callback
     */
    public static void addAdress(Activity act, AddAddressDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,AddAddressResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResAddMemberConsigeeApi"), dto,
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
        get(getAbsoluteUrl("/webservice/Snatch_WebService.asmx/GetAllSnatchComm"), dto,
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
            dto, CallBack<PaypayResult> callback) {
        AsyncCallBack<PaypayResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,PaypayResult.class);
        post(getAbsoluteUrl("/API/WsPlaceOrder.asmx/AddJsonOrderCommServiceApplyApi"), dto,
                asyncCallBack);
    }

    /**
     * 提交订单之会员余额
     * @param act
     * @param dto
     * @param callback
     */
    public static void balance(Activity act, BaseDTO
            dto, CallBack<BalanceResult> callback) {
        AsyncCallBack<BalanceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,BalanceResult.class);
        post(getAbsoluteUrl("/API/WsMember.asmx/ResMemberCashAmountMoneyApi"), dto,
                asyncCallBack);
    }

    /**
     * 发表评论
     * @param act
     * @param dto
     * @param callback
     */
    public static void comment(Activity act, CommentDTO
            dto, CallBack<BalanceResult> callback) {
        AsyncCallBack<BalanceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,BalanceResult.class);
        post(getAbsoluteUrl("/webservice/Parts.asmx/InsertReview"), dto,
                asyncCallBack);
    }



    /**
     * 获取后十五天
     * @param act
     * @param dto
     * @param callback
     */
    public static void time(Activity act, BaseDTO
            dto, CallBack<TimeResult> callback) {
        AsyncCallBack<TimeResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,TimeResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetDateTime"), dto,
                asyncCallBack);
    }

    /**
     * 提交订单之取送方式
     * @param act
     * @param dto
     * @param callback
     */
    public static void take(Activity act, TakeDTO
            dto, CallBack<TakeResult> callback) {
        AsyncCallBack<TakeResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,TakeResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetDistribution"), dto,
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
     * 闪购广告图片
     * @param dto
     * @param callback
     */
    public static void purchase(Fragment fragment, BaseDTO
            dto, CallBack<PurchaseResult> callback) {
        AsyncCallBack<PurchaseResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,PurchaseResult.class);
        post(getAbsoluteUrl("/webservice/Parts.asmx/GetSalesPic"), dto,
                asyncCallBack);
    }

    /**
     * 闪购商品详情
     * @param fragment
     * @param framgent
     * @param act
     * @param dto
     * @param callback
     */
    public static void purProduct(Activity act, SKProduct
            dto, CallBack<SKResult> callback) {
        AsyncCallBack<SKResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,SKResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetDetailSales"), dto,
                asyncCallBack);
    }

    /**
     * 闪购商品
     * @param dto
     * @param callback
     */
    public static void purchaseGoods(Fragment fragment, PurchaseGoodsDTO
            dto, CallBack<PurchaseGoodsResult> callback) {
        AsyncCallBack<PurchaseGoodsResult> asyncCallBack = new AsyncCallBack<>(
                fragment, callback,PurchaseGoodsResult.class);
        get(getAbsoluteUrl("/webservice/Parts.asmx/GetSalesByJoin"), dto,
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
     * 首次定位城市位置
     * @param dto
     * @param callback
     */
    public static void frist(Activity act, FristDTO
            dto, CallBack<LoginResult> callback) {
        AsyncCallBack<LoginResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,LoginResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResEdtMemberFirstAddressApi"), dto,
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
     * 个人积分
     * @param dto
     * @param callback
     */
    public static void personal(Activity act, BaseDTO
            dto, CallBack<IntegralResult> callback) {
        AsyncCallBack<IntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,IntegralResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResBrwPointsIntegralApi"), dto,
                asyncCallBack);
    }

    /**
     * 签到天数
     * @param dto
     * @param callback
     */
    public static void sign(Activity act, BaseDTO
            dto, CallBack<SignResult> callback) {
        AsyncCallBack<SignResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,SignResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResBrwIntegralByShowApi"), dto,
                asyncCallBack);
    }

    /**
     * 签到获取积分
     * @param dto
     * @param callback
     */
    public static void obtainIntegral(Activity act, ObtainIntegralDTO
            dto, CallBack<ObtainIntegralResult> callback) {
        AsyncCallBack<ObtainIntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,ObtainIntegralResult.class);
        post( getAbsoluteUrl("/API/WsMember.asmx/ResAddPointsIntegralApi"), dto,
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
        post( getAbsoluteUrl("/API/WsPlaceOrder.asmx/ResDtByJsonOrderCuringApi"), dto,
                asyncCallBack);
    }

    /**
     * 取消订单
     * @param activity
     * @param dto
     * @param callback
     */
    public static void cancel(Activity activity, CancelDTO
            dto, CallBack<CuringOrderListResult> callback) {
        AsyncCallBack<CuringOrderListResult> asyncCallBack = new AsyncCallBack<>(
                activity, callback,CuringOrderListResult.class);
        post( getAbsoluteUrl("/API/WsPlaceOrder.asmx/ResEdtOrderJsonCancelPaymentApi"), dto,
                asyncCallBack);
    }

    /**
     * 养护订单详情
     * @param dto
     * @param callback
     */
    public static void curingOrderDetails(Activity act, CuringOrderDetailsDTO
            dto, CallBack<CuringOrderDetailsResult> callback) {
        AsyncCallBack<CuringOrderDetailsResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,CuringOrderDetailsResult.class);
        post( getAbsoluteUrl("/API/WsPlaceOrder.asmx/ResDtByJsonOrderCuringShowOneApi"), dto,
                asyncCallBack);
    }

    /**
     * 快递信息
     * @param dto
     * @param callback
     */
    public static void kd(Activity act, KdDTO
            dto, CallBack<CuringOrderDetailsResult> callback) {
        AsyncCallBack<CuringOrderDetailsResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,CuringOrderDetailsResult.class);
        post( getAbsoluteUrl("/API/WsPlaceOrder.asmx/ResAddExpressDeliverOrderJsonApi"), dto,
                asyncCallBack);
    }

    /**
     * 养护之支付
     * @param dto
     * @param callback
     */
    public static void curingOrderPays(Activity act, CuringOrderDetailsDTO
            dto, CallBack<PaymentOrderResult> callback) {
        AsyncCallBack<PaymentOrderResult> asyncCallBack = new AsyncCallBack<>(
                act, callback,PaymentOrderResult.class);
        post( getAbsoluteUrl("/API/WsPlaceOrder.asmx/ResAddTwoOrderJsonOnlinePaymentApi"), dto,
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
