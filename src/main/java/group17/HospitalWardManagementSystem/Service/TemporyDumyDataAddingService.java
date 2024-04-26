package group17.HospitalWardManagementSystem.Service;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemporyDumyDataAddingService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MatronRepository matronRepository;
    private final WardRepository wardRepository;
    private final StaffRepository staffRepository;
    private final RequestLeaveRepository requestLeaveRepository;
    private final CasualityDaysRepository casualityDaysRepository;
    @Autowired
    public TemporyDumyDataAddingService(UserRepository userRepository, PasswordEncoder passwordEncoder, MatronRepository matronRepository, WardRepository wardRepository, StaffRepository staffRepository, RequestLeaveRepository requestLeaveRepository, CasualityDaysRepository casualityDaysRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.matronRepository = matronRepository;
        this.wardRepository = wardRepository;
        this.staffRepository = staffRepository;
        this.requestLeaveRepository = requestLeaveRepository;
        this.casualityDaysRepository = casualityDaysRepository;
    }

    public void addNursesData(){
        List<User> nurses = new ArrayList<>();

        // Add all 30 nurse data records
        nurses.add(User.builder()
                .nic("198511120367")
                .fullName("R M P P Wickramasinghe")
                .firstName("Rajitha")
                .lastName("Wickramasinghe")
                .username("user3")
                .password(passwordEncoder.encode("password3"))
                .dob(LocalDate.parse("1990-12-20"))
                .email("rajitha@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("345-678-9012")
                .careerStatedDate(LocalDate.parse("2010-12-12")) // Already set within range
                .build());

        nurses.add(User.builder()
                .nic("199601280278")
                .fullName("W M K P Silva")
                .firstName("Wimal")
                .lastName("Silva")
                .username("user6")
                .password(passwordEncoder.encode("password6"))
                .dob(LocalDate.parse("1995-01-30"))
                .email("wimal@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("678-901-2345")
                .careerStatedDate(LocalDate.parse("2012-09-15")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199803150256")
                .fullName("M A N K Perera")
                .firstName("Madushi")
                .lastName("Perera")
                .username("user9")
                .password(passwordEncoder.encode("password9"))
                .dob(LocalDate.parse("1993-04-07"))
                .email("madushi@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("901-234-5678")
                .careerStatedDate(LocalDate.parse("2008-03-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198502020202")
                .fullName("L H S Fernando")
                .firstName("Lahiru")
                .lastName("Fernando")
                .username("user12")
                .password(passwordEncoder.encode("password12"))
                .dob(LocalDate.parse("1979-10-20"))
                .email("lahiru@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2005-06-18")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199707070707")
                .fullName("A R K Jayawardena")
                .firstName("Anusha")
                .lastName("Jayawardena")
                .username("user13")
                .password(passwordEncoder.encode("password13"))
                .dob(LocalDate.parse("1991-09-25"))
                .email("anusha@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("345-678-9012")
                .careerStatedDate(LocalDate.parse("2007-07-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199504300378")
                .fullName("K G S P Silva")
                .firstName("Kasun")
                .lastName("Silva")
                .username("user10")
                .password(passwordEncoder.encode("password10"))
                .dob(LocalDate.parse("1989-06-23"))
                .email("kasun@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("012-345-6789")
                .careerStatedDate(LocalDate.parse("2009-04-05")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199909090909")
                .fullName("T H L Fernando")
                .firstName("Tharindu")
                .lastName("Fernando")
                .username("user16")
                .password(passwordEncoder.encode("password16"))
                .dob(LocalDate.parse("1997-07-20"))
                .email("tharindu@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("678-901-2345")
                .careerStatedDate(LocalDate.parse("2012-11-11")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199606060606")
                .fullName("S N M Perera")
                .firstName("Saman")
                .lastName("Perera")
                .username("user21")
                .password(passwordEncoder.encode("password21"))
                .dob(LocalDate.parse("1992-06-15"))
                .email("saman@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("345-678-9012")
                .careerStatedDate(LocalDate.parse("2013-09-17")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199012250394")
                .fullName("W M P Perera")
                .firstName("Waruna")
                .lastName("Perera")
                .username("user28")
                .password(passwordEncoder.encode("password28"))
                .dob(LocalDate.parse("1981-08-30"))
                .email("waruna@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("890-123-4567")
                .careerStatedDate(LocalDate.parse("2007-02-15")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199701010101")
                .fullName("P A K Silva")
                .firstName("Prasad")
                .lastName("Silva")
                .username("user33")
                .password(passwordEncoder.encode("password33"))
                .dob(LocalDate.parse("1996-05-12"))
                .email("prasad@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("901-234-5678")
                .careerStatedDate(LocalDate.parse("2010-08-20")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198806060606")
                .fullName("M L R Fernando")
                .firstName("Malithi")
                .lastName("Fernando")
                .username("user37")
                .password(passwordEncoder.encode("password37"))
                .dob(LocalDate.parse("1983-04-20"))
                .email("malithi@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("012-345-6789")
                .careerStatedDate(LocalDate.parse("2006-07-12")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199409040404")
                .fullName("T W K Perera")
                .firstName("Thilina")
                .lastName("Perera")
                .username("user42")
                .password(passwordEncoder.encode("password42"))
                .dob(LocalDate.parse("1991-11-20"))
                .email("thilina@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("123-456-7890")
                .careerStatedDate(LocalDate.parse("2014-03-30")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198803030303")
                .fullName("K R L Silva")
                .firstName("Kumudu")
                .lastName("Silva")
                .username("user48")
                .password(passwordEncoder.encode("password48"))
                .dob(LocalDate.parse("1978-07-15"))
                .email("kumudu@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2006-02-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199404040404")
                .fullName("S D M Fernando")
                .firstName("Supun")
                .lastName("Fernando")
                .username("user55")
                .password(passwordEncoder.encode("password55"))
                .dob(LocalDate.parse("1990-10-15"))
                .email("supun@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("345-678-9012")
                .careerStatedDate(LocalDate.parse("2013-07-04")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199808080808")
                .fullName("P D W Perera")
                .firstName("Prasanna")
                .lastName("Perera")
                .username("user62")
                .password(passwordEncoder.encode("password62"))
                .dob(LocalDate.parse("1994-03-25"))
                .email("prasanna@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("456-789-0123")
                .careerStatedDate(LocalDate.parse("2011-05-18")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199009090909")
                .fullName("M L M Silva")
                .firstName("Malithi")
                .lastName("Silva")
                .username("user69")
                .password(passwordEncoder.encode("password69"))
                .dob(LocalDate.parse("1986-12-20"))
                .email("malithi@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("567-890-1234")
                .careerStatedDate(LocalDate.parse("2008-08-12")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199709070707")
                .fullName("W H G Fernando")
                .firstName("Wasantha")
                .lastName("Fernando")
                .username("user76")
                .password(passwordEncoder.encode("password76"))
                .dob(LocalDate.parse("1993-08-10"))
                .email("wasantha@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("678-901-2345")
                .careerStatedDate(LocalDate.parse("2012-10-25")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198602020202")
                .fullName("S A L Perera")
                .firstName("Sampath")
                .lastName("Perera")
                .username("user81")
                .password(passwordEncoder.encode("password81"))
                .dob(LocalDate.parse("1977-02-10"))
                .email("sampath@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("789-012-3456")
                .careerStatedDate(LocalDate.parse("2006-05-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199701010101")
                .fullName("T D K Silva")
                .firstName("Thilani")
                .lastName("Silva")
                .username("user87")
                .password(passwordEncoder.encode("password87"))
                .dob(LocalDate.parse("1996-09-15"))
                .email("thilani@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("901-234-5678")
                .careerStatedDate(LocalDate.parse("2010-07-15")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198805080808")
                .fullName("K D K Fernando")
                .firstName("Kasun")
                .lastName("Fernando")
                .username("user93")
                .password(passwordEncoder.encode("password93"))
                .dob(LocalDate.parse("1981-12-10"))
                .email("kaveen.theekshana2000@gmail.com")
                .Position(UserRole.Nurse)
                .mobileNo("012-345-6789")
                .careerStatedDate(LocalDate.parse("2008-10-25")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199703030303")
                .fullName("N M P Perera")
                .firstName("Nadeeka")
                .lastName("Perera")
                .username("user99")
                .password(passwordEncoder.encode("password99"))
                .dob(LocalDate.parse("1979-05-25"))
                .email("nadeeka@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("123-456-7890")
                .careerStatedDate(LocalDate.parse("2006-09-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199303030303")
                .fullName("T R K Silva")
                .firstName("Thilak")
                .lastName("Silva")
                .username("user104")
                .password(passwordEncoder.encode("password104"))
                .dob(LocalDate.parse("1980-06-25"))
                .email("thilak@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2004-08-20")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199712121212")
                .fullName("R W G Fernando")
                .firstName("Ruchira")
                .lastName("Fernando")
                .username("user110")
                .password(passwordEncoder.encode("password110"))
                .dob(LocalDate.parse("1994-02-15"))
                .email("ruchira@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("345-678-9012")
                .careerStatedDate(LocalDate.parse("2013-06-20")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198710101010")
                .fullName("M S D Perera")
                .firstName("Malithi")
                .lastName("Perera")
                .username("user115")
                .password(passwordEncoder.encode("password115"))
                .dob(LocalDate.parse("1983-12-10"))
                .email("malithi@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("456-789-0123")
                .careerStatedDate(LocalDate.parse("2007-07-18")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199010101010")
                .fullName("W K D Silva")
                .firstName("Wimukthi")
                .lastName("Silva")
                .username("user121")
                .password(passwordEncoder.encode("password121"))
                .dob(LocalDate.parse("1986-02-15"))
                .email("wimukthi@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("567-890-1234")
                .careerStatedDate(LocalDate.parse("2007-05-20")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199503030303")
                .fullName("P R K Fernando")
                .firstName("Prabath")
                .lastName("Fernando")
                .username("user126")
                .password(passwordEncoder.encode("password126"))
                .dob(LocalDate.parse("1990-10-15"))
                .email("prabath@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("678-901-2345")
                .careerStatedDate(LocalDate.parse("2013-08-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("198412121212")
                .fullName("S T M Perera")
                .firstName("Supun")
                .lastName("Perera")
                .username("user132")
                .password(passwordEncoder.encode("password132"))
                .dob(LocalDate.parse("1977-02-10"))
                .email("supun@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("789-012-3456")
                .careerStatedDate(LocalDate.parse("2010-03-10")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199212121212")
                .fullName("K S D Silva")
                .firstName("Kavindu")
                .lastName("Silva")
                .username("user137")
                .password(passwordEncoder.encode("password137"))
                .dob(LocalDate.parse("1981-08-10"))
                .email("kavindu@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("890-123-4567")
                .careerStatedDate(LocalDate.parse("2008-09-15")) // Example date within range
                .build());

        nurses.add(User.builder()
                .nic("199706060606")
                .fullName("T N D Fernando")
                .firstName("Thilina")
                .lastName("Fernando")
                .username("user143")
                .password(passwordEncoder.encode("password143"))
                .dob(LocalDate.parse("1992-03-25"))
                .email("thilina@example.com")
                .Position(UserRole.Nurse)
                .mobileNo("901-234-5678")
                .careerStatedDate(LocalDate.parse("2012-04-15")) // Example date within range
                .build());
        // Save all nurses
        userRepository.saveAll(nurses);
    }

    public void addSisterData(){
        List<User> sisters = new ArrayList<>();

        // Add all 13 sister data records
        sisters.add(User.builder()
                .nic("199722350456")
                .fullName("A D P N Fernando")
                .firstName("Asanka")
                .lastName("Fernando")
                .username("user2")
                .password(passwordEncoder.encode("password2"))
                .dob(LocalDate.parse("1980-09-15"))
                .email("asanka@example.com")
                .Position(UserRole.Sister)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2007-08-10")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("198808170591")
                .fullName("D L T Perera")
                .firstName("Dilani")
                .lastName("Perera")
                .username("user5")
                .password(passwordEncoder.encode("password5"))
                .dob(LocalDate.parse("1978-07-05"))
                .email("dilani@example.com")
                .Position(UserRole.Sister)
                .mobileNo("567-890-1234")
                .careerStatedDate(LocalDate.parse("2003-06-25")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199010050189")
                .fullName("P N W Jayawardena")
                .firstName("Pradeep")
                .lastName("Jayawardena")
                .username("user8")
                .password(passwordEncoder.encode("password8"))
                .dob(LocalDate.parse("1987-08-12"))
                .email("pradeep@example.com")
                .Position(UserRole.Sister)
                .mobileNo("890-123-4567")
                .careerStatedDate(LocalDate.parse("2005-09-10")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199212121212")
                .fullName("W A S Silva")
                .firstName("Waruna")
                .lastName("Silva")
                .username("user17")
                .password(passwordEncoder.encode("password17"))
                .dob(LocalDate.parse("1984-02-10"))
                .email("waruna@example.com")
                .Position(UserRole.Sister)
                .mobileNo("789-012-3456")
                .careerStatedDate(LocalDate.parse("2004-11-12")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("198602060707")
                .fullName("T K R Perera")
                .firstName("Tharaka")
                .lastName("Perera")
                .username("user26")
                .password(passwordEncoder.encode("password26"))
                .dob(LocalDate.parse("1977-06-15"))
                .email("weerasinghedilina.edu@gmail.com")
                .Position(UserRole.Sister)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2010-03-15")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199706250696")
                .fullName("S P L Fernando")
                .firstName("Supun")
                .lastName("Fernando")
                .username("user24")
                .password(passwordEncoder.encode("password24"))
                .dob(LocalDate.parse("1992-03-25"))
                .email("supun@example.com")
                .Position(UserRole.Sister)
                .mobileNo("456-789-0123")
                .careerStatedDate(LocalDate.parse("2012-06-05")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199012251203")
                .fullName("W P M Fernando")
                .firstName("Wimukthi")
                .lastName("Fernando")
                .username("user39")
                .password(passwordEncoder.encode("password39"))
                .dob(LocalDate.parse("1983-12-10"))
                .email("wimukthi@example.com")
                .Position(UserRole.Sister)
                .mobileNo("901-234-5678")
                .careerStatedDate(LocalDate.parse("2009-08-10")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("198702020303")
                .fullName("R A H Silva")
                .firstName("Roshan")
                .lastName("Silva")
                .username("user44")
                .password(passwordEncoder.encode("password44"))
                .dob(LocalDate.parse("1979-04-25"))
                .email("roshan@example.com")
                .Position(UserRole.Sister)
                .mobileNo("567-890-1234")
                .careerStatedDate(LocalDate.parse("2011-11-15")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199307070404")
                .fullName("N K D Perera")
                .firstName("Nimashi")
                .lastName("Perera")
                .username("user51")
                .password(passwordEncoder.encode("password51"))
                .dob(LocalDate.parse("1986-02-15"))
                .email("nimashi@example.com")
                .Position(UserRole.Sister)
                .mobileNo("890-123-4567")
                .careerStatedDate(LocalDate.parse("2010-05-01")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("198101010505")
                .fullName("M P G Silva")
                .firstName("Malithi")
                .lastName("Silva")
                .username("user58")
                .password(passwordEncoder.encode("password58"))
                .dob(LocalDate.parse("1976-08-20"))
                .email("malithi@example.com")
                .Position(UserRole.Sister)
                .mobileNo("123-456-7890")
                .careerStatedDate(LocalDate.parse("2013-07-10")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199605050606")
                .fullName("L S M Fernando")
                .firstName("Lakshan")
                .lastName("Fernando")
                .username("user64")
                .password(passwordEncoder.encode("password64"))
                .dob(LocalDate.parse("1993-05-25"))
                .email("lakshan@example.com")
                .Position(UserRole.Sister)
                .mobileNo("345-678-9012")
                .careerStatedDate(LocalDate.parse("2011-02-25")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("198812120587")
                .fullName("A S T Silva")
                .firstName("Asanka")
                .lastName("Silva")
                .username("user69")
                .password(passwordEncoder.encode("password69"))
                .dob(LocalDate.parse("1975-12-20"))
                .email("asanka@example.com")
                .Position(UserRole.Sister)
                .mobileNo("678-901-2345")
                .careerStatedDate(LocalDate.parse("2002-05-10")) // Example within range
                .build());

        sisters.add(User.builder()
                .nic("199707110986")
                .fullName("R S D P Fernando")
                .firstName("Ruchira")
                .lastName("Fernando")
                .username("user74")
                .password(passwordEncoder.encode("password74"))
                .dob(LocalDate.parse("1994-02-15"))
                .email("ruchira@example.com")
                .Position(UserRole.Sister)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2006-08-20")) // Example within range
                .build());
        // Save all sisters
        userRepository.saveAll(sisters);
    }

    public void addMatronData(){
        List<User> matrons = new ArrayList<>();

        // Add all 5 matron data records
        matrons.add(User.builder()
                .nic("200025800895")
                .fullName("K H M L Jayasinghe")
                .firstName("Lakshika")
                .lastName("Jayasinghe")
                .username("user1")
                .password(passwordEncoder.encode("password1"))
                .dob(LocalDate.parse("1975-05-10"))
                .email("weerasinghe.dilina683@gmail.com")
                .Position(UserRole.Matron)
                .mobileNo("123-456-7890")
                .careerStatedDate(LocalDate.parse("2010-08-15")) // Within the range
                .build());

        matrons.add(User.builder()
                .nic("198312040789")
                .fullName("S H D S Bandara")
                .firstName("Saman")
                .lastName("Bandara")
                .username("user4")
                .password(passwordEncoder.encode("password4"))
                .dob(LocalDate.parse("1985-03-25"))
                .email("saman@example.com")
                .Position(UserRole.Matron)
                .mobileNo("456-789-0123")
                .careerStatedDate(LocalDate.parse("2006-03-10")) // Within the range
                .build());

        matrons.add(User.builder()
                .nic("198111190167")
                .fullName("J G A P Gunasekara")
                .firstName("Janitha")
                .lastName("Gunasekara")
                .username("user7")
                .password(passwordEncoder.encode("password7"))
                .dob(LocalDate.parse("1982-11-18"))
                .email("janitha@example.com")
                .Position(UserRole.Matron)
                .mobileNo("789-012-3456")
                .careerStatedDate(LocalDate.parse("2007-05-20")) // Within the range
                .build());

        matrons.add(User.builder()
                .nic("199707110987")
                .fullName("R S D P Fernando")
                .firstName("Ruchira")
                .lastName("Fernando")
                .username("user27")
                .password(passwordEncoder.encode("password27"))
                .dob(LocalDate.parse("1994-02-15"))
                .email("weerasinghe.dilina683@gmail.com")
                .Position(UserRole.Matron)
                .mobileNo("234-567-8901")
                .careerStatedDate(LocalDate.parse("2013-07-30")) // Within the range
                .build());

        matrons.add(User.builder()
                .nic("199303031703")
                .fullName("T D K Perera")
                .firstName("Thilani")
                .lastName("Perera")
                .username("user46")
                .password(passwordEncoder.encode("password46"))
                .dob(LocalDate.parse("1984-12-25"))
                .email("thilani@example.com")
                .Position(UserRole.Matron)
                .mobileNo("678-901-2345")
                .careerStatedDate(LocalDate.parse("2005-10-10")) // Within the range
                .build());
        // Save all matrons
        userRepository.saveAll(matrons);
    }

    public void addMatronsToMatronTable(){
        List<Matron> matrons = new ArrayList<>();

        Matron matron1 = new Matron();
        matron1.setNic("200025800895");
        matron1.setServiceStartedDate(LocalDate.parse("1995-05-10"));

        Matron matron2 = new Matron();
        matron2.setNic("198312040789");
        matron2.setServiceStartedDate(LocalDate.parse("1995-03-25"));

        Matron matron3 = new Matron();
        matron3.setNic("198111190167");
        matron3.setServiceStartedDate(LocalDate.parse("2004-03-25"));

        Matron matron4 = new Matron();
        matron4.setNic("199707110987");
        matron4.setServiceStartedDate(LocalDate.parse("2006-03-25"));

        Matron matron5 = new Matron();
        matron5.setNic("199303031703");
        matron5.setServiceStartedDate(LocalDate.parse("2003-03-25"));

        matrons.add(matron1);
        matrons.add(matron2);
        matrons.add(matron3);
        matrons.add(matron4);
        matrons.add(matron5);

        matronRepository.saveAll(matrons);
    }

    public void addStaffDetails() {
        List<Staff> staffList = new ArrayList<>();

        // Adding nursing staff members with updated service started dates between 2000-12-12 and 2022-12-12
        staffList.add(Staff.builder().nic("198511120367").leaveNum("L001").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2015-01-15")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("199601280278").leaveNum("L002").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2016-03-20")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("199803150256").leaveNum("L003").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2017-09-10")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("198502020202").leaveNum("L004").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-11-28")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("199707070707").leaveNum("L005").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2019-12-01")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("199504300378").leaveNum("L006").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2020-07-17")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("199909090909").leaveNum("L007").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2021-03-03")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("199606060606").leaveNum("L008").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2016-08-20")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("199012250394").leaveNum("L009").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2015-04-12")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("199701010101").leaveNum("L010").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2017-12-15")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("198806060606").leaveNum("L011").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2020-01-25")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("199409040404").leaveNum("L012").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2019-10-10")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("198803030303").leaveNum("L013").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2016-12-20")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("199404040404").leaveNum("L014").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-02-28")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("199808080808").leaveNum("L015").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2016-06-15")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("199009090909").leaveNum("L016").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-03-03")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("199709070707").leaveNum("L017").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2019-05-20")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("198602020202").leaveNum("L018").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2017-02-22")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("199701010101").leaveNum("L019").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2023-08-18")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("198805080808").leaveNum("L020"). remainingVacationLeave(23). remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2021-12-12")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("199703030303").leaveNum("L021").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-04-15")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("199303030303").leaveNum("L022").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2023-12-12")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("199712121212").leaveNum("L023").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2019-02-25")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("198710101010").leaveNum("L024"). remainingVacationLeave(23). remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-11-11")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("199010101010").leaveNum("L025").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2019-07-20")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("199503030303").leaveNum("L026").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2023-09-09")).wardNo(wardRepository.findByWardNo("W013")).build());
        staffList.add(Staff.builder().nic("198412121212").leaveNum("L027").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2017-01-01")).wardNo(wardRepository.findByWardNo("W010")).build());
        staffList.add(Staff.builder().nic("199212121212").leaveNum("L028").remainingVacationLeave(23). remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-03-03")).wardNo(wardRepository.findByWardNo("W005")).build());
        staffList.add(Staff.builder().nic("199706060606").leaveNum("L029"). remainingVacationLeave(23). remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2017-06-16")).wardNo(wardRepository.findByWardNo("W012")).build());
        staffList.add(Staff.builder().nic("198910111213").leaveNum("L030"). remainingVacationLeave(23). remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-10-10")).wardNo(wardRepository.findByWardNo("W013")).build());

//        staffList.add(Staff.builder().nic("198511120367").leaveNum("L001").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2005-05-15")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("199601280278").leaveNum("L002").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2001-03-20")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("199803150256").leaveNum("L003").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2004-09-10")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("198502020202").leaveNum("L004").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2003-11-28")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("199707070707").leaveNum("L005").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2010-12-01")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("199504300378").leaveNum("L006").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2006-07-17")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("199909090909").leaveNum("L007").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2015-03-03")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("199606060606").leaveNum("L008").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2009-08-20")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("199012250394").leaveNum("L009").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2012-04-12")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("199701010101").leaveNum("L010").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2002-12-15")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("198806060606").leaveNum("L011").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2008-01-25")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("199409040404").leaveNum("L012").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2010-10-10")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("198803030303").leaveNum("L013").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2000-12-20")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("199404040404").leaveNum("L014").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2018-02-28")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("199808080808").leaveNum("L015").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2009-06-15")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("199009090909").leaveNum("L016").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2011-03-03")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("199709070707").leaveNum("L017").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2019-05-20")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("198602020202").leaveNum("L018").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2005-02-22")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("199701010101").leaveNum("L019").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2007-08-18")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("198805080808").leaveNum("L020").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2004-12-12")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("199703030303").leaveNum("L021").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2001-04-15")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("199303030303").leaveNum("L022").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2022-12-12")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("199712121212").leaveNum("L023").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2011-02-25")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("198710101010").leaveNum("L024").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2006-11-11")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("199010101010").leaveNum("L025").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2003-07-20")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("199503030303").leaveNum("L026").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2015-09-09")).wardNo(wardRepository.findByWardNo("W013")).build());
//        staffList.add(Staff.builder().nic("198412121212").leaveNum("L027").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2009-01-01")).wardNo(wardRepository.findByWardNo("W010")).build());
//        staffList.add(Staff.builder().nic("199212121212").leaveNum("L028").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2008-03-03")).wardNo(wardRepository.findByWardNo("W005")).build());
//        staffList.add(Staff.builder().nic("199706060606").leaveNum("L029").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2012-06-16")).wardNo(wardRepository.findByWardNo("W012")).build());
//        staffList.add(Staff.builder().nic("198910111213").leaveNum("L030").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2010-10-10")).wardNo(wardRepository.findByWardNo("W013")).build());

        // Saving all staff members to the repository
        staffRepository.saveAll(staffList);
    }


    public void addWards() {
        List<Ward> wards = new ArrayList<>();

        // Inserting ward data
        wards.add(Ward.builder().wardNo("W001").wardName("General Medicine").matron(matronRepository.findByNic("198312040789")).numberOfNurses(20).morningShift(5).eveningShift(5).nightShift(5).build());
        wards.add(Ward.builder().wardNo("W002").wardName("Cardiology").matron(matronRepository.findByNic("198312040789")).numberOfNurses(15).morningShift(5).eveningShift(5).nightShift(5).build());
        wards.add(Ward.builder().wardNo("W003").wardName("Oncology").matron(matronRepository.findByNic("198111190167")).numberOfNurses(12).morningShift(4).eveningShift(4).nightShift(4).build());
        wards.add(Ward.builder().wardNo("W004").wardName("Pediatrics").matron(matronRepository.findByNic("198111190167")).numberOfNurses(18).morningShift(6).eveningShift(6).nightShift(6).build());
        wards.add(Ward.builder().wardNo("W005").wardName("Neurology").matron(matronRepository.findByNic("199707110987")).numberOfNurses(10).morningShift(3).eveningShift(3).nightShift(4).build());
        wards.add(Ward.builder().wardNo("W006").wardName("Orthopedics").matron(matronRepository.findByNic("199707110986")).numberOfNurses(22).morningShift(7).eveningShift(7).nightShift(8).build());
        wards.add(Ward.builder().wardNo("W007").wardName("Gastroenterology").matron(matronRepository.findByNic("199303031703")).numberOfNurses(8).morningShift(2).eveningShift(3).nightShift(3).build());
        wards.add(Ward.builder().wardNo("W008").wardName("Maternity").matron(matronRepository.findByNic("199303031703")).numberOfNurses(25).morningShift(8).eveningShift(8).nightShift(9).build());
        wards.add(Ward.builder().wardNo("W009").wardName("Psychiatry").matron(matronRepository.findByNic("200025800895")).numberOfNurses(12).morningShift(4).eveningShift(4).nightShift(4).build());
        wards.add(Ward.builder().wardNo("W010").wardName("Dermatology").matron(matronRepository.findByNic("200025800895")).numberOfNurses(6).morningShift(2).eveningShift(2).nightShift(2).build());
        wards.add(Ward.builder().wardNo("W011").wardName("Emergency").matron(matronRepository.findByNic("198312040789")).numberOfNurses(30).morningShift(10).eveningShift(10).nightShift(10).build());
        wards.add(Ward.builder().wardNo("W012").wardName("ENT").matron(matronRepository.findByNic("198111190167")).numberOfNurses(7).morningShift(2).eveningShift(2).nightShift(3).build());
        wards.add(Ward.builder().wardNo("W013").wardName("Urology").matron(matronRepository.findByNic("199707110986")).numberOfNurses(7).morningShift(3).eveningShift(3).nightShift(3).build());

        // Save all wards to the repository
        wardRepository.saveAll(wards);
    }

    public void addSistersToTheStaffTable() {
        List<Staff> sisters = new ArrayList<>();

        sisters.add(Staff.builder().nic("199722350456").leaveNum("LS001").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2002-01-12")).wardNo(wardRepository.findByWardNo("W001")).build());
        sisters.add(Staff.builder().nic("198808170591").leaveNum("LS002").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("1996-05-20")).wardNo(wardRepository.findByWardNo("W002")).build());
        sisters.add(Staff.builder().nic("199010050189").leaveNum("LS003").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2001-08-10")).wardNo(wardRepository.findByWardNo("W003")).build());
        sisters.add(Staff.builder().nic("199212121212").leaveNum("LS004").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("1995-10-18")).wardNo(wardRepository.findByWardNo("W004")).build());
        sisters.add(Staff.builder().nic("198602060707").leaveNum("LS005").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2003-03-25")).wardNo(wardRepository.findByWardNo("W005")).build());
        sisters.add(Staff.builder().nic("199706250696").leaveNum("LS006").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("1999-07-10")).wardNo(wardRepository.findByWardNo("W006")).build());
        sisters.add(Staff.builder().nic("199012251203").leaveNum("LS007").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2005-02-28")).wardNo(wardRepository.findByWardNo("W007")).build());
        sisters.add(Staff.builder().nic("198702020303").leaveNum("LS008").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2008-09-15")).wardNo(wardRepository.findByWardNo("W008")).build());
        sisters.add(Staff.builder().nic("199307070404").leaveNum("LS009").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("1994-12-20")).wardNo(wardRepository.findByWardNo("W009")).build());
        sisters.add(Staff.builder().nic("198101010505").leaveNum("LS010").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2006-11-30")).wardNo(wardRepository.findByWardNo("W010")).build());
        sisters.add(Staff.builder().nic("199605050606").leaveNum("LS011").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2002-08-15")).wardNo(wardRepository.findByWardNo("W011")).build());
        sisters.add(Staff.builder().nic("198812120587").leaveNum("LS012").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("1998-04-05")).wardNo(wardRepository.findByWardNo("W012")).build());
        sisters.add(Staff.builder().nic("199707110986").leaveNum("LS013").remainingVacationLeave(23).remainingCasualLeaves(23).serviceStartedDate(LocalDate.parse("2007-03-15")).wardNo(wardRepository.findByWardNo("W013")).build());

        staffRepository.saveAll(sisters);
    }


    public void addRequestLeaves() {
        List<RequestLeave> requestLeaves = new ArrayList<>();

        // Request 1
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198511120367").get()).leaveBeginDate(LocalDate.parse("2024-04-12")).leaveEndDate(LocalDate.parse("2024-04-15")).requestedDateAndTime(LocalDateTime.parse("2024-04-01T09:40:00")).reason("Reason 1").build());

        // Request 2
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199803150256").get()).leaveBeginDate(LocalDate.parse("2024-04-18")).leaveEndDate(LocalDate.parse("2024-04-20")).requestedDateAndTime(LocalDateTime.parse("2024-04-01T09:30:00")).reason("Reason 2").build());

        // Request 3
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198502020202").get()).leaveBeginDate(LocalDate.parse("2024-05-05")).leaveEndDate(LocalDate.parse("2024-05-07")).requestedDateAndTime(LocalDateTime.parse("2024-04-02T14:45:00")).reason("Reason 3").build());

        // Request 4
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199212121212").get()).leaveBeginDate(LocalDate.parse("2024-05-10")).leaveEndDate(LocalDate.parse("2024-05-12")).requestedDateAndTime(LocalDateTime.parse("2024-04-05T11:20:00")).reason("Reason 4").build());

        // Request 5
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199504300378").get()).leaveBeginDate(LocalDate.parse("2024-05-15")).leaveEndDate(LocalDate.parse("2024-05-17")).requestedDateAndTime(LocalDateTime.parse("2024-04-10T16:00:00")).reason("Reason 5").build());

        // Request 6
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199909090909").get()).leaveBeginDate(LocalDate.parse("2024-05-20")).leaveEndDate(LocalDate.parse("2024-05-22")).requestedDateAndTime(LocalDateTime.parse("2024-04-15T10:30:00")).reason("Reason 6").build());

        // Request 7
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199606060606").get()).leaveBeginDate(LocalDate.parse("2024-05-25")).leaveEndDate(LocalDate.parse("2024-05-27")).requestedDateAndTime(LocalDateTime.parse("2024-04-20T13:15:00")).reason("Reason 7").build());

        // Request 8
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198702020303").get()).leaveBeginDate(LocalDate.parse("2024-05-30")).leaveEndDate(LocalDate.parse("2024-06-01")).requestedDateAndTime(LocalDateTime.parse("2024-04-25T09:00:00")).reason("Reason 8").build());

        // Request 9
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199701010101").get()).leaveBeginDate(LocalDate.parse("2024-06-05")).leaveEndDate(LocalDate.parse("2024-06-07")).requestedDateAndTime(LocalDateTime.parse("2024-05-01T11:45:00")).reason("Reason 9").build());

        // Request 10
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198806060606").get()).leaveBeginDate(LocalDate.parse("2024-06-10")).leaveEndDate(LocalDate.parse("2024-06-12")).requestedDateAndTime(LocalDateTime.parse("2024-05-05T14:30:00")).reason("Reason 10").build());

        // Request 11
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199409040404").get()).leaveBeginDate(LocalDate.parse("2024-06-15")).leaveEndDate(LocalDate.parse("2024-06-17")).requestedDateAndTime(LocalDateTime.parse("2024-05-10T17:20:00")).reason("Reason 11").build());

        // Request 12
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198803030303").get()).leaveBeginDate(LocalDate.parse("2024-06-20")).leaveEndDate(LocalDate.parse("2024-06-22")).requestedDateAndTime(LocalDateTime.parse("2024-05-15T09:45:00")).reason("Reason 12").build());

        // Request 13
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199404040404").get()).leaveBeginDate(LocalDate.parse("2024-06-25")).leaveEndDate(LocalDate.parse("2024-06-27")).requestedDateAndTime(LocalDateTime.parse("2024-05-20T12:00:00")).reason("Reason 13").build());

        // Request 14
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199808080808").get()).leaveBeginDate(LocalDate.parse("2024-07-01")).leaveEndDate(LocalDate.parse("2024-07-03")).requestedDateAndTime(LocalDateTime.parse("2024-05-25T14:15:00")).reason("Reason 14").build());

        // Request 15
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199009090909").get()).leaveBeginDate(LocalDate.parse("2024-07-06")).leaveEndDate(LocalDate.parse("2024-07-08")).requestedDateAndTime(LocalDateTime.parse("2024-05-30T10:00:00")).reason("Reason 15").build());

        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199722350456").get()).leaveBeginDate(LocalDate.parse("2024-04-12")).leaveEndDate(LocalDate.parse("2024-04-15")).requestedDateAndTime(LocalDateTime.parse("2024-04-01T09:40:00")).reason("Reason 1").build());

        // Request 2
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198808170591").get()).leaveBeginDate(LocalDate.parse("2024-04-18")).leaveEndDate(LocalDate.parse("2024-04-20")).requestedDateAndTime(LocalDateTime.parse("2024-04-01T09:30:00")).reason("Reason 2").build());

        // Request 3
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199010050189").get()).leaveBeginDate(LocalDate.parse("2024-05-05")).leaveEndDate(LocalDate.parse("2024-05-07")).requestedDateAndTime(LocalDateTime.parse("2024-04-02T14:45:00")).reason("Reason 3").build());

        // Request 4
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199707070707").get()).leaveBeginDate(LocalDate.parse("2024-05-10")).leaveEndDate(LocalDate.parse("2024-05-12")).requestedDateAndTime(LocalDateTime.parse("2024-04-05T11:20:00")).reason("Reason 4").build());

        // Request 5
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198602060707").get()).leaveBeginDate(LocalDate.parse("2024-05-15")).leaveEndDate(LocalDate.parse("2024-05-17")).requestedDateAndTime(LocalDateTime.parse("2024-04-10T16:00:00")).reason("Reason 5").build());

        // Request 6
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199706250696").get()).leaveBeginDate(LocalDate.parse("2024-05-20")).leaveEndDate(LocalDate.parse("2024-05-22")).requestedDateAndTime(LocalDateTime.parse("2024-04-15T10:30:00")).reason("Reason 6").build());

        // Request 7
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199012251203").get()).leaveBeginDate(LocalDate.parse("2024-05-25")).leaveEndDate(LocalDate.parse("2024-05-27")).requestedDateAndTime(LocalDateTime.parse("2024-04-20T13:15:00")).reason("Reason 7").build());

        // Request 8
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199012250394").get()).leaveBeginDate(LocalDate.parse("2024-05-30")).leaveEndDate(LocalDate.parse("2024-06-01")).requestedDateAndTime(LocalDateTime.parse("2024-04-25T09:00:00")).reason("Reason 8").build());

        // Request 9
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199307070404").get()).leaveBeginDate(LocalDate.parse("2024-06-05")).leaveEndDate(LocalDate.parse("2024-06-07")).requestedDateAndTime(LocalDateTime.parse("2024-05-01T11:45:00")).reason("Reason 9").build());

        // Request 10
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198101010505").get()).leaveBeginDate(LocalDate.parse("2024-06-10")).leaveEndDate(LocalDate.parse("2024-06-12")).requestedDateAndTime(LocalDateTime.parse("2024-05-05T14:30:00")).reason("Reason 10").build());

        // Request 11
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199605050606").get()).leaveBeginDate(LocalDate.parse("2024-06-15")).leaveEndDate(LocalDate.parse("2024-06-17")).requestedDateAndTime(LocalDateTime.parse("2024-05-10T17:20:00")).reason("Reason 11").build());

        // Request 12
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("198812120587").get()).leaveBeginDate(LocalDate.parse("2024-06-20")).leaveEndDate(LocalDate.parse("2024-06-22")).requestedDateAndTime(LocalDateTime.parse("2024-05-15T09:45:00")).reason("Reason 12").build());

        // Request 13
        requestLeaves.add(RequestLeave.builder().staff(staffRepository.findById("199707110986").get()).leaveBeginDate(LocalDate.parse("2024-06-25")).leaveEndDate(LocalDate.parse("2024-06-27")).requestedDateAndTime(LocalDateTime.parse("2024-05-20T12:00:00")).reason("Reason 13").build());

        for (RequestLeave requestLeave : requestLeaves) {
            // Set the leaveStatus to LeaveStatus.Not_Concerned_Yet
            requestLeave.setLeaveStatus(LeaveStatus.Not_Concerned_Yet);
        }

        requestLeaveRepository.saveAll(requestLeaves);
    }

    public void addCasualityDays() {
        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W001"))
                        .monday(true)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(true)
                        .friday(false)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W002"))
                        .monday(false)
                        .tuesday(true)
                        .wednesday(false)
                        .thursday(false)
                        .friday(true)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W003"))
                        .monday(false)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(false)
                        .friday(true)
                        .saturday(true)
                        .build()
        );

        // Continue for the rest of the wards
        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W004"))
                        .monday(true)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(false)
                        .friday(false)
                        .saturday(true)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W005"))
                        .monday(false)
                        .tuesday(true)
                        .wednesday(false)
                        .thursday(false)
                        .friday(true)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W006"))
                        .monday(false)
                        .tuesday(false)
                        .wednesday(true)
                        .thursday(true)
                        .friday(false)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W007"))
                        .monday(true)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(true)
                        .friday(false)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W008"))
                        .monday(false)
                        .tuesday(true)
                        .wednesday(false)
                        .thursday(false)
                        .friday(false)
                        .saturday(true)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W009"))
                        .monday(true)
                        .tuesday(false)
                        .wednesday(true)
                        .thursday(false)
                        .friday(false)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W010"))
                        .monday(false)
                        .tuesday(false)
                        .wednesday(true)
                        .thursday(false)
                        .friday(false)
                        .saturday(true)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W011"))
                        .monday(false)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(true)
                        .friday(false)
                        .saturday(true)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W012"))
                        .monday(false)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(true)
                        .friday(true)
                        .saturday(false)
                        .build()
        );

        casualityDaysRepository.save(
                CasualityDays.builder()
                        .wardNo(wardRepository.findByWardNo("W013"))
                        .monday(false)
                        .tuesday(false)
                        .wednesday(false)
                        .thursday(true)
                        .friday(false)
                        .saturday(true)
                        .build()
        );
    }




}

