package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.SessionCours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionCoursRepository extends JpaRepository<SessionCours,Integer> {
}
