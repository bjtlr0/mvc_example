package com.ksh.config.web;

import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ksh.handler.LoginFailureHandler;
import com.ksh.handler.LoginSuccessHandler;
import com.ksh.service.MemberService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	// springsecurity
	@Autowired
	MemberService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	protected PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		// 이후 security를 적용하지 않을 경로를 지정
		// web.ignoring().antMatchers("/css/**", "/js/**", "/image/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/").permitAll() // default Index페이지로 리다이렉트 된다.
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.usernameParameter("q-user-name")
			.passwordParameter("q-user-password")
			.loginPage("/loginPage/customLogin").permitAll()
//			.defaultSuccessUrl("/loginPage/default")
//			.failureUrl("/loginPage?error")
			.loginProcessingUrl("/login.do")
			.successHandler(new LoginSuccessHandler())
			.failureHandler(new LoginFailureHandler())
		.and()
			.logout().logoutSuccessUrl("/loginPage?logout");
	}
	
}
