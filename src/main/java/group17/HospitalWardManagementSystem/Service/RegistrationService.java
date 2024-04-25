package group17.HospitalWardManagementSystem.Service;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MatronRepository matronRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, MatronRepository matronRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.matronRepository = matronRepository;
    }

    public void initUser() {
        List<User> users = new ArrayList<>();

        // Add admin
        User admin1 = new User();
        admin1.setFullName("Dilki Hansapani");
        admin1.setFirstName("Dilki");
        admin1.setLastName("Hansapani");
        admin1.setPosition(UserRole.Admin);
        admin1.setNic("9912121212v");
        admin1.setUsername("Dilki123");
        admin1.setPassword(getEncodedPassword("Dilki@123"));
        admin1.setDob(LocalDate.of(2000, 1, 1)); // Set admins date of birth
        admin1.setEmail("admin@example.com");
        admin1.setMobileNo("1234567890");
        users.add(admin1);

        User admin2 = new User();
        admin2.setNic("200025800891");
        admin2.setFirstName("Dilina");
        admin2.setFullName("K L D K T Weerasinghe");
        admin2.setLastName("Weerasinghe");
        admin2.setUsername("Dilina123");
        admin2.setPassword(getEncodedPassword("Dilina@123"));
        admin2.setEmail("nipunaheshansadu@gmail.com");
        admin2.setPosition(UserRole.Admin);
        admin2.setMobileNo("0715848081");
        admin2.setDob(LocalDate.of(2000,9,14));

        users.add(admin2);



        // Save all users to the database


        User admin3 = User.builder().nic("200025800892").email("weerasinghe.dilina@gmail.com").dob(LocalDate.parse("2000-09-14")).firstName("Nipuna").lastName("Heshan").fullName("N Heshan").mobileNo("0778652280").Position(UserRole.Admin).username("Nipuna123").password(passwordEncoder.encode("Nipuna@123")).build();

        User admin4 = User.builder().nic("200025800893").email("weerasinghe.dilina@gmail.com").dob(LocalDate.parse("2000-09-14")).firstName("Vimukthi").lastName("Ranasinghe").fullName("V Ranasinghe").mobileNo("0778652280").Position(UserRole.Admin).username("Vimukthi123").password(passwordEncoder.encode("Vimukthi@1234")).build();

        User admin5 = User.builder().nic("200025800894").email("weerasinghe.dilina@gmail.com").dob(LocalDate.parse("2000-09-14")).firstName("Ahamad").lastName("Sajad").fullName("A Sajad").mobileNo("0778652280").Position(UserRole.Admin).username("Sajad123").password(passwordEncoder.encode("Sajad@1234")).build();

        users.add(admin3);
        users.add(admin4);
        users.add(admin5);
        userRepository.saveAll(users);

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
