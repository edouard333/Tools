package com.phenix.tools;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * Il sert à aller sur le navigateur par défaut aller sur un site.
 *
 * @author  Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 2.5.0
 */
public class Internet {

    private Desktop bureau = Desktop.getDesktop();

    public Internet(String url) {

        try {
            if (bureau.isSupported(Desktop.Action.BROWSE))
               try {
                bureau.browse(new URI(url));
            } catch (IOException e) {
                throw new IOException("L'url n'est pas correcte.");
            } catch (Exception e) {
                throw new IOException(e);
            } else {
                throw new IOException("L'utilisation d' 'internet' n'est pas supporte.");
            }
        } catch (Exception ex) {
        }

    }
}
