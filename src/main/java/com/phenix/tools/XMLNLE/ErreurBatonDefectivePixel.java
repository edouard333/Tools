package com.phenix.tools.XMLNLE;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class ErreurBatonDefectivePixel extends ErreurBaton {

    /**
     * Liste des positions (x, y) des pixels morts.
     */
    private ArrayList<Pixel> liste_pixel = new ArrayList<Pixel>();

    /**
     * ...
     *
     * @param description ...
     * @param duree ...
     * @param tcStart ...
     * @param tcEnd ...
     * @param item ...
     * @param idCodec ...
     */
    public ErreurBatonDefectivePixel(String description, int duree, String tcStart, String tcEnd, String item, int idCodec, ArrayList<Pixel> liste_pixel) {
        super(description, duree, tcStart, tcEnd, item, idCodec);
        this.liste_pixel = liste_pixel;
    }

    /**
     * Retourne le nombre de pixel défectueux dans l'image (erreur).
     *
     * @return ...
     */
    public int getNombreDefectivePixel() {
        return this.liste_pixel.size();
    }

    /**
     * ...
     *
     * @param index ...
     * @return ...
     */
    public Pixel getPixel(int index) {
        return this.liste_pixel.get(index);
    }

    public void addPixel(int x, int y) {
        this.liste_pixel.add(new Pixel(x, y));
    }
}
