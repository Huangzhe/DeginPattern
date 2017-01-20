package com.sh.lynn.hz.developbox.netframe.volleyutil;


import com.android.volley.DefaultRetryPolicy;
import com.sh.lynn.hz.developbox.event.YYPhotoEvent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/4.
 * <p/>
 * 网络请求处理
 */
public class NetUtils {

    private final static String NET_YYJOY = "http://route.showapi.com/341-2?showapi_appid=30978&showapi_sign=7a1b389d30e547aea07908aaf8826d9b&maxResult=1";// 易源笑图


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

//            json.put("showapi_appid", "30978");
//            json.put("showapi_sign", "7a1b389d30e547aea07908aaf8826d9b");
        }

        public JSONObject createJson() {
            //JSONObject json = base;
            try {

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

    public static void getPhoto(String num) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.putBase("maxResult", num);
//        NET_YYJOY=  NET_YYJOY+"?showapi_appid=30978&showapi_sign=7a1b389d30e547aea07908aaf8826d9b";

        RequestHelper.getInstance().postJson(NET_YYJOY/*地址*/, param.createNLJson(), new YYPhotoEvent()/*自定义事件*/, "tag"/*请求标记*/, new DefaultRetryPolicy());
    }

//    /**
//     * 更新自动还款状态
//     *
//     * @param cardNo 卡号
//     * @param status 按钮状态
//     */
//    public static void updateAutoRepayStatus(String cardNo, String status) {
//        RequestBuilder param = RequestBuilder.newInstance();
//        param.put("cardNo", cardNo);
//        param.put("custId", ClientUserHelper.getCustId());
//        param.put("status", status);//(按钮状态 )
//        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + UpdateAutoRepayStatus, param.createJson(),
//                new UpdateAutoRepayStatusEvent(), "SignPayActivity");
//    }
//
//    /**
//     * 合同查询
//     *
//     * @param orderId    订单编号
//     * @param loanAmount 请款金额
//     */
//    public static void queryProtocol(int orderId, double loanAmount) {
//        RequestBuilder param = RequestBuilder.newInstance();
//        param.put("orderSeq", orderId);
//        param.put("loanAmount", loanAmount);
//        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_Protocol, param.createJson(),
//                new NetEProtocolEvent(), "queryProtocol");
//    }
//
//    /**
//     * 查询银行卡列表新
//     */
//    public static void queryBankListNew() {
//        RequestBuilder param = RequestBuilder.newInstance();
//        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + QueryBankListNew, param.createJson(),
//                new BankListNewEvent(), "SignPayActivity");
//    }
//
//
//    /**
//     * 授信签约合同
//     *
//     * @param orderSeq 订单编号
//     */
//    public static void signTrustProtocol(int orderSeq) {
//        RequestBuilder param = RequestBuilder.newInstance();
//        param.put("orderSeq", orderSeq);
//        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyTrustProtocol, param.createJson(),
//                new NetApplyTrustEvent(), "signTrustProtocol");
//    }
//
//
//    /**
//     * 放款，签约动作
//     *
//     * @param orderSeq    订单编号
//     * @param applyAmt    请款金额
//     * @param contractSeq 合同编号主键
//     * @param fileName    文件名
//     * @param signFile    签名
//     */
//    public static void signLoansProtocol(int orderSeq, double applyAmt, String contractSeq, String fileName,
//                                         String signFile) {
//        RequestBuilder param = RequestBuilder.newInstance();
//        param.put("orderSeq", orderSeq);
//        param.put("applyAmt", applyAmt);
//        param.put("contractSeq", contractSeq);
//        param.put("fileName", fileName);
//        param.put("signFile", signFile);
//        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_ApplyLoansProtocol, param.createJson(),
//                new NetApplyLoansEvent(), "signPayProtocol");
//    }
//
//
//    /**
//     * 子订单查询
//     *
//     * @param orderSeq 订单编号
//     */
//    public static void querySubOrder(int orderSeq) {
//        RequestBuilder param = RequestBuilder.newInstance();
//        param.put("orderSeq", orderSeq);
//        RequestHelper.getInstance().secrtyJson(RequestHelper.HTTP_POST, Config.EURL + NET_SubOrder, param.createJson(),
//                new NetQuerySubOrderEvent(), "querySubOrder");
//    }

}
