package be.condorcet.projetsb.repositories;

import be.condorcet.projetsb.modele.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormateurRepository extends JpaRepository<Formateur,Integer> {
    Formateur findFormateurByMail(String mail);

    List<Formateur> findFormateurByNomAndPrenom(String nom, String prenom);

}
