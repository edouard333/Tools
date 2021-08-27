package com.phenix.tools.XMLNLE;

import java.util.ArrayList;

/**
 * Dossier du projet.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class Dossier {

  /**
   * Liste des médias dans le dossier.
   */
  private ArrayList<Media> listeMedia = new ArrayList<Media>();

  /**
   * Liste des timelines dans le dossier.
   */
  private ArrayList<Timeline> listeTimeline = new ArrayList<Timeline>();

  /**
   * Liste des dossier dans le dossier.
   */
  private ArrayList<Dossier> listeDossier = new ArrayList<Dossier>();

  /**
   * Nom du dossier.
   */
  private String nom;

  /**
   * La couleur du dossier.
   */
  private String couleur;

  /**
   * Couleur orange pour Adobe.
   */
  public static final String COULEUR_ORANGE = "Mango";

  /**
   * Couleur rose pour Adobe.
   */
  public static final String COULEUR_ROSE = "Rose";

  /**
   * Couleur vert pour Adobe.
   */
  public static final String COULEUR_FORET = "Forest";

  /**
   * Couleur bleu pour Adobe.
   */
  public static final String COULEUR_CERULEEN = "Cerulean";

  /**
   * Couleur mauve pour Adobe.
   */
  public static final String COULEUR_LAVANDE = "Lavender";

  /**
   * Couleur XXX pour Adobe.
   */
  public static final String COULEUR_CARAIBE = "Caribbean";

  /**
   * Couleur XXX pour Adobe.
   */
  public static final String COULEUR_IRIS = "Iris";

  /**
   * Couleur violet pour Adobe.
   */
  public static final String COULEUR_VIOLET = "Violet";

  /**
   * Construit un objet Dossier.
   *
   * @param nom Nom du dossier
   */
  public Dossier(String nom) {
    this.nom = nom;
    this.couleur = COULEUR_ORANGE;
  }

  /**
   * Construit un objet Dossier.
   *
   * @param nom Nom du dossier.
   * @param couleur Couleur du dossier.
   */
  public Dossier(String nom, String couleur) {
    this.nom = nom;
    this.couleur = couleur;
  }

  /**
   * Retourne le nom du dossier.
   *
   * @return Nom du dossier.
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Ajoute un sous-dossier au dossier.
   *
   * @param dossier Dossier a ajouter.
   */
  public void addDossier(Dossier dossier) {
    this.listeDossier.add(dossier);
  }

  /**
   * Ajoute un média au dossier.
   *
   * @param media Média a ajouter.
   */
  public void addMedia(Media media) {
    this.listeMedia.add(media);
  }

  /**
   * Ajoute une timeline au dossier.
   *
   * @param timeline La timeline.
   */
  public void addTimeline(Timeline timeline) {
    this.listeTimeline.add(timeline);
  }

  /**
   * Génère le XML XML pour créer un projet d'un NLE.
   *
   * @return Code XML.
   */
  @Override
  public String toString() {
    String xml = "<bin>\n"
            + "<name>" + this.nom + "</name>\n"
            + "<labels>\n"
            + "<label2>" + this.couleur + "</label2>\n"
            + "</labels>\n";

    xml += "<children>\n";

    // Ajout des sous-dossiers:
    for (int i = 0; i < this.listeDossier.size(); i++) {
      xml += this.listeDossier.get(i).toString();
    }

    // Ajout des médias:
    for (int i = 0; i < this.listeMedia.size(); i++) {
      xml += ((Media) this.listeMedia.get(i)).toString();
    }

    // Ajout des séquences:
    for (int i = 0; i < this.listeTimeline.size(); i++) {
      xml += this.listeTimeline.get(i).toString();
    }

    xml += "</children>\n"
            + "</bin>\n";

    return xml;
  }

}
