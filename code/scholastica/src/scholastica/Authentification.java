/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholastica;

/**
 *
 * @author Bilou
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/* Cette classe est l'interface d'affichage de la Connexion */
public class Authentification extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JLabel login, mdp;
    JTextField login1;
    JTextField mdp1;
    JButton valider, modifier;
    public String login2;

    public Authentification() {

        super();
        this.setTitle(" Connexion SCHOLASTICA ");
        this.setSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        login = new JLabel("Identifiant");
        login1 = new JTextField();

        mdp = new JLabel("Mot de Passe");
        mdp1 = new JPasswordField();

        valider = new JButton("Valider ");
        modifier = new JButton("Modifier mot de passe");

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        contenu.add(login);
        login.setBounds(20, 20, 100, 20);

        contenu.add(login1);
        login1.setBounds(150, 20, 150, 20);

        contenu.add(mdp);
        mdp.setBounds(20, 55, 100, 20);

        contenu.add(mdp1);
        mdp1.setBounds(150, 55, 150, 20);

        contenu.add(valider);
        valider.setBounds(100, 100, 77, 20);

        contenu.add(modifier);
        modifier.setBounds(200, 100, 82, 20);
        modifier.setSize(160, 20);

        valider.addActionListener(this);
        modifier.addActionListener(this);

        this.setVisible(true);

    }

    public String get_login() {
        return login2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Base b = new Base();
        Connection conn = null;
        ResultSet resultat;
        Statement statement;

        if (e.getSource() == valider) {

            login2 = login1.getText();
            String mdp2 = mdp1.getText();

            b.connexionBD();
            conn = b.getConnect();

            try {
                statement = conn.createStatement();

                String sql = "select mdp from p1514568.Compte where id_utilisateur ='" + login2 + "'";
                resultat = statement.executeQuery(sql);
                System.out.println(resultat);
                if (resultat.next()) {

                    String motDePasse = resultat.getString(1);
                    if (motDePasse.equals(mdp2)) {

                        Accueil acc = new Accueil();
                        acc.setVisible(true);
                        get_login();
                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(this, "Mot de passe incorrect ! ", "Error", 1);
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "Login incorrect ! ", "Error", 1);
                }

//conn.close();
            } catch (SQLException e4) {

                System.out.println(e4.getMessage());
            }

        } else {
            if (e.getSource() == modifier) {

                String login2 = login1.getText();
                String mdp2 = mdp1.getText();

                b.connexionBD();
                conn = b.getConnect();

                try {
                    statement = conn.createStatement();

                    String sql = "select mdp from p1514568.Compte where id_utilisateur ='" + login2 + "'";
                    resultat = statement.executeQuery(sql);
                    System.out.println(resultat);
                    if (resultat.next()) {

                        String motDePasse = resultat.getString(1);
                        if (motDePasse.equals(mdp2)) {

                            ModificationMdp modif_mdp = new ModificationMdp();
                            modif_mdp.setVisible(true);
                            dispose();

                        } else {

                            JOptionPane.showMessageDialog(this, "Mot de passe incorrect ! ", "Error", 1);
                        }
                    } else {

                        JOptionPane.showMessageDialog(this, "Login incorrect ! ", "Error", 1);
                    }

//conn.close();
                } catch (SQLException e4) {

                    System.out.println(e4.getMessage());
                }

            }
        }
    }
}
