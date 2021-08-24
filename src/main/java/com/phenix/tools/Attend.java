package com.phenix.tools;

/**
 * Objet servant a générer une "attente" dans un programme.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.18.0
 * @since Kit objet V1
 */
public class Attend extends Thread {

  /* -- Constructors -- */
  /**
   * Constructeur qui demande comme paramètre un nombre de miliseconde pour
   * générer l'attente.
   *
   * @param temps Temps en milisconde.
   */
  public Attend(int temps) {
    //On doit mettre un 'try' quand on veut utiliser le 'sleep'.
    try {
      //Ici, il y a un temps d'attende, le temps d'attende est en milliseconde:
      sleep(temps);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

}
