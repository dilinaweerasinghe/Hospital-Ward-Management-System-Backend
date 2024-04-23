package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Duty;
import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DutyRepository extends JpaRepository<Duty,Long> {
    @Query("SELECT d FROM Duty d JOIN d.staff s WHERE s.nic = :nic AND d.date = :date AND d.dutyTime = :dutyTime")
    List<Duty> findDutiesByStaffAndDate(@Param("nic") String nic, @Param("date") LocalDate date, @Param("dutyTime")DutyTime dutyTime);

    @Query("SELECT d FROM Duty d JOIN d.staff s WHERE s.nic = :nic AND d.date BETWEEN :startDate AND :endDate")
    List<Duty> findDutiesByStaffAndWeek(@Param("nic") String nic, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
