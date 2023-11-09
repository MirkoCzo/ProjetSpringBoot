package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.SessionCours;

import java.util.Date;
import java.util.List;

public interface InterfSessionCoursService extends InterfService<SessionCours> {
    List<SessionCours> findSessionCoursByNbreinscritsGreaterThan(int nbreinscrit);
    List<SessionCours> findSessionCoursByCours_Idcours(int idcours);

    public List<SessionCours> findSessionCoursByCours(Cours cours);


}
