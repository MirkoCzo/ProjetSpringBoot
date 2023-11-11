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

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            local = new Local(null,"TestSigle",1,"TestDescription",new ArrayList<>());
            localService.create(local);
            cours = new Cours(null,"TestMatiere",1,new ArrayList<>());
            coursService.create(cours);
            sessionCours = new SessionCours(dateDebut,dateFin,1,local,cours);
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
            assertNotEquals(0, sessionCours.getIdsessioncours(), "Id non incrementé: " + sessionCours.getIdsessioncours());
            assertEquals(dateDebut, sessionCours.getDate_Debut(), "Dates de début différente, date voulue : " + dateDebut + " réelle date : " + sessionCours.getDate_Debut());
            assertEquals(dateFin,sessionCours.getDate_Fin(), "Date de fin différente, date voulue: " + dateDebut + " réelle date: " + sessionCours.getDate_Fin());
            assertEquals(001,sessionCours.getNbreinscrits(),"nombre d'inscrits différent.");
            assertEquals(local.getIdlocal(), sessionCours.getLocal().getIdlocal(), "Id de local différent, ID voulu: " + local.getIdlocal() + " réel ID : " + sessionCours.getLocal().getIdlocal());
            assertEquals(cours.getIdcours(), sessionCours.getCours().getIdcours(), "Id de cours différent, ID voulu: " + cours.getIdcours() + " réel ID: " + sessionCours.getCours().getIdcours());
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la création "+e);
        }
    }

    @Test
    void read() {
        try
        {
            SessionCours sess2=sessionCoursService.read(sessionCours.getIdsessioncours());
            assertEquals(local,sess2.getLocal(),"local différent"+sess2.getLocal()+" et"+local);//TODO le test me dit que les locaux sont différent corrigé en redefinissant la classe equals
            assertEquals(cours,sess2.getCours(),"cours différents"+sess2.getCours()+" et"+cours);
            assertEquals(dateDebut, sessionCours.getDate_Debut(), "Dates de début différente, date voulue : " + sessionCours.getDate_Debut() + " réelle date : " +dateDebut );
            assertEquals(dateFin, sess2.getDate_Fin(), "Date de fin différente, date voulue: " + sess2.getDate_Fin() + " réelle date: " + dateDebut);
            assertEquals(1,sess2.getNbreinscrits(),"Nombre d'inscrit different: "+sess2.getNbreinscrits()+" reel nombre: "+sessionCours.getNbreinscrits());

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
                sessionCoursService.read(sessionCours.getIdsessioncours());
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
            fail("Erreur lors de la recherche de toutes les session par nombre d'inscrits. "+e);
        }

    }

    @Test
    void findSessionCoursByCours_Idcours() {
        try
        {
            boolean flag=false;
            List<SessionCours> lsc = sessionCoursService.findSessionCoursByCours_Idcours(cours.getIdcours());
            for (SessionCours sc : lsc
            ) {
                System.out.println(sc);
                flag=true;

            }
            assertTrue(flag,"Aucune Session dans la liste");
        }catch (Exception e)
        {
            fail("Erreur lors de la recherche de toutes les session par ID donné.");
        }
    }

    @Test
    void findSessionCoursByCours() { //Correction par rapport a findByCours_IDCOURS
        try {
            Cours info = coursService.read(1);
            Cours math = coursService.read(3);
            Collection<SessionCours> lsc = sessionCoursService.findSessionCoursByCours(cours);
            boolean trouve = false;
            for(SessionCours sc:lsc){
                    trouve=true; //TODO pq ça fonctionne pas
                    System.out.println(sc);
                }
            assertTrue(trouve,"Pas de session de cours trouvé pour ce cours");
        }
        catch(Exception e){
            fail("Erreur lors de la recherche de toutes les sessions par cours donné"+e);
        }
    }
}