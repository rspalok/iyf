package net.service.master;

import java.util.Date;
import java.util.Optional;

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

import net.dao.master.BatchDao;
import net.model.GbltUserMst;
import net.model.master.pojo.IYFBatchMst;

@Transactional
@Service
public class BatchSerImp implements BatchSer {
	
	@Autowired
	public BatchDao dao;

	@Override
	public Page<IYFBatchMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest objRequest_p) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.dao.findAllByIsvalid( 1,pageable,org);
		//return this.dao.findAll(pageable);
	}

	@Override
	public void saveIYFBatchMst(IYFBatchMst batch, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//IYFBatchMst [IBatch=null, stName=ALOK, dtBatchStart=Wed Feb 01 00:00:00 IST 2023, dtBatchEnd=Wed Mar 01 00:00:00 IST 2023,
		//stOwnerId=null, stOrgId=null, IsValid=null, dtEntry=null]
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		
		batch.setDtEntry(new Date());
		batch.setIsValid(1);
		batch.setStOwnerId(theUser.getIUserId());
		batch.setStOrgId(org);
		if(batch.getIBatch()==null) { 
			batch.setIBatch(dao.count()+1);
		}
		dao.save(batch);
		
	}

	@Override
	public IYFBatchMst getBatchById(Long id,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		Optional<IYFBatchMst> optional = dao.findById(id,org);
		IYFBatchMst batch = null;
		if (optional.isPresent()) {
			batch = optional.get();
		} else {
			throw new RuntimeException(" Batch not found for id :: " + id);
		}
		return batch;
	}

	@Override
	public void deleteBatchById(IYFBatchMst batch,HttpServletRequest objRequest_p) {
		HttpSession session = objRequest_p.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		
		batch.setIsValid(0);
		batch.setStOwnerId(theUser.getIUserId());
		batch.setStOrgId(org);
		this.dao.save(batch);
	}

}