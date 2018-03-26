/***********************************************************************
 * Module:  Enfant.java
 * Author:  x7000328
 * Purpose: Defines the Class Enfant
 ***********************************************************************/

package scholastica;

import java.util.*;

public class Enfant extends Personne {
   protected int idEnfant;
   protected java.util.Date dtNaissance;
   
   /** @param _nom 
    * @param _prenom 
    * @param _dtNaissance */
   public Enfant (String _nom, String _prenom, Date _dtNaissance) {
      super (_nom, _prenom);
      dtNaissance = _dtNaissance;
   }
   
   public int getIdEnfant() {
      return idEnfant;
   }
   
   /** @pdOid bad8c36f-e446-4fcc-b04d-d73e20812cdd */
   public java.util.Date getDtNaissance() {
      return dtNaissance;
   }
   
   /** @param _idEnfant */
   public void setIdEnfant(int _idEnfant) {
      idEnfant = _idEnfant;
   }
   
   /** @param _dtNaissance */
   public void setDtNaissance(java.util.Date _dtNaissance) {
      dtNaissance = _dtNaissance;
   }

}