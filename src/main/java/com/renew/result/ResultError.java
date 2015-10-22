package com.renew.result;

public enum ResultError
{
    RESULT_OK, // 成功
    RESULT_PARAM_ERR, // 参数错误
    RESULT_INNER_EXCEPTION, // 服务器内部抛出异常
    RESULT_EXECUTE_SQL_ERR, // 数据库异常
    RESULT_APP_NOT_EXIST,          //没有找到app
    RESULT_PASSWORD_ERROR,          //密码错误
    RESULT_DATA_FORMAT_ERR   //数据格式错误
}
