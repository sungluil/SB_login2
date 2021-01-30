package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;


@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/api")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardRepository boardRepository; 
	
//	@GetMapping("/board")
//	public List<Board> getAllBoard() {
//		return boardService.getAllBoard();
//	}
	
	@GetMapping("/board")
	public String getAllBoard(Model model,Board board) {
		List<Board> boardList = boardService.getAllBoard();
		model.addAttribute(boardList);
		return "boardList";
	}
	
	@GetMapping("/boardWrite")
	public String getBoardWrite(Model model) {
		model.addAttribute(new Board());
		return "boardWrite";
	}
	
	@PostMapping("/boardWrite")
	public String boardWriteForm(Board board, Model model) {
		boardService.getBoardWrite(board);
		return "redirect:/api/board";
	}
}
