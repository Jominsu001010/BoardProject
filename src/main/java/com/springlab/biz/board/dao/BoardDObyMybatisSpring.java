package com.springlab.biz.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;
//@Repository("boardDAO")
//@Slf4j
public class BoardDObyMybatisSpring extends SqlSessionDaoSupport implements BoardDAO{
	
	
	@Autowired
	public void setSpuerSqlSessionFactory(SqlSessionFactory factory) {
		super.setSqlSessionFactory(factory); 
	}
	
	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process insertBoard()");
		//자동 commit
		getSqlSession().insert("BoardMapper.insertBoard", board);
	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process UpdateBoard()");
		getSqlSession().update("BoardMapper.updateBoard", board);
		
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process deleteBoard()");
		getSqlSession().delete("BoardMapper.deleteBoard", board);
		
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process getBoard()");
		return getSqlSession().selectOne("BoardMapper.getBoard", board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process getBoardList()");
		return getSqlSession().selectList("BoardMapper.getBoardList", board);
	}

}
