package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BoardData extends BaseEntity{
	@Id
	private Long id; 		//게시글 번호

	@Column(nullable = false)
	private String subject;	//제목
	@Column(nullable = false) @Lob
	private String content;	//내용

	@ManyToOne @JoinColumn(name="userNo")
	private Member member;
}
