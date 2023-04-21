package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
//@Builder를 사용하고 싶은데, 생성자가 private인 경우
//편법으로 public화 할 수 있다.
public class Member extends BaseEntity {
	@Id @GeneratedValue
	private Long userNo;

	@Column(length = 45, nullable = false, unique = true)
	private String userId;
	@Column(length = 65, nullable = false)
	private String userPw;
	@Column(length = 45, nullable = false)
	private String userNm;
	@Column(length = 100)
	private String email;
	@Column(length = 11)
	private String mobile;

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	//BoardData의 멤버변수명을 기재,
	//관계의 주인을 명시한다. - 관계의 변경이 가능한 쪽이 주인
	//현재, BoardData에서 외래키로 userNo를 가지고 있기 때문에
	//BoardData가 관계의 주인이다.
	private List<BoardData> boardData = new ArrayList<>();

	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="addressID")
	private MemberAddress address;
}