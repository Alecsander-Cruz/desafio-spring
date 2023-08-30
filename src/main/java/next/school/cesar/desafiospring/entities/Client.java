package next.school.cesar.desafiospring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int dependents;

    @Column(nullable = false)
    private double income;

    @Column(nullable = false)
    private String marital_status;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;


    @OneToMany
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Vehicle> vehicles;


    @OneToMany
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<House> houses;

}
