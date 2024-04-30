package group17.HospitalWardManagementSystem.Service.AdminServices;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.GetMatronDto;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.MatronDto;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.SelectMatronDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Service.GeneralServices.PasswordGenerateService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.UsernameGenerateService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.MailService;
import group17.HospitalWardManagementSystem.ServiceInterfaces.IMatronService;
import group17.HospitalWardManagementSystem.ServiceInterfaces.IServiceDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MatronService implements IMatronService {
    private final MatronRepository matronRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final UsernameGenerateService usernameGenerateService;
    private final PasswordGenerateService passwordGenerateService;
    private final ServiceDetailsService serviceDetailsService;

    private final IServiceDetails serviceDetails;

    @Autowired
    public MatronService(MatronRepository matronRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService, UsernameGenerateService usernameGenerateService, PasswordGenerateService passwordGenerateService, ServiceDetailsService serviceDetailsService, IServiceDetails serviceDetails) {
        this.matronRepository = matronRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.usernameGenerateService = usernameGenerateService;
        this.passwordGenerateService = passwordGenerateService;
        this.serviceDetailsService = serviceDetailsService;
        this.serviceDetails = serviceDetails;
    }

    @Override
    public Boolean AddMatron(MatronDto matronDto){

        Matron matron = new Matron();
        try{

            Optional<User> existingUser = userRepository.findByNic(matronDto.getNic());

            if (existingUser.isEmpty()){
                User user = new User();

                //Add matron as a user
                user.setNic(matronDto.getNic());
                user.setFullName(matronDto.getFullName());
                user.setFirstName(matronDto.getFirstName());
                user.setLastName(matronDto.getLastName());
                user.setPosition(UserRole.Matron);
                user.setDob(matronDto.getDob());
                user.setEmail(matronDto.getEmail());
                user.setMobileNo(matronDto.getMobileNo());
                user.setUsername(usernameGenerateService.generateUsername(matronDto.getEmail()));
                String password = passwordGenerateService.passwordGenerate();
                user.setPassword(passwordEncoder.encode(password));
                // Need rto delete
                System.out.println(password);

                userRepository.save(user);
            }

            matron.setNic(matronDto.getNic());
            matron.setServiceStartedDate(matronDto.getServiceDate());
            matronRepository.save(matron);
            return true;

        }catch (Exception e){
            return false;
        }
    }
    @Override
    public List<GetMatronDto> getMatronDetailService() {
        List<GetMatronDto> matronDetails = new ArrayList<>();
        try {
            List<Matron> matrons = matronRepository.findAll();
            for (Matron matron : matrons) {
                Optional<User> user = userRepository.findByNic(matron.getNic());
                if (user.isPresent()) {
                    GetMatronDto matronDto = getMatronDto(user.get());

                    matronDetails.add(matronDto);
                } else {
                    throw new RuntimeException("User not found for matron with NIC: " + matron.getNic());
                }
            }
            return matronDetails;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while fetching matron details", ex);
        }
    }

    @Override
    @Transactional
    public String deleteMatronService(String nic) {

        Matron matron = matronRepository.findById(nic)
                .orElseThrow(() -> new EntityNotFoundException("Matron not found with NIC: " + nic));

        if(matron.getWards().isEmpty()){
            matronRepository.delete(matron);

            User user = userRepository.findByNic(nic)
                    .orElseThrow(() ->
                            new EntityNotFoundException("Cannot find user details related to the matron with NIC: " + nic));

            userRepository.updatePositionByNic(nic, UserRole.Currently_None);
            // Return the name of the deleted matron/user as confirmation
            return user.getFirstName() + " " + user.getLastName();
        }else{
            throw new IllegalStateException("Cannot Delete Still Works in some wards. Update them before Delete!");
        }




    }

    public List<SelectMatronDto> getAllMatronNicAndName(){
        List<Matron> matrons = matronRepository.findAll();
        List<SelectMatronDto> selectMatronDtos = new ArrayList<>();
        for(Matron matron : matrons){
            Optional<User> user = userRepository.findById(matron.getNic());
            selectMatronDtos.add(SelectMatronDto.builder().name(user.isPresent() ? user.get().getFullName(): "Not Found").nic(matron.getNic()).build());
        }

        return selectMatronDtos;
    }



    private static GetMatronDto getMatronDto(User user) {
        GetMatronDto matronDto = new GetMatronDto();
        matronDto.setNic(user.getNic());
        matronDto.setFirstName(user.getFirstName());
        matronDto.setLastName(user.getLastName());
        matronDto.setEmail(user.getEmail());
        matronDto.setMobileNo(user.getMobileNo());
        return matronDto;
    }



}
