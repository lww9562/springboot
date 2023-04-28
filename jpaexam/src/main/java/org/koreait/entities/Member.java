package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Member extends BaseEntity{
	@Id @GeneratedValue
	private Long userNo;

	@Column(length = 40, unique = true, nullable = false)
	private String userId;

	@Column(length = 65, nullable = false)
	private String userPw;

	@Column(length = 40, nullable = false)
	private String userNm;

	@OneToMany(mappedBy = "member")	//연관 관계의 주인을 반드시 명시해야한다
	//외래키를 가지고 있는 Many쪽이 주인!
	private List<BoardData> boardDatas = new ArrayList<>();
/*
	@ManyToOne
	@JoinColumn(name = "managerNo")
	private Member member;

	@ManyToOne
	@JoinColumn(name="adminNo")
	private Member admin;
 */
	@OneToOne
	@JoinColumn(name="addressId")
	@ToString.Exclude
	private Address address;
}
