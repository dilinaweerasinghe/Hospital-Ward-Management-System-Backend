package group17.HospitalWardManagementSystem.Model.Domain;

import group17.HospitalWardManagementSystem.Model.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "nic" ,referencedColumnName="nic")
    private User user;
    @Column(nullable = false)
    private LocalDate serviceStartedDate;
    @Column(nullable = false)
    private LocalDate serviceEndDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @ManyToMany
    @JoinTable(name = "serviceDetails_wards",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "wardNo"))
    private Set<Ward> wards = new HashSet<>();


}
