package com.kt.csai.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kt.csai.global.security.custom.CustomAuthenticationProvider;
import com.kt.csai.global.security.jwt.JwtAccessDeniedHandler;
import com.kt.csai.global.security.jwt.JwtAuthenticationEntryPoint;
import com.kt.csai.global.security.jwt.JwtLogoutHandler;
import com.kt.csai.global.security.jwt.JwtRequestFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final JwtRequestFilter jwtRequestFilter;
	private final JwtLogoutHandler jwtLogoutHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new CustomAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/auth/**", "/ws/**", "/**").permitAll() // 허용할 api uri
//		.antMatchers("/admin").hasRole("ADMIN") // 권한별 접근 처리
//		.antMatchers("/manager").hasRole("MANAGER")
//		.antMatchers("/user").hasRole("USER")
				.anyRequest().authenticated();

		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);

		//http.formLogin().loginPage("http://10.217.182.199:8080/login").defaultSuccessUrl("http://10.217.182.199:8080/");
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http.logout().logoutUrl("/auth/signout").logoutSuccessHandler(jwtLogoutHandler);
	}
}