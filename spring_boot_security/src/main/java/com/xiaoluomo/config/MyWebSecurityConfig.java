package com.xiaoluomo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义权限
 */


@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*构建密码*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加用户并增加权限
        auth.inMemoryAuthentication()
                .withUser("root").password("$2a$10$wNxNRe0gJBTsLJtLAU0Mb.DCcSBlLbslMZY4JI2DW4MMtGjYsE8a2")
                .roles("ADMIN", "DBA")
                .and()
                .withUser("admin").password("$2a$10$wNxNRe0gJBTsLJtLAU0Mb.DCcSBlLbslMZY4JI2DW4MMtGjYsE8a2")
                .roles("ADMIN", "USER")
                .and()
                .withUser("sang").password("$2a$10$wNxNRe0gJBTsLJtLAU0Mb.DCcSBlLbslMZY4JI2DW4MMtGjYsE8a2")
                .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            /*http.authorizeRequests()
                    /** /admin/ url下要具备admin权限*/
              /*      .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    /** /user/ url下需要具备admin和user权限*/
                /*    .antMatchers("/user/**")
                    .access("hasAnyRole('ADMIN','USER')")
                    /** /db/url 要具备admin和dba权限 */
                  /*  .antMatchers("/db/**")
                    .access("hasRole('ADMIN') and hasRole('DBA')")
                    /**除了前面定义url外,用户访问其它url都需要认证登陆*/
                    /*.anyRequest()
                    .authenticated()
                    /** 表示启用表单登陆*/
                    /*.and()
                    .formLogin()
                    .loginProcessingUrl("/login");

                     */


        //自定义登陆表单配置
        http.authorizeRequests()
                /** /admin/ url下要具备admin权限*/
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                /** /user/ url下需要具备admin和user权限*/
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN','USER')")
                /** /db/url 要具备admin和dba权限 */
                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA')")
                /**除了前面定义url外,用户访问其它url都需要认证登陆*/
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //.loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("passwd")
                .successHandler(new AuthenticationSuccessHandler() {

                    //定义登陆成功后处理的逻辑

                    @Override
                    public void onAuthenticationSuccess(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Authentication authentication) throws IOException, ServletException {
                        Object principal = authentication.getPrincipal();
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(200);
                        Map<String, Object> map = new HashMap<>();

                        // response.sendRedirect("/hello"); 登陆成功后可以跳转

                        map.put("status", 200);
                        map.put("msg", principal);
                        //转为json字符串
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();


                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            AuthenticationException e) throws IOException, ServletException {

                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(401);
                        Map<String, Object> map = new HashMap<>();
                        if (e instanceof LockedException) {
                            map.put("msg", "账号被锁定,登录失败");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "账号密码错误输入错误");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账户已过期");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "密码已过期");
                        } else {
                            map.put("msg", "登陆失败");
                        }

                        System.out.println("失败");

                        ObjectMapper mapper = new ObjectMapper();
                        out.write(mapper.writeValueAsString(map));
                        out.flush();
                        out.close();

                    }
                })
                .and()
                .logout()
                //.logoutUrl()
                .clearAuthentication(true) //清除身份认证信息
                .invalidateHttpSession(true) //使session失效
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                            System.out.println("退出清理工作");
                    }
                }).logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                request.getSession().invalidate();
                System.out.println("退出成功");
            }
        })
                .permitAll()
                .and()
                .csrf()
                .disable();


    }
}
