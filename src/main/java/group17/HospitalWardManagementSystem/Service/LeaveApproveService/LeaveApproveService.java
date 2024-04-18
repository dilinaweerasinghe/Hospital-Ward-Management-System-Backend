package group17.HospitalWardManagementSystem.Service.LeaveApproveService;

import group17.HospitalWardManagementSystem.Repository.ApprovedLeavesRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaveApproveService {
    private final ApprovedLeavesRepository approvedLeavesRepository;

    public LeaveApproveService(ApprovedLeavesRepository approvedLeavesRepository) {
        this.approvedLeavesRepository = approvedLeavesRepository;
    }
}
