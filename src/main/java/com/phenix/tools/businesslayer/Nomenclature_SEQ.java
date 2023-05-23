package com.phenix.tools.businesslayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Note 1: Voir pour qu'une API pour avoir les données! Note 2: est lié au
 * projet "Name Convention".
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Nomenclature_SEQ {

    /**
     * Codec.
     */
    public static final String CODEC = "codec";

    /**
     * Type.
     */
    public static final String TYPE = "type";

    /**
     * Résolution.
     */
    public static final String RESOLUTION = "resolution";

    /**
     * Ratio.
     */
    public static final String RATIO = "ratio";

    /**
     * Cadence.
     */
    public static final String CADENCE = "cadence";

    /**
     * Studio.
     */
    public static final String STUDIO = "studio";

    /**
     * Nom du fichier.
     */
    private String nom_fichier;

    /**
     * Titre du programme.
     */
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
     * Liste des audios.
     */
    private List<Nomenclature_audio> audio = new ArrayList<Nomenclature_audio>();

    /**
     * Liste de propriétés.
     */
    private Map<String, Map<String, String>> proprietes = new HashMap<String, Map<String, String>>();

    /**
     * Initialise la nomenclature.
     */
    public Nomenclature_SEQ() {
        this.nom_fichier = null;
        this.initValeur();
    }

    /**
     * Plutot destiné pour le décodage des noms de fichier.
     *
     * @param nom_fichier Nom du fichier.
     */
    public Nomenclature_SEQ(String nom_fichier) {
        this.nom_fichier = nom_fichier;
        this.initValeur();
        this.DecodeNomFichier();
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
     * Ajoute un audio.
     *
     * @param audio L'audio à ajouter.
     */
    public void addAudio(Nomenclature_audio audio) {
        this.audio.add(audio);
    }

    /**
     * Décode le nom du fichier en propriétés.
     */
    private void DecodeNomFichier() {
        try {
            Scanner sc = new Scanner(this.nom_fichier).useDelimiter("\\s*_\\s*");

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
     * Retourne une propriété selon son nom.
     *
     * @param propriete Nom de la propriété.
     * @return La propriété.
     */
    public String get(String propriete) {
        return ((Entry<String, String>) (this.proprietes.get(propriete))).getValue();
    }

    /**
     * Retourne la partie audio.
     *
     * @return Texte audio.
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
     * Retourne le codec image.
     *
     * @return Codec image
     */
    public String getCodec() {
        return this.codec;
    }

    /**
     * Retrouve la valeur de la clef.
     *
     * @param map La map.
     * @param value La valeur.
     * @return La clef ou <code>null</code> si aucune valeur trouvée.
     */
    private String getKey(Map<String, String> map, String value) {
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
        this.EncodeNomFichier();
        return this.nom_fichier;
    }

    /**
     * Récupère la liste d'une propriété selon son nom.
     *
     * @param propriete Nom de la propriété.
     * @return Liste.
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
     * Retourne le type.
     *
     * @return Le type.
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
}
