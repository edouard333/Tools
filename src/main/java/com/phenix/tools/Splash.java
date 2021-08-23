package com.phenix.tools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * Fichier: Splash Version: 2.7 Info: Il sert à montrer une image au milieu de
 * l'écran.<br>
 * Attention: Les fichiers '.gif' ne marche pas.<br>
 * Fichiers compatibles: 'jpg'.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class Splash extends JWindow {

  /**
   * Image affiché.
   */
  private ImageIcon image;

  /**
   * Fichier image.
   */
  private String nom_fichier_image;

  /**
   * Temps d'affichage de l'image.
   */
  private int temps;

  /**
   * Si on ferme la fenêtre (image) quand on clique sur l'image.
   */
  private boolean clickKill = false;

  /**
   * Affiche une image au centre de l'écran.
   *
   * @param nom_fichier_image Nom du fichier image.
   */
  public Splash(String nom_fichier_image) {
    JLabel jlabel;

    add(jlabel = new JLabel(this.image = new ImageIcon(nom_fichier_image)));

    jlabel.addMouseListener(
            new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        if (clickKill) {
          dispose();
        }
      }
    });

    //Largeur, hauteur:
    super.setSize(this.image.getIconWidth(), this.image.getIconHeight());

    //Lieu de la fenêtre: Au centre.
    super.setLocationRelativeTo(null);

    super.setVisible(true);
  }

  /**
   * Si l'option clickKill est true, c'est que quand on clique sur l'image, cela
   * la ferme.
   *
   * @param nom_fichier_image Nom du fichier image.
   * @param clickKill
   */
  public Splash(String nom_fichier_image, boolean clickKill) {
    this(nom_fichier_image);
    this.clickKill = clickKill;
  }

  /**
   * Affiche une image pendant un certain temps.
   *
   * @param nom_fichier_image Nom du fichier image.
   * @param temps Temps en miliseconde.
   */
  public Splash(String nom_fichier_image, int temps) {
    this(nom_fichier_image);

    try {
      new Attend(temps);
      super.setVisible(false);
    } catch (Exception exception) {
    }
  }

  /**
   * Définit le temps qu'est affiché l'image.
   *
   * @param temps Temps en miliseconde.
   */
  public void setTemps(int temps) {
    this.temps = temps;
  }
}
