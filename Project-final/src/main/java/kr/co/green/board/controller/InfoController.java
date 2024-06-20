package kr.co.green.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.InfoDTO;
import kr.co.green.board.model.service.InfoServiceImpl;
import kr.co.green.common.paging.PageInfo;
import kr.co.green.common.paging.Pagination;
import kr.co.green.common.session.SessionMessage;
import kr.co.green.common.upload.UploadFile;
import kr.co.green.common.validation.DataValidation;

@Controller
@RequestMapping("/info")
public class InfoController {
	private static final String BOARD_NAME = "info\\";

	@Autowired
	private InfoServiceImpl infoService;
	
	@Autowired
	private SessionMessage sessionMessage;

	@GetMapping("/list.do")
	public ResponseEntity<?> freeList(InfoDTO info,
						   @RequestParam(value = "cpage", defaultValue = "1") int cpage, Model model,
						   HttpSession session) {
		
		int listCount = infoService.selectListCount(info);
		int pageLimit = 10;
		int boardLimit = 15;
		int row = listCount - (cpage - 1) * boardLimit;

		PageInfo pi = Pagination.getPageInfo(listCount, cpage, pageLimit, boardLimit);

		List<BoardDTO> list = infoService.selectListAll(pi, info);

		for (BoardDTO item : list) {
			String indate = item.getIndate().substring(0, 10);
			item.setIndate(indate);
		}

		String msg = (String) session.getAttribute("msg");
		String status = (String) session.getAttribute("status");

//		model.addAttribute("row", row);
//		model.addAttribute("list", list);
//		model.addAttribute("pi", pi);
//		model.addAttribute("msg", msg);
//		model.addAttribute("status", status);
		Map<String, Object> response = new HashMap<>();
		response.put("row", row);
		response.put("list", list);
		response.put("pi", pi);
		response.put("msg", msg);
		response.put("status", status);
		
		session.setAttribute("action", "/info/list.do");

		session.removeAttribute("msg");
		session.removeAttribute("status");

//		return "board/info/infoList";
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("enrollForm.do")
	public String enrollForm() {
		return "board/info/infoEnroll";
	}
	
	@PostMapping("enroll.do")
	public ResponseEntity<?> freeEnroll(@RequestBody InfoDTO info, HttpSession session) throws IllegalStateException, IOException {
//		info.setWriter((String)session.getAttribute("memberName"));
		info.setWriter("홍길동2");
		info.setCategory("가전제품");
		
		boolean titleLengthCheck = DataValidation.CheckLength(info.getTitle(), 150);
		boolean titleEmptyCheck = DataValidation.emptyCheck(info.getTitle());
		
//		if(titleLengthCheck && titleEmptyCheck) {
//			if(!upload.isEmpty()) {
//				UploadFile.uploadMethod(upload, info, session, BOARD_NAME);
//			}
			
			int result = infoService.enrollBoard(info);
			
			if(result > 0) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("error", HttpStatus.OK);
			}
//		} else if(!titleLengthCheck) {
//			return sessionMessage.setSessionMessage("제목이 너무 깁니다.", "error", "/info/list.do", session);
//		} else if(!titleEmptyCheck) {
//			return sessionMessage.setSessionMessage("제목을 입력해주세요.", "error", "/info/list.do", session);
//		} else {
//			return sessionMessage.setSessionMessage("에러가 발생했습니다.", "error", "/info/list.do", session);
//		}
	}
	
	@GetMapping("/detail/{}")
	public String detailBoard(@RequestParam(value="boardIdx") int idx, Model model) {
		InfoDTO result = infoService.detailBoard(idx);
		
		if(!Objects.isNull(result)) {
			model.addAttribute("info", result);
			return "board/info/infoDetail";
		} else {
			return "common/error";
		}
	}
	
	@GetMapping("/editForm.do")
	public String editFormBoard(@RequestParam(value="boardIdx") int idx, Model model) {
		InfoDTO result = infoService.editFormBoard(idx);
		
		if(!Objects.isNull(result)) {
			model.addAttribute("info", result);
			return "board/info/infoEdit";
		} else {
			return "common/error";
		}
	}
	
	@PostMapping("/edit.do")
	public String editBoard(MultipartFile upload, InfoDTO info, HttpSession session) {
		String getWriter = infoService.selectWriter(info.getIdx()); // 게시글 작성자
		String loginWriter = (String) session.getAttribute("memberName"); // 로그인 유저
		boolean titleLengthCheck = DataValidation.CheckLength(info.getTitle(), 150);
		boolean titleEmptyCheck = DataValidation.emptyCheck(info.getTitle());
		int result = 0;
		
		if(titleLengthCheck && titleEmptyCheck) {
			if(getWriter.equals(loginWriter) && !upload.isEmpty()) {
				String fileName = infoService.selectFileName(info.getIdx());
				
				boolean deleteFile = UploadFile.deleteFile(fileName, BOARD_NAME);
				
				if(deleteFile) {
					UploadFile.uploadMethod(upload, info, session, BOARD_NAME);
					result = infoService.editBoard(info);
				}
			} else if(getWriter.equals(loginWriter) && upload.isEmpty()) {
				result = infoService.editBoardEmptyUpload(info);
			}
			
			if(result == 1) {
				return sessionMessage.setSessionMessage("수정되었습니다.", "error", "/info/list.do", session);
			} else {
				return sessionMessage.setSessionMessage("수정에 실패했습니다.", "error", "/info/list.do", session);
			}
			
		} else if(!titleLengthCheck) {
			return sessionMessage.setSessionMessage("제목이 너무 깁니다.", "error", "/info/list.do", session);
		} else if(!titleEmptyCheck) {
			return sessionMessage.setSessionMessage("제목을 입력해주세요.", "error", "/info/list.do", session);
		} else {
			return sessionMessage.setSessionMessage("에러가 발생했습니다.", "error", "/info/list.do", session);
		}
	}
	
	@GetMapping("/delete.do")
	public String deleteBoard(@RequestParam(value="boardIdx") int idx, HttpSession session) {
		String getWriter = infoService.selectWriter(idx); // 게시글 작성자
		String loginWriter = (String) session.getAttribute("memberName"); // 로그인 유저
		
		int result = 0;
		if(getWriter.equals(loginWriter)) {
			String fileName = infoService.selectFileName(idx);

			if(fileName != null) { // 기존에 이미지가 있을 때
				boolean deleteFile = UploadFile.deleteFile(fileName, BOARD_NAME);
				
				if(deleteFile) { // 이미지가 성공적으로 삭제됐을 때
					result = infoService.deleteBoard(idx);
				}
			} else { // 기존 이미지가 없을 때
				result = infoService.deleteBoard(idx);
			}
		}
		
		if(result == 1) {
			session.setAttribute("msg", "삭제되었습니다.");
			session.setAttribute("status", "success");
			return "redirect:/info/list.do";
		} else {
			session.setAttribute("msg", "삭제를 실패했습니다.");
			session.setAttribute("status", "error");
			return "redirect:/info/list.do";
		}
	}
	

}
