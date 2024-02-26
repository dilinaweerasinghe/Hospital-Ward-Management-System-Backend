package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.UpdateDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStaffService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public StaffRepository staffRepository;

    public String updateCustomer(UpdateDto updateDto){

        Staff staff=new Staff();
        User user=new User();

        Staff targetStaff=findStaff(updateDto.getNic());
        User targetUser=findUser(updateDto.getNic());

        targetUser.setFirstName(updateDto.getFirstName());
        targetUser.setLastName(updateDto.getLastName());
        targetUser.setFullName(updateDto.getFullName());
        if(updateDto.getPosition().equals(UserRole.Admin)){
            targetUser.setPosition(UserRole.Admin);
        } else if (updateDto.getPosition().equals(UserRole.Sister)) {
            targetUser.setPosition(UserRole.Sister);
        } else if (updateDto.getPosition().equals(UserRole.Nurse)) {
            targetUser.setPosition(UserRole.Nurse);
        }else if(updateDto.getPosition().equals(UserRole.Matron)){
            targetUser.setPosition(UserRole.Matron);
        }
        targetUser.setDob(updateDto.getDob());
        targetUser.setMobileNo(updateDto.getMobileNo());
        targetUser.setEmail(updateDto.getEmail());
        targetStaff.setServiceStartedDate(updateDto.getServiceStartedDate());
        targetStaff.setLeaveNum(updateDto.getLeaveNum());

        userRepository.save(targetUser);
        staffRepository.save(targetStaff);

        return "Successfully Updated the Staff";
    }

    public Staff findStaff(String nic){
        return staffRepository.findByNic(nic);
    }

    public User findUser(String nic){
        return userRepository.findByNic(nic).get();
    }
}
