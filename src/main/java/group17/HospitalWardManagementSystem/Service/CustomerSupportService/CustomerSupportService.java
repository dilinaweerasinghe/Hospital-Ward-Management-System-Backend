package group17.HospitalWardManagementSystem.Service.CustomerSupportService;

import group17.HospitalWardManagementSystem.Model.Domain.CustomerSupportDetails;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.CustomerSupportDto.CustomerSupportDetailsDto;
import group17.HospitalWardManagementSystem.Repository.CustomerSupportRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerSupportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerSupportRepository customerSupportRepository;

    public String saveCustomerSupport(CustomerSupportDetailsDto customerSupportDetailsDto){

        if(find(customerSupportDetailsDto.getNic()).isPresent()){
            CustomerSupportDetails customerSupportDetails=new CustomerSupportDetails();

            customerSupportDetails.setNic(customerSupportDetailsDto.getNic());
            customerSupportDetails.setUsername(customerSupportDetailsDto.getUsername());
            customerSupportDetails.setEmail(customerSupportDetailsDto.getEmail());
            customerSupportDetails.setDescription(customerSupportDetailsDto.getDescription());

            customerSupportRepository.save(customerSupportDetails);
            return "Successfully Send your message";
        }else{
            return "User is not registered in the System";
        }

    }

    public Optional<User> find(String nic){
        return userRepository.findByNic(nic);
    }

}
