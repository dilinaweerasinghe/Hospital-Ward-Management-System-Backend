package group17.HospitalWardManagementSystem.Model.Domain;

import group17.HospitalWardManagementSystem.Model.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
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
    @ManyToOne
    @JoinColumn(name = "wardNo", referencedColumnName = "wardNo")
    private Ward ward;



}
