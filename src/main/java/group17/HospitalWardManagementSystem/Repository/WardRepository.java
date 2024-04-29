package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward,String> {

    Ward findByWardNo(String wardNo);
    Ward findByWardName(String wardName);
    @Query("SELECT DISTINCT w.wardNo FROM Ward w INNER JOIN w.matron m WHERE m.nic = :nic")
    List<String> findWardNoByMatronId(@Param("nic") String nic);


}
