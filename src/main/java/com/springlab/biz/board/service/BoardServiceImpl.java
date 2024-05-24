package com.springlab.biz.board.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;

@Service("boardService")
@Transactional(readOnly = true, rollbackFor = Throwable.class)
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	@Qualifier("boardDAO")
	private BoardDAO dao;
	
	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process insertBoard()");
		
		dao.insertBoard(board);
		
	}
	@Transactional(readOnly = false)
	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process updateBoard()");
		dao.updateBoard(board);
	}
	@Transactional(readOnly = false)
	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process deleteBoard()");
		dao.deleteBoard(board);
	}
	
	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process getBoard()");
		return dao.getBoard(board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process getBoardList()");
		if (board == null) {
			board = new BoardDO();
		}
		if (board.getSearchCondition() == null || board.getSearchCondition().isBlank()){
			board.setSearchCondition("TITLE");
		}
		
		  if (board.getSearchKeyword() == null){
			  board.setSearchKeyword(""); 
		}
		 
		return dao.getBoardList(board);
	}

}
