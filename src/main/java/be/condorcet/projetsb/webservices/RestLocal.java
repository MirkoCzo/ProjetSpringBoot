package be.condorcet.projetsb.webservices;

import be.condorcet.projetsb.modele.Local;
import be.condorcet.projetsb.services.InterfLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/local")
public class RestLocal {

    @Autowired
    private InterfLocalService localServiceImpl;

    //--------------------------Retrouver le local correspondant à un ID donné---------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Local> getLocal(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Recherche du local d'ID: " + id);
        Local local = localServiceImpl.read(id);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    //--------------------------Retrouver les locaux ayant un nombre de places supérieur à un nombre donné-----------------------------------------
    @RequestMapping(value = "/places={places}", method = RequestMethod.GET)
    public ResponseEntity<List<Local>> listLocauxPlaces(@PathVariable(value = "places") int places) throws Exception {
        System.out.println("Recherche des locaux avec un nombre de places >= " + places);
        List<Local> localList = localServiceImpl.findAllByPlacesGreaterThanEqual(places);
        return new ResponseEntity<>(localList, HttpStatus.OK);
    }
    //--------------------------Retrouver le local avec un sigle donné-----------------------------------------
    @RequestMapping(value = "/sigle={sigle}",method = RequestMethod.GET)
    public ResponseEntity<Local> localBySigle(@PathVariable(value = "sigle")String sigle) throws Exception{
        System.out.println("Recherche du local ayant le sigle "+sigle);
        Local localSigle = localServiceImpl.findBySigle(sigle);
        System.out.println(localSigle);
        return new ResponseEntity<>(localSigle,HttpStatus.OK);
    }


    //--------------------------Créer un local-----------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Local> createLocal(@RequestBody Local local) throws Exception {
        System.out.println("Création du local : " + local);
        localServiceImpl.create(local);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    //--------------------------Mettre à jour un local-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Local> updateLocal(@PathVariable(value = "id") int id, @RequestBody Local newLocal) throws Exception {
        System.out.println("Mise à jour du local d'ID : " + id);
        newLocal.setIdlocal(id);
        Local local = localServiceImpl.update(newLocal);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    //--------------------------Supprimer un local d'ID donné-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLocal(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Suppression du local d'ID : " + id);
        Local local = localServiceImpl.read(id);
        System.out.println(local);
        localServiceImpl.delete(local);
        System.out.println("Local supprimé");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //--------------------------Tous les locaux-----------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Local>> allLocaux() throws Exception {
        System.out.println("Recherche de tous les locaux");
        return new ResponseEntity<>(localServiceImpl.all(), HttpStatus.OK);
    }

    //--------------------------Gérer les exceptions-----------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
        return ResponseEntity.notFound().header("error", ex.getMessage()).build();
    }
}
