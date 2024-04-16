package group17.HospitalWardManagementSystem.Controller.LeaveRequest;

import group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave.ApproveLeaveDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.RequestLeaveDto;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_DisplayData;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_SaveData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveRequestController {

    private final RequestLeaveService_DisplayData requestLeaveServiceDisplayData;
    private final RequestLeaveService requestLeaveService;

    private final RequestLeaveService_SaveData requestLeaveServiceSaveData;

    @Autowired
    public LeaveRequestController(RequestLeaveService_DisplayData requestLeaveServiceDisplayData, RequestLeaveService requestLeaveService, RequestLeaveService_SaveData requestLeaveServiceSaveData) {
        this.requestLeaveServiceDisplayData = requestLeaveServiceDisplayData;
        this.requestLeaveService = requestLeaveService;
        this.requestLeaveServiceSaveData = requestLeaveServiceSaveData;
    }

    @GetMapping("/get-user/{username}")
    public MemberDto getUserDetails(@PathVariable String username){
        System.out.println(requestLeaveServiceDisplayData.provideAutoFilings(username));
        return requestLeaveServiceDisplayData.provideAutoFilings(username);
    }

    @PostMapping("/request-leave")
    public Boolean requestLeave (@RequestBody RequestLeaveDto requestLeaveDto){

        boolean saveSuccessful = requestLeaveServiceSaveData.saveLeave(requestLeaveDto);

        if (saveSuccessful) {
            return true;
        } else {
            return false;

        }
    }

    @PostMapping("/request-leave/getAll")
    public ResponseEntity<?> getRequestedLeaveList(){
        try{
            return ResponseEntity.ok(requestLeaveService.getRequestedLeaveList());
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
