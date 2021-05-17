package com.phenix.tools;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.io.FileOutputStream;

/**
 * Il sert à créer ou à supprimer un répertoire/dossier et a il regarder tous
 * les fichiers qu'il en contient. Attention: Ce fichier ne fait que des
 * répertoires et ne peut être utilisé pour créer des fichiers!
 *
 * @version 3.61.0
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 */
public class Repertoire {

    /**
     * Créer un fichier 'répertoire'.
     */
    private File repertoire;

    /**
     * TODO
     */
    private String nom;

    /**
     * TODO
     */
    private File[] fichier;

    /**
     * TODO
     */
    private int i;

    /**
     * Propriété du bureau.
     */
    private Desktop bureau = Desktop.getDesktop();

    /**
     * ça c'est si le fichier 'répertoire' à une valeur.
     */
    private boolean valeur = false;

    /**
     * TODO
     */
    public Repertoire() {
    }

    /**
     * TODO
     *
     * @param nom
     */
    public Repertoire(String nom) {
        try {
            Repertoire(this.nom = nom);
        } catch (Exception e) {
            this.nom = nom;
        }
    }

    /**
     * On sait s'il y a un dossier et on donne au fichier 'repertoire' les valeurs.
     *
     * @param dossier
     */
    private void Repertoire(String dossier) {
        repertoire = new File(dossier);
        i = -1;

        if (!repertoire.exists()) {
            //System.out.println("Le fichier/repertoire '"+repertoire+"' n'existe pas");

            //C'est pour permettre � l'utilisateur de le cr�er.
            valeur = true;
        } else if (!repertoire.isDirectory())
           //System.out.println("Le chemin '"+repertoire+"' correspond � un fichier et non a un repertoire")
            ; else {
            fichier = repertoire.listFiles();
            valeur = true;
        }

    }

    /**
     * Ici, on cr�� un r�pertoire et renvoie 'true' s'il a �t� cr��, sinon il renvoie 'false':
     * Et si le r�pertoire a �t� d�j� d�finit.
     *
     * @return
     */
    public boolean Creer() {
        try {
            return Creer(this.nom);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO
     *
     * @param nom
     * @return
     */
    public boolean Creer(String nom) {
        try {
            //On cr�� le r�pertoire:
            return new File(nom).mkdirs();
        } catch (Exception e) {
            //Si le r�pertoire existe d�j� alors on renvoie 'true':
            if (new File(nom).exists()) {
                return true;
            } //Sinon on renvoie 'false':
            else {
                return false;
            }
        }
    }

    /**
     * Ici on supprime un r�pertoire et renvoie 'true' s'il a �t� supprim�, sinon il renvoie 'false':
     * Et si le r�pertoire a �t� d�j� d�finit.
     *
     * @return
     */
    public boolean Supprimer() {
        try {
            return Supprimer(repertoire.getAbsolutePath());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ici, on supprime un r�pertoire et renvoie 'true' s'il a �t� supprim�, sinon il renvoie 'false'.
     *
     * @param nom
     * @return
     */
    public boolean Supprimer(String nom) {

        try {
            return new File(nom).delete();
        } catch (Exception e) {
            System.out.println("Erreur:\n" + e);
            return false;
        }

    }

    /**
     * TODO
     *
     * @return
     */
    public boolean Supprimer_dossier() {
        return Supprimer_dossier(repertoire.getAbsolutePath());
    }

    /**
     * TODO
     *
     * @param nom
     * @return
     */
    public boolean Supprimer_dossier(String nom) {
        System.out.println("On supprime le dossier: " + nom);

        Repertoire dossier = new Repertoire(nom);

        Repertoire dossier2 = new Repertoire(nom);

        for (int i = 0; i < dossier2.Nb_Fichier(); i++) {
            System.out.println("L'element du dossier (" + nom + ") a supprimer: " + dossier.Fichier(i));

            if (new Repertoire(nom + "//" + dossier2.Fichier(i)).isDirectory()) {
                System.out.println("C'est un dossier!");
                Supprimer_dossier(nom + "//" + dossier2.Fichier(i));
            } else {
                System.out.println("C'est un fichier! (" + nom + "//" + dossier2.Fichier(i) + ")");
                Supprimer(nom + "//" + dossier2.Fichier(i));
            }
        }

        return dossier2.Supprimer();

    }

    /**
     * Voir si c'est bien un dossier.
     *
     * @return
     */
    public boolean isDirectory() {
        return repertoire.isDirectory();
    }

    /**
     * Il montre un fichier du r�pertoire sans son extension au num�ro ... :
     * Attention: Si le num�ro va renvoyer un dossier, il renvoyera le nom du dossier!
     *
     * @param nb
     * @return
     */
    public String FichierNom(int nb) {
        //Il renvoie le nom du fichier sans l'extension.
        if (valeur && fichier[nb].exists())
            try {
            return Extension(fichier[nb].getName(), true);
        } catch (Exception e) {
            //S'il n'y a pas de fichier
            try {
                return fichier[nb].getName();
            } catch (Exception f) {
                return Message2();
            }
        } else {
            return Message();
        }
    }

    /**
     * Il montre un fichier du r�pertoire.
     *
     * @return
     */
    public String Next_Fichier() {
        if (valeur && fichier[i + 1].exists()) {
            //Try pour quand le dossier n'a pas de fichier et ou de dossier:
            try {
                i++;
                //Il renvoie le nom du fichier avec l'extension.
                return fichier[i].getName();
            } catch (Exception e) {
                return Message2();
            }
        } else {
            return Message();
        }
    }

    /**
     * On remet le compteur à zéro.
     *
     * @param valeur
     */
    public void Next_Fichier(boolean valeur) {
        i = -1;
    }

    /**
     * Il montre un fichier du répertoire au numéro ...
     *
     * @param nb
     * @return
     */
    public String Fichier(int nb) {
        return Fichier(nb, true);
    }

    /**
     * TODO
     *
     * @param nb
     * @param avec
     * @return
     */
    public String Fichier(int nb, boolean avec) {
        //Il renvoie le nom du fichier avec l'extension.
        if (valeur && fichier[nb].exists()) {
            return Extension(fichier[nb].getName(), avec);
        } else {
            return Message();
        }
    }

    /**
     * Il dit s'il y a des fichiers: s'il renvoie 'true' c'est qu'il y a des fichiers, sinon il renvoie 'false'.
     *
     * @return
     */
    public boolean Fichier() {
        //Si le nombre de fichier est �gal � z�ro, il renvoie false:

        if (fichier.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Il montre les fichiers du répertoire.
     *
     * @param extension
     * @return
     */
    public String[] Fichiers(boolean extension) {
        String fichiers[];

        if (valeur && repertoire.exists()) {

            fichiers = new String[fichier.length];

            //Il renvoie le nom des fichiers sans l'extension:
            for (int i = 0; i < fichiers.length; i++) {
                fichiers[i] = Extension(fichier[i].getName(), extension);
            }

            return fichiers;
        } else {
            return new String[]{Message()};
        }

    }

    /**
     * Il renvoie le nombre de fichier/dossier dans le dossier ...
     *
     * @return
     */
    public int Nb_Fichier() {
        if (valeur & repertoire.exists()) {
            return fichier.length;
        } else {
            return 0;
        }
    }

    /**
     * TODO
     *
     * @return
     */
    private String Message() {
        return "Tu n'as pas donnee de repertoire ou il n'est pas cree.";
    }

    /**
     * TODO
     *
     * @return
     */
    private String Message2() {
        return "Le dossier n'a pas de fichier et ou de dossier.";
    }

    /**
     * TODO
     *
     * @return
     */
    public String Nom() {
        return repertoire.getName();
    }

    /**
     * TODO
     *
     * @param nom
     */
    public void Nom(String nom) {
        try {
            if (repertoire.exists()) {
                Repertoire(nom);
            }
        } catch (Exception e) {
            this.nom = nom;
        }
    }

    //-- Il y a la m�me chose dans le fichier r�pertoire: --
    /**
     * Enlève l'extension.
     *
     * @param nom
     * @param avec
     * @return
     */
    public String Extension(String nom, boolean avec) {
        if (!avec) {
            if (!new File(nom).isDirectory()) {
                while (!"".equals(nom)) {

                    if (".".equals(nom.substring(nom.length() - 1))) {
                        nom = nom.substring(0, nom.length() - 1);
                        return nom;
                    }

                    //On remet le mot 'nom' dans un String mais sans son dernier caract�re.
                    nom = nom.substring(0, nom.length() - 1);
                }
            }
        }

        return nom;
    }

    /**
     * TODO
     *
     * @return
     */
    public boolean Exist() {
        return exists();
    }

    /**
     * Il renvoie 'true' si le r�pertoire existe, et 'false' s'il n'existe pas
     *
     * @return
     */
    public boolean exists() {
        try {
            return repertoire.exists();
        } catch (Exception e) {
            repertoire = new File(this.nom);
            return repertoire.exists();
        }
    }

    /**
     * ça sert à ouvrir le fichier avec le programme par d�faut (par rapport � son extension).
     *
     * @return
     */
    public boolean Ouvrir() {
        return Ouvrir(repertoire.getName());
    }

    /**
     * TODO
     *
     * @param nom
     * @return
     */
    public boolean Ouvrir(String nom) {
        if (bureau.isSupported(Desktop.Action.OPEN))
            try {
            bureau.open(new File(nom));
            return true;
        } catch (Exception e) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Ne pas utiliser, il bug:
     * Copier un fichier (fichier source, lieu):
     *
     * @param lieu
     * @return
     */
    public boolean Copier(String lieu) {
        return Copier(this.nom, lieu);
    }

    /**
     * TODO
     *
     * @param source
     * @param destination
     * @return
     */
    public boolean Copier(String source, String destination) {
        try {
            File f = new File(source);
            Creer(destination + "//" + (new File(source).getName()));

            File[] f_f = f.listFiles();

            TRY:
            for (int i = 0; i < (f_f.length); i++) {

                try {
                    //D�claration et ouverture des flux
                    //System.out.println("copie de: "+f_f[i]+", dans: "+destination+".");

                    if (!f_f[i].isDirectory()) {
                        java.io.FileInputStream sourceFile = new java.io.FileInputStream(new File(f_f[i].getAbsolutePath()));

                        try {
                            java.io.FileOutputStream destinationFile = null;

                            try {
                                destinationFile = new FileOutputStream(new File(destination + "//" + f_f[i].getName()));

                                //Lecture par segment de 0.5Mo 
                                byte buffer[] = new byte[512 * 1024];
                                int nbLecture;

                                while ((nbLecture = sourceFile.read(buffer)) != -1) {
                                    destinationFile.write(buffer, 0, nbLecture);
                                }
                            } catch (Exception ex) {
                                //System.out.println("er[]: "+ex);
                                continue TRY;
                            }

                            destinationFile.close();
                        } catch (Exception e) {
                            //System.out.println("er: "+e);
                            continue TRY;
                        }

                        sourceFile.close();
                    } else {
                        //System.out.println("Oui, c'est un dossier...");
                        //System.out.println();
                        Creer(destination + "//" + f_f[i].getName());
                        Copier(f_f[i].getAbsolutePath(), destination + "//" + f_f[i].getName());
                    }

                } catch (IOException e) {
                    //System.out.println("REEE: "+e);
                }

            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Renomer.
     * Ne pas utiliser, il bug.
     *
     * @param nom_initiale
     * @param nouveau_nom
     * @return
     */
    public boolean Renomer(String nom_initiale, String nouveau_nom) {
        try {
            new File(nom_initiale).renameTo(new File(nouveau_nom));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * TODO
     *
     * @param nouveau_nom
     * @return
     */
    public boolean Renomer(String nouveau_nom) {
        try {
            repertoire.renameTo(new File(nouveau_nom));
        } catch (Exception e) {
            return false;
        }
        this.nom = nouveau_nom;
        return true;
    }

    //Ne pas utiliser, il bug:
    //D�placer un dossier:
    public String Deplacer(String destination) {
        return Deplacer(this.nom, destination);
    }

    /**
     * TODO
     *
     * @param source
     * @param destination
     * @return
     */
    public String Deplacer(String source, String destination) {
        //if( !new File(destination).exists() ){

        //On essaye avec renameTo
        //boolean result= new File(source).renameTo(new File(destination+"//"+repertoire.getName()));
        //if( !result ){
        //On essaye de copier
        //result= true;
        Copier(source, destination);

        try {
            repertoire = null;
            //System.out.println("reezer: "+new File(source).delete());
        } catch (Exception e) {
            //System.out.println("Err: "+e);
        }

        //return ""+result;
        //} 
        //else
        //Si le fichier destination existe, on annule ...
        //return "Il existe deja le dossier."; 
        return "" + true;
        //}

        //return "";
    }

}