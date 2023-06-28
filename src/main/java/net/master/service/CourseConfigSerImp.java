package net.master.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.master.dao.BatchDao;
import net.master.dao.CourseConfigDao;
import net.master.dao.CourseDao;
import net.model.GbltOtpStudentRegTrn;
import net.model.master.pojo.IYFBatchMst;
import net.model.master.pojo.IYFCourseConfig;
import net.model.master.pojo.IyfCourseMst;  

@Transactional
@Service
public class CourseConfigSerImp implements CourseConfigSer {
	
	@Autowired
	public CourseConfigDao dao;
	
	@Autowired
	public BatchDao bdao;

	@Autowired 
	public CourseDao cdao;
	
	
	@Override
	public Page<IYFCourseConfig> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); 
		return this.dao.findAllByIsvalid( 1,pageable);
		//return this.dao.findAll(pageable);
	}

	@Override
	public void saveIYFCourseConfig(IYFCourseConfig batch, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//IYFBatchMst [IBatch=null, stName=ALOK, dtBatchStart=Wed Feb 01 00:00:00 IST 2023, dtBatchEnd=Wed Mar 01 00:00:00 IST 2023,
		//stOwnerId=null, stOrgId=null, IsValid=null, dtEntry=null]
		
		batch.setmDtEntry(new Date());
		batch.setmIsValid(1);
		batch.setmStOwnerId("Alok108");
		batch.setmStOrgId("PNB108");
		if(batch.getmICourseConfig()==null) { 
			batch.setmICourseConfig(dao.count()+1);
		}
		dao.save(batch);
	}

	@Override
	public IYFCourseConfig getCourseConfigById(Long id) {
		System.out.println("=======getCourseConfigById===== "+id);
		List<IYFCourseConfig> optional = dao.getCourseConfigById(id);
		 
		return optional.get(0);
	}

	@Override
	public void deleteCourseConfigById(IYFCourseConfig batch) {
		
		
		batch.setmIsValid(0);
		this.dao.save(batch);
	}

	@Override
	public List<IYFBatchMst> getBatchList() {
		// TODO Auto-generated method stub
		return this.bdao.findAll();
	}

	@Override
	public List<IyfCourseMst> getCourseList() {
		// TODO Auto-generated method stub
		return this.cdao.findAll();
	}

	@Override
	public String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p) {
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
	public List<IYFCourseConfig> getCourseConfigList() {
		// TODO Auto-generated method stub
		return dao.getCourseConfigList();
	}

	

	

}
