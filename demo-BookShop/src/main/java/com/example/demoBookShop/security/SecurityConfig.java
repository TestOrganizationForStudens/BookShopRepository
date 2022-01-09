package com.example.demoBookShop.security;

import com.example.demoBookShop.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtRequestFilter=jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()

                .antMatchers( "/api/user/add").permitAll()
                .antMatchers( "/api/user/login").permitAll()
                .antMatchers("/api/product/**").permitAll()
                .antMatchers( "/api/user/findUserByUserName?userName=vali").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                .antMatchers("/api/user/all").hasAnyAuthority( "ROLE_ADMIN")
                .antMatchers( "/api/product/add").hasAnyAuthority("ROLE_ADMIN")
               //.anyRequest().authenticated()
                .and().formLogin();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        http.csrf().disable();
//        //http.cors().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(GET, "api/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "api/user/add").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/product/**").permitAll();
//        //http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin();
       // http.addFilter(new CustomAuthentificationFilter(authenticationManagerBean()));
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

//    @Bean
//    public JavaMailSenderImpl mailSender() {
//        return new JavaMailSenderImpl();
//    }
}
