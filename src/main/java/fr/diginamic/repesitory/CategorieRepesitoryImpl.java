package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategorieRepesitoryImpl {

    public void create (Categorie categorie) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(categorie);
    }

    public Categorie getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Categorie categorie = em.find(Categorie.class, id);
        return categorie;
    }

    public Categorie estDejaCree (String nom) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT c from Categorie c WHERE c.nom = ?0");
        query.setParameter(0, nom);
        query.setMaxResults(1);
        List<Categorie> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }

    public Long estDejaCree2 (String nom) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT c from Categorie c WHERE c.nom = ?0");
        query.setParameter(0, nom);
        query.setMaxResults(1);
        List<Categorie> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return 0l;
        }
        return liste.get(0).getId();
    }


}
