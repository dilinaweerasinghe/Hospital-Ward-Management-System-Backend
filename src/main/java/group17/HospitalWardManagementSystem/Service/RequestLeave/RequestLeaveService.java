package group17.HospitalWardManagementSystem.Service.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.RequestLeaveDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.StaffDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.UserDto;
import group17.HospitalWardManagementSystem.Repository.Login.UserRepository;
import group17.HospitalWardManagementSystem.Repository.RequestLeave.RequestLeaveRepository;
import group17.HospitalWardManagementSystem.Repository.RequestLeave.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestLeaveService {

    @Autowired
    private RequestLeaveRepository requestLeaveRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

    public RequestLeaveService(RequestLeaveRepository requestLeaveRepository, UserRepository userRepository,StaffRepository staffRepository){
        this.requestLeaveRepository=requestLeaveRepository;
        this.userRepository=userRepository;
        this.staffRepository=staffRepository;
    }

    //Details retrieve from User entity method
    public UserDto getUserDetails(String userName) {
        User user = userRepository.findByUsername(userName);
        UserDto userDTO = new UserDto();
        userDTO.setFullName(user.getFullName());
        userDTO.setPosition(user.getPosition());
        return userDTO;
    }

    //Details retrieve from Staff entity method
    public StaffDto getStaffDetails(String userName){

        String username = userName;
        UserDto userDTO = getUserDetails(username);

        //convert userDto to user
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setPosition(userDTO.getPosition());

        String nic = user.getNic();

        // Retrieve Staff
        Staff staff = staffRepository.findByNic(nic);

       StaffDto staffDto = new StaffDto();
        if (staff != null) {
            staffDto.setLeaveNo(staff.getLeaveNo());
            staffDto.setNic(staff.getNic());
            //staffDto.setUser(user);
        }
        return staffDto;
    }

    public void requestLeave(RequestLeaveDto leaveRequestDTO, String userName) {
        // Convert LeaveRequestDTO to RequestLeave entity
        RequestLeave requestLeave = new RequestLeave();

        StaffDto staffDto = getStaffDetails(userName);
        Staff staff = new Staff();
        staff.setLeaveNo(staffDto.getLeaveNo());
        staff.setNic(staffDto.getNic());
        //staff.setServiceStartedDate(staffDto.ge);

        requestLeave.setStaff(staff);

        // Set other fields from LeaveRequestDTO
        requestLeave.setNoOfRequestedDates(leaveRequestDTO.getNoOfRequestedDates());
        requestLeave.setLeaveBeginDate(leaveRequestDTO.getLeaveBeginDate());
        requestLeave.setLeaveEndDate(leaveRequestDTO.getLeaveEndDate());
        requestLeave.setRequestedDateAndTime(LocalDateTime.now());
        requestLeave.setReason(leaveRequestDTO.getReason());
        requestLeave.setDueDate(leaveRequestDTO.getLeaveEndDate());

        // Save the RequestLeave entity to the database
        requestLeaveRepository.save(requestLeave);
    }





}
