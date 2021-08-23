package com.phenix.tools.XMLNLE;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * XML Final Cut Pro 7.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class XMLStandard {

  /**
   * TODO
   */
  private File fichier;

  /**
   * TODO
   */
  private String mode;

  /**
   * Nom du projet.
   */
  private String nom;

  /**
   * Liste des médias.
   */
  private ArrayList<Media> listMedia = new ArrayList<Media>();

  /**
   * Liste des timelines.
   */
  private ArrayList<Timeline> listTimeline = new ArrayList<Timeline>();

  /**
   * Liste des dossiers.
   */
  private ArrayList<Dossier> listDossier = new ArrayList<Dossier>();

  /**
   * Si on lit l'XML.
   */
  public static final String LECTURE = "r";

  /**
   * Si on écrit l'XML.
   */
  public static final String ECRITURE = "w";

  /**
   * L'XML est destiné à quel logiciel (par-défaut Adobe Premiere).
   */
  private byte logiciel_destination;

  /**
   * Si l'XML est pour Adobe Premiere Pro CC2017
   */
  public static final byte PREMIERE = 0;

  /**
   * Si l'XML est pour DaVinci Resolve 16.2.5.015.
   */
  public static final byte RESOLVE = 1;

  /**
   * TODO
   *
   * @param fichier Le chemin et nom du fichier.
   * @param mode Si on lit ou écrit l'XML.
   */
  public XMLStandard(File fichier, String mode, byte logiciel_destination) {
    this.fichier = fichier;
    this.mode = mode;
    this.logiciel_destination = logiciel_destination;
  }

  /**
   * TODO
   *
   * @param fichier Le chemin et nom du fichier.
   * @param mode Si on lit ou écrit l'XML.
   */
  public XMLStandard(File fichier, String mode) {
    this.fichier = fichier;
    this.mode = mode;
    this.logiciel_destination = PREMIERE;
  }

  /**
   * Modifie le nom du projet.
   *
   * @param nom Le nouveau nom du projet.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Ajoute un média audio au projet. Si mode écriture (add).
   */
  public void addMediaAudio(MediaAudio audio) {
    this.listMedia.add(audio);
  }

  /**
   * Ajoute une vidéo au projet.
   *
   * @param video La vidéo à ajouter.
   */
  public void addMediaVideo(MediaVideo video) {
    this.listMedia.add(video);
  }

  /**
   * Ajoute une image au projet.
   *
   * @param image L'image a ajouter.
   */
  public void addMediaImage(MediaImage image) {
    this.listMedia.add(image);
  }

  /**
   * Ajoute une timeline au projet.
   *
   * @param timeline La timeline à ajouter.
   */
  public void addTimeline(Timeline timeline) {
    timeline.setLogicielDestination(logiciel_destination);
    this.listTimeline.add(timeline);
  }

  /**
   * Ajoute un dossier au projet.
   *
   * @param dossier Le dossier.
   */
  public void addDossier(Dossier dossier) {
    this.listDossier.add(dossier);
  }

  /*public void addMediaTimeline(Timeline timeline, Media media) {
        this.listTimeline.get(this.listTimeline.indexOf(timeline)).addMedia(media);
    }*/
  /**
   * On clot le fichier dans soit sa lecture soit dans son écriture.
   */
  public void close() {
    // En écriture, on écrit tout.
    if (this.mode.equals(ECRITURE)) {
      try {
        PrintWriter file = new PrintWriter(this.fichier);
        file.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        file.append("<!DOCTYPE xmeml>\n");
        file.append("<xmeml version=\"4\">\n");

        file.append("\t<project>\n");
        file.append("\t\t<name>" + this.nom + "</name>\n");
        file.append("\t\t<children>\n");

        // Liste des dossiers:
        for (int i = 0; i < listDossier.size(); i++) {
          file.append(listDossier.get(i).toString());
        }

        // Liste timeline
        for (int i = 0; i < listTimeline.size(); i++) {
          file.append(listTimeline.get(i).toString());
        }

        // Liste des médias:
        for (int i = 0; i < listMedia.size(); i++) {
          file.append(listMedia.get(i).toString());
        }

        file.append("\t\t</children>\n");
        file.append("\t</project>\n");

        file.append("</xmeml>");
        file.close();
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    } // En lecture, on ne fait que lire.
    else {
    }
  }

  /**
   * Retourne le média audio. TODO
   *
   * @return L'audio.
   */
  public MediaAudio getMediaAudio() {
    return new MediaAudio("XXX.wav");
  }

  /**
   * Suffixe donné aux fichiers générés.
   *
   * @return Suffixe du fichier.
   */
  public static String getSuffixeFichier(byte logiciel_destination) {
    return (logiciel_destination == PREMIERE) ? "PRE" : "RESOLVE";
  }

}
