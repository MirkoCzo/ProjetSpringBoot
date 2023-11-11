package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Local;

import java.util.List;

public interface InterfLocalService extends InterfService<Local>{
    List<Local> findAllByPlacesGreaterThanEqual(int places);

    Local findBySigle(String sigle);
}
