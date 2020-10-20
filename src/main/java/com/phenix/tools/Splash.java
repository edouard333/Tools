package com.phenix.tools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * Fichier: Splash Version: 2.7 Info: Il sert � montrer une image au milieu de
 * l'�cran. Attention: Les fichiers '.gif' ne marche pas. Fichiers compatibles:
 * 'jpg'.
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class Splash extends JWindow {

    private ImageIcon img;
    private String image;
    private int temps;
    private boolean clickKill = false;

    public Splash() {
    }

//'image' est le lieu de l'image:
    public Splash(String image) {
        JLabel jlabel;

        add(jlabel = new JLabel(img = new ImageIcon(image)));

        jlabel.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (clickKill) {
                    dispose();
                }
            }
        });

        //Largeur, hauteur:
        setSize(img.getIconWidth(), img.getIconHeight());

        //Lieu de la fen�tre: Au centre.
        setLocationRelativeTo(null);

        setVisible(true);
    }

// Si l'option clickKill est true, c'est que quand on clique sur l'image, cela la ferme.
    public Splash(String image, boolean clickKill) {
        this(image);
        this.clickKill = clickKill;
    }

    public Splash(String image, int temps) {
        this(image);

        try {
            new Attend(temps);
            setVisible(false);
        } catch (Exception e) {
        }
    }

    public void SetVisible(boolean valeur) {
        setVisible(valeur);
    }

    public void Temps(int temps) {
        this.temps = temps;
    }

    public void Image(String image) {
        this.image = image;
    }

}

class Attend2 extends Thread {

    public Attend2(int temps) throws IOException {

        //On doit mettre un 'try' quand on veut utiliser le 'sleep'.
        try {
            //Ici, il y a un temps d'attende, le temps d'attende est en milliseconde:
            sleep(temps);
        } catch (Exception e) {
            throw new IOException("Erreur:\n" + e);
        }

    }

}
