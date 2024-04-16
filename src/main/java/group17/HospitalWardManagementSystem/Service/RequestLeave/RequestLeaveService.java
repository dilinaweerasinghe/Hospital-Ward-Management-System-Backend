package group17.HospitalWardManagementSystem.Service.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave.ApproveLeaveDto;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestLeaveService {
    private final RequestLeaveRepository requestLeaveRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public RequestLeaveService(RequestLeaveRepository requestLeaveRepository, UserRepository userRepository, StaffRepository staffRepository) {
        this.requestLeaveRepository = requestLeaveRepository;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }

    public List<ApproveLeaveDto> getRequestedLeaveList(){

            List<RequestLeave> requestLeaves = requestLeaveRepository.findAll();
            List<ApproveLeaveDto> requestedLeaveList = new ArrayList<>();
            for(RequestLeave requestLeave : requestLeaves){
                ApproveLeaveDto approveLeaveDto = new ApproveLeaveDto();
                User user = userRepository.findByNic(requestLeave.getStaff().getNic()).orElseThrow(() ->
                        new EntityNotFoundException("Cannot find user details related to the matron with NIC: "
                                + requestLeave.getStaff().getNic()));
                approveLeaveDto = ApproveLeaveDto.builder().leaveId(requestLeave.getLeaveId())
                        .leaveNo(requestLeave.getStaff().getLeaveNum()).leaveBeginDate(requestLeave.getLeaveBeginDate())
                        .leaveEndDate(requestLeave.getLeaveEndDate()).requestedDate(LocalDate.from(requestLeave.getRequestedDateAndTime()))
                        .name(user.getFirstName() + " " + user.getLastName()).build();

                requestedLeaveList.add(approveLeaveDto);
            }

            return requestedLeaveList;
    }

}
