package fr.m2iformation.authentification.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import fr.m2iformation.authentification.utils.JpaTools;



@Entity
public class Adresse extends AbstractEntity {

	private static final long serialVersionUID = -2739315046497905869L;

	private String voie;
	private String ville;

	@OneToMany(mappedBy = "adresse")
	private List<Stagiaire> stagiaires;

	public Adresse(String voie, String ville) {
		super();
		this.voie = voie;
		this.ville = ville;
	}

	public Adresse() {
		this(null, null);
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@PrePersist
	@PreUpdate
	@PostLoad
	private void initDatas() {
		ville = JpaTools.capitalize(ville);
		voie = JpaTools.capitalize(voie);
	}

	@Override
	public String toString() {
		return "Adresse [ " + voie + ", " + ville + " ]";
	}

}
