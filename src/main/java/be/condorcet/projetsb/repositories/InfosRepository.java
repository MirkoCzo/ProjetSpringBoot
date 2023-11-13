package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.Infos;
import be.condorcet.projetsb.modele.InfosKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfosRepository extends JpaRepository<Infos, InfosKey> {
}
