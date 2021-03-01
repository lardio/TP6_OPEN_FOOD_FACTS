package fr.diginamic.entity;

import javax.persistence.*;

@Entity
public class NutritionGrade {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected long id;

    @Column( name = "code", nullable = false, unique = true )
    protected String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String nom) {
        this.code = nom;
    }

}
