package com.phenix.tools;

/**
 * Objet servant a g�n�rer une "attente" dans un programme.
 *
 * @author  Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.18.0
 * @since Kit objet V1
 */
public class Attend extends Thread {

    /* -- Constructors -- */
    /**
     * Constructeur qui demande comme param�tre un nombre de miliseconde pour
     * g�n�rer l'attente.
     *
     * @param temps ...
     */
    public Attend(int temps) {
        //On doit mettre un 'try' quand on veut utiliser le 'sleep'.
        try {
            //Ici, il y a un temps d'attende, le temps d'attende est en milliseconde:
            sleep(temps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
