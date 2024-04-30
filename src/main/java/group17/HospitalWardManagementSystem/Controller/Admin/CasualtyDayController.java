package group17.HospitalWardManagementSystem.Controller.Admin;

import group17.HospitalWardManagementSystem.Service.AdminServices.CasualtyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CasualtyDayController {
    private final CasualtyService casualtyService;

    @Autowired
    public CasualtyDayController(CasualtyService casualtyService) {
        this.casualtyService = casualtyService;
    }

    @GetMapping("/casualtyDay/{wardNo}")
    public ResponseEntity<?> getCasualtyDay(@PathVariable String wardNo){
        try {
            return ResponseEntity.ok(casualtyService.getCasualityDayByWard(wardNo));
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
