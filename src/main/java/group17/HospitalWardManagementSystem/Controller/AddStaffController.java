
package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Model.Dto.AddStaffDto;
import group17.HospitalWardManagementSystem.Service.Login.AddStaffRequesting;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStaffController {

    private AddStaffRequesting addStaffRequesting;

    @RequestMapping("/add-staff")
    public String addStaff(@RequestBody AddStaffDto addStaffDto){
        String firstN;
        firstN = addStaffRequesting.saveAddStaff(addStaffDto);

        return firstN;
    }
}