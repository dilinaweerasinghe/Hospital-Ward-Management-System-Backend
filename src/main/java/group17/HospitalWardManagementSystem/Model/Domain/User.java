package group17.HospitalWardManagementSystem.Model.Domain;

import group17.HospitalWardManagementSystem.Model.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String nic;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Size(min = 5, max = 30)
    private String username;
    @Column(nullable = false)
    @Size(min = 8, max = 20)
    private String password;
    @Column(nullable = false)
    private LocalDate dob;
    //    //@Column(nullable = false)
//    private LocalDate serviceStartedDate;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole Position;

    //private String leaveNum;
    @Column(nullable = false)
    private String mobileNo;

    @OneToMany(mappedBy = "user")
    private List<ServiceDetails> serviceDetails;

    private LocalDate careerStatedDate;


}
