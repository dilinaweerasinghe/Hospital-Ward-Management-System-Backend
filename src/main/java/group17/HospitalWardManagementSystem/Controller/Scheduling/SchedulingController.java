package group17.HospitalWardManagementSystem.Controller.Scheduling;

import group17.HospitalWardManagementSystem.Service.Scheduling.RetrieveSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(retrieveSchedulingService.getCandidateNurseList(nic, shift, date));
    }
}
