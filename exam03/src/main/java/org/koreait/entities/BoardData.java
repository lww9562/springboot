package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
//@Builder를 사용하고 싶은데, 생성자가 private인 경우
//편법으로 public화 할 수 있다.
public class BoardData extends BaseEntity{
	@Id @GeneratedValue
	private Long id; 		//게시글 번호

	@Column(nullable = false)
	private String subject;	//제목
	@Column(nullable = false) @Lob
	private String content;	//내용

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userNo")
	private Member member;
}
