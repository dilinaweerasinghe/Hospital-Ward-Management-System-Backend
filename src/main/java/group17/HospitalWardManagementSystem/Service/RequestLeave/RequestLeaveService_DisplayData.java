package group17.HospitalWardManagementSystem.Service.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestLeaveService_DisplayData {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    public MemberDto provideAutoFilings(String username){
        MemberDto memberDto = new MemberDto();
        User user = userRepository.findByUsername(username);
        Optional<Staff> staff = staffRepository.findById(user.getNic());
        Staff staffMem;

        if(staff.isPresent()){
            staffMem = staff.get();
            memberDto.setLeaveNo(staffMem.getLeaveNum());
            memberDto.setNumberOfTakenCasualLeaves(21 - staffMem.getRemainingCasualLeaves());
            memberDto.setNumberOfTakenVacationLeaves(21 - staffMem.getRemainingVacationLeave());

        }else{

        }
        memberDto.setNic(user.getNic());
        memberDto.setFullName(user.getFullName());
        memberDto.setPosition(user.getPosition());

        return memberDto;
    }
}

