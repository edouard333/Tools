package com.phenix.tools.XMLNLE;

/**
 * Média de type vidéo (image + audio).
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class MediaVideo extends Media {

  /**
   * Hauteur de l'image.
   */
  private int hauteur;

  /**
   * Largeur de l'image.
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
   * A quel logiciel est destiné ce média vidéo.<br>
   * Par défaut c'est Adobe
   * Premiere.
   */
  private byte logiciel_destination = XMLStandard.PREMIERE;

  /**
   * Information pour le média vidéo, mais qu'on ne peut renseigner qu'ici.<br>
   * Pour le cas de Resolve.<br>
   * La valeur est celle pour de la HD/UHD 1.777.
   */
  private float horizontal = 0.585365831851959F;

  /**
   * Information pour le média vidéo, mais qu'on ne peut renseigner qu'ici.<br>
   * Pour le cas de Resolve.<br>
   * La valeur est celle pour de la HD/UHD 1.777.
   */
  private float vertical = 0.5F;

  /**
   * Construit un MediaVideo à partir d'un nom de fichier.
   *
   * @param nom_fichier Nom du fichier.
   */
  public MediaVideo(String nom_fichier) {
    super(nom_fichier);
    canaux = 0;
    type_media = "video";
  }

  /**
   * Construit un MediaVideo à partir d'un nom de fichier et de son framerate.
   *
   * @param nom_fichier Nom du fichier.
   * @param framerate Framerate.
   */
  public MediaVideo(String nom_fichier, int framerate) {
    super(nom_fichier, framerate);
    canaux = 0;
    type_media = "video";
  }

  /**
   * Récupère le nombre de canaux audio.
   *
   * @return Nombre de canaux audio.
   */
  public int getCanaux() {
    return this.canaux;
  }

  /**
   * Récupère la hauteur de l'image.
   *
   * @return Hauteur en pixel.
   */
  public int getHauteur() {
    return this.hauteur;
  }

  /**
   * Récupère la largeur de l'image.
   *
   * @return Largeur en pixel.
   */
  public int getLargeur() {
    return this.largeur;
  }

  /**
   * Position en x du média (en pixel).
   *
   * @return Coordonnée x.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Position en y du média (en pixel).
   *
   * @return Coordonnée y.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Rapport du zoom.
   *
   * @return 100 = 100%
   */
  public int getEchelle() {
    return this.echelle;
  }

  /**
   * Position en X :<br>
   * 0 = centre, max: +/-7.80488 (1080p).<br>
   * <br>
   * Pour Adobe Premiere :<br>
   * 2160 : 123F (1920/15.6098)<br>
   * 1080 : 123F (960/7.80488)<br>
   * <br>
   * Pour Resolve: UHD :<br>
   * +/- 0.585365831851959 (3840) - 1.777<br>
   * 1920 : +/- 0.585365831851959 - 1.777<br>
   * 1440 : +/- 0.5<br>
   * 720 : +/- 0.585544347763062 - 1.777<br>
   * 576 : +/- 0.585365831851959 (960) - 1.777
   *
   * @param largeur_timeline Largeur de la timeline.
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
   * Position en Y :<br>
   * 0 = centre, max: +/-6.66667 (1080p).<br>
   * <br>
   * Pour Adobe Premiere :<br>
   * 2160 : 81F (1080/13.3333)<br>
   * 1080 : 81F (540/6.6667)<br>
   * <br>
   * Pour Resolve :<br>
   * 2160 : +/- 0.5 - 1.777<br>
   * 1440 : 0/- 0.569620251655579<br>
   * 1080 : +/- 0.5 - 1.777<br>
   * 720p : +/- 0.5 - 1.777<br>
   * 576 : +/- 0.5 (540) - 1.777
   *
   * @param hauteur_timeline Hauteur de la timeline.
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
   * Définit le nombre de canaux audio qu'à le média vidéo.
   *
   * @param canaux Nombre de canaux audio.
   */
  public void setCanaux(int canaux) {
    this.canaux = canaux;
  }

  /**
   * Définit les dimensions de la vidéo.
   *
   * @param largeur Largeur.
   * @param hauteur Hauteur.
   */
  public void setDimension(int largeur, int hauteur) {
    this.largeur = largeur;
    this.x = largeur / 2;
    this.hauteur = hauteur;
    this.y = hauteur / 2;
  }

  /**
   * Modifie le rapport du zoom.
   *
   * @param echelle 100 = 100%
   */
  public void setEchelle(int echelle) {
    this.echelle = echelle;
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
   * Modifie la position horizontale.
   *
   * @param horizontal Position horizontale.
   */
  public void setHorizontal(float horizontal) {
    this.horizontal = horizontal;
  }

  /**
   * Définit la position de l'élément dans l'image.
   *
   * @param x Coordonnée x.
   * @param y Coordonnée y.
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Modifie la position verticale de la vidéo.
   *
   * @param vertical Position verticale.
   */
  public void setVertical(float vertical) {
    this.vertical = vertical;
  }
}
