package be.condorcet.projetsb.webservices;


import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.services.InterfCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/cours")
public class RestCours {
    @Autowired
    private InterfCoursService coursServiceImpl;

    //--------------------------Retrouver le cours correspondant à un ID donné---------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCours(@PathVariable(value = "id")int id) throws Exception
    {
        System.out.println("Recherche du cours d'ID: "+id);
        Cours cours = coursServiceImpl.read(id);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }
    //--------------------------Retrouver les cours portant un nom donné-----------------------------------------
    @RequestMapping(value = "/matiere={matiere}",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCoursMatiere(@PathVariable(value = "matiere")String matiere) throws Exception
    {
        System.out.println("Recherche des cours avec le nom de matière: "+matiere);
        List<Cours> coursList;
        coursList = coursServiceImpl.findByMatiere(matiere);
        return new ResponseEntity<>(coursList,HttpStatus.OK);
    }
    //--------------------------Retrouver les cours ayant un nombre d'heures superieur à un nombre donné-----------------------------------------
    @RequestMapping(value = "/heures={heures}",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCoursHeures(@PathVariable(value = "heures")int heures) throws Exception
    {

        System.out.println("Recherche des cours avec le nombre d'heures >: "+heures);
        List<Cours> coursList;
        coursList = coursServiceImpl.findAllByHeuresGreaterThanEqual(heures);
        return new ResponseEntity<>(coursList,HttpStatus.OK);
    }
    //--------------------------Creer un cours-----------------------------------------

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours) throws Exception
    {
        System.out.println("Creation du cours: "+cours);
        coursServiceImpl.create(cours);
        return new ResponseEntity<>(cours,HttpStatus.OK);
    }
    //--------------------------Update un cours-----------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Cours> updateCours(@PathVariable(value = "id")int id,@RequestBody Cours newCours) throws Exception
    {
        System.out.println("Update du cours d'ID: "+id);
        newCours.setIdcours(id);
        Cours cours = coursServiceImpl.update(newCours);
        return new ResponseEntity<>(cours,HttpStatus.OK);
    }
    //--------------------------Effacer un client d'ID donné-----------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCours(@PathVariable(value = "id")int id) throws Exception
    {
        System.out.println("Suppresion du cours d'ID: "+id);
        Cours cours = coursServiceImpl.read(id);
        System.out.println(cours);
        coursServiceImpl.delete(cours);
        System.out.println("Cours supprimé");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //--------------------------TOUT LES COURS-----------------------------------------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> allCours() throws Exception
    {
        System.out.println("Recherche de tous les cours");
        return new ResponseEntity<>(coursServiceImpl.all(), HttpStatus.OK);
    }
    //-----Cours ayant une session de cours pour une date inférieur ou égal donnée et superieur ou égal à cette même date----------------------------------
    @RequestMapping(value = "/givenDate={givenDate}",method = RequestMethod.GET)
    public ResponseEntity<Set<Cours>> findAllCoursesWithSessionsInRange(@PathVariable(value = "givenDate")String givenDate) throws Exception
    {
        System.out.println("Recherche des cours donnant une session de cours avant ou une date et après ou au moment de cette même date: "+givenDate);
        Set<Cours> coursList= coursServiceImpl.findAllCoursesWithSessionsInRange(givenDate);
        return new ResponseEntity<>(coursList,HttpStatus.OK);
    }


    //--------------------------Gerer les exceptions-----------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex)
    {
        System.out.println("erreur: "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }



}
