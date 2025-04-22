package com.phenix.tools;

import jakarta.validation.constraints.NotNull;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * Il sert à aller sur le navigateur par défaut aller sur un site.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Internet {

    /**
     * L'outil pour accéder à internet.
     */
    @NotNull
    private final Desktop bureau = Desktop.getDesktop();

    /**
     * Accès à internet avec une URL.
     *
     * @param url URL de la page web.
     */
    public Internet(String url) {
        try {
            // On vérifie que l'objet est supporté.
            if (this.bureau.isSupported(Desktop.Action.BROWSE))
               try {
                this.bureau.browse(new URI(url));
            } catch (IOException exception) {
                throw new IOException("L'URL n'est pas correcte.", exception);
            } catch (Exception exception) {
                throw new IOException(exception);
            } else {
                throw new IOException("L'utilisation d' 'internet' n'est pas supporte.");
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
