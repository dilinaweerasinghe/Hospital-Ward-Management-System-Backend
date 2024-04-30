package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.CasualityDays;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasualityDaysRepository extends JpaRepository<CasualityDays, Long> {
    @Query("SELECT c FROM CasualityDays c WHERE c.monday = :isMonday AND c.wardNo = :ward")
    List<CasualityDays> findCasualityDaysByMonday(@Param("isMonday") boolean isMonday, @Param("ward")Ward ward);

    @Query("SELECT c FROM CasualityDays c WHERE c.tuesday = :isTuesday AND c.wardNo = :ward")
    List<CasualityDays> findCasualityDaysByTuesday(@Param("isTuesday") boolean isTuesday , @Param("ward")Ward ward);

    @Query("SELECT c FROM CasualityDays c WHERE c.wednesday = :isWednesday AND c.wardNo = :ward")
    List<CasualityDays> findCasualityDaysByWednesday(@Param("isWednesday") boolean isWednesday , @Param("ward")Ward ward);

    @Query("SELECT c FROM CasualityDays c WHERE c.thursday = :isThursday AND c.wardNo = :ward")
    List<CasualityDays> findCasualityDaysByThursday(@Param("isThursday") boolean isThursday , @Param("ward")Ward ward);

    @Query("SELECT c FROM CasualityDays c WHERE c.friday = :isFriday AND c.wardNo = :ward")
    List<CasualityDays> findCasualityDaysByFriday(@Param("isFriday") boolean isFriday , @Param("ward")Ward ward);

    @Query("SELECT c FROM CasualityDays c WHERE c.saturday = :isSaturday AND c.wardNo = :ward")
    List<CasualityDays> findCasualityDaysBySaturday(@Param("isSaturday") boolean isSaturday , @Param("ward")Ward ward);

    CasualityDays findByWardNo(Ward ward);
}