package fr.diginamic.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Produit {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected long id;

    @Column( name = "nom", nullable = false, unique = true )
    protected String nom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_categorie")
    protected Categorie categorie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_marque")
    protected Marque marque;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_nutritionGrade")
    protected NutritionGrade nutritionGrade;

    @ManyToMany
    @JoinTable(
            name = "Produits_Ingredients",
            joinColumns = @JoinColumn( name = "id_produit" ),
            inverseJoinColumns = @JoinColumn( name = "id_ingredient" )
    )
    protected Set<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(
            name = "Produits_Alergenes",
            joinColumns = @JoinColumn( name = "id_produit" ),
            inverseJoinColumns = @JoinColumn( name = "id_alergene" )
    )
    protected Set<Alergene> alergenes;

    @ManyToMany
    @JoinTable(
            name = "Produits_Additifs",
            joinColumns = @JoinColumn( name = "id_produit" ),
            inverseJoinColumns = @JoinColumn( name = "id_additif" )
    )
    protected Set<Additif> additifs;

    public Set<Alergene> getAlergenes() {
        return alergenes;
    }

    public void setAlergenes(Set<Alergene> alergenes) {
        this.alergenes = alergenes;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public NutritionGrade getNutritionGrade() {
        return nutritionGrade;
    }

    public void setNutritionGrade(NutritionGrade nutritionGrade) {
        this.nutritionGrade = nutritionGrade;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
