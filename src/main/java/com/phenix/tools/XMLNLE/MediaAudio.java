package com.phenix.tools.XMLNLE;

/**
 * Média de type audio.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class MediaAudio extends Media {

  /**
   * Construit un média audio.
   *
   * @param nom_fichier Nom du fichier.
   */
  public MediaAudio(String nom_fichier) {
    super(nom_fichier);
    type_media = "audio";
  }
}
