package net.report.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.model.transection.IyfCourseAttenTrn;
import net.report.dao.DashBoardDao;

@Service
@Transactional
public class DashboardSerImpl implements DashboardSer {
	
	@Autowired
	private DashBoardDao dao;
	
	@Override
	public String getCourseAllCourserRegByStudentId(String mStudentId) {
		// TODO Auto-generated method stub
		String OrgId="PNB108";
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
	public String getCourseAllCourserRegByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
		String OrgId="PNB108";
		List<IyfCourseAttenTrn> list = dao.getCourseAllCourserRegByMobileNo(OrgId, mobileNumber);
		
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

}
