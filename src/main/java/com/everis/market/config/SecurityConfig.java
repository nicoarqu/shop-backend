package com.everis.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/", "/users/new", "/users/create", "/users/login").permitAll()
			.anyRequest().authenticated()
				.and()
		.formLogin().loginPage("/users/signin").permitAll()
			.defaultSuccessUrl("/products")
			.loginProcessingUrl("/users/login")
			.usernameParameter("email").passwordParameter("password")
				.and()
		.logout().permitAll();
	}

}
