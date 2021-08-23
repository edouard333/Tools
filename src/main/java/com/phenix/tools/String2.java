package com.phenix.tools;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Sert à gérer une chaîne de caractère.<br>
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.12.0
 * @since Kit objet V1
 */
public final class String2 {

  /**
   * La chaîne de caractère standard qui va subir les traitements.
   */
  private String variable;

  /**
   * Variable qui va servir pour fragmenter la chaîne de caractère.
   */
  private ArrayList<String> morceau;

  /**
   * Variable contenant le nombre de morceau, si on décompose texte.
   */
  private int nombre_morceau;

  /**
   * Le séparateur, si utilisé.
   */
  private String separateur;

  /* -- Constructors -- */
  /**
   * Permet créer une instance de "String2" sans donner de paramètre.
   */
  public String2() {
  }

  /**
   * Demande une chaine de caractère qui sera affecter à la variable.
   *
   * @param texte
   */
  public String2(String texte) {
    variable = texte;
  }

  /**
   * Remplace le texte existant.
   *
   * @param texte Le nouveau texte?
   */
  public void Txt(String texte) {
    set(texte);
  }

  /**
   * (Obselete) Fonction pour affecter du texte à l'objet.
   *
   * @param texte est le texte qu'on affecte.
   */
  public void Texte(String texte) {
    set(texte);
  }

  /**
   * Ajouter ou modifier le texte existant. Attention: écrasera ce qui avait
   * avant!
   *
   * @param texte Le texte.
   */
  public void set(String texte) {
    this.variable = texte;
  }

  /**
   * Ajouter du texte à la chaine de caractère.
   *
   * @param txt est la chaine de caractère qui sera ajouter à celle d'ensemble.
   *
   * @return
   */
  public String add(String txt) {
    if (variable != null) {
      return (this.variable += txt);
    } else {
      return (this.variable = txt);
    }
  }

  /**
   * Enlève les premieres caractères.
   *
   * @param nb est le nombre de caractère enlevé au début de la chaine de
   * caractère.
   *
   * @return Renvoie la nouvelle chaine de caractère généré.
   */
  public String EnleverPremier(int nb) {
    return Enlever(nb, length());
  }

  /**
   * Enlever du texte à la fin, se base sur la fonction "Enlever(int, int)".
   *
   * @param nb
   *
   * @return
   */
  public String EnleverDernier(int nb) {
    return Enlever(0, length() - nb);
  }

  /**
   * La base pour enlever du texte.
   *
   * @param enlever
   * @param nb
   *
   * @return
   */
  public String Enlever(int enlever, int nb) {
    if (Verifier()) {
      return (this.variable = this.variable.substring(enlever, nb));
    } else {
      return "Le texte est null.";
    }
  }

  /**
   * Garder du texte, spécifie ce qu'on garde au début (nombre).
   *
   * @param nb
   *
   * @return
   */
  public String GarderPremier(int nb) {
    return Garder(0, nb);
  }

  /**
   * Baser sur la fonction "Garder", on spécifie le nombre d'élément à garder à
   * la fin.
   *
   * @param nb
   *
   * @return
   */
  public String GarderDernier(int nb) {
    return Garder(length() - nb, length());
  }

  /**
   * Définit le caractère à garder.
   *
   * @param nb est la position du caractère conservé (mais ne modifie pas la
   * chaine de caractère).
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
   * @param garder est la valeur d'où on garde
   * @param nb est jusqu'à combien de caractère après on garde.
   *
   * @return
   */
  public String Garder(int garder, int nb) {
    if (Verifier()) {
      return (this.variable = this.variable.substring(garder, nb));
    } else {
      return "Le texte est null.";
    }
  }

  /**
   * Définit le séparateur, ce qui sera enlevé, et fragmentera la chaine de
   * caractère.
   *
   * @param enlever Catactère à enlever.
   */
  public void Enlever(String enlever) {
    Separateur(enlever);
  }

  /**
   * Faire plusieurs "morceaux" de la chaine de caractère.
   *
   * @param separateur Définit le spérateur à utiliser.
   */
  public void Separateur(String separateur) {
    if (Verifier()) {
      // On utilise le Scanner avec la fonction de délimiteur pour fragmenter la chaine de caractère.
      Scanner txt = new Scanner(this.variable).useDelimiter("\\s*" + (this.separateur = separateur) + "\\s*");

      this.morceau = new ArrayList();

      // On ajoute à l'ArrayList chaque morceau de la chaîne de caractère:
      for (nombre_morceau = 0; txt.hasNext(); this.nombre_morceau++) {
        this.morceau.add(txt.next());
      }
    } else {
      System.out.println("Le texte est null.");
    }
  }

  /**
   * On montre qu'elle est le séparateur pour les morceaux.
   *
   * @return
   */
  public String getSeparateur() {
    return Separateur();
  }

  /**
   * La base pour savoir quelle est le séparateur pour les morceaux.
   *
   * @return Retourne le séparateur utilisé.
   */
  public String Separateur() {
    return this.separateur;
  }

  /**
   * Retourne le "morceau" définit par le séparateur en fonction d'un index.
   *
   * @param index
   *
   * @return
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
   * @return
   */
  public int NombreMorceau() {
    return this.nombre_morceau;
  }

  /**
   * Retourne si on peut décomposer en plusieurs "caractères".
   *
   * @return Retourne <code>true</code> si on peut décomposer le texte en
   * mémoire, et retourne <code>false</code> si on en peut pas le modifier.
   */
  public boolean Caractere() {
    return Verifier();
  }

  /**
   * Vérifie que ce qu'a en mémoire "String2" est bien initialisé.
   *
   * @return <code>true</code> si <code>String2</code> est initialisé.
   */
  private boolean Verifier() {
    if (variable != null) {
      return true;
    } else {
      return false;
    }
  }

// -- Caractèristiques du texte: --
  /**
   * Donne la longueur (nombre de caractère) du texte.
   *
   * @return Longueur de la chaîne de caractère.
   */
  public int length() {
    return this.variable.length();
  }

// -- Afficher le texte: --
  /**
   * Fonction qui affiche le texte enregistré (sans traitement).
   *
   * @return Texte.
   */
  public String get() {
    return this.variable;
  }

}
