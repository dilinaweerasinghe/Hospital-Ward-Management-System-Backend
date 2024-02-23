package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.UpdateWardBySisterDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.UpdateWardBySisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdteWardBySisterController {

    @Autowired
    private UpdateWardBySisterService updateWardBySisterService;

    @PutMapping("/ward-sister-update")
    private String updateWardSister(@RequestBody UpdateWardBySisterDto updateWardBySisterDto){

        return updateWardBySisterService.UpdateWardBySister(updateWardBySisterDto);
    }
}
