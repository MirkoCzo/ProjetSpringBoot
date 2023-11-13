package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Formateur;

import java.util.List;

public interface InterfFormateurService extends InterfService<Formateur>{

    Formateur findFormateurByMail(String mail);

    List<Formateur> findFormateurByNomAndPrenom(String nom,String prenom);
}
