package com.sample.service;

import java.util.List;

import com.sample.vo.ReplyVO;


public interface ReplyService {

	public List<ReplyVO> selectReplies(Integer no) throws Exception;
	
	public void insertReply(ReplyVO vo) throws Exception;

	public void updateReply(ReplyVO vo) throws Exception;

	public void deleteReply(Integer no) throws Exception;

	public int countReplies(Integer board_No) throws Exception;	
	
}
