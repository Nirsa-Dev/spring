package kr.co.green.member.model.service;

import kr.co.green.member.model.dto.MemberDTO;

public interface MemberService {
	// 로그인
	MemberDTO loginMember(MemberDTO member);
	
	// 이메일 중복검사
	public int checkEmail(String email);
	
	// 회원가입
	public int registerMember(MemberDTO member);
	
	// 회원 조회
	public MemberDTO findMember(int idx);
}
