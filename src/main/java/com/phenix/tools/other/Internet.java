package com.phenix.tools.other;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * Il sert à aller sur le navigateur par défaut aller sur un site.
 *
 * @author  <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 2.5.0
 */
public class Internet {

    /**
     * L'outil pour accéder à internet.
     */
    private Desktop bureau = Desktop.getDesktop();

    /**
     * Accès à internet avec une URL.
     *
     * @param url URL de la page web.
     */
    public Internet(String url) {

        try {
            // On vérifie que l'objet est supporté.
            if (bureau.isSupported(Desktop.Action.BROWSE))
               try {
                bureau.browse(new URI(url));
            } catch (IOException exception) {
                throw new IOException("L'URL n'est pas correcte.");
            } catch (Exception exception) {
                throw new IOException(exception);
            } else {
                throw new IOException("L'utilisation d' 'internet' n'est pas supporte.");
            }
        } catch (Exception exception) {
        }

    }
}
