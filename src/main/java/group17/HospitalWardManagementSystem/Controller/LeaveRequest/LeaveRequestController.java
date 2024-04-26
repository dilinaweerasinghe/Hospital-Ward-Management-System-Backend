package group17.HospitalWardManagementSystem.Controller.LeaveRequest;

import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.RequestLeaveDto;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_DisplayData;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_SaveData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //Get details that are need to autofill in the leave request form
    @GetMapping("/get-user/{username}")
    public ResponseEntity<?> getUserDetails(@PathVariable String username){
        try{
            return ResponseEntity.ok(requestLeaveServiceDisplayData.provideAutoFilings(username));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

    //save request leave
    @PostMapping("/request-leave")
    public Boolean requestLeave (@RequestBody RequestLeaveDto requestLeaveDto){

        boolean saveSuccessful = requestLeaveServiceSaveData.saveLeave(requestLeaveDto);

        if (saveSuccessful) {
            return true;
        } else {
            return false;

        }
    }



}
