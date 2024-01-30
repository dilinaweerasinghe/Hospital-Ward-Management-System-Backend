package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.LeaveRequestDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_DisplayData;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_SaveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class LeaveRequestController {

    @Autowired
    private RequestLeaveService_DisplayData requestLeaveService;

    @Autowired
    private RequestLeaveService_SaveData requestLeaveServiceSaveData;

    public LeaveRequestController(RequestLeaveService_DisplayData requestLeaveService) {
        this.requestLeaveService = requestLeaveService;
    }

    @GetMapping("/user/{username}")
    public MemberDto getUserDetails(@PathVariable String username) {
        return requestLeaveService.provideAutoFilings(username);
    }

    @PostMapping("/request-leave")
    public ResponseEntity<String> saveLeaveData(@RequestBody LeaveRequestDto leaveRequestDto) {
        // Extract data from the DTO and pass it to the service
        Staff staff = leaveRequestDto.getStaff();
        LocalDate leaveBeginDate = leaveRequestDto.getLeaveBeginDate();
        LocalDate leaveEndDate = leaveRequestDto.getLeaveEndDate();
        LocalDateTime requestedDateAndTime = leaveRequestDto.getRequestedDateAndTime();
        String reason = leaveRequestDto.getReason();

        // Call the service method to save leave data
        requestLeaveServiceSaveData.saveLeaveData(staff, leaveBeginDate, leaveEndDate, requestedDateAndTime, reason);
        return ResponseEntity.ok("Leave data saved successfully");
    }
}