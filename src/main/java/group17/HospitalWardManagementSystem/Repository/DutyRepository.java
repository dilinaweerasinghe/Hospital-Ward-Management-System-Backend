package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Duty;
import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface DutyRepository extends JpaRepository<Duty,Long> {
    @Query("SELECT d FROM Duty d JOIN d.staff s WHERE s.nic = :nic AND d.date = :date AND d.dutyTime = :dutyTime")
    List<Duty> findDutiesByStaffAndDate(@Param("nic") String nic, @Param("date") LocalDate date, @Param("dutyTime")DutyTime dutyTime);

    @Query("SELECT d FROM Duty d JOIN d.staff s WHERE s.nic = :nic AND d.date BETWEEN :startDate AND :endDate")
    List<Duty> findDutiesByStaffAndWeek(@Param("nic") String nic, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(d) FROM Duty d JOIN d.staff s JOIN s.wardNo w WHERE w.wardNo = :ward AND d.date = :date AND d.dutyTime = :dutyTime")
    int countDutiesByWardNoDateAndDutyTime(
            @Param("ward") String ward,
            @Param("date") LocalDate date,
            @Param("dutyTime") DutyTime dutyTime
    );
//    @Query("SELECT d FROM Duty d WHERE d.date = :date AND d.dutyTime = :dutyTime")
//    Duty findByDateAndDutyTime(@Param("date") LocalDate date,@Param("dutyTime") DutyTime dutyTime);
        @Query("SELECT d FROM Duty d WHERE d.date = :date AND d.dutyTime = :dutyTime")
        Duty findByDateAndDutyTime(@Param("date") LocalDate date, @Param("dutyTime") DutyTime dutyTime);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Duty d SET d.staff = :staff WHERE d.id = :dutyId")
//    void addStaffToDuty(Long dutyId, Set<Staff> staff);
    @Modifying
    @Transactional
    @Query("UPDATE Duty d SET d.staff = :staff WHERE d.id = :dutyId")
    void addStaffToDuty(@Param("dutyId") Long dutyId, @Param("staff") Set<Staff> staff);

    @Query("SELECT u.fullName FROM Duty d JOIN d.staff s JOIN User u ON s.nic = u.nic WHERE s.wardNo = :ward AND d.dutyTime = :dutyTime AND d.date = :date")
    List<String> findDutyStaffName(@Param("ward")Ward ward, @Param("dutyTime") DutyTime dutyTime, @Param("date") LocalDate date);

}
