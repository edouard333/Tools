package com.phenix.tools.businesslayer;

import java.util.HashMap;
import java.util.Map;

/**
 * Valeur en FR, voir pour une version en anglais.<br>
 * Note 1 : Voir pour qu'une API pour avoir les données !<br>
 * Note 2 : est lié au projet "Name Convention".
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 0.1.0
 */
public class Nomenclature_audio {

    /**
     * Original, VF, etc.
     */
    private String version;

    /**
     * R128, TV, DVD, etc.
     */
    private String mixe;

    /**
     * 20, 51, LtRt, etc.
     */
    private String canaux;

    /**
     *
     */
    Map<String, String> liste_version = new HashMap<String, String>();

    /**
     *
     */
    Map<String, String> liste_mix = new HashMap<String, String>();

    /**
     *
     */
    Map<String, String> liste_canaux = new HashMap<String, String>();

    /**
     * Définit les valeurs d'une piste audio.
     *
     * @param version Version : V(O)F, VO, etc
     * @param mixe Mixe: BR, TV, etc
     * @param canaux Système de reproduction : 20, 51, 71, LtRt, etc.
     */
    public Nomenclature_audio(String version, String mixe, String canaux) {
        this.version = version;
        this.mixe = mixe;
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
     * Nomenclature d'une piste audio.
     *
     * @return Nomenclature audio.
     */
    public String get() {
        return this.version + "-" + this.mixe + "-" + this.canaux;
    }

    @Override
    public String toString() {
        return get();
    }

}
