package group17.HospitalWardManagementSystem.Service.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave.ApproveLeaveDto;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestLeaveService {
    private final RequestLeaveRepository requestLeaveRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final MatronRepository matronRepository;
    private final WardRepository wardRepository;
    private final ApprovedLeavesRepository approvedLeavesRepository;

    @Autowired
    public RequestLeaveService(RequestLeaveRepository requestLeaveRepository, UserRepository userRepository, StaffRepository staffRepository, MatronRepository matronRepository, WardRepository wardRepository, ApprovedLeavesRepository approvedLeavesRepository) {
        this.requestLeaveRepository = requestLeaveRepository;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.matronRepository = matronRepository;
        this.wardRepository = wardRepository;
        this.approvedLeavesRepository = approvedLeavesRepository;
    }

    public List<ApproveLeaveDto> getRequestedLeaveListByMatron(String nic){

            List<RequestLeave> requestLeaves = requestLeaveRepository.findRequestLeaveByMatron(nic);
            if(requestLeaves.isEmpty()){
                throw new IllegalArgumentException("You haven't any leave request to review!");
            }
        return mappingToApprovedLeaveDto(requestLeaves);
    }


    //request leave provided by considering both position and ward
    public List<ApproveLeaveDto> getRequestedLeaveByWardAndPosition(String nic, String positionFilter, String wardFilter){
        User user = userRepository.findByNic(nic).orElseThrow(() ->
                new EntityNotFoundException("Cannot find your details with NIC: " + nic + ". Contact admin to resolve!"));

        List<RequestLeave> requestLeaves = new ArrayList<>();

        if(user.getPosition().equals(UserRole.Sister)){
            Staff staff = staffRepository.findById(nic).orElseThrow(() ->
                    new EntityNotFoundException("Cannot find your details with NIC: " + nic + ". Contact admin to resolve!"));
            requestLeaves = requestLeaveRepository.findRequestLeaveByWard(staff.getWardNo(), nic, LeaveStatus.Not_Concerned_Yet);


        } else if (user.getPosition().equals(UserRole.Matron)) {
            Matron matron = matronRepository.findById(nic).orElseThrow(() ->
                    new EntityNotFoundException("Cannot find your details with NIC: " + nic + ". Contact admin to resolve!"));
            if(positionFilter.equals("All") && wardFilter.equals("All")){
                List<RequestLeave> requestLeaveSister = requestLeaveRepository.findRequestLeaveByPosition(nic, UserRole.Sister, LeaveStatus.Not_Concerned_Yet);
                List<RequestLeave> requestLeaveNurse = requestLeaveRepository.findRequestLeaveByPosition(nic, UserRole.Nurse, LeaveStatus.Pending);
                requestLeaves.addAll(requestLeaveSister);
                requestLeaves.addAll(requestLeaveNurse);


            } else if (positionFilter.equals("All")) {
                Ward ward = wardRepository.findById(wardFilter).orElseThrow(() ->
                        new EntityNotFoundException("Cannot find ward details with ward No: " + wardFilter
                                + ". Contact admin to resolve!"));
                List<RequestLeave> requestLeaveSister = requestLeaveRepository.findRequestLeaveByWardAndPosition(ward.getWardNo(), UserRole.Sister, LeaveStatus.Not_Concerned_Yet);
                List<RequestLeave> requestLeaveNurse = requestLeaveRepository.findRequestLeaveByWardAndPosition(ward.getWardNo(), UserRole.Nurse, LeaveStatus.Pending);

                requestLeaves.addAll(requestLeaveSister);
                requestLeaves.addAll(requestLeaveNurse);


            } else if (wardFilter.equals("All")) {
                if(positionFilter.equals("Sister")){
                    requestLeaves = requestLeaveRepository.findRequestLeaveByPosition(nic, UserRole.Sister, LeaveStatus.Not_Concerned_Yet);

                }
                if(positionFilter.equals("Nurse")){
                    requestLeaves = requestLeaveRepository.findRequestLeaveByPosition(nic, UserRole.Sister, LeaveStatus.Pending);
                }
            }else{
               if(positionFilter.equals("Sister")){
                  requestLeaves = requestLeaveRepository.findRequestLeaveByWardAndPosition(wardFilter, UserRole.Sister, LeaveStatus.Not_Concerned_Yet);
                }
               if(positionFilter.equals("Nurse")){
                  requestLeaves = requestLeaveRepository.findRequestLeaveByWardAndPosition(wardFilter,UserRole.Nurse, LeaveStatus.Pending);
                }
            }
            if(requestLeaves.isEmpty()){
                throw new IllegalArgumentException("You haven't any leave request to review!");
            }


        }else {
            throw new IllegalArgumentException("You haven't access to review Leave Requests!");
        }
        return mappingToApprovedLeaveDto(requestLeaves);
    }



    private List<ApproveLeaveDto> mappingToApprovedLeaveDto(List<RequestLeave> requestLeaves) {
        List<ApproveLeaveDto> requestedLeaveList = new ArrayList<>();
        for(RequestLeave requestLeave : requestLeaves){
            ApproveLeaveDto approveLeaveDto = new ApproveLeaveDto();
            User user = userRepository.findByNic(requestLeave.getStaff().getNic()).orElseThrow(() ->
                    new EntityNotFoundException("Cannot find user details related with NIC: "
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
