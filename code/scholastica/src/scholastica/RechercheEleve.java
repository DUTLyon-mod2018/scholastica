/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholastica;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author x7000328
 */
public class RechercheEleve extends javax.swing.JFrame {

    /**
     * Creates new form RechercheEleve
     */
    public RechercheEleve() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labNom = new javax.swing.JLabel();
        labPrenom = new javax.swing.JLabel();
        tfNom = new javax.swing.JTextField();
        tfPrenom = new javax.swing.JTextField();
        labNeEntre = new javax.swing.JLabel();
        ftfDateDebut = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        ftfDateDebut1 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabResultat = new javax.swing.JTable();
        labMesResultat = new javax.swing.JLabel();
        butRechercher = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labNom.setText("Nom");

        labPrenom.setText("Prénom");

        labNeEntre.setText("Né après ");

        ftfDateDebut.setToolTipText("");

        jLabel1.setText("et avant");

        ftfDateDebut1.setToolTipText("");

        tabResultat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Prénom", "Date de naissance"
            }
        ));
        jScrollPane1.setViewportView(tabResultat);

        butRechercher.setText("Rechercher");
        butRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRechercherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labPrenom)
                            .addComponent(labNom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNom)
                            .addComponent(tfPrenom)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labMesResultat)
                            .addComponent(butRechercher))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labNeEntre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfDateDebut, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftfDateDebut1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNom)
                    .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labPrenom)
                    .addComponent(tfPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfDateDebut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfDateDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addComponent(labNeEntre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labMesResultat)
                .addGap(18, 18, 18)
                .addComponent(butRechercher)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRechercherActionPerformed
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        
        if (nom.isEmpty() && prenom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Il faut remplir au moins un champ.", "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        
        String sql = "select id_enfant, nom_enfant, prenom_enfant, date_naissance from p1514568.Enfant where ";
        if (!nom.isEmpty()) { sql += "nom_enfant like '%" + nom + "%'"; }
        if ((!nom.isEmpty()) && (!prenom.isEmpty())) { sql += " and "; }
        if (!prenom.isEmpty()) { sql += "prenom_enfant like '%" + prenom + "%'"; }
        
        System.out.println(sql);
        
        Base b = new Base();
        Connection conn = null;
        ResultSet res;
        Statement statement;
        b.connexionBD();
        conn = b.getConnect();

        try {
            statement = conn.createStatement();
            res = statement.executeQuery(sql);
            
            // on crée les en-têtes du tableau
            Object[] colonne = new Object[4];
            colonne[0] = "ID";
            colonne[1] = "Nom";
            colonne[2] = "Prénom";
            colonne[3] = "Date de naissance";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res.last();
            int rowCount = res.getRow();
            Object[][] data = new Object[rowCount][4];
            
            // on revient au départ
            res.beforeFirst();
            int r = 0;

            // on remplit le tableau d'Object[][]
            while(res.next()){
                for(int c = 1 ; c <= 4; c++) {
                    data[r][c-1] = res.getObject(c);
                }
                r++;
            }
            
            res.close();
            statement.close();
            
            if (rowCount > 0) {
                tabResultat.setModel(new javax.swing.table.DefaultTableModel(data, colonne));
                labMesResultat.setText("La recherche a retourné "+rowCount+" résultat(s).");
            } else if (rowCount == 0) {
                tabResultat.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null, null} }, colonne));
                labMesResultat.setText("La recherche n'a retourné aucun résultat.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
}
    }//GEN-LAST:event_butRechercherActionPerformed

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
            java.util.logging.Logger.getLogger(RechercheEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RechercheEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RechercheEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RechercheEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RechercheEleve().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butRechercher;
    private javax.swing.JFormattedTextField ftfDateDebut;
    private javax.swing.JFormattedTextField ftfDateDebut1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labMesResultat;
    private javax.swing.JLabel labNeEntre;
    private javax.swing.JLabel labNom;
    private javax.swing.JLabel labPrenom;
    private javax.swing.JTable tabResultat;
    private javax.swing.JTextField tfNom;
    private javax.swing.JTextField tfPrenom;
    // End of variables declaration//GEN-END:variables
}
