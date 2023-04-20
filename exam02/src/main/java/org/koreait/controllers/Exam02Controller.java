package org.koreait.controllers;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.entities.QMember;
import org.koreait.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/exam02")
@RequiredArgsConstructor
public class Exam02Controller {
	private final MemberRepository repository;

	@GetMapping("/ex01")
	public List<Member> ex01(){
		List<Member> members = repository.findByUserIdNot("user2");
		return members;
	}

	@GetMapping("/ex02")
	public List<Member> ex02(){
		List<Member> members = repository.findByUserNmContaining("자");
		return members;
	}

	@GetMapping("/ex03")
	public List<Member> ex03(){
		LocalDate today = LocalDate.now();
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("regDt"), Sort.Order.asc("userId")));


		List<Member> members = repository.findByRegDtBetween(today.minusDays(3), today.plusDays(1), pageable);
		return members;
	}

	@GetMapping("/ex04")
	public List<Member> ex04(){
		LocalDate today = LocalDate.now();

		List<Member> members = repository.findByRegDtBetweenOrderByRegDtDesc(today.minusDays(3), today.plusDays(1));
		return members;
	}

	@GetMapping("/ex05")
	public List<Member> ex05(){
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("regDt")));
		Page<Member> page = repository.findAll(pageable);
		List<Member> members = page.getContent();

		return members;
	}

	@GetMapping("/ex06")
	public List<Member> ex06(){
		List<Member> members = repository.findByUsers("용");

		return members;
	}

	@GetMapping("/ex07")
	public List<Member> ex07(){
		List<Member> members = repository.findUsers("용");

		return members;
	}

	@GetMapping("/ex08")
	public List<Member> ex08(){
		Pageable pageable = PageRequest.of(0, 10);
		BooleanBuilder builder = new BooleanBuilder();
		QMember member = QMember.member;

		builder.and(member.userNm.contains("자"))
				.and(member.userId.notIn("user1", "user2"));

		Page<Member> page = repository.findAll(builder, pageable);

		return page.getContent();
	}
}
