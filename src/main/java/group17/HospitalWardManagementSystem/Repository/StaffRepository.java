package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    Staff findByNic(String nic);

    @Query("SELECT B.nic FROM User A INNER JOIN Staff B ON B.nic=A.nic WHERE B.wardNo=:wardNo AND A.Position='Sister'")
    String findByWard(@Param("wardNo") Ward wardNo);

    @Query("SELECT s FROM User u INNER JOIN Staff s ON u.nic = s.nic WHERE s.wardNo = :wardNo AND u.Position = 'Sister'")
    Staff findSisterByWard(@Param("wardNo") Ward wardNo);


//    @Query("SELECT u.fullName FROM User u " +
//            "INNER JOIN Staff s ON u.nic = s.nic " +
//            "INNER JOIN Ward w ON s.wardNo = w.wardNo " +
//            "WHERE s.wardNo = :wardNo AND u.Position = 'Sister'")
//    String findSistersFullNameByWardNo(@Param("wardNo") String wardNo);

}