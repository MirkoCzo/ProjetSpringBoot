package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.Local;
import be.condorcet.projetsb.modele.SessionCours;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionCoursServiceImplTest {



    LocalDate dateDebutLoc = LocalDate.of(2022, 9, 2);
    Date dateDebut = Date.valueOf(dateDebutLoc);

    LocalDate dateFinLoc = LocalDate.of(2022, 9, 3);
    Date dateFin = Date.valueOf(dateFinLoc);

    private SessionCours sessionCours;
    private Local local;
    private Cours cours;

    @Autowired
    private SessionCoursServiceImpl sessionCoursService;

    @Autowired
    private LocalServiceImpl localService;

    @Autowired
    private CoursServiceImpl coursService;


    @BeforeEach
    void setUp() {
        try {

            local = new Local("TestSigle",001,"TestDescription");
            localService.create(local);
            cours = new Cours("TestMatiere",001);
            coursService.create(cours);
            sessionCours = new SessionCours(dateDebut,dateFin,001,local,cours);
            sessionCoursService.create(sessionCours);
            System.out.println("Creation de la session : "+sessionCours);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la création de la session de cours "+e+" session cours = "+sessionCours);
        }
    }

    @AfterEach
    void tearDown() {
        try
        {
            sessionCoursService.delete(sessionCours);
            System.out.println("effacement de la session: "+sessionCours);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la suppression de la session de cours");

        }
        try
        {
            localService.delete(local);
            System.out.println("effacement du local: "+local);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la suppression du local");
        }
        try
        {
            coursService.delete(cours);
            System.out.println("effacement du cours: "+cours);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la suppression du cours");
        }

    }

    @Test
    void create() {
        try {
            assertNotEquals(0, sessionCours.getId_sessioncours(), "Id non incrementé: " + sessionCours.getId_sessioncours());
            assertEquals(dateDebut, sessionCours.getDate_Debut(), "Dates de début différente, date voulue : " + dateDebut + " réelle date : " + sessionCours.getDate_Debut());
            assertEquals(dateFin,sessionCours.getDate_Fin(), "Date de fin différente, date voulue: " + dateDebut + " réelle date: " + sessionCours.getDate_Fin());
            assertEquals(001,sessionCours.getNbreinscrits(),"nombre d'inscrits différent.");
            assertEquals(local.getId_local(), sessionCours.getLocal().getId_local(), "Id de local différent, ID voulu: " + local.getId_local() + " réel ID : " + sessionCours.getLocal().getId_local());
            assertEquals(cours.getId_cours(), sessionCours.getCours().getId_cours(), "Id de cours différent, ID voulu: " + cours.getId_cours() + " réel ID: " + sessionCours.getCours().getId_cours());
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la création "+e);
        }
    }

    @Test
    void read() {
        try
        {
            int numsess=sessionCours.getId_sessioncours();
            SessionCours sess2=sessionCoursService.read(numsess);
            assertEquals(local,sess2.getLocal(),"local différent"+sess2.getLocal()+" et"+local);
            assertEquals(cours,sess2.getCours(),"cours différents"+sess2.getCours()+" et"+cours);
            assertEquals(dateDebut, sessionCours.getDate_Debut(), "Dates de début différente, date voulue : " + sessionCours.getDate_Debut() + " réelle date : " +dateDebut );
            assertEquals(dateFin, sess2.getDate_Fin(), "Date de fin différente, date voulue: " + sess2.getDate_Fin() + " réelle date: " + dateDebut);
            assertEquals(001,sess2.getNbreinscrits(),"Nombre d'inscrit different: "+sess2.getNbreinscrits()+" reel nombre: "+sessionCours.getNbreinscrits());

        }catch(Exception e)
        {
            fail("recherche infructueuse: "+e);
        }
    }

    @Test
    void update() {
        try
        {
            LocalDate dateDebutLoc2 = LocalDate.of(2022, 9, 4);
            LocalDate dateFinLoc2 = LocalDate.of(2022, 9, 5);
            Date newDateDebut = Date.valueOf(dateDebutLoc2);
            Date newDateFin = Date.valueOf(dateFinLoc2);
            cours.setMatiere("TestMatiere2");
            local.setSigle("TestSigle2");
            sessionCours.setDate_Debut(newDateDebut);
            sessionCours.setDate_Fin(newDateFin);
            sessionCours.setNbreinscrits(002);
            sessionCours = sessionCoursService.update(sessionCours);
            assertEquals("TestMatiere2",sessionCours.getCours().getMatiere()," matière donc cours différent de TestMatiere2: "+sessionCours.getCours().getMatiere());
            assertEquals("TestSigle2",sessionCours.getLocal().getSigle(),"Sigle différent donc local différent de TestSigle2: "+sessionCours.getLocal().getSigle());
            assertEquals(002,sessionCours.getNbreinscrits(),"Nombre d'inscrits différent: "+sessionCours.getNbreinscrits());
            assertEquals(newDateDebut,sessionCours.getDate_Debut(),"Date différente de "+newDateDebut+" : "+sessionCours.getDate_Debut());
            assertEquals(newDateFin,sessionCours.getDate_Fin(),"Date différente de "+newDateFin+" : "+sessionCours.getDate_Fin());


        }catch (Exception e)
        {
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try
        {
            sessionCoursService.delete(sessionCours);
            Assertions.assertThrows(Exception.class,()->
            {
                sessionCoursService.read(sessionCours.getId_sessioncours());
            },"cours non effacé");
        }catch (Exception e )
        {
            fail("Erreur d'effacement: "+e);
        }
    }

    @Test
    void all() {
        try
        {
            List<SessionCours> lsc = sessionCoursService.all();
            assertNotEquals(0,"La Liste ne contient aucun element.");
            for (SessionCours se : lsc
            ) {
                System.out.println(se);
            }

        }catch(Exception e)
        {
            fail("Erreur lors de la recherche de toutes les sessions de cours.");
        }
    }

    @Test
    void findSessionCoursByNbreinscritsGreaterThan() {
        try
        {
            boolean flag = false;
            List<SessionCours> lsc=sessionCoursService.findSessionCoursByNbreinscritsGreaterThan(0);
            for (SessionCours sc : lsc
            ) {
                System.out.println(sc);
                flag=true;

            }
            assertTrue(flag,"Aucune Session dans la liste");

        }catch (Exception e)
        {
            fail("Erreur lors de la recherche de toutes les session par nombre d'inscrits.");
        }

    }
}