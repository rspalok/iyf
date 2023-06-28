package net.transection.service;
 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.bean.GbltStudentBean; 
import net.model.transection.IyfCoureRegTrn;
import net.model.transection.IyfCoureRegTrnPk;
import net.model.transection.IyfCourseAttenTrn;
import net.repository.GbltOtpStudentRegTrnRepository;
import net.transection.dao.CourseAttenTrnDao;
import net.transection.dao.CourseRegistrationDao;  

@Transactional
@Service
public class CourseRegistrationSerImp implements CourseRegistrationSer {
	 
	
	@Autowired
	public CourseRegistrationDao dao;	
	
	@Autowired
	public CourseAttenTrnDao c_dao;
	
	@Autowired
	private GbltOtpStudentRegTrnRepository sdao;
	
	@Override
	public void saveIYFCourseConfig(GbltStudentBean iyfCoureRegTrn, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IyfCoureRegTrn iyfCoureReg=new IyfCoureRegTrn();
		if(iyfCoureRegTrn.getStStudentId()=="" ||iyfCoureRegTrn.getStStudentId()==null) {
			GbltOtpStudentRegTrn employee=new GbltOtpStudentRegTrn();
			employee.setIIsValid(1);
			employee.setEmail(iyfCoureRegTrn.getEmail());
			employee.setFirstName(iyfCoureRegTrn.getFirstName());
			employee.setLastName(iyfCoureRegTrn.getLastName());
			employee.setIMobile(iyfCoureRegTrn.getIMobile());
			employee.setStAddress(iyfCoureRegTrn.getStAddress());
			employee.setStOccupation(iyfCoureRegTrn.getStOccupation());
			employee.setmChanting(iyfCoureRegTrn.getmChanting()); 
			
			employee.setDtEntry(new Date());
			if(employee.getStOrgId()==null) {
				employee.setDtRegistration(new Date());
			}

			employee.setDtRegistration(new Date());
			//AuthenticateAction;
			HttpSession session = request.getSession();  
			GbltUserMst theUser =(GbltUserMst)session.getAttribute("username"); 
			if(theUser !=null) {
				employee.setStOwnerId(String.valueOf(theUser.getIUserId()));
				employee.setStOrgId(String.valueOf(theUser.getStOrgId()));
			}else {
				employee.setStOrgId("PNB108");
				employee.setStOwnerId("ALOK108");
			} 
			GbltOtpStudentRegTrn Id=this.sdao.save(employee);

			System.out.println("=====  employee  ===="+Id);
			iyfCoureRegTrn.setStStudentId(Id.getStStudentId());
		}
		iyfCoureReg.setStStudentId(iyfCoureRegTrn.getStStudentId());
		iyfCoureReg.setmICourseConfig(iyfCoureRegTrn.getmICourseConfig());
		iyfCoureReg.setmDtEntry(new Date());
		iyfCoureReg.setmDtRegistration(new Date());
		iyfCoureReg.setmIsValid(1);
		iyfCoureReg.setmStOwnerId("Alok108");
		iyfCoureReg.setmStOrgId("PNB108");  
		System.out.println("=====  iyfCoureRegTrn  ===="+iyfCoureRegTrn); 
		System.out.println("=====   iyfCoureReg ===="+iyfCoureReg); 
		dao.save(iyfCoureReg);
	}

	@Override
	public String getCourseRegValidStudent(Long id) {
		// TODO Auto-generated method stub 
		Object list=dao.getCourseRegValidStudent(id);
		 
		ObjectMapper mapper=new ObjectMapper(); 
        

		String data = null;
		try {
			data=mapper.writeValueAsString(list);
			System.out.println("=====00000====="+mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public List<IyfCoureRegTrn> getCourseRegValidStudentObj(Long id) {
		// TODO Auto-generated method stub
		//return dao.getCourseRegValidStudentObj(id);
		IyfCoureRegTrnPk key =new IyfCoureRegTrnPk();
		List<IyfCoureRegTrn> abc = dao.getCourseRegValidStudentObj(id);
		
		for (IyfCoureRegTrn iyfCoureRegTrn : abc) {
			iyfCoureRegTrn.getGbltOtpStudentRegTrns();
			System.out.println("======"+iyfCoureRegTrn.getGbltOtpStudentRegTrns()); 
		}
		 
		return dao.getCourseRegValidStudentObj(id);
	}

	@Override
	public String getData(Long mICourseConfig, HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		Object list=dao.getCourseRegValidStudent(mICourseConfig);
		 
		ObjectMapper mapper=new ObjectMapper(); 
        

		String data = null;
		try {
			data=mapper.writeValueAsString(list);
			System.out.println("=====00000====="+mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public void markPresent(String mStudentId, Long mICourseConfig) {
		// TODO Auto-generated method stub
		
		IyfCoureRegTrn studentDetails=dao.getRegStudentForAttendance(mStudentId,mICourseConfig);
		
		
		dao.save(studentDetails);
	}
	
	@Override
	public List<GbltOtpStudentRegTrn> getAllRegStudent(Long mICourseConfig) {
		

		System.out.println("getAllRegStudent  mICourseConfig S"+mICourseConfig);
		List<IyfCoureRegTrn> list =dao.getAllStudentId(mICourseConfig);
		List<String> studentIds = new ArrayList<>();
		for (IyfCoureRegTrn iyfCoureRegTrn : list) {
			studentIds.add(iyfCoureRegTrn.getStStudentId());

			System.out.println("studentIds  "+iyfCoureRegTrn.getStStudentId());
		}
		System.out.println("studentIds  "+studentIds);
		List<GbltOtpStudentRegTrn> list2 =dao.getAllOtpStudentIds(studentIds);
		//System.out.println("studentIds list2 "+list2);
		// TODO Auto-generated method stub
		return list2;
	}

	@Override
	public void deleteIYFCourseConfig(String id, Long configId, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
 

		IyfCoureRegTrn iyfCoureReg=dao.getRegStudentForAttendance(id,configId);
		iyfCoureReg.setmDtEntry(new Date()); 
		iyfCoureReg.setmIsValid(0);
		iyfCoureReg.setmStOwnerId("Alok108");
		iyfCoureReg.setmStOrgId("PNB108");  
		dao.save(iyfCoureReg);
	}

	@Override
	public void markattn(GbltStudentBean gbltStudentBean, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
  
		
		IyfCourseAttenTrn iyfCourseAttenTrn=new IyfCourseAttenTrn();
		iyfCourseAttenTrn.setStStudentId(gbltStudentBean.getStStudentId());
		iyfCourseAttenTrn.setmICourseConfig(gbltStudentBean.getmICourseConfig());
		iyfCourseAttenTrn.setmIClassId(gbltStudentBean.getmClassId());
		iyfCourseAttenTrn.setmDtEntry(new Date()); 
		iyfCourseAttenTrn.setmIsValid(1);
		iyfCourseAttenTrn.setmStOwnerId("Alok108");
		iyfCourseAttenTrn.setmStOrgId("PNB108"); 
		iyfCourseAttenTrn.setIsPresent(1);
		 
		IyfCourseAttenTrn var = c_dao.save(iyfCourseAttenTrn);

		System.out.println("======\n"
				+ "		System.out.println(\"==========\"+list);\n"
				+ "		System.out.println(\"==========\"+list);===="+var);
	}
	@Override
	public List<GbltOtpStudentRegTrn> getAllPresentRegStudent(GbltStudentBean gbltStudentBean) {

		System.out.println(gbltStudentBean.getmClassId()+"======getAllPresentRegStudent===="+gbltStudentBean.getmICourseConfig());
		List<IyfCourseAttenTrn> list =dao.getAllPresentStudentId(gbltStudentBean.getmICourseConfig(),gbltStudentBean.getmClassId());
		System.out.println("=========="+list);
		List<String> studentIds = new ArrayList<>();
		for (IyfCourseAttenTrn iyfCoureRegTrn : list) {
			studentIds.add(iyfCoureRegTrn.getStStudentId());

			System.out.println("studentIds  "+iyfCoureRegTrn.getStStudentId());
		}
		System.out.println("studentIds  "+studentIds);
		List<GbltOtpStudentRegTrn> list2 =dao.getAllOtpStudentIds(studentIds);
		//System.out.println("studentIds list2 "+list2);*/
		// TODO Auto-generated method stub
		return list2;
	}

	@Override
	public List<GbltOtpStudentRegTrn> getAllPresentStudent(Long mICourseConfig) {
		System.out.println("getAllRegStudent  mICourseConfig S"+mICourseConfig);
		List<IyfCourseAttenTrn> list =c_dao.getAllPresentStudent(mICourseConfig);
		List<String> studentIds = new ArrayList<>();
		for (IyfCourseAttenTrn iyfCoureRegTrn : list) {
			studentIds.add(iyfCoureRegTrn.getStStudentId());

			System.out.println("studentIds  "+iyfCoureRegTrn.getStStudentId());
		}
		System.out.println("studentIds  "+studentIds);
		List<GbltOtpStudentRegTrn> list2 =dao.getAllOtpStudentIds(studentIds);
		//System.out.println("studentIds list2 "+list2);
		// TODO Auto-generated method stub
		return list2;
	}
	 
}
