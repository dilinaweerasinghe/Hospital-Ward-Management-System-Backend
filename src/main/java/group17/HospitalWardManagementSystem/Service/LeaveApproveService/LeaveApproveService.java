package group17.HospitalWardManagementSystem.Service.LeaveApproveService;

import group17.HospitalWardManagementSystem.Model.Domain.ApprovedLeaves;
import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave.ApproveLeaveMore;
import group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave.PreviousLeaveDetailsDto;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Repository.ApprovedLeavesRepository;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeaveApproveService {
    private final ApprovedLeavesRepository approvedLeavesRepository;
    private final RequestLeaveRepository requestLeaveRepository;
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;

    @Autowired
    public LeaveApproveService(ApprovedLeavesRepository approvedLeavesRepository, RequestLeaveRepository requestLeaveRepository, StaffRepository staffRepository, UserRepository userRepository) {
        this.approvedLeavesRepository = approvedLeavesRepository;
        this.requestLeaveRepository = requestLeaveRepository;
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
    }

    // Approve Leave Request by Sister
    public void approveLeaveRequestBySister(int leaveID){
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveID).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));
        requestLeaveRepository.updateLeaveStatus(leaveID, LeaveStatus.Pending);
    }

    @Transactional
    public void approveLeaveRequestByMatron(int leaveID){
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveID).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));

        ApprovedLeaves approvedLeaves = ApprovedLeaves.builder().leaveId(leaveID).leaveBeginDate(requestLeave.getLeaveBeginDate())
                .leaveEndDate(requestLeave.getLeaveEndDate()).requestedDateAndTime(requestLeave.getRequestedDateAndTime())
                .staff(requestLeave.getStaff()).reason(requestLeave.getReason()).status(LeaveStatus.Approved).build();

        Staff staff = requestLeave.getStaff();

            approvedLeavesRepository.save(approvedLeaves);
            requestLeaveRepository.delete(requestLeave);

            if(staff.getRemainingCasualLeaves()>0){
                staffRepository.updateRemainingCasualLeaves(staff.getNic(), staff.getRemainingCasualLeaves() - 1);
            } else if (staff.getRemainingVacationLeave()>0) {
                staffRepository.updateRemainingVacationLeave(staff.getNic(), staff.getRemainingVacationLeave() - 1);
            }else {
                staffRepository.updateRemainingCasualLeaves(staff.getNic(), staff.getRemainingCasualLeaves() - 1);
            }



    }


    @Transactional
    public void declineLeaveRequest(int leaveId){
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveId).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));

        ApprovedLeaves approvedLeaves = ApprovedLeaves.builder().leaveId(leaveId).leaveBeginDate(requestLeave.getLeaveBeginDate())
                .leaveEndDate(requestLeave.getLeaveEndDate()).requestedDateAndTime(requestLeave.getRequestedDateAndTime())
                .staff(requestLeave.getStaff()).reason(requestLeave.getReason()).status(LeaveStatus.Decline).build();

        approvedLeavesRepository.save(approvedLeaves);

        requestLeaveRepository.delete(requestLeave);

    }

    public ApproveLeaveMore getLeaveApproveMoreDetails(int leaveId){
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveId).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));

        Staff staff = requestLeave.getStaff();
        List<RequestLeave> requestLeavesInCurrentMonth = filterRequestLeaveByCurrentMonth(staff.getLeaveRequests());
        List<ApprovedLeaves> approvedLeavesInCurrentMonth = filterApproveLeaveByCurrentMonth(staff.getApprovedLeaves());

        User user = userRepository.findById(staff.getNic()).orElseThrow(()-> new EntityNotFoundException("Cannot find user details of Nic: " + staff.getNic()));

        return ApproveLeaveMore.builder().leaveId(leaveId).leaveNo(staff.getLeaveNum())
                .name(user.getFullName()).requestedDate(LocalDate.from(requestLeave.getRequestedDateAndTime()))
                .leaveBeginDate(requestLeave.getLeaveBeginDate()).leaveEndDate(requestLeave.getLeaveEndDate())
                .reason(requestLeave.getReason()).remainingVacationLeave(staff.getRemainingVacationLeave())
                .remainingCasualLeaves(staff.getRemainingCasualLeaves()).totalLeaveOfTheMonth(requestLeavesInCurrentMonth.size() + approvedLeavesInCurrentMonth.size())
                .build();
    }

    public List<PreviousLeaveDetailsDto> getPreviousLeaveDetails(int leaveId){
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveId).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));

        Staff staff = requestLeave.getStaff();

        Set <ApprovedLeaves> approvedLeaves = staff.getApprovedLeaves();

        return mapApprovedLeavesSetToDtoList(approvedLeaves);
    }




    /////////////////////////////////////////////////////////////////////////////////////////////////

    public  List<RequestLeave> filterRequestLeaveByCurrentMonth(Set<RequestLeave> leaveRequests) {
        Month currentMonth = LocalDate.now().getMonth(); // Get the current month

        return leaveRequests.stream()
                .filter(leave -> leave.getRequestedDateAndTime().getMonth() == currentMonth) // Filter by current month
                .collect(Collectors.toList());
    }

    public  List<ApprovedLeaves> filterApproveLeaveByCurrentMonth(Set<ApprovedLeaves> approvedLeaves) {
        Month currentMonth = LocalDate.now().getMonth(); // Get the current month

        return approvedLeaves.stream()
                .filter(leave -> leave.getRequestedDateAndTime().getMonth() == currentMonth) // Filter by current month
                .collect(Collectors.toList());
    }

    // Method to map a single ApprovedLeaves object to a PreviousLeaveDetailsDto
    public PreviousLeaveDetailsDto mapApprovedLeavesToDto(ApprovedLeaves approvedLeave) {
        if (approvedLeave == null) {
            throw new IllegalArgumentException("ApprovedLeaves object cannot be null");
        }

        Period period = Period.between( approvedLeave.getLeaveBeginDate(), approvedLeave.getLeaveEndDate());


        return PreviousLeaveDetailsDto.builder()
                .leaveId(approvedLeave.getLeaveId())
                .numberOfLeaveDays(period.getDays())
                .leaveBeginDate(approvedLeave.getLeaveBeginDate())
                .Reason(approvedLeave.getReason())
                .leaveStatus(approvedLeave.getStatus())
                .build();
    }

    // Method to map a list of ApprovedLeaves objects to a list of PreviousLeaveDetailsDto
    public List<PreviousLeaveDetailsDto> mapApprovedLeavesSetToDtoList(Set<ApprovedLeaves> approvedLeavesSet) {
        if (approvedLeavesSet == null || approvedLeavesSet.isEmpty()) {
            throw new IllegalArgumentException("This staff member has not previously applied leaves");
        }

        List<PreviousLeaveDetailsDto> dtoList = new ArrayList<>();

        for (ApprovedLeaves approvedLeave : approvedLeavesSet) {
            PreviousLeaveDetailsDto dto = mapApprovedLeavesToDto(approvedLeave);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
