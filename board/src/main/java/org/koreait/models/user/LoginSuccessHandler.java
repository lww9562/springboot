package org.koreait.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 성공시 유입되는 핸들러

		// 1. 실패시 저장된 세션 지우기
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userPw");
		session.removeAttribute("field");
		session.removeAttribute("message");

		// 2. 아이디 저장 - 쿠키 저장
		String saveId = request.getParameter("saveId");
		String userId = request.getParameter("userId");
		Cookie cookie = new Cookie("saveId", userId);
		if(saveId == null){	// 쿠키 삭제
			cookie.setMaxAge(0);
		} else {			// 쿠키 등록
			cookie.setMaxAge(60*60*24*365);
		}
		response.addCookie(cookie);

		// 3. 성공시 이동할 URL
		String url = request.getContextPath();
		response.sendRedirect(url);
	}
}
