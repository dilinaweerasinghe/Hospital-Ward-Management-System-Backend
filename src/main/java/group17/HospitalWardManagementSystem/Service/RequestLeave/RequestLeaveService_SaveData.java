package group17.HospitalWardManagementSystem.Service.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.RequestLeaveDto;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestLeaveService_SaveData {

    private final RequestLeaveRepository requestLeaveRepository;

    private final StaffRepository staffRepository;

    @Autowired
    public RequestLeaveService_SaveData(RequestLeaveRepository requestLeaveRepository, StaffRepository staffRepository) {
        this.requestLeaveRepository = requestLeaveRepository;
        this.staffRepository = staffRepository;
    }

    public boolean saveLeave(RequestLeaveDto leaveRequestDTO) {
        try {
            RequestLeave requestLeave = new RequestLeave();

            requestLeave.setLeaveBeginDate(leaveRequestDTO.getLeaveBeginDate());
            requestLeave.setStaff(findStaff(leaveRequestDTO.getNic()));
            requestLeave.setLeaveEndDate(leaveRequestDTO.getLeaveEndDate());
            requestLeave.setRequestedDateAndTime(LocalDateTime.now());
            requestLeave.setReason(leaveRequestDTO.getReason());

            // Save the requestLeave object
            requestLeaveRepository.save(requestLeave);

            // If the save operation completes without exceptions, return true
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Staff findStaff(String nic){
        return staffRepository.findByNic(nic);
    }
}
