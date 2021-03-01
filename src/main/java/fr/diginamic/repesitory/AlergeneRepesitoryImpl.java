package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Additif;
import fr.diginamic.entity.Alergene;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AlergeneRepesitoryImpl {

    public void create (Alergene alergene) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(alergene);
    }

    public Alergene getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Alergene alergene = em.find(Alergene.class, id);
        return alergene;
    }

    public Alergene estDejaCree (String nom) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT a from Alergene a WHERE a.nom = ?0");
        query.setParameter(0, nom);
        query.setMaxResults(1);
        List<Alergene> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }

}
