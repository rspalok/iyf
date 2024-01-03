package net.master.org.controller;

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

import net.master.org.service.OrgSer;
import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.org.GbltOrgUnitMst; 

@Controller
@RequestMapping("/org")
public class OrgCnt {
 
	@Autowired
	private OrgSer service;
	
	
	@GetMapping("/list")
	public String OrgUnitList(GbltOrgUnitMst gbltOrgUnitMst,Model model,HttpServletRequest request) {
		return findPaginated(1, "strName", "asc", model, request);		
	}
	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model,HttpServletRequest request) {
			int pageSize = 15;
			
			System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
			Page<GbltOrgUnitMst> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, request);
			
			List<GbltOrgUnitMst> listGbltOtpStudentRegTrns = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir); 
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			System.out.println("================"+listGbltOtpStudentRegTrns);
			model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "master/org/org_list";
	}
	@GetMapping("/form")
	public String showNewGbltOtpStudentRegTrnForm(Model model,HttpServletRequest request) {
		// create model attribute to bind form data
		GbltOrgUnitMst gbltOrgUnitMst = new GbltOrgUnitMst();
		model.addAttribute("gbltOrgUnitMst", gbltOrgUnitMst);

		List<GbltOrgMst> org = service.getOrgList(request);

		model.addAttribute("org", org);
		
		return "master/org/org_new";
	}
	@PostMapping("/save")
	public String saveGbltOrgUnitMst(@ModelAttribute("gbltOrgUnitMst") GbltOrgUnitMst gbltOrgUnitMst,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		
		System.out.println("==============batch======"+gbltOrgUnitMst);
		service.saveGbltOrgUnitMst(gbltOrgUnitMst, request, response);
		return "redirect:/org/list";
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Integer id, Model model,HttpServletRequest request) {

		List<GbltOrgMst> org = service.getOrgList(request);

		model.addAttribute("org", org);
		// get gbltOtpStudentRegTrn from the service
		GbltOrgUnitMst gbltOrgUnitMst = service.getOrgUnitById(id,request);
		
		// set gbltOtpStudentRegTrn as a model attribute to pre-populate the form
		model.addAttribute("gbltOrgUnitMst", gbltOrgUnitMst);
		return "master/org/org_new";
	}
	@GetMapping("/delete/{id}")
	public String deleteGbltOtpStudentRegTrn(@PathVariable (value = "id") Integer id,HttpServletRequest request) {
		
		// call delete gbltOtpStudentRegTrn method 
		//this.gbltOtpStudentRegTrnService.deleteGbltOtpStudentRegTrnById(id);

		GbltOrgUnitMst batch = service.getOrgUnitById(id,request);
		
		service.deleteOrgUnitById(batch,request);
		return "redirect:/org/list";
	}
}
