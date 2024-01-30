package group17.HospitalWardManagementSystem.Model.Domain;

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
public class  Staff {
    @Id
    private String nic;

    @Column(nullable = false)
    private LocalDate serviceStartedDate;

    @Column(nullable = false)
    private String leaveNum;

    @OneToMany(mappedBy = "staff")
    private Set<RequestLeave> leaveRequests = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "wardNo", referencedColumnName = "wardNo")
    //@Column(nullable = false)
    private Ward wardNo;
    @Column(nullable = false)
    private int remainingCasualLeaves;
    @Column(nullable = false)
    private int remainingVacationLeave;
}
