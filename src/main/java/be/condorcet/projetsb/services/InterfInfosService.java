package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Infos;
import be.condorcet.projetsb.modele.InfosKey;

public interface InterfInfosService extends InterfService<Infos>{
    Infos read(InfosKey id) throws Exception;
}
