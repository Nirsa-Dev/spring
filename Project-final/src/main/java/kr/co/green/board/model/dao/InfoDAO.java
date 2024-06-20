package kr.co.green.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.InfoDTO;
import kr.co.green.common.paging.PageInfo;

@Repository
public class InfoDAO {
	public int selectListCount(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.selectOne("infoMapper.selectListCount", board);
	}

	public List<BoardDTO> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi, BoardDTO board) {
		int offset = (pi.getCpage()-1) * pi.getBoardLimit();
		RowBounds rb = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("infoMapper.selectListAll", board, rb);
	}

	// 게시글 등록
	public int enrollBoard(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.insert("infoMapper.enrollBoard", board);
	}

	public InfoDTO detailBoard(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("infoMapper.detailBoard", idx);
	}

	public int views(SqlSessionTemplate sqlSession, InfoDTO info) {
		return sqlSession.update("infoMapper.views", info);
	}
	
	// 게시글 수정 폼
	public InfoDTO editFormBoard(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("infoMapper.detailBoard", idx);
	}

	public int editBoard(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.update("infoMapper.editBoard", board);
	}
	
	public int editBoardEmptyUpload(SqlSessionTemplate sqlSession, BoardDTO board) {
		return sqlSession.update("infoMapper.editBoardEmptyUpload", board);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.delete("infoMapper.deleteBoard", idx);
	}
	
	public String selectWriter(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("infoMapper.selectWriter", idx);
	}

	public String selectFileName(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("infoMapper.selectFileName", idx);
	}
}






