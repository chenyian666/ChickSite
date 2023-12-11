package org.yian.chicksiteblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色和资源关联表
 * @TableName sys_role_resource
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="sys_role_resource")
public class SysRoleResourceDO implements Serializable {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 资源ID
     */
    private Long resourceId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}