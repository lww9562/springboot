package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity @Data
public class BoardData {
	@Id @GeneratedValue
	private Long id;

	@Column(length = 100, nullable = false)
	private String subject;

	@Lob
	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userNo")
	@ToString.Exclude
	private Member member;
}
