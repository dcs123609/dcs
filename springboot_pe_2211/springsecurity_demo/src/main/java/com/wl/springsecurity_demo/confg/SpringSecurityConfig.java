package com.wl.springsecurity_demo.confg;

import com.wl.springsecurity_demo.confg.handler.AuthenticationEntryPointHandler;
import com.wl.springsecurity_demo.confg.handler.SecurityAccessDeniedHandler;
import com.wl.springsecurity_demo.confg.handler.SecurityAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * SpringSecurity 配置类
 */

@EnableWebSecurity//自动开启WebSecurity
//开启全局的security方法注解    prePostEnabled = true权限验证  securedEnabled = true角色验证
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private AuthenticationEntryPointHandler securityAuthenticationEntryPoint;
    @Resource
    private SecurityAccessDeniedHandler securityAccessDeniedHandler;
    @Resource
    private SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;

    ////1、配置密码加密的方式 交给spring容器管理
    @Bean
    public PasswordEncoder passwordEncoder() {
        //不可逆的   123  djkfdjkfjd
        return new BCryptPasswordEncoder();
    }


    //进行账号密码登录
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //配置


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//跨域
                .formLogin()//表单登录
                .loginPage("/login.html")//指定登录页面
                .loginProcessingUrl("/login")////指定登录请求
                .defaultSuccessUrl("/index.html").permitAll()//登录成功跳转的页面
                .failureHandler(securityAuthenticationFailureHandler)//登录失败 的处理器
                .and() //其他的拦截配置
                .authorizeRequests() //授权所有请求
                .antMatchers("/login.html","/login").permitAll()//放行登录相关的请求
                .antMatchers("/js/**", "/css/**", "/images/**", "/favicon.ico").permitAll()//放行静态资源
                .anyRequest()//其他请求
                .authenticated();//验证
        http.exceptionHandling()
                .authenticationEntryPoint(securityAuthenticationEntryPoint)//匿名访问处理器
                .accessDeniedHandler(securityAccessDeniedHandler);//没有权限访问的处理器
        //退出登录
        http.logout()
                .logoutUrl("/logout")//退出的请求
                .logoutSuccessUrl("/login.html"); //退出成功的跳转页面

    }
}
