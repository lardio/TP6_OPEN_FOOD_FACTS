package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Additif;
import fr.diginamic.entity.Alergene;
import fr.diginamic.repesitory.AlergeneRepesitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class AlergeneService {

    private AlergeneRepesitoryImpl alergeneRepesitory;

    public AlergeneService() {
        this.alergeneRepesitory = new AlergeneRepesitoryImpl();
    }

    public Alergene getAlergene(Long id) {
        Alergene alergene = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            alergene = alergeneRepesitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return alergene;
    }

    public Alergene createAlergene(Alergene alergene) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            Alergene check = alergeneRepesitory.estDejaCree(alergene.getNom());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                alergeneRepesitory.create(alergene);
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
        return alergene;
    }


}
