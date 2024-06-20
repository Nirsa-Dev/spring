package kr.co.green.board.model.service;

import java.util.List;
import java.util.Objects;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.green.board.model.dao.InfoDAO;
import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.InfoDTO;
import kr.co.green.common.paging.PageInfo;

@Service
public class InfoServiceImpl implements BoardService{
	
	// DB 연결에 관련된 템플릿
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired 
	private InfoDAO infoDao;
	
	@Override
	public int selectListCount(BoardDTO board) {
		return infoDao.selectListCount(sqlSession, board);
	}
	
	// 목록 불러오기
	@Override
	public List<BoardDTO> selectListAll(PageInfo pi, BoardDTO board) {
		return infoDao.selectListAll(sqlSession, pi, board);
	}
	
	// 게시글 등록
	@Override
	public int enrollBoard(BoardDTO board) {
		return infoDao.enrollBoard(sqlSession, board);
	}
	
	// 게시글 상세보기
	@Transactional  // 해당 메소드에서 실행되는 데이터베이스의 액션을 하나로 묶음
	@Override
	public InfoDTO detailBoard(int idx) {
		
		InfoDTO info = infoDao.detailBoard(sqlSession, idx); // 게시글 정보
		
		if(!Objects.isNull(info)) { // 게시글이 있을 때
			info.setViews(info.getViews()+1); // 조회수 증가
			int result = infoDao.views(sqlSession, info);
			
			if(result == 1) { // 조회수가 잘 증가되었는지 검증
				return info;
			}
		}
		return info;
	}
	
	@Override
	public InfoDTO editFormBoard(int idx) {
		return infoDao.editFormBoard(sqlSession, idx);
	}
	
	@Override
	public int editBoard(BoardDTO board) {
		return infoDao.editBoard(sqlSession, board);
	}
	
	@Override
	public int editBoardEmptyUpload(BoardDTO board) {
		return infoDao.editBoardEmptyUpload(sqlSession, board);
	}

	@Override
	public int deleteBoard(int idx) {
		return infoDao.deleteBoard(sqlSession, idx);
	}
	
	@Override
	public String selectWriter(int idx) {
		return infoDao.selectWriter(sqlSession, idx);
	}
	
	@Override
	public String selectFileName(int idx) {
		return infoDao.selectFileName(sqlSession, idx);
	}
	
	
	
}

