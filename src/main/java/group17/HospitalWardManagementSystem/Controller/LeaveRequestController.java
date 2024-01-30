package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_DisplayData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveRequestController {

    @Autowired
    private RequestLeaveService_DisplayData requestLeaveService;
       public  LeaveRequestController(RequestLeaveService_DisplayData requestLeaveService){
            this.requestLeaveService = requestLeaveService;
        }

    @GetMapping("/user/{username}")
    public MemberDto getUserDetails(@PathVariable String username){
        return requestLeaveService.provideAutoFilings(username);
    }
//    private final RequestLeaveService requestLeaveService;
//    public  LeaveRequestController(RequestLeaveService requestLeaveService){
//        this.requestLeaveService = requestLeaveService;
//    }
//
//    @GetMapping("/user/{username}")
//    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
//        UserDto user = requestLeaveService.getUserDetails(username);
//        return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/request/{username}")
//    public ResponseEntity<String> requestLeave(
//            @RequestBody RequestLeaveDto leaveRequestDTO,
//            @PathVariable String username
//    ) {
//        requestLeaveService.requestLeave(leaveRequestDTO, username);
//        return ResponseEntity.ok("Leave requested successfully");
//    }
}
