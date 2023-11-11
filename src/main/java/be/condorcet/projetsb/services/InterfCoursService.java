package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;

import java.util.List;

//Pour les méthodes spéciales
public interface InterfCoursService extends InterfService<Cours>{

    List<Cours> findAllByHeuresGreaterThanEqual(int heures);
    List<Cours> findByMatiere(String matiere);

}
