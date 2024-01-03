package net.master.journey.controller;

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

import net.master.journey.service.CourseSer;
import net.model.master.pojo.course.IyfCourseMst;

@Controller
@RequestMapping("/journey")
public class CourseCnt {
 
	@Autowired
	private CourseSer service;
	
	
	@GetMapping("/list")
	public String CourseList(IyfCourseMst iyfCourseMst,Model model,HttpServletRequest request) {
		return findPaginated(1, "mStName", "asc", model, request);		
	}
	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model,HttpServletRequest request) {
			int pageSize = 15;
			
			System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
			Page<IyfCourseMst> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, request);
			
			List<IyfCourseMst> listGbltOtpStudentRegTrns = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir); 
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			System.out.println("================"+listGbltOtpStudentRegTrns);
			model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "master/course/course_list";
	}
	@GetMapping("/form")
	public String showNewGbltOtpStudentRegTrnForm(Model model,HttpServletRequest request) {
		// create model attribute to bind form data
		IyfCourseMst iyfCourseMst = new IyfCourseMst();
		model.addAttribute("iyfCourseMst", iyfCourseMst);
		return "master/course/course_new";
	}
	@PostMapping("/save")
	public String saveGbltCourseMst(@ModelAttribute("iyfCourseMst") IyfCourseMst iyfCourseMst,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		
		service.saveIyfCourseMst(iyfCourseMst, request, response);
		return "redirect:/journey/list";
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Integer id, Model model,HttpServletRequest request) {

		
		// get gbltOtpStudentRegTrn from the service
		IyfCourseMst iyfCourseMst = service.getOrgUnitById(id,request);
		
		// set gbltOtpStudentRegTrn as a model attribute to pre-populate the form
		model.addAttribute("iyfCourseMst", iyfCourseMst);
		return "master/course/course_new";
	}
	@GetMapping("/delete/{id}")
	public String deleteGbltOtpStudentRegTrn(@PathVariable (value = "id") Integer id,HttpServletRequest request) {
		
		// call delete gbltOtpStudentRegTrn method 
		//this.gbltOtpStudentRegTrnService.deleteGbltOtpStudentRegTrnById(id);

		IyfCourseMst batch = service.getOrgUnitById(id,request);
		
		service.deleteOrgUnitById(batch,request);
		return "redirect:/journey/list";
	}
}
