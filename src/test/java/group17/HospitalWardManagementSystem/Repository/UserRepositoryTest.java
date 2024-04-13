package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.TestConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration.class)
public class UserRepositoryTest {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeEach
    public void setupInitialValue(){
        User user1 = User
                .builder()
                .nic("200025800891")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Admin)
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
                .Position(UserRole.Admin)
                .username("Dilina1234")
                .password(passwordEncoder.encode("Dilina@1234")).build();

        userRepository.save(user1);
        userRepository.save(user2);

    }

    @Test
    public void saveDataTest(){
        User user = User
                .builder()
                .nic("200025800891")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Admin)
                .username("Dilina123")
                .password(passwordEncoder.encode("Dilina@123")).build();

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals("Dilina123", savedUser.getUsername());
    }

    @Test
    public void retrieveDataTest(){

        List<User> users = userRepository.findAll();
        assertEquals(2, users.size(),"Size should be return 2");
        assertNotNull(users);
        assertEquals(users.getFirst().getNic(), "200025800891", "Nic did not match");
        assertEquals(users.get(1).getNic(), "200025800892");
    }

    @Test
    public void findByUsernameTest(){
        User user = userRepository.findByUsername("Dilina123");
        assertNotNull(user);
    }

    @Test
    public void changePositionById(){
        userRepository.updatePositionByNic("200025800892", UserRole.Currently_None);

        assertEquals(UserRole.Currently_None, userRepository.findById("200025800892").get().getPosition());
    }

//    User findByUsername(String username);
//
//    Optional<User> findByNic(String nic);
//
//    Optional<User> findByEmail(String email);
//
//    void deleteUserByNic(String nic);



}
