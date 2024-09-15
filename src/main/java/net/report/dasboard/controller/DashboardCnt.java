package net.report.dasboard.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.com.utilityService;
import net.model.bean.GbltStudentBean;
import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.model.transection.pojo.schedule.IyfClassSchedTrn;
import net.report.dasboard.service.DashboardSer;

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
	
	@GetMapping("/otp")
	public String studentOtpReport(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
	

		model.addAttribute("CourseConfigList", util_service.getCourseConfigList(request));
		List<Map> list = service.studentOtpReport(gbltStudentBean,request);
		System.out.println("=======  "+list);
		model.addAttribute("allPresentCount",list);

		model.addAttribute("allPresentStudent",service.getAllPresentStudentList(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(),request));

		return "report/otp_student_report"; 
	}
	@PostMapping("/otp")
	public String postOtp(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
	
		
		
		return studentOtpReport( gbltStudentBean, model, request,  response);

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
			 List<Map> AttenList = service.getAttenListHasMap(gbltStudentBean.getStStudentId(), setCourse, request);
			System.out.println("== === lists ==== === ");
			model.addAttribute("classList",ClassList);
			System.out.println("== === ClassList==== === "+ClassList);
			model.addAttribute("attenList",AttenList);
			System.out.println("== === AttenList==== === "+AttenList);
			model.addAttribute("classCount",ClassList.size());
			System.out.println("== === ClassList.size()==== === "+ClassList.size());
			model.addAttribute("attenCount",AttenList.size());
			System.out.println("== ===AttenList.size() ==== === "+AttenList.size());
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
		 
		System.out.println("======= getmIMode   	  = "+gbltStudentBean.getmIMode());
		System.out.println("======= getmClassId  	  = "+gbltStudentBean.getmClassId());
		System.out.println("======= getmICourseConfig = "+gbltStudentBean.getmICourseConfig());
		System.out.println("======= getmICourseConfig1= "+gbltStudentBean.getmICourseConfig1());
		System.out.println("======= mIRagisterReport  = "+gbltStudentBean.getmIRagisterReport());
		
		if(gbltStudentBean.getmICourseConfig()!=null && gbltStudentBean.getmIMode()==1) {
			System.out.println("======= level =1 ");
			model.addAttribute("classList", service.getScheduleClassAgainstCourseConfig(gbltStudentBean.getmICourseConfig(),request));
			if(gbltStudentBean.getmClassId() == null) {
				System.out.println("======= level =1.1 ");
				model.addAttribute("allPresentCount",service.getAttendanceCount(gbltStudentBean.getmICourseConfig(),request));
			}
		}
		if(gbltStudentBean.getmICourseConfig()!=null && gbltStudentBean.getmClassId() != null && gbltStudentBean.getmIMode()!=2) {
			System.out.println("======= level =2 ");
			model.addAttribute("allPresentStudent",service.getAllPresentStudentList(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(),request));
		}
		
		
		
		if(gbltStudentBean.getmICourseConfig()!=null  && gbltStudentBean.getmIMode()==2) {
			System.out.println("======= level =3 ");
			if(gbltStudentBean.getmIRagisterReport()==1) {
				System.out.println("======= level =3.1 ");
				model.addAttribute("allRagisterdStudent", service.getRagisterdStudentOnDateandCourseConfig(gbltStudentBean,request));
			}else {
				System.out.println("======= level =3.2 ");
				model.addAttribute("allRagisterdStudent",service.getAllRegisterdStudentList(gbltStudentBean,request));
			}
		}
		//if(gbltStudentBean.getmICourseConfig()!=null && gbltStudentBean.getmIMode()==2) {
			
		//	model.addAttribute("allRagisterdStudent", service.getRagisterdStudentOnDateandCourseConfig(gbltStudentBean,request));
		//}
		String Result;
		if(gbltStudentBean.getmIMode()==2) {
			System.out.println("======= level =4 ");
			Result= ragistrationReport( gbltStudentBean, model, request,  response) ;
			
		}else {
			System.out.println("======= level =4.1 ");
			Result= attendanceReport( gbltStudentBean, model, request,  response) ;
		}
		return Result;
	}
	
	@PostMapping("/genReport")
	public String genReport(GbltStudentBean gbltStudentBean, Model objMap_p, RedirectAttributes flashMap_p, HttpSession hs_p,
			HttpServletRequest objRequest_p, HttpServletResponse objResponse_p
			){	
		
		try {
			service.genReport(gbltStudentBean, objRequest_p, objResponse_p);
			}
			
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return "report/registration_report"; 
	}
	
	
}
