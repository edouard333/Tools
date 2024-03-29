package com.phenix.tools.XMLNLE;

/**
 * Média de type image.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class MediaImage extends Media {

    /**
     * Construit un média image.
     *
     * @param nom_fichier Nom du fichier.
     */
    public MediaImage(String nom_fichier) {
        super(nom_fichier);
        super.type_media = "image";
    }
}
