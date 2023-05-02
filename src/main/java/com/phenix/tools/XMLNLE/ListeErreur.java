package com.phenix.tools.XMLNLE;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Répertorie la liste des erreurs Baton dans le fichier XML Baton.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class ListeErreur {

    /**
     * Nom générique de l'erreur de conformance (drop render).
     */
    public static final String CONFORMANCE_ERROR = "Conformance";

    /**
     * Nom de l'erreur pour un defective pixel.
     */
    public static final String DEFECTIVE_PIXEL_ERROR = "Defective Pixel";

    /**
     * Nom de l'erreur pour les blacks bars.
     */
    public static final String BLACK_BARS_ERROR = "Black Bars";

    /**
     * Nom de l'erreur pour les dropouts.
     */
    public static final String VIDEO_DROPOUTERROR = "Video Dropout";

    /**
     * Nom de l'erreur pour les duplicate frame.
     */
    public static final String DUPLICATE_FRAMES_ERROR = "Duplicate Frames";

    /**
     * Nom de l'erreur pour les freeze frame.
     */
    public static final String FREEZE_FRAMES_ERROR = "Freeze Frames";

    /**
     * Document XML.
     */
    private Document document;

    /**
     * Racine du document XML.
     */
    private Element racine;

    /**
     * Les noeuds dans la racine.
     */
    private NodeList racineNoeuds;

    /**
     * Les noeuds "streamnode".
     */
    private NodeList streamnode;

    /**
     * La liste d'erreur.
     */
    private ArrayList<ErreurBaton> liste_erreur_baton;

    /**
     * Le codec de la vidéo utilisé pour la vérification Baton.
     */
    private int codec;

    /**
     * Construit une liste d'erreur à partir d'une URL d'un rapport XML Baton et
     * du codec du fichier vidéo de base.
     *
     * @param fichier URL du fichier XML Baton.
     * @param codec Codec du fichier vidéo utilisé pour la vérification Baton.
     */
    public ListeErreur(String fichier, int codec) {
        this.codec = codec;
        init(fichier);

        this.liste_erreur_baton = new ArrayList<ErreurBaton>();

        NodeList errors = findNodeListByName(streamnode, "errors");
        {
            NodeList customchecks = findNodeListByName(errors, "customchecks");
            // Ici se trouve la liste des erreurs!!
            NodeList decodedvideochecks = findNodeListByName(customchecks, "decodedvideochecks");
            addList(decodedvideochecks);
        }
        // Erreur type conformance :
        {
            NodeList customchecks = findNodeListByName(errors, "conformancechecks");
            addListConformance(customchecks);
        }

    }

    /**
     * Retourne les erreurs se trouvant dans le rapport XML Baton.
     *
     * @param fichier Le fichier XML Baton.
     */
    private void init(String fichier) {

        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fichier));

            racine = document.getDocumentElement();
            racineNoeuds = racine.getChildNodes();

            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

                    switch (racineNoeuds.item(i).getNodeName()) {

                        case "streamnode":
                            NodeList nl = (NodeList) racineNoeuds.item(i).getAttributes().getNamedItem("id");
                            // On ne prend que celui avec les info images:
                            if ((nl + "").equals("id=\"1\"")) {
                                streamnode = (NodeList) racineNoeuds.item(i).getChildNodes();
                            }
                            break;

                    }
                }
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Récupére le node enfant via son nom.
     *
     * @param list Le node où on doit chercher.
     * @param name Nom du node à trouver.
     *
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
     * Ajouter les erreurs se trouvant dans ce node (du rapport XML Baton) à la
     * liste d'erreur.
     *
     * @param nodeList Le node contenant la liste des erreurs à ajoute aux
     * erreurs.
     */
    private void addListConformance(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE && nodeList.item(i).getNodeName().equals("error")) {
                String description = ((Element) nodeList.item(i)).getAttribute("description");
                String smptetimecode = ((Element) nodeList.item(i)).getAttribute("smptetimecode");
                this.liste_erreur_baton.add(new ErreurBaton(description, 1, smptetimecode, smptetimecode, CONFORMANCE_ERROR, this.codec));
            }
        }
    }

    /**
     * Ajouter les erreurs se trouvant dans ce node (du rapport XML Baton) à la
     * liste d'erreur.
     *
     * @param nodeList Le node contenant la liste des erreurs à ajoute aux
     * erreurs.
     */
    private void addList(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                switch (nodeList.item(i).getNodeName()) {

                    // Quand on tient une des erreurs, on l'ajoute à la liste:
                    case "error":

                        int FrameDuration = Integer.parseInt(((Element) nodeList.item(i)).getAttribute("FrameDuration"));
                        String description = ((Element) nodeList.item(i)).getAttribute("description");
                        String smptetimecode = ((Element) nodeList.item(i)).getAttribute("startsmptetimecode"); //smptetimecode
                        String endsmptetimecode = ((Element) nodeList.item(i)).getAttribute("endsmptetimecode");
                        String item = ((Element) nodeList.item(i)).getAttribute("item");

                        if (item.equals("Defective Pixel")) {
                            ArrayList<Pixel> liste_pixel = new ArrayList<Pixel>();

                            // Récupère les positions du/des pixels:
                            Node param = ((Element) ((Element) nodeList.item(i)).getElementsByTagName("Params").item(0)).getElementsByTagName("Param").item(0);
                            String coordonnees = ((Element) param).getAttribute("Value"); // Récupère le 1er car il n'y en a qu'un.

                            String liste[] = coordonnees.split("\\D");

                            String liste_nombre = "";

                            for (int j = 0; j < liste.length; j++) {
                                liste_nombre += " " + liste[j];
                            }

                            int nb_pixel = (liste_nombre.split("\\s+").length - 1) / 2;

                            Scanner sc = new Scanner(liste_nombre);

                            for (int nombre = 0; nombre < nb_pixel; nombre++) {
                                liste_pixel.add(new Pixel(sc.nextInt(), sc.nextInt()));
                            }

                            sc.close();
                            this.liste_erreur_baton.add(new ErreurBatonDefectivePixel(description, FrameDuration, smptetimecode, endsmptetimecode, item, this.codec, liste_pixel));
                        } else {
                            this.liste_erreur_baton.add(new ErreurBaton(description, FrameDuration, smptetimecode, endsmptetimecode, item, this.codec));
                        }

                        break;
                }
            }
        }
    }

    /**
     * Retourne le codec.
     *
     * @return Le codec.
     */
    public int getCodec() {
        return this.codec;
    }

    /**
     * Retourne la liste d'erreur.
     *
     * @return Liste d'erreur.
     */
    public ArrayList<ErreurBaton> getList() {
        return this.liste_erreur_baton;
    }

    /**
     * Retourne l'ensemble des types d'erreurs se trouvant dans le rapport.
     *
     * @return Liste des types d'erreur.
     */
    public ArrayList<String> getListTypeErreur() {
        ArrayList<String> l = new ArrayList<String>();

        for (int i = 0; i < this.liste_erreur_baton.size(); i++) {
            if (!l.contains(this.liste_erreur_baton.get(i).getItem())) {
                l.add(this.liste_erreur_baton.get(i).getItem());
            }
        }

        return l;
    }

    /**
     * Retourne la liste d'erreur avec un seule type d'erreur, reçu en
     * paramètre.
     *
     * @param type Le filtre des erreurs qu'on souhaite retourner.
     *
     * @return La liste d'erreur avec juste celle avec le type d'erreur reçu en
     * paramètre.
     */
    public ArrayList<ErreurBaton> getList(String type) {
        ArrayList<ErreurBaton> l = new ArrayList<ErreurBaton>();

        for (int i = 0; i < this.liste_erreur_baton.size(); i++) {
            if (this.liste_erreur_baton.get(i).getItem().equals(type)) {
                l.add(this.liste_erreur_baton.get(i));
            }
        }

        return l;
    }

    /**
     * Retourne un affichage de l'ensemble des types erreurs.
     *
     * @return Types d'erreur.
     */
    public String getListTypeErreurA() {
        ArrayList<String> l = getListTypeErreur();
        String a = "";

        for (int i = 0; i < l.size(); i++) {
            a += l.get(i) + ", ";
        }

        return a;
    }

    /**
     * Afficher l'ensemble des types d'erreurs rencontré dans le rapport.
     */
    public void AfficheErreur() {
        for (int i = 0; i < this.liste_erreur_baton.size(); i++) {
            System.out.println(this.liste_erreur_baton.get(i));
        }
    }

}
