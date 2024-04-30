package group17.HospitalWardManagementSystem.Controller.Scheduling;

import group17.HospitalWardManagementSystem.Model.Dto.Scheduling.AssigningDto;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import group17.HospitalWardManagementSystem.Service.Scheduling.CreateSchedulingService;
import group17.HospitalWardManagementSystem.Service.Scheduling.RetrieveSchedulingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final RetrieveSchedulingService retrieveSchedulingService;
    private final CreateSchedulingService createSchedulingService;

    @Autowired
    public SchedulingController(RetrieveSchedulingService retrieveSchedulingService, CreateSchedulingService createSchedulingService) {
        this.retrieveSchedulingService = retrieveSchedulingService;
        this.createSchedulingService = createSchedulingService;
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


    @PutMapping("/add/{sisterNic}")
    public ResponseEntity<?> assignedDuty(@PathVariable String sisterNic, @RequestBody AssigningDto dutyAssign) {
        try {
            createSchedulingService.addNursesToTheDuties(sisterNic, dutyAssign.getNurseNic(), dutyAssign.getDate(), dutyAssign.getDutyTime());
            return ResponseEntity.ok("Duty Assigning is success!");
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {

            return ResponseEntity.ok("Fail");
        }

    }

    @GetMapping("/view/{nicOrWard}")
    public ResponseEntity<?> getDutyDetails(@PathVariable String nicOrWard, @RequestParam String date){
        try{
            return ResponseEntity.ok(retrieveSchedulingService.getDutyDetailsForDate(nicOrWard, date));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> TestController(){
//        return ResponseEntity.ok(retrieveSchedulingService.getExistenceOfPreviousDutyTest("198805080808", LocalDate.parse("2024-01-25"), DutyTime.Morning));
//    }

    @GetMapping("/view/matron{ward}")
    public ResponseEntity<?> getDutyDetailsForMatron(@PathVariable String ward, @RequestParam String date){
        try{
            return ResponseEntity.ok(retrieveSchedulingService.getDutyDetailsForDateForMatron(ward, date));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }


}
