package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.SessionCours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SessionCoursRepository extends JpaRepository<SessionCours,Integer> {

   List<SessionCours> findSessionCoursByNbreinscritsGreaterThan(int nbreinscrit) throws Exception; //critere non unique

   List<SessionCours> findSessionCoursByCours_Idcours(int idcours)throws Exception;

   List<SessionCours> findSessionCoursByCours(Cours cours)throws Exception;


}
