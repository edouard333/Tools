package com.phenix.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Objet permettant de faire une connexion entre une base de donn�e
 * <code>SQL</code> et Java.<br>
 * On peut cr�er la base de donn�e via cette interface.<br>
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.1.0
 * @since 1.0
 */
public class SQL {

    /**
     * Conserve la connexion � la base de donn�e.
     */
    private Connection connexion;

    /**
     * Objet permettant d'executer les instructions <code>SQL</code>.
     */
    private Statement instruction;

    /**
     * Objet conservant les dernière résultats d'une requête
     * <code>select</code>.
     */
    private ResultSet resultat;

    /**
     * Variable contenant le nom de la base de donn�e � laquelle se connect�.
     */
    private String nom_bdd;

    /**
     * Variable avec le nom pour se loger � la base de donn�e.
     */
    private String nom = "root";

    /**
     * Variable avec le mot de passe pour se loger � la base de donn�e.
     */
    private String mdp = "naruto";

    /**
     * Cr�� un nouveau <code>SQL</code> � partir du nom de la base de donn�e.
     *
     * @param nom_bdd nom de la base de donn�e
     */
    public SQL(String nom_bdd) {
        this.nom_bdd = nom_bdd;
        Connexion();
    }

    /**
     * Cr�� un nouveau <code>SQL</code> � partir du nom de la base de donn�e,
     * d'un nom d'utilisateur et d'un mot de passe.
     *
     * @param nom_bdd nom de la base de donn�e
     * @param nom nom de l'utilisateur
     * @param mdp mot de passe de l'utilisateur
     */
    public SQL(String nom_bdd, String nom, String mdp) {
        this.nom_bdd = nom_bdd;
        this.nom = nom;
        this.mdp = mdp;
        Connexion();
    }

// == Fonction interne � la classe: ==
    /**
     * Se connecte � une base de donn�e.
     *
     * @throws SQLException S'il y a une erreur avec
     * <code>createStatement()</code>.
     */
    private void Connexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.connexion = DriverManager.getConnection("jdbc:mysql://localhost/" + this.nom_bdd, this.nom, this.mdp);

            instruction = connexion.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Force le nom de la base de donn�e.
     *
     * @param bdd nom de la base de donn�e
     */
    public void BDD(String bdd) {
        nom_bdd = bdd;
        Connexion();
    }

    /**
     * Permet de faire un select.
     *
     * @param instruction Instruction <code>select</code> a r�aliser
     */
    public void select(String instruction) {
        try {
            this.resultat = this.instruction.executeQuery(instruction);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de faire un insert.
     *
     * @param instruction Instruction <code>insert</code> a r�aliser
     */
    public void insert(String instruction) {

        try {
            this.instruction.executeUpdate(instruction);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Permet de faire un delete.
     *
     * @param instruction Instruction <code>delete</code> a r�aliser
     */
    public void delete(String instruction) {
        try {
            this.instruction.executeUpdate(instruction);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de faire un update.
     *
     * @param instruction Instruction <code>update</code> a r�aliser.
     */
    public void update(String instruction) {

        try {
            this.instruction.executeUpdate(instruction);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Met le curseur sur l'entr�e pr�c�dente.
     */
    public void previous() {
        try {
            this.resultat.previous();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// == Setter: ==
    /**
     * Force le nom de la base de donn�e.
     *
     * @param nom nom du login
     * @param mdp mot de passe du login
     */
    public void setLogin(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }

// == Getter: ==
    /**
     * Va a l'entrée suivante.
     *
     * @return <code>true</code> s'il y a encore une entrée, sinon
     * <code>false</code>.
     */
    public boolean fetch() {
        try {
            this.resultat.next();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retourne la valeur se trouvant dans � la colonne.
     *
     * @param colonne nom de la colonne choisie
     *
     * @return Une chaine de caract�re contenant la valeur de la colonne.
     */
    public String getEntree(String colonne) {
        try {
            return resultat.getString(colonne);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retourne le nombre d'entr�e.
     *
     * @return ...
     */
    public int rowCount() {
        try {
            int lignecurseur = resultat.getRow(); // Position du curseur.

            resultat.last(); // On le place � la fin.
            int nombreLignes = resultat.getRow();

            if (lignecurseur == 0) {
                resultat.beforeFirst();
            } else {
                resultat.absolute(lignecurseur);
            }

            return nombreLignes;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Erreur.
    }

    /**
     * Retourne le nombre de colonne.
     *
     * @return ...
     */
    public int getColumnCount() {
        try {
            return resultat.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Erreur.
    }

}
