/***********************************************************************
 * Module:  Adulte.java
 * Author:  x7000328
 * Purpose: Defines the Class Adulte
 ***********************************************************************/
package scholastica;

import java.util.*;


public class Adulte extends Personne {
   
   protected int idAdulte;
   protected String profession;
   protected String adresse;
   protected String telephone;
   protected String email;
   protected String lieuTr;
   protected String telephoneTr;
   protected String horaires;
   protected boolean decede;
   
   
   public Adulte (String _nom, String _prenom) {
      super (_nom, _prenom);
   }
   
   
   public int getIdAdulte() {
      return idAdulte;
   }
   
   public String getProfession() {
      return profession;
   }
   
   public String getAdresse() {
      return adresse;
   }
   
   public String getTelephone() {
      return telephone;
   }
   
   public String getEmail() {
      return email;
   }
   
   public String getLieuTr() {
      return lieuTr;
   }
   
   public String getTelephoneTr() {
      return telephoneTr;
   }
   
   public String getHoraires() {
      return horaires;
   }
   
   public boolean getDecede() {
      return decede;
   }
   
   public void setIdAdulte(int _idAdulte) {
      idAdulte=_idAdulte;
   }
   
   public void setProfession(String _profession) {
      profession=_profession;
   }
   
   public void setAdresse(String _adresse) {
      adresse=_adresse;
   }
   
   public void setTelephone(String _telephone) {
      telephone=_telephone;
   }
   
   public void setEmail(String _email) {
      email=_email;
   }

   public void setLieuTr(String _lieuTr) {
      lieuTr=_lieuTr;
   }

   public void setTelephoneTr(String _telephoneTr) {
      telephoneTr=_telephoneTr;
   }
   
   public void setHoraires(String _horaires) {
      horaires=_horaires;
   }
   
   public void setDecede(boolean _decede) {
      decede=_decede;
   }

}