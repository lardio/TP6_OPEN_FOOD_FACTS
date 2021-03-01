package fr.diginamic.entity;

import javax.persistence.*;

@Entity
public class Marque {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;

    @Column( name = "nom", nullable = false, unique = true )
    protected String nom;

    public Marque (String nom) {
        this.nom = nom;
    }

    public Marque() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
