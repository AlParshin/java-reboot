package ru.sberbank.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users").permitAll()
                .antMatchers("/users/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("admin")
                .password(passwordEncoder.encode("123456"))
                .authorities("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;

    }


}
