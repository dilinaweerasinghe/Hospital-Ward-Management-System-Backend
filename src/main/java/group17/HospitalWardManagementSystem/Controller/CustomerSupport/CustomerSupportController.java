package group17.HospitalWardManagementSystem.Controller.CustomerSupport;

import group17.HospitalWardManagementSystem.Model.Dto.CustomerSupportDto.CustomerSupportDetailsDto;
import group17.HospitalWardManagementSystem.Service.CustomerSupportService.CustomerSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerSupportController {

    @Autowired
    private CustomerSupportService customerSupportService;

    @PostMapping("/customer-support")
    public String saveCustomerComplain(@RequestBody CustomerSupportDetailsDto customerSupportDetailsDto){
        return customerSupportService.saveCustomerSupport(customerSupportDetailsDto);
    }
}

