package com.springlab.biz.board.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;
//@Repository("boardDAO")
public class BoardDAObySpringJDBC extends JdbcDaoSupport implements BoardDAO {
	
	private final String BOARD_INSERT = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT) "
			+ "values((select nvl(max(SEQ), 0)+1 from BOARD), ?, ?, ?)";
	private final String BOARD_UPDATE = "update BOARD set TITLE=?, CONTENT=?, CNT=? where SEQ=?";
	private final String BOARD_DELETE = "delete BOARD where SEQ=?";
	private final String BOARD_GET = "select * from BOARD where SEQ=?";
	private final String BOARD_LIST = "select * from BOARD order by SEQ desc";
	
	@Autowired
	@Qualifier("boardRowMapper")
	private BoardRowMapper boardRowMapper;
	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		
	}

	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process insertBoard()");
		this.getJdbcTemplate().update(BOARD_INSERT, 
				board.getTitle(), board.getWriter(), board.getContent());

	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process updateBoard()");
		this.getJdbcTemplate().update(BOARD_UPDATE, 
				board.getTitle(), board.getContent(), board.getCnt(), board.getSeq());
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process deleteBoard()");
		this.getJdbcTemplate().update(BOARD_DELETE, board.getSeq());
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process getBoard()");
		return this.getJdbcTemplate().queryForObject(BOARD_GET, boardRowMapper ,board.getSeq());
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process getBoardList()");
		return  this.getJdbcTemplate().query(BOARD_LIST, boardRowMapper);
	}

}
