package kr.co.green.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.green.board.model.dao.NoticeDAO;
import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.paging.PageInfo;

@Service
public class NoticeServiceImpl{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private NoticeDAO noticeDao;

}








