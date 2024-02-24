package group17.HospitalWardManagementSystem.Service.MatronDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterMatronService {

    @Autowired
    private MatronRepository matronRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void RegisterMatron(){
        User user=new User();
        Matron matron=new Matron();

        user.setNic("990823099V");
        user.setFirstName("Kasun");
        user.setFullName("Kasun Dush");
        user.setLastName("Last");
        user.setUsername("Sayu");
        user.setPassword(getEncodedPassword("Sayu1234"));
        user.setEmail("Kasun@gmail.com");
        user.setPosition(UserRole.Matron);
        user.setMobileNo("0775128310");
        user.setDob(LocalDate.of(2000,9,14));
        userRepository.save(user);
        matron.setNic("990823099V");
        matronRepository.save(matron);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
