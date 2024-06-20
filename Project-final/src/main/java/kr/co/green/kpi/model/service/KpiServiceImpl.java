package kr.co.green.kpi.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.green.kpi.model.dao.KpiDAO;
import kr.co.green.kpi.model.dto.KpiDTO;

@Service
public class KpiServiceImpl implements KpiService {
	@Autowired
	private KpiDAO kpiDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<KpiDTO> kpiPool() {
		return kpiDao.kpiPool(sqlSession);
	}
}






