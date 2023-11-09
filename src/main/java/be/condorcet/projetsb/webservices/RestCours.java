package be.condorcet.projetsb.webservices;


import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.services.InterfCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/cours")
public class RestCours {
    @Autowired
    private InterfCoursService courServiceImpl;

    //--------------------------Retrouver le cours correspondant à un ID donné---------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCours(@PathVariable(value = "id")int id) throws Exception
    {
        System.out.println("Recherche du cours d'ID: "+id);
        Cours cours = courServiceImpl.read(id);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }
    //--------------------------Retrouver les cours portant un nom donné-----------------------------------------
    @RequestMapping(value = "/matiere={matiere}",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCoursMatiere(@PathVariable(value = "matiere")String matiere) throws Exception
    {
        System.out.println("Recherche des cours avec le nom de matière: "+matiere);
        List<Cours> coursList;
        coursList = courServiceImpl.findByMatiere(matiere);
        return new ResponseEntity<>(coursList,HttpStatus.OK);
    }
    //--------------------------Retrouver les cours ayant un nombre d'heures superieur à un nombre donné-----------------------------------------
    @RequestMapping(value = "/heures={heures}",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCoursHeures(@PathVariable(value = "heures")int heures) throws Exception
    {

        System.out.println("Recherche des cours avec le nombre d'heures >: "+heures);
        List<Cours> coursList;
        coursList = courServiceImpl.findAllByHeuresGreaterThanEqual(heures);
        return new ResponseEntity<>(coursList,HttpStatus.OK);
    }
    //--------------------------Creer un cours-----------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours) throws Exception
    {
        System.out.println("Creation du cours: "+cours);
        courServiceImpl.create(cours);
        return new ResponseEntity<>(cours,HttpStatus.OK);
    }
    //--------------------------Update un cours-----------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Cours> updateCours(@PathVariable(value = "id")int id,@RequestBody Cours newCours) throws Exception
    {
        System.out.println("Update du cours d'ID: "+id);
        newCours.setIdcours(id);
        Cours cours = courServiceImpl.update(newCours);
        return new ResponseEntity<>(cours,HttpStatus.OK);
    }
    //--------------------------Effacer un client d'ID donné-----------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCours(@PathVariable(value = "id")int id) throws Exception
    {
        System.out.println("Suppresion du cours d'ID: "+id);
        Cours cours = courServiceImpl.read(id);
        courServiceImpl.delete(cours);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //--------------------------TOUT LES COURS-----------------------------------------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> allCours() throws Exception
    {
        System.out.println("Recherche de tous les cours");
        return new ResponseEntity<>(courServiceImpl.all(), HttpStatus.OK);
    }
    //--------------------------Gerer les exceptions-----------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex)
    {
        System.out.println("erreur: "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }



}
