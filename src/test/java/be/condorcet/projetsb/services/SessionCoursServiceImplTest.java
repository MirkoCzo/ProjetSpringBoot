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
            assertEquals(dateDebut, sessionCours.getDateDebut(), "Dates de début différente, date voulue : " + dateDebut + " réelle date : " + sessionCours.getDateDebut());
            assertEquals(dateFin,sessionCours.getDateFin(), "Date de fin différente, date voulue: " + dateDebut + " réelle date: " + sessionCours.getDateFin());
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
            assertEquals(dateDebut, sessionCours.getDateDebut(), "Dates de début différente, date voulue : " + sessionCours.getDateDebut() + " réelle date : " +dateDebut );
            assertEquals(dateFin, sess2.getDateFin(), "Date de fin différente, date voulue: " + sess2.getDateFin() + " réelle date: " + dateDebut);

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
            sessionCours.setDateDebut(newDateDebut);
            sessionCours.setDateFin(newDateFin);
            sessionCours = sessionCoursService.update(sessionCours);
            assertEquals("TestMatiere2",sessionCours.getCours().getMatiere()," matière donc cours différent de TestMatiere2: "+sessionCours.getCours().getMatiere());
            assertEquals("TestSigle2",sessionCours.getLocal().getSigle(),"Sigle différent donc local différent de TestSigle2: "+sessionCours.getLocal().getSigle());
            assertEquals(newDateDebut,sessionCours.getDateDebut(),"Date différente de "+newDateDebut+" : "+sessionCours.getDateDebut());
            assertEquals(newDateFin,sessionCours.getDateFin(),"Date différente de "+newDateFin+" : "+sessionCours.getDateFin());


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
}