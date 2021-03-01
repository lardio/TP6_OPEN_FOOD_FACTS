package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Produit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProduitRepersitoryImpl {

    public void create (Produit produit) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(produit);
    }

    public Produit getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Produit produit = em.find(Produit.class, id);
        return produit;
    }

    public Produit estDejaCree (String nom) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT p from Produit p WHERE p.nom = ?0");
        query.setParameter(0, nom);
        query.setMaxResults(1);
        List<Produit> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }

}
