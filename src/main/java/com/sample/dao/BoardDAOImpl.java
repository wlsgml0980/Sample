package com.sample.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sample.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.sample.mapper.BoardMapper";
	
	
	@Override
	public void insert(BoardVO vo) throws Exception {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public BoardVO read(Integer no) throws Exception {
		return session.selectOne(namespace + ".read", no);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
	    session.update(namespace + ".update", vo);
	}
	
	@Override
	public void updateHit(Integer no) throws Exception {
	    session.update(namespace + ".updateHit", no);
	}

	@Override
	public void delete(Integer no) throws Exception {
	    session.delete(namespace + ".delete", no);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
	    return session.selectList(namespace + ".listAll");
	}

}
