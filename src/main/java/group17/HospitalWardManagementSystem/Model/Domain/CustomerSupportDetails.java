package group17.HospitalWardManagementSystem.Model.Domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSupportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_complain_id;
    private String nic;
    private String username;
    private String email;
    private String description;

}
