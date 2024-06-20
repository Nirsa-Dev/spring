package kr.co.green.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

// @RestController : @Controller + @ResponseBody
@RestController
@RequestMapping("/api/member")
public class MemberApi {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@GetMapping("/{idx}")
	public ResponseEntity findMemberByEmail(@PathVariable("idx") int idx) {
		
		MemberDTO member = memberService.findMember(idx);
		
//		MemberDTO member = new MemberDTO();
//		member.setIdx(idx);
//		member.setEmail("islandtim@naver.com");
		
		return new ResponseEntity(member, HttpStatus.OK);
		
	}
}









