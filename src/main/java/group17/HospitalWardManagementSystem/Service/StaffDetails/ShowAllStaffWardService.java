package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAllStaffWardService {

    public final UserRepository userRepository;

    public final StaffRepository staffRepository;

    public final WardRepository wardRepository;
    @Autowired
    public ShowAllStaffWardService(UserRepository userRepository, StaffRepository staffRepository, WardRepository wardRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.wardRepository = wardRepository;
    }

    public List<ShowStaffDto> showStaff(String wardNo){
        List<ShowStaffDto> staffDto;

        Ward targetWard=findrelaventWard(wardNo);

        staffDto=new ArrayList<>(findByStaffMembers(targetWard));
        if(staffDto.isEmpty()){
            throw new IllegalArgumentException("This ward has not staff members yet!");
        }

        return staffDto;
    }

    public List<ShowStaffDto> findByStaffMembers(Ward wardNo){
        return userRepository.findByWardNo(wardNo);
    }

    public Ward findrelaventWard(String wardNo){
        return wardRepository.findById(wardNo).orElseThrow(() ->
                new EntityNotFoundException("Cannot find ward details with ward No: " + wardNo
                        + ". Contact admin to resolve!"));
    }


}
