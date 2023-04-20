package org.koreait.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.entities.Member;
import org.koreait.entities.QMember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

//JpaRepositroy<엔티티 클래스, 엔티티 기본키> 를 상속받는다.
public interface MemberRepository extends JpaRepository<Member, Long> , QuerydslPredicateExecutor {
	Member findByUserId(String userId);

	List<Member> findByUserIdNot(String userId);		// userId <> ...
	List<Member> findByUserNmContaining(String key); 	// userNm LIKE '%:key%'

	List<Member> findByRegDtBetween(LocalDate sDate, LocalDate eDate, Pageable pageable);

	List<Member> findByRegDtBetweenOrderByRegDtDesc(LocalDate sDate, LocalDate eDate);

	@Query("SELECT m FROM Member m WHERE m.userNm LIKE %:key% ORDER BY m.regDt DESC")
	//@Query의 Member는 테이블명이 아닌, 엔티티 명이다.
	List<Member> findByUsers(@Param("key") String keyword);

	default List<Member> findUsers(String keyword){
		QMember member = QMember.member;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(member.userNm.contains(keyword))
				.and(member.userId.notIn("user1", "user2"));

		List<Member> members = (List)findAll(builder);

		return members;
	}
}
