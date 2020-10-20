package com.phenix.tools.XMLNLE;

/**
 * Classe utilisé pour connaitre la position des pixels defecteux ou mort dans l'image.
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 1.0.0
 */
public class Pixel {

    /**
     * ...
     */
    private int x;

    /**
     * ...
     */
    private int y;

    /**
     * ...
     *
     * @param x ...
     * @param y ...
     */
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getX() {
        return this.x;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getY() {
        return this.y;
    }
}
