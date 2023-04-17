package org.koreait.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//@Transactional	//테스트에서만 데이터가 추가되고, 테스트 종료시 제거된다.
public class BoardDaoTest {
	@Autowired
	private BoardDao boardDao;
	
	@Test
	@DisplayName("게시글 추가 테스트")
	public void insertTest(){
		Board board = new Board();
		board.setSubject("제목");
		board.setContent("내용");

		boolean result = boardDao.insert(board);

		assertTrue(result);
	}

	@Test
	@DisplayName("게시글 조회 테스트")
	public void getTest(){
		Board board = boardDao.get(3L);
		System.out.println(board);
	}
}
