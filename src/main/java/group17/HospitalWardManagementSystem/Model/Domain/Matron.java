package group17.HospitalWardManagementSystem.Model.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Matron {

    @Id
    private String nic;

    @Column(nullable = false)
    private LocalDate serviceStartedDate;

    @OneToMany(mappedBy = "matron")
    private Set<Ward> wards =new HashSet<>();
}
