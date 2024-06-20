package kr.co.green.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.paging.PageInfo;

@Repository
public class NoticeDAO {
	public int selectListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.selectListCount");
	}
	
	public List<BoardDTO> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi) {
		RowBounds rb = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return sqlSession.selectList("noticeMapper.selectListAll", null, rb);
	}
}






