package com.phenix.tools.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Objet permettant de faire une connexion entre une base de donnée
 * <code>SQL</code> et Java.<br>
 * On peut créer la base de donnée via cette interface.<br>
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.1.0
 * @since 1.0
 */
public class SQL {

    /**
     * Conserve la connexion à la base de donnée.
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
     * Variable contenant le nom de la base de donnée à laquelle se connecté.
     */
    private String nom_bdd;

    /**
     * Variable avec le nom pour se loger à la base de données.
     */
    private String nom = "root";

    /**
     * Variable avec le mot de passe pour se loger à la base de données.
     */
    private String mdp = "naruto";

    /**
     * Créé un nouveau <code>SQL</code> à partir du nom de la base de données.
     *
     * @param nom_bdd Nom de la base de données.
     */
    public SQL(String nom_bdd) {
        this.nom_bdd = nom_bdd;
        Connexion();
    }

    /**
     * Créé un nouveau <code>SQL</code> à partir du nom de la base de données,
     * d'un nom d'utilisateur et d'un mot de passe.
     *
     * @param nom_bdd Nom de la base de données
     * @param nom Nom de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     */
    public SQL(String nom_bdd, String nom, String mdp) {
        this.nom_bdd = nom_bdd;
        this.nom = nom;
        this.mdp = mdp;
        Connexion();
    }

// == Fonction interne à la classe: ==
    /**
     * Se connecte à une base de données.
     *
     * @throws SQLException S'il y a une erreur avec
     * <code>createStatement()</code>.
     */
    private void Connexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.connexion = DriverManager.getConnection("jdbc:mysql://localhost/" + this.nom_bdd, this.nom, this.mdp);

            instruction = connexion.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Force le nom de la base de données.
     *
     * @param bdd nom de la base de données.
     */
    public void BDD(String bdd) {
        nom_bdd = bdd;
        Connexion();
    }

    /**
     * Permet de faire un select.
     *
     * @param instruction Instruction <code>select</code> a réaliser.
     */
    public void select(String instruction) {
        try {
            this.resultat = this.instruction.executeQuery(instruction);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Permet de faire un insert.
     *
     * @param instruction Instruction <code>insert</code> a réaliser.
     */
    public void insert(String instruction) {

        try {
            this.instruction.executeUpdate(instruction);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Permet de faire un delete.
     *
     * @param instruction Instruction <code>delete</code> a réaliser.
     */
    public void delete(String instruction) {
        try {
            this.instruction.executeUpdate(instruction);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Permet de faire un update.
     *
     * @param instruction Instruction <code>update</code> a réaliser.
     */
    public void update(String instruction) {

        try {
            this.instruction.executeUpdate(instruction);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Met le curseur sur l'entrée précédente.
     */
    public void previous() {
        try {
            this.resultat.previous();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

// == Setter: ==
    /**
     * Force le nom de la base de donnée.
     *
     * @param nom Nom du login.
     * @param mdp Mot de passe du login.
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
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * Retourne la valeur se trouvant dans la colonne.
     *
     * @param colonne Nom de la colonne choisie.
     *
     * @return Une chaine de caractère contenant la valeur de la colonne.
     */
    public String getEntree(String colonne) {
        try {
            return resultat.getString(colonne);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Retourne le nombre d'entrée.
     *
     * @return Nombre d'entrée. Retourne <code>-1</code> en cas d'erreur.
     */
    public int rowCount() {
        try {
            int lignecurseur = resultat.getRow(); // Position du curseur.

            resultat.last(); // On le place à la fin.
            int nombreLignes = resultat.getRow();

            if (lignecurseur == 0) {
                resultat.beforeFirst();
            } else {
                resultat.absolute(lignecurseur);
            }

            return nombreLignes;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -1; // Erreur.
    }

    /**
     * Retourne le nombre de colonne.
     *
     * @return Nombre de colonne. Retourne <code>-1</code> en cas d'erreur.
     */
    public int getColumnCount() {
        try {
            return resultat.getMetaData().getColumnCount();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -1; // Erreur.
    }

}
