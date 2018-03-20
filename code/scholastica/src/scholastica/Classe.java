/***********************************************************************
 * Module:  Classe.java
 * Author:  x7000328
 * Purpose: Defines the Class Classe
 ***********************************************************************/

import java.util.*;

/** @pdOid cd10ed7e-2e10-4bc0-a38a-fd41d869c5db */
public class Classe {
   /** @pdOid b4d4ce82-b22b-40b0-9ea8-eb79623e4758 */
   private String nom;
   /** @pdOid ccc13b1e-4937-465d-a9d2-87cdeb03e720 */
   private String salle;
   /** @pdOid 7ef879c5-dd32-4eea-aa01-4e301fc4cd66 */
   private String niveau;
   /** @pdOid 7fb46489-d2c6-4892-b16c-58a6a964e14e */
   private String anneeScolaire;
   /** @pdOid 252a4316-f914-4486-bd72-2671d499ab33 */
   private Vector eqEnseignante;
   /** @pdOid a59dac3c-c11d-4c94-aaf6-797cea3ffa15 */
   private Vector listeEleve;
   
   /** @pdRoleInfo migr=no name=Enseignant assc=association1 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Enseignant> gere;
   /** @pdRoleInfo migr=no name=Eleve assc=association2 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Eleve> appartient;
   
   /** @pdOid e233dc24-59e4-4f65-a1fa-bc827eb8fb0e */
   public void classe() {
      // TODO: implement
   }
   
   /** @pdOid 33dad14e-281a-43be-9368-9d3a7c6afc20 */
   public String getNom() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 9b9f4633-06fd-4a7c-8173-2e07173d44d5 */
   public String getSalle() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 49eb8ad2-4f8d-4b6c-ab84-2eed77113dc1 */
   public String getNiveau() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 0610f0e5-5afc-4175-a102-70a6045aef3c */
   public String getAnneeScolaire() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 28a8dfb7-7bcc-4ff1-8ed0-963764a26676 */
   public Vector getEqEnseignante() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid c259acbe-4191-4654-992b-d9d29d3919e3 */
   public Vector getListeEleve() {
      // TODO: implement
      return null;
   }
   
   /** @param Nom
    * @pdOid bcba8b77-33f6-47bc-961a-5f52a68ae3dd */
   public void setNom(String Nom) {
      // TODO: implement
   }
   
   /** @param Salle
    * @pdOid edc9ea40-f72c-4392-a556-20f4afe0a28b */
   public void setSalle(String Salle) {
      // TODO: implement
   }
   
   /** @param Niveau
    * @pdOid e87073b3-bdcf-477d-a27b-7968075667ef */
   public void setNiveau(String Niveau) {
      // TODO: implement
   }
   
   /** @param AnneeScolaire
    * @pdOid 276ef0a0-3016-41aa-810d-45a4f31d5c02 */
   public void setAnneeScolaire(String AnneeScolaire) {
      // TODO: implement
   }
   
   /** @param EqEnseignante
    * @pdOid 326c1ddc-5ec8-4936-83c0-aaaa86355779 */
   public void setEqEnseignante(Vector EqEnseignante) {
      // TODO: implement
   }
   
   /** @param ListeEleve
    * @pdOid b552ba4e-c093-441d-9f72-c57b7ce4d295 */
   public void setListeEleve(Vector ListeEleve) {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Enseignant> getGere() {
      if (gere == null)
         gere = new java.util.HashSet<Enseignant>();
      return gere;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorGere() {
      if (gere == null)
         gere = new java.util.HashSet<Enseignant>();
      return gere.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newGere */
   public void setGere(java.util.Collection<Enseignant> newGere) {
      removeAllGere();
      for (java.util.Iterator iter = newGere.iterator(); iter.hasNext();)
         addGere((Enseignant)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newEnseignant */
   public void addGere(Enseignant newEnseignant) {
      if (newEnseignant == null)
         return;
      if (this.gere == null)
         this.gere = new java.util.HashSet<Enseignant>();
      if (!this.gere.contains(newEnseignant))
         this.gere.add(newEnseignant);
   }
   
   /** @pdGenerated default remove
     * @param oldEnseignant */
   public void removeGere(Enseignant oldEnseignant) {
      if (oldEnseignant == null)
         return;
      if (this.gere != null)
         if (this.gere.contains(oldEnseignant))
            this.gere.remove(oldEnseignant);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllGere() {
      if (gere != null)
         gere.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Eleve> getAppartient() {
      if (appartient == null)
         appartient = new java.util.HashSet<Eleve>();
      return appartient;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppartient() {
      if (appartient == null)
         appartient = new java.util.HashSet<Eleve>();
      return appartient.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppartient */
   public void setAppartient(java.util.Collection<Eleve> newAppartient) {
      removeAllAppartient();
      for (java.util.Iterator iter = newAppartient.iterator(); iter.hasNext();)
         addAppartient((Eleve)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newEleve */
   public void addAppartient(Eleve newEleve) {
      if (newEleve == null)
         return;
      if (this.appartient == null)
         this.appartient = new java.util.HashSet<Eleve>();
      if (!this.appartient.contains(newEleve))
         this.appartient.add(newEleve);
   }
   
   /** @pdGenerated default remove
     * @param oldEleve */
   public void removeAppartient(Eleve oldEleve) {
      if (oldEleve == null)
         return;
      if (this.appartient != null)
         if (this.appartient.contains(oldEleve))
            this.appartient.remove(oldEleve);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppartient() {
      if (appartient != null)
         appartient.clear();
   }

}