package next.school.cesar.desafiospring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;


    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int risk;

    @Column(nullable = false)
    private String analysis;

    private String observation;

//    @Column(nullable = false)
    private Date createdAt;

//    @Column(nullable = false)
    private Date validatedAt;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    @JsonBackReference
    private Client client;

}
