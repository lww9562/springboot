package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Data
public class BoardData extends BaseUserEntity {
	@Id @GeneratedValue
	private Long id;		//게시글 번호

	@Column(nullable = false)
	private String subject;	//게시글 제목
	@Lob
	@Column(nullable = false)
	private String content;	//게시글 내용
}
