package group17.HospitalWardManagementSystem.Service.RequestLeave;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RequestLeaveService_SaveData {

    @Autowired
    private RequestLeaveRepository requestLeaveRepository;

    public void saveLeaveData(Staff staff, LocalDate leaveBeginDate, LocalDate leaveEndDate,LocalDateTime requestedDateAndTime,String reason){
        RequestLeave requestLeave = new RequestLeave();

        requestLeave.setStaff(staff);
        requestLeave.setLeaveBeginDate(leaveBeginDate);
        requestLeave.setLeaveEndDate(leaveEndDate);
        requestLeave.setRequestedDateAndTime(requestedDateAndTime);
        requestLeave.setReason(reason);

        requestLeaveRepository.save(requestLeave);
    }
}
