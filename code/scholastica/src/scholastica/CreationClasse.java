/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholastica;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Emanuelle
 */
public class CreationClasse extends javax.swing.JFrame {
    
    String idFenetre = "CreationClasse";
    String idFenetrePrec;
    int idClasse;
    Boolean enCours = false;
    
    /**
     * Creates new form CreationClasse
     */
    public CreationClasse() {
        initComponents();
    }

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
                enCours = true;
            }
            res.close();
            stmt2.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public CreationClasse(String _idFenetrePrec, int _id_classe) {
        idClasse = _id_classe;
        idFenetrePrec = _idFenetrePrec;
        initComponents();
        this.populate(idClasse);
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

            stmt2 = conn2.prepareStatement("select e.id_adulte, nom_adulte, prenom_adulte, commentaire, titulaire from p1514568.Adulte e join p1514568.Affectation a on e.id_adulte = a.id_adulte where id_classe = ?");
            stmt2.setInt(1, idClasse);
            res2 = stmt2.executeQuery();
            
            // on crée les en-têtes du tableau
            Object[] colonneEns = new Object[5];
            colonneEns[0] = "ID";
            colonneEns[1] = "Nom";
            colonneEns[2] = "Prénom";
            colonneEns[3] = "Commentaire";
            colonneEns[4] = "Titulaire";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res2.last();
            int rowCount2 = res2.getRow();
            Object[][] dataEns = new Object[rowCount2][5];
            
            // on revient au départ
            res2.beforeFirst();
            int r2 = 0;

            // on remplit le tableau d'Object[][]
            while(res2.next()){
                for(int c = 1 ; c <= 5; c++) {
                    dataEns[r2][c-1] = res2.getObject(c);
                }
                r2++;
            }
            
            if (rowCount2 > 0) {
                tabEnseignant.setModel(new javax.swing.table.DefaultTableModel(dataEns, colonneEns));
            } else if (rowCount2 == 0) {
                tabEnseignant.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null, null, null} }, colonneEns));
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

    public void enregistrerClasse() {
        String nomClasse = tfNomClasse.getText();
        if (nomClasse.equals("") || nomClasse.equals("Temp")) {
            JOptionPane.showMessageDialog(null, "Il faut donner un nom à la classe.", "Erreur !", JOptionPane.ERROR_MESSAGE);
        } else {
            Base b = new Base();
            Connection conn = null;
            PreparedStatement statement;
            b.connexionBD();
            conn = b.getConnect();
            String sql = "update p1514568.Classe "
                        + "set nom_classe = ?"
                        + ", salle = ?"
                        + ", niveau = ?"
                        + ", annee = ?"
                        + " where id_classe = "+idClasse;
            System.out.println(sql);
            try {
                statement = conn.prepareStatement(sql);
                statement.setString(1, nomClasse);
                statement.setString(2, tfSalle.getText());
                statement.setString(3, tfNiveau.getText());
                statement.setString(4, tfAnnee.getText());
                
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void writeToCSV(List<Map> objectList) {
        String CSV_SEPARATOR = ",";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("results.csv"), "UTF-8"));
            for (Map objectDetails : objectList) {
                StringBuilder oneLine = new StringBuilder();
                Iterator it = objectDetails.values().iterator();

                while (it.hasNext()) {
                    Object value = it.next();

                    if(value !=null){
                        oneLine.append(value.toString());
                        }

                    if (it.hasNext()) {
                        oneLine.append(CSV_SEPARATOR);
                    }
                }
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            Runtime.getRuntime().exec("notepad.exe results.csv");
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    
    public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String,Object>> list = new ArrayList<>();

        while (rs.next()) {
            HashMap<String,Object> row = new HashMap<>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(row);
        }
        return list;
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
        setTitle("Création/modification de classe");

        labNomClasse.setText("* Nom");

        labSalle.setText("Salle");

        labAnnee.setText("Année scolaire");

        labNiveau.setText("Niveau");

        panEnseignant.setBorder(javax.swing.BorderFactory.createTitledBorder("Enseignants"));

        tabEnseignant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prénom", "Commentaire", "Titulaire"
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
        butSuppEnseignant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSuppEnseignantActionPerformed(evt);
            }
        });

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
        butSuppEleve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSuppEleveActionPerformed(evt);
            }
        });

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
        enregistrerClasse();
        RechercheAdulte f = new RechercheAdulte(idFenetre, idClasse);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_butAjoutEnseignantActionPerformed

    private void butAjoutEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAjoutEleveActionPerformed
        enregistrerClasse();
        RechercheEleve f = new RechercheEleve(idFenetre, idClasse);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_butAjoutEleveActionPerformed

    private void butExportListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExportListeActionPerformed
        enregistrerClasse();
        Base b = new Base();
        Connection conn = null;
        ResultSet res;
        PreparedStatement ps;
        b.connexionBD();
        conn = b.getConnect();
        String sql = "SELECT e.nom_enfant, e.prenom_enfant, e.date_naissance, ec.niveau_eleve " +
            "FROM p1514568.Enfant e " +
            "JOIN p1514568.Enfant_classe ec " +
            "ON ec.id_enfant = e.id_enfant " +
            "AND ec.id_classe = ? " +
            "ORDER BY e.nom_enfant, e.prenom_enfant;";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idClasse);
            System.out.println(ps);
            res = ps.executeQuery();
            List listeres = convertResultSetToList(res);
            RechercheClasse.writeToCSV(listeres);

            res.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_butExportListeActionPerformed

    private void butSuppEnseignantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSuppEnseignantActionPerformed
        enregistrerClasse();
        int row = tabEnseignant.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Il faut choisir un enseignant.", "Erreur !", JOptionPane.ERROR_MESSAGE);
        } else {
            Base b = new Base();
            Connection conn;
            PreparedStatement stmt;
            b.connexionBD();
            conn = b.getConnect();        

            try {
                stmt = conn.prepareStatement("delete from p1514568.Affectation where id_adulte = ? and id_classe = ?");
                stmt.setInt(1, (int)tabEnseignant.getValueAt(row,0));
                stmt.setInt(2, idClasse);
                System.out.println(stmt);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        this.populate(idClasse);
    }//GEN-LAST:event_butSuppEnseignantActionPerformed

    private void butSuppEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSuppEleveActionPerformed
        enregistrerClasse();
        int row = tabEleve.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Il faut choisir un élève.", "Erreur !", JOptionPane.ERROR_MESSAGE);
        } else {
            Base b = new Base();
            Connection conn;
            PreparedStatement stmt;
            b.connexionBD();
            conn = b.getConnect();

            try {
                stmt = conn.prepareStatement("delete from p1514568.Enfant_classe where id_enfant = ? and id_classe = ?");
                stmt.setInt(1, (int)tabEleve.getValueAt(row,0));
                stmt.setInt(2, idClasse);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        this.populate(idClasse);
    }//GEN-LAST:event_butSuppEleveActionPerformed

    private void butValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValiderActionPerformed
        enregistrerClasse();
        RechercheClasse f = new RechercheClasse();
        f.setVisible(true); 
        f.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_butValiderActionPerformed

    private void butAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAnnulerActionPerformed
        try {
            if (enCours == true) {
                Base b = new Base();
                Connection conn = null;
                PreparedStatement statement;
                b.connexionBD();
                conn = b.getConnect();
                String sql = "delete from p1514568.Classe where id_classe = "+idClasse;
                statement = conn.prepareStatement(sql);
                statement.executeUpdate();
                statement.close();                
            }
            RechercheClasse f = new RechercheClasse();
            f.setVisible(true); 
            f.setLocationRelativeTo(null);
            dispose();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_butAnnulerActionPerformed

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
