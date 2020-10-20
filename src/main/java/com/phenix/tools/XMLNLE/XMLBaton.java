package com.phenix.tools.XMLNLE;

import com.phenix.tools.tools.Timecode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

/**
 * Gère l'XML venant de Baton.
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class XMLBaton {

    /**
     * TODO
     */
    private Document document;

    /**
     * TODO
     */
    private Element racine;

    /**
     * TODO
     */
    private NodeList racineNoeuds;

    /**
     * TODO
     */
    private NodeList streamnode;

    /**
     * Nom du fichier vidéo analysé par Baton.
     */
    private String nom_fichier;

    /**
     * Hauteur de la vidéo analysé.
     */
    private int hauteur;

    /**
     * Largeur de la vidéo analysé.
     */
    private int largeur;

    /**
     * Timecode de début de la vidéo analysé.
     */
    private String tcstart;

    /**
     * Duréee de la vidéo analysé.
     */
    private Timecode duree;

    /**
     * Framerate de la vidéo analysé.
     */
    private int framerate;

    /**
     * Récupérer des informations d'un rapport Baton formaté en XML.
     *
     * @param fichier Le fichier Baton à traiter.
     */
    public XMLBaton(String fichier) {
        try {
            // Le fichier a analyser.
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fichier));

            // La racine du document (taskReport).
            racine = document.getDocumentElement();

            // On récupère directement les enfants.
            racineNoeuds = racine.getChildNodes();

            // On parcoure les nodes:
            for (int i = 0; i < racineNoeuds.getLength(); i++) {

                // Si c'est effectivement un node, on regarde:
                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

                    // En fonction de son nom, on fait un traitement:
                    switch (racineNoeuds.item(i).getNodeName()) {

                        // On récupère depuis le node "toplevelinfo" l'information du nom de fichier vidéo analysé:
                        case "toplevelinfo":
                            this.nom_fichier = ((Element) racineNoeuds.item(i)).getAttribute("Filepath");
                            break;

                        // Si c'est streamnode (il y a en a 2... dans l'XML).
                        case "streamnode":
                            NodeList nl = (NodeList) racineNoeuds.item(i).getAttributes().getNamedItem("id");

                            // On ne prend que celui avec les info images:
                            if ((nl + "").equals("id=\"1\"")) {

                                // Récupérer le timecode de début:
                                int b = (Integer.parseInt(((Element) racineNoeuds.item(i)).getAttribute("StartTimecode")) / 1000)/* * 24*/;

                                this.tcstart = b + "";

                                streamnode = (NodeList) racineNoeuds.item(i).getChildNodes();
                            }
                            break;
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // On cherche le node "info":
        NodeList field = findNodeListByName(streamnode, "info");

        // Ici se trouve la liste des fields!!
        for (int i = 0; i < field.getLength(); i++) {

            // On ne prend que les nodes:
            if (field.item(i).getNodeType() == Node.ELEMENT_NODE) {

                // En fonction du nom du node:
                switch (field.item(i).getNodeName()) {
                    // Quand on tient une des erreurs, on l'ajoute à la liste:
                    case "field":

                        //System.out.println(((Element) field.item(i)).getAttribute("name"));
                        if (((Element) field.item(i)).getAttribute("name").equals("Resolution")) {
                            // Récupérer la résolution (hauteur x largeur):
                            String a = ((Element) field.item(i)).getAttribute("value");
                            Scanner sc = new Scanner(a);
                            sc.useDelimiter("x");
                            this.largeur = sc.nextInt();
                            this.hauteur = sc.nextInt();
                            sc.close();
                        } else if (((Element) field.item(i)).getAttribute("name").equals("Frame Rate")) {
                            //System.out.println("Framerate/XMLBaton: " + Integer.parseInt(((Element) field.item(i)).getAttribute("value")));

                            this.framerate = Integer.parseInt(((Element) field.item(i)).getAttribute("value"));

                            // Seulement maintenant on peut définir le TC start (car il faut connaitre le framerate):
                            this.tcstart = new Timecode((Integer.parseInt(this.tcstart) * this.framerate), this.framerate).toString();
                        } else if (((Element) field.item(i)).getAttribute("name").equals("Duration")) {
                            NodeList duree = findNodeListByName(field.item(i).getChildNodes(), "DurationSMPTE");

                            // Vérifie qu'une durée est renseignée:
                            if (!((Element) field.item(i)).getAttribute("desc").equals("Info not found")) {
                                this.duree = new Timecode(((Element) duree).getAttribute("value"));
                            } else {
                                this.duree = new Timecode(1);
                                System.out.println("Aucune durée n'a été trouvée! On a mis 1 image. :/");
                            }
                        }

                        break;
                }
            }
        }

    }

    /**
     * Transforme sur 2 digit:
     */
    private String digit(int valeur) {
        return (valeur > 9) ? "" + valeur : "0" + valeur;
    }

    /**
     * Récupère le node enfant via son nom.
     *
     * @param list Le node où on doit chercher.
     * @param name String Nom du node à trouver.
     * @return Retourne le node demandé, sinon retourne <code>null</code> s'il
     * ne trouve pas.
     */
    private NodeList findNodeListByName(NodeList list, String name) {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(name)) {
                return ((Element) list.item(i)).getChildNodes();
            }
        }

        return null;
    }

    /**
     * Récupère le nom de la vidéo analysé.
     *
     * @return Retourne le nom du fichier vidéo analysé.
     */
    public String getNomFichier() {
        return this.nom_fichier;
    }

    /**
     * Récupère le framerate de la vidéo analysé.
     *
     * @return TODO
     */
    public int getFramerate() {
        return this.framerate;
    }

    /**
     * Récupère la hauteur de la vidéo analysé.
     *
     * @return TODO
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Récupère la largeur de la vidéo analysé.
     *
     * @return TODO
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Récupère la durée de la vidéo analysé.
     *
     * @return TODO
     */
    public Timecode getDuree() {
        this.duree.setFramerate(this.framerate);
        return this.duree;
    }

    /**
     * Récupère le timecode de début de la vidéo analysé.
     *
     * @return TODO
     */
    public String getStartTC() {
        return this.tcstart;
    }

}
