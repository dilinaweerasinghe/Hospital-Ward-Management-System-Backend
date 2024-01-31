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



        if(userRepository.existsById(updateDto.getNic())){
            User user=new User();
            Staff staff=new Staff();


            user.setNic(updateDto.getNic());
            user.setFullName(updateDto.getFullName());
            user.setFirstName(updateDto.getFirstName());
            user.setLastName(updateDto.getLastName());
            user.setUsername(updateDto.getUsername());
            user.setPassword(updateDto.getPassword());
            user.setDob(updateDto.getDob());
            user.setEmail(updateDto.getEmail());
            if(updateDto.getPosition().equals("Admin")){
                user.setPosition(UserRole.Admin);
            }else if(updateDto.getPosition().equals("Metron")){
                user.setPosition(UserRole.Matron);
            }else if(updateDto.getPosition().equals("Nurse")){
                user.setPosition(UserRole.Nurse);
            }else if(updateDto.getPosition().equals("Sister")){
                user.setPosition(UserRole.Sister);
            }
            user.setMobileNo(updateDto.getMobileNo());

            staff.setNic(updateDto.getNic());
            staff.setServiceStartedDate(updateDto.getServiceStartedDate());
            staff.setLeaveNum(updateDto.getLeaveNum());
            staff.setRemainingCasualLeaves(updateDto.getRemainingCasualLeaves());
            staff.setRemainingVacationLeave(updateDto.getRemainingVacationLeave());

            userRepository.save(user);
            staffRepository.save(staff);

            return "Updated Successfully";

        }else
        {
            return "User Nic No does not exist";
        }


    }
}
