package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    Optional<User> findByNic(String nic);
    //User findByNic(String nic);

    Optional<User> findByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.Position = :position WHERE u.nic = :nic")
    void updatePositionByNic(@Param("nic") String nic,@Param("position") UserRole position);

    @Query("SELECT A.fullName FROM User A INNER JOIN Staff B ON B.nic=A.nic WHERE B.wardNo=:wardNo AND A.Position='Sister'")
    String findBy(@Param("wardNo") Ward wardNo);

    @Query("SELECT new group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto(A.nic,A.fullName,A.email,A.mobileNo, A.Position)  FROM User A INNER JOIN Staff B ON B.nic=A.nic WHERE B.wardNo=:wardNo ANd A.Position='Nurse'")
    List<ShowStaffDto> findByWardNo(@Param("wardNo") Ward wardNo);

    @Query("SELECT u.nic FROM User u WHERE UPPER(u.Position) = UPPER('Sister')")
    List<String> findAllSisterNics();

    @Query("SELECT u.nic FROM User u WHERE u.Position = 'Nurse' OR u.Position = 'Sister'")
    List<String> findAllNics();


    @Query("SELECT a.nic FROM User a WHERE a.Position = :position")
    List<String> findAllNicByPosition(@Param("position") UserRole position);


  


}

