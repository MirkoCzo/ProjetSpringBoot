package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Integer> {

    List<Cours> findByMatiere(String matiere);

    @Query(value = "SELECT * FROM APICOURS WHERE idcours BETWEEN :id1 AND :id2",nativeQuery = true)
    Collection<Cours> findAllCoursBetweenId(@Param("id1")Integer id1,@Param("id2")Integer id2);

    @Query(value = "SELECT * FROM APICOURS WHERE MATIERE = :findMat",nativeQuery = true)
    Collection<Cours> findAllCoursByMatiere(@Param("findMat") String findMat);

    List<Cours> findAllByHeuresGreaterThanEqual(int heures);


}
