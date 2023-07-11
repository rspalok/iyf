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

import net.model.bean.YatraBean;
import net.model.transection.YatraCruiseTrn;
import net.service.master.CourseConfigSer;
import net.service.transection.YatraMngSer;

@Controller
@RequestMapping("/yatra")
public class YatraMngController {

	@Autowired
	private YatraMngSer service;
	@Autowired
	private CourseConfigSer ser;


	@GetMapping("/list")
	public String theYatraList(YatraBean yatraBean, Model model, HttpServletRequest request) {
		return findPaginated(1, "stSanctuaryId", "desc", model, request);
	}

	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model,
			HttpServletRequest request) {
		System.out.println("============");
		int pageSize = 10;

		Page<YatraCruiseTrn> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, request);

		List<YatraCruiseTrn> listYatraCruiseTrn = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listYatraCruiseTrn", listYatraCruiseTrn);
		return "transection/yatra/yatra";
	}
	@GetMapping("/studentByMobileNo")
	@ResponseBody
	public String studentByMobileNo(HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber, HttpServletRequest request) {

		return ser.studentByMobileNo(mobileNumber, objRequest_p);
	}
	
	@GetMapping("/studentfromYatra")
	@ResponseBody
	public String studentByMobileNoFromYatraTable(HttpServletRequest objRequest_p,
			@RequestParam(value = "studentId", required = true) String studentId, 
			@RequestParam(value = "yatraCruiseId", required = true) Long yatraCruiseId,
			
			HttpServletRequest request) {
		System.out.println("===studentId=="+studentId+"====yatraCruiseId==="+yatraCruiseId);
		return service.studentByMobileNofromYatraTable(studentId, yatraCruiseId,objRequest_p);
	}
	
	@GetMapping("/registration/{Id}")
	public String forRegistration(@PathVariable(value = "Id") Long yatraCruiseId, YatraBean yatraBean,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		

		model.addAttribute("facilitatorList",service.getfacilitatorList(request));
		model.addAttribute("YatraCruise",service.getYatraCruiseById(yatraCruiseId,request));
		model.addAttribute("YatraCruiseDtl",service.getYatraCruiseById(yatraCruiseId,request).get(0));

		model.addAttribute("yatraRagisterdList",service.yatraRagisterdList(yatraCruiseId,request));
		return "transection/yatra/YatraRegistration";
	}
	@PostMapping("/registerd")
	public String registerStudentForTheCourse(YatraBean yatraBean, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		service.registerStudentForTheYatra(yatraBean, request, response);

		return "redirect:/yatra/registration/"+yatraBean.getYatraCruiseId();
	}
}
