package com.sample.dao;

import java.util.List;

import com.sample.vo.BoardVO;


public interface BoardDAO {

	  public void insert(BoardVO vo) throws Exception;

	  public BoardVO read(Integer no) throws Exception;

	  public void update(BoardVO vo) throws Exception;
	  
	  public void updateHit(Integer vo) throws Exception;

	  public void delete(Integer no) throws Exception;

	  public List<BoardVO> listAll() throws Exception;	
	
}
