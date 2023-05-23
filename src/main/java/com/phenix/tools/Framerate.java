package com.phenix.tools;

/**
 * Liste les différents framerates.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public enum Framerate {

    /**
     * Framerate 23,976is.
     */
    F23976(23976D, false),
    /**
     * Framerate 24is (cinéma/Bluray).
     */
    F24(24D, false),
    /**
     * Framerate 25is (PAL TV).
     */
    F25(25D, false),
    /**
     * Framerate 29,97 (NTSC TV).
     */
    F2997(2997D, true);

    /**
     * Valeur à utiliser.
     */
    private final double framerate;

    /**
     * Si drop frame ou non.
     */
    private boolean drop_frame;

    /**
     *
     * @param framerate
     * @param drop_frame
     */
    private Framerate(double framerate, boolean drop_frame) {
        this.framerate = framerate;
        this.drop_frame = drop_frame;
    }

    /**
     *
     * @return
     */
    public double getValeur() {
        return this.framerate;
    }
}