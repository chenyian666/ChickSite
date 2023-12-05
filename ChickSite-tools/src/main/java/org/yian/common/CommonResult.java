package org.yian.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.Assert;
import org.yian.common.enums.GlobalErrorCodeConstants;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回
 *
 * @param <T> 数据泛型
 */
@Data
public class CommonResult<T> implements Serializable {

    /**
     * 错误码
     *
     * @see CommonErrorCode#getCode()
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     *
     * @see CommonErrorCode#getMsg() ()
     */
    private String msg;
    /**
     * 详情提示，作为可选的消息参数
     */
    private String detailedMsg;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result, String detailedMsg) {
        return error(result.getCode(), result.getMsg(), detailedMsg);
    }

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMsg(), "");
    }
    public static <T> CommonResult<T> error(Integer code, String message, String detailedMessage) {
        Assert.isTrue(!Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode()), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = message;
        result.detailedMsg = detailedMessage; // 新增的字段
        return result;
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode()), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = message;
        result.detailedMsg = ""; // 新增的字段
        return result;
    }

    public static <T> CommonResult<T> error(CommonErrorCode errorCode, String detailedMsg) {
        return error(errorCode.getCode(), errorCode.getMsg(), detailedMsg);
    }
    public static <T> CommonResult<T> error(CommonErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg(), "");
    }

    /**
     * 成功
     *
     */
    public static <T> CommonResult<T> success(T data,String successMessage) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = successMessage;
        return result;
    }
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = "";
        return result;
    }
    public static <T> CommonResult<T> successNoData(String successMessage) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.msg = successMessage;
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    public static <T> CommonResult<T> error(ServiceException serviceException,String detailedMsg) {
        return error(serviceException.getCode(), serviceException.getMessage(),detailedMsg);
    }

    public static <T> CommonResult<T> error(ServiceException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage(),"");
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    // ========= 和 Exception 异常体系集成 =========

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     */
    @SuppressWarnings("java:S1120")
    public void checkError() throws ServiceException {
        if (isSuccess()) {
            return;
        }
        // 服务端异常
        if (GlobalErrorCodeConstants.isServerSystemErrorCode(code)) {
            throw new ServerException(code, msg);
        }
        // 业务异常
        throw new ServiceException(code, msg);
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     * 如果没有，则返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return data;
    }

}
