package be.condorcet.projetsb.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name="APIFORMATEUR", schema = "ORA2", catalog = "XE")
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formateur_generator")
    @SequenceGenerator(name = "formateur_generator",sequenceName = "APIFORMATEUR_SEQ1", allocationSize = 1)
    private Integer idformateur;

    @NonNull
    private String mail;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "formateur")
    private List<Infos> infos;


}
