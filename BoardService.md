package com.service.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.spring.dao.BoardDAO;
import com.service.spring.domain.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	
	public int write(Board vo) {
		System.out.println("Before vo :: "+vo);//no가 없을것이다.
		int row=boardDAO.write(vo);
		
		
		String date=boardDAO.selectByNoForDate(vo.getNo());
		vo.setWriteDate(date);
		
		System.out.println("After vo :: "+vo);//no가 있을것이다.
		return row;
	}

	
	public List<Board> getBoardList() {
		return boardDAO.getBoardList();
	}


	public Board showContent(int no) {
		return boardDAO.showContent(no);
	}


	public void deleteBoard(int no) {
		boardDAO.deleteBoard(no);
		
	}


	public void updateCount(int no) {
		boardDAO.updateCount(no);
		
	}

	
	public void updateBoard(Board vo) {
		boardDAO.updateBoard(vo);		
	}
}
