package kr.co.green.kpi.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.green.kpi.model.dto.KpiDTO;

@Repository
public class KpiDAO {

	public List<KpiDTO> kpiPool(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("kpiMapper.kpipool");
	}

}
