package com.ykq.security.sevice.impl;

import com.ykq.security.sevice.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @author: ykq
 * @date: 2020/10/25 12:01
 */
@Service
public class UserServiceImpl implements UserService {
    /***
     * 功能描述: 实现自定义认证流程
     * @author ykq
     * @date 2020/10/25 12:01
     * @param
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!"2".equals(s)) {
            return null;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ROOT");   // 加个角色
        authorities.add(authority);
        // 模拟数据库中查出的信息(输入信息要与之比对)
//        UserDetails user = new User(s, "$2a$10$FyefHbUvs17AvEqR5Z3Hb.MCNmf74U1X8ttSP/h3nJucqpQsvJMEm", authorities);
        UserDetails user = new User(s,
                "$2a$10$FyefHbUvs17AvEqR5Z3Hb.MCNmf74U1X8ttSP/h3nJucqpQsvJMEm",
                true,
                true,
                true,
                true,
                authorities);
        return user;
    }
}
