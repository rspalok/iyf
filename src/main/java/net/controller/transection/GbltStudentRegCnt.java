package net.controller.transection;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.com.utilityService;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.MenuMaster;
import net.service.transection.GbltOtpStudentRegTrnService;
 

@Controller
public class GbltStudentRegCnt {

	@Autowired
	private GbltOtpStudentRegTrnService service;
	@Autowired
	private utilityService utilSer;
	@GetMapping("/")
	public String index(MenuMaster menuMaster,Model model,HttpServletRequest request){ 
		//service.getMemuList(user);
		Map<String, List<MenuMaster>> data = service.getMemuList(request);
		model.addAttribute("transections", data.get("transaction"));
		model.addAttribute("setups", data.get("setup"));
		model.addAttribute("reports", data.get("report"));
		return "page/index";	
	}

	@GetMapping("/home")
	public String menuList(MenuMaster menuMaster,Model model,HttpServletRequest request) throws UnsupportedEncodingException { 
		//service.getMemuList(user);
		Map<String, List<MenuMaster>> data = service.getMemuList(request);
		model.addAttribute("transections", data.get("transaction"));
		model.addAttribute("setups", data.get("setup"));
		model.addAttribute("reports", data.get("report"));
		return "transection/menu/menu_master";	
	}
	// display list of gbltOtpStudentRegTrns
	@GetMapping("/iyf")
	public String viewHomePage(Model model,HttpServletRequest request) {
		return findPaginated(1, "dtRegistration", "asc", model, request);		
	}
	
	@GetMapping("/showNewForm")
	public String showNewGbltOtpStudentRegTrnForm(Model model,HttpServletRequest request) {
		// create model attribute to bind form data
		GbltOtpStudentRegTrn gbltOtpStudentRegTrn = new GbltOtpStudentRegTrn();
		model.addAttribute("gbltOtpStudentRegTrn", gbltOtpStudentRegTrn);
		model.addAttribute("allCurrentRegStudent", service.allCurrentRegStudent(request));
		model.addAttribute("orgUnits", utilSer.allOrgUnits(request));

		return "transection/new_registration";
	}
	
	@PostMapping("/save")
	public String saveGbltOtpStudentRegTrn(@ModelAttribute("gbltOtpStudentRegTrn") GbltOtpStudentRegTrn gbltOtpStudentRegTrn,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		service.saveGbltOtpStudentRegTrn(gbltOtpStudentRegTrn, request, response);
		return "redirect:/showNewForm";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model,HttpServletRequest request) {
		
		// get gbltOtpStudentRegTrn from the service
		GbltOtpStudentRegTrn gbltOtpStudentRegTrn = service.getGbltOtpStudentRegTrnById(id,request);
		
		// set gbltOtpStudentRegTrn as a model attribute to pre-populate the form
		model.addAttribute("gbltOtpStudentRegTrn", gbltOtpStudentRegTrn);
		return "transection/new_registration";// "update_gbltOtpStudentRegTrn";//
	} 
	@GetMapping("/studentByMobileNo")
	public @ResponseBody String getData( HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber ,HttpServletRequest request) {
  
		return service.studentByMobileNo(mobileNumber, objRequest_p);
	}
	@GetMapping("/search")
	public String delete(@RequestParam("gbltOtpStudentRegTrnName") String theName, Model model,HttpServletRequest request) {
		System.out.println("============  "+theName);
		// delete the gbltOtpStudentRegTrn
		List<GbltOtpStudentRegTrn> theGbltOtpStudentRegTrns = service.searchBy(theName,request);
		
		// add to the spring model
		model.addAttribute("gbltOtpStudentRegTrns", theGbltOtpStudentRegTrns);
		
		int pageSize = 50;
		String sortField="firstName";
		String sortDir="asc";
		int pageNo=1;
		System.out.println("pageNo :"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
		Page<GbltOtpStudentRegTrn> page = service.findPaginated(pageNo, pageSize, sortField, sortDir,request);
		System.out.println("pageNo===== :"+page+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listGbltOtpStudentRegTrns", theGbltOtpStudentRegTrns);
		return "transection/index";
		
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteGbltOtpStudentRegTrn(@PathVariable (value = "id") String id,HttpServletRequest request) {
		
		// call delete gbltOtpStudentRegTrn method 
		//this.service.deleteGbltOtpStudentRegTrnById(id);

		GbltOtpStudentRegTrn gbltOtpStudentRegTrn = service.getGbltOtpStudentRegTrnById(id,request);
		
		service.deleteGbltOtpStudentRegTrnById(gbltOtpStudentRegTrn, request);
		return "redirect:/iyf";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,HttpServletRequest request) {
		int pageSize = 15;
		/*String[] strAr2 = {"firstName", "lastName","email"};  
		if(!Arrays.stream(strAr2).anyMatch(sortField::equals)) {
			System.out.println("=========="+sortField);
			sortField=""+sortField;
			System.out.println("=========="+sortField);
		}*/
		
		System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
		Page<GbltOtpStudentRegTrn> page = service.findPaginated(pageNo, pageSize, sortField, sortDir,request);
		
		List<GbltOtpStudentRegTrn> listGbltOtpStudentRegTrns = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "transection/index";
	}
}
