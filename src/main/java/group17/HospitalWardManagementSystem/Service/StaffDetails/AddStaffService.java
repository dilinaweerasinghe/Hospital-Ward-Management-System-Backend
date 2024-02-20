package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.AddStaffRepository;
import group17.HospitalWardManagementSystem.Repository.AddUserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddStaffService {

    @Autowired
    public AddStaffRepository addStaffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AddUserRepository addUserRepository;

    @Autowired
    public WardRepository wardRepository;


    public String staffSave(AddStaffDto addStaffDto){

        User user=new User();
        Staff staff=new Staff();
        Ward ward=new Ward();

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
        staff.setNic(addStaffDto.getNic());
        ward.setWardNo(addStaffDto.getWardNo());
        ward.setWardName(wardRepository.findByWardNo(addStaffDto.getWardNo()).getWardName());

        wardRepository.save(ward);
        staff.setWardNo(ward);
        staff.setServiceStartedDate(addStaffDto.getServiceStartedDate());
        staff.setLeaveNum(addStaffDto.getLeaveNum());
        staff.setRemainingCasualLeaves(addStaffDto.getRemainingCasualLeaves());
        staff.setRemainingVacationLeave(addStaffDto.getRemainingVacationLeave());

         //new add
        addUserRepository.save(user);
        addStaffRepository.save(staff);

        return addStaffDto.getFullName();
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}



