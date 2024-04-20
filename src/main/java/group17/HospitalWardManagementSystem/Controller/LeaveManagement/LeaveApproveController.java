package group17.HospitalWardManagementSystem.Controller.LeaveManagement;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/leaveApprove")
public class LeaveApproveController {

    private final RequestLeaveService requestLeaveService;

    @Autowired
    public LeaveApproveController(RequestLeaveService requestLeaveService, RequestLeaveRepository requestLeaveRepository) {
        this.requestLeaveService = requestLeaveService;
    }

    @GetMapping("/{nic}")
    public ResponseEntity<?> getRequestLeaveList(@PathVariable String nic, @RequestParam String positionFilter, @RequestParam String wardFilter) {
        try{
            return ResponseEntity.ok(requestLeaveService.getRequestedLeaveByWardAndPosition(nic, positionFilter, wardFilter));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {

            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

    //test controller

}
