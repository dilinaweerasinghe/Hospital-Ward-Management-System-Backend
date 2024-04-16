package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestLeaveRepository extends JpaRepository<RequestLeave, Integer> {
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s WHERE s.wardNo = :ward")
    List<RequestLeave> findRequestLeaveByWard(@Param("ward") Ward ward);

    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s WHERE s.wardNo = :ward AND s.nic IN (:nic)")
    List<RequestLeave> findRequestLeaveByWardAndPosition(@Param("ward") Ward ward, @Param("nic") List<String> nic);




}
