package be.condorcet.projetsb.modele;

import be.condorcet.projetsb.modele.Cours;

import java.util.Comparator;

public class CoursComparator implements Comparator<Cours> {
    @Override
    public int compare(Cours cours1, Cours cours2) {
        return Integer.compare(cours1.getId_cours(), cours2.getId_cours());
    }
}
