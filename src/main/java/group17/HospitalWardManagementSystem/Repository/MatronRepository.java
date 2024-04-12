package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatronRepository extends JpaRepository<Matron,String> {
    Matron findByNic(String nic);

    //Matron findByUsername(String username);
}
