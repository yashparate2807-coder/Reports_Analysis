package in.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.reports.entity.CitizenPlan;
import in.reports.request.SearchRequest;
import in.reports.service.CitizenPlanService;

@Controller
public class CitizenPlanController {

	@Autowired
	private CitizenPlanService service;

	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {

		System.out.println(request);
		
		List<CitizenPlan> plans =service.search(request);
		model.addAttribute("plans", plans);
		init(model);

		return "index";
	}

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);

		return "index";
	}

	private void init(Model model) {
		
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuses());
	}
}
