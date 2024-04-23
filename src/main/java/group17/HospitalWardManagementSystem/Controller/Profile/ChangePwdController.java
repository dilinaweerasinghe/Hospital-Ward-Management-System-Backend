package group17.HospitalWardManagementSystem.Controller.Profile;

import group17.HospitalWardManagementSystem.Model.Dto.PassWord.ChangePwdDto;
import group17.HospitalWardManagementSystem.Service.Profile.ChangePwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change")
public class ChangePwdController {

    @Autowired
    private ChangePwdService changePwdService;

    @PutMapping("/password/{nic}")
    public ResponseEntity<String> changePassword(@RequestBody ChangePwdDto changePwdDto, @PathVariable String nic){
        try {
            return ResponseEntity.ok(changePwdService.changePassword(changePwdDto,nic));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
