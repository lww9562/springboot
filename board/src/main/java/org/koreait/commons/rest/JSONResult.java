package org.koreait.commons.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JSONResult<T> {
	private boolean success;
	private T data;					// 성공 시 데이터
	private String errorMessage;	// 실패 시 메세지
}
