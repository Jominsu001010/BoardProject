package com.springlab.biz.board.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"searchCondition","searchKeyword"})
public class BoardDO {
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private int seq;
	private String title;
	private String uploadFiles;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String searchCondition;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String searchKeyword;
}
