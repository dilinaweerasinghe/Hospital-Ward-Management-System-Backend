package group17.HospitalWardManagementSystem.Service.AdminServices;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddMatronDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Service.StaffDetails.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public Boolean AddMatron(AddMatronDto addMatronDto){
        User user = new User();
        Matron matron = new Matron();
        String password = null;
        String username = null;
        try{
            //Add matron
            matron.setNic(addMatronDto.getNic());
            matronRepository.save(matron);

            //Add matron as a user
            user.setNic(addMatronDto.getNic());
            user.setFullName(addMatronDto.getFullName());
            user.setFirstName(addMatronDto.getFirstName());
            user.setLastName(addMatronDto.getLastName());
            user.setPosition(UserRole.Matron);
            user.setDob(addMatronDto.getDob());
            user.setEmail(addMatronDto.getEmail());
            user.setMobileNo(addMatronDto.getMobileNo());
            user.setUsername(username);
            user.setPassword(password);


            return true;

        }catch (Exception e){
            return false;
        }
    }

}
