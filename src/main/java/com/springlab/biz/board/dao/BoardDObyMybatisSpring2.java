package com.springlab.biz.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;
@Repository("boardDAO")
@Slf4j
public class BoardDObyMybatisSpring2 implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
//sqlSession template이 commit을 자동으로 만들어준다.
	
	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process insertBoard()");
		sqlSession.insert("BoardMapper.insertBoard", board);

		
	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process UpdateBoard()");
		sqlSession.update("BoardMapper.updateBoard", board);

		
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyMybatis : process deleteBoard()");
		sqlSession.delete("BoardMapper.deleteBoard", board);

		
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
