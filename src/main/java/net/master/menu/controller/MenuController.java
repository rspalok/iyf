package net.master.menu.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.master.menu.service.MenuService;
import net.model.master.pojo.menu.MenuMaster;
 

@Controller
@RequestMapping("/my")
public class MenuController {

	@Autowired
	private MenuService service;
	
	@GetMapping("/home")
	public String menuList(Model model,HttpServletRequest request) {
		 
		
		Map<String,MenuMaster> menuMap =service.getMemuList(request);
		model.addAttribute("employee", menuMap);
		return "menu_master";		
	}
	 
	
	@GetMapping("/new_menu")
	public String showNewMenu(MenuMaster menuMaster,Model model,HttpServletRequest request) {

		return "transection/menu/new_menu";
	}
	
	@PostMapping("/save")
	public String saveGbltOtpStudentRegTrn(@ModelAttribute("employee") MenuMaster menuMaster,HttpServletRequest request, HttpServletResponse response) {
		// save employee to database
		service.saveMenu(menuMaster, request, response);
		return "redirect:/home";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model,HttpServletRequest request) {
		
		// get employee from the service
		Optional<MenuMaster> menuMaster = service.getMenuMasterById(id,request);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("menuMaster", menuMaster);
		return "new_menu";
	}
	 
	@GetMapping("/deleteStudent/{id}")
	public String deleteMenuMaster(@PathVariable (value = "id") String id,HttpServletRequest request) {
		
		// call delete employee method 
		//this.service.deleteMenuMasterById(id);

		Optional<MenuMaster> menuMaster = service.getMenuMasterById(id,request);
		
		service.deleteMenuMasterById(menuMaster.get(),request);
		return "redirect:/home";
	}
	 
}
