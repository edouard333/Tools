package com.phenix.tools;


import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;

import java.io.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;

import java.awt.Desktop;

/**
 * sert à gérer un fichier
 *
 * @version 3.93.0
 * @author  Edouard JEANJEAN <edouard128@hotmail.com>
 */
public class Fichier {

//Le fichier:
    //L'objet File (fichier):
    private File fichier;

    //Le contenu du fichier en texte:
    private ArrayList contenu;
    private String contenu_string;

    //Si l'utilisateur veut l'extension du fichier:
    private boolean extension;

    //
    private boolean valeur = false;

    //Le nom du fichier:
    private String nom;

    //Propri�t� du bureau:
    private Desktop bureau = Desktop.getDesktop();

    //Quand on doit "lire" le fichier:
    private Scanner lire;

    //Quand on doit "�crire" le fichier:
    private PrintWriter f;

//On donne la valeur du fichier:
    public Fichier() {
    }

    public Fichier(String nom) {
        fichier = new File(this.nom = nom);

        valeur = true;

        //Si le fichier existe:
        if (fichier.exists()) //Avoir le contenu dans un texte:
        {
            Charger();
        }

        /*else
   System.out.println("Le fichier '"+nom+"', n'existe pas.");*/
    }

//Cr�er le fichier:
    public boolean Creer() {
        try {
            //System.out.println("nom: "+fichier.getAbsolutePath());
            return Creer(fichier.getAbsolutePath());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Creer(String nom) {
        try {
            //On cr�� le fichier s'il y en a pas:
            if (!new File(nom).exists()) {
                return new File(nom).createNewFile();
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erreur:\n" + e);
            return false;
        }
    }

//Supprimer le fichier, si le fichier est supprim� alors il renvoie 'true' sinon 'false':
    public boolean Supprimer() {
        try {
            return Supprimer(fichier.getAbsolutePath());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Supprimer(String nom) {
        try {
            valeur = false;
            System.out.println("nom f a supr: " + nom);
            return new File(nom).delete();
        } catch (Exception e) {
            valeur = true;
            return false;
        }
    }

//�a sert � ouvrir le fichier avec le programme par d�faut (par rapport � son extension).
    public boolean Ouvrir() {
        try {
            return Ouvrir("" + fichier.getCanonicalFile());
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean Open() {
        return Ouvrir();
    }

    public boolean Ouvrir(String nom) {
        if (bureau.isSupported(Desktop.Action.OPEN))
         try {
            bureau.open(new File(nom).getCanonicalFile());
            return true;
        } catch (Exception e) {
            return false;
        } else {
            return false;
        }
    }

    public boolean Open(String nom) {
        return Ouvrir(nom);
    }

    private boolean Charger() {
        //System.out.println("On charge les donn�es du fichier.");

        //Exception: Quand le fichier n'existe pas. 
        try {
            lire = new Scanner(fichier);

            this.contenu = new ArrayList();

            //System.out.println("-- Contenu: --");
            //Lecture enti�re du fichier.
            while (lire.hasNext()) {
                this.contenu.add(lire.nextLine());
            }

            try {
                while (1 == 1) {
                    this.contenu.add(lire.nextLine());
                }
            } catch (Exception e) {
            }

            //System.out.println("-- Fin contenu --");
            lire.close();

            return true;
        } catch (Exception e) {
            //System.out.println("Erreur:\n"+e);
            return false;
        }
    }

    public boolean Load() {
        return Charger();
    }

//Valeur qu'on donne:
    public void Nom(String nom) {
        fichier = new File(this.nom = nom);
        valeur = true;

        //Si le fichier existe:
        if (fichier.exists()) //Avoir le contenu dans un texte:
        {
            Charger();
        }
        /*else
         System.out.println("Le fichier: '"+fichier.getName()+"�, n'existe pas.");*/
    }

    public void append(String txt) {
        Contenu(txt, true);
    }

    public void Append(String txt) {
        Contenu(txt, true);
    }

    public void Contenu(String txt) {
        Contenu(txt, false);
    }

    public void setContenu(String txt) {
        Contenu(txt, false);
    }

//Il y a encore un petit bug, mais mineur:
    public void Contenu(String txt, boolean contenu) {
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

            if (!txt.equals("")) {
                f.print(txt);
            } else {
                f.println();
            }

            f.close();

            //On met � jour le contenu:
            Charger();
        } catch (Exception e) {
            //System.out.println("Erreur:\n"+e);
        }
    }

    public void println(String txt, boolean contenu) {
        Contenu(txt, contenu);
    }

//Valeur qu'on renvoie:
    public String Nom() {
        return Nom(true);
    }

    public String Nom(boolean extension) {
        if (!extension) {
            return Extension(this.nom);
        } else {
            return this.nom;
        }
    }

//-- Il y a la m�me chose dans le fichier r�pertoire: --
//Enl�ve l'extension:
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

    public boolean Exist() {
        return this.fichier.exists();
    }

    public boolean exists() {
        return this.fichier.exists();
    }

    public String getText() {
        return Contenu();
    }

//Avoir le contenu en 'String':
    public String Contenu() {
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

//V�rifier si c'est un fichier avec l'extension 'zip' et si c'est le cas, alors on enl�ve le 'zip':
    public String Zip(String nom) {
        try {
            //On remet le mot 'nom' dans un String mais sans ses 4 derniers caract�res.
            String new_nom = nom.substring(nom.length() - 4);
            if (".zip".equals(new_nom) ^ ".ZIP".equals(new_nom)) {
                return nom.substring(0, nom.length() - 4);
            }
        } catch (Exception e) {
        }
        return nom;
    }

    public String[] Contenu_Zip() {
        return Contenu_Zip(Nom(false));
    }

    public String[] Contenu_Zip(String nom) {
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
        } catch (Exception e) {
            return new String[]{"Erreur: Le fichier: '" + nom + ".zip', n'existe pas."};
        }
    }

    public boolean Compresser() {
        return Compresser(new String[]{this.nom}, this.nom, 0);
    }

    public boolean Compresser(String nom) {
        if (!"".equals(this.nom) && this.nom != null) {
            return Compresser(new String[]{this.nom}, Zip(nom), 0);
        } else {
            return Compresser(new String[]{nom}, Zip(nom), 0);
        }
    }

    public boolean Compresser(int taux_compression) {
        return Compresser(new String[]{this.nom}, Zip(this.nom), taux_compression);
    }

    public boolean Compresser(String nom, int taux_compression) {
        if (!"".equals(this.nom) && this.nom != null) {
            return Compresser(new String[]{this.nom}, Zip(nom), taux_compression);
        } else {
            return Compresser(new String[]{nom}, nom, taux_compression);
        }
    }

    public boolean Compresser(ArrayList nom) {
        if (!"".equals(this.nom) && this.nom != null) {
            return Compresser(Tableau(nom), Zip(this.nom), 0);
        } else {
            return Compresser(Tableau(nom), "" + nom.get(0), 0);
        }
    }

    private String[] Tableau(ArrayList list) {
        String[] tableau = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            tableau[i] = "" + list.get(i);
        }

        return tableau;
    }

    public boolean Compresser(ArrayList nom, String zip) {
        return Compresser(Tableau(nom), Zip(zip), 0);
    }

    public boolean Compresser(ArrayList nom, int taux_compression) {
        if (!"".equals(this.nom) && this.nom != null) {
            return Compresser(Tableau(nom), Zip(this.nom), taux_compression);
        } else {
            return Compresser(Tableau(nom), "" + nom.get(0), taux_compression);
        }
    }

    public boolean Compresser(ArrayList nom, String zip, int taux_compression) {
        return Compresser(Tableau(nom), Zip(zip), taux_compression);
    }

    //Garder du texte:
    public String Garder(String txt, int nb) {
        return (txt.substring(nb - 1, nb));
    }

    public boolean Compresser(String[] nom, String zip, int taux_compression) {
        try {
            final int BUFFER = 2048;
            byte data[] = new byte[BUFFER];

            if (new File(zip).isDirectory()) {
                zip += "/" + new File(nom[0]).getName();
                System.out.println("Le chemin n'est qu'un chemin, alors on lui donne comme nom: " + zip);
            } else //Si 'zip' a d�j� ".zip" dans son nom, on l'enl�ve:
            if (Garder(zip, 3).equals("zip") ^ Garder(zip, 3).equals("Zip") ^ Garder(zip, 3).equals("ZIP")) {
                zip = zip.substring(0, zip.length() - 4);
            } //Sinon, on v�rifie si le dossier
            else if (new File(zip + ".zip").exists()) {
                System.out.println("Le zip: '" + zip + ".zip' existe deja, on va l'appelle: '" + zip + " (2).zip'.");

                zip += "/" + new File(nom[0]).getName() + " (2)";
            }

            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zip + ".zip")));

            out.setMethod(ZipOutputStream.DEFLATED);
            out.setLevel(taux_compression);

            //Ici, c'est les fichiers qu'on ajoute au fichier 'zip':
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
                    //Peut-�tre un dossier:
                    try {

                    } catch (Exception e_v) {
                        System.out.println("Erreur:\n" + e_v);
                    }

                }
            }

            out.close();

            return true;
        } catch (Exception e) {
            System.out.println("Erreur:\n" + e);
            return false;
        }
    }

    public boolean Decompresser() {
        return Decompresser(new String[]{Nom(false)}, this.nom);
    }

    public boolean Decompresser(ArrayList nom) {
        if (!"".equals(this.nom) && this.nom != null) {
            return Decompresser(Tableau(nom), this.nom);
        } else {
            return Decompresser(Tableau(nom), "");
        }
    }

    public boolean Decompresser(ArrayList nom, String lieu) {
        return Decompresser(Tableau(nom), Zip(lieu));
    }

    public boolean Fichier(String nom) {
        for (int i = 0; i < nom.length(); i++) {
            if (".".equals(nom.substring(nom.length() - 1))) {
                return true;
            }

            //On remet le mot 'nom' dans un String mais sans son dernier caract�re.
            System.out.println(nom = nom.substring(0, nom.length() - 1));
        }

        return false;
    }

    public boolean Decompresser(String[] nom, String lieu) {
        try {

            for (int i = 0; i < nom.length; i++) {
                if (nom[i] != null && !"".equals(nom[i]) && new File((nom[i] = Zip(nom[i])) + ".zip").exists()) {
                    final int BUFFER = 2048;
                    byte data[] = new byte[BUFFER];

                    //D�claration d�un fichier destination:
                    BufferedOutputStream dest = null;

                    System.out.println("");
                    System.out.println("");
                    System.out.println("Fichier a decompresser: " + nom[i] + ".zip.");

                    //Ouverture de l�archive Zip via ce buffer (Ouverture buffer sur ce fichier (Ouverture du fichier � d�compresser (ou "Lieu on se trouve le zip"))):
                    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(nom[i] + ".zip")));

                    //Lieu o� on d�compresse les fichiers du fichier 'zip':
                    String t = "";
                    //Si on a pas de lieu o� d�compresser on donne celui du fichier zip.
                    if ("".equals(lieu)) {
                        t = nom[i];
                    } else {
                        t = lieu;
                    }

                    for (; !"".equals(t); t = t.substring(0, t.length() - 1)) {
                        if ((new File("").separator).equals(t.substring(t.length() - 1)) ^ "/".equals(t.substring(t.length() - 1))) {
                            t = t.substring(0, t.length() - 1);
                            break;
                        }
                    }

                    System.out.println("Le lieu de destination: " + t);

                    //Parcours des entr�es de l�archive (donc de c'est fichier):
                    ZipEntry entry;

                    while ((entry = zis.getNextEntry()) != null)
                  try {
                        System.out.println(" -- Fichier dans '" + new File(nom[i] + ".zip").getName() + "': " + entry);

                        //Est-ce que c'est un repertoire, oui...:
                        if (!Fichier(t + (new File("").separator) + entry.getName())) {
                            new File(t + new File("").separator + entry.getName()).mkdirs();
                        } else {
                            //V�rifie s'il n'y a pas de dossier parent:
                            {
                                File f = new File(t + (new File("").separator) + entry.getName());
                                if (!new File(f.getParent()).isDirectory()) {
                                    new File(f.getParent()).delete();
                                    new File(f.getParent()).mkdirs();
                                }
                            }

                            //Cr�ation du fichier de sortie (O� on d�compresse le fichier):
                            //Affectation au buffer de sortie de ce flux vers fichier:
                            dest = new BufferedOutputStream(new FileOutputStream(t + (new File("").separator) + entry.getName()), BUFFER);

                            System.out.println("Nom complet de l'entre '" + entry + "': " + t + new File("").separator + entry.getName());

                            //�criture sur le disque:
                            int count;
                            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                                dest.write(data, 0, count);
                            }

                            dest.flush();
                            dest.close();
                        }
                    } catch (Exception e_2) {
                        System.out.println("Erreur:\n" + e_2);
                    }

                    //Fermeture de l�archive:
                    zis.close();
                } else {
                    System.out.println("Le nom: '" + nom[i] + "(zip)', n'est pas correcte.");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Erreur:\n" + e);
            return false;
        }
    }

//Copier un fichier (fichier source, lieu):
    public boolean Copier(String lieu) {
        return Copier(this.nom, lieu);
    }

    public boolean Copier(String source, String destination) {
        try {
            //D�claration et ouverture des flux
            java.io.FileInputStream sourceFile = new java.io.FileInputStream(new File(source));

            try {
                java.io.FileOutputStream destinationFile = null;

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
        } catch (IOException e) {
            e.printStackTrace();
            return false; //Erreur
        }

        //Ok
        return true;
    }

//D�placer un fichier:
    public boolean Deplacer(String destination) {
        return Deplacer(this.nom, destination);
    }

    public boolean Deplacer(String source, String destination) {
        if (!new File(destination).exists()) {

            //On essaye avec renameTo
            boolean result = new File(source).renameTo(new File(destination));
            if (!result) {

                //On essaye de copier
                result = true;
                result &= Copier(source, destination);

                if (result) {
                    result &= new File(source).delete();
                }
            }

            return result;
        } else //Si le fichier destination existe, on annule ...
        {
            return false;
        }
    }

//Renomer:
    public boolean Renomer(String nom_initiale, String nouveau_nom) {
        try {
            new File(nom_initiale).renameTo(new File(nouveau_nom));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean Renomer(String nouveau_nom) {
        try {
            fichier.renameTo(new File(nouveau_nom));
        } catch (Exception e) {
            return false;
        }
        this.nom = nouveau_nom;
        return true;
    }

}
