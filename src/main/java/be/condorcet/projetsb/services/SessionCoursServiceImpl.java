package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Cours;
import be.condorcet.projetsb.modele.SessionCours;
import be.condorcet.projetsb.repositories.SessionCoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class SessionCoursServiceImpl implements InterfSessionCoursService {

    @Autowired
    private SessionCoursRepository sessionCoursRepository;

    @Override
    public SessionCours create(SessionCours sessionCours) throws Exception {
        sessionCoursRepository.save(sessionCours);
        return sessionCours;
    }

    @Override
    public SessionCours read(Integer id) throws Exception {
        Optional<SessionCours> osc = sessionCoursRepository.findById(id);
        return osc.get();
    }

    @Override
    public SessionCours update(SessionCours sessionCours) throws Exception {
        read(sessionCours.getIdsessioncours());
        sessionCoursRepository.save(sessionCours);
        return sessionCours;
    }

    @Override
    public void delete(SessionCours sessionCours) throws Exception {
        sessionCoursRepository.deleteById(sessionCours.getIdsessioncours());
    }

    @Override
    public List<SessionCours> all() throws Exception {
        return sessionCoursRepository.findAll();
    }


    @Override
    public List<SessionCours> findSessionCoursByNbreinscritsGreaterThan(int nbreinscrit) throws Exception {
        return sessionCoursRepository.findSessionCoursByNbreinscritsGreaterThan(nbreinscrit);
    }

    @Override
    public List<SessionCours> findSessionCoursByCours_Idcours(int idcours)throws Exception {
        return sessionCoursRepository.findSessionCoursByCours_Idcours(idcours);
    }

    @Override
    public List<SessionCours> findSessionCoursByCours(Cours cours)throws Exception {
        return sessionCoursRepository.findSessionCoursByCours(cours);
    }




}
