package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.TestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration.class)
public class ServiceDetailsRepositoryTest {
    private final ServiceDetailsRepository serviceDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public ServiceDetailsRepositoryTest(ServiceDetailsRepository serviceDetailsRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.serviceDetailsRepository = serviceDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void setUpInitialValues(){
        User user1 = User
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

        User user2 = User
                .builder()
                .nic("200025800892")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Sister)
                .username("Dilina1234")
                .password(passwordEncoder.encode("Dilina@1234")).build();

        userRepository.save(user1);
        userRepository.save(user2);

//        Ward ward = Ward.builder().
//
//        Staff staff = Staff.builder().nic("200025800892").serviceStartedDate(LocalDate.parse("2010-12-12")).leaveNum("L001").build();


    }

    
}
