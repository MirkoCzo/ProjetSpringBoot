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
@Table(name = "APILOCAL", schema = "ORA2", catalog = "XE")
public class Local {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_generator")
    @SequenceGenerator(name="local_generator", sequenceName = "APILOCAL_SEQ", allocationSize = 1)
    private Integer idlocal;

    @NonNull
    private String sigle;

    @NonNull
    private Integer places;

    @NonNull
    private String description;

    @JsonIgnore
    // @OneToMany(mappedBy = "local" , fetch = FetchType.EAGER)
    // @OneToMany(mappedBy = "local" , fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
    @OneToMany(mappedBy = "local")
    //LAZY est la version par défaut
    //cascadeType.ALL permet d'effacer en cascade si le local disparaît
    // orphanRemoval=true permet d'ajouter et supprimer des sessions de cours en DB à partir de la liste
    @ToString.Exclude
    private List<SessionCours> sessionCoursList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return Objects.equals(idlocal, local.idlocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlocal);
    }
}
