package group17.HospitalWardManagementSystem.Repository;


import group17.HospitalWardManagementSystem.Model.Domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

    PasswordResetToken findByToken(String passwordResetToken);
}
