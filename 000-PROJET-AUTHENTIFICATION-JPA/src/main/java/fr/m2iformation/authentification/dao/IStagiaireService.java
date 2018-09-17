package fr.m2iformation.authentification.dao;

import java.util.List;

import fr.m2iformation.authentification.entities.Stagiaire;



public interface IStagiaireService {

    List<Stagiaire> allStagiaires();

    Stagiaire findStagiaire(Integer id);

    Stagiaire findStagiaire(String identifiant,String mdp);

    void createStagiaire(Stagiaire stagiaire);

    Stagiaire updateStagiaire(Stagiaire stagiaire);

    void removeStagiaire(Integer id);
}
