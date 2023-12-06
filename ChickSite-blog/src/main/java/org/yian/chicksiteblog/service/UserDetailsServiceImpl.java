package org.yian.chicksiteblog.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yian.chicksiteblog.dao.UserMapper;
import org.yian.chicksiteblog.entity.UserDO;

/**
 * UserDetailsService实现类
 *
 * author chenyian
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDO userDO = userMapper.selectByUsername(username);
//        if (userDO == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//        String password = userDO.getPassword();
//        passwordEncoder.matches(password,"123" );
        String password = passwordEncoder.encode("123");
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
