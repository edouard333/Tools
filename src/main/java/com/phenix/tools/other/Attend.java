package com.phenix.tools.other;

/**
 * Objet servant à générer une "attente" dans un programme.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Attend extends Thread {

    /**
     * Pour ne pas instancier la classe.
     */
    private Attend() throws Exception {
        throw new Exception("Cette classe ne peut pas être instanciée.");
    }

    /**
     * Construit une {@link Attend} pour générer une attente reçu en paramètre.
     *
     * @param temps Temps en milliseconde.
     */
    public static void delais(int temps) {
        // On doit mettre un 'try' quand on veut utiliser le 'sleep'.
        try {
            // Ici, il y a un temps d'attente, le temps d'attente est en milliseconde :
            sleep(temps);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
