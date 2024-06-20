package kr.co.green.common.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import kr.co.green.board.model.dto.BoardDTO;

public class UploadFile {
	private static final String UPLOAD_PATH="C:\\spring\\Project\\src\\main\\webapp\\resources\\uploads\\";
	
	public static boolean deleteFile(String fileName, String boardName) {
		// C:\\spring\\Project\\src\\main\\webapp\\resources\\uploads\\        240103113409_cRvSeZPY.png
		// 240103113409_cRvSeZPY.png
		File file = new File(UPLOAD_PATH+boardName+fileName);
		return file.delete();
		
	}
	
	public static void uploadMethod(MultipartFile upload, 
									BoardDTO board,
									HttpSession session,
									String boardName) {
		if(!upload.isEmpty()) {
			// 원본 파일명 구하기
			String originName = upload.getOriginalFilename();
			
			// 확장자 구하기
			String extension = originName.substring(originName.lastIndexOf("."));
			
			// 현재 년-월-일-시-분-초
			LocalDateTime nowDate = LocalDateTime.now();
			
			// 데이터포맷을 년월일시분초로 가공
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
			String output = nowDate.format(formatter);
			
			// 랜덤 문자열 생성
			int length = 8;
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";                                     
			
			Random random = new Random();
			String randomString = random.ints(length, 0, characters.length()) // 길이가 length인 난수 생성
								  .mapToObj(characters::charAt) // 각 난수들을 characters에서 해당하는 문자의 인덱스로 매핑
								  .map(Object::toString) // 위의 문자를 문자열로 변환
								  .collect(Collectors.joining()); // 문자열 스트림을 하나의 문자열로 변환
			
			// 파일명 : 날짜_랜덤문자열.확장자
			// 231229144025_aK834NNM.png
			String fileName = (output+"_"+randomString+extension);
			
			// 경로+파일명
			// C:\\spring\\Project\\src\\main\\webapp\\resources\\uploads\\        240103113409_cRvSeZPY.png
			// 240103113409_cRvSeZPY.png
			String filePathName = UPLOAD_PATH + boardName + fileName;
			
			// 서버에 파일 저장
			Path filePath = Paths.get(filePathName);
			
			try {
				upload.transferTo(filePath);
				
				board.setUploadPath(UPLOAD_PATH + boardName); // 업로드 경로
				board.setUploadName(fileName); // 수정된 파일명
				board.setUploadOriginName(originName); // 원본 파일명
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}






