package fr.m2iformation.authentification.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.m2iformation.authentification.entities.Stagiaire;
import fr.m2iformation.authentification.utils.JpaTools;

import java.io.Serializable;

public class StagiaireService implements IStagiaireService, Serializable {

    private static final long serialVersionUID = -3716383543676685650L;
	
    private final EntityManagerFactory emf;

    public StagiaireService() {
        emf = Persistence.createEntityManagerFactory("MOD-JPA-PU");
    }

    public EntityManager getEm() {
        return emf.createEntityManager();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Stagiaire> allStagiaires() {
        List<Stagiaire> stagiaires = new ArrayList<>();
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            stagiaires = em.createNamedQuery("AllStagiaires").getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return stagiaires;
    }

    @Override
    public Stagiaire findStagiaire(Integer id) {
        EntityManager em = getEm();
        Stagiaire stagiaire = new Stagiaire();
        try {
            em.getTransaction().begin();
            stagiaire = em.find(Stagiaire.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return stagiaire;
    }

    @Override
    public void createStagiaire(Stagiaire stagiaire) {
        if (!allStagiaires().contains(stagiaire)) {
            EntityManager em = getEm();
            try {
                em.getTransaction().begin();
                em.persist(stagiaire);
                em.getTransaction().commit();
            } catch (Exception e) {
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }

    @Override
    public Stagiaire updateStagiaire(Stagiaire stagiaire) {
        // TODO A faire vous meme en exercice
        return null;
    }

    @Override
    public void removeStagiaire(Integer id) {
        Stagiaire stagiaire = findStagiaire(id);
        if (stagiaire.getId() != null) {
            EntityManager em = getEm();
            try {
                em.getTransaction().begin();
                em.remove(em.merge(stagiaire));
                em.getTransaction().commit();
            } catch (Exception e) {
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }

    @Override
    public Stagiaire findStagiaire(String identifiant, String mdp) {
        EntityManager em = getEm();
        Stagiaire stagiaire = new Stagiaire();
        try {
            em.getTransaction().begin();
            stagiaire = (Stagiaire) em.createNamedQuery("FindStagiaire").
                    setParameter("identifiant", identifiant).
                    setParameter("mdp", JpaTools.hashGuava(mdp)).
                    getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return stagiaire;
    }
}
