package net.com;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IReportUtil {
	public void saveReport(HttpServletRequest request, HttpServletResponse response,
			String reportPath, String strModuleName, String fileName,
			String reportFormat, Map<String, Object> params,
			boolean shouldDisplay) throws Exception;

	
	public void displayReport(HttpServletRequest request,
			HttpServletResponse response, String strReportPath,
			String strReportFormat, Map<String, Object> params)
			throws Exception;

}
