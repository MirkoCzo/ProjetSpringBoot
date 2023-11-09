package be.condorcet.projetsb.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private Integer id_local;

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
}
