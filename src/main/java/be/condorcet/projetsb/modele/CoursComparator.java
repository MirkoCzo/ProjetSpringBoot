package be.condorcet.projetsb.modele;

import be.condorcet.projetsb.modele.Cours;

import java.util.Comparator;

public class CoursComparator implements Comparator<Cours> {
    @Override
    public int compare(Cours cours1, Cours cours2) {
        return Integer.compare(cours1.getIdcours(), cours2.getIdcours());
    }
}
