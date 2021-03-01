package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Categorie;
import fr.diginamic.repesitory.CategorieRepesitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class CategorieService {

    private CategorieRepesitoryImpl categorieRepesitory;

    public CategorieService() {
        this.categorieRepesitory = new CategorieRepesitoryImpl();
    }

    public Categorie getCategorie(Long id) {
        Categorie categorie = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            categorie = categorieRepesitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public Categorie createCategorie(Categorie categorie) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            Categorie check = categorieRepesitory.estDejaCree(categorie.getNom());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                categorieRepesitory.create(categorie);
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
        return categorie;
    }

}
