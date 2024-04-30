package group17.HospitalWardManagementSystem.Controller.Admin;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.GetMatronDto;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.MatronDto;
import group17.HospitalWardManagementSystem.Service.AdminServices.MatronService;
import group17.HospitalWardManagementSystem.ServiceInterfaces.IMatronService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class MatronController {

    private final IMatronService matronService;
    private final  MatronService adminMatronService;
    @Autowired
    public MatronController(MatronService matronService, MatronService adminMatronService){
        this.matronService = matronService;
        this.adminMatronService = adminMatronService;
    }
    @PostMapping("/matron/add")
    public ResponseEntity<String> AddMatron(@RequestBody MatronDto matronDto){
        return matronService.AddMatron(matronDto) ? ResponseEntity.ok("Added Successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed Adding Matron! Try again later.");
    }

    @GetMapping("/matron/get")
    public List<GetMatronDto> getMatronDetails(){
        return matronService.getMatronDetailService();
    }

    @DeleteMapping("/matron/delete/{nic}")
    public ResponseEntity<?> deleteMatron(@PathVariable String nic) {
        try {
            String result = matronService.deleteMatronService(nic);

            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/matron/all")
    public ResponseEntity<?> retrieveMatronNicAndName(){
        try {
            return ResponseEntity.ok(adminMatronService.getAllMatronNicAndName());
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }


}
