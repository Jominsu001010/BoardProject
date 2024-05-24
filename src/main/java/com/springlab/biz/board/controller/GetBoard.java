package com.springlab.biz.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoard implements Controller {
	@Autowired
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 상세보기 처리...");
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		ModelAndView mv = new ModelAndView("getBoard");
		
		board = boardService.getBoard(board);		
		mv.addObject("board", board);
		
		
		return mv;
	}

}
