package net.controller.transection;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import net.model.bean.FollowUpBean;
import net.model.transection.FollowUpTrn;
import net.service.master.CourseConfigSer;
import net.service.transection.FollowUpSer;

@Controller
@RequestMapping("/follow_up")
public class FollowUpCnt {
	
	@Autowired
	private FollowUpSer servic;
	@Autowired
	private CourseConfigSer service;
	//Calling Services
	
	/*callerList -- otp reg list how's flag was ???
	 * *list to call
	 * * * Calling Content
	 * * * Update the response
	 * */
	@GetMapping("/list")
	public String CourseConfigList(FollowUpBean followUpBean, Model model, HttpServletRequest request) {
		return findPaginated(1, "dtEntry", "desc", model, request);
	}

	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model,
			HttpServletRequest request) {
		int pageSize = 15;

		System.out.println("findPaginated" + pageNo + "  pageSize :" + pageSize + "  sortField: " + sortField
				+ "  sortDir: " + sortDir);
		
		Page<FollowUpTrn> page = servic.findPaginated(pageNo, pageSize, sortField, sortDir, request);

		List<FollowUpTrn> listFollowUpTrn = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		System.out.println("================" + listFollowUpTrn);
		model.addAttribute("listFollowUpTrn", listFollowUpTrn);
		return "transection/follow_up/followUpTrn";
	}
	
	@GetMapping("/form")
	public String NewEntry(FollowUpBean followUpBean, Model model, HttpServletRequest request) {

		model.addAttribute("followUp", servic.getconfigFolowUpList(request));//servic.getFolowUpMstList(request));

		model.addAttribute("courseConfigList", servic.getCourseConfigList(request));
		model.addAttribute("CallerList", servic.getCallerListFromOTPStudent(request));
		return "transection/follow_up/CreateFollowUp";
	}
	
	@GetMapping("/create/{id}")
	private String createFolloup(@PathVariable(value = "id")Integer FollowUpId,FollowUpBean followUpBean, Model model, HttpServletRequest request) {

		followUpBean.setFollowUpId(FollowUpId);
		
		model.addAttribute("followUp", servic.getconfigFolowUpList(request));

		model.addAttribute("courseConfigList", servic.getCourseConfigList(request));
		model.addAttribute("CallerList", servic.getCallerListFromOTPStudent(request));
		return "transection/follow_up/CreateFollowUp";
	}
	
	@PostMapping("/save")
	public String Save(FollowUpBean followUpBean, HttpServletRequest request) throws ParseException {
		System.out.println("==== = ==  = = = = "+followUpBean);
		//return null;
		servic.saveFollowUpDetails(followUpBean,request);
		return "redirect:/follow_up/list";
	}
	
	@GetMapping("/configFollowup")
	public String scheduleFollowup(FollowUpBean followUpBean, Model model, HttpServletRequest request) {

		model.addAttribute("followUp", servic.getFolowUpMstList(request));
		
		return "transection/follow_up/scheduleFollowup";
	}
	@PostMapping("/saveConfig")
	public String SaveConfig(FollowUpBean followUpBean, HttpServletRequest request) throws ParseException {
		System.out.println("==== = ==  = = = = "+followUpBean);
		//return null;
		servic.saveFollowUpConfig(followUpBean,request);
		return "redirect:/follow_up/list";
	}
	@GetMapping("/followup_list")
	public String followUpList(FollowUpBean followUpBean, Model model, HttpServletRequest request) {
		
		followUpBean.setIsValid(3);
		model.addAttribute("followUpPicklist", servic.getFolowUpTrnList(request));
		model.addAttribute("followUp", servic.getFolowUpStudentList(followUpBean,request));
		return "transection/follow_up/FollowUpList";
	}
	@PostMapping("/get")
	public String getDate(FollowUpBean followUpBean, Model model, HttpServletRequest request) {
		if(followUpBean.getIsValid()==3) {
			return followUpList( followUpBean,  model,  request);
		}
		return myFollowUpList( followUpBean,  model,  request);
	}
	
	@GetMapping("/myFollowUpList")
	public String myFollowUpList(FollowUpBean followUpBean, Model model, HttpServletRequest request) {
		//model.addAttribute("mode", followUpBean.setIsValid(2));
		followUpBean.setIsValid(2);
		model.addAttribute("followUpPicklist", servic.getFolowUpTrnList(request));
		model.addAttribute("followUp", servic.getMyFolowUpStudentList(followUpBean,request));
		return "transection/follow_up/FollowUpList";
	}
	
	@GetMapping("/getStudentForFolloup")
	@ResponseBody
	public String getStudentForFolloup(HttpServletRequest objRequest_p,
			@RequestParam(value = "stStudentId", required = true) String stStudentId,
			@RequestParam(value = "followUpId", required = true) Integer followUpId,
			HttpServletRequest request) {
		
		return servic.getStudentForFolloup(stStudentId,followUpId, objRequest_p);
	}
	
	@PostMapping("/saveResponce")
	public String SaveResponse(FollowUpBean followUpBean, HttpServletRequest request) {
		servic.saveFollowUpResponse(followUpBean,request);
		if(followUpBean.getIsValid()==3) {
			return "redirect:/follow_up/followup_list";
		}
		return "redirect:/follow_up/myFollowUpList";//followup_list";
	}
}
