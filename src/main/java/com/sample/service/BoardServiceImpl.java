package com.sample.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sample.dao.BoardDAO;
import com.sample.vo.BoardVO;


@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	
	@Override
	public void insert(BoardVO board) throws Exception {
		dao.insert(board);
	}

	@Override
	public BoardVO read(Integer no) throws Exception {
	    return dao.read(no);
	}

	@Override
	public void update(BoardVO board) throws Exception {
	    dao.update(board);
	}

	@Override
	public void updateHit(Integer no) throws Exception {
	    dao.updateHit(no);
	}
	
	@Override
	public void delete(Integer no) throws Exception {
	    dao.delete(no);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

}
