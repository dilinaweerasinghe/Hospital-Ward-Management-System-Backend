package group17.HospitalWardManagementSystem.Service.Login;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.Login.UserRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User userdto) {
        User user = new User();
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setFullName(userdto.getFullName());
        user.setUsername(userdto.getUsername());
        user.setNic(userdto.getNic());
        user.setPassword(getEncodedPassword(userdto.getPassword()));
        user.setPosition(userdto.getPosition());

        userRepository.save(user);
    }
    public void initUser(){
        User user = new User();
        user.setFullName("K L D K T Weerasinghe");
        user.setFirstName("Dilina");
        user.setLastName("Weerasinghe");
        user.setPosition(UserRole.Admin);
        user.setNic("200025800891");
        user.setUsername("Dilina123");
        user.setPassword(getEncodedPassword("Dilina@123"));
        user.setDob(LocalDate.of(2000,9,14));
        user.setServiceStartedDate(LocalDate.of(2020,02,15));
        user.setEmail("dilinaweerasinghe123@gmail.com");
        user.setMobileNo("0775521608");
        userRepository.save(user);

    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
