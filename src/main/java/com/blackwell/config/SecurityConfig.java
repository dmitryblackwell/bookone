package com.blackwell.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String DEFAULT_PASSWORD = "{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K";

	private static final String ROLE_USER = "USER";

	private static final String ROLE_ADMIN = "ADMIN";

	@Value("${mocks.enabled}")
	private boolean isMocksEnabled;
	
	private final DataSource dataSource;

	@Autowired(required = false)
	public SecurityConfig(@Nullable DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (isMocksEnabled) {
			User.UserBuilder users = User.builder();
			auth.inMemoryAuthentication()
					.withUser(users.username("herasim").password(DEFAULT_PASSWORD).roles(ROLE_USER))
					.withUser(users.username("mumu").password(DEFAULT_PASSWORD).roles(ROLE_USER, ROLE_ADMIN));
		} else {
			auth.jdbcAuthentication().dataSource(dataSource);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
        .antMatchers("/resources/**").permitAll()
		.antMatchers("/orders/**").hasRole(ROLE_ADMIN)
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
