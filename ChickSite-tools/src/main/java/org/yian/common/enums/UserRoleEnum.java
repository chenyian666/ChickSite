package org.yian.common.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局用户权限枚举
 */
@AllArgsConstructor
@Getter
public enum UserRoleEnum {

    ADMIN(0, "admin"), // 管理员
    MEMBER(1, "member"), // 会员
    NORMAL(2, "normal"), // 普通用户
    VISITOR(3, "visitor"); // 游客

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static UserRoleEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getValue().equals(value), UserRoleEnum.values());
    }

}
