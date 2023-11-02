package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;

import java.util.List;

//Pour les méthodes spéciales
public interface InterfCoursService extends InterfService<Cours>{

    public List<Cours> read(String matiere);
}
