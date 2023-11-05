package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.SessionCours;

import java.util.Date;
import java.util.List;

public interface InterfSessionCoursService extends InterfService<SessionCours> {
    List<SessionCours> findSessionCoursByNbreinscritsGreaterThan(int nbreinscrit);
}
