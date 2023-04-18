package org.koreait.commons;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class JSONResult<T> {
	private boolean success;
	private String message;		//에러 발생 시 메세지
	private HttpStatus status = HttpStatus.OK; //기본 응답 상태 코드 200
	private T data;				//성공 시 응답 데이터
}
