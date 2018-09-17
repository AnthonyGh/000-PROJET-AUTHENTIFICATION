package fr.m2iformation.authentification.test;

import java.time.LocalDate;

import fr.m2iformation.authentification.dao.IStagiaireService;
import fr.m2iformation.authentification.dao.StagiaireService;
import fr.m2iformation.authentification.entities.Adresse;
import fr.m2iformation.authentification.entities.Stagiaire;
import fr.m2iformation.authentification.utils.JpaTools;

public class ModJpaTest {

    public static void main(String[] args) {
        IStagiaireService service = new StagiaireService();

        Adresse adresse = new Adresse("bessieres", "paris");
        Stagiaire stagiaire = new Stagiaire("joachim", JpaTools.asDate(LocalDate.of(1979,3,20)), "joachim@esic.fr", "123456", "admin");
        stagiaire.setAdresse(adresse);
        service.createStagiaire(stagiaire);
        
//        Stagiaire stagiaire = service.findStagiaire("joachim@esic.fr", "123456");
//
//        System.out.println(stagiaire);
    }

}
