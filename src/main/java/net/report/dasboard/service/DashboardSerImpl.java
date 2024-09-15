package net.report.dasboard.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.com.ReportUtil;
import net.model.bean.GbltStudentBean;
import net.model.bean.GbltUserBean;
import net.model.transection.pojo.attendance.IyfCoureRegTrn;
import net.model.transection.pojo.attendance.IyfCourseAttenTrn;
import net.model.transection.pojo.schedule.IyfClassSchedTrn;
import net.report.dasboard.dao.DashBoardDao;
import net.transection.attendance.dao.CourseAttenTrnDao;
import net.transection.attendance.dao.CourseRegistrationDao;
import net.transection.schedule.dao.ClassScheduleDao;

@Service
@Transactional
public class DashboardSerImpl implements DashboardSer {
	
	private static final String ReportType = "PDF";
	
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
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");
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
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");
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
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		// TODO Auto-generated method stub
		return classDao.getCourseClassScheduleById(mICourseConfig, Orgid);
	}

	@Override
	public List<IyfCourseAttenTrn> getAllPresentStudentList(Long getmICourseConfig, Long getmClassId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		List<IyfCourseAttenTrn> alist=attenDao.getAllPresentStudentInClass(getmICourseConfig, getmClassId, Orgid);
		
		List<IyfCourseAttenTrn> alistNew = new ArrayList<IyfCourseAttenTrn>();
		for (IyfCourseAttenTrn iyfCourseAttenTrn : alist) {
			if(iyfCourseAttenTrn.getObjGbltOtpStudentRegTrns().getIIsValid()==1)
				alistNew.add(iyfCourseAttenTrn);
		}
		
		return alistNew;// attenDao.getAllPresentStudentInClass(getmICourseConfig, getmClassId, Orgid);
	}

	@Override
	public List<IyfCoureRegTrn> getAllRegisterdStudentList( GbltStudentBean gbltStudentBean,HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		HttpSession session = objRequest_p.getSession(); 
		GbltUserBean obj =(GbltUserBean) session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		List<IyfCoureRegTrn> list=regDao.getCourseRegValidStudentObj( gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmICourseConfig1(),  Orgid);
		
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();  
		
		
		List<IyfCoureRegTrn> list2 = new ArrayList<>();
		for (IyfCoureRegTrn iyfCoureRegTrn : list) {
			System.out.println("====iyfCoureRegTrn==="+iyfCoureRegTrn);
			if(iyfCoureRegTrn.getObjGbltOtpStudentRegTrns().getIIsValid()==1) {
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
		}
		return list2;
	}

	@Override
	public List<IyfCoureRegTrn> getRagisterdStudentOnDateandCourseConfig(GbltStudentBean gbltStudentBean,
			HttpServletRequest request) {
		
		
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		String sDate1=gbltStudentBean.getDtRegistration();

		String[] str=sDate1.split("-");
	    
		return dao.getRagisterdStudentOnDateandCourseConfig(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]),gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmICourseConfig1(),org);

		//return dao.getRagisterdStudentOnDateandCourseConfig(stre,gbltStudentBean.getmICourseConfig(),org);
	}

	@Override
	public List<IyfCoureRegTrn> getAllRagisterdCourseList(GbltStudentBean gbltStudentBean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		System.out.println("=================================");
		return regDao.getCourseInrolledListbyStudentId(gbltStudentBean.getStStudentId(),org);
	}

	@Override
	public HashMap<Long, List<IyfClassSchedTrn>> getClassListHasMap(String studentId,Set<Long> courseSet, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		
		HashMap<Long,List<IyfClassSchedTrn> > classSchedule=new HashMap<>();
		if(courseSet.size()>0) {
			for (Long courseId : courseSet) {
				List<IyfClassSchedTrn> classList = classDao.getAllClassLst(courseId,org);
				classSchedule.put(courseId, classList);	
			} 
		}

		
		//fetch class list against course
		//classDao.getClassListOfCourseList(ids,org);
		//fetch attendance in course
		
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//String data = null;
		

		return classSchedule; 
	}
	@Override
	public  List<Map> getAttenListHasMap(String studentId,Set<Long> courseSet, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		

		List<Map> ClassAttendance = null;
		if(courseSet.size()>0) {
				 ClassAttendance=dao.ClassAttendance(studentId,courseSet,org);
				
		}
		return ClassAttendance;
	}
	@Override//Attendance
	public  List<Map> getAttendanceCount(Long mICourseConfig, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		

		List<Map> ClassAttendance = null;
		if(mICourseConfig != null) {
			 ClassAttendance=dao.getAttendanceCount(mICourseConfig,org);
		}
		return ClassAttendance;
	}

	@Override
	public List<Map> studentOtpReport(GbltStudentBean gbltStudentBean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		

		List<Map> studentOtpAttendance = null; 
		
		//ClassAttendance=dao.getAttendanceCount(mICourseConfig,org);

		studentOtpAttendance=dao.getAttendanceCount(org,gbltStudentBean);
		
		return studentOtpAttendance;
	}

	@Override
	public void genReport(GbltStudentBean gbltStudentBean, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception {
		// TODO Auto-generated method stub
			Map<String, Object> params = new HashMap<String, Object>();
			String reportPath ="";
		 reportPath ="iyf/src/main/java/net/report/attendance/atten_report.rptdesign";
		 
		 params.put("isHeaderReq", "");
		 params.put("isFooterReq", "");
			//String date = sf.format(dateObj);
		ReportUtil ts = new ReportUtil();
		ts.displayReport(objRequest_p, objResponse_p, reportPath, ReportType, params);
		
	}
	
	
}
