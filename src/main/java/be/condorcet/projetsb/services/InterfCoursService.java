package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

//Pour les méthodes spéciales
public interface InterfCoursService extends InterfService<Cours>{

    List<Cours> findAllByHeuresGreaterThanEqual(int heures);
    List<Cours> findByMatiere(String matiere);

    Set<Cours> findAllCoursesWithSessionsInRange(@Param("givenDate") String givenDate);

}
