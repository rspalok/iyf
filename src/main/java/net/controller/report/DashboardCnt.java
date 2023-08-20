package net.controller.report;

import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

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
import net.model.master.GbltOtpStudentRegTrn;
import net.model.transection.IyfClassSchedTrn;
import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCourseAttenTrn;
import net.service.report.DashboardSer;

@Controller
@RequestMapping("report")
public class DashboardCnt {
	@Autowired DashboardSer service;
	
	@Autowired utilityService util_service;

	@GetMapping("/studentByMobileNo")
	@ResponseBody
	public String studentByMobileNo(HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber, HttpServletRequest request) {

		return util_service.studentByMobileNo(mobileNumber, objRequest_p);
	}
	
	
	@GetMapping("/student")
	public String studentReport(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {

		//get course registered list of student
		
		model.addAttribute("CourseConfigList", service.getAllRagisterdCourseList(gbltStudentBean,request));
		/*
		if(gbltStudentBean.getStStudentId()!=null || gbltStudentBean.getStStudentId() !="") {
			util_service.studentByStudentId(gbltStudentBean.getStStudentId(), request);
		}
		*/

		Set<Long> setCourse = new HashSet<Long> (); 
		if(gbltStudentBean.getStStudentId()!=null || gbltStudentBean.getStStudentId() !="") {
			List<IyfCoureRegTrn> list = service.getAllRagisterdCourseList(gbltStudentBean,request);
			model.addAttribute("CourseConfigList",list);
			for (IyfCoureRegTrn iyfCoureRegTrn : list) {
				setCourse.add(iyfCoureRegTrn.getmICourseConfig());
			}
			System.out.println("== === ==== === "+setCourse);
		}
		System.out.println("gbltStudentBean.getIMobile()  "+gbltStudentBean.getIMobile());
		if(gbltStudentBean.getIMobile()!=null) {
			List<GbltOtpStudentRegTrn> list = util_service.getStudentListByMobile(gbltStudentBean.getIMobile(), request);
			model.addAttribute("StudentList",list );
			
		} 

		if((gbltStudentBean.getStStudentId()!=null || gbltStudentBean.getStStudentId() !="") && setCourse.size()>0) {
			HashMap<Long, List<IyfClassSchedTrn>> ClassList = service.getClassListHasMap(gbltStudentBean.getStStudentId(), setCourse,request);
			HashMap<Long, List<IyfCourseAttenTrn>> AttenList = service.getAttenListHasMap(gbltStudentBean.getStStudentId(), setCourse, request);
			model.addAttribute("classList",ClassList);
			model.addAttribute("attenList",AttenList);
			model.addAttribute("classCount",ClassList.size());
			model.addAttribute("attenCount",AttenList.size());
		}
		//fetch class against that course list
		
		//fetch list of the class 
		
		//get Yatra registered 
		
		//fetch 

		//model.addAttribute("courseReg", service.getCourseAllCourserRegByStudentId("IYF20230204421"));
		return "report/student_report"; 
	}
	@PostMapping("/getStudentDetails")
	public String getStudentDetails (GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		
		
		return studentReport(gbltStudentBean, model, request, response);
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
			Result= ragistrationReport( gbltStudentBean, model, request,  response) ;
			
		}else {
			Result= attendanceReport( gbltStudentBean, model, request,  response) ;
		}
		return Result;
	}
	
	
}
