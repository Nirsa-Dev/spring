package kr.co.green.kpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.green.kpi.model.dto.KpiDTO;
import kr.co.green.kpi.model.service.KpiServiceImpl;

// http://localhost/admin/kpi.pool
@Controller
@RequestMapping("/admin")
public class KpiController {
	
	@Autowired
	private KpiServiceImpl kpiService;
	
	@RequestMapping("/kpi.pool")
	public String kpiPool(Model model) {
		List<KpiDTO> result = kpiService.kpiPool();
		model.addAttribute("result", result);
		return "kpi_pool";
	}
}






