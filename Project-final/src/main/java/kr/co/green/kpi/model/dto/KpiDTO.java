package kr.co.green.kpi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 게터
@Setter // 세터
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 매개변수가 모두 들어간 생성자
public class KpiDTO {
	private String subKpi;
	private String kpiName;
	private String mainKpi;
}
