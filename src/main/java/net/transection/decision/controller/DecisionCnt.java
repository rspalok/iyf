package net.transection.decision.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.com.utilityService;
import net.model.transection.bean.GbltStudentRegBean;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.transection.decision.service.DecisionService;

@Controller
@RequestMapping("/decision")
public class DecisionCnt {

	@Autowired
	private DecisionService service;

	@Autowired
	private utilityService utilSer;
	@GetMapping("/update")
	public String takeDecision(GbltStudentRegBean gbltStudentRegBean,Model model,HttpServletRequest request) {
		System.out.println("======decision=======");	
		// create model attribute to bind form data
		model.addAttribute("gbltStudentRegBean", gbltStudentRegBean);
		model.addAttribute("allCurrentRegStudent", service.allCurrentRegStudent(request));
		model.addAttribute("orgUnits", utilSer.allOrgUnits(request));
		model.addAttribute("blackListType", utilSer.getBlackListType(request));
		
		return "transection/decision/user_exit";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,HttpServletRequest request) {
		int pageSize = 15;
		/*String[] strAr2 = {"firstName", "lastName","email"};  
		if(!Arrays.stream(strAr2).anyMatch(sortField::equals)) {
			System.out.println("=========="+sortField);
			sortField=""+sortField;
			System.out.println("=========="+sortField);
		}*/
		
		System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
		Page<GbltOtpStudentRegTrn> page = service.findPaginated(pageNo, pageSize, sortField, sortDir,request);
		
		List<GbltOtpStudentRegTrn> listGbltOtpStudentRegTrns = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "transection/decision/user_exit";
	}
	
	
	
	
	@PostMapping("/update")
	public String saveGbltOtpStudentRegTrn(@ModelAttribute("gbltStudentRegBean") GbltStudentRegBean gbltStudentRegBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		System.out.println("========= == "+gbltStudentRegBean);
		service.saveGbltOtpStudentRegTrn(gbltStudentRegBean, request, response);
		return takeDecision(gbltStudentRegBean, model, request);//"redirect:/decision/update";
	}
	
}
