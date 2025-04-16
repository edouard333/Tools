package com.phenix.tools.other;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Objet permettant de faire une connexion entre une base de donnée <em>SQL</em>
 * et Java.<br>
 * On peut créer la base de donnée via cette interface.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class SQL {

    /**
     * Conserve la connexion à la base de donnée.
     */
    private Connection connexion;

    /**
     * Objet permettant d'exécuter les instructions <em>SQL</em>.
     */
    private Statement instruction;

    /**
     * Objet conservant les derniers résultats d'une requête
     * <code>SELECT</code>.
     */
    private ResultSet resultat;

    /**
     * Variable contenant le nom de la base de donnée à laquelle se connecté.
     */
    @NotNull
    @NotBlank
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
     * Construit un objet {@link SQL} à partir du nom de la base de données.
     *
     * @param nom_bdd Nom de la base de données.
     */
    public SQL(@NotNull @NotBlank String nom_bdd) {
        this.nom_bdd = nom_bdd;
        this.connexion();
    }

    /**
     * Construit un nouveau {@link SQL} à partir du nom de la base de données,
     * d'un nom d'utilisateur et d'un mot de passe.
     *
     * @param nom_bdd Nom de la base de données
     * @param nom Nom de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     */
    public SQL(@NotNull @NotBlank String nom_bdd, String nom, String mdp) {
        this.nom_bdd = nom_bdd;
        this.nom = nom;
        this.mdp = mdp;
        this.connexion();
    }

    /**
     * Se connecte à une base de données.
     *
     * @throws SQLException S'il y a une erreur avec
     * {@link Connection#createStatement() createStatement()}.
     */
    private void connexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.connexion = DriverManager.getConnection("jdbc:mysql://localhost/" + this.nom_bdd, this.nom, this.mdp);

            this.instruction = this.connexion.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Force le nom de la base de données.
     *
     * @param nom_bdd nom de la base de données.
     */
    public void BDD(@NotNull @NotBlank String nom_bdd) {
        this.nom_bdd = nom_bdd;
        this.connexion();
    }

    /**
     * Permet de faire un select.
     *
     * @param instruction Instruction <em>SELECT</em> à réaliser.
     */
    public void select(@NotNull @NotBlank String instruction) {
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
     * @param instruction Instruction <em>INSERT</em> à réaliser.
     */
    public void insert(@NotNull @NotBlank String instruction) {
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
     * @param instruction Instruction <em>DELETE</em> à réaliser.
     */
    public void delete(@NotNull @NotBlank String instruction) {
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
     * @param instruction Instruction <em>UPDATE</em> à réaliser.
     */
    public void update(@NotNull @NotBlank String instruction) {
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

    /**
     * Va à l'entrée suivante.
     *
     * @return {@code true} s'il y a encore une entrée, sinon {@code false}.
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
    @Null
    public String getEntree(@NotNull @NotBlank String colonne) {
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
     * Retourne le nombre d'entrées.
     *
     * @return Nombre d'entrées ou {@code -1} en cas d'erreur.
     */
    public int rowCount() {
        try {
            int ligne_curseur = this.resultat.getRow(); // Position du curseur.

            this.resultat.last(); // On le place à la fin.
            int nombreLignes = this.resultat.getRow();

            if (ligne_curseur == 0) {
                this.resultat.beforeFirst();
            } else {
                this.resultat.absolute(ligne_curseur);
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
     * Retourne le nombre de colonnes.
     *
     * @return Nombre de colonnes ou {@code -1} en cas d'erreur.
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
