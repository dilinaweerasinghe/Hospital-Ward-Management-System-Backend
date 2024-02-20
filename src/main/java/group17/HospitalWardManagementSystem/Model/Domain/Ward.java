package group17.HospitalWardManagementSystem.Model.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Ward {

    @Id
    private String wardNo;

    @Column(nullable = false)
    private String wardName;

    @ManyToOne
    @JoinColumn(name="matron",referencedColumnName = "nic")

    //@Column(nullable = false)
    private Matron matron;

    @OneToMany(mappedBy = "wardNo")
    private Set<Staff> staff = new HashSet<>();

    @Column(nullable = false)
    private int numberOfNurses;
    @Column(nullable = false)
    private int morningShift;

    @Column(nullable = false)
    private int eveningShift;

    @Column(nullable = false)
    private int nightShift;



}
