package fr.diginamic.services;

import fr.diginamic.EntityManagerGestion;
import fr.diginamic.entity.Ingredient;
import fr.diginamic.entity.Marque;
import fr.diginamic.entity.Produit;
import fr.diginamic.repesitory.ProduitRepersitoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class ProduitService {

    private ProduitRepersitoryImpl produitRepersitory;

    public ProduitService() {
        this.produitRepersitory = new ProduitRepersitoryImpl();
    }

    public Produit getProduit(Long id) {
        Produit produit = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            produit = produitRepersitory.getById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return produit;
    }

    public Produit createProduit(Produit produit) {
        EntityTransaction tx = null;
        try {
            EntityManager em = new EntityManagerGestion().recuperationEntityManager();
            Produit check = produitRepersitory.estDejaCree(produit.getNom());
            if(Objects.isNull(check)){
                tx = em.getTransaction();
                tx.begin();
                produitRepersitory.create(produit);
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
        return produit;
    }

}
