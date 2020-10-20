package com.phenix.tools.businesslayer;

import java.util.HashMap;
import java.util.Map;

/**
 * valeur en FR, voir pour une version en anglais. Note 1: Voir pour qu'une API
 * pour avoir les données! Note 2: est lié au projet "Name Convention".
 *
 * @author Edouard JEANJEAN <edouard128@hotmail.com>
 * @version 0.1.0
 */
public class Nomenclature_audio {

    /**
     * Original, VF, ...
     */
    private String version;

    /**
     * R128, TV, DVD, ...
     */
    private String mix;

    /**
     * 20, 51, LtRt, ...
     */
    private String canaux;

    /**
     * TODO
     */
    Map<String, String> liste_version = new HashMap<String, String>();

    /**
     * TODO
     */
    Map<String, String> liste_mix = new HashMap<String, String>();

    /**
     * TODO
     */
    Map<String, String> liste_canaux = new HashMap<String, String>();

    /**
     * TODO
     *
     * @param version ...
     * @param mix ...
     * @param canaux ...
     */
    public Nomenclature_audio(String version, String mix, String canaux) {
        this.version = version;
        this.mix = mix;
        this.canaux = canaux;
    }

    /**
     * Initiale les valeurs.
     */
    private void initValeur() {
        liste_version.put("VO", "version originale");
        liste_version.put("VF", "version double francais");
        liste_version.put("VA", "version double anglais");

        liste_mix.put("BR", "Blu-ray");
        liste_mix.put("DVD", "DVD");
        liste_mix.put("R128", "R128");
        liste_mix.put("DCP", "cinema");

        liste_canaux.put("51", "5.1");
        liste_canaux.put("20", "stereo");
        liste_canaux.put("LoRo", "Lo Ro");
        liste_canaux.put("LtRt", "Lt Rt");
    }

    /**
     * TODO
     *
     * @return ...
     */
    public String get() {
        return this.version + "-" + this.mix + "-" + this.canaux;
    }

    @Override
    public String toString() {
        return get();
    }

}
