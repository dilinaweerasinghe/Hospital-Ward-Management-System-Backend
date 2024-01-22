package group17.HospitalWardManagementSystem.Repository.Login;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}

