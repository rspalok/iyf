package net.service.report;

import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.dao.report.DashBoardDao;
import net.dao.transection.ClassScheduleDao;
import net.dao.transection.CourseAttenTrnDao;
import net.dao.transection.CourseRegistrationDao;
import net.model.bean.GbltStudentBean;
import net.model.master.GbltUserMst;
import net.model.transection.IyfClassSchedTrn;
import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCourseAttenTrn;

@Service
@Transactional
public class DashboardSerImpl implements DashboardSer {
	
	@Autowired
	private DashBoardDao dao;
	@Autowired 
	private ClassScheduleDao classDao;
	@Autowired 
	private CourseAttenTrnDao attenDao;

	@Autowired
	public CourseRegistrationDao regDao;	
	@Override
	public String getAllCourserRegStudentByStudentId(String mStudentId, HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String OrgId=obj.getStOrgId();
		
		List<IyfCourseAttenTrn> list = dao.getCourseAllCourserRegByStudentIdfromAtten(OrgId, mStudentId);
		
		System.out.println("list"+list);
		 
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
	public String getAllCourserRegStudentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		
		List<IyfCourseAttenTrn> list = dao.getCourseAllCourserRegByMobileNo(Orgid, mobileNumber);
		
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
	public List<IyfClassSchedTrn> getScheduleClassAgainstCourseConfig(Long mICourseConfig, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		// TODO Auto-generated method stub
		return classDao.getCourseClassScheduleById(mICourseConfig, Orgid);
	}

	@Override
	public List<IyfCourseAttenTrn> getAllPresentStudentList(Long getmICourseConfig, Long getmClassId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		return attenDao.getAllPresentStudentInClass(getmICourseConfig, getmClassId, Orgid);
	}

	@Override
	public List<IyfCoureRegTrn> getAllRegisterdStudentList(Long mICourseConfig, Long getmClassId,
			HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		List<IyfCoureRegTrn> list=regDao.getCourseRegValidStudentObj( mICourseConfig,  Orgid);
		
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();  
		
		
		List<IyfCoureRegTrn> list2 = new ArrayList<>();
		for (IyfCoureRegTrn iyfCoureRegTrn : list) {
			System.out.println("====iyfCoureRegTrn==="+iyfCoureRegTrn);
			
			try {
				Date d1 = sdformat.parse(now.toString());
			    Date d2 = sdformat.parse(iyfCoureRegTrn.getmDtRegistration().toString());
				if(d1.compareTo(d2) == 0) {
					iyfCoureRegTrn.setTempStatus(iyfCoureRegTrn.getObjGbltOtpStudentRegTrns().getLastName() + " (New)");
				}else {
					iyfCoureRegTrn.setTempStatus(iyfCoureRegTrn.getObjGbltOtpStudentRegTrns().getLastName());
				}
				list2.add(iyfCoureRegTrn);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("====iyfCoureRegTrn==="+list2);
		return list;
	}

	@Override
	public List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(GbltStudentBean gbltStudentBean,
			HttpServletRequest request) {
		
		
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		String sDate1=gbltStudentBean.getDtRegistration();

		Date date1 = null; 
	    try {
			 date1=new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return dao.getRagisterdStudentOnDateandCourseConfig(date1,gbltStudentBean.getmICourseConfig(),org);
	}

}
