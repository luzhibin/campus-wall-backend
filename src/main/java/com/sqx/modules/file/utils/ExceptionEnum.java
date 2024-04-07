package com.sqx.modules.file.utils;

public enum ExceptionEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    LIMIT_USER(-100, "账号已经禁用，请联系管理员！"),
    USER_NOT_FIND(-101, "用户未注册"),
    USER_IS_BIND_FOR_ANTHER_OPENID(-99, "当前手机号已经被其他微信绑定"),
    WRONT_TOKEN(-102, "用户信息失效，请重新登录"),
    USER_PWD_EMPTY(-103, "用户名密码不能为空"),
    USER_PWD_ERROR(-104, "用户名或密码错误"),
    USER_IS_EXITS(-105, "手机号已经注册!"),
    ERROR(-106, "服务器内部错误"),
    UPDATE_PWD_ERROR(-107, "密码修改失败"),
    STATE_PWD_ERROR(-108, "状态修改失败"),
    DATA_EMPTY(-109, "添加数据不能为空"),
    Return_ATA_EMPTY(-110, "暂无数据"),
    ADD_ERROR(-111, "提现失败"),
    CODE_ERROR(-112, "验证码不正确"),
    BIND_ERROR(-113, "手机号已经被其他账号绑定"),
    SEND_ERROR(-114, "验证码发送失败"),
    USER_PHONE_ERROR(-115, "用户名不能为空"),
    OLD_PWD_ERROR(-116, "原始密码错误"),
    IS_REGISTER(-117, "当前手机号已经绑定其他微信账号"),
    IS_BIND(-118, "当前淘宝账号已经绑定其他手机号"),
    IS_BIND_RELATION(-119, "当前账号已经绑定其他淘宝账号"),
    OLD_NOT_SAME_NEW_PWD_ERROR(-120, "新密码不能等于和原始密码一致"),
    USER_IS_REGISTER(-121, "用户已经注册请前往登录"),
    RELATIONID_IS_REGISTER(-122, "淘宝账号已经授权绑定其他手机号"),
    CODE_NOT_FOUND(-123, "邀请码不存在"),
    COMMON_IS_EXITS(-124, "已经存在"),
    COUPONS_ZERO(-125, "优惠券被领完了"),
    COUPONS_GET_OUT(-126, "优惠券超过领取次数"),
    COUPONS_TIME_OUT(-127, "优惠券已过期");
    private Integer code;
    private String msg;
    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

