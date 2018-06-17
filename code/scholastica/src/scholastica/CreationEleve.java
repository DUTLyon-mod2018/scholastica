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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author p1514568
 */
public class CreationEleve extends javax.swing.JFrame {

    int idEnfant;
    String idFenetre = "CreationEleve";

    /**
     * Creates new form Crea_eleve
     */
    public CreationEleve() {
        initComponents();
        idEnfant = -1;
       
    }

    public CreationEleve(int _idEnfant) {
        initComponents();
        idEnfant = _idEnfant;
        this.populate(idEnfant);
    }

    public void populate(int idEnfant) {
        Base b = new Base();
        Connection conn = null;
        ResultSet res;
        PreparedStatement statement;
        b.connexionBD();
        conn = b.getConnect();

        try {
            statement = conn.prepareStatement("select * from p1514568.Enfant where id_enfant = ?");
            statement.setInt(1, idEnfant);
            res = statement.executeQuery();

            while (res.next()) {
                String nom_eleve = res.getString("nom_enfant");
                String prenom_enfant = res.getString("prenom_enfant");
                String date_naissance_eleve = res.getString("date_naissance");
                String lieu_naissance_eleve = res.getString("ville_naissance");
                String nationalite_eleve = res.getString("nationalite");
                String adresse_eleve = res.getString("adresse_enfant");
                String telephone_eleve = res.getString("tel_enfant");
                String situ_famille_eleve = res.getString("situation_familiale");
                String tel_secu = res.getString("tel_secu_social");
                String adr_secu = res.getString("adr_secu_social");
                String tel_assu = res.getString("tel_assurance");
                String adr_assu = res.getString("adr_assurance");
                String nom_medecin = res.getString("nom_medecin");
                String tel_medecin = res.getString("tel_medecin");
                String adr_medecin = res.getString("adr_medecin");
                String info_medical = res.getString("infos_medicales");
                String date_rapel_antitenaq = res.getString("date_vaccin");
                Boolean lunette_eleve = res.getBoolean("port_lunettes");
                Boolean pai_eleve = res.getBoolean("pai");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String sDateDebut = null;
                String sDateFin = null;
                Date dateDebut = res.getDate("date_inscription");
                if (null != dateDebut) {
                    sDateDebut = formatter.format(dateDebut);
                }
                Date dateFin = res.getDate("date_radiation");
                if (null != dateFin) {
                    sDateFin = formatter.format(dateFin);
                }

                ch_nom_eleve.setText(nom_eleve);
                ch_prenom_enfant.setText(prenom_enfant);
                ch_naissance_eleve.setText(date_naissance_eleve);
                ch_lieu_naiss_eleve.setText(lieu_naissance_eleve);
                ch_nationalite_eleve.setText(nationalite_eleve);
                ch_adresse_eleve.setText(adresse_eleve);
                ch_tel_eleve.setText(telephone_eleve);
                ch_situ_famil_eleve.setText(situ_famille_eleve);
                ch_tel_secu.setText(tel_secu);
                ch_adr_secu.setText(adr_secu);
                ch_tel_assu.setText(tel_assu);
                ch_adr_assu.setText(adr_assu);
                ch_nom_medecin.setText(nom_medecin);
                ch_tel_medecin.setText(tel_medecin);
                ch_adr_medecin.setText(adr_medecin);
                ch_info_medical.setText(info_medical);
                ch_date_rapel_antitenaq.setText(date_rapel_antitenaq);
                ch_lunette_eleve.setSelected(lunette_eleve);
                ch_pai_eleve.setSelected(pai_eleve);
                ch_date_inscri_eleve.setText(sDateDebut);
                ch_date_radiation_eleve.setText(sDateFin);
            }
            
            //remplissage de la table Adulte
            statement = conn.prepareStatement("select nom_adulte, prenom_adulte, lien, autoriser from p1514568.Adulte a join p1514568.Responsabilite r on a.id_adulte = r.id_adulte where r.id_enfant = ?");
            statement.setInt(1, idEnfant);
            res = statement.executeQuery();
            
            // on crée les en-têtes du tableau
            Object[] colonneAd = new Object[4];
            colonneAd[0] = "Nom";
            colonneAd[1] = "Prénom";
            colonneAd[2] = "Relation";
            colonneAd[3] = "Autorisé à retirer l'enfant";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res.last();
            int rowCount = res.getRow();
            Object[][] dataAd = new Object[rowCount][4];
            
            // on revient au départ
            res.beforeFirst();
            int r = 0;

            // on remplit le tableau d'Object[][]
            while(res.next()){
                for(int c = 1 ; c <= 4; c++) {
                    dataAd[r][c-1] = res.getObject(c);
                }
                r++;
            }
            
            if (rowCount > 0) {
                tabAdulte.setModel(new javax.swing.table.DefaultTableModel(dataAd, colonneAd));
            } else if (rowCount == 0) {
                tabAdulte.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null, null} }, colonneAd));
            }
            
            //remplissage de la table fratrie
            statement = conn.prepareStatement("select nom_enfant, prenom_enfant, date_naissance from p1514568.Enfant e join p1514568.Fratrie f on e.id_enfant = f.id_enfant1 where f.id_enfant2 = ? UNION select nom_enfant, prenom_enfant, date_naissance from p1514568.Enfant e join p1514568.Fratrie f on e.id_enfant = f.id_enfant2 where f.id_enfant1 = ? order by date_naissance");
            statement.setInt(1, idEnfant);
            statement.setInt(2, idEnfant);
            res = statement.executeQuery();
            
            // on crée les en-têtes du tableau
            Object[] colonneFrat = new Object[3];
            colonneFrat[0] = "Nom";
            colonneFrat[1] = "Prénom";
            colonneFrat[2] = "Date de naissance";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res.last();
            int rowCount2 = res.getRow();
            Object[][] dataFrat = new Object[rowCount2][3];
            
            // on revient au départ
            res.beforeFirst();
            int r2 = 0;

            // on remplit le tableau d'Object[][]
            while(res.next()){
                for(int c = 1 ; c <= 3; c++) {
                    dataFrat[r2][c-1] = res.getObject(c);
                }
                r2++;
            }
            
            if (rowCount2 > 0) {
                tabFratrie.setModel(new javax.swing.table.DefaultTableModel(dataFrat, colonneFrat));
            } else if (rowCount2 == 0) {
                tabFratrie.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null} }, colonneFrat));
            }
            
            //remplissage de la table historique
            String sql = "select c.annee, ec.niveau_eleve, coalesce(a.prenom_adulte + ' ' + a.nom_adulte, '-') "
                    + "from p1514568.Enfant_classe ec "
                    + "join p1514568.Classe c on c.id_classe = ec.id_classe "
                    + "left join p1514568.Affectation af on ec.id_classe = af.id_classe and af.titulaire = 1 "
                    + "left join p1514568.Adulte a on a.id_adulte = af.id_adulte "
                    + "where ec.id_enfant = ? "
                    + "order by c.annee desc";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idEnfant);
            System.out.println(statement);
            res = statement.executeQuery();
            
            // on crée les en-têtes du tableau
            Object[] colonneHist = new Object[3];
            colonneHist[0] = "Année";
            colonneHist[1] = "Niveau";
            colonneHist[2] = "Enseignant titulaire";
            
            // on récupère le nombre de résultats pour créer l'objet + l'afficher
            res.last();
            int rowCount3 = res.getRow();
            Object[][] dataHist = new Object[rowCount3][3];
            
            // on revient au départ
            res.beforeFirst();
            int r3 = 0;

            // on remplit le tableau d'Object[][]
            while(res.next()){
                for(int c = 1 ; c <= 3; c++) {
                    dataHist[r3][c-1] = res.getObject(c);
                }
                r3++;
            }
            
            if (rowCount2 > 0) {
                tabHist.setModel(new javax.swing.table.DefaultTableModel(dataHist, colonneHist));
            } else if (rowCount2 == 0) {
                tabHist.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {null, null, null} }, colonneHist));
            }
            
            res.close();
            statement.close();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ch_nom_eleve = new javax.swing.JTextField();
        ch_prenom_enfant = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ch_lieu_naiss_eleve = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ch_nationalite_eleve = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ch_adresse_eleve = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        ch_tel_eleve = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ch_lunette_eleve = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        ch_situ_famil_eleve = new javax.swing.JTextArea();
        valider = new javax.swing.JButton();
        ret_acc_crea_ele = new javax.swing.JButton();
        ch_naissance_eleve = new javax.swing.JFormattedTextField();
        ch_date_inscri_eleve = new javax.swing.JFormattedTextField();
        ch_date_radiation_eleve = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabAdulte = new javax.swing.JTable();
        butCreerFamille = new javax.swing.JButton();
        butRechFamille = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabFratrie = new javax.swing.JTable();
        butCreerFratrie = new javax.swing.JButton();
        butRechFratrie = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ch_tel_secu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ch_adr_secu = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ch_tel_assu = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        ch_adr_assu = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        ch_nom_medecin = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        ch_tel_medecin = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ch_adr_medecin = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ch_info_medical = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        ch_date_rapel_antitenaq = new javax.swing.JFormattedTextField();
        ch_pai_eleve = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        ch_notif_evs = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        ch_notif_avs = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        ch_rased_maitree = new javax.swing.JCheckBox();
        ch_rased_maitreg = new javax.swing.JCheckBox();
        ch_rased_psy = new javax.swing.JCheckBox();
        ch_rased_mdph = new javax.swing.JCheckBox();
        ch_rased_assi_social = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabHist = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Création/modification élève");

        jLabel2.setText("Nom");

        jLabel3.setText("Prénom");

        jLabel4.setText("Date de naissance");

        jLabel5.setText("Lieu de naissance");

        jLabel6.setText("Nationalité");

        jLabel7.setText("Date d'inscription");

        jLabel8.setText("Date de radiation");

        jLabel9.setText("Adresse");

        ch_adresse_eleve.setColumns(20);
        ch_adresse_eleve.setRows(5);
        jScrollPane1.setViewportView(ch_adresse_eleve);

        jLabel10.setText("Téléphone");

        jLabel11.setText("Port de lunettes");

        this.setLocationRelativeTo(null);

        jLabel12.setText("Situation familiale");

        ch_situ_famil_eleve.setColumns(20);
        ch_situ_famil_eleve.setRows(5);
        jScrollPane9.setViewportView(ch_situ_famil_eleve);

        valider.setText("Créer élève");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        ret_acc_crea_ele.setText("Accueil");
        ret_acc_crea_ele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ret_acc_crea_eleActionPerformed(evt);
            }
        });

        jButton2.setText("Annuler");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Famille et autres adultes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabAdulte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Relation", "Autorisé à retirer l'enfant"
            }
        ));
        jScrollPane3.setViewportView(tabAdulte);

        butCreerFamille.setText("Créer");
        butCreerFamille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCreerFamilleActionPerformed(evt);
            }
        });

        butRechFamille.setText("Rechercher");
        butRechFamille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRechFamilleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(butCreerFamille)
                        .addGap(18, 18, 18)
                        .addComponent(butRechFamille)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butCreerFamille)
                    .addComponent(butRechFamille))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fratrie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabFratrie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Date de naissance"
            }
        ));
        jScrollPane2.setViewportView(tabFratrie);

        butCreerFratrie.setText("Créer");
        butCreerFratrie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCreerFratrieActionPerformed(evt);
            }
        });

        butRechFratrie.setText("Rechercher");
        butRechFratrie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRechFratrieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(butCreerFratrie)
                        .addGap(18, 18, 18)
                        .addComponent(butRechFratrie)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butCreerFratrie)
                    .addComponent(butRechFratrie))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ch_tel_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(28, 28, 28)
                                .addComponent(ch_date_inscri_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ch_date_radiation_eleve, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ch_nom_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ch_prenom_enfant, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ch_naissance_eleve, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                        .addComponent(ch_lieu_naiss_eleve)
                                        .addComponent(ch_nationalite_eleve))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ch_lunette_eleve)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(valider)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(669, 669, 669)
                        .addComponent(ret_acc_crea_ele)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_nom_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ch_naissance_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(ch_prenom_enfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_lieu_naiss_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_nationalite_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ch_date_inscri_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ch_date_radiation_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(ch_tel_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ch_lunette_eleve)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valider)
                            .addComponent(jButton2)
                            .addComponent(ret_acc_crea_ele)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fiche élève", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Centre de Sécurité sociale", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel13.setText("Téléphone");

        jLabel14.setText("Adresse");

        ch_adr_secu.setColumns(20);
        ch_adr_secu.setRows(5);
        jScrollPane4.setViewportView(ch_adr_secu);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(ch_tel_secu))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(ch_tel_secu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assurance scolaire", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel16.setText("Téléphone");

        jLabel17.setText("Adresse");

        ch_adr_assu.setColumns(20);
        ch_adr_assu.setRows(5);
        jScrollPane5.setViewportView(ch_adr_assu);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(ch_tel_assu))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ch_tel_assu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Médecin traitant", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel18.setText("Nom");

        jLabel19.setText("Téléphone");

        jLabel20.setText("Adresse");

        ch_adr_medecin.setColumns(20);
        ch_adr_medecin.setRows(5);
        jScrollPane6.setViewportView(ch_adr_medecin);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(ch_tel_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ch_nom_medecin))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(ch_nom_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(ch_tel_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations médicales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        ch_info_medical.setColumns(20);
        ch_info_medical.setRows(5);
        jScrollPane7.setViewportView(ch_info_medical);

        jLabel22.setText("Dernier rappel antiténanique");

        ch_pai_eleve.setText("PAI");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ch_date_rapel_antitenaq)))
                    .addComponent(ch_pai_eleve, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(ch_date_rapel_antitenaq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_pai_eleve)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations pédagogiques", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel25.setText("Notification EVS");

        ch_notif_evs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3h", "6h", "9h", "12h" }));
        ch_notif_evs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_notif_evsItemStateChanged(evt);
            }
        });
        ch_notif_evs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_notif_evsActionPerformed(evt);
            }
        });

        jLabel26.setText("Notification AVS");

        ch_notif_avs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3h", "6h", "9h", "12h" }));

        jLabel27.setText("RASED");

        ch_rased_maitree.setText("Maître E");

        ch_rased_maitreg.setText("Maître G");

        ch_rased_psy.setText("Psychologue");

        ch_rased_mdph.setText("Suivi MDPH");
        ch_rased_mdph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_rased_mdphActionPerformed(evt);
            }
        });

        ch_rased_assi_social.setText("Assistante sociale");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_rased_mdph)
                    .addComponent(jLabel27)
                    .addComponent(ch_rased_psy)
                    .addComponent(ch_rased_assi_social)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(ch_rased_maitree)
                            .addComponent(ch_rased_maitreg)
                            .addComponent(jLabel25))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ch_notif_evs, 0, 101, Short.MAX_VALUE)
                            .addComponent(ch_notif_avs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(ch_notif_evs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(ch_notif_avs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_rased_maitree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_rased_maitreg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_rased_mdph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_rased_psy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_rased_assi_social)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(311, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informations médicales et pédagogiques", jPanel2);

        tabHist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Année", "Niveau", "Enseignant titulaire"
            }
        ));
        jScrollPane8.setViewportView(tabHist);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 193, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Historique", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ch_rased_mdphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_rased_mdphActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_rased_mdphActionPerformed

    private void ch_notif_evsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_notif_evsActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_ch_notif_evsActionPerformed

    private void ch_notif_evsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_notif_evsItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_ch_notif_evsItemStateChanged

    private void ret_acc_crea_eleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ret_acc_crea_eleActionPerformed
        // TODO add your handling code here:
        Accueil acc = new Accueil();
        acc.setVisible(true);
        acc.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_ret_acc_crea_eleActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        // TODO add your handling code here:
        String nom_enfant = ch_nom_eleve.getText();
        String prenom_enfant = ch_prenom_enfant.getText();

        String lieu_naiss_eleve = ch_lieu_naiss_eleve.getText();
        String nation_eleve = ch_nationalite_eleve.getText();
        java.util.Date DateNaissance = (Date) ch_naissance_eleve.getValue();
        java.sql.Date sqlDateNaissance = null;
        if (null != DateNaissance)
        {
            sqlDateNaissance = new java.sql.Date(DateNaissance.getTime());
        }
        String adresse_eleve = ch_adresse_eleve.getText();
        String tel_eleve = ch_tel_eleve.getText();
        String sit_famille_eleve = ch_situ_famil_eleve.getText();
        String tel_secu = ch_tel_secu.getText();
        String adr_secu = ch_adr_secu.getText();
        String tel_assu = ch_tel_assu.getText();
        String adr_assu = ch_adr_assu.getText();
        String nom_medecin = ch_nom_medecin.getText();
        String tel_medecin = ch_tel_medecin.getText();
        String adr_medecin = ch_adr_medecin.getText();
        String info_medical = ch_info_medical.getText();
        java.util.Date DateVaccin = (Date) ch_date_rapel_antitenaq.getValue();
        java.sql.Date sqlDateVaccin = null;
        if (null != DateVaccin)
        {
            sqlDateVaccin = new java.sql.Date(DateVaccin.getTime());
        }
        java.util.Date DateDebut = (Date) ch_date_inscri_eleve.getValue();
        java.sql.Date sqlDateDebut = null;
        if (null != DateDebut)
        {
            sqlDateDebut = new java.sql.Date(DateDebut.getTime());
        }
        java.util.Date DateFin = (Date) ch_date_radiation_eleve.getValue();
        java.sql.Date sqlDateFin = null;
        if (null != DateFin)
        {
            sqlDateFin = new java.sql.Date(DateFin.getTime());
        }

        if (nom_enfant.equals("") || prenom_enfant.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Il faut remplir au moins les champs Nom et Prénom.", "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Base b = new Base();
            Connection conn = null;
            PreparedStatement statement;
            b.connexionBD();
            conn = b.getConnect();
            String sql = "";

            if (idEnfant < 0)
            {
                sql = "insert into p1514568.Enfant "
                + "(nom_enfant, prenom_enfant, date_naissance, ville_naissance, nationalite, adresse_enfant, tel_enfant, situation_familiale, tel_secu_social, adr_secu_social, tel_assurance, adr_assurance, nom_medecin, tel_medecin, adr_medecin, infos_medicales, date_vaccin, port_lunettes, pai,  date_inscription, date_radiation ) values "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?)";
            }
            else
            {
                sql = "update p1514568.Enfant "
                + "set nom_enfant = ?"
                + ", prenom_enfant = ?"
                + ", date_naissance = ?"
                + ", ville_naissance = ?"
                + ", nationalite = ?"
                + ", adresse_enfant = ?"
                + ", tel_enfant = ?"
                + ", situation_familiale = ?"
                + ", tel_secu_social = ?"
                + ", adr_secu_social = ?"
                + ", tel_assurance = ?"
                + ", adr_assurance = ?"
                + ", nom_medecin = ?"
                + ", tel_medecin = ?"
                + ", adr_medecin = ?"
                + ", infos_medicales = ?"
                + ", date_vaccin = ?"
                + ", port_lunettes = ?"
                + ", pai = ?"
                + ", date_inscription = ?"
                + ", date_radiation = ?"
                + " where id_enfant = " + idEnfant;
            }
            System.out.println(sql);
            try
            {
                statement = conn.prepareStatement(sql);
                statement.setString(1, nom_enfant);
                statement.setString(2, prenom_enfant);
                statement.setDate(3, sqlDateNaissance);
                statement.setString(4, lieu_naiss_eleve);
                statement.setString(5, nation_eleve);
                statement.setString(6, adresse_eleve);
                statement.setString(7, tel_eleve);
                statement.setString(8, sit_famille_eleve);
                statement.setString(9, tel_secu);
                statement.setString(10, adr_secu);
                statement.setString(11, tel_assu);
                statement.setString(12, adr_assu);
                statement.setString(13, nom_medecin);
                statement.setString(14, tel_medecin);
                statement.setString(15, adr_medecin);
                statement.setString(16, info_medical);
                statement.setDate(17, sqlDateVaccin);
                statement.setBoolean(18, ch_lunette_eleve.isSelected());
                statement.setBoolean(19, ch_pai_eleve.isSelected());
                statement.setDate(20, sqlDateDebut);
                statement.setDate(21, sqlDateFin);

                statement.executeUpdate();
                statement.close();

                /*RechercheAdulte w = new RechercheAdulte();
                w.setVisible(true);
                dispose();*/

            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_validerActionPerformed

    private void butCreerFratrieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCreerFratrieActionPerformed
        CreationEnfantSimp w = new CreationEnfantSimp(idEnfant);
        w.setVisible(true); 
        dispose();
    }//GEN-LAST:event_butCreerFratrieActionPerformed

    private void butRechFratrieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRechFratrieActionPerformed
        RechercheEleve w = new RechercheEleve(idFenetre, idEnfant);
        w.setVisible(true); 
        dispose();
    }//GEN-LAST:event_butRechFratrieActionPerformed

    private void butRechFamilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRechFamilleActionPerformed
        RechercheAdulte w = new RechercheAdulte(idFenetre, idEnfant);
        w.setVisible(true); 
        dispose();
    }//GEN-LAST:event_butRechFamilleActionPerformed

    private void butCreerFamilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCreerFamilleActionPerformed
        CreationAdulte w = new CreationAdulte(idFenetre, idEnfant);
        w.setVisible(true); 
        dispose();    }//GEN-LAST:event_butCreerFamilleActionPerformed

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
            java.util.logging.Logger.getLogger(CreationEleve.class


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        





} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreationEleve.class


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        





} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreationEleve.class


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        





} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreationEleve.class


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreationEleve().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butCreerFamille;
    private javax.swing.JButton butCreerFratrie;
    private javax.swing.JButton butRechFamille;
    private javax.swing.JButton butRechFratrie;
    private javax.swing.JTextArea ch_adr_assu;
    private javax.swing.JTextArea ch_adr_medecin;
    private javax.swing.JTextArea ch_adr_secu;
    private javax.swing.JTextArea ch_adresse_eleve;
    private javax.swing.JFormattedTextField ch_date_inscri_eleve;
    private javax.swing.JFormattedTextField ch_date_radiation_eleve;
    private javax.swing.JFormattedTextField ch_date_rapel_antitenaq;
    private javax.swing.JTextArea ch_info_medical;
    private javax.swing.JTextField ch_lieu_naiss_eleve;
    private javax.swing.JCheckBox ch_lunette_eleve;
    private javax.swing.JFormattedTextField ch_naissance_eleve;
    private javax.swing.JTextField ch_nationalite_eleve;
    private javax.swing.JTextField ch_nom_eleve;
    private javax.swing.JTextField ch_nom_medecin;
    private javax.swing.JComboBox<String> ch_notif_avs;
    private javax.swing.JComboBox<String> ch_notif_evs;
    private javax.swing.JCheckBox ch_pai_eleve;
    private javax.swing.JTextField ch_prenom_enfant;
    private javax.swing.JCheckBox ch_rased_assi_social;
    private javax.swing.JCheckBox ch_rased_maitree;
    private javax.swing.JCheckBox ch_rased_maitreg;
    private javax.swing.JCheckBox ch_rased_mdph;
    private javax.swing.JCheckBox ch_rased_psy;
    private javax.swing.JTextArea ch_situ_famil_eleve;
    private javax.swing.JTextField ch_tel_assu;
    private javax.swing.JTextField ch_tel_eleve;
    private javax.swing.JTextField ch_tel_medecin;
    private javax.swing.JTextField ch_tel_secu;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton ret_acc_crea_ele;
    private javax.swing.JTable tabAdulte;
    private javax.swing.JTable tabFratrie;
    private javax.swing.JTable tabHist;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}
