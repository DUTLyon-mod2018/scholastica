/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholastica;

import java.sql.*;
import java.text.*;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author x7000328
 */
public class Creation_Adulte extends javax.swing.JFrame {

    /**
     * Creates new form CrAdulte
     */
    public Creation_Adulte() {
        initComponents();
        // ne pas afficher les infos de mise en poste si l'adulte ne fait pas
        // partie de l'équipe enseignante
        Boolean eqEns = cbEqEns.isSelected();
        if (eqEns) {
            jPanel2.setVisible(true);
        } else {
            jPanel2.setVisible(false);
        }
    }
    
    public Creation_Adulte(int _id_adulte) {
        initComponents();
        this.populate(_id_adulte);
    }
    
    public void populate(int _id_adulte) {
        Base b = new Base();
        Connection conn = null;
        ResultSet res;
        PreparedStatement statement;
        b.connexionBD();
        conn = b.getConnect();

        try {
            statement = conn.prepareStatement("select * from p1514568.Adulte where id_adulte = ?");
            statement.setInt(1, _id_adulte);
            res = statement.executeQuery();
            
            while (res.next()){     
                String nom = res.getString("nom_adulte");
                String prenom = res.getString("prenom_adulte");
                String profession = res.getString("profession");
                String adresse = res.getString("adresse_adulte");
                String telephone = res.getString("telephone");
                String email = res.getString("email");
                String lieuTr = res.getString("adresse_travail");
                String telephoneTr = res.getString("telephone_travail");   
                String horaires = res.getString("horaires");
                Boolean decede = res.getBoolean("decede");
                Boolean enseignant = res.getBoolean("enseignant");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String sDateDebut = null;
                String sDateFin = null;
                Date dateDebut = res.getDate("date_debut");
                if (null != dateDebut) {
                    sDateDebut = formatter.format(dateDebut);
                }
                Date dateFin = res.getDate("date_fin");
                if (null != dateFin) {
                    sDateFin = formatter.format(dateFin);
                }

                res.close();
                statement.close();

                tfNom.setText(nom);
                tfPrenom.setText(prenom);
                tfProfession.setText(profession);
                taAdresse.setText(adresse);
                tfTelephone.setText(telephone);
                tfEmail.setText(email);
                tfLieuTr.setText(lieuTr);
                tfTelephoneTr.setText(telephoneTr);
                tfHoraires.setText(horaires);
                cbDecede.setSelected(decede);
                cbEqEns.setSelected(enseignant);
                ftfDtDebut.setText(sDateDebut);
                ftfDtFin.setText(sDateFin);
            }

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

        jPanel1 = new javax.swing.JPanel();
        labNom = new javax.swing.JLabel();
        labPrenom = new javax.swing.JLabel();
        labProfession = new javax.swing.JLabel();
        labAdresse = new javax.swing.JLabel();
        labTelephone = new javax.swing.JLabel();
        labEmail = new javax.swing.JLabel();
        labLieuTr = new javax.swing.JLabel();
        labTelephoneTr = new javax.swing.JLabel();
        tfNom = new javax.swing.JTextField();
        tfPrenom = new javax.swing.JTextField();
        tfProfession = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfLieuTr = new javax.swing.JTextField();
        tfTelephoneTr = new javax.swing.JTextField();
        labHoraires = new javax.swing.JLabel();
        tfHoraires = new javax.swing.JTextField();
        labDecede = new javax.swing.JLabel();
        cbDecede = new javax.swing.JCheckBox();
        butValider = new javax.swing.JButton();
        butAnnuler = new javax.swing.JButton();
        cbEqEns = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        labDtDebut = new javax.swing.JLabel();
        labDtFin = new javax.swing.JLabel();
        ftfDtDebut = new javax.swing.JFormattedTextField();
        ftfDtFin = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAdresse = new javax.swing.JTextArea();
        tfTelephone = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labNom.setText("* Nom");

        labPrenom.setText("* Prénom");

        labProfession.setText("Profession");

        labAdresse.setText("Adresse");

        labTelephone.setText("Téléphone");

        labEmail.setText("Email");

        labLieuTr.setText("Lieu de travail");

        labTelephoneTr.setText("Téléphone travail");

        tfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomActionPerformed(evt);
            }
        });

        labHoraires.setText("Horaires");

        labDecede.setText("Décédé(e)");

        butValider.setText("Valider");
        butValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butValiderActionPerformed(evt);
            }
        });

        butAnnuler.setText("Annuler");
        butAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAnnulerActionPerformed(evt);
            }
        });

        cbEqEns.setText("Équipe enseignante");
        cbEqEns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEqEnsActionPerformed(evt);
            }
        });

        labDtDebut.setText("Date de mise en poste");

        labDtFin.setText("Date de fin de poste");

        ftfDtDebut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ftfDtDebut.setToolTipText("");

        ftfDtFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(labDtDebut)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labDtFin)
                        .addGap(48, 48, 48)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ftfDtFin)
                    .addComponent(ftfDtDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDtDebut)
                    .addComponent(ftfDtDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDtFin)
                    .addComponent(ftfDtFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        taAdresse.setColumns(20);
        taAdresse.setRows(5);
        jScrollPane1.setViewportView(taAdresse);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(butValider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butAnnuler))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNom)
                            .addComponent(labPrenom)
                            .addComponent(labProfession)
                            .addComponent(labAdresse)
                            .addComponent(labTelephone)
                            .addComponent(labEmail)
                            .addComponent(labLieuTr)
                            .addComponent(labTelephoneTr)
                            .addComponent(labHoraires)
                            .addComponent(labDecede))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDecede)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfTelephone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfHoraires, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(tfTelephoneTr, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfPrenom, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfProfession, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNom)
                                    .addComponent(tfEmail)
                                    .addComponent(tfLieuTr, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbEqEns)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labNom))
                    .addComponent(cbEqEns))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labPrenom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfProfession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labProfession))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labTelephone)
                                    .addComponent(tfTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labEmail)))
                            .addComponent(labAdresse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfLieuTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labLieuTr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTelephoneTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labTelephoneTr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfHoraires, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labHoraires))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labDecede)
                    .addComponent(cbDecede))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butValider)
                    .addComponent(butAnnuler))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomActionPerformed

    private void cbEqEnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEqEnsActionPerformed
        // TODO add your handling code here:
        // si coché, apparition des deux dates
        // si date de mise en  poste est renseigné, alors impossible de décocher
        Boolean eqEns = cbEqEns.isSelected();
        if (eqEns) {
            jPanel2.setVisible(true);
        } else {
            jPanel2.setVisible(false);
        }
    }//GEN-LAST:event_cbEqEnsActionPerformed

    private void butValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValiderActionPerformed
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String profession = tfProfession.getText();
        String adresse = taAdresse.getText();
        String telephone = tfTelephone.getText();
        String email = tfEmail.getText();
        String lieuTr = tfLieuTr.getText();
        String telephoneTr = tfTelephoneTr.getText();
        String horaires = tfHoraires.getText();
        Boolean decede = cbDecede.isSelected();
        Boolean eqEns = cbEqEns.isSelected();
        Date dateDebut = (Date)ftfDtDebut.getValue();
        Date dateFin = (Date)ftfDtFin.getValue();
        
        if (nom.equals("") || prenom.equals("")) {
            JOptionPane jop = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Il faut remplir au moins les champs Nom et Prénom.", "Erreur !", JOptionPane.ERROR_MESSAGE);
        } else {
            if (eqEns) {
                Enseignant a = new Enseignant(nom, prenom);
                if (!dateDebut.equals("")) {a.setDateDebut(dateDebut);}
                if (!dateFin.equals("")) {a.setDateFin(dateFin);}
            } else {
                Adulte a = new Adulte(nom, prenom);
            }
 /*           if (!profession.equals("")) {a.setProfession(profession);}
            if (!adresse.equals("")) {a.setAdresse(adresse);}
            if (!telephone.equals("")) {a.setTelephone(telephone);}
            if (!email.equals("")) {a.setEmail(email);}
            if (!lieuTr.equals("")) {a.setLieuTr(lieuTr);}
            if (!telephoneTr.equals("")) {a.setTelephoneTr(telephoneTr);}
            if (decede) {a.setDecede(true);}*/
        }
        
    }//GEN-LAST:event_butValiderActionPerformed

    private void butAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAnnulerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butAnnulerActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CrAdulte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CrAdulte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CrAdulte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CrAdulte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CrAdulte().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAnnuler;
    private javax.swing.JButton butValider;
    private javax.swing.JCheckBox cbDecede;
    private javax.swing.JCheckBox cbEqEns;
    private javax.swing.JFormattedTextField ftfDtDebut;
    private javax.swing.JFormattedTextField ftfDtFin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labAdresse;
    private javax.swing.JLabel labDecede;
    private javax.swing.JLabel labDtDebut;
    private javax.swing.JLabel labDtFin;
    private javax.swing.JLabel labEmail;
    private javax.swing.JLabel labHoraires;
    private javax.swing.JLabel labLieuTr;
    private javax.swing.JLabel labNom;
    private javax.swing.JLabel labPrenom;
    private javax.swing.JLabel labProfession;
    private javax.swing.JLabel labTelephone;
    private javax.swing.JLabel labTelephoneTr;
    private javax.swing.JTextArea taAdresse;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfHoraires;
    private javax.swing.JTextField tfLieuTr;
    private javax.swing.JTextField tfNom;
    private javax.swing.JTextField tfPrenom;
    private javax.swing.JTextField tfProfession;
    private javax.swing.JTextField tfTelephone;
    private javax.swing.JTextField tfTelephoneTr;
    // End of variables declaration//GEN-END:variables
}
