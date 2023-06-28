package net.transection.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.model.transection.IyfClassSchedTrn;
import net.transection.dao.ClassScheduleDao;

@Service
@Transactional
public class ClassScheduleSerImpl implements ClassScheduleSer {

	@Autowired ClassScheduleDao dao;
	@Override
	public Page<IyfClassSchedTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.dao.findAllByIsvalid(1, pageable);
		// return this.dao.findAll(pageable); 
	}
	
	@Override
	public List<IyfClassSchedTrn> getClassScheduleList(Long id) {
		// TODO Auto-generated method stub  
		
		System.out.println("sys date today ");
		return dao.getClassScheduleList(id);
	}

	@Override
	public void saveClassSchedule(IyfClassSchedTrn iyfClassSchedTrn, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		// TODO Auto-generated method stub
		iyfClassSchedTrn.setmDtEntry(new Date());
		iyfClassSchedTrn.setmIsValid(1);
		iyfClassSchedTrn.setmStOwnerId("Alok108");
		iyfClassSchedTrn.setmStOrgId("PNB108"); 
		System.out.println("=========  "+iyfClassSchedTrn);
		dao.save(iyfClassSchedTrn);
	}

	@Override
	public IyfClassSchedTrn getClassScheduleById(Long id,Long course) {
		// TODO Auto-generated method stub
		return dao.getClassScheduleById( id, course);
	}

}
