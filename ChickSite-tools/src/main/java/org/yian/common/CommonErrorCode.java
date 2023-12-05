package org.yian.common;


import lombok.Data;
import org.yian.common.enums.GlobalErrorCodeConstants;
import org.yian.common.enums.ServiceErrorCodeRange;

/**
 * 错误码对象
 *
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCodeConstants}
 * 业务异常错误码，占用 [1 000 000 000, +∞)，参见 {@link ServiceErrorCodeRange}
 *
 * TODO 错误码设计成对象的原因，为未来的 i18 国际化做准备
 */
@Data
public class CommonErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public CommonErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}