package org.koreait.tests;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class JPAExam02Test {
	@Autowired
	private EntityManager em;

	@Test
	@DisplayName("관계 매핑 연습1")
	void test1() {
		Member member = new Member();
		member.setUserId("user01");
		member.setUserPw("123456");
		member.setUserNm("사용자01");

		em.persist(member);
		em.flush();

		BoardData boardData = new BoardData();
		boardData.setSubject("제목1");
		boardData.setContent("내용1");
		boardData.setMember(member);

		em.persist(boardData);
		em.flush();
		em.detach(boardData);

		BoardData boardData2 = em.find(BoardData.class, boardData.getId());
		System.out.println(boardData2);
	}
}
