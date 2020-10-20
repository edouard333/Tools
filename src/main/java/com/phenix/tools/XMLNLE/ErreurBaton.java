package com.phenix.tools.XMLNLE;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class ErreurBaton {

    /**
     * ...
     */
    private String description;

    /**
     * ...
     */
    private int duree;

    /**
     * ...
     */
    private String tcStart;

    /**
     * ...
     */
    private String tcEnd;

    /**
     * ...
     */
    private String item;

    /**
     * ID pour le codec "DPX".
     */
    public static final int CODEC_DPX_HD = 0;

    /**
     * ID pour le codec "Apple Pro Res 4444 XQ"
     */
    public static final int CODEC_PR4444XQ = 1;
    public static final int CODEC_PR4444_UHD = 2;
    public static final int CODEC_PR4444 = 3;
    public static final int CODEC_PR422HQ = 4;
    public static final int CODEC_PR422PROXY = 5;

    /**
     * Liste des codecs possibles pour les erreurs.
     */
    private boolean codec[] = new boolean[6];

    /**
     * Cr�� un ojet <code>ErreurBaton</code>.
     *
     * @param description
     * @param duree
     * @param tcStart
     * @param tcEnd
     * @param item
     * @param idCodec
     */
    public ErreurBaton(String description, int duree, String tcStart, String tcEnd, String item, int idCodec) {
        this.description = description;
        this.duree = duree;
        this.tcStart = tcStart;
        this.tcEnd = tcEnd;
        this.item = item;

        for (int i = 0; i < this.codec.length; i++) {
            this.codec[i] = false;
        }

        this.codec[idCodec] = true;
    }

    /**
     * Compare deux erreurs entre-elle.
     *
     * @param e L'erreur a comparer avec celle actuelle.
     * @return Retourne <true> si les deux erreurs sont identiques (voir la
     * m�thode <code>toString</code> d�finissant les crit�res).
     */
    public boolean compare(ErreurBaton e) {
        if (toString().equals(e.toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ajoute le codec d'une autre erreur.
     *
     * @param erreur L'erreur dont on doit r�cup�rer le codec.
     */
    public void addCodec(ErreurBaton erreur) {
        this.codec[CODEC_DPX_HD] = (erreur.getCodecB(CODEC_DPX_HD)) ? true : this.codec[CODEC_DPX_HD];
        this.codec[CODEC_PR4444XQ] = (erreur.getCodecB(CODEC_PR4444XQ)) ? true : this.codec[CODEC_PR4444XQ];
        this.codec[CODEC_PR4444_UHD] = (erreur.getCodecB(CODEC_PR4444_UHD)) ? true : this.codec[CODEC_PR4444_UHD];
        this.codec[CODEC_PR4444] = (erreur.getCodecB(CODEC_PR4444)) ? true : this.codec[CODEC_PR4444];
        this.codec[CODEC_PR422HQ] = (erreur.getCodecB(CODEC_PR422HQ)) ? true : this.codec[CODEC_PR422HQ];
        this.codec[CODEC_PR422PROXY] = (erreur.getCodecB(CODEC_PR422PROXY)) ? true : this.codec[CODEC_PR422PROXY];
    }

    /**
     * Retourne la description de l'erreur (selon Baton).
     *
     * @return La descreption.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retourne la dur�e en nombre d'image de l'erreur.
     *
     * @return
     */
    public int getDuree() {
        return this.duree;
    }

    /**
     * Retourne le timecode de d�but en SMPTE de l'erreur.
     *
     * @return
     */
    public String getTcStart() {
        return this.tcStart;
    }

    /**
     * Retourne le timecode de fin en SMPTE de l'erreur.
     *
     * @return
     */
    public String getTcEnd() {
        return this.tcEnd;
    }

    /**
     * Retourne le type d'erreur (selon Baton).
     *
     * @return
     */
    public String getItem() {
        return this.item;
    }

    /**
     * Retourne un bool�an disant si le codec est pr�sent dans l'erreur ou non.
     *
     * @param idCodec ID du codec dans la liste des codecs.
     * @return
     */
    public boolean getCodecB(int idCodec) {
        return this.codec[idCodec];
    }

    /**
     * Retourne si le codec est pr�sent dans l'erreur avec mise en page.
     *
     * @param idCodec
     * @return
     */
    public String getCodec(int idCodec) {
        return (this.codec[idCodec]) ? "<span style= 'color: green'><strong>X</strong></span>" : "";
    }

    @Override
    public String toString() {
        return this.description + " " + this.duree + " " + this.tcStart + " " + this.item;
    }

}
