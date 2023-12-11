package org.yian.chicksiteblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户和角色关联表
 * @TableName sys_user_role
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="sys_user_role")
public class SysUserRoleDO implements Serializable {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}