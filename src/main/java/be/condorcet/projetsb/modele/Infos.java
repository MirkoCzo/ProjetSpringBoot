package be.condorcet.projetsb.modele;

import be.condorcet.projetsb.modele.Formateur;
import be.condorcet.projetsb.modele.SessionCours;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIINFOS", schema = "ORA2", catalog = "XE")
public class Infos {

    @Id
    @JoinColumn(name = "IDFORMATEUR")
    private int idformateur;

    @Id
    @JoinColumn(name = "IDSESSIONCOURS")
    private int idsessioncours;

    @NonNull
    private int nombreHeures;

    //TODO JE NE SAIS PAS COMMENT CREER UN REPOSITORY QUAND CELUI SI DEPEND DE 2 CLEFS
}



