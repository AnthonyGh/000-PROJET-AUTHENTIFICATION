package fr.m2iformation.authentification.mbeans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.m2iformation.authentification.dao.IStagiaireService;
import fr.m2iformation.authentification.dao.StagiaireService;
import fr.m2iformation.authentification.entities.Adresse;
import fr.m2iformation.authentification.entities.Stagiaire;

/**
 *
 * @author Joachim
 */
@ManagedBean(name = "stagiaireManager")
@RequestScoped
public class StagiaireManager implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final IStagiaireService service;
    private Stagiaire stagiaire;
    private Adresse adresse;
    private List<Stagiaire> stagiaires;
    private String titreFormStagiaire;
    
    @ManagedProperty(value = "#{navigation}")
    private NavigationManager navigationManager;
    
    public StagiaireManager() {
        service = new StagiaireService();
        stagiaire = new Stagiaire();
        stagiaires = new ArrayList<>();
        adresse = new Adresse();
    }
    
    public void setNavigationManager(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }
    
    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
    
    public Stagiaire getStagiaire() {
        return stagiaire;
    }
    
    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }
    
    public Adresse getAdresse() {
        return adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public List<Stagiaire> getStagiaires() {
        return (stagiaires != null) ? service.allStagiaires() : new ArrayList<>();
    }
    
    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }
    
    public String getTitreFormStagiaire() {
        if (stagiaire.getId() == null) {
            titreFormStagiaire = "AJOUTER STAGIAIRE";
        } else {
            titreFormStagiaire = "MODIFIER STAGIAIRE";
        }
        return titreFormStagiaire;
    }
    
    public void setTitreFormStagiaire(String titreFormStagiaire) {
        this.titreFormStagiaire = titreFormStagiaire;
    }
    
    public String pageStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
        adresse = stagiaire.getAdresse();
        return navigationManager.toStagiaire();
    }
    
    public String saveOrMerge() {
        if (stagiaire.getId() == null) {
            stagiaire.setAdresse(adresse);
            service.createStagiaire(stagiaire);
        } else {
            
        }
        return navigationManager.redirectToListe();
    }
    
    public String delete(Integer id) {
        service.removeStagiaire(id);
        return navigationManager.redirectToListe();
    }
}
