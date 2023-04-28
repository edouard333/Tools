package com.phenix.tools.XMLNLE;

/**
 * Média de type image.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class MediaImage extends Media {

    /**
     * Construit un média image.
     *
     * @param nom_fichier Nom du fichier.
     */
    public MediaImage(String nom_fichier) {
        super(nom_fichier);
        type_media = "image";
    }
}
