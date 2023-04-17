package org.koreait.controllers.boards;

import jakarta.validation.Valid;
import org.koreait.models.board.BoardWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardWriteService service;

	@GetMapping("/write")
	public String write(Model model){
		BoardForm boardForm = new BoardForm();
		model.addAttribute("boardForm", boardForm);

		boolean result = false;
		if(!result){
			throw new RuntimeException("예외 발생!");
		}

		return "board/write";
	}

	@PostMapping("/write")
	public String writePs(@Valid BoardForm boardForm, Errors errors){
		if(errors.hasErrors()){
			return "board/write";
		}

		service.write(boardForm);
		return "redirect:/board/list";
	}
	/*
	@ExceptionHandler(RuntimeException.class)
	public String errorHandler(RuntimeException e, Model model){
		e.printStackTrace();

		model.addAttribute("message", e.getMessage());

		return "error/common";
	}

	 */
}
