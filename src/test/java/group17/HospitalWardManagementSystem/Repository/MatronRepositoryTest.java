package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.TestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration.class)
public class MatronRepositoryTest {
    private final MatronRepository matronRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public MatronRepositoryTest(MatronRepository matronRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.matronRepository = matronRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
                .Position(UserRole.Matron)
                .username("Dilina1234")
                .password(passwordEncoder.encode("Dilina@1234")).build();

        userRepository.save(user1);
        userRepository.save(user2);

        Matron matron1 = Matron.builder().nic("200025800891").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        Matron matron2 = Matron.builder().nic("200025800892").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        matronRepository.save(matron1);
        matronRepository.save(matron2);

    }

    @Test
    public void addMatronTest(){
        Matron matron = Matron.builder().nic("200025800891").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        Matron savedMatron = matronRepository.save(matron);

        assertNotNull(savedMatron);
        assertEquals("200025800891", savedMatron.getNic());
    }

    @Test
    public void addMultipleMatronTest(){
        Matron matron1 = Matron.builder().nic("200025800891").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        Matron matron2 = Matron.builder().nic("200025800892").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        List<Matron> matrons = new ArrayList<>();
        matrons.add(matron1);
        matrons.add(matron2);
        List<Matron> savedMatrons = matronRepository.saveAll(matrons);

        assertNotNull(savedMatrons);
        assertEquals("200025800891", savedMatrons.getFirst().getNic());
    }

    @Test
    public void retrieveMatronTest(){
        List<Matron> matrons = matronRepository.findAll();

        assertNotNull(matrons);
        assertEquals(matrons.getFirst().getNic(), "200025800891", "Nic did not match");
        assertEquals(matrons.get(1).getNic(), "200025800892");
    }

    @Test
    public void deleteMatronById(){
        matronRepository.deleteMatronByNic("200025800891");

        List<Matron> matrons = matronRepository.findAll();
        assertEquals(matrons.getFirst().getNic(), "200025800892", "Nic did not match");
        assertEquals(1, matrons.size());


    }

}
