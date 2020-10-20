package com.phenix.tools.XMLNLE;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class MediaVideoGenere extends MediaVideo {

    /**
     * TODO
     */
    public final static int VIDEO_NOIR = 0;

    /**
     * TODO
     */
    public final static int CALQUE_EFFET = 1;

    /**
     * Qu'est-ce qui est généré.
     */
    private int element_genere;

    /**
     * ...
     *
     * @param element_genere ...
     * @param framerate      ...
     */
    public MediaVideoGenere(int element_genere, int framerate) {
        super("Vidéo noire", framerate);
        type_media = "genere";
        this.element_genere = element_genere;
    }
}