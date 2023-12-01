package net.transection.attendance.dao;

import java.util.List;

import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

public interface OtpRegAndAttendenceDao {

	List<GbltOtpStudentRegTrn> allCurrentOtpRegStudent();

}
