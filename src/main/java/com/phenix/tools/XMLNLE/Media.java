package com.phenix.tools.XMLNLE;

import com.phenix.tools.tools.Timecode;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class Media {

    /**
     * Nom du fichier.
     */
    private String nom_fichier;

    /**
     * Durée du média.
     */
    private Timecode duree;

    /**
     * Framerate du média.
     */
    private int framerate;

    /**
     * Timecode in.
     */
    private Timecode in;

    /**
     * Timecode out.
     */
    private Timecode out;

    /**
     * Timecode début du média.
     */
    private Timecode start;

    /**
     * Où se trouve le fichier.
     */
    private String localisation;

    /**
     * ...
     */
    private int balayage;

    /**
     * ...
     */
    private String trame;

    /**
     * ...
     */
    public static final int BALAYAGE_PROGRESSIF = 0;

    /**
     * ...
     */
    public static final int BALAYAGE_ENTRELACE = 1;

    /**
     * ...
     */
    public static final String TRAME_PAIRE = "...";

    /**
     * ...
     */
    public static final String TRAME_IMPAIRE = "upper";

    /**
     * ...
     */
    public static final String TRAME_AUCUNE = "none";

    /**
     * ...
     */
    private static int id_actuelle = 1;

    /**
     * Masterclip id.
     */
    private int id;

    /**
     * ...
     */
    private boolean utiliser;

    /**
     * Savoir ce qu'est le fichier: un fichier audio, vidéo, image, ... élément qui existe (png, mov, wav, ...) ou un élément généré (mir, décompte, ...).
     */
    protected String type_media = "media";

    /**
     * La couleur du média: Iris par défaut.
     */
    private String couleur = "Iris";

    /**
     * ...
     */
    public static final String COULEUR_IRIS = "Iris";

    /**
     * ...
     */
    public static final String COULEUR_MANGO = "Mango";

    /**
     * ...
     */
    public static final String COULEUR_ROSE = "Rose";

    /**
     * ...
     *
     * @param nom_fichier ...
     */
    public Media(String nom_fichier) {
        this.nom_fichier = nom_fichier;

        // Par défaut, balayage progressig (donc aucune trame).
        this.balayage = BALAYAGE_PROGRESSIF;
        this.trame = TRAME_AUCUNE;

        id = id_actuelle;
        id_actuelle++;
    }

    /**
     * ...
     *
     * @param nom_fichier ...
     * @param framerate ...
     */
    public Media(String nom_fichier, int framerate) {
        this.nom_fichier = nom_fichier;
        this.framerate = framerate;

        // Par défaut, balayage progressig (donc aucune trame).
        this.balayage = BALAYAGE_PROGRESSIF;
        this.trame = TRAME_AUCUNE;

        id = id_actuelle;
        id_actuelle++;
    }

    /**
     * ...
     *
     * @return ...
     */
    public String getTypeMedia() {
        return this.type_media;
    }

    /**
     * ...
     *
     * @return ...
     */
    public String getNomFichier() {
        return this.nom_fichier;
    }

    /**
     * ...
     *
     * @return ...
     */
    public Timecode getDuree() {
        this.duree = new Timecode((this.out.toImage() - this.in.toImage() + 1), this.framerate);
        return this.duree;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getFramerate() {
        return this.framerate;
    }

    /**
     * ...
     *
     * @param in ...
     */
    public void setIn(Timecode in) {
        this.in = in;
        // Si média a un framerate, on l'affecte au timecode reçu.
        if (this.framerate != 0) {
            this.in.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) this.in.getFramerate();
        }
    }

    /**
     * @param out
     */
    public void setOut(Timecode out) {
        this.out = out;
        if (this.framerate != 0) {
            this.out.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) this.out.getFramerate();
        }
    }

    /**
     * ...
     *
     * @return ...
     */
    public Timecode getIn() {
        return this.in;
    }

    /**
     * ...
     *
     * @return ...
     */
    public Timecode getOut() {
        return this.out;
    }

    /**
     * ...
     *
     * @param localisation ...
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    /**
     * Récupère la localisation du média.
     *
     * @return Chemin du média.
     */
    public String getLocalisation() {
        return this.localisation;
    }

    /**
     * Définit le timecode de début.
     *
     * @param start Timecode de début.
     */
    public void setStart(Timecode start) {
        this.start = start;
        if (this.framerate != 0) {
            this.start.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) this.start.getFramerate();
        }
    }

    /**
     * Modifie la couleur par défaut du média.
     *
     * @param couleur La nouvelle couleur.
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Retourne la couleur du média.
     *
     * @return La couleur.
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * ...
     *
     * @return ...
     */
    public Timecode getStart() {
        return this.start;
    }

    /**
     * ...
     *
     * @param balayage ...
     * @param trame ...
     */
    public void setBalayage(int balayage, String trame) {
        this.balayage = balayage;
        this.trame = trame;
    }

    /**
     * ...
     *
     * @return ...
     */
    public String getTrame() {
        return this.trame;
    }

    /**
     * Savoir si le média a déjà été utilisé.
     *
     * @return ...
     */
    public boolean dejaUtilise() {
        return this.utiliser;
    }

    /**
     * Indique que le média a été utilisé dans une timeline.
     */
    public void utiliser() {
        this.utiliser = true;
    }

    /**
     * Retourne l'ID du "master-clip".
     *
     * @return ...
     */
    public int getId() {
        return this.id;
    }

    /**
     * On modifie l'ID du master-clip.
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * ...
     *
     * @return ...
     */
    @Override
    public String toString() {
        String xml = "<clip id=\"masterclip-5\" explodedTracks=\"true\">\n"
                + "\t\t\t\t\t\t\t\t\t\t<uuid>8712fc3e-6ee7-459f-87d3-1866ff0a68fe</uuid>\n"
                + "\t\t\t\t\t\t\t\t\t\t<masterclipid>masterclip-5</masterclipid>\n"
                + "\t\t\t\t\t\t\t\t\t\t<ismasterclip>TRUE</ismasterclip>\n"
                + "\t\t\t\t\t\t\t\t\t\t<duration>40</duration>\n"
                + "\t\t\t\t\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                + "\t\t\t\t\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t<name>tu_memmerdes.mp3</name>\n"
                + "\t\t\t\t\t\t\t\t\t\t<media>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<audio>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t<track>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t<clipitem id=\"clipitem-5\">\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<masterclipid>masterclip-5</masterclipid>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<name>tu_memmerdes.mp3</name>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<file id=\"file-5\">\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<name>tu_memmerdes.mp3</name>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<pathurl>file://localhost/C%3a/Users/Edouard/Desktop/tu_memmerdes.mp3</pathurl>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<duration>40</duration>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<timecode>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<string>00;00;00;00</string>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<frame>0</frame>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<displayformat>DF</displayformat>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</timecode>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<media>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<audio>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<depth>16</depth>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<samplerate>48000</samplerate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<channelcount>1</channelcount>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<layout>stereo</layout>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<audiochannel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<sourcechannel>1</sourcechannel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<channellabel>left</channellabel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</audiochannel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</audio>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<audio>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<depth>16</depth>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<samplerate>48000</samplerate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<channelcount>1</channelcount>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<layout>stereo</layout>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<audiochannel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<sourcechannel>2</sourcechannel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<channellabel>right</channellabel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</audiochannel>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</audio>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</media>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</file>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<sourcetrack>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<trackindex>1</trackindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</sourcetrack>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<linkclipref>clipitem-6</linkclipref>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<trackindex>1</trackindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<linkclipref>clipitem-7</linkclipref>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<trackindex>2</trackindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t</clipitem>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t</track>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t<track>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t<clipitem id=\"clipitem-2\">\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<masterclipid>masterclip-1</masterclipid>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<name>tu_memmerdes.mp3</name>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<file id=\"file-5\"/>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<sourcetrack>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<trackindex>2</trackindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</sourcetrack>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<linkclipref>clipitem-6</linkclipref>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<trackindex>1</trackindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<linkclipref>clipitem-7</linkclipref>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<trackindex>2</trackindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</link>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t</clipitem>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t</track>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t</audio>\n"
                + "\t\t\t\t\t\t\t\t\t\t</media>\n"
                + "\t\t\t\t\t\t\t\t\t\t<logginginfo>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<description></description>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<scene></scene>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<shottake></shottake>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<lognote></lognote>\n"
                + "\t\t\t\t\t\t\t\t\t\t</logginginfo>\n"
                + "\t\t\t\t\t\t\t\t\t\t<labels>\n"
                + "\t\t\t\t\t\t\t\t\t\t\t<label2>Caribbean</label2>\n"
                + "\t\t\t\t\t\t\t\t\t\t</labels>\n"
                + "\t\t\t\t\t\t\t\t\t</clip>";
        return xml;
    }

}
