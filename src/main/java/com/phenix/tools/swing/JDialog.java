package com.phenix.tools.swing;

import java.awt.HeadlessException;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Fenêtre premettant de choisir un fichier et renvoie le nom du fichier (sans
 * l'extension et ou le chemin) sélectionné.
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 3.9.0
 */
public class JDialog extends JPanel {

    /**
     * Si on séléctionne un fichier ou un dossier ou encore les 2.
     */
    public static int selectionner_dossier = JFileChooser.FILES_ONLY;

    /**
     * TODO
     */
    private JFileChooser fc = new JFileChooser();

    /**
     * TODO
     */
    private File fichier;

    /**
     * Les valeurs de 'JDialog'
     */
    private String valeur;

    /**
     * TODO
     */
    private String repertoire;

    /**
     * Montrer l'extension du fichier.
     */
    private boolean extension = true;

    /**
     * Montrer le chemin du fichier.
     */
    private boolean chemin = false;

    /**
     * Renvoyera une valeur plus tard.
     *
     * @param repertoire ...
     * @param extension ...
     * @param chemin ...
     */
    public JDialog(String repertoire, boolean extension, boolean chemin) {
        this.repertoire = repertoire;
        this.extension = extension;
        this.chemin = chemin;
    }

    /**
     * TODO
     *
     * @param repertoire ...
     * @param extension ...
     */
    public JDialog(String repertoire, boolean extension) {
        this.repertoire = repertoire;
        this.extension = extension;
    }

    /**
     * TODO
     *
     * @param repertoire ...
     */
    public JDialog(String repertoire) {
        this.repertoire = repertoire;
    }

    /**
     * TODO
     *
     * @param extension ...
     */
    public JDialog(boolean extension) {
        this.extension = extension;
    }

    /**
     * TODO
     */
    public JDialog() {
    }

    /**
     * Renvoie la valeur:
     *
     * @return ...
     */
    public String JDialog() {
        Void_JDialog();

        return Valeur();
    }

    /**
     * TODO
     *
     * @param repertoire ...
     */
    public void setCurrentDirectory(File repertoire) {
        fc.setCurrentDirectory(repertoire);
    }

    /**
     * TODO
     */
    private void Void_JDialog() {

        //On met un try parce que parfois il y a des erreurs bizarres.
        try {
            if (fc.showDialog(JDialog.this, "OK") == JFileChooser.APPROVE_OPTION) {
                fichier = fc.getSelectedFile();

                valeur = Valeur();
            }

        } catch (HeadlessException e) {
        }

    }

    /**
     * Renvoie de la valeur.
     *
     * @return ...
     */
    public String Valeur() {
        if (chemin) {
            if (extension) {
                valeur = "" + fichier;
            } else {
                valeur = Extension("" + fichier);
            }
        } else if (extension) {
            valeur = fichier.getName();
        } else {
            valeur = Extension(fichier.getName());
        }

        return valeur;
    }

    //-- Il y a la même chose dans le fichier repertoire: --
    /**
     * Enleve l'extension.
     *
     * @param nom ...
     * @return ...
     */
    public String Extension(String nom) {
        while (!"".equals(nom)) {

            if (".".equals(nom.substring(nom.length() - 1))) {
                return nom.substring(0, nom.length() - 1);
            }

            //On remet le mot 'nom' dans un String mais sans son dernier caract�re.
            nom = nom.substring(0, nom.length() - 1);

        }

        return nom;
    }

    /**
     * TODO
     *
     * @return ...
     */
    public String Repertoire() {
        return repertoire;
    }

    /**
     * TODO
     *
     * @return ...
     */
    public boolean Extension() {
        return extension;
    }

    /**
     * Définit des valeurs.
     *
     * @param repertoire ...
     */
    public void Repertoire(String repertoire) {
        this.repertoire = repertoire;
    }

    /**
     * TODO
     *
     * @param extension ...
     */
    public void Extension(boolean extension) {
        this.extension = extension;
    }

    /**
     * TODO
     *
     * @param chemin ...
     */
    public void Chemin(boolean chemin) {
        this.chemin = chemin;
    }

    /**
     * Réeniasaliter la valeur.
     *
     * @param t ...
     */
    public void Valeur(boolean t) {
        this.valeur = null;
        this.fichier = null;
        fc = new JFileChooser();
    }

    /**
     * TODO
     *
     * @param option ...
     */
    public void setFileSelectionMode(int option) {
        fc.setFileSelectionMode(option);
    }

    /**
     * TODO
     *
     * @param f ...
     */
    public void addChoosableFileFilter(FileFilter f) {
        fc.addChoosableFileFilter(f);
    }

}

/**
 * Filtre des extensions
 *
 * @author Edouard Jeanjean
 */
class Filtre extends FileFilter {

    /**
     * Description et extension acceptée par le filtre.
     */
    private String description;

    /**
     * Description et extension acceptée par le filtre.
     */
    private String extension;

    /**
     * Constructeur à partir de la description et de l'extension acceptée.
     *
     * @param description ...
     * @param extension ...
     */
    public Filtre(String description, String extension) {
        if (description == null || extension == null) {
            throw new NullPointerException("La description (ou extension) ne peut �tre null.");
        }
        this.description = description;
        this.extension = extension;
    }

    /**
     * Implémentation de FileFilter.
     *
     * @param file ...
     * @return ...
     */
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String nomFichier = file.getName().toLowerCase();

        return nomFichier.endsWith(extension);
    }

    /**
     * TODO
     *
     * @return ...
     */
    @Override
    public String getDescription() {
        return description;
    }
}
