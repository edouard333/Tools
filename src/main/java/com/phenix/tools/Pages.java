package com.phenix.tools;

import jakarta.validation.constraints.NotNull;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Sert à montrer des pages web.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Pages extends JFrame implements ActionListener, HyperlinkListener {

    /**
     * Le fichier HTML de travail.
     */
    private File file;

    /**
     *
     */
    @NotNull
    private final JEditorPane txt = new JEditorPane();

    /**
     *
     */
    @NotNull
    private final JScrollPane barre;

    /**
     * La page web à charger.
     */
    private final String page;

    /**
     * Construit une page web.
     *
     * @param page La page web a charger.
     *
     * @throws IOException Erreur lors de la définition de la page.
     */
    public Pages(String page) throws IOException {
        this.page = page;

        // Caractéristiques de la fenêtre :
        this.setTitle("Page web:");

        // Largeur, hauteur :
        this.setSize(600, 600);

        // Modifiable : non
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.txt.setEditable(false);
        this.txt.addHyperlinkListener(this);

        this.add(this.barre = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        this.barre.setAutoscrolls(true);

        // Page par défaut :
        this.chargerHTML(page);
    }

    /**
     * S'il y a une action quelconque.
     *
     * @param evt L'évènement.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    /**
     * Action à propos d'un hyperlien.
     *
     * @param evt L'évènement.
     */
    @Override
    public void hyperlinkUpdate(HyperlinkEvent evt) {
        if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                chargerHTML(evt.getDescription());
                setTitle(this.page + ": " + evt.getDescription());
            } catch (IOException exception) {
                System.out.println("Erreur[hyperlien]:\n" + exception.getMessage());
            }
        }
    }

    /**
     * Charge une page HTML.
     *
     * @param nom Le nom du fichier HTML.
     *
     * @throws IOException Erreur lors de la définition de la page.
     */
    private void chargerHTML(@NotNull String nom) throws IOException {
        this.file = new File(nom);

        this.txt.setEditorKit(new HTMLEditorKit());
        this.txt.setPage(this.file.toURL());
    }
}
