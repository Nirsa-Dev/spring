package kr.co.green.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.FreeDTO;
import kr.co.green.common.paging.PageInfo;

// DAO로 사용하겠다
@Repository
public class FreeDAO {
	
	public int selectListCount(SqlSessionTemplate sqlSession, BoardDTO board) {
		//							  네임스페이스.사용할쿼리의ID
		return sqlSession.selectOne("freeMapper.selectListCount", board);
	}

	// 목록 불러오기
	public List<BoardDTO> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi, BoardDTO board) {
		// 현재 페이지의 게시글을 불러오기 위한 변수
		int offset = (pi.getCpage()-1) * pi.getBoardLimit();
		
		// 작은 규모일 경우에만 사용하는것을 권장
		RowBounds rb = new RowBounds(offset, pi.getBoardLimit());
		
		return sqlSession.selectList("freeMapper.selectListAll", board, rb);
	}
	
	// 게시글 등록
	public int enrollBoard(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.insert("freeMapper.enrollBoard", board);
	}

	public FreeDTO detailBoard(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("freeMapper.detailBoard", idx);
	}

	public int views(SqlSessionTemplate sqlSession, FreeDTO free) {
		return sqlSession.update("freeMapper.views", free);
	}
	
	// 게시글 수정 폼
	public FreeDTO editFormBoard(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("freeMapper.detailBoard", idx);
	}

	public int editBoard(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.update("freeMapper.editBoard", board);
	}
	
	public int editBoardEmptyUpload(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.update("freeMapper.editBoardEmptyUpload", board);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.delete("freeMapper.deleteBoard", idx);
	}
	
	public String selectWriter(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("freeMapper.selectWriter", idx);
	}

	public String selectFileName(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("freeMapper.selectFileName", idx);
	}
	
}






