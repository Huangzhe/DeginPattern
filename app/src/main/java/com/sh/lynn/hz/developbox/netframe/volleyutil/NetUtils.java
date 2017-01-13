package com.sh.lynn.hz.developbox.netframe.volleyutil;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.base.Config;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.bean.MBook;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.bean.Pay2MerchantEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.bean.PayMerchantRequestBean;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.bean.Tran_param_ApplyInstalment_Submit;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.bean.person.BaseMeta;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.bean.person.ValuePair;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.common.ClientUserHelper;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.constact.Constant;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AccountBillHistoryEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AccountBillListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AccountQueryEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AccountRepayEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AccountTrialRepayEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AcquisitionAmountEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ApplyAbandonEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ApplyInstallPlanEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ApplyInstallSubmitEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ApplyListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.AutoLoginEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.BankListNewEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.BaseEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.CashLoanEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.CashLoanSubmitEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.CategoryEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ChangeLoginPWDEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ChangeTradePWDEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.CustMessageEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.CustomListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.DoUnbindCardEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.DownloadEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.GetAgreementEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.GetVersionEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.IdentityOCREvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.JLXCollectonEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.JLXCreditResouctListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.JLXResetEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.JLXSendCodeEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.JLXSkipEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.LogEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.LoginEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.MsgForwordEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.MsgListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetAcceptLoanEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetApplyLoansEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetApplyTrustEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetEProtocolEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetQueryApplySupplyEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetQueryFaverListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetQueryOrderPatchEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetQuerySubOrderEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetSubmitCustInputEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetSubmitOrderPatchEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetSubmitPersonInfoEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.NetValidateRealNameEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.OrderListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.OsmPatchBusinessCardEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.QueryBankListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.QuickPayConfirmEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.QuickPaySMSEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.QuickPaymentConfirmEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.QuickPaymentSMSEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.RegisterEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ResetLoginPWDEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ResetTradePWDEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.SessionTimeOutEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.SignPayConfirmEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.SignPaySMSEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.SmsEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.SplashImageEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.TopMsgListEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.UnbindCardSendMsgEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.UpdateAutoRepayStatusEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.UploadBookEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.UploadFileEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ZMXYEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.event.ZMXYParamEvent;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.utils.Des3;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.utils.DialogUtil;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.utils.StringUtil;
import com.sh.yirisheng.yijiaren.yrsheng_yijiaren.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 * <p/>
 * 网络请求处理
 */
public class NetUtils {
    // private final static String NET_QueryAppVersion = "/cloud/queryAppVersion.do";// 查询版本号
    private final static String NET_QueryAppVersion = "/yrsapi/tools/checkUpgrade";// 查询版本号


    private final static String NET_Login = "/yrsapi/user/login";// 登录系统
    private final static String NET_appPictures = "/yrsapi/tools/appPictures";// 过度界面

    private final static String NET_Register = "/yrsapi/user/register";// 注册系统
    private final static String NET_Change_Login_pwd = "/yrsapi/user/change_login_pwd";// 修改登录密码系统
    private final static String NET_Reset_Login_pwd = "/yrsapi/user/reset_login_pwd";// 重置登录密码系统
    private final static String NET_Change_trade_pwd = "/yrsapi/user/change_trade_pwd";// 修改交易密码系统
    private final static String NET_Reset_trade_pwd = "/yrsapi/user/reset_trade_pwd";// 重置交易密码系统
    private final static String NET_Cust_Message = "/yrsapi/user/cust_message";// 查询个人资料系统
    private final static String NET_Sms = "/yrsapi/tools/send_verification";//发送验证码

    private final static String NET_AccountQuery = "/yrsapi/bill/accountquery";// 查询个人账户信息-账户余额
    private final static String NET_AccountBillList = "/yrsapi/bill/query_list";// 获取账单列表
    private final static String NET_AccountBillHistory = "/yrsapi/bill/rep_hisquery";// 查询历史还款记录
    private final static String NET_AccountRepay = "/yrsapi/bill/normal_repay";// 主动还款
    private final static String NET_AccountTrialRepay = "/yrsapi/bill/trial_repay";//获取还款多期试算信息

    private final static String NET_ApplyList = "/yrsapi/apply/applpro";// 获取申请列表
    private final static String NET_ApplyAbandon = "/yrsapi/apply/abandon";// 放弃申请
    private final static String NET_AcceptLoan = "/yrsapi/apply/loans_confirm";//接受借款
    private final static String NET_AuthReal = "/yrsapi/tools/realname_validate";//实名认证
    private final static String NET_ApplyInstallPlan = "/yrsapi/apply/query_per_install_plan";//个人分期申请查询还款计划
    private final static String NET_ApplyInstallSubmit = "/yrsapi/apply/per_install_application";//个人分期申请提交
    private final static String NET_ApplyTrustProtocol = "/yrsapi/apply/creditConfirmNew";//授信签约
    private final static String NET_ApplyLoansProtocol = "/yrsapi/apply/loansConfirmNew";//请款签约
    private final static String NET_Protocol = "/yrsapi/apply/createLoanContract";//合同查询
    private final static String NET_SubOrder = "/yrsapi/order/findSubInfos ";//子订单查询
    private final static String NET_GetORDERLIST = "/yrsapi/order/search_personal_order";// 订单列表
    private final static String NET_Post_CustomList = "/yrsapi/shop/info";// 商户列表
    private final static String NET_Post_CustomList_group_info = "/yrsapi/shop/group_info";// 商户列表产品条件（公司）
    private final static String NET_Post_CustomList_store_info = "/yrsapi/shop/store_info";// 商户列表产品条件（门店）
    private final static String NET_Post_CustomList_merchant_info = "/yrsapi/shop/merchant_info";// 商户列表产品条件（商户）
    private final static String NET_Post_CustomList_product_info = "/yrsapi/shop/product_info";// 商户列表产品条件（产品）
    private final static String NET_Post_CustomList_fuzzy_info = "/yrsapi/shop/fuzzy_info";//商户列表查询
    private final static String NET_Post_CustomList_pos_info = "/yrsapi/shop/pos_info";//pos商户产品查询条件
    private final static String NET_Post_Category_list = "/yrsapi/tools/category_list";//行业类别

    private final static String NET_Post_CashLoanList = "/yrsapi/bill/cash_loan";//现金贷
    private final static String NET_Post_CashLoanSub = "/yrsapi/order/pay_to_merchant";//现金贷借款提交申请

    private final static String NET_Pay2Merchant = "/yrsapi/order/pay_to_merchant";// 付款给商户

    private final static String NET_SubmitPersonInfo = "/yrsapi/user/";//提交个人资料 通用接口 + id
//    private final static String NET_SubmitPersonBaseInfo = "/yrsapi/user/customer_customer_base_info_edit";//提交个人资料
//    private final static String NET_SubmitAddressInfo = "/yrsapi/user/customer_com_address_edit";//提交住址资料
//    private final static String NET_SubmitCardInfo = "/yrsapi/user/customer_com_comcar_edit";//提交车辆资料
//    private final static String NET_SubmitContactInfo = "/yrsapi/user/customer_com_contact_edit";//提交联系人资料
//    private final static String NET_SubmitCompanyInfo = "/yrsapi/user/customer_com_inf_edit";//提交公司资料
//    private final static String NET_SubmitEduInfo = "/yrsapi/user/customer_com_education_edit";//提交教育资料
//    private final static String NET_SubmitDegreeInfo = "/yrsapi/user/customer_com_education_proof_edit";//提交学历资料
//    private final static String NET_SubmitIncomingInfo = "/yrsapi/user/customer_com_income_proof_edit";//提交收入资料
//    private final static String NET_SubmitHouseInfo = "/yrsapi/user/customer_com_premises_permit_edit";//提交房产资料
//    private final static String NET_SubmitCardPaperInfo = "/yrsapi/user/customer_com_vehicle_permit_edit";//提交车辆正面资料
//    private final static String NET_SubmitOtherInfo = "/yrsapi/user/customer_com_other_proof_assets_edit";//提其它资料


    private final static String NET_SubmitCustInput = "/yrsapi/apply/upload_cust_input";//上传客户补充资料
    private final static String NET_SubmitOrderPatch = "/yrsapi/apply/upload_patch";//上传客户补证资料
    private final static String NET_QueryOrderPatch = "/yrsapi/apply/order_patch";//查询客户补证资料
    private final static String NET_UploadMobileBook = "/yrsapi/tools/upload_mobile_book";//上传电话薄(电话通讯录)
    private final static String NET_GetCreditResourceList = "/yrsapi/apply/cimjxl_jxlauth";//聚立信资源列表

    private final static String NET_GetJLXPhoneCode = "/yrsapi/apply/cimjxl_jxl_reset_pwd";//聚立信短信验证码/重置密码
    private final static String NET_UploadFile = "/yrsapi/user/file_upload";//上传文件
    private final static String NET_DownloadFile = "/yrsapi/user/file_download";//下载文件

    private final static String NET_Commit_JLXCollection = "/yrsapi/apply/cimjxl_jxlCollect";//聚立信采集提交
    private final static String NET_Skip_JLXStep = "/yrsapi/apply/jxl_skip";//聚立信采集提交

    private final static String NET_ZMXY = "/yrsapi/apply/zm_generate_params_and_sign";//芝麻信用
    private final static String NET_ZMXY_PARAM = "/yrsapi/apply/openId";//芝麻信用验证Params


    private final static String NET_QUERY_BANK_LIST = "/yrsapi/tools/QueryBankList";//查询支持银行卡列表

    /**
     * 签约代扣绑定银行卡
     */
    private final static String NET_Sign_Pay_SMS = "/yrsapi/tools/personBindCard";//签约代扣短信
    private final static String NET_Sign_Pay_Confirm = "/yrsapi/tools/personBindCardConfirm";//签约代扣确认
    /**
     * 签约快捷支付绑定银行卡
     */
    private final static String NET_Quick_Pay_SMS = "/yrsapi/tools/personBindCard4QuickPay";//签约快捷支付短信
    private final static String NET_Quick_Pay_Confirm = "/yrsapi/tools/personBindCardConfirm4QuickPay";//签约快捷支付确认
    /**
     * 快捷支付 ：快捷支付确认接口改回主动还款接口
     */
    private final static String NET_Quick_Payment_SMS = "/yrsapi/tools/sendSms4QuickPay";//快捷支付短信
    private final static String NET_Quick_Payment_Confirm = "/yrsapi/tools/quickpay";//支付确认


    private final static String NET_Get_Agreement = "/yrsapi/tools/getAgreement";//获取电子协议
    private final static String NET_IdentityOCR = "/yrsapi/tools/identityOCR";//获取电子协议

    private final static String NET_osmPatchBusinessCard = "/yrsapi/tools/osmPatchBusinessCard";//名片OCR信息上传

    private final static String NET_NotificationInfo = "/yrsapi/tools/msgListView";//单个公告信息详情

    private final static String NET_NotificationList = "/yrsapi/tools/msgList";//公告消息列表
    private final static String NET_FaverList = "/yrsapi/tools/favoriteList";//收藏列表

    /**
     * 侧滑获取金额信息
     */
    private final static String NET_AcquisitionAmount = "/yrsapi/tools/acquisitionAmount";

    private final static String Net_log = "/yrsapi/tools/appLogs";//日志信息

    private final static String Net_MsgForward = "/yrsapi/tools/msgForward";//文章转发数记录

    /**
     * 解除绑卡发送短信
     */
    private final static String UnBindCardSendMsg = "/yrsapi/tools/unBindCardSendSms";//解除绑卡发送短信
    private final static String DoUnBindCard = "/yrsapi/tools/unBindCardConfirm";//解除绑卡请求
    private final static String UpdateAutoRepayStatus = "/yrsapi/tools/updateAutoRepayStatus";//更新自动还款标志
    private final static String QueryBankListNew = "/yrsapi/tools/QueryBankListNew";//查询银行列表新
    private final static String NET_Pay2MerchantNew = "/yrsapi/order/payToMerchantNew";// 付款给商户新接口


    static class RequestBuilder {
        JSONObject param;
        JSONObject base;

        public RequestBuilder() {
            param = new JSONObject();
            base = new JSONObject();
        }

        public static RequestBuilder newInstance() {
            return new RequestBuilder();
        }

        /**
         * 设置内层参数
         */
        public RequestBuilder put(String key, Object value) {
            try {
                param.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * 设置外层参数
         */
        public RequestBuilder putBase(String key, Object value) {
            try {
                base.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * 设置基本信息
         */
        private void setBase(JSONObject json) throws JSONException {
            json.put("custId", ClientUserHelper.getCustId());//id
            json.put("userMP", ClientUserHelper.getCustPhone());//手机号
            json.put("mpIemi", ClientUserHelper.getUniquePsuedoID());//设备唯一号
            json.put("clientGps", Config.gps);//gps
            //其它信息
            json.put("nonce_str", StringUtil.nonce_str());
            json.put("pos_type", "01");
            json.put("tran_param", param);
        }

        public JSONObject createJson() {
            //JSONObject json = base;
            try {
                base.put("sessionID", ClientUserHelper.getSessionID());
                setBase(base);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
            return base;
        }

        public JSONObject createNLJson() {
            JSONObject json = base;
            try {
                setBase(json);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
            return json;
        }


    }

    /**
     * 样例
     */
    public static void sample(String param1, String param2) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("p1", param1);
        param.put("p2", param2);
        RequestHelper.getInstance().postJson("http://url"/*地址*/, param.createNLJson(), null/*自定义事件*/, "tag"/*请求标记*/, new DefaultRetryPolicy());
    }

    /**
     * 获取版本信息
     *
     * @param versionCode 版本号
     */
    public static void getVersion(int versionCode) {
//        JSONObject json = new JSONObject();
//        try {
//            json.put("VER", "66");
//            json.put("SOFTTYPE", "ANDROID");
//            json.put("ORGVERSION", versionCode + "");
//            json.put("CLIENTTYPE", "CREAM");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        RequestHelper.getInstance().authRequest(Config.BASEURL + NET_QueryAppVersion, json, new GetVersionEvent(), "getVersion",
//                new DefaultRetryPolicy(30 * 1000, 1, 1.0f));

        RequestBuilder param = RequestBuilder.newInstance();
        try {
            RequestHelper.getInstance().authRequest(RequestHelper.HTTP_POST, Config.EURL + NET_QueryAppVersion, param.createNLJson().put("app_version", versionCode),
                    new GetVersionEvent(), "getVersion", new DefaultRetryPolicy(30 * 1000, 1, 1.0f));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void splashImage() {
        RequestBuilder param = RequestBuilder.newInstance();
        RequestHelper.getInstance().Request(RequestHelper.HTTP_POST, Config.EURL + NET_appPictures, param.createNLJson(), new SplashImageEvent(), "splash", new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
    }

    /**
     * 登录
     *
     * @param name 手机号
     * @param pwd  密码
     */
    public static void login(String name, String pwd) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userName", name);
        param.put("password", pwd);
        param.putBase("msgClientId", Constant.GT_CID);
        param.putBase("loginDeviceId", Constant.loginDeviceId);
        JSONObject temp = param.createNLJson();
        try {
            temp.put("userMP", name);//手机号
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Login, temp,
                new LoginEvent(), "login");
    }

    /**
     * 自动登录
     *
     * @param name 手机号
     * @param pwd  密码
     */
    public static void autoLogin(String name, String pwd) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userName", name);
        param.put("password", pwd);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Login, param.createNLJson(),
                new AutoLoginEvent(), "autoLogin");
    }

    /**
     * 注册
     *
     * @param name     手机号
     * @param pwd      密码
     * @param authcode 短信验证码
     */
    public static void register(String name, String pwd, String authcode, String sessionID) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userMP", name);
        param.put("password", pwd);
        param.put("authcode", authcode);
        param.put("roleName", "APP_PERSONAL");
        param.putBase("sessionID", sessionID);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Register, param.createNLJson(),
                new RegisterEvent(), "register");
    }

    /**
     * 修改登录密码
     *
     * @param password  原登录密码
     * @param nPassword 新登录密码
     */
    public static void changeLoginpwd(String password, String nPassword, String rePassword) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userMP", ClientUserHelper.getCustPhone());
        param.put("password", password);
        param.put("nPassword", nPassword);
        param.put("nPasswordCfm", rePassword);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Change_Login_pwd, param.createJson(),
                new ChangeLoginPWDEvent(), "changeLoginpwd");
    }

    /**
     * 重置登录密码
     *
     * @param userMP    手机号码
     * @param authCode  短信验证码
     * @param nPassword 新登录密码
     */
    public static void resetLoginpwd(String userMP, String authCode, String nPassword, String rePassword, String sessionID) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userMP", userMP);
        param.put("authcode", authCode);
        param.put("nPassword", nPassword);
        param.put("nPasswordCfm", rePassword);
        param.putBase("sessionID", sessionID);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Reset_Login_pwd, param.createNLJson(),
                new ResetLoginPWDEvent(), "resetLoginpwd");
    }

    /**
     * 修改交易密码
     *
     * @param userMP    手机号码
     * @param authCode  短信验证码
     * @param password  原交易密码
     * @param nPassword 新交易密码
     */
    public static void changeTradepwd(String userMP, String authCode, String password, String nPassword, String rePassword) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userMP", userMP);
        param.put("authcode", authCode);
        param.put("password", password);
        param.put("nPassword", nPassword);
        param.put("nPasswordCfm", rePassword);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Change_trade_pwd, param.createJson(),
                new ChangeTradePWDEvent(), "changeTradepwd");
    }

    /**
     * 重置交易密码
     *
     * @param userMP    手机号码
     * @param authCode  短信验证码
     * @param nPassword 新交易密码
     */
    public static void resetTradepwd(String userMP, String authCode, String nPassword, String rePassword) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userMP", userMP);
        param.put("authcode", authCode);
        param.put("nPassword", nPassword);
        param.put("nPasswordCfm", rePassword);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Reset_trade_pwd, param.createJson(),
                new ResetTradePWDEvent(), "resetTradepwd");
    }

    /**
     * 查询个人资料
     *
     * @param custId 客户编号
     */
    public static void custMessage(int custId) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Cust_Message, param.createJson(),
                new CustMessageEvent(), "custMessage");
    }

    /**
     * 发送手机验证码
     *
     * @param phone 手机号
     */
    public static void sendSms(String phone) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userMP", phone);
        if (ClientUserHelper.isLogin()) {
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Sms, param.createJson(),
                    new SmsEvent(), "sms");
        } else {
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Sms, param.createNLJson(),
                    new SmsEvent(), "sms");
        }
    }

    /**
     * 获取还款多期试算信息
     *
     * @param acctSeq    贷款账号
     * @param subOrderId 消费子订单订单号
     * @param period     还款期数
     * @param txnAmt     还款金额
     *                   //rate（标准贷款手续费率）、productSeq（产品序号）、discount（贴息）；
     */
    public static void accountTrialRepay(String acctSeq, int subOrderId, int period, double txnAmt) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("acctSeq", acctSeq);
        param.put("subOrderId", subOrderId);
        param.put("period", period);
        param.put("txnAmt", txnAmt);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AccountTrialRepay, param.createJson(),
                new AccountTrialRepayEvent(), "applyTrialRepay");
    }

    /**
     * ---------------------------------------- 账户类 --------------------------------------------------
     */


    /**
     * 查询个人账户信息-账户余额
     *
     * @param custId 商户Id
     */
    public static void accountQuery(int custId) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        param.put("custType", "C"); //客户类型 固定值：C
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AccountQuery, param.createJson(),
                new AccountQueryEvent(), "accountQuery");
    }

    /**
     * 获取账单列表 /NET_AccountBillHisquery
     *
     * @param custId 商户Id
     */
    public static void accountBillList(int custId, int beginNumber, int endNumber) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        param.put("beginNumber", beginNumber);
        param.put("endNumber", endNumber);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AccountBillList, param.createJson(),
                new AccountBillListEvent(), "accountBillList");
    }

    /**
     * 查询历史还款记录
     *
     * @param custId 商户Id
     */
    public static void accountBillHistory(int custId, int beginNumber, int endNumber) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        param.put("beginNumber", beginNumber);
        param.put("endNumber", endNumber);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AccountBillHistory, param.createJson(),
                new AccountBillHistoryEvent(), "accountBillHistory");
    }

    /**
     * 主动还款
     *
     * @param custId    客户号
     * @param txnAmt    还款金额
     * @param password  支付密码
     * @param payMethod 使用代扣或者快捷
     * @param cardNo    卡号
     * @param acctSeq   贷款子账号
     * @param orderId   消费子订单编号
     */
    public static void accountRepay(int custId,
                                    double txnAmt,
                                    String password,
                                    String payMethod,
                                    String cardNo,
                                    String acctSeq,
                                    int orderId) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        param.put("txnAmt", txnAmt);
        param.put("password", password);
        param.put("payMethod", payMethod);
        param.put("cardNo", cardNo);
        param.put("acctSeq", acctSeq);
        param.put("orderId", orderId);
        RequestHelper.getInstance().repayRequest(RequestHelper.HTTP_POST, Config.EURL + NET_AccountRepay, param.createJson(),
                new AccountRepayEvent(), "accountRepay");
    }


    /** ---------------------------------------- 申请类 --------------------------------------------------*/

    /**
     * 获取申请列表
     *
     * @param custId 客户号
     */
    public static void applyList(int custId, int beginNumber, int endNumber) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custUid", custId);
        param.put("beginNumber", beginNumber);
        param.put("endNumber", endNumber);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyList, param.createJson(),
                new ApplyListEvent(), "applyList");
    }

    /**
     * 接受放款
     *
     * @param orderSeq 申请编号
     * @param signFile 签名文件
     * @param fileName 文件名
     * @param attachId 附件编号
     */
    public static void acceptLoan(int orderSeq, String signFile, String fileName, int attachId) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderSeq", orderSeq);
        param.put("signFile", signFile);
        param.put("fileName", fileName);
        param.put("attachId", attachId);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AcceptLoan, param.createJson(),
                new NetAcceptLoanEvent(), "acceptLoan");
    }

    /**
     * 放弃申请
     *
     * @param seq 申请编号
     */
    public static void applyAbandon(int seq) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("seq", seq);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyAbandon, param.createJson(),
                new ApplyAbandonEvent(), "applyAbandon");
    }


    /**
     * 个人分期申请查询还款计划
     *
     * @param txnAmt                   申请金额
     * @param orderProductInstanceCode 订单号（扫描分期二维码）
     * @param rate                     标准手续费率
     * @param period                   选择期数
     * @param payMethod                还款方式
     * @param benchmarkY               基准年利率
     * @param productSeq               产品序号
     */
    public static void applyInstallPlan(double txnAmt, String orderProductInstanceCode,
                                        String rate, int period, String payMethod, String benchmarkY, String productSeq) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userId", ClientUserHelper.getCustId());
        param.put("txnAmt", txnAmt);
        param.put("orderProductInstanceCode", orderProductInstanceCode);
        param.put("rate", rate);
        param.put("period", period);
        param.put("payMethod", payMethod);
        param.put("benchmarkY", benchmarkY);
        param.put("productSeq", productSeq);

        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyInstallPlan, param.createJson(),
                new ApplyInstallPlanEvent(), "applyInstallPlan");
    }

    /**
     * 个人分期申请提交还款
     *
     * @param
     * @param tranNo       POS订单号
     * @param posTranPayId POS支付序号
     * @param userMP       手机号码
     * @param tran_param   业务参数集
     */
    public static void applyInstallSubmit(String tranNo, String posTranPayId, String userMP, Tran_param_ApplyInstalment_Submit tran_param) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("userId", tran_param.userId);
        param.put("merchantId", tran_param.merchantId);
        param.put("storeId", tran_param.storeId);
        param.put("txnAmt", tran_param.txnAmt);
        param.put("orderProductInstanceCode", tran_param.orderProductInstanceCode);
        param.put("rate", tran_param.rate);
        param.put("period", tran_param.period);
        param.put("payMethod", tran_param.payMethod);
        param.put("productSeq", tran_param.productSeq);
        param.put("salePhone", tran_param.salePhone);
        param.put("vehiclePriceAmt", tran_param.vehiclePriceAmt);
        param.put("vehicle1stPayAmt", tran_param.vehicle1stPayAmt);


        try {
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyInstallSubmit, param.createJson().put("tranNo", tranNo).put("posTranPayId", posTranPayId).put("userMP", userMP),
                    new ApplyInstallSubmitEvent(), "applyInstallSubmit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /** ---------------------------------------- 订单类 --------------------------------------------------*/
    /**
     * 获取订单列表
     */
    public static void getOrderList(String achartNo, int beginNum, int endNum) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", achartNo);
        param.put("beginNumber", beginNum);
        param.put("endNumber", endNum);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_GetORDERLIST, param.createJson(),
                new OrderListEvent(), "OrderFragment");
    }

    /**
     * 付款给商户
     */
    public static void pay2Merchant(PayMerchantRequestBean request) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", request.cust_id);
        param.put("txnAmt", request.txnAmt);
        param.put("password", request.passWord);
        param.put("orderId", request.orderId);
        param.put("merchantId", request.merchantId);

        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Pay2Merchant, param.createJson(),
                new Pay2MerchantEvent(), "MyOrderActivity");
    }

    /**
     * 付款给商户
     */
    public static void pay2MerchantNew(PayMerchantRequestBean request) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", request.cust_id);
        param.put("txnAmt", request.txnAmt);
        param.put("password", request.passWord);
        param.put("orderId", request.orderId);
        param.put("merchantId", request.merchantId);

        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Pay2MerchantNew, param.createJson(),
                new Pay2MerchantEvent(), "MyOrderActivity");
    }


    /** ---------------------------------------- 商户类 --------------------------------------------------*/

    /**
     * 获取商户产品信息列表
     *
     * @param provId 省 ID
     * @param cityId 市 ID
     * @param distId 区 ID
     */
    public static void CustomList(JSONObject jsonobj, String query, String provId, String cityId, String distId, int beginNumber, int endNumber) {
        Double payAmt = jsonobj.optDouble("payAmt", 0.0);//判断金额
        String tranNo = jsonobj.optString("tranNo");//判断订单号
        String groupId = jsonobj.optString("groupId");
        String storeId = jsonobj.optString("storeId");
        String merchantId = jsonobj.optString("merchantId");
        String prodSeq = jsonobj.optString("prodSeq");
        RequestBuilder param = RequestBuilder.newInstance();
        if (payAmt > 0 && tranNo.length() > 0) {//先判断金额如果有值并且大于0且订单号不为空则表示为POS二维码
            param.put("prodSeq", prodSeq);//产品
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList_pos_info, param.createJson(),
                    new CustomListEvent(), "CustomListActivity");
        } else if (groupId.length() > 0) {//公司
            param.put("groupId", groupId);//公司编号
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList_group_info, param.createJson(),
                    new CustomListEvent(), "CustomListActivity");
        } else if (storeId.length() > 0) {//门店
            param.put("storeId", storeId);//门店编号
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList_store_info, param.createJson(),
                    new CustomListEvent(), "CustomListActivity");
        } else if (merchantId.length() > 0) {//商户
            param.put("merchantId", merchantId);//商户编号
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList_merchant_info, param.createJson(), new CustomListEvent(), "CustomListActivity");
        } else if (prodSeq.length() > 0) {// 产品
            param.put("prodSeq", prodSeq);//产品
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList_product_info, param.createJson(),
                    new CustomListEvent(), "CustomListActivity");

        } else if (query.length() > 0) {//搜索条件
            param.put("query", query);//条件搜索
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList_fuzzy_info, param.createJson(),
                    new CustomListEvent(), "CustomListActivity");


        } else {
            param.put("merchantType", jsonobj.optString("merchantType"));//行业分类编码
            param.put("groupId", "");//公司编号
            param.put("merchantId", "");//商户编号
            param.put("storeId", "");//门店编号
            param.put("query", "");//条件搜索
            param.put("provId", provId);//省
            param.put("cityId", cityId);//市
            param.put("distId", distId);//市
            param.put("beginNumber", beginNumber);
            param.put("endNumber", endNumber);
            RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CustomList, param.createJson(),
                    new CustomListEvent(), "CustomListActivity");
        }
    }

    /**
     * 借款(现金贷)
     */
    public static void CashLoanList() {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("businessCategory", "C");
        param.put("beginNumber", 1);
        param.put("endNumber", 10);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CashLoanList, param.createJson(),
                new CashLoanEvent(), "CashLoanActivity");
    }

    /**
     * 提交借款申请(现金贷)
     */
    public static void SubmitCashLoan(String txnAmt, String password, String orderId, String merchantId, String period, String payMethod, JSONObject necFidld, String productInstanceCode) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("txnAmt", txnAmt);
        param.put("password", password);
        param.put("orderId", orderId);
        param.put("merchantId", merchantId);
        param.put("period", period);
        param.put("payMethod", payMethod);
        param.put("intUnit", "D");
        param.put("intUnitMult", "365");
        param.put("necFidld", necFidld);
        param.put("productInstanceCode", productInstanceCode);


        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_CashLoanSub, param.createJson(),
                new CashLoanSubmitEvent(), "CashLoanActivity");


    }


    public static void category_list() {
        RequestBuilder param = RequestBuilder.newInstance();
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Post_Category_list, param.createJson(),
                new CategoryEvent(), "Category");
    }


    /**
     * 实名认证
     *
     * @param userName         姓名
     * @param userIdNo         身份证号码
     * @param idPhotoFrontId   身份证正面照片附件Id
     * @param idPhotoReverseId 身份证背面照片附件Id
     * @param idInHandId       手持证件照附件Id
     * @param idOcrName        证件OCR姓名
     * @param idOcrGender      证件OCR性别
     * @param idOcrNation      证件OCR民族
     * @param idOcrBirthday    证件OCR出生日期
     * @param idOcrAddress     证件OCR地址
     * @param idOcrExpireDate  证件OCR有效期
     */
    public static void validateRealName(String userName, String userIdNo, String idPhotoFrontId, String idPhotoReverseId, String idInHandId, String idOcrName
            , String idOcrGender, String idOcrNation, String idOcrBirthday, String idOcrAddress, String idOcrExpireDate) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("userName", userName);
        param.put("userIdNo", userIdNo);
        param.put("idPhotoFrontId", idPhotoFrontId);
        param.put("idPhotoReverseId", idPhotoReverseId);
        param.put("idInHandId", idInHandId);
        param.put("idOcrName", idOcrName);
        param.put("idOcrGender", idOcrGender);
        param.put("idOcrNation", idOcrNation);
        param.put("idOcrBirthday", idOcrBirthday);
        param.put("idOcrAddress", idOcrAddress);
        param.put("idOcrExpireDate", idOcrExpireDate);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AuthReal, param.createJson(),
                new NetValidateRealNameEvent(), "validateRealName");
    }
/*

    */
/**
 * 提交基本资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitBaseInfo(int cust_id, ComCustomer info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitPersonBaseInfo, param.createJson(),
                new NetSubmitPersonBaseInfoEvent(), "submitBaseInfo");
    }

    */
/**
 * 提交住址资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitAddress(int cust_id, ComAddresses info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitAddressInfo, param.createJson(),
                new NetSubmitAddressInfoEvent(), "submitAddress");
    }

    */
/**
 * 提交车辆资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitCard(int cust_id, ComVehicle info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitCardInfo, param.createJson(),
                new NetSubmitCardInfoEvent(), "submitCard");
    }


    */
/**
 * 提交联系人资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitContact(int cust_id, ComContact info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitContactInfo, param.createJson(),
                new NetSubmitContactInfoEvent(), "submitContact");
    }

    */
/**
 * 提交企业资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitCompany(int cust_id, ComCustComInf info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitCompanyInfo, param.createJson(),
                new NetSubmitCompanyInfoEvent(), "submitCompany");
    }


    */
/**
 * 提交教育资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitEdu(int cust_id, ComEducation info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitEduInfo, param.createJson(),
                new NetSubmitEducationInfoEvent(), "submitEdu");
    }

    */
/**
 * 提交学历证明资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitDegree(int cust_id, ComEducationProof info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        param.put("educationSeq", info.educationSeq);
        param.put("educationProofAttachmentId", info.getProofAssetsAttachmentId());
        param.put("educationProofAttachmentName", info.getProofAssetsAttachmentName());
        param.put("educationProofFileName", info.getProofAssetsFileName());
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitDegreeInfo, param.createJson(),
                new NetSubmitDegreeInfoEvent(), "submitDegree");
    }


    */
/**
 * 提交收入资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitIncoming(int cust_id, ProofInfo info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        param.put("incomeProofAttachmentId", info.getProofAssetsAttachmentId());
        param.put("incomeProofAttachmentName", info.getProofAssetsAttachmentName());
        param.put("incomeProofFileName", info.getProofAssetsFileName());
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitIncomingInfo, param.createJson(),
                new NetSubmitIncomeInfoEvent(), "submitIncoming");
    }


    */
/**
 * 提交房产资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitHouse(int cust_id, ProofInfo info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        param.put("premisesPermitAttachmentId", info.getProofAssetsAttachmentId());
        param.put("premisesPermitAttachmentName", info.getProofAssetsAttachmentName());
        param.put("premisesPermitFileName", info.getProofAssetsFileName());
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitHouseInfo, param.createJson(),
                new NetSubmitHouseInfoEvent(), "submitHouse");
    }


    */
/**
 * 提交车辆证明资料
 *
 * @param cust_id 客户号
 *//*

    public static void submitCardPaper(int cust_id, ProofInfo info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        param.put("vehiclePermitAttachmentId", info.getProofAssetsAttachmentId());
        param.put("vehiclePermitAttachmentName", info.getProofAssetsAttachmentName());
        param.put("vehiclePermitFileName", info.getProofAssetsFileName());
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitCardPaperInfo, param.createJson(),
                new NetSubmitCardPaperInfoEvent(), "submitCardPaper");
    }


    */

    /**
     * 提交其它资料
     *
     * @param
     *//*

    public static void submitOther(int cust_id, ProofInfo info) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", cust_id);
        param.put("otherProofAssetsAttachmentId", info.getProofAssetsAttachmentId());
        param.put("otherProofAssetsAttachmentName", info.getProofAssetsAttachmentName());
        param.put("otherProofAssetsFileName", info.getProofAssetsFileName());
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitOtherInfo, param.createJson(),
                new NetSubmitOtherInfoEvent(), "submitOther");
    }
*/
    public static void submitPersonInfo(String id, List<ValuePair> data, BaseMeta baseMeta) {
        RequestBuilder param = RequestBuilder.newInstance();
        for (ValuePair pair : data) {
            if (!TextUtils.isEmpty(pair.value))//如果值是空字符串，则不传key与值
                param.put(pair.key, pair.value);
        }
        if (baseMeta.code != null && baseMeta.id != null && baseMeta.code.length() > 0 && baseMeta.id.length() > 0) {
            param.put(baseMeta.code, baseMeta.id);
        }
        param.put("custId", ClientUserHelper.getCustId());

        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitPersonInfo + id, param.createJson(),
                new NetSubmitPersonInfoEvent(), "submitPersonInfo");
    }


    /**
     * 申请补充资料，提交
     *
     * @param seq  申请号
     * @param data 字段数据
     */
    public static void submitCustmInput(int seq, List<ValuePair> data) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderSeq", seq);
        for (ValuePair pair : data) {
            param.put(pair.key, pair.value);
        }
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitCustInput, param.createJson(),
                new NetSubmitCustInputEvent(), "submitCustmInput");
    }

    /**
     * 补件资料，提交
     *
     * @param seq  申请号
     * @param data 字段数据
     */
    public static void submitOrderPatch(int seq, List<ValuePair> data) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderId", seq);
        for (ValuePair pair : data) {
            param.put(pair.key, pair.value);
        }
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubmitOrderPatch, param.createJson(),
                new NetSubmitOrderPatchEvent(), "submitOrderPatch");
    }

    /**
     * 查询补件
     *
     * @param seq 申请号
     */
    public static void queryOrderPatch(String seq) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderId", seq);
        param.put("beginNumber", 1);
        param.put("endNumber", 100);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_QueryOrderPatch, param.createJson(),
                new NetQueryOrderPatchEvent(), "queryOrderPatch");
    }

    /**
     * 查询待提交信息
     *
     * @param seq 申请号
     */
    public static void queryApplySupply(String seq) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderId", seq);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyInstallSubmit, param.createJson(),
                new NetQueryApplySupplyEvent(), "queryApplySupply");
    }


    /**
     * 上传附件
     *
     * @param custId     申请号
     * @param fileName
     * @param fileBase64
     */
    public static void uploadFile(int custId, String fileName, String fileBase64) {
        RequestBuilder param = RequestBuilder.newInstance();
//        param.put("custId", custId);
        param.put("fileName", fileName);
        param.put("fileBytes", fileBase64);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_UploadFile, param.createJson(),
                new UploadFileEvent(), "uploadFile");
    }

    /**
     * 下載附件的URL
     *
     * @param id id
     * @return string
     */
    public static String downloadUrl(String id) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("fileId", id);
        JSONObject json = NetSecrty.endcodeRequest(param.createJson());
        String strUrl = "";
        strUrl += "client_id=" + Config.CLIENT_ID;
        strUrl += "&content=" + json.optString("content").replaceAll("\\+", "_");
        strUrl += "&sign=" + json.optString("sign");
        return Config.EURL + NET_DownloadFile + "?" + strUrl;
    }

    /**
     * 下載附件的URL只附加id
     *
     * @param url url
     * @return string
     */
    public static String downloadIdUrl(String url) {
        int pos = url.indexOf('?');
        String head = url.substring(0, pos);
        String queryString = url.substring(pos + 1, url.length());
        String[] urls = queryString.split("&");
        for (String cnt : urls) {
            if (cnt.startsWith("content=")) {
                try {
                    String jstr = Des3.decode(cnt.substring(cnt.indexOf('=') + 1, cnt.length()).replaceAll("_", "+"), Config.CLIENT_KEY);
                    JSONObject json = new JSONObject(jstr);
                    JSONObject obj = json.optJSONObject("tran_param");
                    return head + "?fileId=" + obj.optString("fileId");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return head;
    }

    /**
     * 上传电话薄，提交
     *
     * @param custId ID
     * @param data   字段数据
     */
    public static void uploadMobileBook(int custId, List<MBook> data) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        try {
            param.put("custMobile", new JSONArray(new Gson().toJson(data)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_UploadMobileBook, param.createJson(),
                new UploadBookEvent(), "uploadMobileBook");
    }

    /**
     * 获取聚立信资源列表
     */
    public static void getCreditResourList() {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("name", ClientUserHelper.getCustName());
        param.put("id_card_num", ClientUserHelper.getCustIdNo());
        param.put("cell_phone_num", ClientUserHelper.getCustPhone());
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_GetCreditResourceList, param.createJson(),
                new JLXCreditResouctListEvent(), "JLXTempActivity");
    }


    /**
     * 获取聚立信短信验证码
     */
    public static void getJLXPhoneCode(String webSiteName, String token) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("account", ClientUserHelper.getCustPhone());
        param.put("website", webSiteName);
        param.put("token", token);
        param.put("type", "RESEND_RESET_PWD_CAPTCHA");
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_GetJLXPhoneCode, param.createJson(),
                new JLXSendCodeEvent(), "JXLVerifyStep1Activity");
    }


    /**
     * 聚立信 重置密码
     *
     * @param webSiteName 平台名称
     * @param token
     * @param psw         新密码
     * @param code        短信验证码
     */
    public static void resetJLXPhonePsw(String webSiteName, String token, String psw, String code) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("account", ClientUserHelper.getCustPhone());
        param.put("website", webSiteName);
        param.put("token", token);
        param.put("type", "SUBMIT_RESET_PWD");
        param.put("password", psw);
        param.put("captcha", code);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_GetJLXPhoneCode, param.createJson(),
                new JLXResetEvent(), "JXLVerifyStep1Activity");
    }


    /**
     * 提交聚立信 采集请求
     *
     * @param webSiteName
     * @param token
     * @param psw
     * @param code
     */
    public static void commitJLXPCollection(String webSiteName, String token, String account, String psw, String code, String type) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("account", account);
        param.put("websiteName", webSiteName);
        param.put("token", token);
        param.put("password", psw);
        param.put("idNo", ClientUserHelper.getCustIdNo());//用户身份证
        param.put("type", type);//默认可为空
        param.put("captcha", code);//默认为空
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Commit_JLXCollection, param.createJson(),
                new JLXCollectonEvent(), "JXLVerifyStep");
    }


    /**
     * 跳过聚立信步骤
     */
    public static void skipJLXVerify(String webSiteName, String token, String tag) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("websiteName", webSiteName);
        param.put("token", token);
        param.put("idNo", ClientUserHelper.getCustIdNo());//用户身份证
        JLXSkipEvent mEvent = new JLXSkipEvent();
        mEvent.setTag(tag);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Skip_JLXStep, param.createJson(),
                mEvent, "JXLVerifyStep1Activity");
    }


    /**
     * 处理错误信息
     *
     * @param ctx   设备上下文
     * @param event 事件信息
     */
    public static NetResult showError(Context ctx, BaseEvent event) {
        if (event.getStatus().equals(BaseEvent.EVENT_STATUS.EVENT_ERROR)) {
            String msg = (String) event.getMsg();
            if (TextUtils.isEmpty(msg)) {
                ToastUtil.show("服务器响应错误");
            } else {
                ToastUtil.show((String) event.getMsg());
            }
            return null;
        } else {
            NetResult result = (NetResult) event.getMsg();
            if (result.getState() == NetResult.CODE_ERROR) {
                //判断是否为session失效
                if ("0000000001".equals(result.getErr_code())) {
                    ToastUtil.show(result.getErr_msg());
                    EventBus.getDefault().post(new SessionTimeOutEvent());
                    return null;
                }
                DialogUtil.showMsgDialog((Activity) ctx, "错误提示", result.getErr_msg(), "确定", null);
                return null;
            } else {
                return result;
            }
        }
    }


    /**
     * 下載附件
     *
     * @param id id
     */
    public static void downloadFile(int id) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("fileId", id);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_DownloadFile, param.createJson(),
                new DownloadEvent(), "downloadFile");
    }


    /** ---------------------------------------- 支付绑卡类 --------------------------------------------------*/

    /**
     * 签约代扣绑卡短信发送
     *
     * @param usrName    姓名
     * @param idType     证件类型 （暂定I）
     * @param idNum      证件号码
     * @param cardNo     银行卡号
     * @param cardMobile 预留手机
     * @param cardProv   开户省
     * @param cardArea   开户市
     * @param bankName   开户行
     * @param cardStreet 开户区/县
     */
    public static void signPaySMS(String usrName,
                                  String idType, String idNum, String cardNo, String cardMobile, String cardProv,
                                  String cardArea, String cardStreet, String bankName) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("ver", "00");
        param.put("custId", ClientUserHelper.getCustId());
        param.put("usrName", usrName);
        param.put("idType", idType);
        param.put("idNum", idNum);
        param.put("cardNo", cardNo);
        param.put("cardMobile", cardMobile);
        param.put("cardProv", cardProv);
        param.put("cardArea", cardArea);
        param.put("cardStreet", cardStreet);
        param.put("bankName", bankName);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Sign_Pay_SMS, param.createJson(),
                new SignPaySMSEvent(), "signPaySMS");
    }

    /**
     * 签约代扣绑卡确认
     *
     * @param usrName    姓名
     * @param idType     证件类型 （暂定I）
     * @param idNum      证件号码
     * @param cardNo     银行卡号
     * @param cardMobile 预留手机
     * @param cardProv   开户省
     * @param cardArea   开户市
     * @param bankName   开户行
     * @param cardStreet 开户区/县
     */
    public static void signPayConfirm(String usrName,
                                      String idType, String idNum, String cardNo, String cardMobile, String cardProv,
                                      String cardArea, String cardStreet, String smsCode, String bankName, String channelNo, String autoRepay) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("ver", "00");
        param.put("custId", ClientUserHelper.getCustId());
        param.put("usrName", usrName);
        param.put("idType", idType);
        param.put("idNum", idNum);
        param.put("cardNo", cardNo);
        param.put("cardMobile", cardMobile);
        param.put("cardProv", cardProv);
        param.put("cardArea", cardArea);
        param.put("cardStreet", cardStreet);
        param.put("smsCode", smsCode);
        param.put("bankName", bankName);
        param.put("pcFlag", "");
        param.put("channelNo", channelNo);
        param.put("autoRepay", autoRepay);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Sign_Pay_Confirm, param.createJson(),
                new SignPayConfirmEvent(), "signPayConfirm");
    }

    /**
     * 签约快捷支付短信发送
     *
     * @param usrName    姓名
     * @param idType     证件类型 （暂定I）
     * @param idNum      证件号码
     * @param cardNo     银行卡号
     * @param cardMobile 预留手机
     * @param cardProv   开户省
     * @param cardArea   开户市
     * @param bankName   开户行
     * @param cardStreet 开户区/县
     */
    public static void quickPaySMS(String usrName,
                                   String idType, String idNum, String cardNo, String cardMobile, String cardProv,
                                   String cardArea, String cardStreet, String bankName) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("ver", "00");
        param.put("custId", ClientUserHelper.getCustId());
        param.put("usrName", usrName);
        param.put("idType", idType);
        param.put("idNum", idNum);
        param.put("cardNo", cardNo);
        param.put("cardMobile", cardMobile);
        param.put("cardProv", cardProv);
        param.put("cardArea", cardArea);
        param.put("cardStreet", cardStreet);
        param.put("bankName", bankName);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Quick_Pay_SMS, param.createJson(),
                new QuickPaySMSEvent(), "quickPaySMS");
    }

    /**
     * 签约快捷支付确认
     *
     * @param usrName    姓名
     * @param idType     证件类型 （暂定I）
     * @param idNum      证件号码
     * @param cardNo     银行卡号
     * @param cardMobile 预留手机
     * @param cardProv   开户省
     * @param cardArea   开户市
     * @param bankName   开户行
     * @param cardStreet 开户区/县
     */
    public static void quickPayConfirm(String usrName,
                                       String idType, String idNum, String cardNo, String cardMobile, String cardProv,
                                       String cardArea, String cardStreet, String smsCode, String bankName, String channelNo, String autoRepay) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("ver", "00");
        param.put("custId", ClientUserHelper.getCustId());
        param.put("usrName", usrName);
        param.put("idType", idType);
        param.put("idNum", idNum);
        param.put("cardNo", cardNo);
        param.put("cardMobile", cardMobile);
        param.put("cardProv", cardProv);
        param.put("cardArea", cardArea);
        param.put("cardStreet", cardStreet);
        param.put("smsCode", smsCode);
        param.put("bankName", bankName);
        param.put("pcFlag", "");
        param.put("channelNo", channelNo);
        param.put("autoRepay", autoRepay);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Quick_Pay_Confirm, param.createJson(),
                new QuickPayConfirmEvent(), "quickPayConfirm");
    }

    /**
     * 快捷支付短信发送
     *
     * @param cardNo  银行卡号
     * @param phoneNo 预留手机
     */
    public static void quickPaymentSMS(String cardNo, String phoneNo) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("cardNo", cardNo);
        param.put("phoneNo", phoneNo);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Quick_Payment_SMS, param.createJson(),
                new QuickPaymentSMSEvent(), "quickPaymentSMS");
    }


    /**
     * 芝麻信用征信
     *
     * @param orderNO 订单号
     */
    public static void queryZMXY(String orderNO) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("name", ClientUserHelper.getCustName());//姓名
        param.put("certNo", ClientUserHelper.getCustIdNo());//身份证
        param.put("state", orderNO);//订单号
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ZMXY, param.createJson(),
                new ZMXYEvent(), "ZMXYTempActivity");
    }


    /**
     * 芝麻成功返回验证param
     *
     * @param params 芝麻信用返回的params
     */
    public static void verfifyZMXY(String params) {
        Log.e("amtf", "芝麻信用Parma请求");
        RequestBuilder mParam = RequestBuilder.newInstance();
        mParam.put("params", params);//订单号
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ZMXY_PARAM, mParam.createJson(),
                new ZMXYParamEvent(), "ZMXYTempActivity");
    }

    /**
     * 快捷支付确认
     *
     * @param custId    客户号
     * @param txnAmt    还款金额
     * @param password  支付密码
     * @param payMethod 使用代扣或者快捷
     * @param cardNo    卡号
     * @param acctSeq   贷款子账号
     * @param orderId   消费子订单编号
     * @param smsCode   短信验证码
     */
    public static void accountQuickRepay(int custId,
                                         double txnAmt,
                                         String password,
                                         String payMethod,
                                         String cardNo,
                                         String acctSeq,
                                         int orderId,
                                         String smsCode) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", custId);
        param.put("txnAmt", txnAmt);
        param.put("password", password);
        param.put("payMethod", payMethod);
        param.put("cardNo", cardNo);
        param.put("acctSeq", acctSeq);
        param.put("orderId", orderId);
        param.put("smsCode", smsCode);
        RequestHelper.getInstance().repayRequest(RequestHelper.HTTP_POST, Config.EURL + NET_AccountRepay, param.createJson(),
                new QuickPaymentConfirmEvent(), "accountQuickRepay");
    }


    /**
     * 快捷支付确认
     *
     * @param cardNo   银行卡号
     * @param smsCode  短信验证码
     * @param transAmt 交易金额
     * @param password 交易密码
     */
    public static void quickPaymentConfirm(String cardNo, String smsCode, String transAmt, String password, String payMethod) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("cardNo", cardNo);
        if (StringUtil.isNotNull(smsCode))
            param.put("smsCode", smsCode);
        param.put("transAmt", transAmt);
        param.put("password", password);
        param.put("payMethod", payMethod);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Quick_Payment_Confirm, param.createJson(),
                new QuickPaymentConfirmEvent(), "quickPaymentConfirm");
    }

    /**
     * 获取电子协议
     *
     * @param orderSeq      订单号 agreementType为LOAN 时必填
     * @param agreementType 协议类型
     *                      	REGISTER=注册
     *                      	LOAN=分期借款
     *                      	QUICK_PAY=快捷支付
     *                      	SIGN_PAY=签约代扣
     *                      	AUTH_JXL=聚信立
     *                      	AUTH_ZM=芝麻
     */
    public static void getAgreement(String orderSeq, String agreementType) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId()); //agreementType为REGISTER和LOAN时不必填，其他必填
        param.put("orderSeq", orderSeq);
        param.put("agreementType", "LOAN"); // 	LOAN=分期借款
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Get_Agreement, param.createJson(),
                new GetAgreementEvent(), "getAgreement");
    }


    /**
     * OCR识别
     *
     * @param fileId 文件ID
     * @param type   识别类型
     *               	2=身份证
     *               	3=身份证反面
     *               	17=银行卡
     *               	20=名片
     */
    public static void identityOCR(Integer fileId, String type) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("fileId", fileId);
        param.put("type", type);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_IdentityOCR, param.createJson(),
                new IdentityOCREvent(), "identityOCR");
    }


    /**
     * 查询支持银行卡列表
     *
     * @param transType 交易类型
     *                  	W=代扣
     *                  	F=快捷
     *                  	C=取现
     */
    public static void queryBankList(String transType) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("exclusiveID", "null");//TODO 暂时写死为null为方便以后拓展
        param.put("transType", transType);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_QUERY_BANK_LIST, param.createJson(),
                new QueryBankListEvent(), "queryBankList");
    }


    /**
     * 名片OCR信息上传
     *
     * @param orderId 订单ID
     * @param data    名片ocr信息
     * @param picId   名片图片Id
     */
    public static void osmPatchBusinessCard(Integer orderId, String data, Integer picId) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderId", orderId);
        param.put("data", data);
        param.put("picId", picId);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_osmPatchBusinessCard, param.createJson(),
                new OsmPatchBusinessCardEvent(), "osmPatchBusinessCard");
    }

    /**
     * 侧滑获取金额信息
     */
    public static void acquisitionAmount() {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("beginNumber", 1);
        param.put("endNumber", 2);
        param.put("custType", "C");
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_AcquisitionAmount, param.createJson(),
                new AcquisitionAmountEvent(), "acquisitionAmount");
    }

    /**
     * 发送日志信息
     *
     * @param msg 日志内容
     */
    public static void sendLog(String msg) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("log", msg);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + Net_log, param.createNLJson(),
                new LogEvent(), "sendLog");
    }

    /**
     * 发送日志信息
     *
     * @param msg             日志内容
     * @param successListener
     * @param errorListener
     */
    public static void sendLog(String msg, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("log", msg);
        RequestHelper.getInstance().sysRequest(RequestHelper.HTTP_POST, Config.EURL + Net_log, param.createNLJson(), "sendLog",
                new DefaultRetryPolicy(300, 1, 1.0F), successListener, errorListener);
    }


    /**
     * 发送照片,用于异步发送图片
     *
     * @param fileName        日志内容
     * @param fileBase64
     * @param successListener
     * @param errorListener
     */
    public static void sendImage(String fileName, String fileBase64, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("fileName", fileName);
        param.put("fileBytes", fileBase64);
        RequestHelper.getInstance().sysRequest(RequestHelper.HTTP_POST, Config.EURL + NET_UploadFile, param.createJson(), "sendImage",
                new DefaultRetryPolicy(16000, 1, 1.0F), successListener, errorListener);
    }

    /**
     * 消息和提醒列表
     */
    public static void getMessageList(Integer pageSize, Integer pageNo, String search, String msgCate) {
        Log.e("amtf", "----请求消息列表----->");
        RequestBuilder param = RequestBuilder.newInstance();

        param.putBase("userMP", ClientUserHelper.getCustPhone());
        param.putBase("table_pageSize", pageSize);
        param.putBase("table_page", pageNo);
        param.putBase("table_search", search);
        param.putBase("msgCate", msgCate);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_NotificationList, param.createNLJson(),
                new MsgListEvent(), "MessageActivity");
    }

    /**
     * 置顶消息列表
     */
    public static void getTopMessageList(Integer pageSize, Integer pageNo, String search, String msgCate) {
        RequestBuilder param = RequestBuilder.newInstance();

        param.putBase("userMP", ClientUserHelper.getCustPhone());
        param.putBase("table_pageSize", pageSize);
        param.putBase("table_page", pageNo);
        param.putBase("table_search", search);
        param.putBase("msgCate", msgCate);
        param.putBase("isTop", "Y");
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_NotificationList, param.createNLJson(),
                new TopMsgListEvent(), "TopMessageList");
    }


    /**
     * 获取收藏列表
     *
     * @param table_pageSize 每页条数
     * @param table_page     第几页
     * @param table_search   查询条件
     * @param favType        分类默认S
     *                       {	M=商户
     *                       	S=门店
     *                       	P=产品
     *                       }
     */
    public static void getFaverList(int table_pageSize, int table_page, String table_search, String favType) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.putBase("table_pageSize", table_pageSize);
        param.putBase("table_page", table_page);
        param.putBase("table_search", table_search);
        param.putBase("favType", favType);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_FaverList, param.createJson(),
                new NetQueryFaverListEvent(), "getFaverList");
    }

    /**
     * 文章转发数记录
     */
    public static void msgForward(String mId) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("mId", mId);
        JSONObject json = NetSecrty.endcodeRequest(param.createJson());
        String strUrl = "";
        strUrl += "client_id=" + Config.CLIENT_ID;
        strUrl += "&content=" + json.optString("content").replaceAll("\\+", "_");
        strUrl += "&sign=" + json.optString("sign");

        String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
        String url = Config.EURL + Net_MsgForward + "?" + strUrl;
        String encodedUrl = Uri.encode(url, ALLOWED_URI_CHARS);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_GET, encodedUrl, null,
                new MsgForwordEvent(), "MsgForwordEvent");
    }


    /**
     * 解除绑定银行卡发送短息
     */
    public static void unBindCardSendMsg(String cardNo, String type) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("custId", ClientUserHelper.getCustId());
        param.put("cardMobile", ClientUserHelper.getCustPhone());
        param.put("cardNo", cardNo);
        param.put("type", type);//(解绑类型 代扣W 解绑F)
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + UnBindCardSendMsg, param.createJson(),
                new UnbindCardSendMsgEvent(), "SignPayActivity");
    }


    /**
     * 确认解除绑定
     *
     * @param cardNo  银行卡号
     * @param smsCode 短信验证码
     * @param type    类型
     */
    public static void doUnBindCard(String cardNo, String smsCode, String type) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("cardMobile", ClientUserHelper.getCustPhone());
        param.put("cardNo", cardNo);
        param.put("custId", ClientUserHelper.getCustId());
        param.put("smsCode", smsCode);
        param.put("type", type);//(解绑类型 代扣W 解绑F)
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + DoUnBindCard, param.createJson(),
                new DoUnbindCardEvent(), "SignPayActivity");
    }

    /**
     * 更新自动还款状态
     *
     * @param cardNo 卡号
     * @param status 按钮状态
     */
    public static void updateAutoRepayStatus(String cardNo, String status) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("cardNo", cardNo);
        param.put("custId", ClientUserHelper.getCustId());
        param.put("status", status);//(按钮状态 )
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + UpdateAutoRepayStatus, param.createJson(),
                new UpdateAutoRepayStatusEvent(), "SignPayActivity");
    }

    /**
     * 合同查询
     *
     * @param orderId    订单编号
     * @param loanAmount 请款金额
     */
    public static void queryProtocol(int orderId, double loanAmount) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderSeq", orderId);
        param.put("loanAmount", loanAmount);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Protocol, param.createJson(),
                new NetEProtocolEvent(), "queryProtocol");
    }

    /**
     * 查询银行卡列表新
     */
    public static void queryBankListNew() {
        RequestBuilder param = RequestBuilder.newInstance();
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + QueryBankListNew, param.createJson(),
                new BankListNewEvent(), "SignPayActivity");
    }


    /**
     * 授信签约合同
     *
     * @param orderSeq 订单编号
     */
    public static void signTrustProtocol(int orderSeq) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderSeq", orderSeq);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyTrustProtocol, param.createJson(),
                new NetApplyTrustEvent(), "signTrustProtocol");
    }


    /**
     * 放款，签约动作
     *
     * @param orderSeq    订单编号
     * @param applyAmt    请款金额
     * @param contractSeq 合同编号主键
     * @param fileName    文件名
     * @param signFile    签名
     */
    public static void signLoansProtocol(int orderSeq, double applyAmt, String contractSeq, String fileName,
                                         String signFile) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderSeq", orderSeq);
        param.put("applyAmt", applyAmt);
        param.put("contractSeq", contractSeq);
        param.put("fileName", fileName);
        param.put("signFile", signFile);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyLoansProtocol, param.createJson(),
                new NetApplyLoansEvent(), "signPayProtocol");
    }


    /**
     * 子订单查询
     *
     * @param orderSeq 订单编号
     */
    public static void querySubOrder(int orderSeq) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("orderSeq", orderSeq);
        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubOrder, param.createJson(),
                new NetQuerySubOrderEvent(), "querySubOrder");
    }

}
