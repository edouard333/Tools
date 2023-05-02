package com.phenix.tools.XMLNLE;

import com.phenix.tools.Timecode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Gère l'XML venant du logiciel Baton.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class XMLBaton {

    /**
     *
     */
    private Document document;

    /**
     *
     */
    private Element racine;

    /**
     *
     */
    private NodeList racineNoeuds;

    /**
     *
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
     * Contient la liste des erreurs du rapport Baton.
     */
    private ListeErreur liste_erreur;

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

                        // Si c'est streamnode (il y a en a 2 dans l'XML).
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
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException exception) {
            exception.printStackTrace();
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

                        if (((Element) field.item(i)).getAttribute("name").equals("Resolution")) {
                            // Récupérer la résolution (hauteur x largeur):
                            String a = ((Element) field.item(i)).getAttribute("value");
                            Scanner sc = new Scanner(a);
                            sc.useDelimiter("x");
                            this.largeur = sc.nextInt();
                            this.hauteur = sc.nextInt();
                            sc.close();
                        } else if (((Element) field.item(i)).getAttribute("name").equals("Frame Rate")) {
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
                            }
                        }

                        break;
                }
            }
        }

        this.liste_erreur = new ListeErreur(fichier, ErreurBaton.CODEC_PR4444);
    }

    /**
     * Transforme la valeur sur 2 digit.
     *
     * @param valeur La valeur.
     * @return La valeur sous forme de 2 digits.
     */
    private String digit(int valeur) {
        return (valeur > 9) ? "" + valeur : "0" + valeur;
    }

    /**
     * Retourne le node enfant via son nom.
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
     * Retourne le nom de la vidéo analysé.
     *
     * @return Retourne le nom du fichier vidéo analysé.
     */
    public String getNomFichier() {
        return this.nom_fichier;
    }

    /**
     * Retourne le framerate de la vidéo analysé.
     *
     * @return Le framerate.
     */
    public int getFramerate() {
        return this.framerate;
    }

    /**
     * Retourne la hauteur de la vidéo analysé.
     *
     * @return La hauteur.
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Retourne la largeur de la vidéo analysé.
     *
     * @return La largeur.
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Retourne la liste de toutes les erreurs selon un type.
     *
     * @return Liste d'erreur de Baton
     */
    public ListeErreur getListeErreur() {
        return this.liste_erreur;
    }

    /**
     * Retourne la liste de toutes les erreurs selon un type.
     *
     * @param type_erreur Le type d'erreur.
     * @return Liste d'erreur selon un type.
     */
    public ArrayList<ErreurBaton> getListeErreur(String type_erreur) {
        return this.liste_erreur.getList(type_erreur);
    }

    /**
     * Retourne la durée de la vidéo analysé.
     *
     * @return Durée.
     */
    public Timecode getDuree() {
        this.duree.setFramerate(this.framerate);
        return this.duree;
    }

    /**
     * Retourne le timecode de début de la vidéo analysé.
     *
     * @return Timecode début.
     */
    public String getStartTC() {
        return this.tcstart;
    }
}
