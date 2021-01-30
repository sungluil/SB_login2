package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> getAllBoard() {
		// TODO Auto-generated method stub
		return boardRepository.findAll(Sort.by("no").descending());
	}

	public Board getBoardWrite(Board board) {
		// TODO Auto-generated method stub
		return boardRepository.save(board);
	}
	

}
