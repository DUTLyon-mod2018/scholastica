/***********************************************************************
 * Module:  Adulte.java
 * Author:  x7000328
 * Purpose: Defines the Class Enseignant
 ***********************************************************************/
package scholastica;

import java.util.*;

public class Personne {
 
   protected String nom;
   protected String prenom;
   
   public Personne (String _nom, String _prenom) {
       nom=_nom;
       prenom=_prenom;
   }
   
   public String getNom() {
       return nom;
   }
   
   public String getPrenom() {
      return prenom;
   }
   
   public void setNom(String _nom) {
         nom=_nom;
   }
   
   public void setPrenom(String _prenom) {
         prenom=_prenom;
   }
}