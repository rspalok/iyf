package net.dao.transection;

import java.util.List;

import net.model.GbltOtpStudentRegTrn;

public interface OtpRegAndAttendenceDao {

	List<GbltOtpStudentRegTrn> allCurrentOtpRegStudent();

}
