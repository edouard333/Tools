package com.phenix.tools.businesslayer;

import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;
import java.util.Map.Entry;

import java.util.List;
import java.util.ArrayList;

/**
 * Note 1: Voir pour qu'une API pour avoir les données! Note 2: est lié au
 * projet "Name Convention".
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 0.1.0
 */
public class Nomenclature_SEQ {

  /**
   * Nom du fichier.
   */
  private String nom_fichier;

  /**
   * Liste des propriétés.
   */
  public static final String CODEC = "codec";
  public static final String TYPE = "type";
  public static final String RESOLUTION = "resolution";
  public static final String RATIO = "ratio";
  public static final String CADENCE = "cadence";
  public static final String STUDIO = "studio";

  private String titre;

  /**
   * Si c'est un FTR (long métrage), DOC (documentaire), etc.
   */
  private String type;

  /**
   * Codec image.
   */
  private String codec;

  /**
   * Ratio image.
   */
  private String ratio;

  /**
   * Résolution de l'image.
   */
  private String resolution;

  /**
   * TODO
   */
  private List<Nomenclature_audio> audio = new ArrayList<Nomenclature_audio>();

  /**
   * TODO
   */
  private Map<String, Map<String, String>> proprietes = new HashMap<String, Map<String, String>>();

  /**
   * TODO
   */
  public Nomenclature_SEQ() {
    this.nom_fichier = null;
    initValeur();
  }

  /**
   * Plutot destiné pour le décodage des noms de fichier.
   *
   * @param nom_fichier Nom du fichier.
   */
  public Nomenclature_SEQ(String nom_fichier) {
    this.nom_fichier = nom_fichier;
    initValeur();
    DecodeNomFichier();
  }

  /**
   * Initiale les valeurs.
   */
  private void initValeur() {

    Map<String, String> liste_type = new HashMap<String, String>();
    Map<String, String> liste_codec = new HashMap<String, String>();
    Map<String, String> liste_resolution = new HashMap<String, String>();
    Map<String, String> liste_ratio = new HashMap<String, String>();
    Map<String, String> liste_framerate = new HashMap<String, String>();
    Map<String, String> liste_langue_sous_titre = new HashMap<String, String>();
    Map<String, String> liste_studio = new HashMap<String, String>();
    Map<String, String> liste_cadence = new HashMap<String, String>();

    liste_type.put("FTR", "film");
    liste_type.put("SHR", "court metrage");
    liste_type.put("ADV", "publicite");

    liste_codec.put("PR444XQ", "Apple Pro Res 4444 XQ");
    liste_codec.put("PR444", "Apple Pro Res 444");
    liste_codec.put("PR422HQ", "Apple Pro Res 422 HQ");
    liste_codec.put("PR422LT", "Apple Pro Res 422 LT");
    liste_codec.put("PR422PROXY", "Apple Pro Res 422 PROXY");

    liste_resolution.put("2160", "3860x2160");
    liste_resolution.put("1080", "1920x1080");
    liste_resolution.put("576", "1024x576");
    liste_resolution.put("576", "720x576");

    liste_ratio.put("239", "2.39");
    liste_ratio.put("185", "1.85");

    liste_cadence.put("25", "25");
    liste_cadence.put("24", "24");
    liste_cadence.put("2398", "23.976");

    liste_langue_sous_titre.put("FR", "francais");
    liste_langue_sous_titre.put("EN", "anglais");

    liste_studio.put("SEQ", "Studio l'Equipe");

    this.proprietes.put(STUDIO, liste_studio);
    this.proprietes.put(CODEC, liste_codec);
    this.proprietes.put(TYPE, liste_type);
    this.proprietes.put(RATIO, liste_ratio);
    this.proprietes.put(RESOLUTION, liste_resolution);
  }

  /**
   * TODO
   *
   * @param audio TODO
   */
  public void addAudio(Nomenclature_audio audio) {
    this.audio.add(audio);
  }

  /**
   * Décode le nom du fichier en propriétés.
   */
  private void DecodeNomFichier() {
    try {
      Scanner sc = new Scanner(this.nom_fichier).useDelimiter("\\s*_\\s*");;

      this.titre = sc.next();

      this.type = this.proprietes.get(TYPE).get(sc.next());

      this.codec = this.proprietes.get(CODEC).get(sc.next());

      sc.close();
    } catch (Exception exception) {
      System.out.println("Une erreur est survenue: " + exception);
    }
  }

  /**
   * Encode le nom du fichier.
   */
  private void EncodeNomFichier() {
    this.nom_fichier = this.titre;
    this.nom_fichier += "_" + getKey(this.proprietes.get(TYPE), this.type);
    this.nom_fichier += "_" + getKey(this.proprietes.get(CODEC), this.codec);
    this.nom_fichier += "_" + getKey(this.proprietes.get(RESOLUTION), this.resolution);
    this.nom_fichier += "-" + getKey(this.proprietes.get(RATIO), this.ratio);
    this.nom_fichier += "_" + getAudio();
  }

  /**
   * TODO
   *
   * @param propriete TODO
   *
   * @return TODO
   */
  public String get(String propriete) {
    return ((Entry<String, String>) (this.proprietes.get(propriete))).getValue();
  }

  /**
   * TODO
   *
   * @return TODO
   */
  public String getAudio() {
    String piste = "";
    for (int i = 0; i < this.audio.size(); i++) {
      if (i != 0) {
        piste += "-";
      }
      piste += this.audio.get(i);
    }
    return piste;
  }

  /**
   * Récupère le codec image.
   *
   * @return Codec image
   */
  public String getCodec() {
    return this.codec;
  }

  /**
   * Retrouve la valeur de la clef.
   */
  private String getKey(Map<String, String> map, String value) {
    System.out.println(map);
    for (Entry<String, String> entry : map.entrySet()) {
      String cle = entry.getKey();
      String valeur = entry.getValue();
      if (valeur.equals(value)) {
        return cle;
      }
    }
    return null;
  }

  /**
   * Retourne le nom du fichier.
   *
   * @return Nom du fichier.
   */
  public String getNomFichier() {
    EncodeNomFichier();
    return this.nom_fichier;
  }

  /**
   * TODO
   *
   * @param propriete TODO
   *
   * @return TODO
   */
  public Map<String, String> getListe(String propriete) {
    switch (propriete) {
      case "codec":
        return this.proprietes.get(propriete);
      default:
        return null;
    }
  }

  /**
   * Retourne le titre.
   *
   * @return Titre.
   */
  public String getTitre() {
    return this.titre;
  }

  /**
   * TODO
   *
   * @return TODO
   */
  public String getType() {
    return this.type;
  }

  /**
   * Définit le codec image.
   *
   * @param codec Codec.
   */
  public void setCodec(String codec) {
    this.codec = codec;
  }

  /**
   * Définit le nom du fichier.
   *
   * @param nom_fichier Nom du fichier.
   */
  public void setNomFichier(String nom_fichier) {
    this.nom_fichier = nom_fichier;
  }

  /**
   * Définit le ratio image.
   *
   * @param ratio Ratio.
   */
  public void setRatio(String ratio) {
    this.ratio = ratio;
  }

  /**
   * Définit la résolution.
   *
   * @param resolution Résolution (en hauteur de pixel).
   */
  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  /**
   * Définit le titre.
   *
   * @param titre Titre.
   */
  public void setTitre(String titre) {
    this.titre = titre;
  }

  /**
   * Indique si s'est un FTR, DOC, SHR, etc.
   *
   * @param type Type en abréviation.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Pour les tests.
   *
   * @param args Paramètres reçus.
   */
  public static void main(String[] args) {
    Nomenclature_SEQ n = new Nomenclature_SEQ(/*"XXX_FTR_PR444_1080-239_50i_.mov"*/);
    /*System.out.println(n.getTitre());
      System.out.println(n.getType());
      System.out.println(n.getCodec());*/

    n.setTitre("Moi-moche");

    n.setResolution("1920x1080");

    n.setRatio("2.39");

    n.setType("film");

    n.setCodec("Apple Pro Res 444");

    n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
    n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
    n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
    System.out.println(n.getNomFichier());
    //System.out.println(n.getAudio());

  }
}
