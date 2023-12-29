package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local,Integer> {

    List<Local> findAllByPlacesGreaterThanEqual(int places);

    List<Local> findBySigle(String sigle);
}
