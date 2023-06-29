package net.service.transection;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.dao.transection.ClassScheduleDao;
import net.model.GbltUserMst;
import net.model.transection.IyfClassSchedTrn;

@Service
@Transactional
public class ClassScheduleSerImpl implements ClassScheduleSer {

	@Autowired ClassScheduleDao dao;
	
	@Override
	public Page<IyfClassSchedTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		HttpSession session = request.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		
		return this.dao.findAllByIsvalid(1, pageable,Orgid);
		// return this.dao.findAll(pageable); 
	}
	
	@Override
	public List<IyfClassSchedTrn> getClassScheduleList(Long id,HttpServletRequest request) {
		// TODO Auto-generated method stub  
		HttpSession session = request.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		System.out.println("sys date today ");
		LocalDate currentdate = LocalDate.now(); 
	      int day = currentdate.getDayOfMonth();   
	      int year = currentdate.getYear();
	      int month = currentdate. getMonthValue();
		return dao.getClassScheduleList(id,day,month,year,Orgid);
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
	public IyfClassSchedTrn getClassScheduleById(Long id,Long course,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst obj= (GbltUserMst)  session.getAttribute("user");
		String Orgid=obj.getStOrgId();
		return dao.getClassScheduleById( id, course,Orgid);
	}

}
