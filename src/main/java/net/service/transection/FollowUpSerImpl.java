package net.service.transection;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.dao.master.CourseConfigDao;
import net.dao.transection.FollowUpDao;
import net.dao.transection.FollowUpTrnDao;
import net.dao.transection.GbltOtpStudentRegTrnRepository;
import net.model.bean.FollowUpBean;
import net.model.master.FollowUpMaster;
import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.GbltUserMst;
import net.model.transection.FollowUpResponsePk;
import net.model.transection.FollowUpResponseTrn;
import net.model.transection.FollowUpTrn;
import net.model.transection.IYFCourseConfig;
import net.model.transection.IyfCoureRegTrn;
import net.user.dao.UserRepository;

@Service
public class FollowUpSerImpl implements FollowUpSer {

	@Autowired
	public FollowUpDao dao;
	@Autowired
	public CourseConfigDao cDao;

	@Autowired
	public FollowUpTrnDao fdao;
	
	@Autowired
	public UserRepository userDao;

	@Autowired
	private GbltOtpStudentRegTrnRepository r_dao;
	
	@Override
	public List<FollowUpMaster> getFolowUpList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		
		return dao.getFolowUpList(Org);
	}
	@Override
	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		
		return cDao.getCourseConfigList(Org);
	}
	@Override
	public List<GbltUserMst> getCallerListFromOTPStudent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		
		return userDao.getAllCallerStudent(Org);
	}
	@Override
	public void saveFollowUpDetails(FollowUpBean followUpBean, HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		String[] callerList = followUpBean.getDuallistbox_demo1();
		Integer CallerCount=callerList.length;
		//Integer followUpSize=0;
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		String user=theUser.getIUserId();

		FollowUpResponsePk pk=new FollowUpResponsePk();
		pk.setFollowUpId(followUpBean.getFollowUpId());
		pk.setStOrgId(Org);
		Integer i=0;
		if(followUpBean.getFollowUpTo()==1) {// All course registered Student
			//get all course registered Student
			List<IyfCoureRegTrn> list =dao.getAllCourseRegisteredStudent(Org);
			System.out.println("getAllCourseRegisteredStudent 1="+list);
			//divide equally and insert in caller response table
			
			if(list.size()>0) {
				for (IyfCoureRegTrn iyfCoureRegTrn : list) {

					FollowUpResponseTrn followUpResponseTrn=new FollowUpResponseTrn();
					pk.setStStudentId(iyfCoureRegTrn.getStStudentId());
					if(!dao.findById(pk).isEmpty()) {
						//followUpResponseTrn =dao.getOne(pk);
					}else {
						followUpResponseTrn.setDtEntry(new Date());
						followUpResponseTrn.setFollowUpId(followUpBean.getFollowUpId());
						followUpResponseTrn.setIsValid(1);
						followUpResponseTrn.setStOrgId(Org);
						followUpResponseTrn.setStOwnerId(user);
						followUpResponseTrn.setStStudentId(iyfCoureRegTrn.getStStudentId());
						followUpResponseTrn.setFollowUpBy(callerList[i]);
						dao.save(followUpResponseTrn);
						
						i++;
						if (i==CallerCount) {
							i=0;
						}
					}
				}
			}
			
		}else if(followUpBean.getFollowUpTo()==2) {//course specific
			//get all course specific registered Student
			List<IyfCoureRegTrn> list = dao.getAllCourseSpecificRegisteredStudent(followUpBean.getmICourseConfig(),Org);
			System.out.println("getAllCourseSpecificRegisteredStudent 2="+list);
			if(list.size()>0) {
				for (IyfCoureRegTrn iyfCoureRegTrn : list) {
					FollowUpResponseTrn followUpResponseTrn=new FollowUpResponseTrn();
					pk.setStStudentId(iyfCoureRegTrn.getStStudentId());
					if(!dao.findById(pk).isEmpty()) {
						//followUpResponseTrn =dao.getOne(pk);
					}else {
						
						followUpResponseTrn.setDtEntry(new Date());
						followUpResponseTrn.setFollowUpId(followUpBean.getFollowUpId());
						followUpResponseTrn.setIsValid(1);
						followUpResponseTrn.setStOrgId(Org);
						followUpResponseTrn.setStOwnerId(user);
						followUpResponseTrn.setStStudentId(iyfCoureRegTrn.getStStudentId());
						followUpResponseTrn.setFollowUpBy(callerList[i]);
						dao.save(followUpResponseTrn);
						i++;
						if (i==CallerCount) {
							i=0;
						}
					}
				}
			}
			//divide equally and insert in caller response table
			
		}else if(followUpBean.getFollowUpTo()==3) {//Except registered one who attended otp and not register for course
			//get all one who attended otp and not register for course
			
		       
			List<GbltOtpStudentRegTrn> result = null;
			//1 normal only registration , 2 on course registration, 
			//3 course attendance registration ,
			//5 yatra registration

		    SimpleDateFormat formatter1=new SimpleDateFormat("dd-MM-yyyy");  

		    Date date=formatter1.parse(followUpBean.getDtFrom());  
		    Date date1=formatter1.parse(followUpBean.getDtTo());  
		    
			Integer RegMode=1;
			result =r_dao.getRegisterdStudentBetweenDates(date,date1,RegMode,Org);
			

			System.out.println("getAllCourseSpecificRegisteredStudent 3="+result);
			//followUpSize=result.size();
			if(result.size()>0) {
				for (GbltOtpStudentRegTrn gbltOtpStudentRegTrn : result) {
					System.out.println("==================="+result);
					FollowUpResponseTrn followUpResponseTrn=new FollowUpResponseTrn();
					pk.setStStudentId(gbltOtpStudentRegTrn.getStStudentId());
					if(!dao.findById(pk).isEmpty()) {

						System.out.println(dao.findById(pk) +"\n========22==========="+result);
						//followUpResponseTrn =dao.getOne(pk);
					}else {
						
						followUpResponseTrn.setDtEntry(new Date());
						followUpResponseTrn.setFollowUpId(followUpBean.getFollowUpId());
						followUpResponseTrn.setIsValid(1);
						followUpResponseTrn.setStOrgId(Org);
						followUpResponseTrn.setStOwnerId(user);
						followUpResponseTrn.setStStudentId(gbltOtpStudentRegTrn.getStStudentId());
						followUpResponseTrn.setFollowUpBy(callerList[i]);
						dao.save(followUpResponseTrn);
						i++;
						if (i==CallerCount) {
							i=0;
						}
					}
				}
			}
			//List<IyfCoureRegTrn> list =dao.getAllCourseSpecificRegisteredStudent(followUpBean.getmICourseConfig(),Org);
			//followUpSize=list.size();
			System.out.println("getAllCourseSpecificRegisteredStudent 3="+followUpBean.getFollowUpTo());
			//divide equally and insert in caller response table
		}else if(followUpBean.getFollowUpTo()==4){//get all only registered Student between time period
			

			List<IyfCoureRegTrn> list =dao.getAllCourseSpecificRegisteredStudent(followUpBean.getmICourseConfig(),Org);
			//followUpSize=list.size();
			System.out.println("getAllCourseSpecificRegisteredStudent 4="+list);
			
		}
	}
	@Override
	public void saveFollowUpResponse(FollowUpBean followUpBean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		String user=theUser.getIUserId();
		FollowUpResponsePk pk=new FollowUpResponsePk();
		pk.setStStudentId(followUpBean.getStStudentId());
		pk.setFollowUpId(followUpBean.getFollowUpId());
		pk.setStOrgId(org);
		
		FollowUpResponseTrn followUpResponseTrn =dao.getOne(pk);
		followUpResponseTrn.setDtEntry(new Date());
		followUpResponseTrn.setStOrgId(org);
		followUpResponseTrn.setStOwnerId(user);
		followUpResponseTrn.setResponseType(followUpBean.getResponseType());
		followUpResponseTrn.setStResponse(followUpBean.getStResponse());
		dao.save(followUpResponseTrn);
	}
	@Override
	public List<FollowUpResponseTrn> getFolowUpStudentList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		String user=theUser.getIUserId();
		
		return dao.getFolowUpStudentList(Org);
	}
	@Override
	public List<FollowUpResponseTrn> getMyFolowUpStudentList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		String user=theUser.getIUserId();
		
		return dao.getMyFolowUpStudentList(Org,user);
	}
	@Override
	public String getStudentForFolloup(String stStudentId, Integer followUpId, HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		// TODO Auto-generated method stub
		List<FollowUpResponseTrn> list = dao.getStudentForFolloup(stStudentId,followUpId,org);
		
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
	public Page<FollowUpTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,
			HttpServletRequest request) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); 
		return this.fdao.findAllByIsvalid( 1,pageable,org);
	}
	@Override
	public void saveFollowUpConfig(FollowUpBean followUpBean, HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		GbltUserMst theUser = (GbltUserMst)session.getAttribute("user");
		String Org=theUser.getStOrgId();
		String user=theUser.getIUserId();
		FollowUpTrn ft=new FollowUpTrn();

		System.out.println("======"
				+followUpBean);
		ft.setDtEntry(new Date());
		ft.setDtEvent(followUpBean.getDtEvent());
		ft.setFollowUpMstId(followUpBean.getFollowUpId());
		ft.setIsValid(1);
		ft.setStOwnerId(user);
		ft.setStContent(followUpBean.getStContent());
		ft.setStName(followUpBean.getStName());
		ft.setStOrgId(Org);
		System.out.println(ft);
		fdao.save(ft);
	}
	@Override
	public List<FollowUpTrn> getconfigFolowUpList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return fdao.findAll();
	}
	
}
