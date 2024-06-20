package kr.co.green.board.model.service;

import java.util.List;
import java.util.Objects;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.co.green.board.model.dao.FreeDAO;
import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.FreeDTO;
import kr.co.green.common.paging.PageInfo;

// 서비스로 사용하겠다
@Service
public class FreeServiceImpl implements BoardService {
	
	// DB 연결에 관련된 템플릿
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired 
	private FreeDAO freeDao;
	
	// 트랜젝션 관리해주는 객체 
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	
	@Override
	public int selectListCount(BoardDTO board) {
		return freeDao.selectListCount(sqlSession, board);
	}
	
	// 목록 불러오기
	@Override
	public List<BoardDTO> selectListAll(PageInfo pi, BoardDTO board) {
		return freeDao.selectListAll(sqlSession, pi, board);
	}
	
	// 게시글 등록
	@Override
	public int enrollBoard(BoardDTO board) {
		return freeDao.enrollBoard(sqlSession, board);
	}
	
	// 게시글 상세보기
	@Transactional  // 해당 메소드에서 실행되는 데이터베이스의 액션을 하나로 묶음
	@Override
	public FreeDTO detailBoard(int idx) {
		// 트랜젝션 기본 설정
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        
        // 트랜젝션의 격리 수준을 기본 설정으로
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        
        // 트랜잭션의 동작을 설정 (트랜잭션이 이미 존재하면 참여, 없으면 새로운 트랜잭션 생성)
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        
        // 트랜젝션 시작
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		
        FreeDTO free = new FreeDTO();
        try {
			free = freeDao.detailBoard(sqlSession, idx); // 게시글 정보
			
			if(!Objects.isNull(free)) { // 게시글이 있을 때
				free.setViews(free.getViews()+1); // 조회수 증가
				int result = freeDao.views(sqlSession, free);
				
//				if(true) {
//					throw new RuntimeException("Rollback"); // 예외 발생
//				}
				
				if(result == 1) { // 조회수가 잘 증가되었는지 검증
					transactionManager.commit(status); // 모든 쿼리가 잘 동작했다면 commit
					return free;
				}
			}
        } catch(Exception e) {
        	transactionManager.rollback(status);
        	throw e;
        }
        
		return null; // 게시글이 없거나 조회수가 증가되지 않았을 때
	}
	
	@Override
	public FreeDTO editFormBoard(int idx) {
		return freeDao.editFormBoard(sqlSession, idx);
	}
	
	@Override
	public int editBoard(BoardDTO board) {
		return freeDao.editBoard(sqlSession, board);
	}
	
	@Override
	public int editBoardEmptyUpload(BoardDTO board) {
		return freeDao.editBoardEmptyUpload(sqlSession, board);
	}

	@Override
	public int deleteBoard(int idx) {
		return freeDao.deleteBoard(sqlSession, idx);
	}
	
	@Override
	public String selectWriter(int idx) {
		return freeDao.selectWriter(sqlSession, idx);
	}
	
	@Override
	public String selectFileName(int idx) {
		return freeDao.selectFileName(sqlSession, idx);
	}
	
}










