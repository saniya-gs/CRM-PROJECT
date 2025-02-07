//package com.ourdept.crm_software.crm.repository;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.hr_labs.our_dept_com.hrm.domain.entities.Attendance.Attendance;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Attendance.AttendanceType;
//
//public interface AttendanceTypeRepository extends JpaRepository<AttendanceType, Long>{
//
//	 @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId AND DATE(a.punchInTime) = :date")
//	    Optional<Attendance> findAttendanceByEmployeeAndDate(long employeeId, LocalDate date);
//
//	Optional<AttendanceType> findByAttendanceType(String attendanceType);
//
//}
