package org.koreait.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages.validations");
															//메세지를 가져온다
		HttpSession session = request.getSession();

		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		try{
			if(userId == null || userId.isBlank()){
				throw new LoginValidationException(bundle.getString("NotBlank.userId"), "userId");
			}

			if(userPw == null || userPw.isBlank()){
				throw new LoginValidationException(bundle.getString("NotBlank.userPw"), "userPw");
			}

			throw new LoginValidationException(bundle.getString("Validation.incorrect.login"), "global");

		} catch(LoginValidationException e){
			String field = e.getField();					// 어떤 항목에서 검증 실패했는지
			String message = e.getMessage();

			session.setAttribute("field", field);		//세션에 저장
			session.setAttribute("message", message);
		}

		session.setAttribute("userId", userId);
		session.setAttribute("userPw", userPw);

		String url = request.getContextPath() + "/user/login";
		response.sendRedirect(url);
	}
}
