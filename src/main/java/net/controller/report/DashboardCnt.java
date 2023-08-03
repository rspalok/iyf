package net.controller.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.com.utilityService;
import net.model.bean.GbltStudentBean;
import net.service.report.DashboardSer;

@Controller
@RequestMapping("report")
public class DashboardCnt {
	@Autowired DashboardSer service;
	
	@Autowired utilityService util_service;

	@GetMapping("/student")
	public String studentReport(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {

		//get course registered list of student
		
		model.addAttribute("CourseConfigList", service.getAllRagisterdCourseList(gbltStudentBean,request));
		//fetch class against that course list
		//fetch list of the class 
		
		//get Yatra registered 
		//fetch 

		//model.addAttribute("courseReg", service.getCourseAllCourserRegByStudentId("IYF20230204421"));
		return "report/student_report"; 
	}
	
	
	
	
	@GetMapping("/attendance")
	public String attendanceReport(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		
		gbltStudentBean.setmIMode(1);
		model.addAttribute("CourseConfigList", util_service.getCourseConfigList(request));
		///model.addAttribute("courseReg", service.getCourseAllCourserRegByStudentId("IYF20230204421"));
		return "report/attendance_report"; 
	}
	
	@GetMapping("/registration")
	public String ragistrationReport(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		///model.addAttribute("courseReg", service.getCourseAllCourserRegByStudentId("IYF20230204421"));
		gbltStudentBean.setmIMode(2);
		
		model.addAttribute("CourseConfigList", util_service.getCourseConfigList(request));
		
		return "report/registration_report"; 
	}
	
	
	@GetMapping("/studentByMobileNo")
	public @ResponseBody String studentByMobileNo( HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber ,HttpServletRequest request) {
  
		return service.getAllCourserRegStudentByMobileNo(mobileNumber, objRequest_p);
	}
	
	@PostMapping("/getDetails")
	public String getDetails (GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		 
		System.out.println("======= getmIMode   	 = "+gbltStudentBean.getmIMode());
		System.out.println("======= getmClassId  	 = "+gbltStudentBean.getmClassId());
		System.out.println("======= getmICourseConfig= "+gbltStudentBean.getmICourseConfig());
		System.out.println("======= mIRagisterReport = "+gbltStudentBean.getmIRagisterReport());
		
		if(gbltStudentBean.getmICourseConfig()!=null && gbltStudentBean.getmIMode()==1) {
			model.addAttribute("classList", service.getScheduleClassAgainstCourseConfig(gbltStudentBean.getmICourseConfig(),request));
		}
		if(gbltStudentBean.getmICourseConfig()!=null && gbltStudentBean.getmClassId() != null && gbltStudentBean.getmIMode()!=2) {
			model.addAttribute("allPresentStudent",service.getAllPresentStudentList(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(),request));
		}
		
		
		
		if(gbltStudentBean.getmICourseConfig()!=null  && gbltStudentBean.getmIMode()==2) {
			if(gbltStudentBean.getmIRagisterReport()==1) {
				model.addAttribute("allRagisterdStudent", service.getRagisterdStudentOnDateandCourseConfig(gbltStudentBean,request));
			}else {
				model.addAttribute("allRagisterdStudent",service.getAllRegisterdStudentList(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(),request));
			}
		}
		//if(gbltStudentBean.getmICourseConfig()!=null && gbltStudentBean.getmIMode()==2) {
			
		//	model.addAttribute("allRagisterdStudent", service.getRagisterdStudentOnDateandCourseConfig(gbltStudentBean,request));
		//}
		String Result;
		if(gbltStudentBean.getmIMode()==2) {
			System.out.println("=======  === = MODE= = == =  = "+gbltStudentBean.getmIMode());
			//gbltStudentBean.setmIRagisterReport(0);
			Result= ragistrationReport( gbltStudentBean, model, request,  response) ;
			
		}else {
			Result= attendanceReport( gbltStudentBean, model, request,  response) ;
		} 
		return Result;
	}
	
	
}
