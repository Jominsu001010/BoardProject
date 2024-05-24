package com.springlab.biz.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;
//@Repository("boardDAO")
//@Slf4j
public class BoardDObyMybatis implements BoardDAO{
	
	private SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession() {
		sqlSession = SqlSessionFactoryBean.getSqlSession();
	}
	
	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process insertBoard()");
		sqlSession.insert("BoardMapper.insertBoard", board);
		sqlSession.commit();
		
	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process UpdateBoard()");
		sqlSession.update("BoardMapper.updateBoard", board);
		sqlSession.commit();
		
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process deleteBoard()");
		sqlSession.delete("BoardMapper.deleteBoard", board);
		sqlSession.commit();
		
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process getBoard()");
		return sqlSession.selectOne("BoardMapper.getBoard", board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process getBoardList()");
		return sqlSession.selectList("BoardMapper.getBoardList", board);
	}

}
