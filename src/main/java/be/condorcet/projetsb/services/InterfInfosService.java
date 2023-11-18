package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Infos;
import be.condorcet.projetsb.modele.InfosKey;

import java.util.List;

public interface InterfInfosService extends InterfService<Infos>{
    Infos read(InfosKey id) throws Exception;
    List<Infos> findAllByNhIsGreaterThanEqual(int nh);
    List<Infos> findAllByIdIdformateur(int idformateur);
    List<Infos> findAllByIdIdsessioncours(int idsessioncours);

}
