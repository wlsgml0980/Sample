package com.sample.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sample.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "com.sample.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> selectReplies(Integer no) throws Exception {
		return session.selectList(namespace + ".selectReplies", no);
	}

	@Override
	public void insertReply(ReplyVO vo) throws Exception {
		session.insert(namespace + ".insertReply", vo);
		
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		session.update(namespace + ".updateReply", vo);
	}

	@Override
	public void deleteReply(Integer no) throws Exception {
		session.update(namespace + ".deleteReply", no);
	}

	@Override
	public int countReplies(Integer no) throws Exception {
		return session.selectOne(namespace + ".countReplies", no);
	}

}






