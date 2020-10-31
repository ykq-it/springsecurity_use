package com.ykq.security.config;

import com.ykq.security.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 功能描述：
 *
 * @author: ykq
 * @date: 2020/10/19 23:58
 */
@Configuration
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("2").password("{noop}123").authorities("ADMIN");

//        auth.userDetailsService(userService);

        // passwordEncoder的目的是将页面中传入进来的明文密码加密，为了与保存的密码信息做匹配
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()    // 设置哪些页面可以直接访问，哪些需要验证
            .antMatchers("/login.html", "/error.html").permitAll()  // 这些直接放过
            .anyRequest().authenticated()   //  剩下的所有地址都需要在认证状态下才可以访问
            .and()  // 再次获取http对象
                .formLogin()    //  添加一个formLoginConfigure过滤器
                .loginPage("/login.html")   // 指定自定义的登录页面
                .loginProcessingUrl("/login.do")    // 处理认证逻辑的请求的URL,"/login.do"是页面中的action， <form th:action="@{/login.do}" method="post">
                .defaultSuccessUrl("/home.html")
                .failureForwardUrl("/error.html")
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
            .and().csrf().disable();    // 关闭跨域攻击的预防
    }

    // 如果使用加密的密码，这里必须向容器中注册一个BCryptPasswordEncoder加解密的编码器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
        System.out.println(encoder.encode("123"));
    }
}
