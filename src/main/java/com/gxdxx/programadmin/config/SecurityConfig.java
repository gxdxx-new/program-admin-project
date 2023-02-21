package com.gxdxx.programadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/members/login", "/members/new").permitAll()
                .antMatchers("/members/**").hasAnyRole("SUPERADMIN", "ADMIN", "USER")
                .antMatchers("/superadmins/**", "/admins/**").hasRole("SUPERADMIN")
                .antMatchers("/admins/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("memberName")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {  // SecurityFilter를 거치지 않고 통과
        return (web) -> web.ignoring().antMatchers("bootstrap/css/**", "bttostrap/js/**", "bootstrap/img/**");
    }

}
