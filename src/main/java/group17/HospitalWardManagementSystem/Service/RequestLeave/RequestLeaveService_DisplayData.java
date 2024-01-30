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
            memberDto.setNumberOfTakenCasualLeaves(24 - staffMem.getRemainingCasualLeaves());
            memberDto.setNumberOfTakenVacationLeaves(22 - staffMem.getRemainingVacationLeave());

        }else{

        }
        memberDto.setNic(user.getNic());
        memberDto.setFullName(user.getFullName());
        memberDto.setPosition(user.getPosition());

        return memberDto;
    }
}

//    @Autowired
//    private RequestLeaveRepository requestLeaveRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    public RequestLeaveService(RequestLeaveRepository requestLeaveRepository, UserRepository userRepository,StaffRepository staffRepository){
//        this.requestLeaveRepository=requestLeaveRepository;
//        this.userRepository=userRepository;
//        this.staffRepository=staffRepository;
//    }
//
//    //Details retrieve from User entity method
//    public UserDto getUserDetails(String userName) {
//        User user = userRepository.findByUsername(userName);
//        UserDto userDTO = new UserDto();
//        userDTO.setFullName(user.getFullName());
//        userDTO.setPosition(user.getPosition());
//        return userDTO;
//    }
//
//    //Details retrieve from Staff entity method
//    public memberDto getStaffDetails(String userName){
//
//        String username = userName;
//        UserDto userDTO = getUserDetails(username);
//
//        //convert userDto to user
//        User user = new User();
//        user.setFullName(userDTO.getFullName());
//        user.setPosition(userDTO.getPosition());
//
//        String nic = user.getNic();
//
//        // Retrieve Staff
//        Staff staff = staffRepository.findByNic(nic);
//
//       memberDto staffDto = new memberDto();
//        if (staff != null) {
//            staffDto.setLeaveNo(staff.getLeaveNo());
//            staffDto.setNic(staff.getNic());
//            //staffDto.setUser(user);
//        }
//        return staffDto;
//    }
//
//    public void requestLeave(RequestLeaveDto leaveRequestDTO, String userName) {
//        // Convert LeaveRequestDTO to RequestLeave entity
//        RequestLeave requestLeave = new RequestLeave();
//
//        memberDto staffDto = getStaffDetails(userName);
//        Staff staff = new Staff();
//        staff.setLeaveNo(staffDto.getLeaveNo());
//        staff.setNic(staffDto.getNic());
//        //staff.setServiceStartedDate(staffDto.ge);
//
//        requestLeave.setStaff(staff);
//
//        // Set other fields from LeaveRequestDTO
//        requestLeave.setNoOfRequestedDates(leaveRequestDTO.getNoOfRequestedDates());
//        requestLeave.setLeaveBeginDate(leaveRequestDTO.getLeaveBeginDate());
//        requestLeave.setLeaveEndDate(leaveRequestDTO.getLeaveEndDate());
//        requestLeave.setRequestedDateAndTime(LocalDateTime.now());
//        requestLeave.setReason(leaveRequestDTO.getReason());
//        requestLeave.setDueDate(leaveRequestDTO.getLeaveEndDate());
//
//        // Save the RequestLeave entity to the database
//        requestLeaveRepository.save(requestLeave);
//    }
//
//
//
//
//
//}
