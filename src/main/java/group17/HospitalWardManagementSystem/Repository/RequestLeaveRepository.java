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
    // For matron to perform only filter by ward function
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s WHERE s.wardNo = :ward")
    List<RequestLeave> findRequestLeaveByWard(@Param("ward") Ward ward);

    //For sisters to perform only filter by ward function
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s WHERE s.wardNo = :ward AND s.nic != :nic")
    List<RequestLeave> findRequestLeaveByWard(@Param("ward") Ward ward,@Param("nic") String nic);

    //For matron to perform filter by ward AND filter by position = Sister function
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s JOIN s.wardNo w JOIN Matron m WHERE m.nic = :nic AND s.nic IN (:sisterNic)")
    List<RequestLeave> findRequestLeaveByPositionSister(@Param("nic") String nic, @Param("sisterNic") List<String> sisterNic);

    //For matron to perform filter by ward AND filter by position = Nurse function
    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s JOIN User u ON u.nic = s.nic JOIN s.wardNo w JOIN Matron m WHERE m.nic = :nic AND u.Position = :position")
    List<RequestLeave> findRequestLeaveByPosition(@Param("nic") String nic, @Param("position") UserRole position);

    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s JOIN s.wardNo w  WHERE w.wardNo = :ward AND s.nic IN (:sisterNic)")
    List<RequestLeave> findRequestLeaveByWardPositionSister(@Param("ward") String ward, @Param("sisterNic") List<String> sisterNic);

    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s JOIN s.wardNo w  WHERE w.wardNo = :ward AND s.nic NOT IN (:nurseNic)")
    List<RequestLeave> findRequestLeaveByWardPositionNurse(@Param("ward") String ward, @Param("nurseNic") List<String> nurseNic);

    @Query("SELECT rl FROM RequestLeave rl JOIN rl.staff s  JOIN s.wardNo w JOIN w.matron m WHERE m.nic = :nic")
    List<RequestLeave>  findRequestLeaveByMatron(@Param("nic") String nic);

    @Query("SELECT rl FROM RequestLeave  rl JOIN rl.staff s WHERE s.nic = :nic")
    List<RequestLeave> findByNic(@Param("nic") String nic);

}
