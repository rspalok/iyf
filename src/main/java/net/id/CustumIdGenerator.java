package net.id;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.master.FollowUpMaster;
import net.model.transection.FollowUpTrn;
import net.model.transection.IyfClassSchedTrn; 
 
 
public class CustumIdGenerator implements IdentifierGenerator  {
 

	//@Autowired
	EntityManager sf;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		System.out.println("========id generator called ========");
		Connection connection = session.connection();  
		System.out.println("object::"+object);
		
		if (object instanceof IyfClassSchedTrn) {
			IyfClassSchedTrn tableObj = (IyfClassSchedTrn) object;

			if (tableObj.getmIClassId() != null
					&& tableObj.getmIClassId() != 0)
				return tableObj.getmIClassId();

			Map<String, Object> params = new HashMap<String, Object>();

			System.out.println("tableObj.getId().getmStOrgId()::"+tableObj.getmStOrgId());
			System.out.println("tableObj.getId().getmICourseConfig()::"+tableObj.getmICourseConfig());
			
			 try {
			        Statement statement=connection.createStatement();

			        ResultSet rs=statement.executeQuery("select count(num_course_config_id) as Id from iyf_schedule_class_trn where num_course_config_id="+tableObj.getmICourseConfig());

			        if(rs.next())
			        {
			            int id=rs.getInt(1)+1; 
			            return (long)id;
			        }
			    } catch (SQLException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
 
			
		}
		/*else if (object instanceof GbltOtpStudentRegTrn) {

			GbltOtpStudentRegTrn tableObj = (GbltOtpStudentRegTrn) object;

			if (tableObj.getStStudentId() != null
					&& tableObj.getStStudentId() != "")
				return tableObj.getStStudentId();
			

			Map<String, Object> params = new HashMap<String, Object>();

			System.out.println("tableObj.getId().getGnumHospitalCode()::"+tableObj.getStOrgId());
			params.put("hCode", tableObj.getStOrgId());
			
			return new HibernateConnection().generateTransPk(session, "genTransPk_MstSmstClassRoom", params);
			
		}*/else if (object instanceof GbltOtpStudentRegTrn){   
			GbltOtpStudentRegTrn tableObj = (GbltOtpStudentRegTrn) object;

			if (tableObj.getStStudentId() != null
					&& tableObj.getStStudentId() != "")
				return tableObj.getStStudentId();

			Map<String, Object> params = new HashMap<String, Object>();
			
			Date date=new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year  = localDate.getYear();
			int month = localDate.getMonthValue();
			int day   = localDate.getDayOfMonth();
			System.out.println("tableObj.getId().getmStOrgId()::"+tableObj.getStOrgId());
			System.out.println("======================="+object.getClass().getSimpleName());
			String orgId=tableObj.getStOrgId();
			try {
				
				//uuuu
				System.out.println("=======");
				Statement statement = connection.createStatement();
				String Query="select count(str_student_id) as Id from iyf.gblt_otp_reg_trn where str_org_id ='"+orgId
						+ "' and extract(year from dt_registration) ="+year
						+" and extract(month from dt_registration) ="+month ;
				
				System.out.println("=====countQuery=="+Query);
				ResultSet rs = statement.executeQuery(Query);
				if (rs.next()) {
					Long id = (long) (rs.getInt(1) + 1);
					DecimalFormat df = new DecimalFormat("0000");

					DecimalFormat months = new DecimalFormat("00");

					String prefix=String.valueOf(year)+months.format(month);
					String count = df.format(id);   // Output: 0009					
					System.out.println("=====count=="+count);
					String generatedId = "IYF"+prefix + count;
					
					System.out.println("===generatedId===="+generatedId);
					return generatedId;
				}
				
		
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (object instanceof GbltUserMst) {
			GbltUserMst tableObj = (GbltUserMst) object;


			if (tableObj.getIUserId() != null)
				return tableObj.getIUserId();
			

			Map<String, Object> params = new HashMap<String, Object>();

			System.out.println("tableObj.getId().getGnumHospitalCode():::::::::"+tableObj.getStOrgId());
			params.put("hCode", tableObj.getStOrgId());
			
			 try {
			        Statement statement=connection.createStatement();

			        String Query="select count(str_org_id) as Id from iyf.gblt_user_mst where  str_org_id = '"+tableObj.getStOrgId()+"'";

			         System.out.println("+++===Query===+++"+Query); 
					ResultSet rs = statement.executeQuery(Query);
					if (rs.next()) {
						Long id = (long) (rs.getInt(1) + 1);
						DecimalFormat df = new DecimalFormat("0000");
	 
						String count = df.format(id);   // Output: 0009					
						System.out.println("=====count=="+count);
						String generatedId = "IYF"+ count;
						
						System.out.println("===generatedId===="+generatedId);
						return generatedId;//generatedId;
					}
			    } catch (SQLException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
 
			
		}else if (object instanceof FollowUpTrn){   
			FollowUpTrn tableObj = (FollowUpTrn) object;

			if (tableObj.getFollowUpId() != null)
				return tableObj.getFollowUpId();

			String orgId=tableObj.getStOrgId();
			try {
				
				//uuuu
				System.out.println("=======");
				Statement statement = connection.createStatement();
				String Query="select count(num_follow_up_id) as Id from iyf.iyf_follow_up_trn where str_org_id ='"+orgId
						 +"'";
				
				System.out.println("=====countQuery=="+Query);
				ResultSet rs = statement.executeQuery(Query);
				if (rs.next()) {
					Integer generatedId = (rs.getInt(1) + 1);

					System.out.println("=str_follow_up_id==generatedId===="+generatedId);
					return generatedId;
				}
				
		
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (object instanceof FollowUpMaster){   
			FollowUpMaster tableObj = (FollowUpMaster) object;

			if (tableObj.getFollowUpId() != null)
				return tableObj.getFollowUpId();

			String orgId=tableObj.getStOrgId();
			try {
				
				//uuuu
				System.out.println("=======");
				Statement statement = connection.createStatement();
				String Query="select count(num_mst_id) as Id from iyf.iyf_follow_up_mst where str_org_id ='"+orgId
						 ;
				
				System.out.println("=====countQuery=="+Query);
				ResultSet rs = statement.executeQuery(Query);
				if (rs.next()) {
					Integer generatedId = (rs.getInt(1) + 1);

					System.out.println("=num_mst_id==generatedId===="+generatedId);
					return generatedId;
				}
				
		
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		 
		return null;
	}

}
