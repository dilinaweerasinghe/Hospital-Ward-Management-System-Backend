package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.ProPicture;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProPictureRepository extends JpaRepository<ProPicture,Integer> {

    Optional<ProPicture> findByUser(User user);
}
