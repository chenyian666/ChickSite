package org.yian.chicksiteblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.yian.chicksiteblog.handler.AuthzSuccessHandler;
import org.yian.common.enums.UserRoleEnum;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * spring security 配置类
 * <p>
 * author chenyian
 */
@Configuration
public class SecurityConfig {
    /**
     * 创建密码编译器实例
     *
     * @return 密码编译器
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全过滤器链
     *
     * @param httpSecurity http安全配置
     * @return 安全过滤器链
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 关闭csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // 授权配置
        httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/login.html").permitAll()
                        .requestMatchers("/main.html").hasRole(UserRoleEnum.ADMIN.getName())
                        // 所有请求都需要认证
                        .anyRequest().authenticated())
                // 表单登录
                .formLogin((formLogin) ->
                        formLogin
                                // 自定义登录页面
                                .loginPage("/login.html")
                                .loginProcessingUrl("/user/login")
                                .defaultSuccessUrl("/main.html")
                                // 自定义登录成功处理器
                                .successHandler(new AuthzSuccessHandler())
                )
                // 注销
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login.html"))
                .httpBasic(withDefaults());
        return httpSecurity.build();
    }

    /**
     * 忽略静态资源和页面
     *
     * @return 忽略静态资源
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore.html");
    }
}
