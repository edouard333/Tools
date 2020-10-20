package com.phenix.tools.structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Objet qui gère les métadonnées d'un fichier XML.
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 0.1.0
 */
public class XMLMetadata {

    // --- Liste des attributs. ---
    /**
     * TOOD
     */
    public General general;

    /**
     * TODO
     */
    public Image image;

    /**
     * TODO
     */
    public SousTitre sousTitre;

    /**
     * TODO
     */
    public Audio audio;

    /**
     * TODO
     */
    public QC qc;

    // --- Liste des constructeurs. ---
    /**
     * On indique le fichier a lire ou à écrire.
     *
     * @param fichier ...
     */
    public XMLMetadata(String fichier) {

    }

    // --- Liste des getters. ---
    // --- Liste des méthodes. ---
    /**
     * On clos le flux du fichier, utile que quand on écrit le fichier.
     */
    public void close() {
    }
}

/**
 * Gère les données de l'XML de la partie "commun"/"general".
 *
 * @author Edouard
 */
class General {

    /**
     * Nom du fichier vidéo dont les données sont issues.
     */
    private String nom_fichier;

    /**
     * Nom du film.
     */
    private String nom_film;

    /**
     * Indique les différents nom du film. Avec date de modification.
     */
    private HashMap<String, String> liste_nom_film;

    /**
     * ID du projet. Utiliser cette donnée que si on lie en ligne.
     */
    private int id_projet;

    /**
     * Indique s'il s'agit: FTR, TLR, ...
     */
    private String type_fichier;

    /**
     * Si on doit indiquer des informations s'il y a des versions différentes du
     * film.
     * <p>
     * Exemple: version recut, director cut, version censuré (ex "Ni juge ni
     * soumise").
     */
    private String version;

    /**
     * Poids du fichier octet.
     */
    private long poids_octet;

    /**
     * Poids du fichier en chaine de caractère utile.
     */
    private String poids_fichier;

    /**
     * Date de création du fichier du fichier vidéo.
     * <p>
     * TODO: Remplacer par une date.
     */
    private String date_creation_fichier;

    /**
     * Date de création du fichier XML.
     * <p>
     * TODO: Remplacer par une date.
     */
    private String date_creation;

    /**
     * Indique la la valeur du checksum du fichier vidéo. Si la valeur existe!
     */
    private String checksum;

    /**
     * Si checksum utilisé et renseigné, doit indiquer par quel mode: MD5, SHA1,
     * SHA2, ...
     */
    private String mode_checksum;

    /**
     * On peut ajouter des mots clef pour le film.
     */
    private ArrayList<String> liste_mot_clef;

    /**
     * Indique des informations générale sur le fichier.
     */
    private String general_information;

    // --- Liste des setters. ---
    public void setNomFilm(String nom_film) {
    }

    // --- Liste des getters. ---
    // --- Liste des méthodes. ---
    /**
     * Retourne les données qu'il faut pour l'objet XMLMetadata.
     */
    public void get() {
        // TODO.
    }
}

/**
 * Gère les données de l'XML de la partie "image".
 *
 * @author Edouard
 */
class Image {

    // --- Liste des attributs. ---
    private String codec;

    /**
     * TODO: remplacer par objet Debit.
     * Car débit en Mb/s, Kb/s, Go/s, ...
     */
    private float debit;

    private float debit_min;

    private float debit_max;

    private float debit_cible;

    /**
     * Hauteur de l'image en pixel.
     * TODO : remplacer par objet "Resolution".
     */
    private int hauteur;

    private int largeur;

    /**
     * Ratio du fichier.
     * <p>
     * TODO: Remplacer par objet Ratio.
     */
    private String ratio;

    /**
     * Indique si la ratio est celui original du film.
     */
    private boolean OAR;

    /**
     * Indique si le fichier est en entrelacé, progressif, PsF, ...
     */
    private String balayage;

    /**
     * Indique l'espace couleur et toutes ces informations: HDR, SDR, ...
     */
    private String espace_couleur;

    /**
     * Indique s'il y a un watermark à l'image.
     */
    private String watermark;

    /**
     * Indique si le timecode est incrusté dans l'image.
     */
    private boolean TCI;

    /**
     * Indique s'il y a la présence de fond neutre à la fin du fichier.
     */
    private boolean textless;

    /**
     * S'il y a des informations à préciser.
     */
    private String information;

    // --- Liste des constructeurs. ---
    public Image() {
    }

    // --- Liste des setters. ---
    public void setCodec(String codec) {
        this.codec = codec;
    }

    public void setDebit(float debit) {
        this.debit = debit;
    }

    public void setDebitMin(float debit_min) {
        this.debit_min = debit_min;
    }

    public void setDebitMax(float debit_max) {
        this.debit_max = debit_max;
    }

    public void setDebitCible(float debit_cible) {
        this.debit_cible = debit_cible;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public void setOAR(boolean OAR) {
        this.OAR = OAR;
    }

    public void setBalayage(String balayage) {
        this.balayage = balayage;
    }

    public void setEspaceCouleur(String espace_couleur) {
        this.espace_couleur = espace_couleur;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public void setTCI(boolean TCI) {
        this.TCI = TCI;
    }

    public void setTextless(boolean textless) {
        this.textless = textless;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    // --- Liste des getters. ---
    public String setCodec() {
        return this.codec;
    }

    public float setDebit() {
        return this.debit;
    }

    public float setDebitMin() {
        return this.debit_min;
    }

    public float setDebitMax() {
        return this.debit_max;
    }

    public float setDebitCible() {
        return this.debit_cible;
    }

    public int setHauteur() {
        return this.hauteur;
    }

    public int setLargeur() {
        return this.largeur;
    }

    public String setRatio() {
        return this.ratio;
    }

    public boolean setOAR() {
        return this.OAR;
    }

    public String setBalayage() {
        return this.balayage;
    }

    public String setEspaceCouleur() {
        return this.espace_couleur;
    }

    public String setWatermark() {
        return this.watermark;
    }

    public boolean setTCI() {
        return this.TCI;
    }

    public boolean setTextless() {
        return this.textless;
    }

    public String setInformation() {
        return this.information;
    }

    // --- Liste des méthodes. ---
    /**
     * Retourne les données qu'il faut pour l'objet XMLMetadata.
     */
    public void get() {
        // TODO.
    }
}

/**
 * Gère les données de l'XML de la partie "sous-titre".
 *
 * @author Edouard
 */
class SousTitre {
    // --- Liste des attributs. ---

    // --- Liste des constructeurs. ---
    public SousTitre() {
    }

    // --- Liste des setters. ---
    // --- Liste des getters. ---
    // --- Liste des méthodes. ---
    /**
     * Retourne les données qu'il faut pour l'objet XMLMetadata.
     */
    public void get() {
        // TODO.
    }
}

/**
 * Gère les données de l'XML de la partie "audio".
 *
 * @author Edouard
 */
class Audio {
    // --- Liste des attributs. ---

    // --- Liste des constructeurs. ---
    public Audio() {
    }

    // --- Liste des setters. ---
    // --- Liste des getters. ---
    // --- Liste des méthodes. ---
    /**
     * Retourne les données qu'il faut pour l'objet XMLMetadata.
     */
    public void get() {
        // TODO.
    }
}

/**
 * Gère les données de l'XML de la partie "timecode".
 *
 * @author Edouard
 */
class QC {
    // --- Liste des attributs. ---

    /**
     * Le timecode où commence le fichier.
     * <p>
     * TODO: Remplacer par l'objet Timecode.
     */
    private String start;

    /**
     * La durée du fichier en timecode SMPTE.
     * <p>
     * TODO: Remplacer par l'objet Timecode.
     */
    private String duree;

    /**
     * Framerate (cadence) du fichier.
     * Le nombre est à virgule pour gérer les 23.976, 59.94, ...
     */
    private float framerate;

    /**
     * Indique s'il s'agit du framerate original du film.
     */
    private boolean OAF;

    /**
     * Indique si le timecode est dropframe ou non.
     * Pour la lisiblité on indique le "dropframe": si true, alors il est drop frame.
     * Sinon (= false) alors "non-dropframe".
     */
    private boolean drop_frame;

    // --- Liste des constructeurs. ---
    public QC() {
    }

    // --- Liste des setters. ---
    public void setStart(String start) {
        this.start = start;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setFramerate(float framerate) {
        this.framerate = framerate;
    }

    public void setOAF(boolean OAF) {
        this.OAF = OAF;
    }

    public void setDropFrame(boolean drop_frame) {
        this.drop_frame = drop_frame;
    }

    // --- Liste des getters. ---
    public String getStart() {
        return this.start;
    }

    public String getDuree() {
        return this.duree;
    }

    public float getFramerate() {
        return this.framerate;
    }

    public boolean getOAF() {
        return this.OAF;
    }

    public boolean getDropFrame() {
        return this.drop_frame;
    }

    // --- Liste des méthodes. ---
    /**
     * Retourne les données qu'il faut pour l'objet XMLMetadata.
     */
    public void get() {
        // TODO.
    }
}
