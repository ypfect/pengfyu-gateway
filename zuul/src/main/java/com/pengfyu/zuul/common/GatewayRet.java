package com.pengfyu.zuul.common;

/***
 * 返回结果类
 */
public class GatewayRet {

    private boolean success;
    private String message;
    private Object data;
    private int code;

    public GatewayRet() {

    }

    public static GatewayRet successExploreRet(int code, String msg){
        GatewayRet ret = new GatewayRet(true,msg,code);
        return  ret;
    }

    public static GatewayRet successExploreRet(){
        GatewayRet ret = new GatewayRet();
        ret.setSuccess(true);
        return  ret;
    }
    public static GatewayRet successExploreRet(int code,String msg,Object data){
        GatewayRet ret = new GatewayRet(true,msg,data,code);
        return  ret;
    }

    public static GatewayRet failureExploreRet(int code,String msg){
        GatewayRet ret = new GatewayRet(false,msg,code);
        return  ret;
    }

    public static GatewayRet failureExploreRet(int code,String msg,Object data){
        GatewayRet ret = new GatewayRet(false,msg,data,code);
        return  ret;
    }

    public GatewayRet(boolean success, String message, Object data, int code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public GatewayRet(boolean success, String message, int code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public GatewayRet(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
