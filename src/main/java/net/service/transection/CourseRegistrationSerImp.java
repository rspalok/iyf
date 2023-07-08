package net.service.transection;
 

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.dao.transection.CourseAttenTrnDao;
import net.dao.transection.CourseRegistrationDao;
import net.dao.transection.GbltOtpStudentRegTrnRepository;
import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.bean.GbltStudentBean;
import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCourseAttenTrn;
  

@Transactional
@Service
public class CourseRegistrationSerImp implements CourseRegistrationSer {
	 
	
	@Autowired
	public CourseRegistrationDao dao;	
	
	@Autowired
	public CourseAttenTrnDao c_dao;
	
	@Autowired
	private GbltOtpStudentRegTrnRepository sdao;
	
	@Override //registerStudentForTheCourse
	public void registerStudentForTheCourse(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		//Add new Student or update Existing Student if change in existing data
		GbltOtpStudentRegTrn gbltOtpStudentRegTrn = addNewOrUpdateStudent(gbltStudentBean, request, response,2);
		//Add New Student Student id
		gbltStudentBean.setStStudentId(gbltOtpStudentRegTrn.getStStudentId());
		//Register the Student for current course
		regNewStudentForCourse(gbltStudentBean, request, response);
	}

	@Override
	public String getAllRegStudent(Long mICourseConfig, HttpServletRequest objRequest_p) {
		
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		List<IyfCoureRegTrn> list =dao.getAllStudentId(mICourseConfig,Org);


		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String data = null;
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;//list.toString();
	}

	@Override
	public void removeRegistration(GbltStudentBean gbltStudentBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		
		IyfCoureRegTrn iyfCoureReg=dao.getRegStudentForAttendance(gbltStudentBean.getStStudentId(),gbltStudentBean.getmICourseConfig(),Org);
		iyfCoureReg.setmDtEntry(new Date()); 
		iyfCoureReg.setmIsValid(0);
		iyfCoureReg.setmStOwnerId(String.valueOf(theUser.getIUserId()));
		iyfCoureReg.setmStOrgId(String.valueOf(Org));
		dao.save(iyfCoureReg);
	}

	
	@Override
	public String markattn(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		GbltOtpStudentRegTrn gbltOtpStudentRegTrn=new GbltOtpStudentRegTrn ();
		//UI Message
		String updated="";
		System.out.println("===before==="+gbltStudentBean.getStStudentId());
		if(gbltStudentBean.getStStudentId()==null || gbltStudentBean.getStStudentId() == "") {
			//register if new student come for attendance
			gbltOtpStudentRegTrn = addNewOrUpdateStudent(gbltStudentBean, request, response,3);
			System.out.println("===before gbltOtpStudentRegTrn.getStStudentId()==="+gbltOtpStudentRegTrn.getStStudentId());
			//Add StudentId for Student
			gbltStudentBean.setStStudentId(gbltOtpStudentRegTrn.getStStudentId());
			System.out.println("===before regNewStudentForCourse===");
			//registered unregistered student before Attendance for current course config
			IyfCoureRegTrn result = regNewStudentForCourse(gbltStudentBean, request, response);
			System.out.println("===before markAttendence==="+result);
			//update the Attendance
			IyfCourseAttenTrn result1 = markAttendence(gbltStudentBean, request, response);
			System.out.println("===before==="+result1);
			
			updated+="You are registerd with Student Id : "+gbltOtpStudentRegTrn.getStStudentId();
		}else {
			//fetch all register student for course
			List<IyfCoureRegTrn> reglist=dao.checkIsStudentRegisterdForCourse(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getStStudentId(),Org);
			
			if(reglist.size()>0) {
				gbltOtpStudentRegTrn = addNewOrUpdateStudent(gbltStudentBean, request, response,3);
				//update the Attendance
				markAttendence(gbltStudentBean, request, response);
				//UI Message
				updated+=gbltStudentBean.getStStudentId()+" Thanks for comming, Your attendance updated succesfully";
				
			}else {
				gbltOtpStudentRegTrn = addNewOrUpdateStudent(gbltStudentBean, request, response,3);
				//registered unregistered student before Attendance for current course config
				regNewStudentForCourse(gbltStudentBean, request, response);
				//update the Attendance
				markAttendence(gbltStudentBean, request, response);
				//UI Message
				updated+=gbltStudentBean.getStStudentId()+" you are registerd for this course and your attendance updated succesfully";
			}
		}
		//UI Message
		return updated;
	}
	

	@Override
	public List<IyfCourseAttenTrn> getAllPresentStudent(GbltStudentBean gbltStudentBean, HttpServletRequest objRequest_p) {
		System.out.println("getAllRegStudent  mICourseConfig S"+gbltStudentBean);

		LocalDate currentdate = LocalDate.now();
		int day = currentdate.getDayOfMonth(); 
		int year = currentdate.getYear();
		int month = currentdate. getMonthValue();
		
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		
		List<IyfCourseAttenTrn> list =c_dao.getAllPresentStudent(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(),day,month,year,Org);
		
		c_dao.getAllPresentStudentInClass(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(), Org);
		return list;
	}
	
	@Override
	public String getAllPresentStudentInClass(GbltStudentBean gbltStudentBean, HttpServletRequest objRequest_p) {
		System.out.println("getAllRegStudent  mICourseConfig S"+gbltStudentBean);

		
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		
		List<IyfCourseAttenTrn> list =c_dao.getAllPresentStudentInClass(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId(), Org);
		
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String data = null;
		try {
			data = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	//in GbltOtpStudentRegTrn
	public GbltOtpStudentRegTrn addNewOrUpdateStudent(GbltStudentBean iyfCoureRegTrn,HttpServletRequest request, HttpServletResponse response,Integer mode) {

		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		GbltUserMst obj= (GbltUserMst) theUser;
		
		GbltOtpStudentRegTrn gbltOtpStudentRegTrn=new GbltOtpStudentRegTrn();
		if(iyfCoureRegTrn.getStStudentId()=="" ||iyfCoureRegTrn.getStStudentId()==null) {
			
			gbltOtpStudentRegTrn.setIIsValid(1);
			gbltOtpStudentRegTrn.setEmail(iyfCoureRegTrn.getEmail());
			gbltOtpStudentRegTrn.setFirstName(iyfCoureRegTrn.getFirstName());
			gbltOtpStudentRegTrn.setLastName(iyfCoureRegTrn.getLastName());
			gbltOtpStudentRegTrn.setIMobile(iyfCoureRegTrn.getIMobile());
			gbltOtpStudentRegTrn.setStAddress(iyfCoureRegTrn.getStAddress());
			gbltOtpStudentRegTrn.setStOccupation(iyfCoureRegTrn.getStOccupation());
			gbltOtpStudentRegTrn.setmChanting(iyfCoureRegTrn.getmChanting());
			//gbltOtpStudentRegTrn.setDtBirth(iyfCoureRegTrn.dt);
			gbltOtpStudentRegTrn.setDtEntry(new Date());
			if(gbltOtpStudentRegTrn.getStOrgId()==null) {
				gbltOtpStudentRegTrn.setDtRegistration(new Date());
			}
			gbltOtpStudentRegTrn.setDtRegistration(new Date());
			gbltOtpStudentRegTrn.setStOwnerId(String.valueOf(obj.getIUserId()));
			gbltOtpStudentRegTrn.setStOrgId(String.valueOf(obj.getStOrgId()));
			gbltOtpStudentRegTrn.setmRegMode(mode);
			GbltOtpStudentRegTrn Id=this.sdao.save(gbltOtpStudentRegTrn);

			return Id;
		}else {
			gbltOtpStudentRegTrn=sdao.getStudentByIds(iyfCoureRegTrn.getStStudentId(),obj.getStOrgId());
			boolean change = false;
			if(gbltOtpStudentRegTrn.getLastName()==null) {
				gbltOtpStudentRegTrn.setLastName("");
			}if(gbltOtpStudentRegTrn.getEmail()==null) {
				gbltOtpStudentRegTrn.setEmail("");
			}if(gbltOtpStudentRegTrn.getStAddress()==null) {
				gbltOtpStudentRegTrn.setStAddress("");
			}if(gbltOtpStudentRegTrn.getStOccupation()==null) {
				gbltOtpStudentRegTrn.setStOccupation("");
			}if(iyfCoureRegTrn.getLastName()==null) {
				iyfCoureRegTrn.setLastName("");
			}if(iyfCoureRegTrn.getEmail()==null) {
				iyfCoureRegTrn.setEmail("");
			}if(iyfCoureRegTrn.getStAddress()==null) {
				iyfCoureRegTrn.setStAddress("");
			}if(iyfCoureRegTrn.getStOccupation()==null) {
				iyfCoureRegTrn.setStOccupation("");
			}
			if(!gbltOtpStudentRegTrn.getFirstName().equals(iyfCoureRegTrn.getFirstName())
					|| !gbltOtpStudentRegTrn.getLastName().equals(iyfCoureRegTrn.getLastName())
					|| !gbltOtpStudentRegTrn.getEmail().equals(iyfCoureRegTrn.getEmail())
					|| !gbltOtpStudentRegTrn.getStAddress().equals(iyfCoureRegTrn.getStAddress())
					|| !gbltOtpStudentRegTrn.getStOccupation().equals(iyfCoureRegTrn.getStOccupation())
					){
				gbltOtpStudentRegTrn.setEmail(iyfCoureRegTrn.getEmail());
				gbltOtpStudentRegTrn.setFirstName(iyfCoureRegTrn.getFirstName());
				gbltOtpStudentRegTrn.setLastName(iyfCoureRegTrn.getLastName());
				gbltOtpStudentRegTrn.setStAddress(iyfCoureRegTrn.getStAddress());
				gbltOtpStudentRegTrn.setStOccupation(iyfCoureRegTrn.getStOccupation());
				change=true;
			}else if(gbltOtpStudentRegTrn.getmChanting() != iyfCoureRegTrn.getmChanting()) {
				gbltOtpStudentRegTrn.setmChanting(iyfCoureRegTrn.getmChanting()); 
				change=true;
			}
			
			if(change) {
				gbltOtpStudentRegTrn.setDtEntry(new Date());
				gbltOtpStudentRegTrn.setStOwnerId(String.valueOf(obj.getIUserId()));
				gbltOtpStudentRegTrn.setStOrgId(String.valueOf(obj.getStOrgId()));
				GbltOtpStudentRegTrn Id=this.sdao.save(gbltOtpStudentRegTrn);
				return Id;
			}
			return gbltOtpStudentRegTrn;
		}
	}
	public IyfCoureRegTrn regNewStudentForCourse(GbltStudentBean gbltStudentBean,HttpServletRequest request, HttpServletResponse response) {
		

		IyfCoureRegTrn iyfCoureReg=new IyfCoureRegTrn();
		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		GbltUserMst obj= (GbltUserMst) theUser;
		
		iyfCoureReg.setStStudentId(gbltStudentBean.getStStudentId());
		iyfCoureReg.setmICourseConfig(gbltStudentBean.getmICourseConfig());
		iyfCoureReg.setmDtEntry(new Date());
		iyfCoureReg.setmDtRegistration(new Date());
		iyfCoureReg.setmIsValid(1);

		iyfCoureReg.setmStOwnerId(String.valueOf(obj.getIUserId()));
		iyfCoureReg.setmStOrgId(String.valueOf(obj.getStOrgId()));
		IyfCoureRegTrn result = dao.save(iyfCoureReg);
		return result;
	}
	public IyfCourseAttenTrn markAttendence(GbltStudentBean gbltStudentBean,HttpServletRequest request, HttpServletResponse response) {
		IyfCourseAttenTrn iyfCourseAttenTrn=new IyfCourseAttenTrn();
		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		GbltUserMst obj= (GbltUserMst) theUser;
		iyfCourseAttenTrn.setStStudentId(gbltStudentBean.getStStudentId());
		iyfCourseAttenTrn.setmICourseConfig(gbltStudentBean.getmICourseConfig());
		iyfCourseAttenTrn.setmIClassId(gbltStudentBean.getmClassId());
		iyfCourseAttenTrn.setmDtEntry(new Date()); 
		iyfCourseAttenTrn.setmIsValid(1);
		iyfCourseAttenTrn.setmStOwnerId(String.valueOf(obj.getIUserId()));
		iyfCourseAttenTrn.setmStOrgId(String.valueOf(obj.getStOrgId()));
		iyfCourseAttenTrn.setIsPresent(1);
		
		IyfCourseAttenTrn result = c_dao.save(iyfCourseAttenTrn);
		return result;
	}

	@Override
	public Integer attenInfo(Long mICourseConfig,Long classId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		GbltUserMst obj= (GbltUserMst) theUser;
		
		return c_dao.getTotalPresentCount(mICourseConfig,classId,obj.getStOrgId());
	}

	@Override
	public Integer regInfo(Long mICourseConfig, HttpServletRequest request) {

		HttpSession session = request.getSession(); 
		Object theUser = session.getAttribute("user");
		GbltUserMst obj= (GbltUserMst) theUser;
		
		return dao.getTotalRegCount(mICourseConfig,obj.getStOrgId());
	}
}