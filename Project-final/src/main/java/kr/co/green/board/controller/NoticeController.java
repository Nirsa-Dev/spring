package kr.co.green.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.service.NoticeServiceImpl;
import kr.co.green.common.paging.PageInfo;
import kr.co.green.common.paging.Pagination;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	@GetMapping("/list.do")
	public String noticeList(@RequestParam(value="cpage", defaultValue="1") int cpage,
							 Model model,
							 HttpSession session) {
		int listCount = noticeService.selectListCount();
		int pageLimit = 10;
		int boardLimit = 15;
		int row = listCount-(cpage-1)*boardLimit;
		
		PageInfo pi = Pagination.getPageInfo(listCount, cpage, pageLimit, boardLimit);
		List<BoardDTO> list = noticeService.selectListAll(pi);
		
		for(BoardDTO item : list) {
			String indate = item.getIndate().substring(0,10);
			item.setIndate(indate);
		}
		
		String msg = (String) session.getAttribute("msg");
		String status = (String) session.getAttribute("status");
		
		model.addAttribute("row", row);
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		model.addAttribute("msg", msg);
		model.addAttribute("status", status);
		
		session.removeAttribute("msg");
		session.removeAttribute("status");
		
		return "board/notice/noticeList";
		
	}
	

}












