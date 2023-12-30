package be.condorcet.projetsb.webservices;

import be.condorcet.projetsb.modele.Formateur;
import be.condorcet.projetsb.services.InterfFormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/formateur")
public class RestFormateur {

    @Autowired
    private InterfFormateurService formateurServiceImpl;

    //--------------------------Retrouver le formateur correspondant à un ID donné---------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Formateur> getFormateur(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Recherche du formateur d'ID : " + id);
        Formateur formateur = formateurServiceImpl.read(id);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    //--------------------------Créer un formateur-----------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Formateur> createFormateur(@RequestBody Formateur formateur) throws Exception {
        System.out.println("Création du formateur : " + formateur);
        formateurServiceImpl.create(formateur);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    //--------------------------Mettre à jour un formateur-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Formateur> updateFormateur(@PathVariable(value = "id") int id, @RequestBody Formateur newFormateur) throws Exception {
        System.out.println("Mise à jour du formateur d'ID : " + id);
        newFormateur.setIdformateur(id);
        Formateur formateur = formateurServiceImpl.update(newFormateur);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    //--------------------------Supprimer un formateur d'ID donné-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFormateur(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Suppression du formateur d'ID : " + id);
        Formateur formateur = formateurServiceImpl.read(id);
        System.out.println("Formateur: "+formateur);
        formateurServiceImpl.delete(formateur);
        System.out.println("Formateur supprimé");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //--------------------------Tous les formateurs-----------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Formateur>> allFormateurs() throws Exception {
        System.out.println("Recherche de tous les formateurs");
        return new ResponseEntity<>(formateurServiceImpl.all(), HttpStatus.OK);
    }
    //--------------------------Retrouver le formateur correspondant à un e-mail donné---------------------------------
    @RequestMapping(value = "/mail/{mail}", method = RequestMethod.GET)
    public ResponseEntity<Formateur> getFormateurByMail(@PathVariable(value = "mail") String mail) throws Exception {
        System.out.println("Recherche du formateur par e-mail : " + mail);
        Formateur formateur = formateurServiceImpl.findFormateurByMail(mail);
        System.out.println(formateurServiceImpl.findFormateurByMail(mail));
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    //--------------------------Retrouver les formateurs correspondant à un nom et prénom donnés---------------------------------
    @RequestMapping(value = "/nomPrenom/{nom}/{prenom}", method = RequestMethod.GET)
    public ResponseEntity<List<Formateur>> getFormateursByNomAndPrenom(
            @PathVariable(value = "nom") String nom,
            @PathVariable(value = "prenom") String prenom) throws Exception {
        System.out.println("Recherche des formateurs par nom et prénom : " + nom + " " + prenom);
        List<Formateur> formateurs = formateurServiceImpl.findFormateurByNomAndPrenom(nom, prenom);
        return new ResponseEntity<>(formateurs, HttpStatus.OK);
    }


    //--------------------------Gérer les exceptions-----------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
        return ResponseEntity.notFound().header("error", ex.getMessage()).build();
    }
}
