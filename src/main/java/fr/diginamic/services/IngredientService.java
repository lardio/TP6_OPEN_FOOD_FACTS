package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Ingredient;
import fr.diginamic.repesitory.IngredientRepesitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class IngredientService {

    private IngredientRepesitoryImpl ingredientRepesitory;

    public IngredientService() {
        this.ingredientRepesitory = new IngredientRepesitoryImpl();
    }

    public Ingredient getIngredient(Long id) {
        Ingredient ingredient = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            ingredient = ingredientRepesitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            Ingredient check = ingredientRepesitory.estDejaCree(ingredient.getNom());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                ingredientRepesitory.create(ingredient);
                tx.commit();
            }
            else {
                ingredient = check;
            }
        } catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            System.out.println(ingredient.getNom());
            e.printStackTrace();
        }
        return ingredient;
    }


}
