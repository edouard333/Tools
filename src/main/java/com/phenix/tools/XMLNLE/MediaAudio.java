package com.phenix.tools.XMLNLE;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class MediaAudio extends Media {

    /**
     * TODO
     *
     * @param nom_fichier TODO
     */
    public MediaAudio(String nom_fichier) {
        super(nom_fichier);
        type_media = "audio";
    }
}