package in.chandrbhan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.chandrbhan.entity.CitizenPlan;
import in.chandrbhan.request.SearchRequest;
import in.chandrbhan.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	@PostMapping("/searchData")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) {
		
		List<CitizenPlan> plans=service.search(search);
		model.addAttribute("plans", plans);
		
		init(model);
		return "index";
	}
	
	@GetMapping("/")
	public String indexPage(Model model){
		model.addAttribute ("search", new SearchRequest());
		init(model);
	 	
		return "index";
	}

	private void init(Model model) {

		
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuses());
	}
	
	
}
