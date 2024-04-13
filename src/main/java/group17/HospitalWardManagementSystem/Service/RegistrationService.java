package group17.HospitalWardManagementSystem.Service;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
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


        User user3 = User
                .builder()
                .nic("200025800891")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Matron)
                .username("Dilina123")
                .password(passwordEncoder.encode("Dilina@123")).build();

        User user4 = User
                .builder()
                .nic("200025800892")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Matron)
                .username("Dilina1234")
                .password(passwordEncoder.encode("Dilina@1234")).build();

        ;
        users.add(user3);
        users.add(user4);
        userRepository.saveAll(users);

        Matron matron1 = Matron.builder().nic("200025800891").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        Matron matron2 = Matron.builder().nic("200025800892").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        matronRepository.save(matron1);
        matronRepository.save(matron2);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
