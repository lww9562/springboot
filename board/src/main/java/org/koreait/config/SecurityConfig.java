package org.koreait.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.koreait.models.user.LoginFailureHandler;
import org.koreait.models.user.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/user/login")			// 로그인 페이지 URL
				//.defaultSuccessUrl("/")				// 성공시 이동할 URL
				.successHandler(new LoginSuccessHandler())
				.usernameParameter("userId")
				.passwordParameter("userPw")
				.failureHandler(new LoginFailureHandler())
				//.failureForwardUrl("/user/login")	// 로그인 실패시 이동할 URL
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/user/login");	// 로그아웃 성공시 이동할 URL

		http.authorizeHttpRequests()
				.requestMatchers("/mypage/**").authenticated()
									//로그인한 회원만 가능한 URL 패턴
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
									//권한이 ADMIN인 사용자만 접근 가능한 URL 패턴
				.anyRequest().permitAll();


		//관리자 접근 권한 없을 시 → "접근 권한이 없습니다!" 메세지와 상태코드 401 - UnAuthorized
		//비회원 접근 권한 없을 시 → 로그인 페이지로 이동
		http.exceptionHandling()
				.authenticationEntryPoint((req, res, e) -> {
					String redirectUrl = "/user/login";

					String URI = req.getRequestURI();
					if (URI.indexOf("/admin") != -1) {	//관리자 페이지
						redirectUrl = "/error/401";
					}

					res.sendRedirect(redirectUrl);
				});
						//관리자 페이지, 사용자 비회원 등 다르게 구현
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){
		return web -> web.ignoring()
				.requestMatchers("/css/**", "/js/**", "/images/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
