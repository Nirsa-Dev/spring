package kr.co.green.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.green.common.access.JwtUtil;
import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	@GetMapping("/redirect.do")
	public String redirectIndex() {
		return "member/login";
	}
	
	@PostMapping("/login.do")
	public String loginIndex(MemberDTO member, HttpSession session, Model model) {                    
		MemberDTO loginUser = memberService.loginMember(member);
		// loginUser 객체가 비어있지 않을 때 (로그인 성공)
		if(!Objects.isNull(loginUser) && bcryptPasswordEncoder.matches(member.getPwd(), loginUser.getPwd())) {                           
			// JWT 생성
			Map<String, Object> claims = new HashMap<>();
			User userDetails = new User(loginUser.getName(), loginUser.getPwd(), new ArrayList<>());
			String token = jwtUtil.createToken(claims, userDetails);
			
			return token;  // JWT 반환
			
//			session.setAttribute("memberIdx", loginUser.getIdx());
//			session.setAttribute("memberName", loginUser.getName());
//			session.setAttribute("msg", "로그인 되었습니다.");
//			session.setAttribute("status", "success");
//			// 컨트롤러 -> 컨트롤러를 다시 호출해야할 때
//			return "redirect:/free/list.do";
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
			model.addAttribute("status", "error");
			// 컨트롤 -> jsp를 호출할 때
			return "member/login";
		}
	}
	
	@GetMapping("/registerForm.do")
	public String registerForm() {
		return "member/register";
	}
	
	@PostMapping("/checkEmail.do")
	@ResponseBody
	public String checkEmail(String email) {
		// 이메일 중복검사
		int result = memberService.checkEmail(email);
		
		if(result == 1) {
			return "duplication";
		} else {
			return "available";
		}
	}
	
	@PostMapping("/register.do")
	public String register(MemberDTO member) {
		// 패스워드 암호화
		String passwrod = bcryptPasswordEncoder.encode(member.getPwd());
		member.setPwd(passwrod);
		
		int result = memberService.registerMember(member);
		
		if(result == 1) {
			return "member/login";
		} else {
			return "common/error";
		}
		
	}
	
	
}















