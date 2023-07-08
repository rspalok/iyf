package net.service.transection;
 
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

import net.dao.transection.GbltOtpStudentRegTrnRepository;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltOtpStudentRegTrnPk;
import net.model.master.GbltUserMst;
import net.model.master.MenuMaster; 


@Service
public class GbltOtpStudentRegTrnServiceImpl implements GbltOtpStudentRegTrnService {

	@Autowired
	private GbltOtpStudentRegTrnRepository dao;

	@Override
	public List<GbltOtpStudentRegTrn> getAllGbltOtpStudentRegTrns(HttpServletRequest request) {
        return dao.findAll(Sort.by(Direction.DESC, "dtRegistration"));	
	}
 

	@Override
	public void saveGbltOtpStudentRegTrn(GbltOtpStudentRegTrn employee,HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		if(employee.getStStudentId()==null || employee.getStStudentId()=="" ) { 
			employee.setDtRegistration(new Date());
			employee.setIIsValid(1);
			employee.setDtEntry(new Date()); 
			employee.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
			employee.setStOrgId(String.valueOf(((GbltUserMst) theUser).getStOrgId()));
			employee.setmRegMode(1);
			this.dao.save(employee);
			 
		}else { 
			List<GbltOtpStudentRegTrn> student=dao.findStudentById(employee.getStStudentId(),String.valueOf(((GbltUserMst) theUser).getStOrgId()),1);
			if (student.size()>0) {
				GbltOtpStudentRegTrn stu=student.get(0);
				stu.setDtEntry(new Date()); 
				stu.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
				stu.setFirstName(employee.getFirstName());
				stu.setLastName(employee.getLastName());
				stu.setmChanting(employee.getmChanting());
				stu.setStAddress(employee.getStAddress());
				stu.setEmail(employee.getEmail());
				stu.setStOccupation(employee.getStOccupation()); 
				stu.setDtBirth(employee.getDtBirth());
				this.dao.save(stu);
			} 
		}
	}

	@Override
	public GbltOtpStudentRegTrn getGbltOtpStudentRegTrnById(String id, HttpServletRequest objRequest_p) {
		GbltOtpStudentRegTrnPk pid=new GbltOtpStudentRegTrnPk("PNB108",id);
		
		Optional<GbltOtpStudentRegTrn> optional = dao.findById(pid);
		GbltOtpStudentRegTrn employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" GbltOtpStudentRegTrn not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteGbltOtpStudentRegTrnById(GbltOtpStudentRegTrn employee,HttpServletRequest request)  {
		//this.dao.deleteById(id);
		//GbltOtpStudentRegTrn employee=this.deleteGbltOtpStudentRegTrnById(id);
 
		employee.setIIsValid(0);
		this.dao.save(employee);
		
	}

	@Override
	public Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, HttpServletRequest objRequest_p) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return this.dao.findAllByIsvalid( 1,pageable,org);
	}

	@Override
	public List<GbltOtpStudentRegTrn> searchBy(String theName,HttpServletRequest request) {
		List<GbltOtpStudentRegTrn> results = null;

		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		//System.out.println("$$$$$$$$$$$$$$$S"+theUser);  
		//employee.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
		String.valueOf(((GbltUserMst) theUser).getStOrgId());
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
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
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
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
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
	public List<GbltOtpStudentRegTrn> allCurrentRegStudent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Object theUser = session.getAttribute("user");
		GbltUserMst obj = (GbltUserMst) theUser;

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
