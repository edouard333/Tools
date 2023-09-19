package com.phenix.tools.XMLNLE;

import com.phenix.tools.Timecode;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Timeline dans un projet d'un NLE.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class Timeline {

    /**
     * Le nom de la timeline.
     */
    private String nom;

    /**
     * Le timecode de début de la timeline.
     */
    private Timecode start_tc;

    /**
     * Framerate de la timeline.
     */
    private int framerate;

    /**
     * Hauteur de la timeline en pixel.
     */
    private int hauteur;

    /**
     * Largeur de la timeline en pixel.
     */
    private int largeur;

    /**
     * PAR (Pixel Aspect Ratio) de la timeline.
     */
    private double par;

    /**
     *
     */
    private static int clipitem = 0;

    /**
     * ID de la timeline instancé.
     */
    private int numero_timeline;

    /**
     * Cette variable sert à savoir combien de timeline ont été faite.
     */
    public static int nombre_timeline = 0;

    /**
     * Liste des UUID pour rendre unique une timeline.<br>
     * Cela permet de généré dans un projet 14 timelines.
     */
    private final String[] uuid = {
        "4381a977-9cb2-42a7-bfd6-b9ae5e0ede20",
        "57ff1067-1a42-48f7-8a53-be80d3f14505",
        "7a24a4a6-d76c-4ad2-b71f-eade1e61a92c",
        "78ef0806-7f87-4d70-ad55-ba7ad7e2132e",
        "5d3059ef-dbd3-4d28-8d1f-d49a351fc597",
        "3f523f59-06b7-4df8-9d75-4b3d2e930afb",
        "fc6ebd17-1adc-4544-bd75-e1d7b94b557c",
        "69fa176c-00db-4279-88f3-b8e8a0ffeee6",
        "ee3f4248-fd0b-489f-866d-e0d5b3f765f1",
        "6684a79f-3c34-4633-91d8-73f1071cbb7e",
        "a8f673fa-6c72-4c72-8498-bf85bd1bea4e",
        "165c6655-f6b8-4573-8f51-e8f5ba3a14f8",
        "0e2897bc-1636-4432-bcf2-07d28e53dc39",
        "ca9e5f10-a53c-468e-8182-2884b3312c83"
    };

    /**
     * Nombre de canaux audio.
     */
    private int nombre_canaux;

    /**
     * Liste des pistes dans la timeline.
     */
    private ArrayList<Integer> liste_piste = new ArrayList<Integer>();

    /**
     * Liste des médias dans la timeline.
     */
    private ArrayList<Media> liste_media = new ArrayList<Media>();

    /**
     * TC start du média dans la timeline.
     */
    private ArrayList<Timecode> liste_tc_start = new ArrayList<Timecode>();

    /**
     * TC end du média dans la timeline.
     */
    private ArrayList<Timecode> liste_tc_end = new ArrayList<Timecode>();

    /**
     * Si le média est activé dans la timeline.
     */
    private ArrayList<Boolean> liste_active = new ArrayList<Boolean>();

    /**
     * Liste des marqueurs dans la timeline
     */
    private ArrayList<Marqueur> liste_marqueur = new ArrayList<Marqueur>();

    /**
     *
     */
    private HashMap<Integer, Integer> fin_piste = new HashMap<Integer, Integer>();

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
    private boolean verrouiller_piste_video;

    /**
     * Quand on veut verrouiller toutes les pistes audio.
     */
    private boolean verrouiller_piste_audio;

    /**
     * Définit la timeline est pour quel logiciel.
     */
    private byte logiciel_destination;

    /**
     * Créé une timeline avec toutes les données par défaut.
     */
    public Timeline() {
        this("Sans titre", 25, new Timecode("00:00:00:00"));
    }

    /**
     * Créé une timeline avec un nom.
     *
     * @param nom Nom de la timeline.
     */
    public Timeline(String nom) {
        this(nom, 25, new Timecode("00:00:00:00"));
    }

    /**
     * Construit une ligne vide.
     *
     * @param nom Nom de la timeline.
     * @param framerate Framerate de la timeline.
     * @param start_tc Timecode début de la timeline.
     */
    public Timeline(String nom, int framerate, Timecode start_tc) {
        this.nom = nom;
        this.framerate = framerate;
        this.start_tc = start_tc;
        this.par = 1;

        this.nombre_canaux = 2;

        this.verrouiller_piste_video = false;
        this.verrouiller_piste_audio = false;

        // Par défaut c'est pour Adobe Premiere.
        this.logiciel_destination = XMLFCP7.PREMIERE;

        this.numero_timeline = nombre_timeline;
        nombre_timeline++;
    }

    /**
     * Ajoute un marqueur sur la timeline.
     *
     * @param marqueur Le marqueur.
     */
    public void addMarqueur(Marqueur marqueur) {
        this.liste_marqueur.add(marqueur);
    }

    /**
     * Retourne le code XML d'un marqueur dans une timeline pour un projet Adobe
     * Premiere.
     *
     * @param marqueur Le marqueur.
     * @return Code XML.
     */
    private String addMarqueurTimeline(Marqueur marqueur) {
        String xml = "\t\t\t<marker>\n";
        xml += "\t\t\t\t<name>" + marqueur.getNom() + "</name>\n";
        xml += "\t\t\t\t<comment>" + marqueur.getNote() + "</comment>\n";
        xml += "\t\t\t\t<in>" + (marqueur.getIn().toImage() - this.start_tc.toImage()) + "</in>\n";
        xml += "\t\t\t\t<out>" + ((marqueur.getOut() == null || marqueur.getIn().toString().equals(marqueur.getOut().toString())) ? "-1" : (marqueur.getOut().toImage() - this.start_tc.toImage() + 1)) + "</out>\n";

        if (marqueur.getCouleur() != null) {

            if (this.logiciel_destination == XMLFCP7.PREMIERE) {
                // Le vert étant le par défaut, on l'affiche pas.
                if (marqueur.getCouleur() != CouleurMarqueur.VERT) {
                    xml += "\t\t\t\t<pproColor>" + marqueur.getCouleur().getCouleurPremiere() + "</pproColor>\n";
                }
            } else {
                xml += "\t\t\t\t<color>\n";
                xml += "\t\t\t\t\t<alpha>" + marqueur.getCouleur().getCanalApha() + "</alpha>\n";
                xml += "\t\t\t\t\t<red>" + marqueur.getCouleur().getCanalRouge() + "</red>\n";
                xml += "\t\t\t\t\t<green>" + marqueur.getCouleur().getCanalVert() + "</green>\n";
                xml += "\t\t\t\t\t<blue>" + marqueur.getCouleur().getCanalBleu() + "</blue>\n";
                xml += "\t\t\t\t</color>\n";
            }
        }

        xml += "\t\t\t</marker>\n";
        return xml;
    }

    /**
     * Ajoute un média à la timeline.
     *
     * @param media Le média.
     */
    public void addMedia(Media media) {
        this.addMedia(1, media, this.start_tc, new Timecode(this.start_tc.toImage() + media.getDuree().toImage(), media.getFramerate()), true);
    }

    /**
     * Ajoute un média à la timeline en spécifiant une piste.
     *
     * @param piste Le numéro de piste.
     * @param media Le média.
     */
    public void addMedia(int piste, Media media) {
        this.addMedia(piste, media, media.getIn(), media.getOut(), true);
    }

    /**
     * Ajoute un média à la timeline en précisant la piste, point in et point
     * out.
     *
     * @param piste Le numéro de piste.
     * @param media Le média.
     * @param in Point in du média.
     * @param out Point out du média.
     */
    public void addMedia(int piste, Media media, Timecode in, Timecode out) {
        // Média activé pardéfaut.
        this.addMedia(piste, media, in, out, true);
    }

    /**
     * Ajoute un média à la timeline en précisant la piste, point in et point
     * out.
     *
     * @param piste Le numéro de piste.
     * @param media Le média.
     * @param in Point in du média.
     * @param out Point out du média.
     * @param active Si le média est activé dans la timeline.
     */
    public void addMedia(int piste, Media media, Timecode in, Timecode out, boolean active) {
        // Pas de superposition (seulement si trié):
        while (this.fin_piste.containsKey(piste) && (in.toImage() <= this.fin_piste.get(piste))) {
            piste++;
        }

        this.liste_piste.add(piste);
        this.liste_active.add(active);

        // S'il y a une nouvelle piste, on l'ajoute:
        if (!this.fin_piste.containsKey(piste)) {
            this.fin_piste.put(piste, out.toImage());
        } // Sinon, on met à jour le Tc end:
        else {
            // Et si le TC out et plus grand que le tc de fin de la piste:
            if (this.fin_piste.get(piste) < out.toImage()) {
                this.fin_piste.replace(piste, out.toImage());
            }
        }

        this.liste_media.add(media);

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

        this.liste_tc_start.add(in);
        this.liste_tc_end.add(out);

        if (media instanceof MediaVideo) {
            conformiteMedia((MediaVideo) media);
        }
    }

    /**
     * Ajoute un clip vidéo à la timeline.
     *
     * @param m Le média vidéo à ajouter.
     * @param start
     * @param active Si le média est activé dans la timeline.
     *
     * @return Code XML du projet Adobe Premiere.
     */
    private String addItemClipVideo(MediaVideo m, Timecode start, boolean active) {
        clipitem++;

        // On définit à quel logiciel est destiné ce média vidéo.
        m.setLogicielDestination(logiciel_destination);

        String nom_fichier = new File(m.getNomFichier().replace("\\", "/")).getName();

        String xml = "\t\t\t\t\t<clipitem id=\"clipitem-" + clipitem + "\">\n"
                + "\t\t\t\t\t\t<masterclipid>masterclip-" + m.getId() + "</masterclipid>\n"
                + "\t\t\t\t\t\t<name>" + (m.getNom() != null ? m.getNom() : nom_fichier) + "</name>\n"
                + "\t\t\t\t\t\t<enabled>" + ((active) ? "TRUE" : "FALSE") + "</enabled>\n"
                + "\t\t\t\t\t\t<duration>" + m.getDuree().toImage() + "</duration>\n"
                + "\t\t\t\t\t\t<rate>\n"
                + "\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                + "\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                + "\t\t\t\t\t\t</rate>\n"
                + "\t\t\t\t\t\t<start>" + (start.toImage() - this.start_tc.toImage()) + "</start>\n"
                + "\t\t\t\t\t\t<end>" + ((start.toImage() - this.start_tc.toImage() + m.getDuree().toImage()) - ((m.getDuree().toImage() > 1) ? 1 : 0)) + "</end>\n"
                + // "-1" car sinon cela ajoute une frame.
                "\t\t\t\t\t\t<in>" + m.getIn().toImage() + "</in>\n"
                + "\t\t\t\t\t\t<out>" + (m.getOut().toImage()) + "</out>\n"
                + "\t\t\t\t\t\t<pproTicksIn>0</pproTicksIn>\n"
                + // Je sais plus.
                "\t\t\t\t\t\t<pproTicksOut>" + (m.getOut().toImage() * 254016000000L / this.framerate) + "</pproTicksOut>\n"
                + // Je sais plus.
                "\t\t\t\t\t\t<alphatype>none</alphatype>\n"
                + "\t\t\t\t\t\t<pixelaspectratio>square</pixelaspectratio>\n"
                + "\t\t\t\t\t\t<anamorphic>FALSE</anamorphic>\n";

        // Si le fichier est généré.
        if (!m.getTypeMedia().equals("genere")) {
            if (!m.dejaUtilise()) {

                // Si le logiciel est Adobe Premiere :
                if (this.logiciel_destination == XMLFCP7.PREMIERE) {
                    xml += "\t\t\t\t\t\t<file id=\"file-" + m.getId() + "\">\n"
                            + "\t\t\t\t\t\t\t<name>" + nom_fichier + "</name>\n"
                            + "\t\t\t\t\t\t\t<pathurl>" + m.getLocalisation() + "</pathurl>\n"
                            + // Où se trouve le fichier.
                            "\t\t\t\t\t\t\t<rate>\n"
                            + "\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + // Framerate du média.
                            "\t\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                            + "\t\t\t\t\t\t\t</rate>\n"
                            + "\t\t\t\t\t\t\t<duration>" + m.getDureeFichier().toImage() + "</duration>\n"
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
                            + "\t\t\t\t\t\t\t\t\t<name>" + m.getNomBobine() + "</name>\n"
                            + "\t\t\t\t\t\t\t\t</reel>\n"
                            + "\t\t\t\t\t\t\t</timecode>\n"
                            + "\t\t\t\t\t\t\t<media>\n"
                            + "\t\t\t\t\t\t\t\t<video>\n"
                            + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<rate>\n"
                            // Frame rate du média.
                            + "\t\t\t\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + "\t\t\t\t\t\t\t\t\t\t\t<ntsc>FALSE</ntsc>\n"
                            + "\t\t\t\t\t\t\t\t\t\t</rate>\n"
                            // largeur du média.
                            + "\t\t\t\t\t\t\t\t\t\t<width>" + m.getLargeur() + "</width>\n"
                            // hauteur du média.
                            + "\t\t\t\t\t\t\t\t\t\t<height>" + m.getHauteur() + "</height>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<anamorphic>FALSE</anamorphic>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<pixelaspectratio>square</pixelaspectratio>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<fielddominance>" + ((MediaVideo) m).getTrame() + "</fielddominance>\n"
                            + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                            + "\t\t\t\t\t\t\t\t</video>\n";
                    //xml += "\t</media>\n"
                    //       + "</file>";
                } // Cas de Resolve:
                else {
                    xml += "\t\t\t\t\t\t<file id=\"file-" + m.getId() + "\">\n"
                            + "\t\t\t\t\t\t\t<duration>" + m.getDureeFichier().toImage() + "</duration>\n"
                            + "\t\t\t\t\t\t\t<rate>\n"
                            + "\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + "\t\t\t\t\t\t\t\t<ntsc>false</ntsc>\n"
                            + "\t\t\t\t\t\t\t</rate>\n"
                            + "\t\t\t\t\t\t\t<name>" + nom_fichier + "</name>\n"
                            + "\t\t\t\t\t\t\t<pathurl>" + /*new File(m.getLocalisation().replace("\\", "/")).getName()*/ m.getLocalisation() + "</pathurl>\n"
                            + "\t\t\t\t\t\t\t<timecode>\n"
                            + "\t\t\t\t\t\t\t\t<string>" + m.getStart() + "</string>\n"
                            + "\t\t\t\t\t\t\t\t<displayformat>NDF</displayformat>\n"
                            + "\t\t\t\t\t\t\t\t<rate>\n"
                            + "\t\t\t\t\t\t\t\t\t<timebase>" + m.getFramerate() + "</timebase>\n"
                            + "\t\t\t\t\t\t\t\t\t<ntsc>false</ntsc>\n"
                            + "\t\t\t\t\t\t\t\t</rate>\n";

                    if (m.getNomBobine() != null) {
                        xml += "\t\t\t\t\t\t\t\t<reel>\n"
                                + "\t\t\t\t\t\t\t\t\t<name>" + m.getNomBobine() + "</name>\n"
                                + "\t\t\t\t\t\t\t\t</reel>\n";
                    }

                    xml += "\t\t\t\t\t\t\t</timecode>\n"
                            + "\t\t\t\t\t\t\t<media>\n"
                            + "\t\t\t\t\t\t\t\t<video>\n"
                            + "\t\t\t\t\t\t\t\t\t<duration>" + m.getDureeFichier().toImage() + "</duration>\n"
                            + "\t\t\t\t\t\t\t\t\t<samplecharacteristics>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<width>" + m.getLargeur() + "</width>\n"
                            + "\t\t\t\t\t\t\t\t\t\t<height>" + m.getHauteur() + "</height>\n"
                            + "\t\t\t\t\t\t\t\t\t</samplecharacteristics>\n"
                            + "\t\t\t\t\t\t\t\t</video>\n";

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
        } // Cas où c'est un élément généré (mire, noir, calque d'effet, etc):
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

        // Cas qui n'est pas un élément généré (mire, décompte, etc):
        if (!m.getTypeMedia().equals("genere")) {
            xml += "\t\t\t\t\t\t<link>\n"
                    + "\t\t\t\t\t\t\t<linkclipref>masterclip-" + m.getId() + "</linkclipref>\n"
                    + "\t\t\t\t\t\t\t<mediatype>video</mediatype>\n"
                    + "\t\t\t\t\t\t\t<trackindex>1</trackindex>\n"
                    + "\t\t\t\t\t\t\t<clipindex>1</clipindex>\n"
                    + "\t\t\t\t\t\t</link>\n";

            // DaVinci Resolve indique le mode composition.
            if (this.logiciel_destination == XMLFCP7.RESOLVE) {
                xml += "\t\t\t\t\t\t<compositemode>normal</compositemode>\n";
            }

            // Cas quand il y un déplacement:
            xml += "\t\t\t\t\t\t<filter>\n";

            if (this.logiciel_destination == XMLFCP7.RESOLVE) {
                xml += "\t\t\t\t\t\t\t<enabled>TRUE</enabled>\n"
                        + "\t\t\t\t\t\t\t<start>" + m.getStart().toImage() + "</start>\n"
                        + "\t\t\t\t\t\t\t<end>" + m.getStart().toImage() + m.getDuree().toImage() + "</end>\n";
            }

            xml += "\t\t\t\t\t\t\t<effect>\n"
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
                    + "\t\t\t\t\t\t\t\t\t<valuemax>1000</valuemax>\n"
                    + "\t\t\t\t\t\t\t\t\t<value>" + m.getEchelle() + "</value>\n"
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
                    // Position en X: 0 = centre, max: +/-7.80488
                    + "\t\t\t\t\t\t\t\t\t\t<horiz>" + m.getPositionHorizontale(this.largeur, this.hauteur, this.par) + "</horiz>\n"
                    // Position en Y: 0 = centre, max: +/-6.66667
                    + "\t\t\t\t\t\t\t\t\t\t<vert>" + m.getPositionVerticale(this.largeur, this.hauteur, this.par) + "</vert>\n"
                    + "\t\t\t\t\t\t\t\t\t</value>\n"
                    + "\t\t\t\t\t\t\t\t</parameter>\n"
                    + "\t\t\t\t\t\t\t\t<parameter authoringApp=\"PremierePro\">\n"
                    + "\t\t\t\t\t\t\t\t\t<parameterid>centerOffset</parameterid>\n"
                    + "\t\t\t\t\t\t\t\t\t<name>Anchor Point</name>\n"
                    + "\t\t\t\t\t\t\t\t\t<value>\n"
                    // Point d'ancrage?
                    + "\t\t\t\t\t\t\t\t\t\t<horiz>0</horiz>\n"
                    // Point d'ancrage?
                    + "\t\t\t\t\t\t\t\t\t\t<vert>0</vert>\n"
                    + "\t\t\t\t\t\t\t\t\t</value>\n"
                    + "\t\t\t\t\t\t\t\t</parameter>\n"
                    + "\t\t\t\t\t\t\t</effect>\n"
                    + "\t\t\t\t\t\t</filter>\n"
                    + canauxClip2(((MediaVideo) m).getCanaux());
        }

        // Il se peut que cela soit une image et non une vidéo qu'on doit freezer.
        if (this.logiciel_destination == XMLFCP7.RESOLVE && m.isFreeze()) {
            xml += "\t\t\t\t\t\t<filter>\n";
            xml += "\t\t\t\t\t\t\t<enabled>TRUE</enabled>\n";
            xml += "\t\t\t\t\t\t\t<start>-1</start>\n";
            xml += "\t\t\t\t\t\t\t<end>-1</end>\n";
            xml += "\t\t\t\t\t\t\t<effect>\n";
            xml += "\t\t\t\t\t\t\t<name>Time Remap</name>\n";
            xml += "\t\t\t\t\t\t\t<effectid>timeremap</effectid>\n";
            xml += "\t\t\t\t\t\t\t<effecttype>motion</effecttype>\n";
            xml += "\t\t\t\t\t\t\t<mediatype>video</mediatype>\n";
            xml += "\t\t\t\t\t\t\t<effectcategory>motion</effectcategory>\n";
            xml += "\t\t\t\t\t\t\t<parameter>\n";
            xml += "\t\t\t\t\t\t\t<name>speed</name>\n";
            xml += "\t\t\t\t\t\t\t<parameterid>speed</parameterid>\n";
            xml += "\t\t\t\t\t\t\t<value>0</value>\n";
            xml += "\t\t\t\t\t\t\t<valuemin>-10000</valuemin>\n";
            xml += "\t\t\t\t\t\t\t<valuemax>10000</valuemax>\n";
            xml += "\t\t\t\t\t\t\t</parameter>\n";
            xml += "\t\t\t\t\t\t\t<parameter>\n";
            xml += "\t\t\t\t\t\t\t<name>reverse</name>\n";
            xml += "\t\t\t\t\t\t\t<parameterid>reverse</parameterid>\n";
            xml += "\t\t\t\t\t\t\t<value>FALSE</value>\n";
            xml += "\t\t\t\t\t\t\t</parameter>\n";
            xml += "\t\t\t\t\t\t\t<parameter>\n";
            xml += "\t\t\t\t\t\t\t<name>frameblending</name>\n";
            xml += "\t\t\t\t\t\t\t<parameterid>frameblending</parameterid>\n";
            xml += "\t\t\t\t\t\t\t<value>FALSE</value>\n";
            xml += "\t\t\t\t\t\t\t</parameter>\n";
            xml += "\t\t\t\t\t\t\t<parameter>\n";
            xml += "\t\t\t\t\t\t\t<name>variablespeed</name>\n";
            xml += "\t\t\t\t\t\t\t<parameterid>variablespeed</parameterid>\n";
            xml += "\t\t\t\t\t\t\t<value>0</value>\n";
            xml += "\t\t\t\t\t\t\t<valuemin>0</valuemin>\n";
            xml += "\t\t\t\t\t\t\t<valuemax>1</valuemax>\n";
            xml += "\t\t\t\t\t\t\t</parameter>\n";
            xml += "\t\t\t\t\t\t\t<parameter>\n";
            xml += "\t\t\t\t\t\t\t<name>graphdict</name>\n";
            xml += "\t\t\t\t\t\t\t<parameterid>graphdict</parameterid>\n";
            xml += "\t\t\t\t\t\t\t<keyframe>\n";
            xml += "\t\t\t\t\t\t\t<when>0</when>\n";
            xml += "\t\t\t\t\t\t\t<value>0</value>\n";
            xml += "\t\t\t\t\t\t\t<speedvirtualkf>TRUE</speedvirtualkf>\n";
            xml += "\t\t\t\t\t\t\t<speedkfstart>TRUE</speedkfstart>\n";
            xml += "\t\t\t\t\t\t\t</keyframe>\n";
            xml += "\t\t\t\t\t\t\t<keyframe>\n";
            xml += "\t\t\t\t\t\t\t<when>86400</when>\n";
            xml += "\t\t\t\t\t\t\t<value>0</value>\n";
            xml += "\t\t\t\t\t\t\t<speedvirtualkf>TRUE</speedvirtualkf>\n";
            xml += "\t\t\t\t\t\t\t<speedkfin>TRUE</speedkfin>\n";
            xml += "\t\t\t\t\t\t\t</keyframe>\n";
            xml += "\t\t\t\t\t\t\t<keyframe>\n";
            xml += "\t\t\t\t\t\t\t<when>86520</when>\n";
            xml += "\t\t\t\t\t\t\t<value>0</value>\n";
            xml += "\t\t\t\t\t\t\t<speedvirtualkf>TRUE</speedvirtualkf>\n";
            xml += "\t\t\t\t\t\t\t<speedkfout>TRUE</speedkfout>\n";
            xml += "\t\t\t\t\t\t\t</keyframe>\n";
            xml += "\t\t\t\t\t\t\t<keyframe>\n";
            xml += "\t\t\t\t\t\t\t<when>1440001</when>\n";
            xml += "\t\t\t\t\t\t\t<value>1</value>\n";
            xml += "\t\t\t\t\t\t\t<speedvirtualkf>TRUE</speedvirtualkf>\n";
            xml += "\t\t\t\t\t\t\t<speedkfend>TRUE</speedkfend>\n";
            xml += "\t\t\t\t\t\t\t</keyframe>\n";
            xml += "\t\t\t\t\t\t\t<valuemin>0</valuemin>\n";
            xml += "\t\t\t\t\t\t\t<valuemax>0</valuemax>\n";
            xml += "\t\t\t\t\t\t\t<interpolation>\n";
            xml += "\t\t\t\t\t\t\t<name>FCPCurve</name>\n";
            xml += "\t\t\t\t\t\t\t</interpolation>\n";
            xml += "\t\t\t\t\t\t\t</parameter>\n";
            xml += "\t\t\t\t\t\t\t</effect>\n";
            xml += "\t\t\t\t\t\t</filter>\n";
        }

        xml += "\t\t\t\t\t\t<logginginfo>\n"
                + "\t\t\t\t\t\t\t<description></description>\n"
                + "\t\t\t\t\t\t\t<scene></scene>\n"
                + "\t\t\t\t\t\t\t<shottake></shottake>\n"
                + "\t\t\t\t\t\t\t<lognote></lognote>\n"
                + "\t\t\t\t\t\t</logginginfo>\n"
                + "\t\t\t\t\t\t<labels>\n"
                //+ "\t\t\t\t\t\t\t<label>Meilleure prise</label>\n"
                + "\t\t\t\t\t\t\t<label2>" + m.getCouleur().getCouleurAdobe() + "</label2>\n"
                + "\t\t\t\t\t\t</labels>\n"
                + "\t\t\t\t\t</clipitem>\n";
        return xml;
    }

    /**
     * Ajoute un clip audio dans la timeline.
     *
     * @return Le code XML a ajouter au fichier final.
     */
    private String addItemClipAudio(MediaVideo m, int trackindex) {
        clipitem++;
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
                + canauxClip2(((MediaVideo) m).getCanaux())
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
     *
     * @param nb
     * @return
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
     *
     * @param nb
     * @param echantillon
     * @param bit
     * @return
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
     * TODO : Vérifie que le média ajouté est conforme à la timeline.<br>
     *
     * @param media Média à comparer avec la timeline.
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
     * Retourne le framerate de la timeline.
     *
     * @return Framerate.
     */
    public int getFramerate() {
        return this.framerate;
    }

    /**
     * Retourne la hauteur en pixel de la timeline.
     *
     * @return Hauteur en pixel.
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Retourne la largeur en pixel de la timeline.
     *
     * @return Largeur en pixel.
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Retourne le PAR de la timeline.
     *
     * @return Le PAR.
     */
    public double getPAR() {
        return this.par;
    }

    /**
     * Retourne le timecode début de la timeline.
     *
     * @return Timecode début.
     */
    public Timecode getStartTc() {
        return this.start_tc;
    }

    /**
     * Code XML pour générer le projet Adobe Premiere.
     *
     * @return Code XML à ajouter dans le fichier projet Adobe Premiere.
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
        xml += "MZ.ZeroPoint=\"" + (this.start_tc.toImage() * 254016000000L / this.framerate) + "\" explodedTracks=\"true\">\n";
        xml += "\t\t<uuid>" + this.uuid[numero_timeline] + "</uuid>\n";
        xml += "\t\t<duration>" + this.liste_media.get(0).getDuree().toImage() + "</duration>\n";
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

        for (int i = 0; i < this.liste_piste.size(); i++) {
            if (!num_piste.contains(this.liste_piste.get(i))) {
                num_piste.add(this.liste_piste.get(i));

                // Piste max:
                if (this.liste_piste.get(i) > max) {
                    max = this.liste_piste.get(i);
                }
            }
        }

        // Track / piste 1:
        for (int i = 1; i <= max; i++) {
            xml += "\t\t\t\t<track TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\">\n";

            for (int j = 0; j < this.liste_piste.size(); j++) {
                // Piste actuelle:
                if (this.liste_piste.get(j) == i) {
                    if (this.liste_media.get(j) instanceof MediaVideo) {
                        xml += this.addItemClipVideo((MediaVideo) this.liste_media.get(j), this.liste_tc_start.get(j), this.liste_active.get(j));
                    }
                }
            }

            xml += "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                    // Si on verouille la piste vidéo.
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
                + this.outputGroupe(this.nombre_canaux)
                + "\t\t\t\t</outputs>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerCurrentValue=\"0\" PannerIsInverted=\"true\" PannerStartKeyframe=\"-91445760000000000,0.,0,0,0,0,0,0\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a une piste audio, on l'ajoute:
                ((((MediaVideo) this.liste_media.get(0)).getCanaux() >= 1) ? addItemClipAudio((MediaVideo) this.liste_media.get(0), 1) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(1) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>1</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerIsInverted=\"true\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a au moins 2 piste piste audio, on l'ajoute:
                ((((MediaVideo) this.liste_media.get(0)).getCanaux() >= 2) ? addItemClipAudio((MediaVideo) this.liste_media.get(0), 2) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(2) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>2</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerCurrentValue=\"0\" PannerIsInverted=\"true\" PannerStartKeyframe=\"-91445760000000000,0.,0,0,0,0,0,0\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a au moins 3 piste piste audio, on l'ajoute:
                ((((MediaVideo) this.liste_media.get(0)).getCanaux() >= 3) ? addItemClipAudio((MediaVideo) this.liste_media.get(0), 3) : "")
                + "\t\t\t\t\t<enabled>TRUE</enabled>\n"
                + "\t\t\t\t\t<locked>" + ((this.liste_piste_audio_verrouiller.contains(3) || this.verrouiller_piste_audio) ? "TRUE" : "FALSE") + "</locked>\n"
                + "\t\t\t\t\t<outputchannelindex>3</outputchannelindex>\n"
                + "\t\t\t\t</track>\n"
                + "\t\t\t\t<track monotrack=\"TRUE\" TL.SQTrackAudioKeyframeStyle=\"0\" TL.SQTrackShy=\"0\" TL.SQTrackExpandedHeight=\"25\" TL.SQTrackExpanded=\"0\" MZ.TrackTargeted=\"1\" PannerIsInverted=\"true\" PannerName=\"Pan\" currentExplodedTrackIndex=\"0\" totalExplodedTrackCount=\"1\" premiereTrackType=\"Mono\">\n"
                + // Si le fichier vidéo a au moins 4 piste piste audio, on l'ajoute:
                ((((MediaVideo) this.liste_media.get(0)).getCanaux() >= 4) ? addItemClipAudio((MediaVideo) this.liste_media.get(0), 4) : "")
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
        xml += "\t\t\t<string>" + this.start_tc + "</string>\n";
        xml += "\t\t\t<frame>" + this.start_tc.toImage() + "</frame>\n";
        xml += "\t\t\t<displayformat>NDF</displayformat>\n";
        xml += "\t\t</timecode>\n";

        // Les marques:
        for (int i = 0; i < this.liste_marqueur.size(); i++) {
            xml += this.addMarqueurTimeline(this.liste_marqueur.get(i));
        }

        xml += "\t\t<labels>\n";
        xml += "\t\t\t<label2>Forest</label2>\n";
        xml += "\t\t</labels>\n";
        xml += "\t</sequence>\n";

        return xml;
    }

    /**
     *
     * @param nb
     * @return
     */
    private String outputGroupe(int nb) {

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
     * Modifie le nombre de canaux audios.
     *
     * @param nombre_canaux Nombre de canaux audio.
     */
    public void setCanaux(int nombre_canaux) {
        this.nombre_canaux = nombre_canaux;
    }

    /**
     * Définit les dimensions de la timeline.
     *
     * @param largeur La largeur.
     * @param hauteur La hauteur.
     */
    public void setDimension(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
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
     * Modifie le PAR.
     *
     * @param par PAR.
     */
    public void setPAR(float par) {
        this.par = par;
    }

    /**
     * Modifie le timecode de début de la timeline.
     *
     * @param startTc Le timecode de début.
     */
    public void setStart(Timecode startTc) {
        this.start_tc = startTc;
        if (this.framerate != 0) {
            this.start_tc.setFramerate(this.framerate);
        } // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else {
            this.framerate = (int) this.start_tc.getFramerate();
        }
    }

    /**
     * Verrouille toutes les pistes Audio.
     */
    public void verrouillerPisteAudio() {
        this.verrouiller_piste_audio = true;
    }

    /**
     * Verrouille (ou déverrouille) une piste audio.
     *
     * @param numero_piste Numéro de piste audio.
     */
    public void verrouillerPisteAudio(int numero_piste) {
        verrouillerPisteAudio(numero_piste, true);
    }

    /**
     * Verrouille (ou déverrouille) une piste audio.
     *
     * @param numero_piste Numéro de piste audio.
     * @param verrouiller Vérouille la piste audio si <code>true</code>.
     */
    public void verrouillerPisteAudio(int numero_piste, boolean verrouiller) {
        if (verrouiller) {
            this.liste_piste_audio_verrouiller.add(numero_piste);
        } else {
            this.liste_piste_audio_verrouiller.remove(numero_piste);
        }
    }

    /**
     * Verrouille toutes les pistes vidéos.
     */
    public void verrouillerPisteVideo() {
        this.verrouiller_piste_video = true;
    }

    /**
     * Indique un numéro de piste vidéo qu'on veut vérouiller dans le projet du
     * NLE.
     *
     * @param numero_piste Numéro de piste vidéo.
     */
    public void verrouillerPisteVideo(int numero_piste) {
        verrouillerPisteVideo(numero_piste, true);
    }

    /**
     * Verrouille (ou déverrouille) une piste vidéo.
     *
     * @param numero_piste Numéro de piste vidéo.
     * @param verrouiller Vérouille la piste vidéo si <code>true</code>.
     */
    public void verrouillerPisteVideo(int numero_piste, boolean verrouiller) {
        if (verrouiller) {
            this.liste_piste_video_verrouiller.add(numero_piste);
        } else {
            this.liste_piste_video_verrouiller.remove(numero_piste);
        }
    }
}
