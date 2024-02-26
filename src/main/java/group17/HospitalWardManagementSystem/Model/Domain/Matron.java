package group17.HospitalWardManagementSystem.Model.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
