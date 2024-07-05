package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/css/**").permitAll()  // Разрешаем доступ к CSS без аутентификации
				.antMatchers("/signup", "/h2-console/**").permitAll()  // Разрешаем доступ без аутентификации
				.antMatchers("/contact/**").authenticated() // Требуем аутентификацию для /contact/**
				.anyRequest().authenticated() // Требуем аутентификацию для всех остальных запросов
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/contact", true) // Перенаправляем на /contact после успешного входа
				.permitAll()
				.and()
				.logout() // Добавляем настройки для logout
				.logoutUrl("/logout") // URL для logout
				.logoutSuccessUrl("/login") // URL для перенаправления после logout
				.invalidateHttpSession(true) // Сбросить HttpSession после logout
				.deleteCookies("JSESSIONID") // Удалить cookies после logout
				.permitAll();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
