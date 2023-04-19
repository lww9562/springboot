package org.koreait.tests;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
//Hibernate는 @Transaction이 필수적
public class JPAExam01Test {
	@Autowired
	private EntityManager em;

	@Test
	@DisplayName("상태변화 감지")
	public void exam01(){
		Member member = new Member();
		member.setUserNo(1L);
		member.setUserId("user01");
		member.setUserPw("123456");
		member.setUserNm("사용자01");
		member.setRegDt(LocalDate.now());

		em.persist(member);
		em.flush();

		member.setUserNm("(수정)사용자01");
		em.flush();

		Member member2 = em.find(Member.class, 1L);
		System.out.println(member2);

		em.remove(member);
		em.flush();
	}
	@BeforeEach
	public void insertData(){
		Member member = new Member();
		member.setUserNo(2L);
		member.setUserId("user02");
		member.setUserPw("123456");
		member.setUserNm("사용자02");
		member.setRegDt(LocalDate.now());

		em.persist(member);
		em.flush();
		em.detach(member);
	}

	@Test
	public void exam02(){
		Member member = em.find(Member.class, 2L);	//Select 수행
		System.out.println(member);

		for(int i = 0; i < 10; i++)
			System.out.println();

		Member member2 = em.find(Member.class, 2L);	//영속성에서 조회
		System.out.println(member2);
	}

	@Test
	public void exam03(){
		Member member = new Member();
		member.setUserNo(1L);
		member.setUserId("user01");
		member.setUserPw("123456");
		member.setUserNm("사용자01");
		member.setRegDt(LocalDate.now());

		em.persist(member);
		em.flush();

		em.detach(member);	//비 영속 상태 - 상태 변화 감지 X

		for(int i = 0; i < 10; i++)
			System.out.println();

		member.setUserNm("(수정)사용자01");
		em.flush();

		System.out.println("merge Before");
		em.merge(member);
		System.out.println("merge After");
		em.flush();
	}
}