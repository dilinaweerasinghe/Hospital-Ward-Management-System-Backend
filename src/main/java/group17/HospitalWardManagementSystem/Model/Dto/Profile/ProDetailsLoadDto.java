package group17.HospitalWardManagementSystem.Model.Dto.Profile;

import group17.HospitalWardManagementSystem.Model.Domain.ServiceDetails;
import group17.HospitalWardManagementSystem.Model.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProDetailsLoadDto {


    private String nic;

    private String fullName;

    private String username;

    private LocalDate dob;

    private String email;

    private String mobileNo;

    private String Position;

    private LocalDate serviceStartedDate;

    private int remainingCasualLeaves;

    private int remainingVacationLeave;

}
