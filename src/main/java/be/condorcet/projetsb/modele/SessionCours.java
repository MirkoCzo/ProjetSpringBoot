package be.condorcet.projetsb.modele;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APISESSIONCOURS", schema = "ORA2", catalog = "XE")
public class SessionCours {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessioncours_generator")
    @SequenceGenerator(name = "sessioncours_generator", sequenceName = "APISESSIONCOURS_SEQ1", allocationSize = 1)
    private Integer idsessioncours;

    @NonNull
    private Date Date_Debut;

    @NonNull
    private Date Date_Fin;

    @NonNull
    private Integer nbreinscrits;

    @NonNull
    @ManyToOne @JoinColumn(name = "IDLOCAL")
    private Local local;

    @NonNull
    @ManyToOne @JoinColumn(name = "IDCOURS")
    private Cours cours;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "sessionCours")
    private List<Infos> infos;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCours that = (SessionCours) o;
        return Objects.equals(idsessioncours, that.idsessioncours) && Date_Debut.equals(that.Date_Debut) && Date_Fin.equals(that.Date_Fin) && nbreinscrits.equals(that.nbreinscrits) && local.equals(that.local) && cours.equals(that.cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsessioncours, Date_Debut, Date_Fin, nbreinscrits, local, cours);
    }
}
