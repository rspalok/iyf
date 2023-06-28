package net.master.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
 
import net.model.master.pojo.IYFBatchMst;

public interface BatchSer {

	Page<IYFBatchMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

	void saveIYFBatchMst(IYFBatchMst batch, HttpServletRequest request, HttpServletResponse response);

	IYFBatchMst getBatchById(Long id);

	void deleteBatchById(IYFBatchMst batch);

}
