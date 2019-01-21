package com.pengfyu.zuul.common;

/**
 * definition work exception
 *
 */
public class GatewayException extends RuntimeException{
    //错误编码
    private int code;
    //错误描述
    private String message;
    //任意信息
    private Object data;
    //定义基本构造方法

    public GatewayException( String message){
        this.message=message;
    }

    public GatewayException(int code, String message){
        this.code=code;
        this.message=message;
    }

    public GatewayException(int code, String message, Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public static GatewayException errorBase(int code,String message,Object data){
        return new GatewayException(code,message,data);
    }

    public static GatewayException errorBase(int code,String message){
        return new GatewayException(code,message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
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
}
