package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowToUpdateDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowToUpdateStaffMemberDetailsService {
    @Autowired
    public StaffRepository staffRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public WardRepository wardRepository;

    public ShowToUpdateDto showSister(String wardNo){
        ShowToUpdateDto sisterDto=new ShowToUpdateDto();

        Ward ward=findWard(wardNo);

        String nic=findNic(ward);

        sisterDto.setFirstName(findUser(nic).getFirstName());
        sisterDto.setLastName(findUser(nic).getLastName());
        sisterDto.setFullName(findUser(nic).getFullName());
        sisterDto.setNic(findUser(nic).getNic());
        sisterDto.setEmail(findUser(nic).getEmail());
        sisterDto.setDob(findUser(nic).getDob());
        sisterDto.setMobileNo(findUser(nic).getMobileNo());
        if(findUser(nic).getPosition().equals(UserRole.Sister)){
            sisterDto.setPosition("Sister");
        }
        sisterDto.setLeaveNo(findStaff(nic).getLeaveNum());
        sisterDto.setServiceStartedDate(findStaff(nic).getServiceStartedDate());



        return sisterDto;
    }


    public ShowToUpdateDto showNurse(String nic){

        ShowToUpdateDto nurseDto = new ShowToUpdateDto();

        User user = userRepository.findByNic(nic).orElseThrow(()->new EntityNotFoundException("Staff member do not found"));

        Staff staff = staffRepository.findByNic(nic);

        nurseDto.setFirstName(user.getFirstName());
        nurseDto.setLastName(user.getLastName());
        nurseDto.setFullName(user.getFullName());
        nurseDto.setNic(user.getNic());
        nurseDto.setEmail(user.getEmail());
        nurseDto.setDob(user.getDob());
        nurseDto.setMobileNo(user.getMobileNo());
        nurseDto.setPosition(user.getPosition().toString());
        nurseDto.setLeaveNo(staff.getLeaveNum());
        nurseDto.setWardNo(staff.getWardNo().getWardNo());
        nurseDto.setRemainingCasualLeaves(staff.getRemainingCasualLeaves());
        nurseDto.setRemainingVacationLeaves(staff.getRemainingVacationLeave());
        nurseDto.setServiceStartedDate(staff.getServiceStartedDate());



        return nurseDto;
    }



    public Ward findWard(String wardNo){
        return wardRepository.findByWardNo(wardNo);
    }

    public String findNic(Ward wardNo){
        return staffRepository.findByWard(wardNo);
    }

    public Staff findStaff(String nic){
        return staffRepository.findByNic(nic);
    }

    public User findUser(String nic){
        return userRepository.findByNic(nic).get();
    }


}
