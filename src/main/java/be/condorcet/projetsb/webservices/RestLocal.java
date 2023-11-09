package be.condorcet.projetsb.webservices;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.Local;
import be.condorcet.projetsb.services.InterfLocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/local")
public class RestLocal {
    private InterfLocalService localServiceImpl;
    //--------------------------Retrouver le local correspondant à un ID donné---------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Local> getLocal(@PathVariable(value = "id")int id) throws Exception
    {
        System.out.println("Recherche du local ayant l'ID: "+id);
        Local local = localServiceImpl.read(id);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }
    //--------------------------Retrouver les locaux ayant plus de places qu'un nombre donné-----------------------------------------
    @RequestMapping(value = "/places={places}", method = RequestMethod.GET)
    public ResponseEntity<List<Local>> getLocalPlaces(@PathVariable(value = "places")int places) throws Exception
    {
        System.out.println("Recherche du local ayant l'ID: "+places);
        List<Local> localList = localServiceImpl.findAllByPlacesGreaterThanEqual(places);
        return new ResponseEntity<>(localList, HttpStatus.OK);
    }
    //--------------------------Creer un Local-----------------------------------------
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Local> createLocal(@RequestBody Local local) throws Exception
    {
        System.out.println("Creation du cours: "+local);
        localServiceImpl.create(local);
        return new ResponseEntity<>(local,HttpStatus.OK);
    }
}

