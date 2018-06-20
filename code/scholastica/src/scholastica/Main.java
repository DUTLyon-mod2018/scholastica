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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Authentification auth = new Authentification();
        //CreationEleve auth = new CreationEleve();
        //CreationEnfantSimp auth = new CreationEnfantSimp(4);
        //RechercheEleve auth = new RechercheEleve() ;
        //Accueil auth = new Accueil();
        auth.setVisible(true);
        auth.setLocationRelativeTo(null);
    }

}
