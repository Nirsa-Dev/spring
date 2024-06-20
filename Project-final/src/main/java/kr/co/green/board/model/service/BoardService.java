package kr.co.green.board.model.service;

import java.util.List;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.FreeDTO;
import kr.co.green.common.paging.PageInfo;

public interface BoardService {
	// 전체 게시글 수 구하기
	int selectListCount(BoardDTO board);
	
	// 목록 불러오기
	List<BoardDTO> selectListAll(PageInfo pi, BoardDTO board);
	
	// 게시글 등록
	int enrollBoard(BoardDTO board);
	
	// 게시글 상세보기
	BoardDTO detailBoard(int idx);
	
	// 게시글 수정 폼
	BoardDTO editFormBoard(int idx);
	
	// 게시글 수정
	int editBoard(BoardDTO board);
	
	// 게시글 수정(업로드 파일이 없을 때)
	int editBoardEmptyUpload(BoardDTO board);

	// 게시글 삭제
	int deleteBoard(int idx);
	
	// 글 작성자 조회
	String selectWriter(int idx);
	
	// 파일 이름 조회
	String selectFileName(int idx);
}




