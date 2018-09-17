package fr.m2iformation.authentification.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import fr.m2iformation.authentification.utils.JpaTools;



@Entity
@NamedQueries({
    @NamedQuery(name = "AllStagiaires", query = "SELECT s FROM Stagiaire s"),
    @NamedQuery(name = "FindStagiaire", query = "SELECT s FROM Stagiaire s WHERE s.identifiant = :identifiant AND s.mdp = :mdp")
})
public class Stagiaire extends AbstractEntity {

    private static final long serialVersionUID = -5169886593125679503L;

    @Column(nullable = false, unique = true, length = 30)
    private String prenom;

    @Temporal(value = TemporalType.DATE)
    private Date ddn;

    @Transient
    private Integer age;

    @Column(nullable = false, unique = true)
    private String identifiant;

    private String mdp;

    @Column(nullable = false)
    private String nomRole;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Adresse adresse;

    public Stagiaire(String prenom, Date ddn, String identifiant, String mdp, String nomRole) {
        this.prenom = prenom;
        this.ddn = ddn;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.nomRole = nomRole;
        adresse = new Adresse();
    }

    public Stagiaire() {
        this(null, null, null, null, null);
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifiant == null) ? 0 : identifiant.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Stagiaire other = (Stagiaire) obj;
        if (identifiant == null) {
            if (other.identifiant != null) {
                return false;
            }
        } else if (!identifiant.equalsIgnoreCase(other.identifiant)) {
            return false;
        }
        return true;
    }

    @PrePersist
    private void beforePersist() {
        prenom = JpaTools.capitalize(prenom);
        identifiant = identifiant.trim().toLowerCase();
        nomRole = nomRole.trim().toUpperCase();
        mdp = JpaTools.hashGuava(mdp);
    }

    @PreUpdate
    private void beforeUpdate() {
        prenom = JpaTools.capitalize(prenom);
        identifiant = identifiant.trim().toLowerCase();
        nomRole = nomRole.trim().toUpperCase();
    }

    @PostLoad
    private void beforeLoad() {
        age = JpaTools.calculAge(ddn);
    }

    @Override
    public String toString() {
        return "Stagiaire [ " + getId() + ", " + prenom + ", " + JpaTools.format(JpaTools.asLocalDate(ddn)) + ", " + age
                + " ans, " + identifiant + ", " + nomRole + ", " + adresse + "]";
    }
}
