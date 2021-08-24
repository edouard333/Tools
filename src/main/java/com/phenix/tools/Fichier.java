package com.phenix.tools;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

/**
 * Sert à gérer un fichier.<br>
 * Note : La classe a été épuré des méthodes qui aurait dû être static et des
 * méthodes redondante ont été supprimées.
 *
 * @version 4.0.0
 * @author  <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Fichier {

  /**
   * Le fichier.<br>
   * L'objet File (fichier)
   */
  private File fichier;

  /**
   * Le contenu du fichier en texte.
   */
  private ArrayList contenu;

  /**
   * Le texte contenu dans le fichier.
   */
  private String contenu_string;

  /**
   * Si l'utilisateur veut l'extension du fichier.
   */
  private boolean extension;

  /**
   * Le nom du fichier.
   */
  private String nom;

  /**
   * Propriété du bureau.
   */
  private Desktop bureau = Desktop.getDesktop();

  /**
   * Quand on doit "lire" le fichier.
   */
  private Scanner lire;

  /**
   * Quand on doit "écrire" le fichier.
   */
  private PrintWriter f;

  /**
   * Construit un fichier sur base de son nom.
   *
   * @param nom Nom du fichier.
   */
  public Fichier(String nom) {
    fichier = new File(this.nom = nom);

    //Si le fichier existe:
    if (fichier.exists()) //Avoir le contenu dans un texte:
    {
      charger();
    }

    /*else
   System.out.println("Le fichier '"+nom+"', n'existe pas.");*/
  }

  /**
   * Ajoute un texte au fichier.
   *
   * @param texte Texte à ajouter.
   */
  public void append(String texte) {
    contenu(texte, true);
  }

  /**
   * Créé le fichier.
   *
   * @return <code>true</code> si le fichier est créé.
   */
  public boolean creer() {
    String nom = fichier.getAbsolutePath();
    try {
      //On créé le fichier s'il y en a pas:
      if (!new File(nom).exists()) {
        return new File(nom).createNewFile();
      } else {
        return false;
      }
    } catch (Exception exception) {
      System.out.println("Erreur:\n" + exception);
      return false;
    }
  }

  /**
   * Ca sert à ouvrir le fichier avec le programme par défaut (par rapport à son
   * extension).
   *
   * @return <code>false</code> en cas d'erreur.
   */
  public boolean ouvrir() {
    if (bureau.isSupported(Desktop.Action.OPEN)) {
      try {
        bureau.open(new File(nom).getCanonicalFile());
        return true;
      } catch (Exception exception) {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * Récupère le texte contenu dans le fichier.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  private boolean charger() {
    //System.out.println("On charge les données du fichier.");

    //Exception: Quand le fichier n'existe pas. 
    try {
      lire = new Scanner(fichier);

      this.contenu = new ArrayList();

      //System.out.println("-- Contenu: --");
      //Lecture entière du fichier.
      while (lire.hasNext()) {
        this.contenu.add(lire.nextLine());
      }

      try {
        while (1 == 1) {
          this.contenu.add(lire.nextLine());
        }
      } catch (Exception exception) {
      }

      //System.out.println("-- Fin contenu --");
      lire.close();

      return true;
    } catch (Exception exception) {
      //System.out.println("Erreur:\n"+e);
      return false;
    }
  }

  /**
   * Remplace le contenu du fichier.
   *
   * @deprecated On va éviter ce genre de méthode.
   *
   * @param texte
   */
  public void contenu(String texte) {
    contenu(texte, false);
  }

  /**
   * @deprecated @param texte
   */
  public void setContenu(String texte) {
    contenu(texte, false);
  }

  /**
   * Il y a encore un petit bug, mais mineur.
   *
   * @param texte Texte ajouté à la fin.
   * @param contenu Si <code>false</code> alors on remplate le contenu par le
   * texte à ajouter.
   */
  public void contenu(String texte, boolean contenu) {
    try {
      f = new PrintWriter(fichier);

      //Pour conserver le contenu d'autre fois et on ajoute juste le texte:
      if (fichier.exists()) {
        if (contenu && !"".equals(this.contenu) && null != this.contenu) {
          for (int i = 0; i < this.contenu.size(); i++) {
            f.println(this.contenu.get(i));
          }
        }
      }

      if (!texte.equals("")) {
        f.print(texte);
      } else {
        f.println();
      }

      f.close();

      //On met à jour le contenu:
      charger();
    } catch (Exception exception) {
      //System.out.println("Erreur:\n"+e);
    }
  }

  /**
   * Ajoute le texte au fichier.
   *
   * @param texte Texte ajouté à la fin.
   * @param contenu Si <code>false</code> alors on remplate le contenu par le
   * texte à ajouter.
   */
  public void println(String texte, boolean contenu) {
    contenu(texte, contenu);
  }

  /**
   * Valeur qu'on renvoie.
   *
   * @return
   */
  public String getNom() {
    return getNom(true);
  }

  /**
   * Retourne le nom du fichier avec ou sans extension.
   *
   * @param extension Si <code>true</code> alors on ajoute l'extension.
   *
   * @return Nom du fichier avec ou sans extension.
   */
  public String getNom(boolean extension) {
    if (!extension) {
      return getExtension(this.nom);
    } else {
      return this.nom;
    }
  }

//-- Il y a la même chose dans le fichier répertoire: --
  /**
   * Enlève l'extension
   *
   * @param nom
   * @return
   */
  public String getExtension(String nom) {
    while (!"".equals(nom)) {

      if (".".equals(nom.substring(nom.length() - 1))) {
        return nom.substring(0, nom.length() - 1);
      }

      //On remet le mot 'nom' dans un String mais sans son dernier caractère.
      nom = nom.substring(0, nom.length() - 1);

    }

    return nom;
  }

  /**
   * Indique si le fichier existe.
   *
   * @return <code>true</code> si le fichier existe.
   */
  public boolean exists() {
    return this.fichier.exists();
  }

  /**
   *
   * @return
   */
  public String getText() {
    return contenu();
  }

  /**
   *
   * @return Indique si cela s'est bien passé.
   */
  public boolean compresser() {
    return compresser(new String[]{this.nom}, this.nom, 0);
  }

  /**
   * Compresse le fichier à la destination.
   *
   * @param destination Endroit où compresser.
   *
   * @return Indique si cela s'est bien passé.
   */
  public boolean compresser(String destination) {
    if (!"".equals(this.nom) && this.nom != null) {
      return compresser(new String[]{this.nom}, zip(destination), 0);
    } else {
      return compresser(new String[]{this.nom}, zip(destination), 0);
    }
  }

  /**
   * Compresse dans un zip le fichier courant.
   *
   * @param taux_compression
   *
   * @return Indique si cela s'est bien passé.
   */
  public boolean compresser(int taux_compression) {
    return compresser(new String[]{this.nom}, zip(this.nom), taux_compression);
  }

  /**
   * Compresse un fichier.
   *
   * @param nom Fichier a compresser.
   * @param taux_compression Taux de compression.
   *
   * @return Indique si cela s'est bien passé.
   */
  public boolean compresser(String nom, int taux_compression) {
    if (!"".equals(this.nom) && this.nom != null) {
      return compresser(new String[]{this.nom}, zip(nom), taux_compression);
    } else {
      return compresser(new String[]{nom}, nom, taux_compression);
    }
  }

  /**
   * Indique le contenu du zip.
   *
   * @return Liste des éléments.
   */
  public String[] getContenuZip() {
    String nom = getNom(false);

    try {
      ZipFile zf = null;

      if (new File(nom + ".zip").exists()) {
        zf = new ZipFile(nom + ".zip");
      } else if (new File(nom).exists()) {
        zf = new ZipFile(nom);
      } else {
        throw new IOException("Erreur: Le fichier '" + nom + "(.zip)', n'existe pas.");
      }

      Enumeration liste = zf.entries();

      String[] contenu = new String[zf.size()];

      for (int i = 0; liste.hasMoreElements(); i++) {
        contenu[i] = "" + (ZipEntry) liste.nextElement();
      }

      return contenu;
    } catch (Exception exception) {
      return new String[]{"Erreur: Le fichier: '" + nom + ".zip', n'existe pas."};
    }
  }

  /**
   * Avoir le contenu en <code>String</code>.
   *
   * @return Représentation du contenu en <code>String</code>.
   */
  public String contenu() {
    contenu_string = null;
    for (int i = 0; i < this.contenu.size(); i++) {
      if (contenu_string == null) {
        contenu_string = "" + this.contenu.get(i);
      } else {
        contenu_string += this.contenu.get(i);
      }
    }

    return contenu_string;
  }

  /**
   * Garder du texte.
   *
   * @param texte
   * @param nombre_caractere
   *
   * @return
   */
  public String garder(String texte, int nombre_caractere) {
    return (texte.substring(nombre_caractere - 1, nombre_caractere));
  }

  /**
   * Méthode qui compresse les fichiers dans un zip.
   *
   * @param nom La liste des fichiers a compresser.
   * @param zip Le fichier contenant tous les fichiers.
   * @param taux_compression Le taux de compression.
   *
   * @return Indique si la compression s'est bien passée.
   */
  public boolean compresser(String[] nom, String zip, int taux_compression) {
    try {
      final int BUFFER = 2048;
      byte data[] = new byte[BUFFER];

      if (new File(zip).isDirectory()) {
        zip += "/" + new File(nom[0]).getName();
        System.out.println("Le chemin n'est qu'un chemin, alors on lui donne comme nom: " + zip);
      } else // Si 'zip' a déjà ".zip" dans son nom, on l'enlève:
      if (garder(zip, 3).equals("zip") ^ garder(zip, 3).equals("Zip") ^ garder(zip, 3).equals("ZIP")) {
        zip = zip.substring(0, zip.length() - 4);
      } // Sinon, on vérifie si le dossier.
      else if (new File(zip + ".zip").exists()) {
        System.out.println("Le zip: '" + zip + ".zip' existe deja, on va l'appelle: '" + zip + " (2).zip'.");

        zip += "/" + new File(nom[0]).getName() + " (2)";
      }

      ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zip + ".zip")));

      out.setMethod(ZipOutputStream.DEFLATED);
      out.setLevel(taux_compression);

      // Ici, c'est les fichiers qu'on ajoute au fichier 'zip':
      for (int i = 0; i < nom.length; i++) {
        if (new File(nom[i]).exists()) {
          BufferedInputStream buffi = new BufferedInputStream(new FileInputStream(nom[i]), BUFFER);

          out.putNextEntry(new ZipEntry(new File(nom[i]).getName()));

          int count;
          while ((count = buffi.read(data, 0, BUFFER)) != -1) {
            out.write(data, 0, count);
          }

          out.closeEntry();
          buffi.close();
        } else {
          System.out.println("Le fichier: " + nom[i] + ", n'existe pas.");
          // Peut-être un dossier:
          try {

          } catch (Exception e_v) {
            System.out.println("Erreur:\n" + e_v);
          }

        }
      }

      out.close();

      return true;
    } catch (Exception exception) {
      System.out.println("Erreur:\n" + exception);
      return false;
    }
  }

  /**
   * Copie le fichier à la destination indiquée.
   *
   * @param destination Chemin de destination.
   *
   * @return Indique si l'opération s'est bien déroulée.
   */
  public boolean copier(String destination) {
    String source = this.nom;
    try {
      // Déclaration et ouverture des flux
      FileInputStream sourceFile = new FileInputStream(new File(source));

      try {
        FileOutputStream destinationFile = null;

        try {
          destinationFile = new FileOutputStream(new File(destination));

          //Lecture par segment de 0.5Mo 
          byte buffer[] = new byte[512 * 1024];
          int nbLecture;

          while ((nbLecture = sourceFile.read(buffer)) != -1) {
            destinationFile.write(buffer, 0, nbLecture);
          }
        } finally {
          destinationFile.close();
        }
      } finally {
        sourceFile.close();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
      return false; //Erreur
    }

    //Ok
    return true;
  }

  /**
   *
   * @return
   */
  public boolean decompresser() {
    return decompresser(new String[]{getNom(false)}, this.nom);
  }

  /**
   * Décompresse une série de fichier.
   *
   * @param nom Liste des fichiers à décompresser.
   * @param lieu Endroit où les fichiers sont décompressé.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean decompresser(String[] nom, String lieu) {
    try {

      for (int i = 0; i < nom.length; i++) {
        if (nom[i] != null && !"".equals(nom[i]) && new File((nom[i] = zip(nom[i])) + ".zip").exists()) {
          final int BUFFER = 2048;
          byte data[] = new byte[BUFFER];

          // Déclaration d'un fichier destination:
          BufferedOutputStream dest = null;

          System.out.println("");
          System.out.println("");
          System.out.println("Fichier a decompresser: " + nom[i] + ".zip.");

          // Ouverture de l'archive Zip via ce buffer (Ouverture buffer sur ce fichier (Ouverture du fichier à décompresser (ou "Lieu on se trouve le zip"))):
          ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(nom[i] + ".zip")));

          // Lieu où on décompresse les fichiers du fichier 'zip':
          String t = "";
          // Si on a pas de lieu où décompresser on donne celui du fichier zip.
          if ("".equals(lieu)) {
            t = nom[i];
          } else {
            t = lieu;
          }

          for (; !"".equals(t); t = t.substring(0, t.length() - 1)) {
            if ((File.separator).equals(t.substring(t.length() - 1)) ^ "/".equals(t.substring(t.length() - 1))) {
              t = t.substring(0, t.length() - 1);
              break;
            }
          }

          System.out.println("Le lieu de destination: " + t);

          // Parcours des entrées de l'archive (donc de c'est fichier):
          ZipEntry entry;

          while ((entry = zis.getNextEntry()) != null)
                  try {
            System.out.println(" -- Fichier dans '" + new File(nom[i] + ".zip").getName() + "': " + entry);

            // Si c'est répertoire :
            if (!fichier(t + (File.separator) + entry.getName())) {
              new File(t + File.separator + entry.getName()).mkdirs();
            } else {
              // Vérifie s'il n'y a pas de dossier parent:
              {
                File f = new File(t + (File.separator) + entry.getName());
                if (!new File(f.getParent()).isDirectory()) {
                  new File(f.getParent()).delete();
                  new File(f.getParent()).mkdirs();
                }
              }

              // Création du fichier de sortie (Où on décompresse le fichier):
              // Affectation au buffer de sortie de ce flux vers fichier:
              dest = new BufferedOutputStream(new FileOutputStream(t + (File.separator) + entry.getName()), BUFFER);

              System.out.println("Nom complet de l'entre '" + entry + "': " + t + File.separator + entry.getName());

              // Ecriture sur le disque:
              int count;
              while ((count = zis.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, count);
              }

              dest.flush();
              dest.close();
            }
          } catch (Exception exception2) {
            System.out.println("Erreur:\n" + exception2);
          }

          //Fermeture de l'archive:
          zis.close();
        } else {
          System.out.println("Le nom: '" + nom[i] + "(zip)', n'est pas correcte.");
        }
      }

      return true;
    } catch (Exception exception) {
      System.out.println("Erreur:\n" + exception);
      return false;
    }
  }

  /**
   * Déplacer le fichier.
   *
   * @param destination Chemin de destination.
   *
   * @return Indique si l'opération s'est bien passée.
   */
  public boolean deplacer(String destination) {
    String source = this.nom;

    if (!new File(destination).exists()) {

      // On essaye avec renameTo
      boolean result = new File(source).renameTo(new File(destination));
      if (!result) {

        // On essaye de copier
        result = true;
        result &= copier(destination);

        if (result) {
          result &= new File(source).delete();
        }
      }

      return result;
    } else // Si le fichier destination existe, on annule.
    {
      return false;
    }
  }

  /**
   *
   * @param nom
   *
   * @return
   */
  public boolean fichier(String nom) {
    for (int i = 0; i < nom.length(); i++) {
      if (".".equals(nom.substring(nom.length() - 1))) {
        return true;
      }

      //On remet le mot 'nom' dans un String mais sans son dernier caractère.
      System.out.println(nom = nom.substring(0, nom.length() - 1));
    }

    return false;
  }

  /**
   * Renomme le fichier.
   *
   * @param nouveau_nom Nouveau nom.
   *
   * @return <code>true</code> si tout s'est bien passé.
   */
  public boolean renomer(String nouveau_nom) {
    try {
      fichier.renameTo(new File(nouveau_nom));
    } catch (Exception exception) {
      return false;
    }
    this.nom = nouveau_nom;
    return true;
  }

  /**
   * Supprimer le fichier.
   *
   * @return <code>true</code> si le fichier est supprimé sinon
   * <code>false</code>.
   */
  public boolean supprimer() {
    try {
      System.out.println("nom f a supr: " + nom);
      return new File(nom).delete();
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * Vérifier si c'est un fichier avec l'extension 'zip' et si c'est le cas,
   * alors on enlève le 'zip'.
   *
   * @param nom Nom d'un zip.
   *
   * @return Nom d'un zip sans l'extension.
   */
  public String zip(String nom) {
    try {
      // On remet le mot 'nom' dans un String mais sans ses 4 derniers caractères.
      String new_nom = nom.substring(nom.length() - 4);
      if (".zip".equals(new_nom) ^ ".ZIP".equals(new_nom)) {
        return nom.substring(0, nom.length() - 4);
      }
    } catch (Exception exception) {
    }
    return nom;
  }
}
