package org.yian.chicksiteblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security 配置类
 * <p>
 * author chenyian
 */
@Configuration
public class SecurityConfig{
    /**
     * 创建密码编译器实例
     * @return 密码编译器
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
