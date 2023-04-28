package com.phenix.tools.businesslayer.test;

import com.phenix.tools.businesslayer.Nomenclature_SEQ;
import com.phenix.tools.businesslayer.Nomenclature_audio;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class TestNomSEQ {

    /**
     * Pour les tests.
     *
     * @param args Paramètres reçus.
     */
    public static void main(String[] args) {
        Nomenclature_SEQ n = new Nomenclature_SEQ(/*"XXX_FTR_PR444_1080-239_50i_.mov"*/);
        n.setTitre("Moi-moche");

        n.setResolution("1920x1080");

        n.setRatio("2.39");

        n.setType("film");

        n.setCodec("Apple Pro Res 444");

        n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
        n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
        n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
        System.out.println(n.getNomFichier());
    }
}
