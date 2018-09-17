package fr.m2iformation.authentification.mbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joachim
 */
@ManagedBean(name = "navigation")
@SessionScoped
public class NavigationManager implements Serializable {

    private static final long serialVersionUID = 1L;

	public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "/login.xhtml";
    }

    public String redirectToListe() {
        return "/admin/stagiaires.xhtml?faces-redirect=true";
    }
    
    public String toListe() {
        return "/admin/stagiaires.xhtml";
    }
    
    public String toStagiaire() {
        return "/admin/stagiaire.xhtml";
    }
}
