package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.UpdateAllWardDetailsDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.UpdateAllWardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdteAllWardDetailsController {

    @Autowired
    private UpdateAllWardDetailsService updateWardBySisterService;

    @PutMapping("/ward-sister-update")
    private String updateWardSister(@RequestBody UpdateAllWardDetailsDto updateWardBySisterDto){

        return updateWardBySisterService.UpdateAllWardDetails(updateWardBySisterDto);
    }
}
