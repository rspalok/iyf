package net.service.transection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
 
import net.model.transection.IyfClassSchedTrn;

public interface ClassScheduleSer {

	Page<IyfClassSchedTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir, HttpServletRequest request);

	List<IyfClassSchedTrn> getClassScheduleList(Long id,HttpServletRequest request);

	void saveClassSchedule(IyfClassSchedTrn iyfClassSchedTrn, HttpServletRequest request, HttpServletResponse response);

	IyfClassSchedTrn getClassScheduleById(Long id, Long course,HttpServletRequest request);

}
