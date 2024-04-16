package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Service.AdminServices.MatronService;
import group17.HospitalWardManagementSystem.Service.WardDetails.ShowWardService;
import group17.HospitalWardManagementSystem.Service.WardDetails.WardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ShowWardDetailController {

    private final ShowWardService showWardService;
    private final WardService wardService;
    @Autowired
    public ShowWardDetailController(ShowWardService showWardService, MatronService matronService, WardService wardService) {
        this.showWardService = showWardService;
        this.wardService = wardService;
    }

    @GetMapping("/show-ward/{wardName}")
    public ShowWardDto showWardDetails(@PathVariable String wardName){
        return showWardService.showWardDetails(wardName);
    }

    @GetMapping("/show-ward/matron/{nic}")
    public ResponseEntity<?> getWardListByMatron(@PathVariable String nic){
        try {
            List<String> wardNos = wardService.wardListByMatron(nic);

            return ResponseEntity.ok(wardNos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }


}
