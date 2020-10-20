package com.phenix.tools.XMLNLE;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class MediaVideo extends Media {

    /**
     * ...
     */
    private int hauteur;

    /**
     * ...
     */
    private int largeur;

    /**
     * Position du média en x.
     */
    private int x;

    /**
     * Position du média en y.
     */
    private int y;

    /**
     * Nombre de canaux audio.
     */
    private int canaux;

    /**
     * Rapport de zoom du média. 100% = normal, par défaut.
     */
    private int echelle = 100;

    /**
     * A quel logiciel est destiné ce média vidéo. Par-défaut c'est Adobe
     * Premiere.
     */
    private byte logiciel_destination = XMLStandard.PREMIERE;

    /**
     * Information pour le média vidéo, mais qu'on ne peut renseigner qu'ici.<br>
     * Pour le cas de Resolve.<br>
     * La valeur est celle pour de la HD/UHD 1.777
     */
    private float horizontal = 0.585365831851959F;

    /**
     * Information pour le média vidéo, mais qu'on ne peut renseigner qu'ici.<br>
     * Pour le cas de Resolve.<br>
     * La valeur est celle pour de la HD/UHD 1.777
     */
    private float vertical = 0.5F;

    /**
     * TODO
     *
     * @param nom_fichier TODO
     */
    public MediaVideo(String nom_fichier) {
        super(nom_fichier);
        canaux = 0;
        type_media = "video";
    }

    /**
     * TODO
     *
     * @param nom_fichier
     * @param framerate
     */
    public MediaVideo(String nom_fichier, int framerate) {
        super(nom_fichier, framerate);
        canaux = 0;
        type_media = "video";
    }

    /**
     * Modifie à quel logiciel est destiné cette vidéo.
     *
     * @param logiciel_destination Logiciel auquel est destiné la timeline.
     */
    public void setLogicielDestination(byte logiciel_destination) {
        this.logiciel_destination = logiciel_destination;
    }

    /**
     * TODO
     *
     * @param largeur Largeur
     * @param hauteur Hauteur
     */
    public void setDimension(int largeur, int hauteur) {
        this.largeur = largeur;
        this.x = largeur / 2;
        this.hauteur = hauteur;
        this.y = hauteur / 2;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * ...
     *
     * @param canaux ...
     */
    public void setCanaux(int canaux) {
        this.canaux = canaux;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getCanaux() {
        return this.canaux;
    }

    /**
     * ...
     *
     * @param echelle ...
     */
    public void setEchelle(int echelle) {
        this.echelle = echelle;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getEchelle() {
        return this.echelle;
    }

    /**
     * Définit la position de l'élément dans l'image.
     *
     * @param x ...
     * @param y ...
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Position en x du média (en pixel).
     *
     * @return ...
     */
    public int getX() {
        return this.x;
    }

    /**
     * Position en y du média (en pixel).
     *
     * @return ...
     */
    public int getY() {
        return this.y;
    }

    /**
     * Position en X:
     * 0 = centre, max: +/-7.80488 (1080p).
     * 123F = 960/7.80488
     * (FHD) 123F = 1920/15.6098 (UHD)
     *
     * Pour Resolve:
     * UHD : +/- 0.585365831851959 (3840) - 1.777
     * HD : +/- 0.585365831851959 (1920) - 1.777
     * 1440 : +/- 0.5
     * 720 : +/- 0.585544347763062 - 1.777
     * "SD" : +/- 0.585365831851959 (960) - 1.777
     *
     * @return Position X pour Premiere ou Resolve.
     */
    public float getPositionHorizontale(int largeur_timeline) {
        //On divise la largeur en 2 pour avoir la partie positive ou négative.
        if (logiciel_destination == XMLStandard.PREMIERE) {
            return (this.x - (largeur_timeline / 2F)) / 123F;
        } else { // Pour Resolve:
            return (this.x - (largeur_timeline / 2F)) / (((float) largeur_timeline / 2F) / this.horizontal);
        }

    }

    /**
     * Position en Y:
     * 0 = centre, max: +/-6.66667 (1080p).
     * 81F = 540/6.6667
     * (FHD) 81F = 1080/13.3333 (UHD)
     *
     * Pour Resolve:
     * UHD : +/- 0.5 (2160) - 1.777
     * HD : +/- 0.5 (1080) - 1.777
     * 1440 : 0/- 0.569620251655579
     * 720p : +/- 0.5 - 1.777
     * "SD" : +/- 0.5 (540) - 1.777
     *
     * @return Position Y pour Premiere ou Resolve.
     */
    public float getPositionVerticale(int hauteur_timeline) {
        //On divise la hauteur en 2 pour avoir la partie positive ou négative.
        if (logiciel_destination == XMLStandard.PREMIERE) {
            return (this.y - (hauteur_timeline / 2F)) / 81F;
        } else // Pour Resolve:
        {
            return (this.y - (hauteur_timeline / 2F)) / (((float) hauteur_timeline / 2F) / this.vertical);
        }
    }

    /**
     * TODO
     *
     * @param vertical
     */
    public void setVertical(float vertical) {
        this.vertical = vertical;
    }

    /**
     * TODO
     *
     * @param horizontal
     */
    public void setHorizontal(float horizontal) {
        this.horizontal = horizontal;
    }
}
