package fr.m2iformation.authentification.mbeans;


import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.m2iformation.authentification.dao.IStagiaireService;
import fr.m2iformation.authentification.dao.StagiaireService;
import fr.m2iformation.authentification.entities.Stagiaire;


/**
 *
 * @author Joachim
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginManager implements Serializable {

    private static final long serialVersionUID = 1L;
	
    private final IStagiaireService service;
    private Stagiaire stagiaire;

    private String identifiant;
    private String mdp;
    private boolean isLogged;
    private boolean admin;
    private boolean premium;

    @ManagedProperty(value = "#{navigation}")
    private NavigationManager navigationManager;

    public LoginManager() {
        service = new StagiaireService();
    }

    public String doLogin() {
        String page;
        stagiaire = service.findStagiaire(identifiant, mdp);
        if (stagiaire.getId() != null) {
            isLogged = true;
            page = navigationManager.redirectToListe();
        }else{
            FacesMessage msg = new FacesMessage("Login et/ou mdp incorrect(s)", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);            
            page = navigationManager.toLogin();
        }
        return page;
    }
    
    public String doLogout(){
        isLogged = false ;
        
        identifiant = null;
        mdp = null ;
        
        FacesMessage msg = new FacesMessage("Vous êtes déconneté du système", "INFO MSG");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, msg);            
            return navigationManager.toLogin();
    }
    
    public boolean isAdmin() {
        return stagiaire.getNomRole().equalsIgnoreCase("admin");
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

	public boolean isPremium() {
		return stagiaire.getNomRole().equalsIgnoreCase("premium");
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
}
