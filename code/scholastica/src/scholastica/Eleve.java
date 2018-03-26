/***********************************************************************
 * Module:  Eleve.java
 * Author:  x7000328
 * Purpose: Defines the Class Eleve
 ***********************************************************************/

import java.util.*;

public class Eleve extends Enfant {
   private String photo;
   private String lieuNaissance;
   private String nationalite;
   private Date dtInscription;
   private Date dtRadiation;
   private String adresse;
   private String telephone;
   private boolean lunettes;
   private String famille;
   private String medical;
   private String telSecu;
   private String adSecu;
   private String telAssSco;
   private String adAssSco;
   private Date vaccin;
   private String nomMedecin;
   private String telMedecin;
   private String adMedecin;
   private boolean pai;
   private int evs;
   private int avs;
   private boolean maitreE;
   private boolean maitreG;
   private boolean psy;
   private boolean mdph;
   private boolean assisSoc;
   
   /** @param _nom 
    * @param _prenom 
    * @param _dtNaissance */
   public void eleve(String _nom, String _prenom, java.util.Date _dtNaissance) {
      super(_nom, _prenom, _dtNaissance);
   }
   
   public String getPhoto() {
      return photo;
   }
   
   public String getLieuNaissance() {
      return lieuNaissance;
   }
   
   public String getNationalite() {
      return nationalite;
   }
   
   public Date getDtInscription() {
      return dtInscription;
   }
   
   public Date getDtRadiation() {
      return dtRadiation;
   }
   
   public String getAdresse() {
      return adresse;
   }
   
   public String getTelephone() {
      return telephone;
   }
   
   public boolean getLunettes() {
      return lunettes;
   }
   
   public String getFamille() {
      return famille;
   }
   
   public String getMedical() {
      return medical;
   }
   
   public String getTelSecu() {
      return telSecu;
   }
   
   public String getAdSecu() {
      return adSecu;
   }
   
   public String getTelAssSco() {
      return telAssSco;
   }
   
   public String getAdAssScp() {
      return adAssSco;
   }
   
   public Date getVaccin() {
      return vaccin;
   }
   
   public String getNomMedecin() {
      return nomMedecin;
   }
   
   public String getTelMedecin() {
      return telMedecin;
   }
   
   public String getAdMedecin() {
      return adMedecin;
   }
   
   public boolean getPai() {
      return pai;
   }
   
   public int getEvs() {
      return evs;
   }
   
   public int getAvs() {
      return avs;
   }
   
   public boolean getMaitreE() {
      return maitreE;
   }
   
   public boolean getMaitreG() {
      return maitreG;
   }
   
   public boolean getPsy() {
      return psy;
   }
   
   public boolean getMdph() {
      return mdph;
   }
   
   public boolean getAssisSoc() {
      return assisSoc;
   }
   
   /** @param _photo */
   public void setPhoto(String _photo) {
      photo = _photo;
   }
   
   /** @param _lieuNaissance */
   public void setLieuNaissance(String _lieuNaissance) {
      lieuNaissance = _lieuNaissance;
   }
   
   /** @param _nationalite */
   public void setNationalite(String _nationalite) {
      nationalite = _nationalite;
   }
   
   /** @param _dtInscription */
   public void setDtInscription(java.util.Date _dtInscription) {
      dtInscription = _dtInscription;
   }
   
   /** @param _dtRadiation */
   public void setDtRadiation(java.util.Date _dtRadiation) {
      dtRadiation = _dtRadiation;
   }
   
   /** @param _adresse */
   public void setAdresse(String _adresse) {
      adresse = _adresse;
   }
   
   /** @param _telephone */
   public void setTelephone(String _telephone) {
      telephone = _telephone;
   }
   
   /** @param _lunettes */
   public void setLunettes(boolean _lunettes) {
      lunettes = _lunettes;
   }
   
   /** @param _famille */
   public void setFamille(String _famille) {
      famille = _famille;
   }
   
   /** @param _medical */
   public void setMedical(String _medical) {
      medical = _medical;
   }
   
   /** @param _telSecu */
   public void setTelSecu(String _telSecu) {
      telSecu = _telSecu;
   }
   
   /** @param _adSecu */
   public void setAdSecu(String _adSecu) {
      adSecu = _adSecu;
   }
   
   /** @param _telAssSco */
   public void setTelAssSco(String _telAssSco) {
      telAssSco = _telAssSco;
   }
   
   /** @param _adAssSco */
   public void setAdAssSco(String _adAssSco) {
      adAssSco = _adAssSco;
   }
   
   /** @param _vaccin */
   public void setVaccin(Date _vaccin) {
      vaccin = _vaccin;
   }
   
   /** @param _nomMedecin */
   public void setNomMedecin(String _nomMedecin) {
      nomMedecin = _nomMedecin;
   }
   
   /** @param _telMedecin */
   public void setTelMedecin(String _telMedecin) {
      telMedecin = _telMedecin;
   }
   
   /** @param _adMedecin */
   public void setAdMedecin(String _adMedecin) {
      adMedecin = _adMedecin;
   }
   
   /** @param _pai */
   public void setPai(boolean _pai) {
      pai = _pai;
   }
   
   /** @param _evs */
   public void setEvs(int _evs) {
      evs = _evs;
   }
   
   /** @param _avs */
   public void setAvs(int _avs) {
      avs = _avs;
   }
   
   /** @param _maitreE */
   public void setMaitreE(boolean _maitreE) {
      maitreE = _maitreE;
   }
   
   /** @param _maitreG */
   public void setMaitreG(boolean _maitreG) {
      maitreG = _maitreG;
   }
   
   /** @param _psy */
   public void setPsy(boolean _psy) {
      psy = _psy;
   }
   
   /** @param _mdph */
   public void setMdph(boolean _mdph) {
      mdph = _mdph;
   }
   
   /** @param _assisSoc */
   public void setAssisSoc(boolean _assisSoc) {
      assisSoc = _assisSoc;
   }

}