/***********************************************************************
 * Module:  Adulte.java
 * Author:  x7000328
 * Purpose: Defines the Class Enseignant
 ***********************************************************************/
package scholastica;

import java.util.*;


public class Enseignant extends Adulte {

   private Date dateDebut;

   private Date dateFin;
   

   public Enseignant(String _nom, String _prenom) {
       super( _nom, _prenom);
       

   }
   public Date getDateDebut() {
      return dateDebut;
   }
   public Date getDateFin() {
      return dateFin;
   }
   public void setDateDebut(Date _dateDebut) {
       dateDebut=_dateDebut;
   }
   public void setDateFin(Date _dateFin) {
       dateFin=_dateFin;
       
   }

}