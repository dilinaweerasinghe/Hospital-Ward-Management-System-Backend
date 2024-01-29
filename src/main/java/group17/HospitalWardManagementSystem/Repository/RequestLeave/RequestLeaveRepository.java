package group17.HospitalWardManagementSystem.Repository.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLeaveRepository extends JpaRepository<RequestLeave, Integer> {
}
