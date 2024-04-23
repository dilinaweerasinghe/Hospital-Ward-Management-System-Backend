package group17.HospitalWardManagementSystem.Controller.Scheduling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @GetMapping("/candidates/{wardNo}")
    public ResponseEntity<?> getCandidateNurseList(@PathVariable String wardNo, @RequestParam String shift, @RequestParam String date){
        return null;
    }
}
