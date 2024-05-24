/**
 * 
 */
package com.springlab.biz.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;
import com.springlab.biz.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * 
 */
public class GetBoardListController implements Controller {
	@Autowired
	private BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 목록 처리...");
		
		// step #1. get request parameters
		
		// step #2. data processing
		List<BoardDO> boardList = boardService.getBoardList(null);
		
		// step #3. output results
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("board_list", boardList);
		//request.setAttribute("board_list", boardList);
		
		mv.setViewName("getBoardList");
		//-> ModelAndView를 객체 생성할 때 "getBoardList"를 넣으면 생성 따로 안해도 됨
		return mv;
	}

}
