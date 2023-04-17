package org.koreait.models.board;

import org.koreait.controllers.boards.BoardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardWriteService {
	private BoardDao boardDao;

	@Autowired
	public void setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
	}

	public void write(BoardForm boardForm){
		Board board = new Board();
		board.setSubject(boardForm.getSubject());
		board.setContent(boardForm.getContent());

		boardDao.insert(board);
	}
}
