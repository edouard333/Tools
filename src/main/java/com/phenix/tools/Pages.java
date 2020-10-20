package com.phenix.tools;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLEditorKit;

import java.io.File;
import java.io.IOException;

/**
 * Sert à montrer des pages web.
 *
 * @version 2.5.0
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 */
public class Pages extends JFrame implements ActionListener, HyperlinkListener {

    private File file;

    private Container contenu;

    private JEditorPane txt = new JEditorPane();
    private JScrollPane barre;

    private String page;

    public Pages(String page) throws IOException {
        this.page = page;

        //Caract�ristiques de la fen�tre:
        setTitle("Page web:");

        //Largeur, hauteur:
        setSize(600, 600);

        //Modifiable: non
        setResizable(false);
        setLocationRelativeTo(null);

        txt.setEditable(false);
        txt.addHyperlinkListener(this);

        add(barre = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        barre.setAutoscrolls(true);

        //Page par d�faut:
        Charger_html(page);
    }

    //S'il y a une action quelconque:
    public void actionPerformed(ActionEvent e) {
    }

    //Action � propos d'un hyperlien:
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                Charger_html(e.getDescription());
                setTitle(page + ": " + e.getDescription());
            } catch (Exception ex) {
                System.out.println("Erreur[hyperlien]:\n" + ex);
            }
        }
    }

    private void Charger_html(String nom) throws IOException {
        file = new File(nom + ".htm");

        txt.setEditorKit(new HTMLEditorKit());
        txt.setPage(file.toURL());
    }

}
