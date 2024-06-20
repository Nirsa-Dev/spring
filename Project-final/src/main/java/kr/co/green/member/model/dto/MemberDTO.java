package kr.co.green.member.model.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	private int idx = 0;
	private String email;
	private String name;
	private String pwd;
	private Date indate;
	private Date removeDate;
	
	public MemberDTO() {
		super();
	}
}



