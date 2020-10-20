package com.phenix.tools.structure;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 0.1.0
 */
public abstract class StructureXML {
    // Les unités de mesure:

    public final static String[] UNITE_POIDS = new String[]{"unity", "Mo"};
    public final static String[] UNITE_ECHANTILLON = new String[]{"unity", "bit"};
    public final static String[] UNITE_FREQUENCE_AUDIO = new String[]{"unity", "kHz"};
    public final static String[] UNITE_FREQUENCE_IMAGE = new String[]{"unity", "fps"};
    public final static String[] UNITE_DEBIT = new String[]{"unity", "Mo/s"};
    public final static String[] UNITE_TIMECODE = new String[]{"unity", "tc"};
    public final static String[] UNITE_RESOLUTION = new String[]{"unity", "px"};

    // Les formats de données:
    public final static String[] FORMAT_TEMPS = new String[]{"format", "hh:mm:ss:ii"};

    // Nom des nodes:
    public final static String NODE_DOCUMENT = "document";

    public final static String NODE_COMMUN = "common";
    public final static String NODE_IMAGE = "image";
    public final static String NODE_AUDIO = "audio";
    public final static String NODE_SOUSTITRE = "subtitle";
    public final static String NODE_QC = "QC";
    public final static String NODE_TIMECODE = "timecode";

    // Valeurs des noeuds:
    public final static String DOCUMENT_VERSION = "version";

    // Commun:
    public final static String COMMUN_FICHIER = "file";
    public final static String COMMUN_PRODUCTION = "production";
    public final static String COMMUN_TITRE = "title";
    public final static String COMMUN_TYPE = "type";
    public final static String COMMUN_POIDS = "size";
    public final static String COMMUN_CHECKSUM = "checksum";
    public final static String COMMUN_INFORMATION = "information";

    // Image:
    public final static String IMAGE_CODEC = "codec";

    public final static String IMAGE_CODEC_PRORES422HQ = "Apple Pro Res 422 HQ";

    public final static String IMAGE_RESOLUTION = "resolution";

    public final static String IMAGE_RESOLUTION_HAUTEUR = "height";
    public final static String IMAGE_RESOLUTION_LARGEUR = "width";

    public final static String IMAGE_RATIO = "ratio";
    public final static String IMAGE_RATIO_239 = "2.39";

    public final static String IMAGE_BALAYAGE = "balayage";
    public final static String IMAGE_BALAYAGE_PROGRESSIF = "P";
    public final static String IMAGE_BALAYAGE_ENTRELACE = "I";
    public final static String IMAGE_BALAYAGE_PSF = "PsF";

    public final static String IMAGE_WATERMARK = "watermark";
    public final static String IMAGE_INFORMATION = "information";

    // Audio:
    public final static String AUDIO_CODEC = "codec";
    public final static String AUDIO_ECHANTILLON = "sample";
    public final static String AUDIO_FREQUENCE = "frequency";
    public final static String AUDIO_NOMBRE_PISTE = "numberTrack";
    public final static String AUDIO_VERSION = "version";
    public final static String AUDIO_MIXE = "mix";
    public final static String AUDIO_CANAUX = "canaux";
    public final static String AUDIO_CANAL = "canal";

    // Sous-titre:
    public final static String SOUSTITRE_PRESENT = "present";
    public final static String SOUSTITRE_NORME = "norme";
    public final static String SOUSTITRE_LANGUE = "language";
    public final static String SOUSTITRE_TYPE = "type";
    public final static String SOUSTITRE_PRESENCE_SOUSTITRE_PARTIEL = "partialSubtitlePresent";
    public final static String SOUSTITRE_INFORMATION = "information";

    // QC:
    public final static String QC_ACCEPTE = "accepted";
    public final static String QC_DEROULEMENT = "timeline";
    public final static String QC_REMARQUE = "remarks";

    // Timecode:
    public final static String TIMECODE_DEBUT = "start";
    public final static String TIMECODE_DUREE = "duration";
    public final static String TIMECODE_CADENCE = "framerate";
    public final static String TIMECODE_DROPFRAME = "NDF";

    /**
     * Valeur par défaut dans l'XML pour les valeurs non assigné.
     *
     * @return ...
     */
    protected String defaultValue() {
        return "non renseigne";
    }
}
