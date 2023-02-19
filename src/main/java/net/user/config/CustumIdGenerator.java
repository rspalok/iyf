package net.user.config;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
 

import org.hibernate.HibernateException; 
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator; 
 
 
public class CustumIdGenerator implements IdentifierGenerator {
 
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		//String prefix = "cli";
		Connection connection = session.connection(); 
		
		//GbltOtpStudentRegTrn emp=new GbltOtpStudentRegTrn();
		Date date=new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		int day   = localDate.getDayOfMonth();
		System.out.println("======================="+object.getClass().getSimpleName());
		try {
			
			if (object.getClass().getName() != "net.model.GbltOtpStudentRegTrn") {

				Statement statement = connection.createStatement();
				
				ResultSet rs = statement.executeQuery("select count(str_user_id) as Id from gblt_otp_student_reg_trn where extract(year from dt_entry) ="+year);

				if (rs.next()) {
					Long id = (long) (rs.getInt(1) + 1);

					System.out.println("======="+id);
					// String generatedId = /*prefix +*/ new Integer(id).toString();
					return id;
				}
			}
			if (object.getClass().getName() == "net.model.GbltOtpStudentRegTrn") {
				//uuuu
				System.out.println("=======");
				Statement statement = connection.createStatement();
				String Query="select count(str_student_id) as Id from gblt_otp_reg_trn where extract(year from dt_registration) ="+year
						+" and " +" extract(month from dt_registration) ="+month;
				
				ResultSet rs = statement.executeQuery(Query);
				if (rs.next()) {
					Long id = (long) (rs.getInt(1) + 1);
					DecimalFormat df = new DecimalFormat("00000");

					DecimalFormat months = new DecimalFormat("00");

					String prefix=String.valueOf(year)+months.format(month);
					String count = df.format(id);   // Output: 0009					
					System.out.println("=====count=="+count);
					String generatedId = "IYF"+prefix + count;
					
					System.out.println("===generatedId===="+generatedId);
					return generatedId;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		 
		return null;
	}

}
