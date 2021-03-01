package fr.diginamic.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Alergene {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected long id;

    @Column( name = "nom", nullable = false, unique = true )
    protected String nom;

    @ManyToMany
    @JoinTable(
            name = "Produits_Alergenes",
            joinColumns = @JoinColumn( name = "id_alergene" ),
            inverseJoinColumns = @JoinColumn( name = "id_produit" )
    )
    protected Set<Produit> produits;

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
