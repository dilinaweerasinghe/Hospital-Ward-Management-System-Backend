package group17.HospitalWardManagementSystem.Model.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Matron {

    @Id
    @OneToOne
    @JoinColumn(name = "nic", referencedColumnName = "nic")
    private User user;

    @OneToMany(mappedBy = "matron")
    private Set<Ward> wards =new HashSet<>();
}
