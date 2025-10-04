package net.com;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.ReportEngine;

import net.model.bean.GbltUserBean;


public class ReportUtil  implements IReportUtil {

	@Override
	public void saveReport(HttpServletRequest request, HttpServletResponse response, String reportPath,
			String strModuleName, String fileName, String reportFormat, Map<String, Object> params,
			boolean shouldDisplay) throws Exception {
		// TO DO Auto-generated method stub

	}

	@Override
	public void displayReport(HttpServletRequest request,
			HttpServletResponse response, String strReportPath,
			String strReportFormat, Map<String, Object> params) throws Exception {
		EngineConfig conf = null;
		ReportEngine eng = null;
		IReportRunnable design = null;
		IRunAndRenderTask task = null;
		HTMLRenderOption options = null;
		ByteArrayOutputStream out = null;

	//	HTMLRenderContext renderContext = null; 
	//	HashMap contextMap = null;
		
		boolean bRptFormat = true;
		
		String strReportRealPath = "";

		try {

			out = new ByteArrayOutputStream();

			conf = new EngineConfig();
			
			eng = new ReportEngine(conf);

			strReportRealPath = request.getSession().getServletContext()
					.getRealPath(strReportPath); 

			design = eng.openReportDesign(strReportRealPath);

			HttpSession session = request.getSession(); 
			GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
			String org= theUser.getStOrgId();
			
			
						
			task = eng.createRunAndRenderTask(design);

						
			options = new HTMLRenderOption();
			
			
			
		//	options.setImageDirectory("image");
			
			options.setImageHandler(new HTMLServerImageHandler());
			options.setImageDirectory(request.getSession().getServletContext().getRealPath("/hisglobal/images")); 
			options.setBaseImageURL(request.getContextPath()+"/hisglobal/images");
			
			System.out.println("1"+request.getSession().getServletContext().getRealPath("/hisglobal/images"));
			System.out.println("2"+request.getContextPath());
			
/*
			renderContext = new HTMLRenderContext();
			renderContext.setImageDirectory("C:\\image");
			contextMap = new HashMap();
			contextMap.put( EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext );
			task.setAppContext( contextMap );
*/
			//options.setImageHandler( new HTMLServerImageHandler());

		System.out.println(strReportFormat);
			
			if (strReportFormat.equalsIgnoreCase("pdf")) {
				
				
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition","inline; filename=output.pdf");
						
					options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
						
				
				
				//fileName = fileName + ".pdf";

			} else if (strReportFormat.equalsIgnoreCase("xls")) {
				
				response.setContentType("application/xls");
				response.setHeader("Content-Disposition","inline; filename=output.xls");
					
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
				//fileName = fileName + ".pdf";

			} else if (strReportFormat.equalsIgnoreCase("word")) {
				
				response.setContentType("application/msword");
				response.setHeader("Content-Disposition","inline; filename=output.doc");
					
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
				//fileName = fileName + ".pdf";

			} else  {
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);

				response.setHeader("Content-Disposition",
				"inline; filename=output.html");

			}
			options.setOutputStream(out);

			task.setRenderOption(options);

			if (params == null || params.isEmpty()) {

				params = new HashMap<String, Object>();

				params.put("h_code", org);
				params.put("report_Contact", "-----------------------------------------------------------------------");
								
			} else {
				params.put("report_Contact","-----------------------------------------------------------------------");
			}
			
			params.put("lFHeading", "**End of report**" );
			params.put("lRptPageNo","Page No.");
			params.put("reportFormat",strReportFormat);
						
			if(!strReportFormat.equals("pdf"))
				bRptFormat = false;
						
			params.put("rpt_format", bRptFormat);
			
			//System.out.println("Path :"+this.getPath());
			
			task.setParameterValues(params);

		
			
			task.run();
			
		
				response.getOutputStream().write(out.toByteArray());
			

		} catch (Exception e) {

		
			e.printStackTrace();
			throw e;

			
			
		} finally {

			if (out != null)
				out = null;

			if (task != null) {
				task.close();
				task = null;
			}

			if (design != null)
				design = null;

			if (eng != null) {
				eng.destroy();
				eng = null;
			}

			if (conf != null)
				conf = null;
		}
		ByteArrayOutputStream out1 = null;
	}
}

