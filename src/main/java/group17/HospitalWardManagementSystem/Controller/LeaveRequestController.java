package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.RequestLeaveDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.UserDto;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveRequestController {
    private final RequestLeaveService requestLeaveService;
    public  LeaveRequestController(RequestLeaveService requestLeaveService){
        this.requestLeaveService = requestLeaveService;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        UserDto user = requestLeaveService.getUserDetails(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/request/{username}")
    public ResponseEntity<String> requestLeave(
            @RequestBody RequestLeaveDto leaveRequestDTO,
            @PathVariable String username
    ) {
        requestLeaveService.requestLeave(leaveRequestDTO, username);
        return ResponseEntity.ok("Leave requested successfully");
    }
}
