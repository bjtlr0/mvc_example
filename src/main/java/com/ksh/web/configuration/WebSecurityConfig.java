package com.ksh.web.configuration;

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

import com.ksh.database.service.MemberService;
import com.ksh.database.vo.MemberRole;
import com.ksh.web.component.MemberAuthenticationProvider;
import com.ksh.web.handler.LoginFailureHandler;
import com.ksh.web.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MemberService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberAuthenticationProvider memberAuthenticationProvider;
	
	@Bean
	protected PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(memberAuthenticationProvider)
		.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
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
			.antMatchers("/manager/**").hasRole("MANAGER") // SpringSecurity hasRole함수는 prefix로 ROLE_ 을 자동으로 붙여준다.
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/").permitAll() // default Index페이지로 리다이렉트 된다.
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginProcessingUrl("/login.do")
			.usernameParameter("q-user-name")
			.passwordParameter("q-user-password")
			.loginPage("/loginPage/login").permitAll()
//			.defaultSuccessUrl("/loginPage/default")
//			.failureUrl("/loginPage?error")
			.successHandler(new LoginSuccessHandler())
			.failureHandler(new LoginFailureHandler())
		.and()
			.logout().logoutSuccessUrl("/loginPage?logout");
	}
	
}
