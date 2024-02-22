package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    // Adjust the method to match the property name in the User entity

    Staff findByNic(String nic);

    @Query("SELECT B.nic FROM User A INNER JOIN Staff B ON B.nic=A.nic WHERE B.wardNo=:wardNo AND A.Position='Sister'")
    String findByWard(@Param("wardNo") Ward wardNo);

}

