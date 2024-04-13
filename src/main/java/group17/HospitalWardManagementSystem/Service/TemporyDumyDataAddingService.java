package group17.HospitalWardManagementSystem.Service;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemporyDumyDataAddingService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TemporyDumyDataAddingService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addNursesData(){
        List<User> nurses = new ArrayList<>();

        // Add all 30 nurse data records
        nurses.add(User.builder().nic("198511120367").fullName("R M P P Wickramasinghe").firstName("Rajitha").lastName("Wickramasinghe").username("user3").password(passwordEncoder.encode("password3")).dob(LocalDate.parse("1990-12-20")).email("rajitha@example.com").Position(UserRole.Nurse).mobileNo("345-678-9012").build());
        nurses.add(User.builder().nic("199601280278").fullName("W M K P Silva").firstName("Wimal").lastName("Silva").username("user6").password(passwordEncoder.encode("password6")).dob(LocalDate.parse("1995-01-30")).email("wimal@example.com").Position(UserRole.Nurse).mobileNo("678-901-2345").build());
        nurses.add(User.builder().nic("199803150256").fullName("M A N K Perera").firstName("Madushi").lastName("Perera").username("user9").password(passwordEncoder.encode("password9")).dob(LocalDate.parse("1993-04-07")).email("madushi@example.com").Position(UserRole.Nurse).mobileNo("901-234-5678").build());
        nurses.add(User.builder().nic("198502020202").fullName("L H S Fernando").firstName("Lahiru").lastName("Fernando").username("user12").password(passwordEncoder.encode("password12")).dob(LocalDate.parse("1979-10-20")).email("lahiru@example.com").Position(UserRole.Nurse).mobileNo("234-567-8901").build());
        nurses.add(User.builder().nic("199707070707").fullName("A R K Jayawardena").firstName("Anusha").lastName("Jayawardena").username("user13").password(passwordEncoder.encode("password13")).dob(LocalDate.parse("1991-09-25")).email("anusha@example.com").Position(UserRole.Nurse).mobileNo("345-678-9012").build());
        nurses.add(User.builder().nic("199504300378").fullName("K G S P Silva").firstName("Kasun").lastName("Silva").username("user10").password(passwordEncoder.encode("password10")).dob(LocalDate.parse("1989-06-23")).email("kasun@example.com").Position(UserRole.Nurse).mobileNo("012-345-6789").build());
        nurses.add(User.builder().nic("199909090909").fullName("T H L Fernando").firstName("Tharindu").lastName("Fernando").username("user16").password(passwordEncoder.encode("password16")).dob(LocalDate.parse("1997-07-20")).email("tharindu@example.com").Position(UserRole.Nurse).mobileNo("678-901-2345").build());
        nurses.add(User.builder().nic("199606060606").fullName("S N M Perera").firstName("Saman").lastName("Perera").username("user21").password(passwordEncoder.encode("password21")).dob(LocalDate.parse("1992-06-15")).email("saman@example.com").Position(UserRole.Nurse).mobileNo("345-678-9012").build());
        nurses.add(User.builder().nic("199012250394").fullName("W M P Perera").firstName("Waruna").lastName("Perera").username("user28").password(passwordEncoder.encode("password28")).dob(LocalDate.parse("1981-08-30")).email("waruna@example.com").Position(UserRole.Nurse).mobileNo("890-123-4567").build());
        nurses.add(User.builder().nic("199701010101").fullName("P A K Silva").firstName("Prasad").lastName("Silva").username("user33").password(passwordEncoder.encode("password33")).dob(LocalDate.parse("1996-05-12")).email("prasad@example.com").Position(UserRole.Nurse).mobileNo("901-234-5678").build());
        nurses.add(User.builder().nic("198806060606").fullName("M L R Fernando").firstName("Malithi").lastName("Fernando").username("user37").password(passwordEncoder.encode("password37")).dob(LocalDate.parse("1983-04-20")).email("malithi@example.com").Position(UserRole.Nurse).mobileNo("012-345-6789").build());
        nurses.add(User.builder().nic("199409040404").fullName("T W K Perera").firstName("Thilina").lastName("Perera").username("user42").password(passwordEncoder.encode("password42")).dob(LocalDate.parse("1991-11-20")).email("thilina@example.com").Position(UserRole.Nurse).mobileNo("123-456-7890").build());
        nurses.add(User.builder().nic("198803030303").fullName("K R L Silva").firstName("Kumudu").lastName("Silva").username("user48").password(passwordEncoder.encode("password48")).dob(LocalDate.parse("1978-07-15")).email("kumudu@example.com").Position(UserRole.Nurse).mobileNo("234-567-8901").build());
        nurses.add(User.builder().nic("199404040404").fullName("S D M Fernando").firstName("Supun").lastName("Fernando").username("user55").password(passwordEncoder.encode("password55")).dob(LocalDate.parse("1990-10-15")).email("supun@example.com").Position(UserRole.Nurse).mobileNo("345-678-9012").build());
        nurses.add(User.builder().nic("199808080808").fullName("P D W Perera").firstName("Prasanna").lastName("Perera").username("user62").password(passwordEncoder.encode("password62")).dob(LocalDate.parse("1994-03-25")).email("prasanna@example.com").Position(UserRole.Nurse).mobileNo("456-789-0123").build());
        nurses.add(User.builder().nic("199009090909").fullName("M L M Silva").firstName("Malithi").lastName("Silva").username("user69").password(passwordEncoder.encode("password69")).dob(LocalDate.parse("1986-12-20")).email("malithi@example.com").Position(UserRole.Nurse).mobileNo("567-890-1234").build());
        nurses.add(User.builder().nic("199709070707").fullName("W H G Fernando").firstName("Wasantha").lastName("Fernando").username("user76").password(passwordEncoder.encode("password76")).dob(LocalDate.parse("1993-08-10")).email("wasantha@example.com").Position(UserRole.Nurse).mobileNo("678-901-2345").build());
        nurses.add(User.builder().nic("198602020202").fullName("S A L Perera").firstName("Sampath").lastName("Perera").username("user81").password(passwordEncoder.encode("password81")).dob(LocalDate.parse("1977-02-10")).email("sampath@example.com").Position(UserRole.Nurse).mobileNo("789-012-3456").build());
        nurses.add(User.builder().nic("199701010101").fullName("T D K Silva").firstName("Thilani").lastName("Silva").username("user87").password(passwordEncoder.encode("password87")).dob(LocalDate.parse("1996-09-15")).email("thilani@example.com").Position(UserRole.Nurse).mobileNo("901-234-5678").build());
        nurses.add(User.builder().nic("198805080808").fullName("K D K Fernando").firstName("Kasun").lastName("Fernando").username("user93").password(passwordEncoder.encode("password93")).dob(LocalDate.parse("1981-12-10")).email("kasun@example.com").Position(UserRole.Nurse).mobileNo("012-345-6789").build());
        nurses.add(User.builder().nic("199703030303").fullName("N M P Perera").firstName("Nadeeka").lastName("Perera").username("user99").password(passwordEncoder.encode("password99")).dob(LocalDate.parse("1979-05-25")).email("nadeeka@example.com").Position(UserRole.Nurse).mobileNo("123-456-7890").build());
        nurses.add(User.builder().nic("199303030303").fullName("T R K Silva").firstName("Thilak").lastName("Silva").username("user104").password(passwordEncoder.encode("password104")).dob(LocalDate.parse("1980-06-25")).email("thilak@example.com").Position(UserRole.Nurse).mobileNo("234-567-8901").build());
        nurses.add(User.builder().nic("199712121212").fullName("R W G Fernando").firstName("Ruchira").lastName("Fernando").username("user110").password(passwordEncoder.encode("password110")).dob(LocalDate.parse("1994-02-15")).email("ruchira@example.com").Position(UserRole.Nurse).mobileNo("345-678-9012").build());
        nurses.add(User.builder().nic("198710101010").fullName("M S D Perera").firstName("Malithi").lastName("Perera").username("user115").password(passwordEncoder.encode("password115")).dob(LocalDate.parse("1983-12-10")).email("malithi@example.com").Position(UserRole.Nurse).mobileNo("456-789-0123").build());
        nurses.add(User.builder().nic("199010101010").fullName("W K D Silva").firstName("Wimukthi").lastName("Silva").username("user121").password(passwordEncoder.encode("password121")).dob(LocalDate.parse("1986-02-15")).email("wimukthi@example.com").Position(UserRole.Nurse).mobileNo("567-890-1234").build());
        nurses.add(User.builder().nic("199503030303").fullName("P R K Fernando").firstName("Prabath").lastName("Fernando").username("user126").password(passwordEncoder.encode("password126")).dob(LocalDate.parse("1990-10-15")).email("prabath@example.com").Position(UserRole.Nurse).mobileNo("678-901-2345").build());
        nurses.add(User.builder().nic("198412121212").fullName("S T M Perera").firstName("Supun").lastName("Perera").username("user132").password(passwordEncoder.encode("password132")).dob(LocalDate.parse("1977-02-10")).email("supun@example.com").Position(UserRole.Nurse).mobileNo("789-012-3456").build());
        nurses.add(User.builder().nic("199212121212").fullName("K S D Silva").firstName("Kavindu").lastName("Silva").username("user137").password(passwordEncoder.encode("password137")).dob(LocalDate.parse("1981-08-10")).email("kavindu@example.com").Position(UserRole.Nurse).mobileNo("890-123-4567").build());
        nurses.add(User.builder().nic("199706060606").fullName("T N D Fernando").firstName("Thilina").lastName("Fernando").username("user143").password(passwordEncoder.encode("password143")).dob(LocalDate.parse("1992-03-25")).email("thilina@example.com").Position(UserRole.Nurse).mobileNo("901-234-5678").build());

        // Save all nurses
        userRepository.saveAll(nurses);
    }

    public void addSisterData(){
        List<User> sisters = new ArrayList<>();

        // Add all 13 sister data records
        sisters.add(User.builder().nic("199722350456").fullName("A D P N Fernando").firstName("Asanka").lastName("Fernando").username("user2").password(passwordEncoder.encode("password2")).dob(LocalDate.parse("1980-09-15")).email("asanka@example.com").Position(UserRole.Sister).mobileNo("234-567-8901").build());
        sisters.add(User.builder().nic("198808170591").fullName("D L T Perera").firstName("Dilani").lastName("Perera").username("user5").password(passwordEncoder.encode("password5")).dob(LocalDate.parse("1978-07-05")).email("dilani@example.com").Position(UserRole.Sister).mobileNo("567-890-1234").build());
        sisters.add(User.builder().nic("199010050189").fullName("P N W Jayawardena").firstName("Pradeep").lastName("Jayawardena").username("user8").password(passwordEncoder.encode("password8")).dob(LocalDate.parse("1987-08-12")).email("pradeep@example.com").Position(UserRole.Sister).mobileNo("890-123-4567").build());
        sisters.add(User.builder().nic("199212121212").fullName("W A S Silva").firstName("Waruna").lastName("Silva").username("user17").password(passwordEncoder.encode("password17")).dob(LocalDate.parse("1984-02-10")).email("waruna@example.com").Position(UserRole.Sister).mobileNo("789-012-3456").build());
        sisters.add(User.builder().nic("198602060707").fullName("T K R Perera").firstName("Tharaka").lastName("Perera").username("user26").password(passwordEncoder.encode("password26")).dob(LocalDate.parse("1977-06-15")).email("tharaka@example.com").Position(UserRole.Sister).mobileNo("234-567-8901").build());
        sisters.add(User.builder().nic("199706250696").fullName("S P L Fernando").firstName("Supun").lastName("Fernando").username("user24").password(passwordEncoder.encode("password24")).dob(LocalDate.parse("1992-03-25")).email("supun@example.com").Position(UserRole.Sister).mobileNo("456-789-0123").build());
        sisters.add(User.builder().nic("199012251203").fullName("W P M Fernando").firstName("Wimukthi").lastName("Fernando").username("user39").password(passwordEncoder.encode("password39")).dob(LocalDate.parse("1983-12-10")).email("wimukthi@example.com").Position(UserRole.Sister).mobileNo("901-234-5678").build());
        sisters.add(User.builder().nic("198702020303").fullName("R A H Silva").firstName("Roshan").lastName("Silva").username("user44").password(passwordEncoder.encode("password44")).dob(LocalDate.parse("1979-04-25")).email("roshan@example.com").Position(UserRole.Sister).mobileNo("567-890-1234").build());
        sisters.add(User.builder().nic("199307070404").fullName("N K D Perera").firstName("Nimashi").lastName("Perera").username("user51").password(passwordEncoder.encode("password51")).dob(LocalDate.parse("1986-02-15")).email("nimashi@example.com").Position(UserRole.Sister).mobileNo("890-123-4567").build());
        sisters.add(User.builder().nic("198101010505").fullName("M P G Silva").firstName("Malithi").lastName("Silva").username("user58").password(passwordEncoder.encode("password58")).dob(LocalDate.parse("1976-08-20")).email("malithi@example.com").Position(UserRole.Sister).mobileNo("123-456-7890").build());
        sisters.add(User.builder().nic("199605050606").fullName("L S M Fernando").firstName("Lakshan").lastName("Fernando").username("user64").password(passwordEncoder.encode("password64")).dob(LocalDate.parse("1993-05-25")).email("lakshan@example.com").Position(UserRole.Sister).mobileNo("345-678-9012").build());
        sisters.add(User.builder().nic("198812120587").fullName("A S T Silva").firstName("Asanka").lastName("Silva").username("user69").password(passwordEncoder.encode("password69")).dob(LocalDate.parse("1975-12-20")).email("asanka@example.com").Position(UserRole.Sister).mobileNo("678-901-2345").build());
        sisters.add(User.builder().nic("199707110986").fullName("R S D P Fernando").firstName("Ruchira").lastName("Fernando").username("user74").password(passwordEncoder.encode("password74")).dob(LocalDate.parse("1994-02-15")).email("ruchira@example.com").Position(UserRole.Sister).mobileNo("234-567-8901").build());

        // Save all sisters
        userRepository.saveAll(sisters);
    }

    public void addNurseMatron(){
        List<User> matrons = new ArrayList<>();

        // Add all 5 matron data records
        matrons.add(User.builder().nic("200025800895").fullName("K H M L Jayasinghe").firstName("Lakshika").lastName("Jayasinghe").username("user1").password(passwordEncoder.encode("password1")).dob(LocalDate.parse("1975-05-10")).email("lakshika@example.com").Position(UserRole.Matron).mobileNo("123-456-7890").build());
        matrons.add(User.builder().nic("198312040789").fullName("S H D S Bandara").firstName("Saman").lastName("Bandara").username("user4").password(passwordEncoder.encode("password4")).dob(LocalDate.parse("1985-03-25")).email("saman@example.com").Position(UserRole.Matron).mobileNo("456-789-0123").build());
        matrons.add(User.builder().nic("198111190167").fullName("J G A P Gunasekara").firstName("Janitha").lastName("Gunasekara").username("user7").password(passwordEncoder.encode("password7")).dob(LocalDate.parse("1982-11-18")).email("janitha@example.com").Position(UserRole.Matron).mobileNo("789-012-3456").build());
        matrons.add(User.builder().nic("199707110986").fullName("R S D P Fernando").firstName("Ruchira").lastName("Fernando").username("user27").password(passwordEncoder.encode("password27")).dob(LocalDate.parse("1994-02-15")).email("ruchira@example.com").Position(UserRole.Matron).mobileNo("234-567-8901").build());
        matrons.add(User.builder().nic("199303031703").fullName("T D K Perera").firstName("Thilani").lastName("Perera").username("user46").password(passwordEncoder.encode("password46")).dob(LocalDate.parse("1984-12-25")).email("thilani@example.com").Position(UserRole.Matron).mobileNo("678-901-2345").build());

        // Save all matrons
        userRepository.saveAll(matrons);
    }
}
