package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursServiceImplTest {
    @Autowired
    private CoursServiceImpl coursServiceImpl;
    //Je n'arrive pas à utiliser l'interface, bean not found
    Cours cours;

    @BeforeEach
    void setUp() {
        try
        {
            cours = new Cours(null,"TestMatiere",01);
            coursServiceImpl.create(cours);
            System.out.println("Création du cours: "+cours);
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de la création du cours");
        }
    }

    @AfterEach
    void tearDown() {
        try
        {
            coursServiceImpl.delete(cours);
            System.out.println("Effacement du cours:"+cours);
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de l'effacement du cours: "+cours);
        }
    }

    @Test
    void create() {
        try
        {
            assertNotEquals(0,cours.getIdcours(),"Id du cours non incrémenté");
            assertEquals("TestMatiere",cours.getMatiere(),"Matière non enregistré: "+cours.getMatiere()+" au lieu de TestMatiere");
            assertEquals(01, cours.getHeures(),"Nombre d'heures incorrectes: "+cours.getHeures()+" au lieu de 01");
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de la création");
        }
    }

    @Test
    void read() {
        try
        {
            int numcours=cours.getIdcours();
            Cours cours2=coursServiceImpl.read(numcours);
            assertEquals("TestMatiere",cours2.getMatiere());
            assertEquals(01,cours2.getHeures());
        }
        catch (Exception e)
        {
            fail("Recherche infructueuse: "+e);
        }
    }

    @Test
    void update() {
        try
        {
            cours.setMatiere("TestMatiere2");
            cours.setHeures(02);
            cours = coursServiceImpl.update(cours);
            assertEquals("TestMatiere2",cours.getMatiere()," matière différente");
            assertEquals(02,cours.getHeures(),"heures différentes");
        }
        catch (Exception e)
        {
            System.out.println("Erreur de mise à jour du cours");
        }
    }

    @Test
    void delete() {
        try
        {
            coursServiceImpl.delete(cours);
            Assertions.assertThrows(Exception.class,() -> {
                coursServiceImpl.read(cours.getIdcours());
            },"Cours non effacé");
        }catch (Exception e)
        {
            fail("erreur d'effacement: "+e);
        }
    }

    @Test
    void all() {
        try
        {
            List<Cours> lc = coursServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun element");
        }catch (Exception e)
        {
            fail("erreur de recherche de tous les cours");
        }
    }

    @Test
    void findAllByHeuresGreaterThanEqual() {
        try
        {
            List<Cours> lc = coursServiceImpl.findAllByHeuresGreaterThanEqual(0);
            assertNotEquals(0,lc.size(),"la liste ne contient aucun element");
        }catch (Exception e)
        {
            fail("erreur de recherche de tous les cours");
        }

    }
}