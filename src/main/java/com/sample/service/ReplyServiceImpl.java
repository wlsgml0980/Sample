package com.sample.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sample.dao.ReplyDAO;
import com.sample.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> selectReplies(Integer no) throws Exception {
		return dao.selectReplies(no);
	}

	@Override
	public void insertReply(ReplyVO vo) throws Exception {
	    dao.insertReply(vo);
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		dao.updateReply(vo);
	}

	@Override
	public void deleteReply(Integer no) throws Exception {
		dao.deleteReply(no);
	}


	@Override
	public int countReplies(Integer no) throws Exception {
		return dao.countReplies(no);
	}

}
