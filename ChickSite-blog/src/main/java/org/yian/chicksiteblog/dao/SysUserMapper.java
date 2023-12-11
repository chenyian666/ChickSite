package org.yian.chicksiteblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yian.chicksiteblog.entity.SysUserDO;

/**
* @author chenjianji
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2023-12-07 15:25:30
* @Entity org.yian.chicksiteblog.entity.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUserDO> {
    SysUserDO selectByUsername(String username);
}




