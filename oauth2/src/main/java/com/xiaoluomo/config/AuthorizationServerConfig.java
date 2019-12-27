package com.xiaoluomo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * 配置授权服务器
 * */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    /** 该对象用来支持password模式*/
    @Autowired
    AuthenticationManager authenticationManager;

    /** 该对象用来支持redis缓存,将令牌信息存储到redis里面*/
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**　该对象将为刷新token提供支持*/
    @Autowired
    UserDetailsService userDetailsService;


    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("password")
                /** 授权模式为 password token*/
                .authorizedGrantTypes("password","refresh_token")
                /** 配置过期时间*/
                .accessTokenValiditySeconds(1800)
                /** 配置资源 id*/
                .resourceIds("rid")

                .scopes("all")
                /** 配置加密后的密码*/
                .secret("$2a$10$wNxNRe0gJBTsLJtLAU0Mb.DCcSBlLbslMZY4JI2DW4MMtGjYsE8a2");
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         *
         * 配置令牌存储,authenticationManager,userDetailsService主要用于支持password模式以及令牌刷新
         *
         *
         * */
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        /**  支持client_id和client_secret做登陆认证 */
        security.allowFormAuthenticationForClients();
    }


}
