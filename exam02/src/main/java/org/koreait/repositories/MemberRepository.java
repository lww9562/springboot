package org.koreait.repositories;

import org.koreait.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepositroy<엔티티 클래스, 엔티티 기본키> 를 상속받는다.
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByUserId(String userId);
}
