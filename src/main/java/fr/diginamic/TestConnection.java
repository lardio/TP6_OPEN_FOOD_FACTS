package fr.diginamic;

import fr.diginamic.entity.*;
import fr.diginamic.services.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;


public class TestConnection {
    public static void main(String... args){
        System.out.println("test");
        MarqueService marqueService = new MarqueService();
        NutritionGradeService nutritionGradeService = new NutritionGradeService();
        AlergeneService alergeneService = new AlergeneService();
        AdditifService additifService = new AdditifService();
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        IngredientService ingredientService = new IngredientService();

        Integer count = 0;
        File file = new File("/Users/sylvain/IdeaProjects/TP6_OPEN_FOOD_FACTS/src/main/resources/open-food-facts.csv");



        try {
            List<String> lignesFichier = FileUtils.readLines(file, "UTF-8");
            lignesFichier.remove(0);
            long debut = System.currentTimeMillis();

            for(String ligne : lignesFichier) {
                Categorie categorie = null;
                Marque marque = null;
                NutritionGrade nutritionGrade = null;
                Set<Ingredient> ingredientsList = new HashSet<>();
                Set<Alergene> alergenesList = new HashSet<>();
                Set<Additif> additifsList = new HashSet<>();

                count++;
                String[] colonne = ligne.split("\\||;");

                /*
                #####################################################################################
                GESTION DES CATEGORIES
                #####################################################################################
                 */
                categorie = new Categorie();
                categorie.setNom(colonne[0]);
                categorie = categorieService.createCategorie(categorie);

                /*
                #####################################################################################
                GESTION DES INGREDIENTS
                #####################################################################################
                 */
                String stringIngredients = colonne[4].replaceAll("\\*|_", "");
                stringIngredients = stringIngredients.replaceAll("\\d+\\.*\\s*\\d*%", "");
                String[] listeIngredients = stringIngredients.split("\\.|,|;|-");
                for(String ingredientString : listeIngredients) {
                    if(ingredientString.contains(":")) {
                        String[] ingredientSansLibelle = ingredientString.split(":");
                        if(ingredientSansLibelle.length > 1) ingredientString = ingredientSansLibelle[1];
                    }
                    ingredientString = ingredientString.trim();
                    if (ingredientString.matches(".*[a-z].*")) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setNom(ingredientString);
                        ingredient = ingredientService.createIngredient(ingredient);
                        ingredientsList.add(ingredient);
                    }
                }

                /*
                #####################################################################################
                GESTION DES MARQUES
                #####################################################################################
                 */
                String[] marques = colonne[1].split(",");
                for(int i = 0; i < marques.length; i++) {
                    marque = new Marque(marques[i]);
                    marque = marqueService.createMarque(marque);
                }

                /*
                #####################################################################################
                GESTION DES NUTRITIONS
                #####################################################################################
                 */
                nutritionGrade = new NutritionGrade();
                nutritionGrade.setCode(colonne[3]);
                nutritionGrade = nutritionGradeService.createNutritionGrade(nutritionGrade);

                if(colonne.length >= 29) {
                /*
                #####################################################################################
                GESTION DES ALERGENES
                #####################################################################################
                 */
                    if(!colonne[28].isEmpty()) {
                        String[] alergenes = colonne[28].split(",|;");
                        for(int i = 0; i < alergenes.length; i++) {
                            Alergene alergene = new Alergene();
                            alergene.setNom(alergenes[i]);
                            alergene = alergeneService.createAlergene(alergene);
                            alergenesList.add(alergene);
                        }
                    }

                /*
                #####################################################################################
                GESTION DES ADDITIFS
                #####################################################################################
                 */
                    if(colonne.length >= 30 && !colonne[29].isEmpty()) {
                        String[] additifs = colonne[29].split(",|;");
                        for(int i = 0; i < additifs.length; i++) {
                            Additif additif = new Additif();
                            if(additifs[i].contains("-")) {
                                String[] additifsCodeNom = additifs[i].split("-");
                                additif.setNom(additifsCodeNom[1].trim());
                                additif.setCode(additifsCodeNom[0].trim());
                            }else {
                                additif.setNom(additifs[i].trim());
                                additif.setCode(additifs[i].trim());
                            }
                            additif = additifService.createAdditif(additif);
                            additifsList.add(additif);
                        }
                    }
                }

                /*
                #####################################################################################
                GESTION DES PRODUITS
                #####################################################################################
                 */
                Produit produit = new Produit();
                produit.setNom(colonne[2]);
                produit.setIngredients(ingredientsList);
                produit.setCategorie(categorie);
                produit.setAdditifs(additifsList);
                produit.setMarque(marque);
                produit.setNutritionGrade(nutritionGrade);
                produit.setAlergenes(alergenesList);
                produitService.createProduit(produit);

            }
            System.out.println(System.currentTimeMillis()-debut);
        }catch (Exception e) {
            System.out.println("Ligne => " +count);
            System.out.println(e.getMessage());
        }



    }

}
