package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Service.StaffDetails.ShowAllStaffWardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowAllStaffDetailsController {
    @Autowired
    public ShowAllStaffWardService showStaffService;

    @RequestMapping("/show-staff/{wardNo}")
    public ResponseEntity<?> showStaffData(@PathVariable String wardNo){
        try{
            return ResponseEntity.ok(showStaffService.showStaff(wardNo));
        }catch (EntityNotFoundException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: Please Contact Admin");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }



}
