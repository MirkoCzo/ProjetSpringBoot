package be.condorcet.projetsb.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

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
    private Integer idcours;

    @NonNull
    private String matiere;
    @NonNull
    private int heures;
    @JsonIgnore
    // @OneToMany(mappedBy = "cours" , fetch = FetchType.EAGER)
    // @OneToMany(mappedBy = "cours" , fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
    @OneToMany(mappedBy = "cours")
    //LAZY est la version par défaut
    //cascadeType.ALL permet d'effacer en cascade si le cours disparaît
    // orphanRemoval=true permet d'ajouter et supprimer des sessions de cours en DB à partir de la liste
    @ToString.Exclude
    private List<SessionCours> sessionCoursList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return Objects.equals(idcours, cours.idcours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcours);
    }


}
