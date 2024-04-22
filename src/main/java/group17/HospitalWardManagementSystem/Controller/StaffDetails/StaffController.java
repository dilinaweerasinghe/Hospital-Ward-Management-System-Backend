package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Service.StaffDetails.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @DeleteMapping("/delete-nurse/{nic}")
    public ResponseEntity<?> deleteStaffMember(@PathVariable String nic){
        try{
            staffService.deleteStaffMember(nic);
            return ResponseEntity.ok("Delete Successfully!");
        }catch (DataAccessException e) {

            return ResponseEntity.internalServerError().body( e.getStackTrace());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: Please contact admin" );
        }
    }
}
