package com.springlab.biz.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.springlab.biz.board.domain.BoardDO;
@Component("boardRowMapper")
public class BoardRowMapper implements RowMapper<BoardDO> {

	@Override
	public BoardDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardDO board = new BoardDO();
		board = new BoardDO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		board.setUploadFiles(rs.getString("UPLOADFILES"));
		return board;
	}

}
