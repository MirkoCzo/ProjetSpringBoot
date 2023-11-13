package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Formateur;
import be.condorcet.projetsb.repositories.FormateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class FormateurServiceImpl implements InterfFormateurService {
    @Autowired
    private FormateurRepository formateurRepository;

    @Override
    public Formateur create(Formateur formateur) throws Exception {
        formateurRepository.save(formateur);
        return formateur;
    }

    @Override
    public Formateur read(Integer id) throws Exception {
        Optional<Formateur> of = formateurRepository.findById(id);
        return of.get();
    }

    @Override
    public Formateur update(Formateur formateur) throws Exception {
        read(formateur.getIdformateur());
        formateurRepository.save(formateur);
        return formateur;
    }

    @Override
    public void delete(Formateur formateur) throws Exception {
        formateurRepository.deleteById(formateur.getIdformateur());
    }

    @Override
    public List<Formateur> all() throws Exception {
        return formateurRepository.findAll();
    }

    @Override
    public Formateur findFormateurByMail(String mail) {
        return formateurRepository.findFormateurByMail(mail);
    }

    @Override
    public List<Formateur> findFormateurByNomAndPrenom(String nom,String prenom) {
        return formateurRepository.findFormateurByNomAndPrenom(nom,prenom);
    }
}
