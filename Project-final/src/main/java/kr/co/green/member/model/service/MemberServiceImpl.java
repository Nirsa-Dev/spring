package kr.co.green.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.green.member.model.dao.MemberDAO;
import kr.co.green.member.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDAO memberDao;
	
	// 로그인
	@Override
	public MemberDTO loginMember(MemberDTO member) {
		return memberDao.loginMember(sqlSession, member);
	}
	
	@Override
	public int checkEmail(String email) {
		return memberDao.checkEmail(sqlSession, email);
	}

	@Override
	public int registerMember(MemberDTO member) {
		return memberDao.registerMember(sqlSession, member);
	}
	
	@Override
	public MemberDTO findMember(int idx) {
		return memberDao.findMember(sqlSession, idx);
	}
}




