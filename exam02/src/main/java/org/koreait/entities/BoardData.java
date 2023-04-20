package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class BoardData {
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;		// 게시글 번호

	@Column(length = 150, nullable = false)
	private String subject;	// 게시글 제목

	@Column(nullable = false) @Lob
	private String content;	// 게시글 내용

	@CreationTimestamp @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime regDt;
	@UpdateTimestamp @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modDt;

	@ManyToOne @JoinColumn(name = "userNo")
	private Member member;
}