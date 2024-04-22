package group17.HospitalWardManagementSystem.Service.LeaveApproveService;

import group17.HospitalWardManagementSystem.Model.Domain.ApprovedLeaves;
import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Repository.ApprovedLeavesRepository;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class LeaveApproveService {
    private final ApprovedLeavesRepository approvedLeavesRepository;

    private final RequestLeaveRepository requestLeaveRepository;
    @Autowired
    public LeaveApproveService(ApprovedLeavesRepository approvedLeavesRepository, RequestLeaveRepository requestLeaveRepository) {
        this.approvedLeavesRepository = approvedLeavesRepository;
        this.requestLeaveRepository = requestLeaveRepository;
    }

    // Approve Leave Request by Sister
    public void approveLeaveRequestBySister(int leaveID){
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveID).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));
        requestLeaveRepository.updateLeaveStatus(leaveID, LeaveStatus.Pending);
    }

    @Transactional
    public void approveLeaveRequestByMatron(int leaveID) throws SQLException {
        RequestLeave requestLeave = requestLeaveRepository.findById(leaveID).orElseThrow(()->
                new EntityNotFoundException("Cannot found request leave details. Please contact admin!"));

        ApprovedLeaves approvedLeaves = ApprovedLeaves.builder().leaveId(leaveID).leaveBeginDate(requestLeave.getLeaveBeginDate())
                .leaveEndDate(requestLeave.getLeaveEndDate()).requestedDateAndTime(requestLeave.getRequestedDateAndTime())
                .staff(requestLeave.getStaff()).reason(requestLeave.getReason()).status(LeaveStatus.Approved).build();

            approvedLeavesRepository.save(approvedLeaves);

            requestLeaveRepository.delete(requestLeave);

    }
}
