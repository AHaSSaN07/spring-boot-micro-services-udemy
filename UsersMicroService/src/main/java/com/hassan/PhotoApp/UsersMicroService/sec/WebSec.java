package com.hassan.PhotoApp.UsersMicroService.sec;

import com.hassan.PhotoApp.UsersMicroService.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSec extends WebSecurityConfigurerAdapter {
    UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment environment;

    @Autowired
    WebSec(UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder,Environment environment){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll().and().addFilter(getAuthFilter());
        http.headers().frameOptions().disable();
    }
    private AuthFilter getAuthFilter() throws Exception{
        AuthFilter filter = new AuthFilter(this.userService,this.environment,authenticationManager());
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
