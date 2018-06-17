package scholastica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Bilou
 */
public class ModificationMdp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JLabel mdp_old, mdp_new1, mdp_new2;
    JTextField mdp_o;
    JTextField mdp_n1;
    JTextField mdp_n2;
    JButton valider, retour;

    public ModificationMdp() {

        super();
        this.setTitle("Modification mot de passe de");
        this.setSize(new Dimension(430, 200));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mdp_old = new JLabel("Ancien mot de passe");
        mdp_o = new JTextField();

        mdp_new1 = new JLabel("Nouveau mot de Passe");
        mdp_n1 = new JTextField();

        mdp_new2 = new JLabel("Confirmer nouveau mot de Passe");
        mdp_n2 = new JTextField();

        valider = new JButton("Valider ");
        retour = new JButton("Quitter");

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        contenu.add(mdp_old);
        mdp_old.setBounds(20, 20, 150, 20);

        contenu.add(mdp_o);
        mdp_o.setBounds(230, 20, 150, 20);

        contenu.add(mdp_new1);
        mdp_new1.setBounds(20, 55, 150, 20);

        contenu.add(mdp_n1);
        mdp_n1.setBounds(230, 55, 150, 20);

        contenu.add(mdp_new2);
        mdp_new2.setBounds(20, 90, 200, 20);

        contenu.add(mdp_n2);
        mdp_n2.setBounds(230, 90, 150, 20);

        contenu.add(valider);
        valider.setBounds(100, 125, 77, 20);

        contenu.add(retour);
        retour.setBounds(200, 125, 77, 20);

        valider.addActionListener(this);
        retour.addActionListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Base b = new Base();
        Connection conn = null;
        ResultSet resultat;
        Statement statement;

        if (e.getSource() == valider) {
            String mdp_n3 = mdp_n1.getText();
            String mdp_n4 = mdp_n2.getText();
            if (mdp_n3 == mdp_n4) {
                b.connexionBD();
                conn = b.getConnect();
                try {
                    statement = conn.createStatement();
                    String sql = "UPDATE p1514568.Compte set password = '" + mdp_new2 + "' where id_utilisateur=root";
                    resultat = statement.executeQuery(sql);
                    JOptionPane.showMessageDialog(this, "Mot de passe modifié avec succès! ", "", 1);
                } catch (SQLException e4) {
                    System.out.println(e4.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mots de passe non identiques ! ", "Error", 1);
            }
//conn.close();
        } else {
            if (e.getSource() == retour) {
                Authentification f = new Authentification();
                f.setVisible(true);
                f.setLocationRelativeTo(null);
                dispose();
            }

        }
    }
}
