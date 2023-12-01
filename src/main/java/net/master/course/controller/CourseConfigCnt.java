package net.master.course.controller;

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

import net.master.course.service.CourseConfigSer;
import net.model.transection.pojo.courseconfig.IYFCourseConfig; 

@Controller
@RequestMapping("/course-config")
public class CourseConfigCnt {
  
	@Autowired
	private CourseConfigSer service;
	

	
	@GetMapping("/list")
	public String CourseConfigList(IYFCourseConfig IYFCourseConfig,Model model,HttpServletRequest request) {
		return findPaginated(1, "mICourseConfig", "desc", model,request);		
	}
	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model, HttpServletRequest request) {
			int pageSize = 15;
			
			System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
			Page<IYFCourseConfig> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, request);
			
			List<IYFCourseConfig> listGbltOtpStudentRegTrns = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			System.out.println("================"+listGbltOtpStudentRegTrns);
			model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "master/course_config";
	}
	@GetMapping("/form")
	public String showNewGbltOtpStudentRegTrnForm(Model model,HttpServletRequest request) {
		// create model attribute to bind form data
		IYFCourseConfig iyfCourseConfig = new IYFCourseConfig();
		model.addAttribute("iyfCourseConfig", iyfCourseConfig);
		
		model.addAttribute("batch", service.getBatchList(request)); 
		model.addAttribute("course", service.getCourseList(request));
		return "master/course_config_new";
	}
	@PostMapping("/save")
	public String saveIYFCourseConfig(@ModelAttribute("batch") IYFCourseConfig courseConfig,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		
		System.out.println("==============batch======"+courseConfig);
		service.saveIYFCourseConfig(courseConfig, request, response);
		return "redirect:/course-config/list";
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Long id, Model model,HttpServletRequest request) {
		
		// get gbltOtpStudentRegTrn from the service
		IYFCourseConfig iyfCourseConfig = service.getCourseConfigById(id,request);

		model.addAttribute("batch", service.getBatchList(request)); 
		model.addAttribute("course", service.getCourseList(request));
		// set gbltOtpStudentRegTrn as a model attribute to pre-populate the form
		model.addAttribute("iyfCourseConfig", iyfCourseConfig);
		return "master/course_config_new";
	}
	@GetMapping("/delete/{id}")
	public String deleteCourseConfig(@PathVariable (value = "id") Long id,HttpServletRequest request) {
		
		// call delete gbltOtpStudentRegTrn method 
		//this.gbltOtpStudentRegTrnService.deleteGbltOtpStudentRegTrnById(id);

		IYFCourseConfig courseConfig = service.getCourseConfigById(id,request);
		
		service.deleteCourseConfigById(courseConfig,request);
		return "redirect:/course-config/list";
	}
}
