package com.springlab.biz.board.controller2;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//Controller와 ResponseBody(jsp이름이 아닌 return값을 전송하는 것이다 명시) 합친 것 
@RestController
@RequestMapping("/api")
@Slf4j
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/getBoard")
	public ResponseEntity<BoardDO> getBoard(BoardDO board) throws JsonProcessingException {
		System.out.println(">>> 게시글 상세보기 API 처리...");
		board = boardService.getBoard(board);
		//ResponseEntity null 걸러내기
		if (board == null) {
			return ResponseEntity
					.notFound()
					.build();
		}
		else {
			return ResponseEntity
					.ok()
					.body(board);
		}
	}
	
	@GetMapping("/getBoardList")
	public ResponseEntity<Result> getBoardList(BoardDO board) {
		System.out.println(">>> 게시글 목록 처리...");
		List<BoardDO> boardList = boardService.getBoardList(board);
		
		if (boardList == null) {
			return ResponseEntity
					.notFound()
					.build();
		}
		else {
			return ResponseEntity.ok().body(
					new Result(boardList, boardList.size())
					);
		}
	}
	@AllArgsConstructor
	@Getter
	private class Result{
		private List<BoardDO> boardList;
		private int count;
	}
	
//	public BoardDO getBoard(BoardDO board) throws JsonProcessingException {
//		System.out.println(">>> 게시글 상세보기 API 처리...");
//		
//		return boardService.getBoard(board);
//	}
	//@ResponseBody
//	public String getBoard(
//			BoardDO board) throws JsonProcessingException {
//		System.out.println(">>> 게시글 상세보기 API 처리...");
//		
//		board = boardService.getBoard(board);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String result = mapper.writeValueAsString(board);
//
//		return result;
//	}
}
