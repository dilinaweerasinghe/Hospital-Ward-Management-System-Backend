package group17.HospitalWardManagementSystem.Controller.LeaveManagement;

import group17.HospitalWardManagementSystem.Model.Domain.RequestLeave;
import group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave.ApproveLeaveDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.RequestLeaveRepository;
import group17.HospitalWardManagementSystem.Service.LeaveApproveService.LeaveApproveService;
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
    private final LeaveApproveService leaveApproveService;

    @Autowired
    public LeaveApproveController(RequestLeaveService requestLeaveService, RequestLeaveRepository requestLeaveRepository, LeaveApproveService leaveApproveService) {
        this.requestLeaveService = requestLeaveService;
        this.leaveApproveService = leaveApproveService;
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

    @PutMapping("/sister")
    public ResponseEntity<?> approveLeaveBySister(@RequestBody int leaveId){
        try{
            leaveApproveService.approveLeaveRequestBySister(leaveId);
            return ResponseEntity.ok("Leave is Approved!");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {

            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

    @PutMapping("/matron")
    public ResponseEntity<?> approveLeaveByMatron(@RequestBody int leaveId){
        try{
            leaveApproveService.approveLeaveRequestByMatron(leaveId);
            return ResponseEntity.ok("Leave is Approved!");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {

            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

    @PutMapping("/decline")
    public ResponseEntity<?> declineLeaveRequest(@RequestBody int leaveId){
        try{
            leaveApproveService.declineLeaveRequest(leaveId);
            return ResponseEntity.ok("Leave Request is Decline!");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {

            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

    @GetMapping("/more/{leaveId}")
    public ResponseEntity<?> getLeaveApproveMoreDetails(@PathVariable int leaveId){
        try{
            return ResponseEntity.ok(leaveApproveService.getLeaveApproveMoreDetails(leaveId));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {

            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

    @GetMapping("/more/previousRecode/{leaveId}")
    public ResponseEntity<?> getPreviousLeaveRecord(@PathVariable int leaveId){
        try{
            return ResponseEntity.ok(leaveApproveService.getPreviousLeaveDetails(leaveId));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

}
