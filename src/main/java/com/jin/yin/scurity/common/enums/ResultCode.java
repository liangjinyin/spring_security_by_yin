package com.jin.yin.scurity.common.enums;

/**
 * User: liangjinyin
 * Date: 2018-08-07
 * Time: 15:09
 */
public enum ResultCode {
    /**
     * 枚举类
     */
    OPERATION_SUCCESSED("200", "操作成功"),
    OPERATION_FAILED("001", "操作失败"),
    OPERATION_ABNORMAL("002", "操作异常"),
    SESSION_INVALID("003", "会话已失效"),
    OPERATION_NOT_PERMITTED("004", "权限不足"),
    OPERATION_PARAM_ERROR("005", "数据格式错误"),
    CUSTOM_ERROR("998", "{errmsg}"),
    NONE("999","无意义数据"),

    MOBILECODE_EXPIRED("1001", "验证码已失效"),
    MOBILECODE_INCORRECT("1002", "验证码错误"),
    MOBILECODE_TYPE_INVALID("1003", "无效的验证码请求"),
    MOBILECODE_EMPTY("1004", "验证码为空"),

    USER_NAME_OR_PASSWORD_EMPTY("101", "帐号或密码为空"),
    USER_DENIED("102", "未授权登录"),
    USER_NAME_OR_PASSWORD_ERROR("103", "账号或密码错误"),
    USER_PASSWORD_ERROR("104", "密码错误"),
    USER_PASSWORD_NEW_SAMEWITH_OLD("105", "新密码和旧密码一样"),
    USER_PASSWORD_EMPTY("106", "密码为空"),

    USER_NOTEXIST("201", "用户不存在"),
    USER_MOBILE_EXIST("203", "手机号已被使用"),
    USER_NAME_EMPTY("204", "用户姓名不能为空"),
    USER_MOBILE_EMPTY("205", "用户手机号不能为空"),
    USER_HAS_EXIST("206","用户名已经存在！"),

    APP_NOT_EXIST("301","应用不存在"),
    INTENT_NOT_EXIST("302","场景不存在"),
    INTENT_HAS_EXIST("303","场景已经存在"),
    DICT_NOT_EXIST("304","词库不存在"),
    MIC_NOT_EXIST("305","微服务不存在"),
    PROCESS_NOT_EXIST("306","流程不存在"),
    ROLE_NOT_EXITS("307","角色不存在"),
    OFFICE_NOT_EXITS("308","机构不存在"),
    THEME_NOT_EXITS("309","主题不存在"),
    SECRET_NOT_EXITS("310","密级不存在"),

    ROLE_NAME_EMPTY("601", "权限名不能为空"),
    ROLE_NAME_EXIST("602", "权限名已存在"),
    ROLE_NOTEXIST("603", "权限不存在"),
    ;

    private String code;
    private String msg;
    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getResultCode() {
        return code;
    }
    public String getResultMsg() {
        return msg;
    }

    /**
     *
     * @param code
     * @return
     */
    public static ResultCode createResultCode(String code) {
        for(ResultCode val : ResultCode.values()) {
            if(val.getResultCode().equalsIgnoreCase(code)) {
                return val;
            }
        }
        return ResultCode.NONE;
    }
    public static ResultCode createCustomResultCode(String msg) {
        ResultCode custom =  ResultCode.CUSTOM_ERROR;
        custom.msg = msg;
        return custom;
    }
}
