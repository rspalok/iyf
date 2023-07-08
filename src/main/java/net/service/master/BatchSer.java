package net.service.master;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.master.IYFBatchMst;

public interface BatchSer {

	Page<IYFBatchMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest request);

	void saveIYFBatchMst(IYFBatchMst batch, HttpServletRequest request, HttpServletResponse response);

	IYFBatchMst getBatchById(Long id,HttpServletRequest request);

	void deleteBatchById(IYFBatchMst batch,HttpServletRequest request);

}
