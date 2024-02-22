package group17.HospitalWardManagementSystem.Service.Login;

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
        admin.setFullName("Admin Full Name");
        admin.setFirstName("Admin First Name");
        admin.setLastName("Admin Last Name");
        admin.setPosition(UserRole.Admin);
        admin.setNic("Admin NIC");
        admin.setUsername("admin123");
        admin.setPassword(getEncodedPassword("admin@123"));
        admin.setDob(LocalDate.of(2000, 1, 1)); // Set admin's date of birth
        admin.setEmail("admin@example.com");
        admin.setMobileNo("1234567890");
        users.add(admin);

        User user = new User();
        user.setFullName("Admin Full Name");
        user.setFirstName("Admin First Name");
        user.setLastName("Admin Last Name");
        user.setPosition(UserRole.Nurse);
        user.setNic("Admin NIC");
        user.setUsername("user123");
        user.setPassword(getEncodedPassword("user@123"));
        user.setDob(LocalDate.of(2000, 1, 1)); // Set admin's date of birth
        user.setEmail("admin@example.com");
        user.setMobileNo("1234567890");
        users.add(user);

        // Add matrons
        for (int i = 1; i <= 2; i++) {
            User matron = new User();
            matron.setFullName("Matron " + i + " Full Name");
            matron.setFirstName("Matron " + i + " First Name");
            matron.setLastName("Matron " + i + " Last Name");
            matron.setPosition(UserRole.Matron);
            matron.setNic("Matron " + i + " NIC");
            matron.setUsername("matron" + i + "123");
            matron.setPassword(getEncodedPassword("matron" + i + "@123"));
            matron.setDob(LocalDate.of(1990, 1, 1)); // Set matron's date of birth
            matron.setEmail("matron" + i + "@example.com");
            matron.setMobileNo("123456789" + i);
            users.add(matron);
        }

        // Add sisters
        for (int i = 1; i <= 3; i++) {
            User sister = new User();
            sister.setFullName("Sister " + i + " Full Name");
            sister.setFirstName("Sister " + i + " First Name");
            sister.setLastName("Sister " + i + " Last Name");
            sister.setPosition(UserRole.Sister);
            sister.setNic("Sister " + i + " NIC");
            sister.setUsername("sister" + i + "123");
            sister.setPassword(getEncodedPassword("sister" + i + "@123"));
            sister.setDob(LocalDate.of(1980, 1, 1)); // Set sister's date of birth
            sister.setEmail("sister" + i + "@example.com");
            sister.setMobileNo("123456789" + i);
            users.add(sister);
        }

        // Add nurses
        for (int i = 1; i <= 6; i++) {
            User nurse = new User();
            nurse.setFullName("Nurse " + i + " Full Name");
            nurse.setFirstName("Nurse " + i + " First Name");
            nurse.setLastName("Nurse " + i + " Last Name");
            nurse.setPosition(UserRole.Nurse);
            nurse.setNic("Nurse " + i + " NIC");
            nurse.setUsername("nurse" + i + "123");
            nurse.setPassword(getEncodedPassword("nurse" + i + "@123"));
            nurse.setDob(LocalDate.of(1970, 1, 1)); // Set nurse's date of birth
            nurse.setEmail("nurse" + i + "@example.com");
            nurse.setMobileNo("123456789" + i);
            users.add(nurse);
        }

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
