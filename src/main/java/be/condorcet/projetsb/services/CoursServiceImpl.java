package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.repositories.CoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)

public class CoursServiceImpl implements InterfCoursService{
    @Autowired
    private CoursRepository coursRepository;

    @Override
    public Cours create(Cours cours) throws Exception {
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public Cours read(Integer id) throws Exception {
        Optional<Cours> ocl=coursRepository.findById(id);
        return ocl.get();
    }

    @Override
    public Cours update(Cours cours) throws Exception {
        read(cours.getIdcours());
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public void delete(Cours cours) throws Exception {
        coursRepository.deleteById(cours.getIdcours());
    }

    @Override
    public List<Cours> all() throws Exception {
        return coursRepository.findAll();
    }


    @Override
    public List<Cours> findAllByHeuresGreaterThanEqual(int heures) {
        return coursRepository.findAllByHeuresGreaterThanEqual(heures);
    }

    @Override
    public List<Cours> findByMatiere(String matiere) {
        return coursRepository.findByMatiere(matiere);
    }


}
