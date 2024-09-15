package net.transection.registration.service;
 
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.model.bean.GbltUserBean;
import net.model.master.pojo.menu.MenuMaster;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrnPk;
import net.transection.registration.dao.GbltOtpStudentRegTrnRepository; 


@Service
public class GbltOtpStudentRegTrnServiceImpl implements GbltOtpStudentRegTrnService {

	@Autowired
	private GbltOtpStudentRegTrnRepository dao;

	@Override
	public List<GbltOtpStudentRegTrn> getAllGbltOtpStudentRegTrns(HttpServletRequest request) {
        return dao.findAll(Sort.by(Direction.DESC, "dtRegistration"));	
	}
 

	@Override
	public void saveGbltOtpStudentRegTrn(GbltOtpStudentRegTrn gbltOtpStudentRegTrn,HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		if(gbltOtpStudentRegTrn.getStStudentId()==null || gbltOtpStudentRegTrn.getStStudentId()=="" ) { 
			gbltOtpStudentRegTrn.setDtRegistration(new Date());
			gbltOtpStudentRegTrn.setIIsValid(1);
			gbltOtpStudentRegTrn.setDtEntry(new Date()); 
			gbltOtpStudentRegTrn.setStOwnerId(String.valueOf(((GbltUserBean) theUser).getIUserId()));
			gbltOtpStudentRegTrn.setStOrgId(String.valueOf(((GbltUserBean) theUser).getStOrgId()));
			gbltOtpStudentRegTrn.setmRegMode(1);
			this.dao.save(gbltOtpStudentRegTrn);
			 
		}else { 
			List<GbltOtpStudentRegTrn> student=dao.findStudentById(gbltOtpStudentRegTrn.getStStudentId(),String.valueOf(((GbltUserBean) theUser).getStOrgId()),1);
			if (student.size()>0) {
				GbltOtpStudentRegTrn stu=student.get(0);
				stu.setDtEntry(new Date()); 
				stu.setStOwnerId(String.valueOf(((GbltUserBean) theUser).getIUserId()));
				stu.setFirstName(gbltOtpStudentRegTrn.getFirstName());
				stu.setLastName(gbltOtpStudentRegTrn.getLastName());
				stu.setmChanting(gbltOtpStudentRegTrn.getmChanting());
				stu.setStAddress(gbltOtpStudentRegTrn.getStAddress());
				stu.setEmail(gbltOtpStudentRegTrn.getEmail());
				stu.setStOccupation(gbltOtpStudentRegTrn.getStOccupation()); 
				stu.setDtBirth(gbltOtpStudentRegTrn.getDtBirth());
				this.dao.save(stu);
			} 
		}
	}

	@Override
	public GbltOtpStudentRegTrn getGbltOtpStudentRegTrnById(String id, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");
		GbltOtpStudentRegTrnPk pid=new GbltOtpStudentRegTrnPk(obj.getStOrgId(),id);
		
		Optional<GbltOtpStudentRegTrn> optional = dao.findById(pid);
		GbltOtpStudentRegTrn gbltOtpStudentRegTrn = null;
		if (optional.isPresent()) {
			gbltOtpStudentRegTrn = optional.get();
		} else {
			throw new RuntimeException(" GbltOtpStudentRegTrn not found for id :: " + id);
		}
		return gbltOtpStudentRegTrn;
	}

	@Override
	public void deleteGbltOtpStudentRegTrnById(GbltOtpStudentRegTrn gbltOtpStudentRegTrn,HttpServletRequest request)  {
		//this.dao.deleteById(id);
		//GbltOtpStudentRegTrn gbltOtpStudentRegTrn=this.deleteGbltOtpStudentRegTrnById(id);
 
		gbltOtpStudentRegTrn.setIIsValid(0);
		this.dao.save(gbltOtpStudentRegTrn);
		
	}

	@Override
	public Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, HttpServletRequest objRequest_p) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return this.dao.findAllByIsvalid( 1,pageable,org);
	}

	@Override
	public List<GbltOtpStudentRegTrn> searchBy(String theName,HttpServletRequest request) {
		List<GbltOtpStudentRegTrn> results = null;
 
		//System.out.println("$$$$$$$$$$$$$$$S"+theUser);  
		//gbltOtpStudentRegTrn.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
		System.out.println("================ = =========");
		if (theName != null && (theName.trim().length() > 0)) {
			//results = dao.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName,String.valueOf(((GbltUserMst) theUser).getstOrgId()));
			results = dao.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
		} else {
			results = dao.findAll();;
		}

		return results;
	}


	@Override
	public List<GbltOtpStudentRegTrn> getAllValidStudent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		return this.dao.getAllValidStudent( 1,org);
	}


	@Override
	public Map<String, List<MenuMaster>> getMemuList(HttpServletRequest request) { 
		System.out.println("=====00000=mobileNumber====" );
		List<MenuMaster> list = dao.getMemuList();

		System.out.println("=====00000=mobileNumber===="+list );
		Map<String, List<MenuMaster>> map = new HashMap<>();

		List<MenuMaster> listTrn=new ArrayList<>();
		List<MenuMaster> listSetup=new ArrayList<>();
		List<MenuMaster> listReport=new ArrayList<>();
		for (MenuMaster menuMaster : list) {
			if(menuMaster.getmIMenuLevel()==1) {//transaction
				listTrn.add(menuMaster);
			}else if(menuMaster.getmIMenuLevel()==2) {//setup
				listSetup.add(menuMaster);
			}else if(menuMaster.getmIMenuLevel()==3) {//report
				listReport.add(menuMaster);
			}
		}
		map.put("transaction", listTrn);
		map.put("setup", listSetup);
		map.put("report", listReport);
		
		System.out.println("=====00000=77====" + listTrn);
		System.out.println("=====00000=77====" + listSetup);
		System.out.println("=====00000=77====" + listReport);
		
		 
		return map;
	}


	@Override
	public String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		List<GbltOtpStudentRegTrn> list = dao.getStudentByMobile(mobileNumber,org);
		for (GbltOtpStudentRegTrn gbltOtpStudentRegTrn : list) {
			gbltOtpStudentRegTrn.getDtBirth();
			System.out.println(gbltOtpStudentRegTrn.getDtBirth()+"====DOB=======");
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(list);
			System.out.println("===studentByMobileNo==00000=====" + mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	@Override
	public String studentByStudentId(String stStudentId, HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();

		System.out.println(stStudentId+"====DOB=======");
		System.out.println(org+"====DOB=======");
		List<GbltOtpStudentRegTrn> list = dao.getStudentByStudentId(stStudentId,org);
		for (GbltOtpStudentRegTrn gbltOtpStudentRegTrn : list) {
			gbltOtpStudentRegTrn.getDtBirth();
			System.out.println(gbltOtpStudentRegTrn.getDtBirth()+"====DOB=======");
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(list);
			System.out.println("===studentByMobileNo==00000=====" + mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public List<GbltOtpStudentRegTrn> allCurrentRegStudent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");

		LocalDate currentdate = LocalDate.now();
		System.out.println("Current date: " + currentdate);
		// Getting the current day
		int currentDay = currentdate.getDayOfMonth();
		System.out.println("Current day: " + currentDay);
		// Getting the current month
		Month currentMonth = currentdate.getMonth();
		System.out.println("Current month: " + currentMonth);
		// getting the current year
		int currentYear = currentdate.getYear();
		int month = currentdate. getMonthValue();
	       
		List<GbltOtpStudentRegTrn> result = null;
		//1 normal only registration , 2 on course registration, 
		//3 course attendance registration ,
		//5 yatra registration
		try {
			result = dao.allCurrentRegStudent(obj.getStOrgId(),1,currentDay,month,currentYear,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return  result;//dao.allCurrentRegStudent(obj.getStOrgId(),1,currentDay,month,currentYear);
	}


	
}
