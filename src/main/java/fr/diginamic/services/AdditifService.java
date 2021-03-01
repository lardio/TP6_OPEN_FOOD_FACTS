package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Additif;
import fr.diginamic.entity.Categorie;
import fr.diginamic.repesitory.AdditifRepersitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class AdditifService {

    private AdditifRepersitoryImpl additifRepersitory;

    public AdditifService() {
        this.additifRepersitory = new AdditifRepersitoryImpl();
    }

    public Additif getAdditif(Long id) {
        Additif additif = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            additif = additifRepersitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return additif;
    }

    public Additif createAdditif(Additif additif) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            Additif check = additifRepersitory.estDejaCree(additif.getCode());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                additifRepersitory.create(additif);
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
        return additif;
    }

}
