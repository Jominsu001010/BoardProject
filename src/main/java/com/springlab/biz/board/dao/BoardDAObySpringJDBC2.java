package com.springlab.biz.board.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;
//@Repository("boardDAO")
//@Slf4j
public class BoardDAObySpringJDBC2 implements BoardDAO {
	
	
	private final String BOARD_INSERT = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT, UPLOADFILES) "
			+ "values((select nvl(max(SEQ), 0)+1 from BOARD), ?, ?, ?, ?)";
	private final String BOARD_UPDATE = "update BOARD set TITLE=?, CONTENT=?, CNT=? where SEQ=?";
	private final String BOARD_DELETE = "delete BOARD where SEQ=?";
	private final String BOARD_GET = "select * from BOARD where SEQ=?";
	private final String BOARD_LIST = "select * from BOARD where %s like '%%'||?||'%%' order by SEQ desc";
	
	@Autowired
	@Qualifier("boardRowMapper")
	private BoardRowMapper boardRowMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process insertBoard()");
		/*
		 * jdbcTemplate.update(BOARD_INSERT, board.getTitle(), board.getWriter(),
		 * board.getContent());
		 */
		jdbcTemplate.update(BOARD_INSERT, 
				 board.getTitle(), board.getWriter(), board.getContent(), board.getUploadFiles());

	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process updateBoard()");
		jdbcTemplate.update(BOARD_UPDATE, 
				board.getTitle(), board.getContent(), board.getCnt(), board.getSeq());
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process deleteBoard()");
		jdbcTemplate.update(BOARD_DELETE, board.getSeq());
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process getBoard()");
		return jdbcTemplate.queryForObject(BOARD_GET, boardRowMapper ,board.getSeq());
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process getBoardList()");
		String sql = String.format(BOARD_LIST, board.getSearchCondition());
		return  jdbcTemplate.query(sql, boardRowMapper, board.getSearchKeyword());
	}

}
