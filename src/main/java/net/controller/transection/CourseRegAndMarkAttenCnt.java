package net.controller.transection;

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

import net.com.utilityService;
import net.model.bean.GbltStudentBean;
import net.model.transection.IYFCourseConfig;
import net.model.transection.IyfClassSchedTrn;
import net.service.master.CourseConfigSer;
import net.service.transection.ClassScheduleSer;
import net.service.transection.CourseRegistrationSer;

@Controller
@RequestMapping("/course")
public class CourseRegAndMarkAttenCnt {

	@Autowired
	private CourseConfigSer service;

	@Autowired
	private CourseRegistrationSer ser;
	
	@Autowired
	private utilityService utilSer;

	@Autowired
	private ClassScheduleSer classScheduleService;

	@GetMapping("/list")
	public String CourseConfigList(GbltStudentBean gbltStudentBean, Model model, HttpServletRequest request) {
		return findPaginated(1, "mICourseConfig", "desc", model, request);
	}

	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model,
			HttpServletRequest request) {
		int pageSize = 15;

		System.out.println("findPaginated" + pageNo + "  pageSize :" + pageSize + "  sortField: " + sortField
				+ "  sortDir: " + sortDir);
		Page<IYFCourseConfig> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, request);

		List<IYFCourseConfig> listGbltOtpStudentRegTrns = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		System.out.println("================" + listGbltOtpStudentRegTrns);
		model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "transection/registration_attendance";
	}

	@GetMapping("/studentByMobileNo")
	@ResponseBody
	public String studentByMobileNo(HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber, HttpServletRequest request) {

		return service.studentByMobileNo(mobileNumber, objRequest_p);
	}

	@GetMapping("/registration/{Id}")
	public String forRegistration(@PathVariable(value = "Id") Long mICourseConfig, GbltStudentBean gbltStudentBean,
			Model model, HttpServletRequest request, HttpServletResponse response) {

		model.addAttribute("CourseList", "/course/list");
		model.addAttribute("CurrentUrlForRefresh", "/course/registration/" + mICourseConfig);
		gbltStudentBean.setmICourseConfig(mICourseConfig);
		model.addAttribute("regStudentCount", ser.regInfo(mICourseConfig,request));

		model.addAttribute("orgUnits", utilSer.allOrgUnits(request));
		model.addAttribute("courseConfig", service.getCourseConfigById(gbltStudentBean.getmICourseConfig(), request));
		model.addAttribute("allRegStudent", ser.getAllRegStudent(gbltStudentBean.getmICourseConfig(), request));
		return "transection/CourseRegistration";
	}

	

	@PostMapping("/registerd")
	public String registerStudentForTheCourse(GbltStudentBean gbltStudentBean, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		ser.registerStudentForTheCourse(gbltStudentBean, request, response);
		GbltStudentBean newtStudentBean = new GbltStudentBean();
		newtStudentBean.setmICourseConfig(gbltStudentBean.getmICourseConfig()); 

		return "redirect:/course/registration/"+gbltStudentBean.getmICourseConfig();
	}

	@GetMapping("/remove/{StudentId}/{CourseConfigId}")
	public String removeRegistration(@PathVariable(value = "StudentId") String mStudentId,@PathVariable(value = "CourseConfigId") Long mICourseConfig, GbltStudentBean gbltStudentBean, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		gbltStudentBean.setStStudentId(mStudentId);
		gbltStudentBean.setmICourseConfig(mICourseConfig);
		ser.removeRegistration(gbltStudentBean, request, response);
		
		return "redirect:/course/registration/"+mICourseConfig;
	}

	@GetMapping("/attendance/{Id}")
	public String updateAtendance(@PathVariable(value = "Id") Long mICourseConfig, GbltStudentBean gbltStudentBean,
			Model model, HttpServletRequest request, HttpServletResponse response) {

		model.addAttribute("CourseList", "/course/list");
		model.addAttribute("CurrentUrlForRefresh", "/course/attendance/" + mICourseConfig);

		gbltStudentBean.setmICourseConfig(mICourseConfig);
		List<IyfClassSchedTrn> classList = classScheduleService.getClassScheduleList(gbltStudentBean.getmICourseConfig(), request);
		model.addAttribute("courseConfig", service.getCourseConfigById(gbltStudentBean.getmICourseConfig(), request));
		if(classList.size()>0) {
			model.addAttribute("classList", classList);
			gbltStudentBean.setmClassId(classList.get(0).getmIClassId());
	/////		model.addAttribute("allPresentStudent", ser.getAllPresentStudentInClass(gbltStudentBean, request));

			model.addAttribute("orgUnits", utilSer.allOrgUnits(request));
			model.addAttribute("noClassSchedule", "");
			model.addAttribute("attenStudentCount", ser.attenInfo(mICourseConfig,classList.get(0).getmIClassId(), request));
			
		}else {
			model.addAttribute("noClassSchedule", "/schedule/list");
		}
	/////	model.addAttribute("allRegStudent", ser.getAllRegStudent(mICourseConfig, request));
		model.addAttribute("regStudentCount", ser.regInfo(mICourseConfig,request));
		
		return "transection/CourseAttendance";

	}

	@PostMapping("/attended")
	public String markattn(GbltStudentBean gbltStudentBean, Model model, HttpServletRequest request,
			HttpServletResponse response) {
 
		if (gbltStudentBean.getStStudentId() != null && gbltStudentBean.getmICourseConfig() != null && gbltStudentBean.getmClassId() != null) {
			ser.markattn(gbltStudentBean, request, response);
		}
		GbltStudentBean newtStudentBean = new GbltStudentBean();
		newtStudentBean.setmICourseConfig(gbltStudentBean.getmICourseConfig());
		newtStudentBean.setmClassId(gbltStudentBean.getmClassId());

		return "redirect:/course/attendance/"+gbltStudentBean.getmICourseConfig();
	}
	@GetMapping("/getRegiteredStudentList")
	@ResponseBody
	public String getRegiteredStudentList(HttpServletRequest objRequest_p,
			@RequestParam(value = "mICourseConfig", required = true) Long mICourseConfig, HttpServletRequest request) {

		return ser.getAllRegStudent(mICourseConfig, request);
	}
	
	@GetMapping("/getPresentStudentList")
	@ResponseBody
	public String getPresentStudentList(HttpServletRequest objRequest_p,
			@RequestParam(value = "mICourseConfig", required = true) Long mICourseConfig, 
			@RequestParam(value = "mClassId", required = true) Long mClassId, HttpServletRequest request) {

		GbltStudentBean gbltStudentBean = new GbltStudentBean();
		gbltStudentBean.setmICourseConfig(mICourseConfig);
		gbltStudentBean.setmClassId(mClassId);
		return ser.getAllPresentStudentInClass(gbltStudentBean, request);
	}
}
