package group17.HospitalWardManagementSystem.Service.AdminServices;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.MatronDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Service.GeneralServices.PasswordGenerateService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.UsernameGenerateService;
import group17.HospitalWardManagementSystem.Service.StaffDetails.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatronService {
    @Autowired
    private MatronRepository matronRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private UsernameGenerateService usernameGenerateService;
    @Autowired
    private PasswordGenerateService passwordGenerateService;


    public Boolean AddMatron(MatronDto matronDto){
        User user = new User();
        Matron matron = new Matron();
        try{
            //Add matron


            //Add matron as a user
            user.setNic(matronDto.getNic());
            user.setFullName(matronDto.getFullName());
            user.setFirstName(matronDto.getFirstName());
            user.setLastName(matronDto.getLastName());
            user.setPosition(UserRole.Matron);
            user.setDob(matronDto.getDob());
            user.setEmail(matronDto.getEmail());
            user.setMobileNo(matronDto.getMobileNo());
            user.setUsername(usernameGenerateService.generateUsername(matronDto.getEmail()));
            String password = passwordGenerateService.passwordGenerate();
            user.setPassword(passwordEncoder.encode(password));
            // Need rto delete
            System.out.println(password);

            userRepository.save(user);

            matron.setNic(matronDto.getNic());
            matron.setServiceStartedDate(matronDto.getServiceDate());
            matronRepository.save(matron);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public List<MatronDto> getMatronDetailService() {
        List<MatronDto> matronDetails = new ArrayList<>();
        try {
            List<Matron> matrons = matronRepository.findAll();
            for (Matron matron : matrons) {
                Optional<User> user = userRepository.findByNic(matron.getNic());
                if (user.isPresent()) {
                    MatronDto matronDto = getMatronDto(matron, user.get());

                    matronDetails.add(matronDto);
                } else {

                    System.out.println("User not found for matron with NIC: " + matron.getNic());
                }
            }
            return matronDetails;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while fetching matron details", ex);
        }
    }

    private static MatronDto getMatronDto(Matron matron, User user) {
        MatronDto matronDto = new MatronDto();
        matronDto.setNic(user.getNic());
        matronDto.setFullName(user.getFullName());
        matronDto.setFirstName(user.getFirstName());
        matronDto.setLastName(user.getLastName());
        matronDto.setServiceDate(matron.getServiceStartedDate());
        matronDto.setDob(user.getDob());
        matronDto.setEmail(user.getEmail());
        matronDto.setMobileNo(user.getMobileNo());
        return matronDto;
    }


}
