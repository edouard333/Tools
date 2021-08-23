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
   *
   */
  private File file;

  /**
   *
   */
  private Container contenu;

  /**
   *
   */
  private JEditorPane txt = new JEditorPane();

  /**
   *
   */
  private JScrollPane barre;

  /**
   *
   */
  private String page;

  /**
   *
   * @param page
   * @throws IOException
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
   * @param e
   */
  public void actionPerformed(ActionEvent evt) {
  }

  /**
   * Action à propos d'un hyperlien.
   *
   * @param e
   */
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
   *
   * @param nom
   * @throws IOException
   */
  private void chargerHTML(String nom) throws IOException {
    file = new File(nom + ".htm");

    txt.setEditorKit(new HTMLEditorKit());
    txt.setPage(file.toURL());
  }

}
