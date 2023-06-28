package net.service;
 
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.master.MenuMaster;
import net.repository.GbltOtpStudentRegTrnRepository;
import net.user.config.CustomAuthenticationSuccessHandler; 


@Service
public class GbltOtpStudentRegTrnServiceImpl extends CustomAuthenticationSuccessHandler implements GbltOtpStudentRegTrnService {

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
			System.out.println("$$$$$$$$$$$$$$$S"+theUser);  
			employee.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
			employee.setStOrgId(String.valueOf(((GbltUserMst) theUser).getStOrgId()));
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
	public GbltOtpStudentRegTrn getGbltOtpStudentRegTrnById(String id) {
		Optional<GbltOtpStudentRegTrn> optional = dao.findById(id);
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
	public Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.dao.findAllByIsvalid( 1,pageable);
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
		Object theUser = session.getAttribute("user");
		//System.out.println("$$$$$$$$$$$$$$$S"+theUser);  
		//employee.setStOwnerId(String.valueOf(((GbltUserMst) theUser).getIUserId()));
		String.valueOf(((GbltUserMst) theUser).getStOrgId());
		return this.dao.getAllValidStudent( 1);
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
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(map);
			System.out.println("=====00000=====" + mapper.writeValueAsString(map));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}


	@Override
	public String getData(Long mobileNumber, HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		System.out.println("=====00000=mobileNumber====" + mobileNumber);
		List<Object> list = dao.getStudentByMobile(mobileNumber);
		System.out.println("=====00000=77====" + list);
		ObjectMapper mapper = new ObjectMapper();

		String data = null;
		try {
			data = mapper.writeValueAsString(list);
			System.out.println("=====00000=====" + mapper.writeValueAsString(list));
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
		GbltUserMst obj= (GbltUserMst) theUser;
		System.out.println("================ = ========="+obj);

		LocalDateTime firstOfMonth = LocalDate.now().withDayOfMonth( 1 ).atStartOfDay();
		LocalDateTime startdDate = firstOfMonth;
		LocalDateTime endDate= firstOfMonth.plusDays( 1 ).minus( 1, ChronoUnit.SECONDS );
		Date end = java.util.Date
	      .from(endDate.atZone(ZoneId.systemDefault())
	      .toInstant());
		Date start = java.util.Date
			      .from(startdDate.atZone(ZoneId.systemDefault())
			      .toInstant());
		System.out.println("================ = ========="+start+"======="+end);
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
		String stringDate= DateFor.format(date);
		
		LocalDate currentdate = LocalDate.now();
	      System.out.println("Current date: "+currentdate);
	      //Getting the current day
	      int currentDay = currentdate.getDayOfMonth();
	      System.out.println("Current day: "+currentDay);
	      //Getting the current month
	      Month currentMonth = currentdate.getMonth();
	      System.out.println("Current month: "+currentMonth);
	      //getting the current year
	      int currentYear = currentdate.getYear();
	      int month = currentdate. getMonthValue();


		System.out.println("================ = ========="+stringDate+"======="+end);
		return  dao.allCurrentRegStudent(obj.getStOrgId(),1,currentDay,month,currentYear);
	}
}
