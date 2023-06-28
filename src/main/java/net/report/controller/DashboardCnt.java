package net.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.model.bean.GbltStudentBean;
import net.report.service.DashboardSer;

@Controller
@RequestMapping("report")
public class DashboardCnt {
	@Autowired DashboardSer service;

	@GetMapping("/dashboard")
	public String saveIYFCourseConfig(GbltStudentBean gbltStudentBean,Model model,HttpServletRequest request, HttpServletResponse response) {
		// save employee to database//@ModelAttribute("batch") 
		
		System.out.println("==============batch======"+gbltStudentBean); 
		model.addAttribute("attnDetails", service.getCourseAllCourserRegByStudentId("IYF20230204421"));//fromAtten

		///model.addAttribute("courseReg", service.getCourseAllCourserRegByStudentId("IYF20230204421"));
		return "report/dashboard"; 
	}
	
	@GetMapping("/studentByMobileNo")
	public @ResponseBody String studentByMobileNo( HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber ,HttpServletRequest request) {
  
		return service.getCourseAllCourserRegByMobileNo(mobileNumber, objRequest_p);
	}
}
