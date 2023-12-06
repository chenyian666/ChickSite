package org.yian.chicksiteblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yian.chicksiteblog.entity.UserDO;


public interface UserMapper extends BaseMapper<UserDO> {
    UserDO selectByUsername(String username);
}
