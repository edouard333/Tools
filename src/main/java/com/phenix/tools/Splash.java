package com.phenix.tools;

import jakarta.validation.constraints.NotNull;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * Permet de faire un splash : montrer une image au milieu de l'écran.<br>
 * Attention : Les fichiers '.gif' ne marchent pas.<br>
 * Fichiers compatibles : 'jpg'.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Splash extends JWindow {

    /**
     * Image affiché.
     */
    private ImageIcon image;

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
     * @param fichierImage Fichier image.
     */
    public Splash(@NotNull File fichierImage) {
        JLabel jlabel;

        add(jlabel = new JLabel(this.image = new ImageIcon(fichierImage.getAbsolutePath())));

        jlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (clickKill) {
                    dispose();
                }
            }
        });

        // Largeur, hauteur :
        this.setSize(this.image.getIconWidth(), this.image.getIconHeight());

        // Lieu de la fenêtre : Au centre.
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    /**
     * Si l'option clickKill est true, c'est que quand on clique sur l'image,
     * cela la ferme.
     *
     * @param fichierImage Fichier image.
     * @param clickKill Indique si on ferme l'image en cliquant dessus.
     */
    public Splash(File fichierImage, boolean clickKill) {
        this(fichierImage);
        this.clickKill = clickKill;
    }

    /**
     * Affiche une image pendant un certain temps.
     *
     * @param fichierImage Fichier image.
     * @param temps Temps en milliseconde.
     */
    public Splash(File fichierImage, int temps) {
        this(fichierImage);

        try {
            Attend.delais(temps);
            this.dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Définit le temps pendant lequel est affichée l'image.
     *
     * @param temps Temps en milliseconde.
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }
}
