package be.condorcet.projetsb;

import be.condorcet.projetsb.modele.SessionCours;
import be.condorcet.projetsb.repositories.SessionCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sessionCours")
public class GestSessionCours {

    @Autowired
    private SessionCoursRepository sessionCoursRepository;

    @RequestMapping("/tous")
    String affTous(Map<String,Object>model)
    {
        List<SessionCours> lsc;
        try
        {
            lsc=sessionCoursRepository.findAll();
            model.put("mesSessions",lsc);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la recherche de toutes les sessions" +e);
            return "error";
        }
        return "affichagetousSessions";
    }

    @RequestMapping("/findByNbreInscrit") //Critère non unique
    String findByNbreInscrit(@RequestParam("inscrits") int nbreinscrit, Map<String,Object>model)
    {
        List<SessionCours> lsc;
        try
        {
            lsc=sessionCoursRepository.findSessionCoursByNbreinscritsGreaterThan(nbreinscrit);
            model.put("mesSessions",lsc);
            /*if (lsc.size()>0)
            {
                throw new Exception("Aucun session trouvée.");
            }*/
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la recherche de toutes les sessions" +e);
            //model.put("error",e);
            return "error";
        }
        return "affichageParInscrits";
    }


}
//TODO CRITERE NON UNIQUE COURS/LOCAL + TOUTES LES SESSION D'UN COURS DONNE