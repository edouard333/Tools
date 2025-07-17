package com.phenix.tools;

import jakarta.validation.constraints.NotNull;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
     * Si l'option clickKill est true, c'est que quand on clique sur l'image,
     * cela la ferme.
     *
     * @param fichierImage Fichier image.
     */
    public Splash(@NotNull File fichierImage) {
        this(fichierImage, 0, 0, false);
    }

    /**
     * Affiche une image pendant un certain temps.
     *
     * @param fichierImage Fichier image.
     * @param temps Temps en milliseconde.
     */
    public Splash(@NotNull File fichierImage, int temps) {
        this(fichierImage, 0, 0, false);

        try {
            Attend.delais(temps);
            this.dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Affiche une image au centre de l'écran.
     *
     * @param fichierImage Fichier image.
     * @param clickKill Indique si on ferme l'image en cliquant dessus.
     */
    public Splash(@NotNull File fichierImage, boolean clickKill) {
        this(fichierImage, 0, 0, clickKill);
    }

    /**
     * Affiche une image au centre de l'écran.
     *
     * @param fichierImage Fichier image.
     * @param hauteur Hauteur en pixel de l'image et donc de la fenêtre. Si
     * <em>0</em>, utilise la résolution native de l'image.
     * @param largeur Largeur en pixel de l'image et donc de la fenêtre. Si
     * <em>0</em>, utilise la résolution native de l'image.
     */
    public Splash(@NotNull File fichierImage, int hauteur, int largeur) {
        this(fichierImage, hauteur, largeur, false);
    }

    /**
     * Affiche une image au centre de l'écran.
     *
     * @param fichierImage Fichier image.
     * @param hauteur Hauteur en pixel de l'image et donc de la fenêtre. Si
     * <em>0</em>, utilise la résolution native de l'image.
     * @param largeur Largeur en pixel de l'image et donc de la fenêtre. Si
     * <em>0</em>, utilise la résolution native de l'image.
     * @param clickKill Indique si on ferme l'image en cliquant dessus.
     */
    public Splash(@NotNull File fichierImage, int hauteur, int largeur, boolean clickKill) {
        // Rend le fond de la fenêtre transparent.
        this.setBackground(new Color(0, 0, 0, 0));

        JLabel jlabel;

        ImageIcon image = new ImageIcon(fichierImage.getAbsolutePath());

        if (largeur == 0 || hauteur == 0) {
            add(jlabel = new JLabel(image));
            jlabel.setOpaque(false);

            // Ajoute l'évènement qui permet de fermer la fenêtre/image en cliquant dessus si on a dit 'true'.
            if (clickKill) {
                jlabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {
                        dispose();
                    }
                });
            }
        } else {
            ScaledGifPanel panel = new ScaledGifPanel(image, largeur, hauteur);
            add(panel);

            // Ajoute l'évènement qui permet de fermer la fenêtre/image en cliquant dessus si on a dit 'true'.
            if (clickKill) {
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {
                        dispose();
                    }
                });
            }
        }

        // On doit adapter la taille de la fenêtre en fonction de celle de l'image (ainsi que si elle est redimensionnée).
        if (largeur == 0 || hauteur == 0) {
            this.setSize(image.getIconWidth(), image.getIconHeight());
        } else {
            this.setSize(largeur, hauteur);
        }

        // Lieu de la fenêtre : Au centre.
        this.setLocationRelativeTo(null);
    }
}

/**
 * Définit un {@link JPanel} avec une image qu'on définit les dimensions.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
class ScaledGifPanel extends JPanel {

    /**
     * L'image.
     */
    @NotNull
    private final ImageIcon gifIcon;

    /**
     * La largeur cible.
     */
    private final int targetWidth;

    /**
     * La hauteur cible.
     */
    private final int targetHeight;

    /**
     * Définit un panel avec une taille pour l'image.
     *
     * @param gifIcon L'image.
     * @param targetWidth La largeur cible.
     * @param targetHeight La hauteur cible.
     */
    public ScaledGifPanel(ImageIcon gifIcon, int targetWidth, int targetHeight) {
        this.gifIcon = gifIcon;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;

        // Redessiner à chaque frame de l'animation
        gifIcon.setImageObserver(this);

        this.setPreferredSize(new Dimension(targetWidth, targetHeight));
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = gifIcon.getImage();
        Graphics2D g2d = (Graphics2D) g.create();

        // Activer l'interpolation pour meilleure qualité.
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawImage(img, 0, 0, targetWidth, targetHeight, this);
        g2d.dispose();
    }
}
