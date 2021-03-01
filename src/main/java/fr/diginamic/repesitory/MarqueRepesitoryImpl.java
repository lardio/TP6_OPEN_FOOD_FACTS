package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Alergene;
import fr.diginamic.entity.Marque;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MarqueRepesitoryImpl {

    public void create (Marque marque) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(marque);
    }

    public Marque getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Marque marque = em.find(Marque.class, id);
        return marque;
    }

    public Marque estDejaCree (String nom) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT m from Marque m WHERE m.nom = ?0");
        query.setParameter(0, nom);
        query.setMaxResults(1);
        List<Marque> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }


}
