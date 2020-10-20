package com.phenix.tools.XMLNLE;

import com.phenix.tools.tools.Timecode;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class Marqueur {

    /**
     * TODO
     */
    private String nom;

    /**
     * TODO
     */
    private String note;

    /**
     * TODO
     */
    private Timecode in;

    /**
     * TODO
     */
    private Timecode out;

    /**
     * TODO
     */
    private String couleur;

    /**
     * TODO
     */
    private int framerate;

    /**
     * TODO
     */
    private Timecode duree;

    /**
     * TODO
     */
    public static final String ROUGE = "4281740498";

    /**
     * TODO
     */
    public Marqueur() {
        this.note = "";
        this.in = new Timecode("00:00:00:00");
        this.out = new Timecode();
        this.couleur = "";
        this.nom = "";
    }

    /**
     * TODO
     *
     * @param note TODO
     */
    public Marqueur(String note) {
        this.note = note;
        this.in = new Timecode("00:00:00:00");
        this.out = new Timecode();
        this.couleur = "";
        this.nom = "";
    }

    /**
     * TODO
     *
     * @param note      TODO
     * @param framerate TODO
     */
    public Marqueur(String note, int framerate) {
        this.note = note;
        this.in = new Timecode("00:00:00:00");
        this.out = new Timecode();
        this.couleur = "";
        this.nom = "";
        this.framerate = framerate;
    }

    /**
     * TODO
     *
     * @param note TODO
     * @param in   TODO
     */
    public Marqueur(String note, Timecode in) {
        this.note = note;
        this.in = in;
        this.out = new Timecode();
        this.couleur = "";
        this.nom = "";
    }

    /**
     * TODO
     *
     * @param note TODO
     * @param in   TODO
     * @param out  TODO
     */
    public Marqueur(String note, Timecode in, Timecode out) {
        this.note = note;
        this.in = in;
        this.out = out;
        this.couleur = "";
        this.nom = "";
    }

    /**
     * TODO
     *
     * @param note    TODO
     * @param in      TODO
     * @param out     TODO
     * @param couleur TODO
     */
    public Marqueur(String note, Timecode in, Timecode out, String couleur) {
        this.note = note;
        this.in = in;
        this.out = out;
        this.couleur = couleur;
        this.nom = "";
    }

    /**
     * TODO
     *
     * @param couleur TODO
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * TODO
     *
     * @param note TODO
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * TODO
     *
     * @param nom TODO
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * TODO
     *
     * @param out TODO
     */
    public void setOut(Timecode out) {
        this.out = out;

        if (this.framerate != 0) this.out.setFramerate(this.framerate);
            // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else
            this.framerate = (int)this.out.getFramerate();
    }

    /**
     * TODO
     *
     * @param in TODO
     */
    public void setIn(Timecode in) {
        this.in = in;
        if (this.framerate != 0) this.in.setFramerate(this.framerate);
            // Sinon, on affecte le framerate du timecode (s'il en a un) à média.
        else
            this.framerate = (int)this.in.getFramerate();
    }

    /**
     * TODO
     *
     * @param framerate TODO
     */
    public void setFramerate(int framerate) {
        this.framerate = framerate;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Timecode getIn() {
        return this.in;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Timecode getOut() {
        return this.out;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public String getNote() {
        return this.note;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public int getFramerate() {
        return this.framerate;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Timecode getDuree() {
        this.duree = new Timecode((out.toImage() - in.toImage() + 1), this.framerate);
        return this.duree;
    }

}