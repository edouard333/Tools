package com.phenix.tools;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Sert à gérer une chaîne de caractère.<br>
 * <br>
 * Version 1.12: <br>
 * Ajout de documentation formaté sur la JavaDoc.
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.12.0
 * @since Kit objet V1
 */
public final class String2 {

    /**
     * La cha�ne de caract�re standard qui va subir les traitements.
     */
    private String variable;

    /**
     * Variable qui va servir pour fragmenter la cha�ne de caract�re.
     */
    private ArrayList<String> morceau;
    /**
     * Variable contenant le nombre de morceau, si on d�compose texte.
     */
    private int nombre_morceau;
    /**
     * Le s�parateur, si utilis�.
     */
    private String separateur;

    /* -- Constructors -- */
    /**
     * Permet cr�er une instance de "String2" sans donner de param�tre.
     */
    public String2() {
    }

    /**
     * Demande une chaine de caract�re qui sera affecter � variable.
     *
     * @param txt ...
     */
    public String2(String txt) {
        variable = txt;
    }

    /**
     * Modifier le texte:Mettre du texte, l'�craser, ...:
     *
     * @param txt ...
     */
    public void Txt(String txt) {
        set(txt);
    }

    /**
     * (Obselete) Fonction pour affecter du texte � l'objet.
     *
     * @param txt est le texte qu'on affecte.
     */
    public void Texte(String txt) {
        set(txt);
    }

    /**
     * Ajouter ou modifier le texte existant. Attention: �crasera ce qui avait
     * avant!
     *
     * @param txt ...
     */
    public void set(String txt) {
        this.variable = txt;
    }

    /**
     * Ajouter du texte � la chaine de caract�re.
     *
     * @param txt est la chaine de caract�re qui sera ajouter � celle
     * d'ensemble.
     * @return ...
     */
    public String add(String txt) {
        if (variable != null) {
            return (this.variable += txt);
        } else {
            return (this.variable = txt);
        }
    }

    /**
     * Enl�ve les premieres caract�res.
     *
     * @param nb est le nombre de caract�re enlev� au d�but de la chaine de
     * caract�re.
     *
     * @return Renvoie la nouvelle chaine de caract�re g�n�r�.
     */
    public String EnleverPremier(int nb) {
        return Enlever(nb, length());
    }

    /**
     * Enlever du texte � la fin, se base sur la fonction "Enlever(int, int)".
     *
     * @param nb ...
     * @return ...
     */
    public String EnleverDernier(int nb) {
        return Enlever(0, length() - nb);
    }

    /**
     * La base pour enlever du texte.
     *
     * @param enlever ...
     * @param nb ...
     * @return ...
     */
    public String Enlever(int enlever, int nb) {
        if (Verifier()) {
            return (this.variable = this.variable.substring(enlever, nb));
        } else {
            return "Le texte est null.";
        }
    }

    /**
     * Garder du texte, sp�cifie ce qu'on garde au d�but (nombre).
     *
     * @param nb ...
     * @return ...
     */
    public String GarderPremier(int nb) {
        return Garder(0, nb);
    }

    /**
     * Baser sur la fonction "Garder", on sp�cifie le nombre d'�l�ment � garder
     * � la fin.
     *
     * @param nb ...
     * @return ...
     */
    public String GarderDernier(int nb) {
        return Garder(length() - nb, length());
    }

    /**
     * D�finit le caract�re � garder.
     *
     * @param nb est la position du caractère conservé (mais ne modifie pas la
     * chaine de caract�re).
     *
     * @return Renvoie le caractère conservé.
     */
    public String Garder(int nb) {
        if (nb > 0 && nb < length()) {
            return Garder(nb - 1, nb);
        } else {
            return "La lettre a garder n'est pas correcte.";
        }
    }

    /**
     * Garder un morceau du texte.
     *
     * @param garder est la valeur d'o� on garde
     * @param nb est jusqu'� combien de caract�re apr�s on garde.
     *
     * @return ...
     */
    public String Garder(int garder, int nb) {
        if (Verifier()) {
            return (this.variable = this.variable.substring(garder, nb));
        } else {
            return "Le texte est null.";
        }
    }

    /**
     * D�finit le s�parateur, ce qui sera enlev�, et fragmentera la chaine de
     * caract�re.
     *
     * @param enlever ...
     */
    public void Enlever(String enlever) {
        Separateur(enlever);
    }

    /**
     * Faire plusieurs "morceaux" de la chaine de caract�re.
     *
     * @param separateur ...
     */
    public void Separateur(String separateur) {
        if (Verifier()) {
            // On utilise le Scanner avec la fonction de d�limiteur pour fragmenter la chaine de caract�re.
            Scanner txt = new Scanner(this.variable).useDelimiter("\\s*" + (this.separateur = separateur) + "\\s*");

            this.morceau = new ArrayList();

            // On ajoute � l'ArrayList chaque morceau de la cha�ne de caract�re:
            for (nombre_morceau = 0; txt.hasNext(); this.nombre_morceau++) {
                this.morceau.add(txt.next());
            }
        } else {
            System.out.println("Le texte est null.");
        }
    }

    /**
     * On montre qu�elle est le s�parateur pour les morceaux.
     *
     * @return ...
     */
    public String getSeparateur() {
        return Separateur();
    }

    /**
     * La base pour savoir quelle est le s�parateur pour les morceaux.
     *
     * @return Retourne le s�parateur utilis�.
     */
    public String Separateur() {
        return this.separateur;
    }

    /**
     * Retourne le "morceau" d�finit par le s�parateur en fonction d'un index.
     *
     * @param index ...
     *
     * @return ...
     */
    public String Morceau(int index) {
        if (index != 0 && index <= this.nombre_morceau) {
            return this.morceau.get(index - 1);
        } else {
            return "Le morceau (num.: " + index + "), n'existe pas.";
        }
    }

    /**
     * Diviser le texte en plusieurs "morceaux", en fonction.
     *
     * @return ...
     */
    public int NombreMorceau() {
        return this.nombre_morceau;
    }

    /**
     * Retourne si on peut d�composer en plusieurs "caract�res"
     *
     * @return Retourne vrai si on peut d�composer le texte en m�moire, et
     * retourne faux si on en peut pas le modifier.
     */
    public boolean Caractere() {
        return Verifier();
    }

    /**
     * V�rifie que ce qu'a en m�moire "String2" est bien initialis�.
     *
     * @return ...
     */
    private boolean Verifier() {
        if (variable != null) {
            return true;
        } else {
            return false;
        }
    }

// -- Caract�ristiques du texte: --
    /**
     * Donne la longueur (nombre de caract�re) du texte.
     *
     * @return ...
     */
    public int length() {
        return this.variable.length();
    }

// -- Afficher le texte: --
    /**
     * Fonction qui affiche le texte enregistr� (sans traitement).
     *
     * @return ...s
     */
    public String get() {
        return this.variable;
    }

}
