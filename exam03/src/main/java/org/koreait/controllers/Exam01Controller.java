package org.koreait.controllers;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.entities.MemberAddress;
import org.koreait.entities.QBoardData;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberAddressRepository;
import org.koreait.repositories.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exam01")
@RequiredArgsConstructor
@Log
public class Exam01Controller {
	private final MemberRepository memberRepository;
	private final BoardDataRepository boardDataRepository;
	private final MemberAddressRepository memberAddressRepository;
	private final EntityManager em;

	@GetMapping("/ex01")
	public void ex01(){
		Member member = Member.builder()
				.userId("user01")
				.userPw("123456")
				.userNm("사용자01")
				.mobile("01000000000")
				.email("user01@user.org")
				.build();

		member = memberRepository.saveAndFlush(member);
		//저장은 새롭게 생성된 member 객체이며,
		//영속성 상태의 member가 반환되어 저장된다.

		List<BoardData> datas = new ArrayList<>();
		for(int i = 1; i <= 10; i++){
			BoardData data = BoardData.builder()
					.subject("제목" + i)
					.content("내용" + i)
					.member(member)	//다대일 맵핑, 외래키, "userNo"
					.build();
			datas.add(data);
		}
		boardDataRepository.saveAllAndFlush(datas);
	}

	@GetMapping("/ex02")
	public void ex02(){
		BoardData data = boardDataRepository.findById(1L).orElse(null);//BoardData 엔티티만 조회
		//log.info(data.toString());
		Member member = data.getMember();
		String userId = member.getUserId(); //쿼리 실행
		log.info(userId);
		//log.info(member.toString());
	}

	@GetMapping("/ex03")
	public void ex03(){
		Member member = memberRepository.findById(1L).orElse(null);
		List<BoardData> boardDatas = member.getBoardData();

		boardDatas.stream().forEach(System.out::println);
	}

	@GetMapping("/ex04")
	public void ex04(){
		List<BoardData> boardDatas = boardDataRepository.findAll();
		for(BoardData boardData : boardDatas){
			Member member = boardData.getMember();
			String userId = member.getUserId();
			String userNm = member.getUserNm();
			log.info("userId = " + userId + ", userNm = " + userNm);
		}
	}

	@GetMapping("/ex05")
	public void ex05(){
		List<BoardData> boardDatas = boardDataRepository.findBoardDatas();
	}

	@GetMapping("/ex06")
	@Transactional
	public void ex06(){
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		QBoardData boardData = QBoardData.boardData;
		JPAQuery<BoardData> query = jpaQueryFactory.selectFrom(boardData)
				.leftJoin(boardData.member)
				.orderBy(boardData.regDt.desc())
				.fetchJoin();

		List<BoardData> datas = query.fetch();
		datas.stream().forEach(System.out::println);
	}

	@GetMapping("/ex07")
	public void ex07(){
		MemberAddress address = MemberAddress.builder()
				.addr1("주소1")
				.addr2("주소2")
				.zipCode("10000")
				.build();

		address = memberAddressRepository.saveAndFlush(address);

		Member member = Member.builder()
				.userId("user02")
				.userPw("123456")
				.userNm("사용자02")
				.email("user02@user.org")
				.mobile("01000000000")
				.address(address)
				.build();

		member = memberRepository.saveAndFlush(member);
	}

	@GetMapping("/ex08")
	public void ex08(){
		Member member = memberRepository.findById(2L).orElse(null);
		MemberAddress address = member.getAddress();

		log.info(address.toString());
	}

	@GetMapping("/ex09")
	public void ex09(){
		MemberAddress address = memberAddressRepository.findById(1L).orElse(null);
		Member member = address.getMember();

		log.info(member.toString());
	}

	@GetMapping("/ex10")
	public void ex10(){
		//부모를 삭제!
		Member member = memberRepository.findById(1L).orElse(null);
		memberRepository.delete(member);
		memberRepository.flush();
	}
}
