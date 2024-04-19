package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration.class)
public class WardRepositoryTest {
    private final WardRepository wardRepository;
    private final UserRepository userRepository;
    private final MatronRepository matronRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WardRepositoryTest(WardRepository wardRepository, UserRepository userRepository, MatronRepository matronRepository, PasswordEncoder passwordEncoder) {
        this.wardRepository = wardRepository;
        this.userRepository = userRepository;
        this.matronRepository = matronRepository;
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

        Ward ward1 = Ward.builder().wardName("Accident Ward").matron(matron1).wardNo("12").morningShift(12)
                .eveningShift(15).nightShift(12).numberOfNurses(20).build();
        Ward ward2 = Ward.builder().wardName("Maternety Ward").matron(matron2).wardNo("15").morningShift(12)
                .eveningShift(15).nightShift(12).numberOfNurses(20).build();

        wardRepository.save(ward1);
        wardRepository.save(ward2);

    }

    @Test
    public void addWardTest(){
        Matron matron = Matron.builder().nic("200025800891").serviceStartedDate(LocalDate.parse("2010-10-10")).build();

        Ward ward = Ward.builder().wardName("Accident Ward").matron(matron).wardNo("12").morningShift(12)
                .eveningShift(15).nightShift(12).numberOfNurses(20).build();

        Ward savedWard = wardRepository.save(ward);

        assertNotNull(savedWard);
        assertEquals("12", savedWard.getWardNo());
    }

    @Test
    public void addMultipleWardTest(){
        Matron matron1 = Matron.builder().nic("200025800891").serviceStartedDate(LocalDate.parse("2010-10-10")).build();
        Matron matron2 = Matron.builder().nic("200025800892").serviceStartedDate(LocalDate.parse("2010-10-10")).build();

        Ward ward1 = Ward.builder().wardName("Accident Ward").matron(matron1).wardNo("12").morningShift(12)
                .eveningShift(15).nightShift(12).numberOfNurses(20).build();
        Ward ward2 = Ward.builder().wardName("Maternety Ward").matron(matron2).wardNo("15").morningShift(12)
                .eveningShift(15).nightShift(12).numberOfNurses(20).build();

        List<Ward> wards = new ArrayList<>();
        wards.add(ward1);
        wards.add(ward2);
        List<Ward> savedWards = wardRepository.saveAll(wards);

        assertNotNull(savedWards);
        assertEquals("12", savedWards.getFirst().getWardNo());
    }

    @Test
    public void findAllTest(){
        List<Ward> wards = wardRepository.findAll();

        assertEquals(2, wards.size());
        assertNotNull(wards);
        assertEquals("12", wards.getFirst().getWardNo());
    }

    @Test
    public void deleteByIdTest(){
        assertNotNull(wardRepository.findByWardNo("12"));
        wardRepository.deleteById("12");
        assertNull(wardRepository.findByWardNo("12"));

    }
}
