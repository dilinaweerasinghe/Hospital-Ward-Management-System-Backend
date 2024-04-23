package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.ApprovedLeaves;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApprovedLeavesRepository extends JpaRepository<ApprovedLeaves, Integer> {
    @Query("SELECT ar FROM ApprovedLeaves ar JOIN ar.staff s JOIN s.wardNo w JOIN w.matron m WHERE m.nic = :nic AND ar.status = :leaveStatus ")
    List<ApprovedLeaves> getApprovedLeavesByStatusAndMatron(@Param("nic") String nic, @Param("leaveStatus")LeaveStatus leaveStatus);

    // Query to find records where the given date is between leaveBeginDate and leaveEndDate for a specified staff
    @Query("SELECT a FROM ApprovedLeaves a WHERE a.staff = :staff AND a.status = :status AND :givenDate BETWEEN a.leaveBeginDate AND a.leaveEndDate")
    List<ApprovedLeaves> findLeavesByStaffAndDate(
            @Param("staff") Staff staff,
            @Param("givenDate") LocalDate givenDate,
            @Param("status") LeaveStatus status
    );
}
