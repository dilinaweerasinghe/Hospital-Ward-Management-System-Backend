package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.EditWardDto;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardMore;
import group17.HospitalWardManagementSystem.Service.WardDetails.EditWardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditWardController {

    @Autowired
    private EditWardService editWardService;

    @PutMapping("/edit-ward/{wardNo}") //wardNo- which ward and editWardDto - data need to change
    public String editWard(@PathVariable String wardNo, @RequestBody EditWardDto editWardDto){
        return editWardService.EditWard(wardNo,editWardDto);
    }

    @PutMapping("/ward/edit/admin/{wardNo}")
    public ResponseEntity<?> updateByAdmin(@PathVariable String wardNo, @RequestBody ShowWardMore value){
        try {
            return ResponseEntity.ok(editWardService.updateWard(wardNo, value));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
