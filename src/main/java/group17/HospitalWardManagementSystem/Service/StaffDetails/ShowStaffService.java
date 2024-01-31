package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowStaffService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public StaffRepository staffRepository;

    public ShowStaffDto getStaff(String username){
        ShowStaffDto dto=new ShowStaffDto();

        dto.setNic(userRepository.findByUsername(username).getNic());
        dto.setFullName(userRepository.findByUsername(username).getFullName());
        dto.setFirstName(userRepository.findByUsername(username).getFirstName());
        dto.setLastName(userRepository.findByUsername(username).getLastName());
        dto.setUsername(userRepository.findByUsername(username).getUsername());
        dto.setPassword(userRepository.findByUsername(username).getPassword());
        dto.setDob(userRepository.findByUsername(username).getDob());
        dto.setEmail(userRepository.findByUsername(username).getEmail());
        if(userRepository.findByUsername(username).equals(UserRole.Admin)){
            dto.setPosition("Admin");};
        dto.setMobileNo(userRepository.findByUsername(username).getMobileNo());


        String nic=userRepository.findByUsername(username).getNic();
        Staff staff = staffRepository.findByNic(nic);
        dto.setServiceStartedDate(staff.getServiceStartedDate());
        dto.setLeaveNum(staff.getLeaveNum());
        dto.setRemainingCasualLeaves(staff.getRemainingCasualLeaves());
        dto.setRemainingVacationLeave(staff.getRemainingVacationLeave());



        return dto;
    }


}
