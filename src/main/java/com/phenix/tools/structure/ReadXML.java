package com.phenix.tools.structure;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * TODO Lecture XML Baton ?
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 0.1.0
 */
public class ReadXML extends StructureXML {

    private Document document;
    private Element racine;
    private NodeList racineNoeuds;

    private NodeList documents;

    private NodeList commun;
    private NodeList image;
    private NodeList audio;
    private NodeList soustitre;
    private NodeList qc;
    private NodeList timecode;

    /**
     * Initialise l'XML.
     *
     * @param fichier ...
     */
    public ReadXML(File fichier) {
        try {
            this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichier);

            this.racine = document.getDocumentElement();
            racineNoeuds = racine.getChildNodes();

            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    switch (racineNoeuds.item(i).getNodeName()) {
                        case NODE_COMMUN:
                            this.commun = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                        case NODE_IMAGE:
                            this.image = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                        case NODE_AUDIO:
                            this.audio = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                        case NODE_SOUSTITRE:
                            this.soustitre = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                        case NODE_QC:
                            this.qc = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                        case NODE_TIMECODE:
                            this.timecode = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                        case NODE_DOCUMENT:
                            this.documents = (NodeList) racineNoeuds.item(i).getChildNodes();
                            break;
                    }
                }
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public String getXmlVersion() {
        return document.getXmlVersion();
    }

    public String getXmlEncoding() {
        return document.getXmlEncoding();
    }

    public boolean getXmlStandalone() {
        return document.getXmlStandalone();
    }

    private NodeList findNodeListByName(NodeList list, String name) {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(name)) {
                return ((Element) list.item(i)).getChildNodes();
            }
        }

        return null;
    }

    private String findByName(NodeList list, String name) {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(name)) {
                return list.item(i).getTextContent();
            }
        }

        return null;
    }

    private String findByName(NodeList list, String name, String attribut) {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(name)) {
                return ((Element) list.item(i)).getAttribute(attribut);
            }
        }

        return null;
    }

    public String getDocumentVersion() {
        return findByName(this.documents, DOCUMENT_VERSION);
    }

    public String getCommunFichier() {
        return findByName(this.commun, COMMUN_FICHIER);
    }

    public String getCommunProduction() {
        return findByName(this.commun, COMMUN_PRODUCTION);
    }

    public String getCommunTitre() {
        return findByName(this.commun, COMMUN_TITRE);
    }

    public String getCommunType() {
        return findByName(this.commun, COMMUN_TYPE);
    }

    public String getCommunPoids() {
        return findByName(this.commun, COMMUN_POIDS);
    }

    public String getCommunCheckSum() {
        return findByName(this.commun, COMMUN_CHECKSUM);
    }

    public String getCommunInformation() {
        return findByName(this.commun, COMMUN_INFORMATION);
    }

    public String getCommunCheckSum(String attribut) {
        return findByName(this.commun, COMMUN_CHECKSUM, attribut);
    }

    public String getImageCodec() {
        return findByName(this.image, IMAGE_CODEC);
    }

    public String getImageHauteur() {
        return findByName(findNodeListByName(this.image, IMAGE_RESOLUTION), IMAGE_RESOLUTION_HAUTEUR);
    }

    public String getImageLargeur() {
        return findByName(findNodeListByName(this.image, IMAGE_RESOLUTION), IMAGE_RESOLUTION_LARGEUR);
    }

    public String getImageRatio() {
        return findByName(this.image, IMAGE_RATIO);
    }

    public String getImageBalayage() {
        return findByName(this.image, IMAGE_BALAYAGE);
    }

    public String getImageWatermark() {
        return findByName(this.image, IMAGE_WATERMARK);
    }

    public String getImageInformation() {
        return findByName(this.image, IMAGE_INFORMATION);
    }

    public String getAudioCodec() {
        return findByName(this.audio, AUDIO_CODEC);
    }

    public String getAudioEchantillon() {
        return findByName(this.audio, AUDIO_ECHANTILLON);
    }

    public String getAudioFrequence() {
        return findByName(this.audio, AUDIO_FREQUENCE);
    }

    public String getAudioNombrePiste() {
        return findByName(this.audio, AUDIO_NOMBRE_PISTE);
    }

    public String getAudioVersion() {
        return findByName(this.audio, AUDIO_VERSION);
    }

    public String getAudioMixe() {
        return findByName(this.audio, AUDIO_MIXE);
    }

    public String getAudioCanaux() {
        return findByName(this.audio, AUDIO_CANAUX);
    }

    public String getAudioCanal() {
        return findByName(this.audio, AUDIO_CANAL);
    }

    public String getSoustitrePresent() {
        return findByName(this.soustitre, SOUSTITRE_PRESENT);
    }

    public String getSoustitreNorme() {
        return findByName(this.soustitre, SOUSTITRE_NORME);
    }

    public String getSoustitreLangue() {
        return findByName(this.soustitre, SOUSTITRE_LANGUE);
    }

    public String getSoustitreType() {
        return findByName(this.soustitre, SOUSTITRE_TYPE);
    }

    public String getSoustitrePresentPartiel() {
        return findByName(this.soustitre, SOUSTITRE_PRESENCE_SOUSTITRE_PARTIEL);
    }

    public String getSoustitreInformation() {
        return findByName(this.soustitre, SOUSTITRE_INFORMATION);
    }

    public String getQCAccept() {
        return findByName(this.qc, QC_ACCEPTE);
    }

    public String getQCDeroulement() {
        return findByName(this.qc, QC_DEROULEMENT);
    }

    public String getQCRemarque() {
        return findByName(this.qc, QC_REMARQUE);
    }

    public String getTimecodeDebut() {
        return findByName(this.timecode, TIMECODE_DEBUT);
    }

    public String getTimecodeDuree() {
        return findByName(this.timecode, TIMECODE_DUREE);
    }

    public String getTimecodeCadence() {
        return findByName(this.timecode, TIMECODE_CADENCE);
    }

    /**
     * Retourne si c'est drop frame ou non.
     *
     * @return ...
     */
    public String getTimecodeDropframe() {
        return findByName(this.timecode, TIMECODE_DROPFRAME);
    }
}
