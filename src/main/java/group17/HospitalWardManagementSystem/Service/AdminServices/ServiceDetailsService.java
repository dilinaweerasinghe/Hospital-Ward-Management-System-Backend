package group17.HospitalWardManagementSystem.Service.AdminServices;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.ServiceDetailsRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.ServiceInterfaces.IServiceDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ServiceDetailsService implements IServiceDetails {

    private final ServiceDetailsRepository serviceDetailsRepository;
    private final UserRepository userRepository;
    private final MatronRepository matronRepository;
    private final StaffRepository staffRepository;


    @Autowired
    public ServiceDetailsService(ServiceDetailsRepository serviceDetailsRepository, UserRepository userRepository, MatronRepository matronRepository, StaffRepository staffRepository) {
        this.serviceDetailsRepository = serviceDetailsRepository;
        this.userRepository = userRepository;
        this.matronRepository = matronRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public void addServiceDetails(String nic) {
        try{
            Matron matron;
            Staff staff;
            ServiceDetails serviceDetails = new ServiceDetails();

            User user = userRepository.findByNic(nic)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with NIC: " + nic));

            serviceDetails.setUser(user);
            serviceDetails.setUserRole(user.getPosition());

            if(user.getPosition().equals(UserRole.Matron)){
                matron = matronRepository.findById(nic).orElseThrow(()->new EntityNotFoundException("Matron not found with NIC: " + nic));
                serviceDetails.setServiceStartedDate(matron.getServiceStartedDate());
                serviceDetails.setWards(matron.getWards());
            }else{
                staff = staffRepository.findById(nic).orElseThrow(()-> new EntityNotFoundException("Sister or Nurse not found with NIC: " + nic));
                serviceDetails.setServiceStartedDate(staff.getServiceStartedDate());
                Set<Ward> wards = new HashSet<>();
                wards.add(staff.getWardNo());
                serviceDetails.setWards(wards);

            }

            serviceDetails.setServiceEndDate(LocalDate.now());

            serviceDetailsRepository.save(serviceDetails);

        }catch (RuntimeException ex) {
            throw new RuntimeException("Data not added to the Service Details!");
        }
    }
}
