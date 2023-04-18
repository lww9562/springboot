package org.koreait.models.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
	@JsonIgnore
	private Long id;				//게시글 번호(Sequence)
	private String subject;			//게시글 제목
	private String content;			//게시글 내용

	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime regDt;	//등록 일시
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime modDt;	//수정 일시

	//조회수, 작성자, 분류 등 추가할 수도 있다.
}
