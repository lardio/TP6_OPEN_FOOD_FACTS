package fr.diginamic.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Additif {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected long id;

    @Column( name = "nom", nullable = false )
    protected String nom;

    @Column( name = "code", nullable = false, unique = true )
    protected String code;

    @ManyToMany
    @JoinTable(
            name = "Produits_Additifs",
            joinColumns = @JoinColumn( name = "id_additif" ),
            inverseJoinColumns = @JoinColumn( name = "id_produit" )
    )
    protected Set<Produit> produits;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
