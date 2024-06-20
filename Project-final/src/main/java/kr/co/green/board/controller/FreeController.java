package kr.co.green.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.FreeDTO;
import kr.co.green.board.model.service.FreeServiceImpl;
import kr.co.green.common.paging.PageInfo;
import kr.co.green.common.paging.Pagination;
import kr.co.green.common.session.SessionMessage;
import kr.co.green.common.upload.UploadFile;
import kr.co.green.common.validation.DataValidation;

//@Controller  // 이 클래스를 컨트롤러로 사용하겠다.
@RestController
@RequestMapping("/free")  // GET+POST 요청을 받겠다. (/free 로 오는 요청)
public class FreeController {
    private static final Logger logger = Logger.getLogger(FreeController.class.getName());

	private static final String BOARD_NAME = "free\\";
	
	@Autowired
	private FreeServiceImpl freeService;
	
	@Autowired
	private SessionMessage sessionMessage;
	
	@GetMapping("/list.do")
//	public String freeList(FreeDTO free,
	public ResponseEntity<?> freeList(FreeDTO free,
						   @RequestParam(value="cpage", defaultValue="1") int cpage, 
						   Model model,
						   HttpSession session) {
		
//		서블릿일 경우 코드
//		int cpage = request.getParameter("cpage");
//		if(cp == 0) {
//			cpage = 1;
//		}
		/*
		 * Data newData = new Data("jaeseop", 19); Gson gson = new Gson();
		 * 
		 * // Java 객체 -> JSON 문자열로 변환 String jsonString = gson.toJson(newData);
		 * System.out.println(jsonString);
		 * 
		 * // JSON 문자열 -> Java 객체로 변환 Data data = gson.fromJson(jsonString, Data.class);
		 * System.out.println(data); System.out.println(data.getName());
		 */
		
		
		
		
//		if(LoginCheck.loginCheck(session)) {
			// 전체 게시글 수 구하기
			int listCount = freeService.selectListCount(free);
			int pageLimit = 10;
			int boardLimit = 15;
			// 게시글 번호
			//          26
			int row = listCount - (cpage-1) * boardLimit;
			
			PageInfo pi = Pagination.getPageInfo(listCount, cpage, pageLimit, boardLimit);
			
			// 목록 불러오기
			List<BoardDTO> list = freeService.selectListAll(pi, free);
			
			for(BoardDTO item : list) {
				// 2023-12-26 15:57:48.0
				String indate = item.getIndate().substring(0,10);
				item.setIndate(indate);
			}
			
			String msg = (String) session.getAttribute("msg");
			String status = (String) session.getAttribute("status");
			
//			model.addAttribute("row", row);
//			model.addAttribute("list", list);
//			model.addAttribute("pi", pi);
//			model.addAttribute("msg", msg);
//			model.addAttribute("status", status);
			
			Map<String, Object> response = new HashMap<>();
			response.put("row", row);
			response.put("list", list);
			response.put("pi", pi);
			response.put("msg", msg);
			response.put("status", status);
			
			session.setAttribute("action", "/free/list.do");
			
			session.removeAttribute("msg");
			session.removeAttribute("status");
			
//			/WEB-INF/views/board/free/freeList.jsp
//			return "board/free/freeList";
			return new ResponseEntity<>(response, HttpStatus.OK);
//		} 
//	else { // 로그인이 되지 않았을 때
//			model.addAttribute("msg", "잘못된 접근입니다.");
//			model.addAttribute("status", "error");
//			return "member/login";
//		}
            
	}
	
	@GetMapping("enrollForm.do")
	public String enrollForm() {
		return "board/free/freeEnroll";
	}
	
	@PostMapping("/enroll.do")
//	public ResponseEntity<?> freeEnroll(FreeDTO free, MultipartFile upload, HttpSession session) throws IllegalStateException, IOException {
	public ResponseEntity<?> freeEnroll(@RequestBody FreeDTO free, HttpSession session) throws IllegalStateException, IOException {
//		free.setWriter((String)session.getAttribute("memberName"));
		free.setWriter("홍길동2");
		
		// 제목 길이 검사
		boolean titleLengthCheck = DataValidation.CheckLength(free.getTitle(), 150);
		
		// 제목이 비어있는지 검사
		boolean titleEmptyCheck = DataValidation.emptyCheck(free.getTitle());
		
//		if(titleLengthCheck && titleEmptyCheck) {
//			// upload가 비어있지 않은 경우
//			if(!upload.isEmpty()) {
//				UploadFile.uploadMethod(upload, free, session, BOARD_NAME);
//			}
			
			int result = freeService.enrollBoard(free);
			
			if(result > 0) {
				return new ResponseEntity<>("success", HttpStatus.OK);
//				return "redirect:/free/list.do";
			} else {
				return new ResponseEntity<>("failed", HttpStatus.OK);
			}
//		} else if(!titleLengthCheck) {
//			return sessionMessage.setSessionMessage("제목이 너무 깁니다.", "error", "/free/list.do", session);
////			session.setAttribute("msg", "제목이 너무 깁니다.");
////			session.setAttribute("status", "error");
////			return "redirect:/free/list.do";
//		} else if(!titleEmptyCheck) {
//			return sessionMessage.setSessionMessage("제목을 입력해주세요.", "error", "/free/list.do", session);
////			session.setAttribute("msg", "제목을 입력해주세요.");
////			session.setAttribute("status", "error");
////			return "redirect:/free/list.do";
//		} else {
//			return sessionMessage.setSessionMessage("에러가 발생했습니다.", "error", "/free/list.do", session);
////			session.setAttribute("msg", "에러가 발생했습니다.");
////			session.setAttribute("status", "error");
////			return "redirect:/free/list.do";
//		}
	}
	
	// /free/detail.do
	// /free/detail/130
	@GetMapping("/detail/{idx}")
	public ResponseEntity<?> detailBoard(@PathVariable(value="idx") int idx, 
										  Model model,
										  HttpServletRequest request) {
		
		FreeDTO result = freeService.detailBoard(idx);
		
//		String referer = request.getHeader("referer");
//		boolean endWith = referer.endsWith("/list.do");
//		
//		// 정상적인 요청일 경우
//		// referer : http://localhost/free/list.do
//		// endWith : true
//		if(referer == null || !endWith) {
//			return "common/error";
//		}
		
		if(!Objects.isNull(result)) {
//			model.addAttribute("free", result);
			Map<String, Object> response = new HashMap<>();
			response.put("result", result);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("error", HttpStatus.OK);
		}
	}
	
	@GetMapping("/editForm.do")
	public String editFormBoard(@RequestParam(value="boardIdx") int idx, Model model) {
		FreeDTO result = freeService.editFormBoard(idx);
		
		if(!Objects.isNull(result)) {
			model.addAttribute("free", result);
			return "board/free/freeEdit";
		} else {
			return "common/error";
		}
	}
	
//	@PostMapping("/edit.do")
	@PutMapping("/edit/{idx}")
//	public String editBoard(MultipartFile upload, FreeDTO free, HttpSession session) {
	public ResponseEntity<?> editBoard(@RequestBody FreeDTO free, 
										@PathVariable("idx") int idx,
										HttpSession session) {
		// 로그인한 사용자의 이름(session = memberName)
		// 요청한 게시글의 작성자
		String getWriter = freeService.selectWriter(free.getIdx()); // 게시글 작성자
		String loginWriter = (String) session.getAttribute("memberName"); // 로그인 유저
		
		// 제목 길이 검사
		boolean titleLengthCheck = DataValidation.CheckLength(free.getTitle(), 150);
		
		// 제목이 비어있는지 검사
		boolean titleEmptyCheck = DataValidation.emptyCheck(free.getTitle());
		
		
		int result = 0;
		free.setIdx(idx);
		
		result = freeService.editBoardEmptyUpload(free);
		
		if(result == 1) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("error", HttpStatus.OK);
		}
		
		
	}
	
//	@GetMapping("/delete.do")
	@DeleteMapping("/delete/{idx}")
//	public String deleteBoard(@RequestParam(value="boardIdx") int idx, HttpSession session) {
	public ResponseEntity<?> deleteBoard(@PathVariable("idx") int idx, HttpSession session) {
		// 로그인한 사용자의 idx == 글 작성자의 idx
//		String getWriter = freeService.selectWriter(idx); // 게시글 작성자
//		String loginWriter = (String) session.getAttribute("memberName"); // 로그인 유저
		String getWriter = "홍길동";
		String loginWriter = "홍길동";
		
		int result = 0;
//		if(getWriter.equals(loginWriter)) {
//			String fileName = freeService.selectFileName(idx);
//
//			if(fileName != null) { // 기존에 이미지가 있을 때
//				boolean deleteFile = UploadFile.deleteFile(fileName, BOARD_NAME);
//				
//				if(deleteFile) { // 이미지가 성공적으로 삭제됐을 때
//					result = freeService.deleteBoard(idx);
//				}
//			} else { // 기존 이미지가 없을 때
//				result = freeService.deleteBoard(idx);
//			}
//		}
		result = freeService.deleteBoard(idx);

		if(result == 1) {
			session.setAttribute("msg", "삭제되었습니다.");
			session.setAttribute("status", "success");
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			session.setAttribute("msg", "삭제를 실패했습니다.");
			session.setAttribute("status", "error");
			return new ResponseEntity<>("failed", HttpStatus.OK);
		}
	}
	
	
	
}













