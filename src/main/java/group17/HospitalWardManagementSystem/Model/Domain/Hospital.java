package group17.HospitalWardManagementSystem.Model.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "no_of_wards",nullable = false)
    private int noOfWards;
    @Column(name = "no_of_matrons" ,nullable = false)
    private int noOfMatrons;

}
