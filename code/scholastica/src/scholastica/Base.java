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
import java.sql.*;
import java.sql.DriverManager;

public class Base {

    public Connection conn;

    public void connexionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            System.out.print("Erreur de Chargement");
            System.exit(0);
        }
        try {
            String url = "jdbc:mysql://iutdoua-web.univ-lyon1.fr:3306";
            String user = "p1514568";
            String passwd = "249957";
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception ex) {
            System.out.print("Erreur de connexion à la BD");
        }
    }

    /* */
    public Connection getConnect() {

        return conn;
    }

    /* Deconnexion */
    public void Deconnexion() {

        try {
            conn.close();

        } catch (Exception e) {

        }

    }

}
