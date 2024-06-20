package kr.co.green.board.model.dto;

import lombok.Getter;
import lombok.Setter;

public class testDTO {
	
	@Getter
	@Setter
	public static class Create {
		private int idx;
		private String title;
		private String content;
		private String writer;
		private String indate;
		private int views;
		
		private String uploadPath; // 파일 경로
		private String uploadName; // 수정된 파일 이름
		private String uploadOriginName; // 원본 파일 이름
	}
	
	
}
