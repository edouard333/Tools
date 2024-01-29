package com.phenix.tools.other;

/**
 * Objet servant à générer une "attente" dans un programme.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.18.0
 * @since Kit objet V1
 */
public class Attend extends Thread {

    /**
     * Construit une {@code Attend} pour générer une attente reçu en paramètre.
     *
     * @param temps Temps en milliseconde.
     */
    public Attend(int temps) {
        // On doit mettre un 'try' quand on veut utiliser le 'sleep'.
        try {
            // Ici, il y a un temps d'attente, le temps d'attente est en milliseconde :
            sleep(temps);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
