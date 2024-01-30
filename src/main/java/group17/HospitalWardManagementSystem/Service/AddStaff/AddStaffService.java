package group17.HospitalWardManagementSystem.Service.AddStaff;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.AddStaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.AddStaffRepository;
import group17.HospitalWardManagementSystem.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddStaffService {

    @Autowired
    public AddStaffRepository addStaffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String staffSave(AddStaffDto addStaffDto){

        User user=new User();


        user.setNic(addStaffDto.getNic());
        user.setFullName(addStaffDto.getFullName());
        user.setFirstName(addStaffDto.getFirstName());
        user.setLastName(addStaffDto.getLastName());
        user.setUsername(addStaffDto.getUsername());
        user.setPassword(getEncodedPassword(addStaffDto.getPassword()));
        user.setDob(addStaffDto.getDob());
        user.setEmail(addStaffDto.getEmail());
        if(addStaffDto.getPosition().equals("Admin")){
            user.setPosition(UserRole.Admin);
        }else if(addStaffDto.getPosition().equals("Matron")){
            user.setPosition(UserRole.Matron);
        }else if(addStaffDto.getPosition().equals("Sister")){
            user.setPosition(UserRole.Sister);
        }else if(addStaffDto.getPosition().equals("Nurse")){
            user.setPosition(UserRole.Nurse);
        }

        user.setMobileNo(addStaffDto.getMobileNo());

        addStaffRepository.save(user);

        return addStaffDto.getFullName();
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}



