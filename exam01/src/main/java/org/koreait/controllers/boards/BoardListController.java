package org.koreait.controllers.boards;

import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardListController {
	@Autowired
	private BoardDao boardDao;

	@ResponseBody
	@GetMapping("/api2/board/{id}")
	public Board board(@PathVariable Long id){
		Board board = boardDao.get(id);
		return board;
	}
}
