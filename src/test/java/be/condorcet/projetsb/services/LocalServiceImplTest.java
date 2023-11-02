package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalServiceImplTest {

    Local local;

    @Autowired
    private LocalServiceImpl localServiceImpl;

    @BeforeEach
    void setUp() {
        try
        {
            local= new Local("TestSigle",0,"TestDescription");
            localServiceImpl.create(local);
            System.out.println("Création du cours: "+local);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la création du local: "+local);
        }
    }

    @AfterEach
    void tearDown() {
        try
        {
            localServiceImpl.delete(local);
            System.out.println("Effacement du local: "+local);

        }catch (Exception e)
        {
            System.out.println("Erreur lors de l'effacement du local: "+local);
        }
    }

    @Test
    void create() {
        try
        {
            assertNotEquals(0,local.getId_local(),"Id du local non incrémenté");
            assertEquals(0,local.getPlaces(),"Nombre de places enregistré: "+local.getPlaces()+" au lieu de 0");
            assertEquals("TestSigle",local.getSigle(),"Local non enregistré: "+local.getSigle()+" au lieu de TestSigle");

        }catch (Exception e)
        {
            System.out.println("Erreur lors de la création");
        }

    }

    @Test
    void read() {
        try {
            int numLocal = local.getId_local();
            Local local2 = localServiceImpl.read(numLocal);
            assertEquals("TestSigle",local2.getSigle());
            assertEquals(0,local2.getPlaces());
            assertEquals("TestDescription",local2.getDescription());
        }catch (Exception e)
        {
            fail("Recherche infructueuse: "+e);
        }

    }

    @Test
    void update() {
        try {
            local.setSigle("TestLocal2");
            local.setPlaces(001);
            local.setDescription("TestDescription2");
            localServiceImpl.update(local);
            assertEquals("TestLocal2",local.getSigle()," Sigle différent");
            assertEquals(001,local.getPlaces()," nombre de places différentes");
            assertEquals("TestDescription2",local.getDescription()," description différente");
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la mise à jour");
        }
    }

    @Test
    void delete() {
        try
        {
            localServiceImpl.delete(local);
            Assertions.assertThrows(Exception.class,() -> {
                localServiceImpl.read(local.getId_local());
            },"local non effacé");
        }catch(Exception e)
        {
            System.out.println("Erreur lors de l'effacement");
        }
    }

    @Test
    void all() {
        try
        {
            List<Local> ll = localServiceImpl.all();
            assertNotEquals(0,ll.size(),"aucun local dans la liste.");
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la recherche de tout les locauxx");
        }
    }
}