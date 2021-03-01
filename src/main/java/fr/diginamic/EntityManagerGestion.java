package fr.diginamic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerGestion {

    private static final ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();
    private static EntityManagerFactory entityManagerFactory = creationEntityManagerFactory();

    private static EntityManagerFactory creationEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("open_food");
    }

    public EntityManagerGestion(){
    }

    public static EntityManager recuperationEntityManager() {
        EntityManager entityManager = entityManagerThreadLocal.get();
        if (entityManager == null) {
            // Créé un nouveau si pas déjà fait, sinon renvoi le courant
            entityManager = entityManagerFactory.createEntityManager();
            entityManagerThreadLocal.set(entityManager);
        }
        return entityManager;
    }

}