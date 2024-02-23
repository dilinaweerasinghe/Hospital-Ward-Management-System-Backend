package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward,String> {

    Ward findByWardNo(String wardNo);
}
