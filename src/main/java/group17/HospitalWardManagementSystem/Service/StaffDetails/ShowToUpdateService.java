package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowToUpdateDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowToUpdateService {
    @Autowired
    public StaffRepository staffRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public WardRepository wardRepository;

    public ShowToUpdateDto showfullStaff(String wardNo){
        ShowToUpdateDto showToUpdateDto=new ShowToUpdateDto();

        Ward ward=findWard(wardNo);

        String nic=findNic(ward);

        showToUpdateDto.setFirstName(findUser(nic).getFirstName());
        showToUpdateDto.setLastName(findUser(nic).getLastName());
        showToUpdateDto.setFullName(findUser(nic).getFullName());
        showToUpdateDto.setNic(findUser(nic).getNic());
        showToUpdateDto.setEmail(findUser(nic).getEmail());
        showToUpdateDto.setDob(findUser(nic).getDob());
        showToUpdateDto.setMobileNo(findUser(nic).getMobileNo());
        if(findUser(nic).getPosition().equals(UserRole.Sister)){
            showToUpdateDto.setPosition("Sister");
        }
        showToUpdateDto.setLeaveNum(findStaff(nic).getLeaveNum());
        showToUpdateDto.setServiceStartedDate(findStaff(nic).getServiceStartedDate());



        return showToUpdateDto;
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
        return userRepository.findByNic(nic);
    }


}
