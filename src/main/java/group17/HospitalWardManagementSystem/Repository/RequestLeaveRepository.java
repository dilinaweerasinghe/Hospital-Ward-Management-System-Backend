package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestLeaveRepository extends JpaRepository<RequestLeave, Integer> {
    // For matron to perform only filter by ward function
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s WHERE s.wardNo = :ward")
    List<RequestLeave> findRequestLeaveByWardAndLeaveStatus(@Param("ward") Ward ward);

    //For sisters to perform only filter by ward function
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s WHERE s.wardNo = :ward AND s.nic != :nic AND rl.leaveStatus = :leaveStatus")
    List<RequestLeave> findRequestLeaveByWard(@Param("ward") Ward ward,@Param("nic") String nic, @Param("leaveStatus") LeaveStatus leaveStatus);

    //For matron to perform filter by ward AND filter by position
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s JOIN User u ON u.nic = s.nic JOIN s.wardNo w JOIN w.matron m WHERE m.nic = :nic AND u.Position = :position AND rl.leaveStatus = :leaveStatus")
    List<RequestLeave> findRequestLeaveByPosition(@Param("nic") String nic, @Param("position") UserRole position, @Param("leaveStatus")LeaveStatus leaveStatus);

    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s JOIN User u ON u.nic = s.nic JOIN s.wardNo w  WHERE w.wardNo = :ward AND u.Position = :position AND rl.leaveStatus = :leaveStatus")
    List<RequestLeave> findRequestLeaveByWardAndPosition(@Param("ward") String ward, @Param("position") UserRole position, @Param("leaveStatus") LeaveStatus leaveStatus);

    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s  JOIN s.wardNo w JOIN w.matron m WHERE m.nic = :nic")
    List<RequestLeave>  findRequestLeaveByMatron(@Param("nic") String nic);

    @Query("SELECT rl FROM RequestLeave  rl JOIN rl.staff s WHERE s.nic = :nic")
    List<RequestLeave> findByNic(@Param("nic") String nic);

    @Transactional
    @Modifying
    @Query("UPDATE RequestLeave rl SET rl.leaveStatus = :status WHERE rl.leaveId = :leaveId")
    void updateLeaveStatus(int leaveId, LeaveStatus status);

}
