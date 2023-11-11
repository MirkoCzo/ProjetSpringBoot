package be.condorcet.projetsb.webservices;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.SessionCours;
import be.condorcet.projetsb.services.InterfSessionCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/sessionCours")
public class RestSessionCours {

    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;

    //--------------------------Retrouver la session de cours correspondant à un ID donné---------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SessionCours> getSessionCours(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Recherche de la session de cours d'ID : " + id);
        SessionCours sessionCours = sessionCoursServiceImpl.read(id);
        return new ResponseEntity<>(sessionCours, HttpStatus.OK);
    }

    //--------------------------Retrouver les sessions de cours ayant un nombre d'inscrits supérieur à un nombre donné-----------------------------------------
    @RequestMapping(value = "/inscrits={nbreinscrits}", method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> listSessionsCoursInscrits(@PathVariable(value = "nbreinscrits") int nbreinscrits) throws Exception {
        System.out.println("Recherche des sessions de cours avec un nombre d'inscrits >= " + nbreinscrits);
        List<SessionCours> sessionCoursList = sessionCoursServiceImpl.findSessionCoursByNbreinscritsGreaterThan(nbreinscrits);
        if(sessionCoursList.isEmpty())
        {
            System.out.println("Liste vide renvoi 404");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            System.out.println("Liste pas vide renvoi 200");
            return new ResponseEntity<>(sessionCoursList, HttpStatus.OK);
        }
    }

    //--------------------------Créer une session de cours-----------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SessionCours> createSessionCours(@RequestBody SessionCours sessionCours) throws Exception {
        System.out.println("Création de la session de cours : " + sessionCours);
        sessionCoursServiceImpl.create(sessionCours);
        return new ResponseEntity<>(sessionCours, HttpStatus.OK);
    }

    //--------------------------Mettre à jour une session de cours-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SessionCours> updateSessionCours(@PathVariable(value = "id") int id, @RequestBody SessionCours newSessionCours) throws Exception {
        System.out.println("Mise à jour de la session de cours d'ID : " + id);
        newSessionCours.setIdsessioncours(id);
        SessionCours sessionCours = sessionCoursServiceImpl.update(newSessionCours);
        return new ResponseEntity<>(sessionCours, HttpStatus.OK);
    }

    //--------------------------Supprimer une session de cours d'ID donné-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionCours(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Suppression de la session de cours d'ID : " + id);
        SessionCours sessionCours = sessionCoursServiceImpl.read(id);
        System.out.println(sessionCours);
        sessionCoursServiceImpl.delete(sessionCours);
        System.out.println("Session de cours supprimée");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //--------------------------Toutes les sessions de cours-----------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> allSessionsCours() throws Exception {
        System.out.println("Recherche de toutes les sessions de cours");
        return new ResponseEntity<>(sessionCoursServiceImpl.all(), HttpStatus.OK);
    }

    //--------------------------Gérer les exceptions-----------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
        return ResponseEntity.notFound().header("error", ex.getMessage()).build();
    }
}
