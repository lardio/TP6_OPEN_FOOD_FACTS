package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class IngredientRepesitoryImpl {

    public void create (Ingredient ingredient) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(ingredient);
    }

    public Ingredient getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Ingredient ingredient = em.find(Ingredient.class, id);
        return ingredient;
    }

    public Ingredient estDejaCree (String nom) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT i from Ingredient i WHERE i.nom = ?0");
        query.setParameter(0, nom);
        query.setMaxResults(1);
        List<Ingredient> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        } else {
            return liste.get(0);
        }
    }


}
