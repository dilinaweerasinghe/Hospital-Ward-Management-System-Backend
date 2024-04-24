package group17.HospitalWardManagementSystem.Controller.Scheduling;

import group17.HospitalWardManagementSystem.Model.Dto.Scheduling.AssigningDto;
import group17.HospitalWardManagementSystem.Service.Scheduling.RetrieveSchedulingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final RetrieveSchedulingService retrieveSchedulingService;

    @Autowired
    public SchedulingController(RetrieveSchedulingService retrieveSchedulingService) {
        this.retrieveSchedulingService = retrieveSchedulingService;
    }

    @GetMapping("/candidates/{nic}")
    public ResponseEntity<?> getCandidateNurseList(@PathVariable String nic, @RequestParam String shift, @RequestParam String date){
       try{
           return ResponseEntity.ok(retrieveSchedulingService.getCandidateNurseList(nic, shift, date));
       }catch (EntityNotFoundException | IllegalArgumentException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       } catch (DataAccessException e) {
           return ResponseEntity.internalServerError().body( e.getStackTrace());
       } catch (Exception e) {
           return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
       }

    }

    @PostMapping("/add/{sisterNic}")
    public ResponseEntity<?> assignedDuty(@PathVariable String sisterNic, @RequestBody String dutyAssign){
        return null;
    }
}
