package be.condorcet.projetsb.modele;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name="APICOURS", schema = "ORA2", catalog = "XE")
public class Cours {


    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cours_generator")
    @SequenceGenerator(name = "cours_generator",sequenceName = "APICOURS_SEQ", allocationSize = 1)
    private Integer id_cours;

    @NonNull
    private String matiere;
    @NonNull
    private int heures;

}
