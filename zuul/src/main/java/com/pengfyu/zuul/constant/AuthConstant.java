package com.pengfyu.zuul.constant;

/**
 * 系统常量
 *
 * @author Li Shijie
 * @since 1.0.0
 */
public class AuthConstant {

    public static final String BEARER_TOKEN_TYPE = "bearer ";

    public static final String Authorization = "Authorization";

    public static final String USER_ID = "user-header-id";

    public static final String USER_ROLES = "user-header-roles";

    public static final String SECRET = "mySecret";

    public static final String GOODS_OPEN_API_A = "/goods/sku/user";

    public static final String GOODS_OPEN_API_B = "/goods/GoodsClassificationFront/user";

    public static final String AGREEMENT_SUPPLIER_INFO = "/agreement/supplier/info";

    public static final String AGREEMENT_REGISTER_INFO = "/agreement/register/info";

    public static final String REGION_FINDSELECTSERVERRE = "/region/findSelectServerRe";

    public static final String MESSAGE_MESSAGESERVER_QUERYTERMINAL = "/message/messageserver/queryTerminal";

    public static final String MESSAGE_MESSAGESERVER_QUERYTERMINALL = "/message/messageserver/queryTerminall";

    public static final String PAY_CALLBACK = "/payBack";

    public static final String AUTH_PATH = "/auth";

    public static final String AUTH_SERVER = "auth-server";

    public static final String UPMS_SERVER = "user-server";

    public static final String ORDER_SERVER = "order-server";

    public static final String CART_SERVER = "cart-server";

    public static final String GOODS_SERVER = "goods-server";

    public static final String EXPIRE_TOKEN = "expire_token";

    public static final String PASS = "pass";

    public static final String ILLEGAL = "illegal";

    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String ALREADY_LOGIN = "alreadyLogin";

    public static final String AuthFilterError = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":775,\n" +
            "    \"message\":\"无效token\"\n" +
            "}";

    public static final String AskRefresh = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":776,\n" +
            "    \"message\":\"\"\n" +
            "}";

    public static final String ExpireToken = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":9527,\n" +
            "    \"message\":\"登陆过期\"\n" +
            "}";

    public static final String AuthError = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":777,\n" +
            "    \"message\":\"权限服务调用失败\"\n" +
            "}";

    public static final String UpmsError = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":778,\n" +
            "    \"message\":\"用户服务调用失败\"\n" +
            "}";

    public static final String ORDER_SERVER_ERROR = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":779,\n" +
            "    \"message\":\"订单服务调用失败\"\n" +
            "}";
    public static final String alreadyLogin = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":780,\n" +
            "    \"message\":\"当前用户已在其他设备登录\"\n" +
            "}";

    public static final String CART_SERVER_ERROR = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":781,\n" +
            "    \"message\":\"购物车服务调用失败\"\n" +
            "}";

    public static final String GOODS_SERVER_ERROR = "{\n" +
            "    \"success\":false,\n" +
            "    \"errorCode\":782,\n" +
            "    \"message\":\"商品服务调用失败\"\n" +
            "}";

}
