package com.swagger.common;

/**
 * @Author: YL
 * @Date: 2023/06/08/15:19
 * @Description:
 */
public enum ResponseErrorEnums {
    SUCCESS_OPTION("200", "操作成功", 200),
    LOGIN_SUCCESS("200", "登陆成功", 200),
    LOGOUT_SUCCESS("200", "已退出登录", 200),
    SENDEMAIL_SUCCESS("200", "邮件已发送，请注意查收", 200),
    EDITPWD_SUCCESS("200", "修改密码成功", 200),
    FILEUPLOAD_SUCCESS("200", "上传成功", 200),
    BAD_REQUEST("4001", "错误的请求参数", 400),
    NOLOGIN("4002", "未登陆", 401),
    REQUIRED_ERROR("4003", "有必填的参数未传入", 402),
    ACCESS_DENIED_EXCEPTION("4004", "拒绝访问，请联系管理员", 403),
    NOT_FOUND("4005", "找不到请求路径", 404),
    BOUND_STATEMENT_NOT_FOUNT("4006", "找不到方法", 404),
    METHOD_NOT_ALLOWED("4007", "不合法的请求方式", 405),
    NO_PERMISSION("4008", "非法请求", 405),
    ILLEGAL_ARGUMENT("4009", "参数不合法", 406),
    ERROR_IDCODE("4010", "验证码不正确", 406),
    BAD_REQUEST_TYPE("4011", "错误的请求类型", 415),
    TOKEN_INVALID("4012", "当前登录状态已过期", 401),
    KICK_OFF_BY_ANOTHER("4013", "当前账号已在另一地方登录，若不是本人操作，请注意账号安全", 401),
    SYSTEM_ERROR("5001", "系统异常", 500),
    DATABASE_ERROR("5002", "数据库异常", 500),
    CONNECTION_ERROR("5003", "网络连接请求失败", 503),
    NOT_MATCH("5004", "用户名和密码不匹配", 500),
    FAIL_GETDATA("5005", "获取信息失败", 500),
    FAIL_OPTION("5006", "操作失败", 500),
    REPEAT_REGISTER("5007", "重复注册", 500),
    NO_USER_EXIST("5008", "用户不存在", 500),
    INVALID_PASSWORD("5009", "密码错误", 500),
    REPEAT_MOBILE("5010", "已存在此手机号", 500),
    REPEAT_EMAIL("5011", "已存在此邮箱地址", 500),
    NO_RECORD("5012", "没有查到相关记录", 500),
    No_FileSELECT("5013", "未选择文件", 500),
    CERT_ERROR("5014", "系统授权异常", 500),
    WORKFLOW_ERROR("5015", "流程异常", 500),
    LOG_SAVE_ERROR("5016", "日志记录错误", 500),
    FEIGN_EMPTY_ERROR("5017", "未找到对应微服务", 500),
    DATASOURCE_ERROR("5018", "数据源异常", 500),
    SERVICE_INVOKE_ERROR("5019", "服务接口调用异常", 500),
    WEBSERVICE_PARSE_ERROR("5020", "Webservice接口解析异常", 500),
    BPM_PROCESS("5021", "流程定义异常，不允许有多个process", 500),
    DESENSITIZATION("5022", "表单数据脱敏设置异常", 500),
    TOO_MANY_RESULTS_EXCEPTION("5023", "期望返回一条(0条)数据", 500),
    MY_BATIS_SYSTEM_EXCEPTION("5024", "执行数据库语句异常", 500),
    EMPTY_FORM("5025", "表单未配置，请先配置表单", 500),
    REPEATABLE_COMMIT("5026", "请勿重复提交", 500),
    FORM_DATA_MODIFY("5027", "表单数据已经被别人修改,请重新进入", 500),
    REJECTED_EXECUTION_ERROR("5028", "服务器忙，请稍后再试。", 500),
    SQL_INJECT("5029", "参数含有非法字符", 500);

    private String code;
    private String message;
    private int httpCode;

    private ResponseErrorEnums(String code, String message, int httpCode) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
