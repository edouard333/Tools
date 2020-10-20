package com.phenix.tools.structure;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 0.1.0
 */
public class WriteXML extends StructureXML {

    private Document document;
    private Element racine;

    private Element commun;
    private Element image;
    private Element audio;
    private Element soustitre;
    private Element qc;
    private Element timecode;

    // Informations:
    private String image_codec = defaultValue();
    private String image_ratio = defaultValue();
    private String image_balayage = defaultValue();
    private String image_watermark = defaultValue();
    private String image_information = defaultValue();

    // Où se trouvera l'XML:
    private String destinationFichier;

    /**
     * Initalise les données.
     *
     * @param destinationFichier ...
     */
    public WriteXML(String destinationFichier) {
        try {
            // création d'un Document
            this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // création de l'Element racine
            this.racine = document.createElement("element");
            this.document.appendChild(racine);

            this.destinationFichier = destinationFichier;

            Element documents = document.createElement(NODE_DOCUMENT);

            // Ajoute tous les enfants de "commun" avec comme valeur par défaut "non renseigné".
            documents.appendChild(getChild(DOCUMENT_VERSION, "0.9"));

            racine.appendChild(document.createComment("Information sur le document XML."));
            racine.appendChild(documents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element getChild(String nom, String valeur) {
        Element node = document.createElement(nom);
        node.appendChild(document.createTextNode(valeur));
        return node;
    }

    private Element getChild(String nom, SousNode[] sousNodes) {
        Element node = document.createElement(nom);

        for (int i = 0; i < sousNodes.length; i++) {
            Element sousNode = document.createElement(sousNodes[i].getNom());
            sousNode.appendChild(document.createTextNode(sousNodes[i].getValeur()));

            String[][] attributs = sousNodes[i].getAttributs();

            for (int j = 0; j < attributs.length; j++) {
                sousNode.setAttribute(attributs[j][0], attributs[j][0 + 1]);
            }

            node.appendChild(sousNode);
        }
        return node;
    }

    /**
     * Quand on donne plus d'un attribut.
     *
     * @param nom
     * @param valeur
     * @param attribut
     * @return
     */
    private Element getChild(String nom, String valeur, String[][] attribut) {
        Element node = document.createElement(nom);
        node.appendChild(document.createTextNode(valeur));
        for (int i = 0; i < attribut.length; i++) {
            node.setAttribute(attribut[i][0], attribut[i][0 + 1]);
        }
        return node;
    }

    private void commun() {
        this.commun = document.createElement(NODE_COMMUN);

        // Ajoute tous les enfants de "commun" avec comme valeur par défaut "non renseigné".
        this.commun.appendChild(getChild(COMMUN_FICHIER, defaultValue()));
        this.commun.appendChild(getChild(COMMUN_PRODUCTION, defaultValue()));
        this.commun.appendChild(getChild(COMMUN_TITRE, defaultValue()));
        this.commun.appendChild(getChild(COMMUN_TYPE, defaultValue()));
        this.commun.appendChild(getChild(COMMUN_POIDS, defaultValue()));
        this.commun.appendChild(getChild(COMMUN_CHECKSUM, defaultValue(), new String[][]{{"mode", defaultValue()}}));
        this.commun.appendChild(getChild(COMMUN_INFORMATION, defaultValue()));
    }

    private void image() {
        this.image = document.createElement(NODE_IMAGE);
        this.image.appendChild(getChild(IMAGE_CODEC, this.image_codec));

        // Sous-node avec hauteur et largeur:
        SousNode[] sousnode = new SousNode[2];
        sousnode[0] = new SousNode(IMAGE_RESOLUTION_HAUTEUR, defaultValue(), new String[][]{UNITE_RESOLUTION});
        sousnode[1] = new SousNode(IMAGE_RESOLUTION_LARGEUR, defaultValue(), new String[][]{UNITE_RESOLUTION});

        this.image.appendChild(getChild(IMAGE_RESOLUTION, sousnode));

        this.image.appendChild(getChild(IMAGE_RATIO, this.image_ratio));
        this.image.appendChild(getChild(IMAGE_BALAYAGE, this.image_balayage));

        this.image.appendChild(getChild(IMAGE_WATERMARK, this.image_watermark));
        this.image.appendChild(getChild(IMAGE_INFORMATION, this.image_information));
    }

    private void audio() {
        this.audio = document.createElement(NODE_AUDIO);

        this.audio.appendChild(getChild(AUDIO_CODEC, defaultValue()));
        this.audio.appendChild(getChild(AUDIO_ECHANTILLON, defaultValue()));
        this.audio.appendChild(getChild(AUDIO_FREQUENCE, defaultValue()));
        this.audio.appendChild(getChild(AUDIO_NOMBRE_PISTE, defaultValue()));

        // Sous-node pour chaque piste:
        this.audio.appendChild(getChild(AUDIO_VERSION, defaultValue()));
        this.audio.appendChild(getChild(AUDIO_MIXE, defaultValue()));
        this.audio.appendChild(getChild(AUDIO_CANAUX, defaultValue()));
        this.audio.appendChild(getChild(AUDIO_CANAL, defaultValue()));
    }

    private void soustitre() {
        this.soustitre = document.createElement(NODE_SOUSTITRE);

        this.soustitre.appendChild(getChild(SOUSTITRE_PRESENT, defaultValue()));
        this.soustitre.appendChild(getChild(SOUSTITRE_NORME, defaultValue()));
        this.soustitre.appendChild(getChild(SOUSTITRE_LANGUE, defaultValue()));
        this.soustitre.appendChild(getChild(SOUSTITRE_TYPE, defaultValue()));
        this.soustitre.appendChild(getChild(SOUSTITRE_PRESENCE_SOUSTITRE_PARTIEL, defaultValue()));
        this.soustitre.appendChild(getChild(SOUSTITRE_INFORMATION, defaultValue()));
    }

    private void qc() {
        this.qc = document.createElement(NODE_QC);

        this.qc.appendChild(getChild(QC_ACCEPTE, defaultValue()));
        this.qc.appendChild(getChild(QC_DEROULEMENT, defaultValue()));
        this.qc.appendChild(getChild(QC_REMARQUE, defaultValue()));
    }

    private void timecode() {
        this.timecode = document.createElement(NODE_TIMECODE);

        this.timecode.appendChild(getChild(TIMECODE_DEBUT, defaultValue()));
        this.timecode.appendChild(getChild(TIMECODE_DUREE, defaultValue()));
        this.timecode.appendChild(getChild(TIMECODE_CADENCE, defaultValue()));
        this.timecode.appendChild(getChild(TIMECODE_DROPFRAME, defaultValue()));
    }

    // Setter pour la partie "commune":
    public void setCommunFichier(String valeur) {
        this.commun.replaceChild(getChild(COMMUN_FICHIER, valeur), this.commun.getElementsByTagName(COMMUN_FICHIER).item(0));
    }

    public void setCommunProduction(String valeur) {
        this.commun.replaceChild(getChild(COMMUN_PRODUCTION, valeur), this.commun.getElementsByTagName(COMMUN_PRODUCTION).item(0));
    }

    public void setCommunTitre(String valeur) {
        this.commun.replaceChild(getChild(COMMUN_TITRE, valeur), this.commun.getElementsByTagName(COMMUN_TITRE).item(0));
    }

    public void setCommunType(String valeur) {
        this.commun.replaceChild(getChild(COMMUN_TYPE, valeur), this.commun.getElementsByTagName(COMMUN_TYPE).item(0));
    }

    public void setCommunPoids(String valeur) {
        this.commun.replaceChild(getChild(COMMUN_POIDS, valeur, new String[][]{UNITE_POIDS}), this.commun.getElementsByTagName(COMMUN_POIDS).item(0));
    }

    public void setCommunChecksum(String mode, String valeur) {
        this.commun.replaceChild(getChild(COMMUN_CHECKSUM, valeur, new String[][]{{"mode", mode}}), this.commun.getElementsByTagName(COMMUN_CHECKSUM).item(0));
    }

    public void setCommunInformation(String valeur) {
        this.commun.replaceChild(getChild(COMMUN_INFORMATION, valeur), this.commun.getElementsByTagName(COMMUN_INFORMATION).item(0));
    }

    // Setter pour la partie "image":
    public void setImageCodec(String valeur, String debit) {
        if (valeur.equals("H264")) {
            this.image.replaceChild(getChild(IMAGE_CODEC, valeur, new String[][]{{"debit", debit}, UNITE_DEBIT}), this.image.getElementsByTagName(IMAGE_CODEC).item(0));
        } else {
            this.image.replaceChild(getChild(IMAGE_CODEC, valeur), this.image.getElementsByTagName(IMAGE_CODEC).item(0));
        }
    }

    private Element findByName(NodeList list, String name) {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(name)) {
                return (Element) list.item(i);
            }
        }

        return null;
    }

    // Sous-node avec hauteur et largeur:
    public void setImageResolution(String hauteur, String largeur) {
        this.image.getElementsByTagName(IMAGE_RESOLUTION).item(0).replaceChild(getChild(IMAGE_RESOLUTION_HAUTEUR, hauteur, new String[][]{UNITE_RESOLUTION}), findByName(this.image.getElementsByTagName(IMAGE_RESOLUTION).item(0).getChildNodes(), IMAGE_RESOLUTION_HAUTEUR));
        this.image.getElementsByTagName(IMAGE_RESOLUTION).item(0).replaceChild(getChild(IMAGE_RESOLUTION_LARGEUR, largeur, new String[][]{UNITE_RESOLUTION}), findByName(this.image.getElementsByTagName(IMAGE_RESOLUTION).item(0).getChildNodes(), IMAGE_RESOLUTION_LARGEUR));
    }

    public void setImageRatio(String valeur) {
        this.image.replaceChild(getChild(IMAGE_RATIO, valeur), this.image.getElementsByTagName(IMAGE_RATIO).item(0));
    }

    public void setImageBalayage(String valeur) {
        this.image.replaceChild(getChild(IMAGE_BALAYAGE, valeur), this.image.getElementsByTagName(IMAGE_BALAYAGE).item(0));
    }

    public void setImageWatermark(String valeur) {
        this.image.replaceChild(getChild(IMAGE_WATERMARK, valeur), this.image.getElementsByTagName(IMAGE_WATERMARK).item(0));
    }

    public void setImageInformation(String valeur) {
        this.image.replaceChild(getChild(IMAGE_INFORMATION, valeur), this.image.getElementsByTagName(IMAGE_INFORMATION).item(0));
    }

    // Setter pour la partie "audio":
    public void setAudioCodec(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_CODEC, valeur), this.audio.getElementsByTagName(AUDIO_CODEC).item(0));
    }

    public void setAudioEchantillon(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_ECHANTILLON, valeur, new String[][]{UNITE_ECHANTILLON}), this.audio.getElementsByTagName(AUDIO_ECHANTILLON).item(0));
    }

    public void setAudioFrequence(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_FREQUENCE, valeur, new String[][]{UNITE_FREQUENCE_AUDIO}), this.audio.getElementsByTagName(AUDIO_FREQUENCE).item(0));
    }

    public void setAudioNombrePiste(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_NOMBRE_PISTE, valeur), this.audio.getElementsByTagName(AUDIO_NOMBRE_PISTE).item(0));
    }

    // Sous-node pour chaque piste:
    public void setAudioVersion(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_VERSION, valeur), this.audio.getElementsByTagName(AUDIO_VERSION).item(0));
    }

    public void setAudioMixe(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_MIXE, valeur), this.audio.getElementsByTagName(AUDIO_MIXE).item(0));
    }

    public void setAudioCanaux(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_CANAUX, valeur), this.audio.getElementsByTagName(AUDIO_CANAUX).item(0));
    }

    public void setAudioCanal(String valeur) {
        this.audio.replaceChild(getChild(AUDIO_CANAL, valeur), this.audio.getElementsByTagName(AUDIO_CANAL).item(0));
    }

    // Setter pour la partie "sous-titre":
    public void setSoustitrePresent(String valeur) {
        this.soustitre.replaceChild(getChild(SOUSTITRE_PRESENT, valeur), this.soustitre.getElementsByTagName(SOUSTITRE_PRESENT).item(0));
    }

    public void setSoustitreNorme(String valeur) {
        this.soustitre.replaceChild(getChild(SOUSTITRE_NORME, valeur), this.soustitre.getElementsByTagName(SOUSTITRE_NORME).item(0));
    }

    public void setSoustitreLangue(String valeur) {
        this.soustitre.replaceChild(getChild(SOUSTITRE_LANGUE, valeur), this.soustitre.getElementsByTagName(SOUSTITRE_LANGUE).item(0));
    }

    public void setSoustitreType(String valeur) {
        this.soustitre.replaceChild(getChild(SOUSTITRE_TYPE, valeur), this.soustitre.getElementsByTagName(SOUSTITRE_TYPE).item(0));
    }

    public void setSoustitrePresentPartiel(String valeur) {
        this.soustitre.replaceChild(getChild(SOUSTITRE_PRESENCE_SOUSTITRE_PARTIEL, valeur), this.soustitre.getElementsByTagName(SOUSTITRE_PRESENCE_SOUSTITRE_PARTIEL).item(0));
    }

    public void setSoustitreInformation(String valeur) {
        this.soustitre.replaceChild(getChild(SOUSTITRE_INFORMATION, valeur), this.soustitre.getElementsByTagName(SOUSTITRE_INFORMATION).item(0));
    }

    // Setter pour la partie "QC":
    public void setQCAccept(String valeur) {
        this.qc.replaceChild(getChild(QC_ACCEPTE, valeur), this.qc.getElementsByTagName(QC_ACCEPTE).item(0));
    }

    // Doit donner lieu à des sous-nodes avec TC IN/OUT, IMAGE ou SON, remarque...
    public void setQCDeroulement(String valeur) {
        this.qc.replaceChild(getChild(QC_DEROULEMENT, valeur), this.qc.getElementsByTagName(QC_DEROULEMENT).item(0));
    }

    public void setQCRemarque(String valeur) {
        this.qc.replaceChild(getChild(QC_REMARQUE, valeur), this.qc.getElementsByTagName(QC_REMARQUE).item(0));
    }

    // Setter pour la partie "timecode":
    public void setTimecodeDebut(String valeur) {
        this.timecode.replaceChild(getChild(TIMECODE_DEBUT, valeur, new String[][]{UNITE_TIMECODE, FORMAT_TEMPS}), this.timecode.getElementsByTagName(TIMECODE_DEBUT).item(0));
    }

    public void setTimecodeDuree(String valeur) {
        this.timecode.replaceChild(getChild(TIMECODE_DUREE, valeur, new String[][]{UNITE_TIMECODE, FORMAT_TEMPS}), this.timecode.getElementsByTagName(TIMECODE_DUREE).item(0));
    }

    public void setTimecodeCadence(String valeur) {
        this.timecode.replaceChild(getChild(TIMECODE_CADENCE, valeur, new String[][]{UNITE_FREQUENCE_IMAGE}), this.timecode.getElementsByTagName(TIMECODE_CADENCE).item(0));
    }

    public void setTimecodeDropframe(String valeur) {
        this.timecode.replaceChild(getChild(TIMECODE_DROPFRAME, valeur), this.timecode.getElementsByTagName(TIMECODE_DROPFRAME).item(0));
    }

    /**
     * Définit le codec de l'image.
     *
     * @param image_codec ...
     */
    public void setImageCodec(String image_codec) {
        this.image_codec = image_codec;
    }

    /**
     * Export l'XML.
     *
     * @return Retourne false si l'export ne s'est pas bien passé (a retourné
     * une erreur).
     */
    public boolean close() {
        try {
            commun();
            image();
            audio();
            soustitre();
            qc();
            timecode();

            // On ajoute toutes la partie qui ont quelque chose:
            racine.appendChild(document.createComment("Commun"));
            racine.appendChild(this.commun);

            racine.appendChild(document.createComment("Image"));
            racine.appendChild(this.image);

            racine.appendChild(document.createComment("Audio"));
            racine.appendChild(this.audio);

            racine.appendChild(document.createComment("Sous titre"));
            racine.appendChild(this.soustitre);

            racine.appendChild(document.createComment("Controle qualite"));
            racine.appendChild(this.qc);

            racine.appendChild(document.createComment("Timecode"));
            racine.appendChild(this.timecode);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            //prologue
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            //formatage
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            //sortie
            transformer.transform(new DOMSource(document), new StreamResult(new File(this.destinationFichier)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

class SousNode {

    private String nom;
    private String valeur;
    private String[][] attributs;

    public SousNode(String nom, String valeur, String[][] attributs) {
        this.nom = nom;
        this.valeur = valeur;
        this.attributs = attributs;
    }

    public String getNom() {
        return this.nom;
    }

    public String getValeur() {
        return this.valeur;
    }

    public String[][] getAttributs() {
        return this.attributs;
    }

}
