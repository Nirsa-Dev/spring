package kr.co.green.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // 게터
@Setter // 세터
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 매개변수가 모두 들어간 생성자
@ToString
public class BoardDTO {
	private int idx;
	private String title;
	private String content;
	private String writer;
	private String indate;
	private int views;
	
	private String searchText = "";
	private String searchCategory = "";
	
	private String uploadPath; // 파일 경로
	private String uploadName; // 수정된 파일 이름
	private String uploadOriginName; // 원본 파일 이름
	
}






