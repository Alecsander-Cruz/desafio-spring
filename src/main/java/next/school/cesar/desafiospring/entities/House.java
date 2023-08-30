package next.school.cesar.desafiospring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;


    @Column(nullable = false)
    private String ownership_status;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String zipcode;

    @ManyToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
    private Client client;

}
