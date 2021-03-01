package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Additif;
import fr.diginamic.entity.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AdditifRepersitoryImpl {

    public void create (Additif additif) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(additif);
    }

    public Additif getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Additif additif = em.find(Additif.class, id);
        return additif;
    }

    public Additif estDejaCree (String code) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT a from Additif a WHERE a.code = ?0");
        query.setParameter(0, code);
        query.setMaxResults(1);
        List<Additif> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }


}
