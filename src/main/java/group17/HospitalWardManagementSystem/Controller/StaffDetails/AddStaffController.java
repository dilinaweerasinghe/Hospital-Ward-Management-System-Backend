package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.WardNumbersDto;
import group17.HospitalWardManagementSystem.Service.StaffDetails.AddStaffService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddStaffController {

    @Autowired
    public AddStaffService addStaffService;

    @RequestMapping("/add-staff")
    public  ResponseEntity<?> addStaff(@RequestBody  AddStaffDto addStaffDto){

        try {
            String fullN = addStaffService.staffSave(addStaffDto);
            return ResponseEntity.ok(fullN);
    } catch (EntityNotFoundException | IllegalStateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (
    DataAccessException e) {
        return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
    }
    }

    @GetMapping("/get-ward-numbers")
    public WardNumbersDto getWardNumbers(){
        return new WardNumbersDto(addStaffService.findAllWardNumbers());
    }

    @GetMapping("/get-existing-user/{nic}")
    public AddStaffDto getExistingUser(@PathVariable String nic){
        return addStaffService.getExistingUser(nic);
    }

    @GetMapping("/get-all-users-nics")
    public List<String> getAllUserNics(){
        return addStaffService.retrieveAllUserNics();
    }
}
