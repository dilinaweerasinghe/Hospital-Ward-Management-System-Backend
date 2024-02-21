package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowStaffService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public StaffRepository staffRepository;

    @Autowired
    public WardRepository wardRepository;

    public List<ShowStaffDto> showStaff(String wardNo){
        List<ShowStaffDto> staffDto;

        Ward targetWard=findrelaventWard(wardNo);

        staffDto=new ArrayList<>(findByStaffMembers(targetWard));

        return staffDto;
    }

    public List<ShowStaffDto> findByStaffMembers(Ward wardNo){
        return userRepository.findByWardNo(wardNo);
    }

    public Ward findrelaventWard(String wardNo){
        return wardRepository.findByWardNo(wardNo);
    }


}
