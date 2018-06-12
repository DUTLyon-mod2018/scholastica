/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholastica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Emanuelle
 */
public class CreationClasse extends javax.swing.JFrame {
    
    String idFenetre = "CreationClasse";
    int idClasse;
    
    /**
     * Creates new form CreationClasse
     */
    public CreationClasse(String _idFenetrePrec) {
        initComponents();
        // créer une classe temporaire dans la base
        Base b = new Base();
        Connection conn = null;
        ResultSet res;
        PreparedStatement stmt1;
        PreparedStatement stmt2;
        b.connexionBD();
        conn = b.getConnect();

        try {
            stmt1 = conn.prepareStatement("insert into p1514568.Classe (nom_classe, niveau, annee) values ('Temp', 'Temp', 'Temp')");
            stmt1.executeUpdate();
            stmt1.close();
            stmt2 = conn.prepareStatement("select max(id_classe) as idclasse from p1514568.Classe where nom_classe = 'Temp'");
            res = stmt2.executeQuery();
            
            while (res.next()){     
                idClasse = res.getInt("idclasse");
            }
            res.close();
            stmt2.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public CreationClasse(String _idFenetrePrec, int _id_classe) {
        idClasse = _id_classe;
        String idFenetrePrec = _idFenetrePrec;
        initComponents();
        this.populate(idClasse);
    }

    public CreationClasse(String _idFenetrePrec, int _id_classe, int _id_personne) {
        idClasse = _id_classe;
        int idPersonne = _id_personne;
        String idFenetrePrec = _idFenetrePrec;
        initComponents();
        this.populate(idClasse);
        // suivant l'id de la fenêtre précédente, il va falloir créer des liens dans la base
    }

    public void populate(int idClasse) {
        Base b1 = new Base();
        Base b2 = new Base();
        Base b3 = new Base();
        Connection conn1, conn2, conn3;
        ResultSet res1, res2, res3;
        PreparedStatement stmt1, stmt2, stmt3;
        b1.connexionBD();
        b2.connexionBD();
        b3.connexionBD();
        conn1 = b1.getConnect();
        conn2 = b2.getConnect();
        conn3 = b3.getConnect();

        try {
            stmt1 = conn1.prepareStatement("select * from p1514568.Classe where id_classe = ?");
            stmt1.setInt(1, idClasse);
            res1 = stmt1.executeQuery();
            
            while (res1.next()){     
                String nom_classe = res1.getString("nom_classe");
                String salle = res1.getString("salle");
                String niveau = res1.getString("niveau");
                String annee = res1.getString("annee");

                tfNomClasse.setText(nom_classe);
                tfSalle.setText(salle);
                tfNiveau.setText(niveau);
                tfAnnee.setText(annee);
            }

            stmt2 = conn2.prepareStatement("select e.id_adulte, nom_adulte, prenom_adulte, commentaire from p1514568.Adulte e join p1514568.Affectation a on e.id_adulte = a.id_adulte where id_classe = ?");
            stmt2.setInt(1, idClasse);
            res2 = stmt2.executeQuery();
            
            // on crée les en-têtes du tableau
            Object[] colonneEns = new Object[4];
            colonneEns[0] = "ID";
            colonneEns[1] = "Nom";
            colonneEns[2] = "Prénom";
            colonneEns[3] = "Commentaire";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res2.last();
            int rowCount2 = res2.getRow();
            Object[][] dataEns = new Object[rowCount2][4];
            
            // on revient au départ
            res2.beforeFirst();
            int r2 = 0;

            // on remplit le tableau d'Object[][]
            while(res2.next()){
                for(int c = 1 ; c <= 4; c++) {
                    dataEns[r2][c-1] = res2.getObject(c);
                }
                r2++;
            }
            
            if (rowCount2 > 0) {
                tabEnseignant.setModel(new javax.swing.table.DefaultTableModel(dataEns, colonneEns));
            } else if (rowCount2 == 0) {
                tabEnseignant.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null, null} }, colonneEns));
            }

            stmt3 = conn3.prepareStatement("select e.id_enfant, nom_enfant, prenom_enfant, date_naissance, niveau_eleve from p1514568.Enfant e join p1514568.Enfant_classe a on e.id_enfant = a.id_enfant where id_classe = ?");
            stmt3.setInt(1, idClasse);
            res3 = stmt3.executeQuery();
            
            // on crée les en-têtes du tableau
            Object[] colonneElv = new Object[5];
            colonneElv[0] = "ID";
            colonneElv[1] = "Nom";
            colonneElv[2] = "Prénom";
            colonneElv[3] = "Date de naissance";
            colonneElv[4] = "Niveau";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res3.last();
            int rowCount3 = res3.getRow();
            Object[][] dataElv = new Object[rowCount3][5];
            
            // on revient au départ
            res3.beforeFirst();
            int r3 = 0;

            // on remplit le tableau d'Object[][]
            while(res3.next()){
                for(int c = 1 ; c <= 5; c++) {
                    dataElv[r3][c-1] = res3.getObject(c);
                }
                r3++;
            }
            
            if (rowCount3 > 0) {
                tabEleve.setModel(new javax.swing.table.DefaultTableModel(dataElv, colonneElv));
            } else if (rowCount3 == 0) {
                tabEleve.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null, null, null} }, colonneElv));
            }
            
            res1.close();
            stmt1.close();
            res2.close();
            stmt2.close();
            res3.close();
            stmt3.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labNomClasse = new javax.swing.JLabel();
        labSalle = new javax.swing.JLabel();
        tfNomClasse = new javax.swing.JTextField();
        labAnnee = new javax.swing.JLabel();
        tfAnnee = new javax.swing.JTextField();
        tfSalle = new javax.swing.JTextField();
        labNiveau = new javax.swing.JLabel();
        tfNiveau = new javax.swing.JTextField();
        panEnseignant = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabEnseignant = new javax.swing.JTable();
        butAjoutEnseignant = new javax.swing.JButton();
        butSuppEnseignant = new javax.swing.JButton();
        panEleve = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabEleve = new javax.swing.JTable();
        butAjoutEleve = new javax.swing.JButton();
        butSuppEleve = new javax.swing.JButton();
        butExportListe = new javax.swing.JButton();
        butValider = new javax.swing.JButton();
        butAnnuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labNomClasse.setText("* Nom");

        labSalle.setText("Salle");

        labAnnee.setText("Année scolaire");

        labNiveau.setText("Niveau");

        panEnseignant.setBorder(javax.swing.BorderFactory.createTitledBorder("Enseignants"));

        tabEnseignant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prénom", "Commentaire"
            }
        ));
        jScrollPane2.setViewportView(tabEnseignant);

        butAjoutEnseignant.setText("Ajouter enseignant");
        butAjoutEnseignant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAjoutEnseignantActionPerformed(evt);
            }
        });

        butSuppEnseignant.setText("Supprimer enseignant");

        javax.swing.GroupLayout panEnseignantLayout = new javax.swing.GroupLayout(panEnseignant);
        panEnseignant.setLayout(panEnseignantLayout);
        panEnseignantLayout.setHorizontalGroup(
            panEnseignantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEnseignantLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panEnseignantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(panEnseignantLayout.createSequentialGroup()
                        .addComponent(butAjoutEnseignant)
                        .addGap(18, 18, 18)
                        .addComponent(butSuppEnseignant)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panEnseignantLayout.setVerticalGroup(
            panEnseignantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEnseignantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panEnseignantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butAjoutEnseignant)
                    .addComponent(butSuppEnseignant))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panEleve.setBorder(javax.swing.BorderFactory.createTitledBorder("Élèves"));

        tabEleve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prénom", "Date de naissance", "Niveau"
            }
        ));
        jScrollPane1.setViewportView(tabEleve);

        butAjoutEleve.setText("Ajouter élève");
        butAjoutEleve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAjoutEleveActionPerformed(evt);
            }
        });

        butSuppEleve.setText("Supprimer élève");

        butExportListe.setText("Exporter en CSV");
        butExportListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExportListeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panEleveLayout = new javax.swing.GroupLayout(panEleve);
        panEleve.setLayout(panEleveLayout);
        panEleveLayout.setHorizontalGroup(
            panEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEleveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panEleveLayout.createSequentialGroup()
                        .addComponent(butAjoutEleve)
                        .addGap(18, 18, 18)
                        .addComponent(butSuppEleve)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butExportListe)))
                .addContainerGap())
        );
        panEleveLayout.setVerticalGroup(
            panEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEleveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panEleveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butAjoutEleve)
                    .addComponent(butSuppEleve)
                    .addComponent(butExportListe))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        butValider.setText("Valider");

        butAnnuler.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labNomClasse)
                                    .addComponent(labSalle))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNomClasse)
                                    .addComponent(tfSalle, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labNiveau)
                                .addGap(18, 18, 18)
                                .addComponent(tfNiveau, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labAnnee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panEleve, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panEnseignant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butValider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butAnnuler)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNomClasse)
                    .addComponent(tfNomClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labAnnee)
                    .addComponent(tfAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labSalle)
                    .addComponent(tfSalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNiveau)
                    .addComponent(tfNiveau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panEnseignant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panEleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butValider)
                    .addComponent(butAnnuler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butAjoutEnseignantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAjoutEnseignantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butAjoutEnseignantActionPerformed

    private void butAjoutEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAjoutEleveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butAjoutEleveActionPerformed

    private void butExportListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExportListeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butExportListeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreationClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreationClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreationClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreationClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String idFenetrePrec = null;
                new CreationClasse(idFenetrePrec).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAjoutEleve;
    private javax.swing.JButton butAjoutEnseignant;
    private javax.swing.JButton butAnnuler;
    private javax.swing.JButton butExportListe;
    private javax.swing.JButton butSuppEleve;
    private javax.swing.JButton butSuppEnseignant;
    private javax.swing.JButton butValider;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labAnnee;
    private javax.swing.JLabel labNiveau;
    private javax.swing.JLabel labNomClasse;
    private javax.swing.JLabel labSalle;
    private javax.swing.JPanel panEleve;
    private javax.swing.JPanel panEnseignant;
    private javax.swing.JTable tabEleve;
    private javax.swing.JTable tabEnseignant;
    private javax.swing.JTextField tfAnnee;
    private javax.swing.JTextField tfNiveau;
    private javax.swing.JTextField tfNomClasse;
    private javax.swing.JTextField tfSalle;
    // End of variables declaration//GEN-END:variables
}
