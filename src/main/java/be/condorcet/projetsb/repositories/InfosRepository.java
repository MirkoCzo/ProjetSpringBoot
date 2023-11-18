package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.Infos;
import be.condorcet.projetsb.modele.InfosKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfosRepository extends JpaRepository<Infos, InfosKey> {

    List<Infos> findAllByNhIsGreaterThanEqual(int nh);

    List<Infos> findAllByIdIdformateur(int idformateur);

    List<Infos> findAllByIdIdsessioncours(int idsessioncours);
}
