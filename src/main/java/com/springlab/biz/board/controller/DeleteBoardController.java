package com.springlab.biz.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteBoardController implements Controller {
	@Autowired
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 삭제 처리...");
		// step #1. get request parameters
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// step #2. data processing - DB 연동 처리
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		ModelAndView mv = new ModelAndView("redirect:getBoardList");
		
		boardService.deleteBoard(board);
		
		// step #3. output processing result
		return mv;
	}

}
