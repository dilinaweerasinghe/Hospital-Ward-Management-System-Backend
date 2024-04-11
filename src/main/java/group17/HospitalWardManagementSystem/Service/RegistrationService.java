package group17.HospitalWardManagementSystem.Service;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void initUser() {
        List<User> users = new ArrayList<>();

        // Add admin
        User admin = new User();
        admin.setFullName("Dilki Hansapani");
        admin.setFirstName("Dilki");
        admin.setLastName("Hansapani");
        admin.setPosition(UserRole.Admin);
        admin.setNic("9912121212v");
        admin.setUsername("admin123");
        admin.setPassword(getEncodedPassword("admin@123"));
        admin.setDob(LocalDate.of(2000, 1, 1)); // Set admin's date of birth
        admin.setEmail("admin@example.com");
        admin.setMobileNo("1234567890");
        users.add(admin);

        User user1 = new User();
        user1.setNic("200025800891");
        user1.setFirstName("Dilina");
        user1.setFullName("K L D K T Weerasinghe");
        user1.setLastName("Weerasinghe");
        user1.setUsername("Dilina123");
        user1.setPassword(getEncodedPassword("Dilina@123"));
        user1.setEmail("weerasinghe.dilina683@gmail.com");
        user1.setPosition(UserRole.Admin);
        user1.setMobileNo("0715848081");
        user1.setDob(LocalDate.of(2000,9,14));

        users.add(user1);



        // Save all users to the database
        userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
