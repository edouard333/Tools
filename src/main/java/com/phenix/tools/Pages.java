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
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Pages extends JFrame implements ActionListener, HyperlinkListener {

  /**
   * Le fichier HTML de travail.
   */
  private File file;

  /**
   * TODO
   */
  private Container contenu;

  /**
   * TODO
   */
  private JEditorPane txt = new JEditorPane();

  /**
   * TODO
   */
  private JScrollPane barre;

  /**
   * La page web a charger.
   */
  private String page;

  /**
   * Construit une page web.
   *
   * @param page La page web a charger.
   *
   * @throws IOException Erreur lors de la définition de la page.
   */
  public Pages(String page) throws IOException {
    this.page = page;

    //Caractéristiques de la fenêtre:
    super.setTitle("Page web:");

    //Largeur, hauteur:
    super.setSize(600, 600);

    //Modifiable: non
    super.setResizable(false);
    super.setLocationRelativeTo(null);

    txt.setEditable(false);
    txt.addHyperlinkListener(this);

    super.add(barre = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
    barre.setAutoscrolls(true);

    // Page par défaut:
    chargerHTML(page);
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
        setTitle(page + ": " + evt.getDescription());
      } catch (Exception exception) {
        System.out.println("Erreur[hyperlien]:\n" + exception);
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
  private void chargerHTML(String nom) throws IOException {
    this.file = new File(nom);

    this.txt.setEditorKit(new HTMLEditorKit());
    this.txt.setPage(file.toURL());
  }

}
