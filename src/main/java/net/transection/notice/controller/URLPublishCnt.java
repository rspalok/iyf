package net.transection.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.com.utilityService;
import net.model.transection.pojo.notice.IyfPublicRegUrlTrn;

@Controller
@RequestMapping("/publish")
public class URLPublishCnt {

    @Autowired
    private utilityService uitilService;
	@GetMapping("/list")
	public String UrlList(IyfPublicRegUrlTrn iyfPublicRegUrlTrn,Model theMode,HttpServletRequest request) {
		//create default session with default user and org
 
		theMode.addAttribute("urlList", uitilService.getUrlList(request));
		return "public/publish_url";
	}
	@GetMapping("/create")
	public String CreateUrl(IyfPublicRegUrlTrn iyfPublicRegUrlTrn,Model theMode,HttpServletRequest request) {
		
		theMode.addAttribute("courseConfigList", uitilService.getCourseConfigList(request));
		return "public/create_url";
	}
}
