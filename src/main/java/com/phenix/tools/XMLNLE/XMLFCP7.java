package com.phenix.tools.XMLNLE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * XML Final Cut Pro 7.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class XMLFCP7 {

    /**
     * Si on lit l'XML.
     */
    public static final String LECTURE = "r";

    /**
     * Si on écrit l'XML.
     */
    public static final String ECRITURE = "w";

    /**
     * Si l'XML est pour Adobe Premiere Pro CC2017.
     */
    public static final byte PREMIERE = 0;

    /**
     * Si l'XML est pour DaVinci Resolve 16.2.5.015.
     */
    public static final byte RESOLVE = 1;

    /**
     * Le fichier XML a créer ou lire.
     */
    private File fichier;

    /**
     * Si écriture ou lecture.
     */
    private String mode;

    /**
     * Nom du projet.
     */
    private String titre_projet;

    /**
     * Liste des médias.
     */
    private ArrayList<Media> liste_media = new ArrayList<Media>();

    /**
     * Liste des timelines.
     */
    private ArrayList<Timeline> liste_timeline = new ArrayList<Timeline>();

    /**
     * Liste des dossiers.
     */
    private ArrayList<Dossier> liste_dossier = new ArrayList<Dossier>();

    /**
     * L'XML est destiné à quel logiciel.
     */
    private byte logiciel_destination;

    /**
     * Construit un <code>XMLFCP7</code>.
     *
     * @param fichier Le chemin et nom du fichier.
     * @param mode Si on lit ou écrit l'XML.
     */
    public XMLFCP7(File fichier, String mode) {
        this(fichier, mode, PREMIERE);
    }

    /**
     * Construit un <code>XMLStandard</code>.
     *
     * @param fichier Le chemin et nom du fichier.
     * @param mode Si on lit ou écrit l'XML.
     * @param logiciel_destination Le XML est destiné à quel logiciel.
     */
    public XMLFCP7(File fichier, String mode, byte logiciel_destination) {
        this.fichier = fichier;
        this.mode = mode;
        this.logiciel_destination = logiciel_destination;

        // Si on créé un nouvel XML FCP7, alors le nombre de timeline est de 0.
        Timeline.nombre_timeline = 0;
    }

    /**
     * Ajoute un média audio au projet.Si mode écriture (add).
     *
     * @param audio Média audio.
     */
    public void addMediaAudio(MediaAudio audio) {
        this.liste_media.add(audio);
    }

    /**
     * Ajoute une vidéo au projet.
     *
     * @param video La vidéo à ajouter.
     */
    public void addMediaVideo(MediaVideo video) {
        this.liste_media.add(video);
    }

    /**
     * Ajoute une image au projet.
     *
     * @param image L'image a ajouter.
     */
    public void addMediaImage(MediaImage image) {
        this.liste_media.add(image);
    }

    /**
     * Ajoute une timeline au projet.
     *
     * @param timeline La timeline à ajouter.
     */
    public void addTimeline(Timeline timeline) {
        timeline.setLogicielDestination(this.logiciel_destination);
        this.liste_timeline.add(timeline);
    }

    /**
     * Ajoute un dossier au projet.
     *
     * @param dossier Le dossier.
     */
    public void addDossier(Dossier dossier) {
        this.liste_dossier.add(dossier);
    }

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
                file.append("\t\t<name>" + this.titre_projet + "</name>\n");
                file.append("\t\t<children>\n");

                // Liste des dossiers :
                for (int i = 0; i < this.liste_dossier.size(); i++) {
                    file.append(this.liste_dossier.get(i).toString());
                }

                // Liste timeline :
                for (int i = 0; i < this.liste_timeline.size(); i++) {
                    file.append(this.liste_timeline.get(i).toString());
                }

                // Liste des médias :
                for (int i = 0; i < this.liste_media.size(); i++) {
                    file.append(this.liste_media.get(i).toString());
                }

                file.append("\t\t</children>\n");
                file.append("\t</project>\n");

                file.append("</xmeml>");
                file.close();
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        } // En lecture, on ne fait que lire.
        else {
        }
    }

    /**
     * TODO : Retourne le média audio.
     *
     * @return L'audio.
     */
    public MediaAudio getMediaAudio() {
        return new MediaAudio("XXX.wav");
    }

    /**
     * Suffixe donné aux fichiers générés.
     *
     * @param logiciel_destination En fonction du logiciel de destination le
     * suffixe est différent.
     *
     * @return Suffixe du fichier.
     */
    public static String getSuffixeFichier(byte logiciel_destination) {
        return (logiciel_destination == PREMIERE) ? "PRE" : "RESOLVE";
    }

    /**
     * Modifie le nom du projet.
     *
     * @param titre_projet Le nouveau titre du projet.
     */
    public void setTitreProjet(String titre_projet) {
        this.titre_projet = titre_projet;
    }
}
