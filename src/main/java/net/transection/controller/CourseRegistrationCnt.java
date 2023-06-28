package net.transection.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.master.service.CourseConfigSer;
import net.model.bean.GbltStudentBean;
import net.model.master.pojo.IYFCourseConfig;
import net.model.transection.IyfClassSchedTrn;
import net.transection.service.ClassScheduleSer;
import net.transection.service.CourseRegistrationSer; 

@Controller
@RequestMapping("/course-reg")
public class CourseRegistrationCnt {
  
	@Autowired
	private CourseConfigSer service;
	
	@Autowired 
	private CourseRegistrationSer ser;
 

	@Autowired 
	private ClassScheduleSer classScheduleService;
	
	@GetMapping("/list")
	public String CourseConfigList(IYFCourseConfig IYFCourseConfig,Model model,HttpServletRequest request) {
		return findPaginated(1, "mICourseConfig", "desc", model);		
	}
	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
			int pageSize = 5;
			
			System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
			Page<IYFCourseConfig> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
			
			List<IYFCourseConfig> listGbltOtpStudentRegTrns = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			System.out.println("================"+listGbltOtpStudentRegTrns);
			model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "transection/CourseRegistration";
	}
	@GetMapping("/form")
	public String showNewGbltOtpStudentRegTrnForm( Model model,HttpServletRequest request) {
		// create model attribute to bind form data 
		
		model.addAttribute("batch", service.getBatchList()); 
		model.addAttribute("course", service.getCourseList());
		return "transection/CourseRegistrationNew";
	}
	@PostMapping("/save")
	public String saveIYFCourseConfig(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		// save employee to database//@ModelAttribute("batch") 
		
		System.out.println("==============batch======"+gbltStudentBean);
		ser.saveIYFCourseConfig(gbltStudentBean, request, response);
		
		return showFormForUpdate( gbltStudentBean.getmICourseConfig(),  model, request); 
	}
	@GetMapping("/add-student/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Long id, Model model,HttpServletRequest request) {
		
		// get employee from the service
		IYFCourseConfig iyfCourseConfig = service.getCourseConfigById(id);

		model.addAttribute("batch", service.getBatchList()); 
		model.addAttribute("courseConfig", iyfCourseConfig);

		GbltStudentBean gbltStudentBean = new GbltStudentBean();
		gbltStudentBean.setmICourseConfig(iyfCourseConfig.getmICourseConfig());
		
		model.addAttribute("allRegStudent", ser.getAllRegStudent(id));

		model.addAttribute("gbltStudentBean", gbltStudentBean); 
		return "transection/CourseRegistrationNew";
	}
//	
	@GetMapping("/studentByMobileNo")
	public @ResponseBody String studentByMobileNo( HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber,HttpServletRequest request ) {
  
		return service.studentByMobileNo(mobileNumber, objRequest_p);
	}
	@GetMapping("/take-attendance/{id}")
	public String attendance(GbltStudentBean gbltStudentBean,@PathVariable (value = "id") Long id, Model model,HttpServletRequest request) {
		System.out.println("attendance  "+id);
		gbltStudentBean.setmICourseConfig(id);
		return attendanceList(gbltStudentBean, id, model, request);
	}
	
	public String attendanceList( GbltStudentBean gbltStudentBean, Long id, Model model,HttpServletRequest request)  {
		System.out.println("attendanceList"+id);
		List <IyfClassSchedTrn> iyfClassSchedTrn=classScheduleService.getClassScheduleList(id); 
		model.addAttribute("classList", iyfClassSchedTrn);

		System.out.println("iyfClassSchedTrn  "+iyfClassSchedTrn);
		model.addAttribute("allPresentRegStudent", ser.getAllPresentRegStudent(gbltStudentBean));
		//=======================

		IYFCourseConfig iyfCourseConfig = service.getCourseConfigById(id);
 
		model.addAttribute("courseConfig", iyfCourseConfig); 

		System.out.println("iyfCourseConfig  "+iyfCourseConfig);
		
		model.addAttribute("gbltStudentBean", gbltStudentBean); 
		model.addAttribute("allPresentStudent", ser.getAllPresentStudent(id));
		
		model.addAttribute("allRegStudent", ser.getAllRegStudent(id));
		
		return "transection/CourseAttendance";
	}
	@GetMapping("/delete/{id}/{configId}")
	public String delete(@PathVariable (value = "id") String id,
			@PathVariable (value = "configId") Long configId, Model model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		 
		 System.out.println("++++++++++ "+id);
		 System.out.println("++++++configId++++ "+configId);
		  

		 ser.deleteIYFCourseConfig(id,configId, request, response);
			

		 return showFormForUpdate( configId,  model, request); 
	}
	
	//@RequestMapping(value = "/getData", method = RequestMethod.GET)
	@GetMapping("/getData")
	public @ResponseBody String getData( HttpServletRequest objRequest_p,
			@RequestParam(value = "mICourseConfig", required = true) Long mICourseConfig ,HttpServletRequest request) {
  
		return ser.getData(mICourseConfig, objRequest_p);
	}
	
	//present
	@GetMapping("/present/{id}/{configId}/{classId}")//no in use
	public String takeAttendabce(@PathVariable (value = "id") String mStudentId,
			@PathVariable (value = "configId") Long mICourseConfig,
			@PathVariable (value = "classId") Long classId, 
			Model model,HttpServletRequest request)  throws JsonProcessingException {
		 

		System.out.println("=====mStudentId======="+mStudentId);
		System.out.println("=====configId======="+mICourseConfig);
		System.out.println("=====classId======="+classId); 
		//ser.markPresent(mStudentId,mICourseConfig);
 
		System.out.println("============");
		return attendanceList(null, mICourseConfig, model, request);
	}
	
	//markattn
	@PostMapping("/markattn")
	public String markattn(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response){
		// save employee to database//@ModelAttribute("batch") 
		
		System.out.println("==============batch======"+gbltStudentBean);
		if(gbltStudentBean.getStStudentId()!=null) {
			ser.markattn(gbltStudentBean, request, response);
		}
		
		return attendanceList(gbltStudentBean, gbltStudentBean.getmICourseConfig(),  model, request);
	}
}
