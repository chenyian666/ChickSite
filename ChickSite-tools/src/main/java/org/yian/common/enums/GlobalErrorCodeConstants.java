package org.yian.common.enums;

import org.yian.common.CommonErrorCode;

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 *
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 * 比较特殊的是，因为之前一直使用 0 作为成功，就不使用 200 啦。
 *
 * @author 芋道源码
 */
public interface GlobalErrorCodeConstants {

    CommonErrorCode SUCCESS = new CommonErrorCode(200, "成功");

    // ========== 客户端错误段 ==========

    CommonErrorCode BAD_REQUEST = new CommonErrorCode(400, "请求参数不正确");
    CommonErrorCode UNAUTHORIZED = new CommonErrorCode(401, "账号未登录");
    CommonErrorCode FORBIDDEN = new CommonErrorCode(403, "没有该操作权限");
    CommonErrorCode NOT_FOUND = new CommonErrorCode(404, "请求未找到");
    CommonErrorCode METHOD_NOT_ALLOWED = new CommonErrorCode(405, "请求方法不正确");
    CommonErrorCode LOCKED = new CommonErrorCode(423, "请求失败，请稍后重试"); // 并发请求，不允许
    CommonErrorCode TOO_MANY_REQUESTS = new CommonErrorCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    CommonErrorCode INTERNAL_SERVER_ERROR = new CommonErrorCode(500, "系统异常");
    CommonErrorCode NOT_IMPLEMENTED = new CommonErrorCode(501, "功能未实现/未开启");

    // ========== 自定义错误段 ==========
    CommonErrorCode REPEATED_REQUESTS = new CommonErrorCode(900, "重复请求，请稍后重试"); // 重复请求
    CommonErrorCode DEMO_DENY = new CommonErrorCode(901, "演示模式，禁止写操作");

    CommonErrorCode UNKNOWN = new CommonErrorCode(999, "未知错误");

    /**
     * 是否为服务端错误，参考 HTTP 5XX 错误码段
     *
     * @param code 错误码
     * @return 是否
     */
   static boolean isServerSystemErrorCode(Integer code) {
       return code != null
               && code >= INTERNAL_SERVER_ERROR.getCode() && code <= INTERNAL_SERVER_ERROR.getCode() + 99;
   }

}