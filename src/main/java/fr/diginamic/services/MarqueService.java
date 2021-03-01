package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Alergene;
import fr.diginamic.entity.Marque;
import fr.diginamic.repesitory.MarqueRepesitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class MarqueService {

    private MarqueRepesitoryImpl marqueRepesitory;

    public MarqueService() { this.marqueRepesitory = new MarqueRepesitoryImpl(); };

    public Marque getMarque(Long id) {
        Marque marque = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            marque = marqueRepesitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return marque;
    }

    public Marque createMarque(Marque marque) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            Marque check = marqueRepesitory.estDejaCree(marque.getNom());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                marqueRepesitory.create(marque);
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
        return marque;
    }


}
