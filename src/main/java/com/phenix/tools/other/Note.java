package com.phenix.tools.other;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

/**
 * Classe jouant une note en fonction d'une fréquence.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 0.8.0
 * @since Kit objet V2
 */
public class Note extends Thread {

    /**
     * ???
     */
    private static final int SAMPLE_RATE = 16 * 1024;

    /**
     * La fréquence définit.
     */
    private final int frequence;

    /**
     * Construit une {@code Note} à l'aide d'une fréquence.
     *
     * @param frequence Définit la fréquence en Hz.
     */
    public Note(int frequence) {
        this.frequence = frequence;
    }

    /**
     * Fonction interne à la classe Note.
     *
     * @param freq Fréquence du son qui sera lu.
     * @param ms Milliseconde du temps de lecture du son.
     */
    private byte[] createSinWaveBuffer(double freq, int ms) {
        int samples = (int) ((ms * SAMPLE_RATE) / 1000);
        byte[] output = new byte[samples];

        double period = (double) SAMPLE_RATE / freq;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) (Math.sin(angle) * 127f);
        }

        return output;
    }

    /**
     * Lit la note de manière à pouvoir en lire d'autres en parallèle.
     */
    @Override
    public void run() {
        try {
            final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
            SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open(af, SAMPLE_RATE);
            line.start();

            byte[] toneBuffer = createSinWaveBuffer(this.frequence, 50);
            int count = line.write(toneBuffer, 0, toneBuffer.length);

            line.drain();
            line.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
