package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.constants.MemberType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users", indexes={
		@Index(name="idx_userNm", columnList="userNm"),
		@Index(name="idx_regDt_desc", columnList="regDt DESC")
})
public class Member {
	@Id	//기본키
	@GeneratedValue(strategy = GenerationType.TABLE)
	//플랫폼마다 다른 증감번호 생성방식을 자동으로 맞춰준다. 기본값 : AUTO
	private Long userNo;

	//제약조건 등을 간단히 정의만 함으로써 구현할 수 있다.
	@Column(length=45, unique = true, nullable = false)
	private String userId;
	@Column(length=65, nullable = false)
	private String userPw;
	@Column(length=40, nullable = false)
	private String userNm;

	//@Transient //해당 컬럼을 제외하고 테이블을 만든다.
	@Column(name="cellPhone", length=11)
	private String mobile;

	@Enumerated(EnumType.STRING)
	@Column(length=40)
	private MemberType type;

	@Temporal(TemporalType.DATE) // 형식 지정 - DB에 들어갈 때 날짜만 들어간다
	@CreationTimestamp //insert 시 알아서 현재 시간이 들어간다.
	private LocalDate regDt;

	@Temporal(TemporalType.DATE)
	@UpdateTimestamp //update 시 알아서 현재 시간이 들어간다.
	private LocalDate modDt;
}
