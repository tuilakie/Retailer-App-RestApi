package com.ntneik15.selflearning.retailerapp.config;

import com.ntneik15.selflearning.retailerapp.repository.IUserRepository;
import com.ntneik15.selflearning.retailerapp.security.jwt.JwtEntryPoint;
import com.ntneik15.selflearning.retailerapp.security.jwt.JwtProvider;
import com.ntneik15.selflearning.retailerapp.security.jwt.JwtTokenFilter;
import com.ntneik15.selflearning.retailerapp.security.userprinciple.UserPrincipleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Autowired
    private UserPrincipleService userPrincipleService;
    @Autowired
    IUserRepository userRepository;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtProvider,userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userPrincipleService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll();
//                .requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated();
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}