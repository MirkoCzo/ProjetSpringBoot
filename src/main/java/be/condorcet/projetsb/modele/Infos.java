package be.condorcet.projetsb.modele;

import be.condorcet.projetsb.modele.Formateur;
import be.condorcet.projetsb.modele.SessionCours;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "APIINFOS", schema = "ORA2", catalog = "XE")
public class Infos {


    @EmbeddedId
    private InfosKey id;


    @ManyToOne
    @MapsId("idsessioncours")
    @JoinColumn(name = "IDSESSIONCOURS")
    private SessionCours sessionCours;


    @ManyToOne
    @MapsId("idformateur")
    @JoinColumn(name = "IDFORMATEUR")
    private Formateur formateur;

    private int nh;

    //TODO JE NE SAIS PAS COMMENT CREER UN REPOSITORY QUAND CELUI SI DEPEND DE 2 CLEFS
}



