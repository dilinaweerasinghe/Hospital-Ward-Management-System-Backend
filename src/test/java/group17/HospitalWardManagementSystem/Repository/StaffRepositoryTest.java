package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration.class)
public class StaffRepositoryTest {

    private final UserRepository userRepository;
    private final WardRepository wardRepository;
    private final MatronRepository matronRepository;
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StaffRepositoryTest(UserRepository userRepository, WardRepository wardRepository, MatronRepository matronRepository, StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.wardRepository = wardRepository;
        this.matronRepository = matronRepository;
        this.staffRepository = staffRepository;
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
        User user3 = User
                .builder()
                .nic("200025800893")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Sister)
                .username("Dilina1234")
                .password(passwordEncoder.encode("Dilina@1234")).build();

        User user4 = User
                .builder()
                .nic("200025800894")
                .email("weerasinghe.dilina@gmail.com")
                .dob(LocalDate.parse("2000-09-14"))
                .firstName("Dilina")
                .lastName("Weerasinghe")
                .fullName("K L D K T Weerasinghe")
                .mobileNo("0778652280")
                .Position(UserRole.Nurse)
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
    public void addStaffMemberTest(){
        Staff staff = Staff.builder().nic("200025800893").leaveNum("L1").serviceStartedDate(LocalDate.parse("2012-12-12"))
                .remainingCasualLeaves(12).remainingVacationLeave(26).wardNo(wardRepository.findByWardNo("12")).build();

        Staff savedStaff = staffRepository.save(staff);

        assertNotNull(savedStaff);
        assertEquals("L1", savedStaff.getLeaveNum() );
    }

    @Test
    public void addMultipleStaffMemberTest(){
        Staff staff1 = Staff.builder().nic("200025800893").leaveNum("L1").serviceStartedDate(LocalDate.parse("2012-12-12"))
                .remainingCasualLeaves(12).remainingVacationLeave(26).wardNo(wardRepository.findByWardNo("12")).build();
        Staff staff2 = Staff.builder().nic("200025800894").leaveNum("L1").serviceStartedDate(LocalDate.parse("2012-12-12"))
                .remainingCasualLeaves(12).remainingVacationLeave(26).wardNo(wardRepository.findByWardNo("12")).build();

        List<Staff> staffs = new ArrayList<>();
        staffs.add(staff1);
        staffs.add(staff2);
        List<Staff> savedStaffs = staffRepository.saveAll(staffs);

        assertNotNull(savedStaffs);
        assertEquals("L1", savedStaffs.getFirst().getLeaveNum() );
    }
}
