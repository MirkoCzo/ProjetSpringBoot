package be.condorcet.projetsb;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.CoursComparator;
import be.condorcet.projetsb.modele.Local;
import be.condorcet.projetsb.modele.SessionCours;
import be.condorcet.projetsb.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/local")
public class GestLocal {
    @Autowired
    private LocalRepository localRepository;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){

        List<Local> liste;
        try {
            liste = localRepository.findAll();
            model.put("mesLocaux", liste);
        } catch (Exception e) {
            return "error";
        }
        return "affichagetousLocaux";
    }
    @RequestMapping("/findByNbrePlaces") //Critère non unique
    String findByNbreInscrit(@RequestParam("places") int places, Map<String,Object>model)
    {
        List<Local> ll;
        try
        {
            ll=localRepository.findAllByPlacesGreaterThanEqual(places);
            model.put("mesLocaux",ll);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la recherche de tous les locaux" +e);
            return "error";
        }
        return "affichageParPlaces";
    }
}
