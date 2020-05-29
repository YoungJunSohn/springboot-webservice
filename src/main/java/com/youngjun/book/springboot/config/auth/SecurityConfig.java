package com.youngjun.book.springboot.config.auth;

import com.youngjun.book.springboot.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화 시켜주는 annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable()//h2-console 화면을 사용하기 위해 보안설정 disable
                .and().authorizeRequests() //URL 별로 권한관리 설정을 하는 옵션 시작점(선언후 antMatcher 사용가능)
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**","/profile")
                .permitAll()
                .antMatchers("/api/v1/**")
                .hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/") //로그아웃시 해당 주소 "/" 로 이동
                .and().oauth2Login().userInfoEndpoint()
                .userService(customOAuth2UserService);
    }//configure
}//SecurityConfig
