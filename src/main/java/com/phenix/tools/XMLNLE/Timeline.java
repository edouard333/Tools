package com.phenix.tools.XMLNLE;

import com.phenix.tools.tools.Timecode;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class Timeline {

    /**
     * Le nom de la timeline.
     */
    private String nom;

    /**
     * Le timecode de début de la timeline.
     */
    private Timecode startTc;

    /**
     * Framerate de la timeline.
     */
    private int framerate;

    /**
     * ...
     */
    private int hauteur;

    /**
     * ...
     */
    private int largeur;

    /**
     * ...
     */
    private static int clipitem = 0;

    /**
     * ...
     */
    private int numero_timeline;

    /**
     * Cette variable sert à savoir combien de timeline ont été faite.
     */
    public static int timeline = 0;

    /**
     * Liste des UUID pour rendre unique une timeline.
     */
    private String[] uuid = {"4381a977-9cb2-42a7-bfd6-b9ae5e0ede20", "57ff1067-1a42-48f7-8a53-be80d3f14505", "7a24a4a6-d76c-4ad2-b71f-eade1e61a92c", "78ef0806-7f87-4d70-ad55-ba7ad7e2132e", "5d3059ef-dbd3-4d28-8d1f-d49a351fc597", "3f523f59-06b7-4df8-9d75-4b3d2e930afb"};

    /**
     * Nombre de canaux audio.
     */
    private int canaux;

    /**
     * ...
     */
    private ArrayList<Integer> listePiste = new ArrayList<Integer>();

    /**
     * ...
     */
    private ArrayList<Media> listMedia = new ArrayList<Media>();

    /**
     * ...
     */
    private ArrayList<Timecode> listTCstart = new ArrayList<Timecode>();

    /**
     * ...
     */
    private ArrayList<Timecode> listTCend = new ArrayList<Timecode>();

    /**
     * ...
     */
    private ArrayList<Marqueur> listMarqueur = new ArrayList<Marqueur>();

    /**
     * ...
     */
    private HashMap<Integer, Integer> finPiste = new HashMap<Integer, Integer>();

    /**
     * Liste des pistes vidéos à verrouiller.
     */
    private ArrayList<Integer> liste_piste_video_verrouiller = new ArrayList<Integer>(10);

    /**
     * Liste des pistes audio à verrouiller.
     */
    private ArrayList<Integer> liste_piste_audio_verrouiller = new ArrayList<Integer>(4);

    /**
     * Quand on veut verrouiller toutes les pistes vidéos.
     */
    private boolean verrouiller_piste_video = false;

    /**
     * Quand on veut verrouiller toutes les pistes audio.
     */
    private boolean verrouiller_piste_audio = false;

    /**
     * Définit la timeline est pour quel logiciel.
     * Par-défaut c'est pour Adobe Premiere.
     */
    private byte logiciel_destination = XMLStandard.PREMIERE;

    /**
     * Créé une timeline avec toutes les données par-défaut.
     */
    public Timeline() {
        this.nom = "Sans titre";
        this.framerate = 25;
        this.startTc = new Timecode("00:00:00:00");

        this.canaux = 2;

        this.numero_timeline = timeline;
        timeline++;
    }

    /**
     * Créé une timeline avec un nom.
     *
     * @param nom Nom de la timeline.
     */
    public Timeline(String nom) {
        this.nom = nom;
        this.framerate = 25;
        this.startTc = new Timecode("00:00:00:00");

        this.canaux = 2;

        this.numero_timeline = timeline;
        timeline++;
    }

    /**
     * Créé une timeline avec un nom et un timecode de début.
     *
     * @param nom Nom de la timeline.
     * @param startTc Timecode de début.
     */
    public Timeline(String nom, Timecode startTc) {
        this.nom = nom;
        this.framerate = 25;
        this.startTc = startTc;

        this.canaux = 2;

        this.numero_timeline = timeline;
        timeline++;
    }

    /**
     * ...
     *
     * @param nom ...
     * @param framerate ...
     * @param startTc ...
     */
    public Timeline(String nom, int framerate, Timecode startTc) {
        this.nom = nom;
        this.framerate = framerate;
        this.startTc = startTc;

        this.canaux = 2;

        this.numero_timeline = timeline;
        timeline++;
    }

    /**
     * Modifie à quel logiciel est destiné la timeline.
     *
     * @param logiciel_destination Logiciel auquel est destiné la timeline.
     */
    public void setLogicielDestination(byte logiciel_destination) {
        this.logiciel_destination = logiciel_destination;
    }

    /**
     * Modifie le nombre de canaux audios.
     *
     * @param canaux ...
     */
    public void setCanaux(int canaux) {
        this.canaux = canaux;
    }

    /**
     * Modifie le timecode de début de la timeline.
     *
     * @param startTc Le timecode de début.
     */
    public void setStart(Timecode startTc) {
        this.startTc = startTc;
        if (this.framerate != 0) {
            this.startTc.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) this.startTc.getFramerate();
        }
    }

    /**
     * TODO
     *
     * @param numero_piste TODO
     */
    public void verrouillerPisteVideo(int numero_piste) {
        verrouillerPisteVideo(numero_piste, true);
    }

    /**
     * Verrouille (ou déverrouille) une piste vidéo.
     *
     * @param numero_piste ...
     * @param verrouiller ...
     */
    public void verrouillerPisteVideo(int numero_piste, boolean verrouiller) {
        if (verrouiller) {
            this.liste_piste_video_verrouiller.add(numero_piste);
        } else {
            this.liste_piste_video_verrouiller.remove(numero_piste);
        }
    }

    /**
     * TODO
     *
     * @param numero_piste TODO
     */
    public void verrouillerPisteAudio(int numero_piste) {
        verrouillerPisteAudio(numero_piste, true);
    }

    /**
     * Verrouille (ou déverrouille) une piste audio.
     *
     * @param numero_piste ...
     * @param verrouiller ...
     */
    public void verrouillerPisteAudio(int numero_piste, boolean verrouiller) {
        if (verrouiller) {
            this.liste_piste_audio_verrouiller.add(numero_piste);
        } else {
            this.liste_piste_audio_verrouiller.remove(numero_piste);
        }
    }

    /**
     * ...
     */
    public void verrouillerPisteVideo() {
        this.verrouiller_piste_video = true;
    }

    /**
     * ...
     */
    public void verrouillerPisteAudio() {
        this.verrouiller_piste_audio = true;
    }

    /**
     * Vérifie que le média ajouté est conforma à la timeline.
     *
     * @param media ...
     */
    private void conformiteMedia(MediaVideo media) {
        // On traite une vidéo:
        /* if (media.getClass().getSimpleName().equals("MediaVideo")) {
            if (media.getHauteur() != this.hauteur || media.getLargeur() != this.largeur)
                System.out.println("Alert: Les dimensions du média sont différents de la timeline.");

            if (media.getFramerate() != this.framerate)
                System.out.println("Alert: Le framerate du média est différent de celui de la timeline.");
        }*/
    }

    /**
     * ...
     *
     * @param media ...
     */
    public void addMedia(Media media) {
        addMedia(1, media, this.startTc, new Timecode(this.startTc.toImage() + media.getDuree().toImage(), media.getFramerate()));
    }

    /**
     * ...
     *
     * @param piste ...
     * @param media ...
     */
    public void addMedia(int piste, Media media) {
        addMedia(piste, media, media.getIn(), media.getOut());
    }

    /**
     * ...
     *
     * @param piste ...
     * @param media ...
     * @param in ...
     * @param out ...
     */
    public void addMedia(int piste, Media media, Timecode in, Timecode out) {
        // Pas de superposition (seulement si trié):
        while (this.finPiste.containsKey(piste) && (in.toImage() <= this.finPiste.get(piste))) {
            piste++;
        }

        this.listePiste.add(piste);

        // S'il y a une nouvelle piste, on l'ajoute:
        if (!this.finPiste.containsKey(piste)) {
            this.finPiste.put(piste, out.toImage());
        } // Sinon, on met à jour le Tc end:
        else {
            // Et si le TC out et plus grand que le tc de fin de la piste:
            if (this.finPiste.get(piste) < out.toImage()) {
                this.finPiste.replace(piste, out.toImage());
            }
        }

        this.listMedia.add(media);

        if (this.framerate != 0) {
            in.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) in.getFramerate();
        }

        if (this.framerate != 0) {
            out.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) out.getFramerate();
        }

        this.listTCstart.add(in);
        this.listTCend.add(out);

        conformiteMedia((MediaVideo) media);
    }

    /**
     * ...
     *
     * @param largeur ...
     * @param hauteur ...
     */
    public void setDimension(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    /**
     * ...
     *
     * @param marqueur ...
     */
    public void addMarqueur(Marqueur marqueur) {
        this.listMarqueur.add(marqueur);
    }

    /**
     * ...
     *
     * @return ...
     */
    @Override
    public String toString() {
        // Informations générale:
        String xml = "\t<sequence id=\"sequence-" + (this.numero_timeline + 1) + "\" TL.SQAudioVisibleBase=\"0\" TL.SQVideoVisibleBase=\"0\" ";
        xml += "TL.SQVisibleBaseTime=\"0\" TL.SQAVDividerPosition=\"0.5\" TL.SQHideShyTracks=\"0\" ";
        xml += "TL.SQHeaderWidth=\"236\" Monitor.ProgramZoomOut=\"1461057696000000\" ";
        xml += "Monitor.ProgramZoomIn=\"0\" TL.SQTimePerPixel=\"5.6763479296991362\" ";
        xml += "MZ.EditLine=\"1302022512000000\" MZ.Sequence.PreviewFrameSizeHeight=\"" + this.hauteur + "\" ";
        xml += "MZ.Sequence.PreviewFrameSizeWidth=\"" + this.largeur + "\" MZ.Sequence.AudioTimeDisplayFormat=\"200\" ";
        xml += "MZ.Sequence.PreviewRenderingClassID=\"1061109567\" MZ.Sequence.PreviewRenderingPresetCodec=\"1096172337\" ";
        xml += "MZ.Sequence.PreviewRenderingPresetPath=\"EncoderPresets\\SequencePreview\\9678af98-a7b7-4bdb-b477-7ac9c8df4a4e\\I-Frame Only MPEG.epr\" ";
        xml += "MZ.Sequence.PreviewUseMaxRenderQuality=\"false\" MZ.Sequence.PreviewUseMaxBitDepth=\"false\" ";
        xml += "MZ.Sequence.EditingModeGUID=\"9678af98-a7b7-4bdb-b477-7ac9c8df4a4e\" ";
        xml += "MZ.Sequence.VideoTimeDisplayFormat=\"100\" MZ.WorkOutPoint=\"1461057696000000\" MZ.WorkInPoint=\"0\" ";
        xml += "MZ.ZeroPoint=\"" + (this.startTc.toImage() * 254016000000L / this.framerate) + "\" explodedTracks=\"true\">\n";
        xml += "\t\t<uuid>" + uuid[numero_timeline] + "</uuid>\n";
        xml += "\t\t<duration>" + listMedia.get(0).getDuree().toImage() + "</duration>\n";
        xml += "\t\t<rate>\n";
        xml += "\t\t\t<timebase>" + this.framerate + "</timebase>\n";
        xml += "\t\t\t<ntsc>FALSE</ntsc>\n";
        xml += "\t\t</rate>\n";
        xml += "\t\t<name>" + this.nom + "</name>\n";

        // Les médias dans la timeline;
        xml += "\t\t<media>\n";

        // La partie image de la timeline:
        xml += "\t\t\t<video>\n"
                + "\t\t\t\t<format>\n"
                + "\t\t\t\t\t<samplecharacteristics>\n"
                + "\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t<timebase>" + this.framerate + "</timebase>\n"
                + "\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                + "\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t<codec>\n"
                + "\t\t\t\t\t\t\t<name>Apple ProRes 422</name>\n"
                + "\t\t\t\t\t\t\t<appspecificdata>\n"
                + "\t\t\t\t\t\t\t\t<appname>Final Cut Pro</appname>\n"
                + "\t\t\t\t\t\t\t\t<appmanufacturer>Apple Inc.</appmanufacturer>\n"
                + "\t\t\t\t\t\t\t\t<appversion>7.0</appversion>\n"
                + "\t\t\t\t\t\t\t\t<data>\n"
                + "\t\t\t\t\t\t\t\t\t<qtcodec>\n"
                + "\t\t\t\t\t\t\t\t\t\t<codecname>Apple ProRes 422</codecname>\n"
                + "\t\t\t\t\t\t\t\t\t\t<codectypename>Apple ProRes 422</codectypename>\n"
                + "\t\t\t\t\t\t\t\t\t\t<codectypecode>apcn</codectypecode>\n"
                + "\t\t\t\t\t\t\t\t\t\t<codecvendorcode>appl</codecvendorcode>\n"
                + "\t\t\t\t\t\t\t\t\t\t<spatialquality>1024</spatialquality>\n"
                + "\t\t\t\t\t\t\t\t\t\t<temporalquality>0</temporalquality>\n"
                + "\t\t\t\t\t\t\t\t\t\t<keyframerate>0</keyframerate>\n"
                + "\t\t\t\t\t\t\t\t\t\t<datarate>0</datarate>\n"
                + "\t\t\t\t\t\t\t\t\t</qtcodec>\n"
                + "\t\t\t\t\t\t\t\t</data>\n"
                + "\t\t\t\t\t\t\t</appspecificdata>\n"
                + "\t\t\t\t\t\t</codec>\n"
                + "\t\t\t\t\t\t<width>" + this.largeur + "</width>\n"
                + "\t\t\t\t\t\t<height>" + this.hauteur + "</height>\n"
                + "\t\t\t\t\t\t<anamorphic>FALSE</anamorphic>\n"
                + "\t\t\t\t\t\t<pixelaspectratio>square</pixelaspectratio>\n"
                + "\t\t\t\t\t\t<fielddominance>none</fielddominance>\n"
                + "\t\t\t\t\t\t<colordepth>24</colordepth>\n"
                + "\t\t\t\t\t</samplecharacteristics>\n"
                + "\t\t\t\t</format>\n";

        ArrayList<Integer> num_piste = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < listePiste.size(); i++) {
            if (!num_piste.contains(listePiste.get(i))) {
                num_piste.add(listePiste.get(i));

                // Piste max:
                if (listePiste.get(i) > max) {
                    max = listePiste.get(i);
                }
            }
        }

        // Track / piste 1:
        for (int i = 1; i <= max; i++) {
            xml += "\t\t\t\t<track TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\">\n";

            //System.out.println("piste vidéo " + i);
            for (int j = 0; j < listePiste.size(); j++) {
                // Piste actuelle:
                if (listePiste.get(j) == i) {
                    xml += addItemClipVideo((MediaVideo) listMedia.get(j), listTCstart.get(j));
                }
            }

            xml += "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                    + "\t\t\t\t\t<locked>" + ((this.liste_piste_video_verrouiller.contains(i) || this.verrouiller_piste_video) ? "TRUE" : "FALSE") + "</locked>\n"
                    + "\t\t\t\t</track>\n";
        }

        xml += "\t\t\t</video>\n"
                + // La partie audio de la timeline:
                "\t\t\t<audio>\n"
                + "\t\t\t\t<numOutputChannels>4</numOutputChannels>\n"
                + "\t\t\t\t<format>\n"
                + "\t\t\t\t\t<samplecharacteristics>\n"
                + "\t\t\t\t\t\t<depth>16</depth>\n"
                + "\t\t\t\t\t\t<samplerate>48000</samplerate>\n"
                + "\t\t\t\t\t</samplecharacteristics>\n"
                + "\t\t\t\t</format>\n"
                + "\t\t\t\t<outputs>\n"
                + outputGroupe(this.canaux, 48000, 16)
                + "\t\t\t\t</outputs>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerCurrentValue=\"0\" PannerIsInverted=\"true\" PannerStartKeyframe=\"-91445760000000000,0.,0,0,0,0,0,0\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a une piste audio, on l'ajoute:
                ((((MediaVideo) listMedia.get(0)).getCanaux() >= 1) ? addItemClipAudio((MediaVideo) listMedia.get(0), 1) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(1) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>1</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerIsInverted=\"true\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a au moins 2 piste piste audio, on l'ajoute:
                ((((MediaVideo) listMedia.get(0)).getCanaux() >= 2) ? addItemClipAudio((MediaVideo) listMedia.get(0), 2) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(2) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>2</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerCurrentValue=\"0\" PannerIsInverted=\"true\" PannerStartKeyframe=\"-91445760000000000,0.,0,0,0,0,0,0\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a au moins 3 piste piste audio, on l'ajoute:
                ((((MediaVideo) listMedia.get(0)).getCanaux() >= 3) ? addItemClipAudio((MediaVideo) listMedia.get(0), 3) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(3) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>3</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerIsInverted=\"true\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a au moins 4 piste piste audio, on l'ajoute:
                ((((MediaVideo) listMedia.get(0)).getCanaux() >= 4) ? addItemClipAudio((MediaVideo) listMedia.get(0), 4) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(4) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>4</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t</audio>\n";

        xml += "\t\t</media>\n";

        // Information de timecode:
        xml += "\t\t<timecode>\n";
        xml += "\t\t\t<rate>\n";
        xml += "\t\t\t\t<timebase>" + this.framerate + "</timebase>\n";
        xml += "\t\t\t\t<ntsc>FALSE</ntsc>\n";
        xml += "\t\t\t</rate>\n";
        xml += "\t\t\t<string>" + this.startTc + "</string>\n";
        xml += "\t\t\t<frame>" + this.startTc.toImage() + "</frame>\n";
        xml += "\t\t\t<displayformat>NDF</displayformat>\n";
        xml += "\t\t</timecode>\n";

        // Les marques:
        for (int i = 0; i < listMarqueur.size(); i++) {
            xml += addMarqueurTimeline(listMarqueur.get(i));
        }

        xml += "\t\t<labels>\n";
        xml += "\t\t\t<label2>Forest</label2>\n";
        xml += "\t\t</labels>\n";
        xml += "\t</sequence>\n";

        return xml;
    }

    /**
     * Ajoute un clip vidéo à la timeline.
     *
     * @param m TODO
     * @param start TODO
     * @return TODO
     */
    private String addItemClipVideo(MediaVideo m, Timecode start) {
        //System.out.println("addVideo: " + m.getIn() + " * " + m.getOut() + ") " + ", duree: " + m.getDuree().toImage());
        clipitem++;

        // On définit à quel logiciel est destiné ce média vidéo.
        m.setLogicielDestination(logiciel_destination);

        String xml = "\t\t\t\t\t<clipitem id=\"clipitem-" + clipitem + "\">\n"
                + "\t\t\t\t\t\t<masterclipid>masterclip-" + m.getId() + "</masterclipid>\n"
                + "\t\t\t\t\t\t<name>" + new File(m.getNomFichier().replace("\\", "/")).getName() + "</name>\n"
                + "\t\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t\t<duration>" + m.getDuree().toImage() + "</duration>\n"
                + "\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                + "\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                + "\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t<start>" + (start.toImage() - this.startTc.toImage()) + "</start>\n"
                + "\t\t\t\t\t\t<end>" + ((start.toImage() - this.startTc.toImage() + m.getDuree().toImage()) - ((m.getDuree().toImage() > 1) ? 1 : 0)) + "</end>\n"
                + // "-1" car sinon cela ajoute une frame...
                "\t\t\t\t\t\t<in>" + m.getIn().toImage() + "</in>\n"
                + "\t\t\t\t\t\t<out>" + (m.getOut().toImage()) + "</out>\n"
                + "\t\t\t\t\t\t<pproTicksIn>0</pproTicksIn>\n"
                + // Je sais plus...
                "\t\t\t\t\t\t<pproTicksOut>" + (m.getOut().toImage() * 254016000000L / this.framerate) + "</pproTicksOut>\n"
                + // Je sais plus...
                "\t\t\t\t\t\t<alphatype>none</alphatype>\n"
                + "\t\t\t\t\t\t<pixelaspectratio>square</pixelaspectratio>\n"
                + "\t\t\t\t\t\t<anamorphic>FALSE</anamorphic>\n";

        if (!m.getTypeMedia().equals("genere")) {
            if (!m.dejaUtilise()) {

                if (this.logiciel_destination == XMLStandard.PREMIERE) {
                    xml += "\t\t\t\t\t\t<file id=\"file-" + m.getId() + "\">\n"
                            + "\t\t\t\t\t\t\t<name>" + new File(m.getNomFichier().replace("\\", "/")).getName() + "</name>\n"
                            + "\t\t\t\t\t\t\t<pathurl>" + m.getLocalisation() + "</pathurl>\n"
                            + // Où se trouve le fichier.
                            "\t\t\t\t\t\t\t<rate>\n"
                            + "\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + // Framerate du média.
                            "\t\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                            + "\t\t\t\t\t\t\t</rate>\n"
                            + "\t\t\t\t\t\t\t<duration>" + m.getDuree().toImage() + "</duration>\n"
                            + // Durée du média.
                            "\t\t\t\t\t\t\t<timecode>\n"
                            + "\t\t\t\t\t\t\t\t<rate>\n"
                            + "\t\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + // Framerate du média.
                            "\t\t\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                            + "\t\t\t\t\t\t\t\t</rate>\n"
                            + "\t\t\t\t\t\t\t\t<string>" + m.getStart() + "</string>\n"
                            + // Timecode de début du média.
                            "\t\t\t\t\t\t\t\t<frame>" + m.getStart().toImage() + "</frame>\n"
                            + // Début du média en nombre d'image.
                            "\t\t\t\t\t\t\t\t<displayformat>NDF</displayformat>\n"
                            + "\t\t\t\t\t\t\t\t<reel>\n"
                            + "\t\t\t\t\t\t\t\t\t<name></name>\n"
                            + "\t\t\t\t\t\t\t\t</reel>\n"
                            + "\t\t\t\t\t\t\t</timecode>\n"
                            + "\t\t\t\t\t\t\t<media>\n"
                            + "\t\t\t\t\t\t\t\t<video>\n"
                            + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<rate>\n"
                            + "\t\t\t\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + // Frame rate du média.
                            "\t\t\t\t\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                            + "\t\t\t\t\t\t\t\t\t\t</rate>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<width>" + m.getLargeur() + "</width>\n"
                            + // largeur du média.
                            "\t\t\t\t\t\t\t\t\t\t<height>" + m.getHauteur() + "</height>\n"
                            + // hauteur du média.
                            "\t\t\t\t\t\t\t\t\t\t<anamorphic>FALSE</anamorphic>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<pixelaspectratio>square</pixelaspectratio>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<fielddominance>" + ((MediaVideo) m).getTrame() + "</fielddominance>\n"
                            + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                            + "\t\t\t\t\t\t\t\t</video>\n";
                    //xml += "\t</media>\n"
                     //       + "</file>";
                } // Cas de Resolve:
                else {
                    xml += "<file id=\"file-" + m.getId() + "\">\n"
                            + "\t<duration>" + m.getDuree().toImage() + "</duration>\n"
                            + "\t<rate>\n"
                            + "\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + "\t\t<ntsc>false</ntsc>\n"
                            + "\t</rate>\n"
                            + "\t<name>" + new File(m.getNomFichier().replace("\\", "/")).getName() + "</name>\n"
                            + "\t<pathurl>" + new File(m.getLocalisation().replace("\\", "/")).getName() + "</pathurl>\n"
                            + "\t<timecode>\n"
                            + "\t\t<string>" + m.getStart() + "</string>\n"
                            + "\t\t<displayformat>NDF</displayformat>\n"
                            + "\t\t<rate>\n"
                            + "\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + "\t\t\t<ntsc>false</ntsc>\n"
                            + "\t\t</rate>\n"
                            + "\t</timecode>\n"
                            + "\t<media>\n"
                            + "\t\t<video>\n"
                            + "\t\t\t<duration>" + m.getDuree().toImage() + "</duration>\n"
                            + "\t\t\t<samplecharacteristics>\n"
                            + "\t\t\t\t<width>" + m.getLargeur() + "</width>\n"
                            + "\t\t\t\t<height>" + m.getHauteur() + "</height>\n"
                            + "\t\t\t</samplecharacteristics>\n"
                            + "\t\t</video>\n";

                    /* S'il y a des pistes audios.
                    if()
                    xml += "";*/
                }

                if (((MediaVideo) m).getCanaux() > 0) {
                    xml += canauxClip(((MediaVideo) m).getCanaux(), 48000, 16);
                }

                xml += "\t\t\t\t\t\t\t</media>\n"
                        + "\t\t\t\t\t\t</file>\n";
            } else {
                xml += "\t\t\t\t\t\t<file id=\"file-" + m.getId() + "\"/>\n";
            }
        } // Cas où c'est un élément généré (mire, noir, calque d'effet, ...):
        else {
            xml += "\t\t\t\t\t\t<file id=\"genere-" + m.getId() + "\">\n"
                    + "\t\t\t\t\t\t\t<name>Vidéo noire</name>\n"
                    + "\t\t\t\t\t\t\t<mediaSource>Slug</mediaSource>\n"
                    + "\t\t\t\t\t\t\t<rate>\n"
                    + "\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                    + "\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                    + "\t\t\t\t\t\t\t</rate>\n"
                    + "\t\t\t\t\t\t\t<timecode>\n"
                    + "\t\t\t\t\t\t\t\t<rate>\n"
                    + "\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                    + "\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                    + "\t\t\t\t\t\t\t\t</rate>\n"
                    + "\t\t\t\t\t\t\t\t<string>00;00;00;00</string>\n"
                    + "\t\t\t\t\t\t\t\t<frame>0</frame>\n"
                    + "\t\t\t\t\t\t\t\t<displayformat>DF</displayformat>\n"
                    + "\t\t\t\t\t\t\t\t<reel>\n"
                    + "\t\t\t\t\t\t\t\t\t<name></name>\n"
                    + "\t\t\t\t\t\t\t\t</reel>\n"
                    + "\t\t\t\t\t\t\t</timecode>\n"
                    + "\t\t\t\t\t\t\t<media>\n"
                    + "\t\t\t\t\t\t\t\t<video>\n"
                    + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<rate>\n"
                    + "\t\t\t\t\t\t\t\t\t\t\t<timebase>30</timebase>\n"
                    + "\t\t\t\t\t\t\t\t\t\t\t<ntsc>TRUE</ntsc>\n"
                    + "\t\t\t\t\t\t\t\t\t\t</rate>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<width>" + m.getLargeur() + "</width>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<height>" + m.getHauteur() + "</height>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<anamorphic>FALSE</anamorphic>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<pixelaspectratio>square</pixelaspectratio>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<fielddominance>none</fielddominance>\n"
                    + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t</video>\n"
                    + "\t\t\t\t\t\t\t</media>\n"
                    + "\t\t\t\t\t\t</file>\n";
        }
        m.utiliser();

        //System.out.println(start + " - Position Adobe: x=" + m.getPositionHorizontale() + "(" + m.getX() + "), y= " + m.getPositionVerticale() + " (" + m.getY() + ")");
        // Cas qui n'est pas un élément généré (mire, décompte, ...):
        if (!m.getTypeMedia().equals("genere")) {
            xml += "\t\t\t\t\t\t<link>\n"
                    + "\t\t\t\t\t\t\t<linkclipref>masterclip-" + m.getId() + "</linkclipref>\n"
                    + "\t\t\t\t\t\t\t<mediatype>video</mediatype>\n"
                    + "\t\t\t\t\t\t\t<trackindex>1</trackindex>\n"
                    + "\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                    + "\t\t\t\t\t\t</link>\n"
                    + // Cas quand il y un déplacement:
                    "\t\t\t\t\t\t<filter>\n"
                    + "\t\t\t\t\t\t\t<effect>\n"
                    + "\t\t\t\t\t\t\t\t<name>Basic Motion</name>\n"
                    + "\t\t\t\t\t\t\t\t<effectid>basic</effectid>\n"
                    + "\t\t\t\t\t\t\t\t<effectcategory>motion</effectcategory>\n"
                    + "\t\t\t\t\t\t\t\t<effecttype>motion</effecttype>\n"
                    + "\t\t\t\t\t\t\t\t<mediatype>video</mediatype>\n"
                    + "\t\t\t\t\t\t\t\t<pproBypass>false</pproBypass>\n"
                    + "\t\t\t\t\t\t\t\t<parameter authoringApp=\"PremierePro\">\n"
                    + "\t\t\t\t\t\t\t\t\t<parameterid>scale</parameterid>\n"
                    + "\t\t\t\t\t\t\t\t\t<name>Scale</name>\n"
                    + "\t\t\t\t\t\t\t\t\t<valuemin>0</valuemin>\n"
                    + "\t\t\t\t\t\t\t\t\t<valuemax>1000</valuemax>\n";

            xml += "\t\t\t\t\t\t\t\t\t<value>" + m.getEchelle() + "</value>\n"
                    + "\t\t\t\t\t\t\t\t</parameter>\n"
                    + "\t\t\t\t\t\t\t\t<parameter authoringApp=\"PremierePro\">\n"
                    + "\t\t\t\t\t\t\t\t\t<parameterid>rotation</parameterid>\n"
                    + "\t\t\t\t\t\t\t\t\t<name>Rotation</name>\n"
                    + "\t\t\t\t\t\t\t\t\t<valuemin>-8640</valuemin>\n"
                    + // Rotation?
                    "\t\t\t\t\t\t\t\t\t<valuemax>8640</valuemax>\n"
                    + // Rotation?
                    "\t\t\t\t\t\t\t\t\t<value>0</value>\n"
                    + "\t\t\t\t\t\t\t\t</parameter>\n"
                    + "\t\t\t\t\t\t\t\t<parameter authoringApp=\"PremierePro\">\n"
                    + "\t\t\t\t\t\t\t\t\t<parameterid>center</parameterid>\n"
                    + "\t\t\t\t\t\t\t\t\t<name>Center</name>\n"
                    + "\t\t\t\t\t\t\t\t\t<value>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<horiz>" + m.getPositionHorizontale(this.largeur) + "</horiz>\n"
                    + // Position en X: 0 = centre, max: +/-7.80488
                    "\t\t\t\t\t\t\t\t\t\t<vert>" + m.getPositionVerticale(this.hauteur) + "</vert>\n"
                    + // Position en Y: 0 = centre, max: +/-6.66667
                    "\t\t\t\t\t\t\t\t\t</value>\n"
                    + "\t\t\t\t\t\t\t\t</parameter>\n"
                    + "\t\t\t\t\t\t\t\t<parameter authoringApp=\"PremierePro\">\n"
                    + "\t\t\t\t\t\t\t\t\t<parameterid>centerOffset</parameterid>\n"
                    + "\t\t\t\t\t\t\t\t\t<name>Anchor Point</name>\n"
                    + "\t\t\t\t\t\t\t\t\t<value>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<horiz>0</horiz>\n"
                    + // Point d'ancrage?
                    "\t\t\t\t\t\t\t\t\t\t<vert>0</vert>\n"
                    + // Point d'ancrage?
                    "\t\t\t\t\t\t\t\t\t</value>\n"
                    + "\t\t\t\t\t\t\t\t</parameter>\n"
                    + "\t\t\t\t\t\t\t</effect>\n"
                    + "\t\t\t\t\t\t</filter>\n"
                    + canauxClip2(((MediaVideo) m).getCanaux());
        }

        xml += "\t\t\t\t\t\t<logginginfo>\n"
                + "\t\t\t\t\t\t\t<description></description>\n"
                + "\t\t\t\t\t\t\t<scene></scene>\n"
                + "\t\t\t\t\t\t\t<shottake></shottake>\n"
                + "\t\t\t\t\t\t\t<lognote></lognote>\n"
                + "\t\t\t\t\t\t</logginginfo>\n"
                + "\t\t\t\t\t\t<labels>\n"
                + "\t\t\t\t\t\t\t<label2>" + m.getCouleur() + "</label2>\n"
                + "\t\t\t\t\t\t</labels>\n"
                + "\t\t\t\t\t</clipitem>\n";
        return xml;
    }

    /**
     * TODO
     *
     * @param nb ...
     * @param echantillion ...
     * @param bite ...
     * @return ...
     */
    private String outputGroupe(int nb, int echantillion, int bite) {

        String xml = "";

        for (int i = 1; i <= nb; i++) {
            xml += "\t\t\t\t\t<group>\n"
                    + "\t\t\t\t\t\t<index>" + i + "</index>\n"
                    + "\t\t\t\t\t\t<numchannels>1</numchannels>\n"
                    + "\t\t\t\t\t\t<downmix>0</downmix>\n"
                    + "\t\t\t\t\t\t<channel>\n"
                    + "\t\t\t\t\t\t\t<index>" + i + "</index>\n"
                    + "\t\t\t\t\t\t</channel>\n"
                    + "\t\t\t\t\t</group>\n";
        }

        return xml;
    }

    /**
     * TODO
     *
     * @param nb ...
     * @return ...
     */
    private String canauxClip2(int nb) {
        String xml = "";

        for (int i = 1; i <= nb; i++) {
            xml += "\t\t\t\t\t\t<link>\n"
                    + "\t\t\t\t\t\t\t<linkclipref>clipitem-" + (1 + i) + "</linkclipref>\n"
                    + "\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                    + "\t\t\t\t\t\t\t<trackindex>" + i + "</trackindex>\n"
                    + "\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                    + "\t\t\t\t\t\t</link>\n";
        }

        return xml;
    }

    /**
     * TODO
     *
     * @param nb TODO
     * @param echantillon TODO
     * @param bit TODO
     * @return TODO
     */
    private String canauxClip(int nb, int echantillon, int bit) {
        String xml;

        if (nb == 2) {
            xml = "\t\t\t\t\t\t\t\t<audio>\n"
                    + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<depth>" + bit + "</depth>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<samplerate>" + echantillon + "</samplerate>\n"
                    + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t<channelcount>1</channelcount>\n"
                    + "\t\t\t\t\t\t\t\t\t<layout>stereo</layout>\n"
                    + "\t\t\t\t\t\t\t\t\t<audiochannel>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<sourcechannel>1</sourcechannel>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<channellabel>left</channellabel>\n"
                    + "\t\t\t\t\t\t\t\t\t</audiochannel>\n"
                    + "\t\t\t\t\t\t\t\t</audio>\n"
                    + "\t\t\t\t\t\t\t\t<audio>\n"
                    + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<depth>" + bit + "</depth>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<samplerate>" + echantillon + "</samplerate>\n"
                    + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t<channelcount>1</channelcount>\n"
                    + "\t\t\t\t\t\t\t\t\t<layout>stereo</layout>\n"
                    + "\t\t\t\t\t\t\t\t\t<audiochannel>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<sourcechannel>2</sourcechannel>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<channellabel>right</channellabel>\n"
                    + "\t\t\t\t\t\t\t\t\t</audiochannel>\n"
                    + "\t\t\t\t\t\t\t\t</audio>\n";
        } else {
            xml = "\t\t\t\t\t\t\t\t<audio>\n"
                    + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<depth>" + bit + "</depth>\n"
                    + "\t\t\t\t\t\t\t\t\t\t<samplerate>" + echantillon + "</samplerate>\n"
                    + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                    + "\t\t\t\t\t\t\t\t\t<channelcount>" + nb + "</channelcount>\n"
                    + "\t\t\t\t\t\t\t\t</audio>\n";
        }
        return xml;
    }

    /**
     * @param m TODO
     * @return TODO
     */
    private String addMarqueurTimeline(Marqueur m) {
        String xml = "\t\t\t<marker>\n";
        xml += "\t\t\t\t<comment>" + m.getNote() + "</comment>\n";
        xml += "\t\t\t\t<name>" + m.getNom() + "</name>\n";
        xml += "\t\t\t\t<in>" + (m.getIn().toImage() - this.startTc.toImage()) + "</in>\n";
        xml += "\t\t\t\t<out>" + ((m.getOut() == null || m.getIn().toString().equals(m.getOut().toString())) ? "-1" : (m.getOut().toImage() - this.startTc.toImage() + 1)) + "</out>\n";

        if (!m.getCouleur().equals("")) {
            xml += "\t\t\t\t<pproColor>" + m.getCouleur() + "</pproColor>\n";
        }

        xml += "\t\t\t</marker>\n";
        return xml;
    }

    /**
     * Ajoute un clip audio dans la timeline.
     *
     * @return TOOD
     */
    private String addItemClipAudio(MediaVideo m, int trackindex) {
        this.clipitem++;
        String xml = "\t\t\t\t\t<clipitem id=\"clipitem-" + clipitem + "\" premiereChannelType=\"mono\">\n"
                + "\t\t\t\t\t\t<masterclipid>masterclip-" + m.getId() + "</masterclipid>\n"
                + "\t\t\t\t\t\t<name>" + new File(m.getNomFichier().replace("\\", "/")).getName() + "</name>\n"
                + "\t\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t\t<duration>" + m.getDuree().toImage() + "</duration>\n"
                + "\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                + "\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                + "\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t<start>" + m.getIn().toImage() + "</start>\n"
                + "\t\t\t\t\t\t<end>" + (m.getIn().toImage() + m.getDuree().toImage()) + "</end>\n"
                + "\t\t\t\t\t\t<in>" + m.getIn().toImage() + "</in>\n"
                + "\t\t\t\t\t\t<out>" + m.getOut().toImage() + "</out>\n"
                + "\t\t\t\t\t\t<pproTicksIn>0</pproTicksIn>\n"
                + "\t\t\t\t\t\t<pproTicksOut>" + (m.getOut().toImage() * 254016000000L / this.framerate) + "</pproTicksOut>\n"
                + "\t\t\t\t\t\t<file id=\"file-1\"/>\n"
                + "\t\t\t\t\t\t<sourcetrack>\n"
                + "\t\t\t\t\t\t\t<mediatype>audio</mediatype>\n"
                + "\t\t\t\t\t\t\t<trackindex>" + trackindex + "</trackindex>\n"
                + "\t\t\t\t\t\t</sourcetrack>\n"
                + "\t\t\t\t\t\t<link>\n"
                + "\t\t\t\t\t\t\t<linkclipref>clipitem-1</linkclipref>\n"
                + "\t\t\t\t\t\t\t<mediatype>video</mediatype>\n"
                + "\t\t\t\t\t\t\t<trackindex>1</trackindex>\n"
                + "\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                + "\t\t\t\t\t\t</link>\n"
                + // "\t\t\t\t\t\t<link>\n" +
                canauxClip2(((MediaVideo) m).getCanaux())
                + "\t\t\t\t\t\t<logginginfo>\n"
                + "\t\t\t\t\t\t\t<description></description>\n"
                + "\t\t\t\t\t\t\t<scene></scene>\n"
                + "\t\t\t\t\t\t\t<shottake></shottake>\n"
                + "\t\t\t\t\t\t\t<lognote></lognote>\n"
                + "\t\t\t\t\t\t</logginginfo>\n"
                + "\t\t\t\t\t\t<labels>\n"
                + "\t\t\t\t\t\t\t<label2>Iris</label2>\n"
                + "\t\t\t\t\t\t</labels>\n"
                + "\t\t\t\t\t</clipitem>\n";
        return xml;
    }

    /**
     * ...
     *
     * @return ...
     */
    public Timecode getStartTc() {
        return this.startTc;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getFramerate() {
        return this.framerate;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getHauteur() {
        return this.hauteur;
    }

}
