package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.EditWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.EditWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditWardController {

    @Autowired
    private EditWardService editWardService;

    @PutMapping("/edit-ward/{wardNo}")
    public String editWard(@PathVariable String wardNo, @RequestBody EditWardDto editWardDto){
        return editWardService.EditWard(wardNo,editWardDto);
    }
}
