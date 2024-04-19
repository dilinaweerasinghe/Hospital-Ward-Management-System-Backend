package group17.HospitalWardManagementSystem.Controller.LeaveManagement;

import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaveApprove")
public class LeaveApproveController {

    private final RequestLeaveService requestLeaveService;

    @Autowired
    public LeaveApproveController(RequestLeaveService requestLeaveService) {
        this.requestLeaveService = requestLeaveService;
    }

    @GetMapping("/{nic}")
    public ResponseEntity<?> getRequestLeaveList(@PathVariable String nic, @RequestParam String positionFilter, @RequestParam String wardFilter) {
        try{
            return ResponseEntity.ok(requestLeaveService.getRequestedLeaveByWardAndPosition(nic, positionFilter, wardFilter));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: Please Contact Admin");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }
}
