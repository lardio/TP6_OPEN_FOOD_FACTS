package fr.diginamic.repesitory;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Marque;
import fr.diginamic.entity.NutritionGrade;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NutritionGradeRepesitoryImpl {

    public void create (NutritionGrade nutritionGrade) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        em.persist(nutritionGrade);
    }

    public NutritionGrade getById (Long id) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        NutritionGrade nutritionGrade = em.find(NutritionGrade.class, id);
        return nutritionGrade;
    }

    public NutritionGrade estDejaCree (String code) {
        EntityManager em = EntityManagerGestion.recuperationEntityManager();
        Query query = em.createQuery("SELECT n from NutritionGrade n WHERE n.code = ?0");
        query.setParameter(0, code);
        query.setMaxResults(1);
        List<NutritionGrade> liste = query.getResultList();
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }

}
