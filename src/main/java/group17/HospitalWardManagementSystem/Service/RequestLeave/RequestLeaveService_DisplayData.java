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

    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public RequestLeaveService_DisplayData(UserRepository userRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }


    public MemberDto provideAutoFilings(String username){
        MemberDto memberDto = new MemberDto();
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new IllegalArgumentException("Cannot find your details in Our System. please Contact Admin!");
        }
        Optional<Staff> staff = staffRepository.findById(user.getNic());
        Staff staffMem;



        if(staff.isPresent()){
            staffMem = staff.get();
            memberDto.setLeaveNum(staffMem.getLeaveNum());
            memberDto.setNumberOfTakenCasualLeaves(23 - staffMem.getRemainingCasualLeaves());
            memberDto.setNumberOfTakenVacationLeaves(23 - staffMem.getRemainingVacationLeave());

        }else{
            throw new IllegalArgumentException("Cannot find your details in Our System. please Contact Admin!");
        }
        memberDto.setNic(user.getNic());
        memberDto.setFullName(user.getFullName());
        memberDto.setPosition(user.getPosition());


        return memberDto;


    }
}


