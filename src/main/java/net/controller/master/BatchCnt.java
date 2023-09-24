package net.controller.master;

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

import net.model.master.IYFBatchMst;
import net.service.master.BatchSer; 

@Controller
@RequestMapping("/batch")
public class BatchCnt {
 
	@Autowired
	private BatchSer service;
	
	
	@GetMapping("/list")
	public String BatchList(IYFBatchMst iyfBatchMst,Model model,HttpServletRequest request) {
		return findPaginated(1, "stName", "asc", model, request);		
	}
	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model,HttpServletRequest request) {
			int pageSize = 15;
			
			System.out.println("findPaginated"+pageNo+"  pageSize :"+ pageSize+"  sortField: "+sortField+"  sortDir: "+sortDir);
			Page<IYFBatchMst> page = service.findPaginated(pageNo, pageSize, sortField, sortDir, request);
			
			List<IYFBatchMst> listGbltOtpStudentRegTrns = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			System.out.println("================"+listGbltOtpStudentRegTrns);
			model.addAttribute("listGbltOtpStudentRegTrns", listGbltOtpStudentRegTrns);
		return "master/batch";
	}
	@GetMapping("/form")
	public String showNewGbltOtpStudentRegTrnForm(Model model,HttpServletRequest request) {
		// create model attribute to bind form data
		IYFBatchMst iyfBatchMst = new IYFBatchMst();
		model.addAttribute("batch", iyfBatchMst);
		return "master/batch_new";
	}
	@PostMapping("/save")
	public String saveIYFBatchMst(@ModelAttribute("batch") IYFBatchMst batch,HttpServletRequest request, HttpServletResponse response) {
		// save gbltOtpStudentRegTrn to database
		
		System.out.println("==============batch======"+batch);
		service.saveIYFBatchMst(batch, request, response);
		return "redirect:/batch/list";
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Long id, Model model,HttpServletRequest request) {
		
		// get gbltOtpStudentRegTrn from the service
		IYFBatchMst batch = service.getBatchById(id,request);
		
		// set gbltOtpStudentRegTrn as a model attribute to pre-populate the form
		model.addAttribute("batch", batch);
		return "master/batch_new";
	}
	@GetMapping("/delete/{id}")
	public String deleteGbltOtpStudentRegTrn(@PathVariable (value = "id") Long id,HttpServletRequest request) {
		
		// call delete gbltOtpStudentRegTrn method 
		//this.gbltOtpStudentRegTrnService.deleteGbltOtpStudentRegTrnById(id);

		IYFBatchMst batch = service.getBatchById(id,request);
		
		service.deleteBatchById(batch,request);
		return "redirect:/batch/list";
	}
}
