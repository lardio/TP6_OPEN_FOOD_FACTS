package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.NutritionGrade;
import fr.diginamic.entity.Produit;
import fr.diginamic.repesitory.NutritionGradeRepesitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class NutritionGradeService {

    private NutritionGradeRepesitoryImpl nutritionGradeRepesitory;

    public NutritionGradeService() {
        this.nutritionGradeRepesitory = new NutritionGradeRepesitoryImpl();
    }

    public NutritionGrade getNutritionGrade(Long id) {
        NutritionGrade nutritionGrade = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            nutritionGrade = nutritionGradeRepesitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return nutritionGrade;
    }

    public NutritionGrade createNutritionGrade(NutritionGrade nutritionGrade) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            NutritionGrade check = nutritionGradeRepesitory.estDejaCree(nutritionGrade.getCode());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                nutritionGradeRepesitory.create(nutritionGrade);
                tx.commit();
            }
            else {
                return check;
            }
        } catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return nutritionGrade;
    }

}
