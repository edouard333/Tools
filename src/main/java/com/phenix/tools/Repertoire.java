package com.phenix.tools;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Il sert à créer ou à supprimer un répertoire/dossier et a il regarder tous
 * les fichiers qu'il en contient. Attention: Ce fichier ne fait que des
 * répertoires et ne peut être utilisé pour créer des fichiers!<br>
 * Note : La classe a été épuré des méthodes qui aurait dû être static et des
 * méthodes redondante ont été supprimées.
 *
 * @version 4.0.0
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Repertoire {

  /**
   * Créer un fichier 'répertoire'.
   */
  private File repertoire;

  /**
   * Nom du répertoire.
   */
  private String nom;

  /**
   * Liste des fichiers dans le répertoire.
   */
  private File[] fichier;

  /**
   * Utilisé dans la navigation (nextFichier) des fichiers du répertoire.
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
   * Message d'erreur.
   */
  private final static String message_erreur = "Tu n'as pas donnee de repertoire ou il n'est pas créé.";

  /**
   * Message d'erreur.
   */
  private final static String message_erreur_2 = "Le dossier n'a pas de fichier et ou de dossier.";

  /**
   * Construit un répertoire sur base de son nom.
   *
   * @param nom Nom du répertoire.
   */
  public Repertoire(String nom) {
    try {
      init(this.nom = nom);
    } catch (Exception exception) {
      this.nom = nom;
    }
  }

  /**
   * Il dit s'il y a des fichiers: s'il renvoie <code>true</code> c'est qu'il y
   * a des fichiers, sinon il renvoie <code>false</code>.
   *
   * @return Si le dossier contient des fichiers/dossier.
   */
  public boolean contientFichier() {
    // Si le nombre de fichier est égal à zéro, il renvoie false:
    if (fichier.length == 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Ne pas utiliser, il bug: Copier un fichier (fichier source, lieu).
   *
   * @param destination Chemin de destination.
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean copier(String destination) {
    return copier(this.nom, destination);
  }

  /**
   * Copie le dossier.
   *
   * @param source Oùse trouve le dossier.
   * @param destination Chemin de destination.
   * @return Indique si l'opération s'est bien passée.
   */
  private boolean copier(String source, String destination) {
    try {
      File f = new File(source);
      creer(destination + "//" + (new File(source).getName()));

      File[] f_f = f.listFiles();

      TRY:
      for (int i = 0; i < (f_f.length); i++) {

        try {
          // Déclaration et ouverture des flux
          // System.out.println("copie de: "+f_f[i]+", dans: "+destination+".");

          if (!f_f[i].isDirectory()) {
            FileInputStream sourceFile = new FileInputStream(new File(f_f[i].getAbsolutePath()));

            try {
              FileOutputStream destinationFile = null;

              try {
                destinationFile = new FileOutputStream(new File(destination + "//" + f_f[i].getName()));

                //Lecture par segment de 0.5Mo 
                byte buffer[] = new byte[512 * 1024];
                int nbLecture;

                while ((nbLecture = sourceFile.read(buffer)) != -1) {
                  destinationFile.write(buffer, 0, nbLecture);
                }
              } catch (Exception exception) {
                //System.out.println("er[]: "+exception);
                continue TRY;
              }

              destinationFile.close();
            } catch (Exception exception) {
              //System.out.println("er: "+e);
              continue TRY;
            }

            sourceFile.close();
          } else {
            // System.out.println("Oui, c'est un dossier...");
            //System.out.println();
            creer(destination + "//" + f_f[i].getName());
            copier(f_f[i].getAbsolutePath(), destination + "//" + f_f[i].getName());
          }

        } catch (IOException exception) {
          //System.out.println("REEE: "+e);
        }

      }

      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * Ici, on créé un répertoire et renvoie <code>true</code> s'il a été créé,
   * sinon il renvoie <code>false</code>: Et si le répertoire a été déjà
   * définit.
   *
   * @return Indique si la création s'est bien passée.
   */
  public boolean creer() {
    return creer(this.nom);
  }

  /**
   * Ici, on créé un répertoire et renvoie <code>true</code> s'il a été créé,
   * sinon il renvoie <code>false</code>: Et si le répertoire a été déjà
   * définit.
   *
   * @param nom Le dossier a créer.
   *
   * @return Indique si la création s'est bien passée.
   */
  public boolean creer(String nom) {
    try {
      // On créé le répertoire:
      return new File(nom).mkdirs();
    } catch (Exception exception) {
      //Si le répertoire existe déjà alors on renvoie 'true':
      if (new File(nom).exists()) {
        return true;
      } // Sinon on renvoie 'false':
      else {
        return false;
      }
    }
  }

  /**
   * Déplacer un dossier.
   *
   * @deprecated Ne pas utiliser, il bug.
   *
   * @param destination Où on déplace le dossier.
   *
   * @return Si l'opération s'est bien passée.
   */
  public String deplacer(String destination) {
    return deplacer(this.nom, destination);
  }

  /**
   * Déplacer un dossier.
   *
   * @deprecated Ne pas utiliser, il bug.
   *
   * @param source Le dossier source.
   * @param destination La destination.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public String deplacer(String source, String destination) {
    copier(source, destination);

    try {
      repertoire = null;
    } catch (Exception exception) {
    }
    return "" + true;
  }

  /**
   * Il renvoie <code>true</code> si le répertoire existe, et <code>false</code>
   * s'il n'existe pas.
   *
   * @return <code>true</code> si le dossier existe.
   */
  public boolean exists() {
    try {
      return repertoire.exists();
    } catch (Exception exception) {
      repertoire = new File(this.nom);
      return repertoire.exists();
    }
  }

  /**
   * Il montre un fichier du répertoire sans son extension au numéro ... :
   * Attention: Si le numéro va renvoyer un dossier, il renvoyera le nom du
   * dossier!
   *
   * @param index Index dans la liste du répertoire.
   *
   * @return Nom du fichier.
   */
  public String getNomFichier(int index) {
    //Il renvoie le nom du fichier sans l'extension.
    if (valeur && fichier[index].exists())
            try {
      return getExtension(fichier[index].getName(), true);
    } catch (Exception exception) {
      //S'il n'y a pas de fichier
      try {
        return fichier[index].getName();
      } catch (Exception f) {
        return message_erreur_2;
      }
    } else {
      return message_erreur;
    }
  }

  /**
   * Il montre un fichier du répertoire.
   *
   * @return Nom du fichier.
   */
  public String nextFichier() {
    if (valeur && fichier[this.i + 1].exists()) {
      //Try pour quand le dossier n'a pas de fichier et ou de dossier:
      try {
        this.i++;
        //Il renvoie le nom du fichier avec l'extension.
        return fichier[this.i].getName();
      } catch (Exception exception) {
        return message_erreur;
      }
    } else {
      return message_erreur;
    }
  }

  /**
   * On remet le compteur à zéro.
   */
  public void initNextFichier() {
    this.i = -1;
  }

  /**
   * Il montre un fichier du répertoire via son index..
   *
   * @param index Index du fichier.
   *
   * @return Nom d'un fichier.
   */
  public String getFichier(int index) {
    //Il renvoie le nom du fichier avec l'extension.
    if (valeur && fichier[index].exists()) {
      return fichier[index].getName();
    } else {
      return message_erreur;
    }
  }

  /**
   * Il montre les fichiers du répertoire.
   *
   * @param extension Si on garde ou non l'extension du fichier.
   *
   * @return Liste des fichier.
   */
  public String[] getFichiers(boolean extension) {
    String fichiers[];

    if (valeur && repertoire.exists()) {

      fichiers = new String[fichier.length];

      //Il renvoie le nom des fichiers sans l'extension:
      for (int i = 0; i < fichiers.length; i++) {
        fichiers[i] = getExtension(fichier[i].getName(), extension);
      }

      return fichiers;
    } else {
      return new String[]{message_erreur};
    }

  }

  /**
   * Retourne le nom du répertoire.
   *
   * @return Nom du répertoire.
   */
  public String getNom() {
    return repertoire.getName();
  }

  /**
   * Enlève l'extension.
   *
   * @param nom Nom du fichier.
   * @param avec Indique si on garde l'extension où nom.
   *
   * @return Nom sans l'extension.
   */
  public String getExtension(String nom, boolean avec) {
    if (!avec) {
      if (!new File(nom).isDirectory()) {
        while (!"".equals(nom)) {

          if (".".equals(nom.substring(nom.length() - 1))) {
            nom = nom.substring(0, nom.length() - 1);
            return nom;
          }

          // On remet le mot 'nom' dans un String mais sans son dernier caractère.
          nom = nom.substring(0, nom.length() - 1);
        }
      }
    }

    return nom;
  }

  /**
   * Il renvoie le nombre de fichier/dossier dans le dossier.
   *
   * @return Nombre de fichier/dossier.
   */
  public int getNombreFichier() {
    if (valeur & repertoire.exists()) {
      return fichier.length;
    } else {
      return 0;
    }
  }

  /**
   * On sait s'il y a un dossier et on donne au fichier 'repertoire' les
   * valeurs.
   *
   * @param dossier Nom du dossier.
   */
  private void init(String dossier) {
    repertoire = new File(dossier);
    this.i = -1;

    if (!repertoire.exists()) {
      // System.out.println("Le fichier/repertoire '"+repertoire+"' n'existe pas");

      // C'est pour permettre à l'utilisateur de le créer.
      valeur = true;
    } else if (!repertoire.isDirectory())
           //System.out.println("Le chemin '"+repertoire+"' correspond à un fichier et non a un repertoire")
            ; else {
      fichier = repertoire.listFiles();
      valeur = true;
    }

  }

  /**
   * Voir si c'est bien un dossier.
   *
   * @return <code>true</code> si c'est bien un dossier.
   */
  public boolean isDirectory() {
    return repertoire.isDirectory();
  }

  /**
   * ça sert à ouvrir le fichier avec le programme par défaut (par rapport à son
   * extension).
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean ouvrir() {
    String nom = repertoire.getName();
    if (bureau.isSupported(Desktop.Action.OPEN))
            try {
      bureau.open(new File(nom));
      return true;
    } catch (Exception exception) {
      return false;
    } else {
      return false;
    }
  }

  /**
   * Renomer.
   *
   * @deprecated Ne pas utiliser, il bug.
   *
   * @param nouveau_nom Nouveau nom de répertoire.
   *
   * @return Indique si cela s'est bien passé.
   */
  public boolean renomer(String nouveau_nom) {
    try {
      repertoire.renameTo(new File(nouveau_nom));
    } catch (Exception exception) {
      return false;
    }
    this.nom = nouveau_nom;
    return true;
  }

  /**
   * Ici on supprime un répertoire et renvoie <code>true</code> s'il a été
   * supprimé, sinon il renvoie <code>false</code>: Et si le répertoire a été
   * déjà définit.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean supprimer() {
    return supprimer(repertoire.getAbsolutePath());
  }

  /**
   * Ici on supprime un répertoire et renvoie <code>true</code> s'il a été
   * supprimé, sinon il renvoie <code>false</code>: Et si le répertoire a été
   * déjà définit.
   *
   * @param nom Nom du fichier/dossier à supprimer.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean supprimer(String nom) {
    try {
      return new File(nom).delete();
    } catch (Exception exception) {
      System.out.println("Erreur:\n" + exception);
      return false;
    }
  }

  /**
   * Supprime un dossier.
   *
   * @param nom Dossier à supprimer.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean supprimerDossier(String nom) {
    System.out.println("On supprime le dossier: " + nom);

    Repertoire dossier = new Repertoire(nom);

    Repertoire dossier2 = new Repertoire(nom);

    for (int i = 0; i < dossier2.getNombreFichier(); i++) {
      System.out.println("L'element du dossier (" + nom + ") a supprimer: " + dossier.getFichier(i));

      if (new Repertoire(nom + "//" + dossier2.getFichier(i)).isDirectory()) {
        System.out.println("C'est un dossier!");
        supprimerDossier(nom + "//" + dossier2.getFichier(i));
      } else {
        System.out.println("C'est un fichier! (" + nom + "//" + dossier2.getFichier(i) + ")");
        supprimer(nom + "//" + dossier2.getFichier(i));
      }
    }

    return dossier2.supprimer();

  }

}
