package be.condorcet.projetsb;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.CoursComparator;
import be.condorcet.projetsb.modele.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/cours")
public class GestCours {
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    private CoursRepository coursRepository;
    /*@Autowired
    CacheManager cacheManager;

    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }*/
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        //evictAllCaches();
        System.out.println("recherche cours");
        List<Cours> liste;
        try {
            liste = coursRepository.findAll();
            Collections.sort(liste,new CoursComparator());
            model.put("mesCours", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousCours";
    }

    @RequestMapping("/selection")
    String selection(@RequestParam("id") int id, Map<String,Object> model)
    {
        Cours c = null;
        Optional<Cours> ocl;
        try
        {
            ocl = coursRepository.findById(id);
            if (ocl.isPresent())
            {
                c=ocl.get();
            }
            else
            {
                throw new Exception("Cours introuvable");
            }
            model.put("monCours",c);

        } catch (Exception e)
        {
            System.out.println("Erreur lors de la lecture "+e);
            model.put("error",e);
            return "error";
        }
        return "affCours";
    }

    @RequestMapping("/ajout")
    String ajout(@RequestParam("mat") String mat,@RequestParam("heures") int heures,Map<String,Object> model)
    {
        Cours c = new Cours(mat,heures);
        try
        {
            coursRepository.save(c);
            model.put("monCours",c);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de l'ajout "+e);
            model.put("error",e);
            return "error";
        }
        return "ajoutCours";
    }

    @RequestMapping("/tous/suppression")
    String suppression(@RequestParam("del")int id,Map<String,Object> model)
    {
        Optional<Cours> ocl;
            Cours c= new Cours();
            try
            {
                ocl=coursRepository.findById(id);
                if (ocl.isPresent())
                {
                    /*c.setId_cours(ocl.get().getId_cours());
                    c.setMatiere(ocl.get().getMatiere());
                    c.setHeures(ocl.get().getHeures());*/
                    c=ocl.get();
                    model.put("monCours",c);
                    coursRepository.deleteById(id);
                }
            }catch (Exception e)
            {
                System.out.println("Erreur lors de la suppression "+e);
                model.put("error",e);
                return "error";

            }
            return "suppressionCours";
    }

    @RequestMapping("/selectionMatiere")
    String selection(@RequestParam("matiere") String mat, Map<String,Object> model)
    {
        List<Cours> lc;
        Optional<Cours> ocl;
        try
        {
            lc = coursRepository.findByMatiere(mat);
            model.put("mesCours",lc);

        } catch (Exception e)
        {
            System.out.println("Erreur lors de la lecture "+e);
            model.put("error",e);
            return "error";
        }
        return "affCoursNom";
    }

    @RequestMapping("/findBetweenId")
    String findBetweenId(@RequestParam("id1") Integer id1,@RequestParam("id2") Integer id2,Map<String,Object> model)
    {
        Collection<Cours> lc;
        try
        {
            lc = coursRepository.findAllCoursBetweenId(id1,id2);
            model.put("mesCours",lc);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la lecture "+e);
            model.put("error",e);
            return "error";
        }
        return "affichagetousCours";
    }
    @RequestMapping("/findAllCoursByMatiere")
    String findAllCoursByMatiere(@RequestParam("findMat")String findMat,Map<String,Object>model)
    {
        Collection<Cours> lc;
        try
        {
            lc = coursRepository.findAllCoursByMatiere(findMat);
            model.put("mesCours",lc);
        }catch (Exception e)
        {
            System.out.println("Erreur lors de la lecture "+e);
            model.put("error",e);
            return "error";
        }
        return "affichagetousCours";
    }







}
