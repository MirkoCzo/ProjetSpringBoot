package be.condorcet.projetsb.webservices;

import be.condorcet.projetsb.modele.Infos;
import be.condorcet.projetsb.modele.InfosKey;
import be.condorcet.projetsb.services.InterfInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/infos")
public class RestInfos {

    @Autowired
    private InterfInfosService infosServiceImpl;

    //--------------------------Retrouver les informations ID session et ID formateur---------------------------------
    @RequestMapping(value = "/{idSession}/{idFormateur}", method = RequestMethod.GET)
    public ResponseEntity<Infos> getInfos(
            @PathVariable(value = "idSession") int idSession,
            @PathVariable(value = "idFormateur") int idFormateur) throws Exception {
        System.out.println("Recherche du nombre d'heures attribué pour la session d'ID : " + idSession + " et le formateur d'ID : " + idFormateur);
        InfosKey infosKey = new InfosKey(idSession, idFormateur);
        Infos infos = infosServiceImpl.read(infosKey);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    //--------------------------Créer des informations-----------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Infos> createInfos(@RequestBody Infos infos) throws Exception {
        System.out.println("Attribution du nombre d'heures : " + infos);
        infosServiceImpl.create(infos);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    //--------------------------Mettre à jour des informations-----------------------------------------
    @RequestMapping(value = "/{idSession}/{idFormateur}", method = RequestMethod.PUT)
    public ResponseEntity<Infos> updateInfos(
            @PathVariable(value = "idSession") int idSession,
            @PathVariable(value = "idFormateur") int idFormateur,
            @RequestBody Infos newInfos) throws Exception {
        System.out.println("Mise à jour du nombre d'heures pour la session d'ID : " + idSession + " et le formateur d'ID : " + idFormateur);
        InfosKey infosKey = new InfosKey(idSession, idFormateur);
        newInfos.setId(infosKey);
        Infos infos = infosServiceImpl.update(newInfos);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    //--------------------------Supprimer des informations pour un ID session et un ID formateur donné----------------------------------------
    @RequestMapping(value = "/{idSession}/{idFormateur}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteInfos(
            @PathVariable(value = "idSession") int idSession,
            @PathVariable(value = "idFormateur") int idFormateur) throws Exception {
        System.out.println("Suppression des infos pour la session d'ID : " + idSession + " et le formateur d'ID : " + idFormateur);
        InfosKey infosKey = new InfosKey(idSession, idFormateur);
        Infos infos = infosServiceImpl.read(infosKey);
        infosServiceImpl.delete(infos);
        System.out.println("Infos supprimées");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //--------------------------Toutes les informations-----------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> allInfos() throws Exception {
        System.out.println("Recherche de toutes les infos");
        return new ResponseEntity<>(infosServiceImpl.all(), HttpStatus.OK);
    }
    //--------------------------Retrouver toutes les infos superieures ou égales à un nombre d'heures données--------------
    @RequestMapping(value = "/nh/{nh}",method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> findAllByNhIsGreaterThanEqual(@PathVariable (value = "nh") int nh) throws Exception
    {
        System.out.println("Recherche des infos avec un nombre d'heures superieure ou égal à: "+nh);
        List<Infos> infos = infosServiceImpl.findAllByNhIsGreaterThanEqual(nh);

        if (!infos.isEmpty()) {
            return new ResponseEntity<>(infos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //--------------------------Retrouver toutes les infos lié à un formateur----------------------------
    @RequestMapping(value = "/formateur/{idformateur}",method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> findAllInfosByFormateur(@PathVariable(value = "idformateur")int idformateur) throws Exception
    {
        System.out.println("Recherche des infos lié au formateur d'ID "+idformateur);
        List<Infos> infos = infosServiceImpl.findAllByIdIdformateur(idformateur);
        if (!infos.isEmpty()) {
            return new ResponseEntity<>(infos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //--------------------------Retrouver toutes les infos lié à une session de cours--------------------
    @RequestMapping(value = "/sessionCours/{idsessioncours}",method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> findAllInfosBySessionCours(@PathVariable(value = "idsessioncours")int idsessioncours) throws Exception
    {
        System.out.println("Recherche des infos lié à la session d'ID "+idsessioncours);
        List<Infos> infos = infosServiceImpl.findAllByIdIdsessioncours(idsessioncours);
        if (!infos.isEmpty()) {
            return new ResponseEntity<>(infos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //--------------------------Gérer les exceptions-----------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
        return ResponseEntity.notFound().header("error", ex.getMessage()).build();
    }
}
