package com.blackwell.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${mocks.enabled}")
	private boolean isMocksEnabled;
	
	@Autowired(required = false)
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (isMocksEnabled) {
			// TODO rewrite it with new method
			User.UserBuilder users = User.withDefaultPasswordEncoder();

			// TODO hash passwords for mocks
			auth.inMemoryAuthentication()
					.withUser(users.username("herasim").password("fun123").roles("USER"))
					.withUser(users.username("mumu").password("fun123").roles("USER", "ADMIN"));
		} else {
			auth.jdbcAuthentication().dataSource(dataSource);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
        .antMatchers("/resources/**").permitAll()
		.antMatchers("/orders/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authentication")
			.permitAll()
		.and()
			.logout()
			.permitAll();
	}
}
