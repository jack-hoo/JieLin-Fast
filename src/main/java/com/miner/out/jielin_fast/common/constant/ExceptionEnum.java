/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: ExceptionEnum
 * Author:   shugan
 * Date:     2017/12/15 17:05
 * Description: 异常状态标识码
 */
package com.miner.out.jielin_fast.common.constant;

/**
 * 〈异常状态标识码〉
 *
 * @author shugan
 * @create 2017/12/15
 * @since 1.0.0
 */
public enum ExceptionEnum {
    UNKNOW_ERR(-1,"未知错误"),
    USER_NOT_EXIST(100001,"用户不存在"),
    PARAMS_INVALID(100002,"参数验证失败"),
    PARAMS_TYPE_ERR(100003,"参数类型不正确"),
    PARAMS_MISSING_ERR(100004,"缺少相应参数"),
    ILLEGAL_CHARACTER(100003,"sql语句包含非法字符"),
    ARRAY_LENGTH_ZERO_ERR(100004,"数组长度不能为0"),
    LOGIN_ERR(400003,"登录异常"),
    UNAUTHORIZED_ERR(400001,"无权访问,请联系管理员"),
    METHOD_NOT_SUPPORTED_ERR(400000,"请求方法不被支持"),


    //数据库相关自定义异常
    DATA_NOT_EXIST(600001,"数据不存在"),
    DATA_ALREADY_EXIST(600002,"数据已经存在");
    private int code;
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
