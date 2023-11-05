package be.condorcet.projetsb.modele;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

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
    private Integer id_sessioncours;

    @NonNull
    private Date Date_Debut;

    @NonNull
    private Date Date_Fin;

    @NonNull
    private Integer nbreinscrits;

    @NonNull
    @ManyToOne @JoinColumn(name = "ID_LOCAL")
    private Local local;

    @NonNull
    @ManyToOne @JoinColumn(name = "IDCOURS")
    private Cours cours;
}
