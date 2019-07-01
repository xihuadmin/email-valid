package com.zjzc.manage.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfig
 * 权限拦截
 * @author jlicc
 * @create 2017-03-20-11:28
 **/
@Slf4j
@Configuration
@EnableWebSecurity //开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { //设置一些web安全的细节

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USERS = "USERS";
    private static final String USER_ADMIN = "admin";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css*//**", "/js*//**","/images*//**","/assest*//**","/assets*//**","/small/api/**")
                .permitAll()//不需要任何认证就可以访问，其他的路径都必须通过身份验证。
                .antMatchers("/system*//**","/job*//**").hasRole(ROLE_ADMIN)//system路径下只能admin角色访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//定义当需要用户登录时候，转到的登录页面。
                .successForwardUrl("/loginDeail")//登录成功跳转处理
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    //在内存中创建了一个用户，用户角色为USER。
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hzed").password("123456").roles(ROLE_USERS);
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles(ROLE_ADMIN);
        auth.inMemoryAuthentication().withUser("test").password("123456").roles(ROLE_USERS);
    }
}
