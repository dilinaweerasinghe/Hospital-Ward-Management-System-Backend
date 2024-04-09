package group17.HospitalWardManagementSystem.Model.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasualityDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "wardNo", referencedColumnName = "wardNo")
    private Ward wardNo;
    @Column(nullable = false)
    private boolean monday;
    @Column(nullable = false)
    private boolean tuesday;
    @Column(nullable = false)
    private boolean wednesday;
    @Column(nullable = false)
    private boolean thursday;
    @Column(nullable = false)
    private boolean friday;
    @Column(nullable = false)
    private boolean saturday;
}
