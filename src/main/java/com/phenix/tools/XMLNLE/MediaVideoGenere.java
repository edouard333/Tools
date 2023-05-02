package com.phenix.tools.XMLNLE;

/**
 * Média généré (dans le logiciel) et pas issu d'un fichier.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class MediaVideoGenere extends MediaVideo {

    /**
     * ID du média vidéo noire.
     */
    public static final int VIDEO_NOIRE = 0;

    /**
     * ID du média calque d'effet.
     */
    public static final int CALQUE_EFFET = 1;

    /**
     * Qu'est-ce qui est généré.
     */
    private int element_genere;

    /**
     * Définit un élément généré via son ID et un framerate.<br>
     * Pour l'instant, ne gère que les "Vidéo noire".
     *
     * @param element_genere ID de l'élément généré.
     * @param framerate Framerate.
     */
    public MediaVideoGenere(int element_genere, int framerate) {
        super("Vidéo noire", framerate);
        super.type_media = "genere";
        this.element_genere = element_genere;
    }
}
