package net.transection.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
 
import net.model.transection.IyfClassSchedTrn;

public interface ClassScheduleSer {

	Page<IyfClassSchedTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

	List<IyfClassSchedTrn> getClassScheduleList(Long id);

	void saveClassSchedule(IyfClassSchedTrn iyfClassSchedTrn, HttpServletRequest request, HttpServletResponse response);

	IyfClassSchedTrn getClassScheduleById(Long id, Long course);

}
