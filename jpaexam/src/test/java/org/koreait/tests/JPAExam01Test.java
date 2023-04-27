package org.koreait.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class JPAExam01Test {
	@Autowired
	private EntityManager em;



	@Test
	@DisplayName("엔티티 테이블 생성 테스트")
	void test01() {
		Member member = new Member();
		member.setUserNo(1L);
		member.setUserId("user01");
		member.setUserPw("123456");
		member.setUserNm("사용자01");

		em.persist(member);    // 상태 변화 감지 → member : 영속성 관리 상태
		em.flush();            // DB에 상태 변경 사항 반영 → insert 쿼리 수행

		em.detach(member);    // 상태 변화 감지 X → member : 영속성 상태 분리

		member.setUserNm("(수정)사용자01");    // 상태 변화
		em.flush();                            // update 쿼리 수행

		em.merge(member);    // 분리 상태 → 영속성 관리 상태(상태 변화 감지)

		Member member2 = em.find(Member.class, member.getUserNo());
		System.out.println(member2);

		//em.remove(member);
		//em.flush();							// delete 쿼리 수행

		String sql = "SELECT m FROM Member m WHERE userNo = :userNo";
		TypedQuery<Member> typedQuery = em.createQuery(sql, Member.class);

		typedQuery.setParameter("userNo", member.getUserNo());

		List<Member> members = typedQuery.getResultList();

		members.get(0).setUserNm("(수정2)사용자01");
		em.flush();
	}
}
