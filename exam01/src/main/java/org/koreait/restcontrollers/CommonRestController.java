package org.koreait.restcontrollers;

import org.koreait.commons.CommonException;
import org.koreait.commons.JSONResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//공통 기능이 수행될 패키지 설정
@RestControllerAdvice("org.koreait.restcontrollers")
public class CommonRestController {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JSONResult<Object>> errorHandler(Exception e){
		//e → CommonException 클래스로부터 만들어진 객체이면,
		//상태코드 getStatus()
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500- 기본 응답 코드

		if(e instanceof CommonException){
			CommonException commonException = (CommonException) e;
			status = commonException.getStatus();
		}

		JSONResult<Object> jsonResult = new JSONResult<>();
		jsonResult.setSuccess(false);
		jsonResult.setMessage(e.getMessage());
		jsonResult.setStatus(status);

		return ResponseEntity.status(status).body(jsonResult);
	}
}