package net.transection.schedule.controller;

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
import net.master.course.service.CourseConfigSer;
import net.model.transection.pojo.schedule.IyfClassSchedTrn;
import net.transection.schedule.service.ClassScheduleSer;

@Controller
@RequestMapping("/schedule")
public class ClassScheduleCnt {
	
	@Autowired 
	private ClassScheduleSer service;
	@Autowired
	private CourseConfigSer courseConfigSer;
	
	@Autowired
	private utilityService util_service;
	
	private Long courseId = null;
	
	@GetMapping("/list")
	public String CourseConfigList(IyfClassSchedTrn iyfClassSchedTrn,Model model,HttpServletRequest request) {
		if(iyfClassSchedTrn.getmICourseConfig()!=null) {
			this.courseId=iyfClassSchedTrn.getmICourseConfig();
		}
		
		model.addAttribute("CourseConfigList", util_service.getCourseConfigList(request));

		return findPaginated(1, "mDtschedule", "desc", model,request,iyfClassSchedTrn);		
	}
	@PostMapping("/lists")
	public String CourseSpecificConfigList(IyfClassSchedTrn iyfClassSchedTrn,Model model,HttpServletRequest request) {
		
		model.addAttribute("CourseConfigList", util_service.getCourseConfigList(request));
		System.out.println("getmICourseConfig" +iyfClassSchedTrn.getmICourseConfig());
		if(iyfClassSchedTrn.getmICourseConfig()!=null) {
			this.courseId=iyfClassSchedTrn.getmICourseConfig();
		}
		return findPaginated(1, "mDtschedule", "desc", model,request,iyfClassSchedTrn);		
	}
	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model, HttpServletRequest request,IyfClassSchedTrn iyfClassSchedTrn) {
			int pageSize = 5;
			model.addAttribute("CourseConfigList", util_service.getCourseConfigList(request));

			if(iyfClassSchedTrn.getmICourseConfig()==null) {
				iyfClassSchedTrn.setmICourseConfig(this.courseId);
			}
			System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir +"getmICourseConfig" +iyfClassSchedTrn.getmICourseConfig());
			Page<IyfClassSchedTrn> page = service.findPaginated(pageNo, pageSize, sortField, sortDir,request,iyfClassSchedTrn);
			
			List<IyfClassSchedTrn> listGbltOtpStudentRegTrns = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc"); 
			model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "transection/class_schedule/class";
	}
	@GetMapping("/form")
	public String showNewGbltOtpStudentRegTrnForm(IyfClassSchedTrn iyfClassSchedTrn,Model model,HttpServletRequest request) {
		// create model attribute to bind form data   
		model.addAttribute("courseConfig", courseConfigSer.getCourseConfigList(request));
		model.addAttribute("ClassTypeMst", courseConfigSer.getClassTypeMstList(request));
		System.out.println("ClassTypeMst"+ courseConfigSer.getClassTypeMstList(request));
		return "transection/class_schedule/classNew";
	}
	@PostMapping("/save")
	public String saveClassSchedule(@ModelAttribute("iyfClassSchedTrn") IyfClassSchedTrn iyfClassSchedTrn,Model model,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		
		System.out.println("==============batch======"+iyfClassSchedTrn);
		service.saveClassSchedule(iyfClassSchedTrn, request, response);
		this.courseId=iyfClassSchedTrn.getmICourseConfig();
		return "redirect:/schedule/list";
		//return null;//CourseConfigList(null,model);
	}
	@GetMapping("/showFormForUpdate/{id}/{course}")
	public String showFormForUpdate(@PathVariable ( value = "id") Long id,@PathVariable ( value = "course") Long course, Model model,HttpServletRequest request) {

		model.addAttribute("iyfClassSchedTrn", service.getClassScheduleById(id,course,request));
		model.addAttribute("courseConfig", courseConfigSer.getCourseConfigList(request));
		
		return "transection/class_schedule/classNew";
	}

}
