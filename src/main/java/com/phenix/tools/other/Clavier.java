package com.phenix.tools.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Sert à "écrire" quelque chose avec le clavier.
 *
 * @author  <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 * @since Kit objet V1
 */
public class Clavier {

    /**
     * Variable qui contient ce qui est lu au clavier.
     */
    private static String myLine;

    /**
     * Objet buffer qui gére le flux de ce qui entre au clavier.
     */
    private static BufferedReader br;

    /**
     * Objet permettant de décomposer une chaîne de caractères en une suite de
     * "mots" séparés par des "délimiteurs".
     */
    private static StringTokenizer st;

    /* -- Constructors -- */
    /**
     * Null besoin d'instancer l'objet "Clavier", chaque fonction sont "static".
     */
    private Clavier() {
    }

    /**
     * Retourne en <code>byte</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>byte</code>.
     */
    public static byte lireByte() {

        byte byte0;

        try {
            myLine = br.readLine();
            st = new StringTokenizer(myLine);
            byte0 = Byte.parseByte(st.nextToken());
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            byte0 = lireByte();
        } catch (NumberFormatException numberformatexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nLe format du nombre lu ne semble pas correspondre \340 un entier de type (byte).\nValeurs permises : de -128 \340 127.\nMessage d'erreur du syst\350me: " + numberformatexception + "\nVeuillez recommencer.");
            byte0 = lireByte();
        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
            byte0 = lireByte();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            byte0 = lireByte();
        }

        return byte0;
    }

    /**
     * Retourne en "short" ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>short</code>.
     */
    public static short lireShort() {

        short short0;

        try {
            myLine = br.readLine();
            st = new StringTokenizer(myLine);
            short0 = Short.parseShort(st.nextToken());
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            short0 = lireShort();
        } catch (NumberFormatException numberformatexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nLe format du nombre lu ne semble pas correspondre \340 un entier de type (short).\nValeurs permises : de -32768 \340 32767.\nMessage d'erreur du syst\350me: " + numberformatexception + "\nVeuillez recommencer.");
            short0 = lireShort();
        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
            short0 = lireShort();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            short0 = lireShort();
        }

        return short0;
    }

    /**
     * Retourne en <code>int</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>int</code>.
     */
    public static int lireInt() {

        int int0;

        try {
            myLine = br.readLine();
            st = new StringTokenizer(myLine);
            int0 = Integer.parseInt(st.nextToken());
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nVoici le message d'erreur du syst\350me :" + ioexception + "\nVeuillez recommencer.");
            int0 = lireInt();
        } catch (NumberFormatException numberformatexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nLe format du nombre lu ne semble pas correspondre \340 un entier de type (int).\nValeurs permises : de -2147483648 \340 2147483647.\nMessage d'erreur du syst\350me :" + numberformatexception + "\nVeuillez recommencer.");
            int0 = lireInt();
        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
            int0 = lireInt();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            int0 = lireInt();
        }

        return int0;
    }

    /**
     * Retourne en <code>long</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>long</code>.
     */
    public static long lireLong() {

        long long0;

        try {
            myLine = br.readLine();
            st = new StringTokenizer(myLine);
            long0 = Long.parseLong(st.nextToken());
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            long0 = lireLong();
        } catch (NumberFormatException numberformatexception) {
            System.out.println("Erreur lors de la lecture de l'entier au clavier.\nLe format du nombre lu ne semble pas correspondre \340 un entier de type (long).\nValeurs permises : de -9223372036854775808 \340 9223372036854775807.\nMessage d'erreur du syst\350me: " + numberformatexception + "\nVeuillez recommencer.");
            long0 = lireLong();
        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
            long0 = lireLong();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            long0 = lireLong();
        }

        return long0;
    }

    /**
     * Retourne en <code>float</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>float</code>.
     */
    public static float lireFloat() {

        float float0;

        try {
            myLine = br.readLine();
            st = new StringTokenizer(myLine);
            float0 = Float.parseFloat(st.nextToken());
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture du r\351el au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            float0 = lireFloat();
        } catch (NumberFormatException numberformatexception) {
            System.out.println("Erreur lors de la lecture du r\351el au clavier.\nLe format du nombre lu ne semble pas correspondre \340 un r\351el de type (float).\nMessage d'erreur du syst\350me: " + numberformatexception + "\nVeuillez recommencer.");
            float0 = lireFloat();
        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
            float0 = lireFloat();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            float0 = lireFloat();
        }

        return float0;
    }

    /**
     * Retourne en <code>double</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>double</code>.
     */
    public static double lireDouble() {

        double double0;

        try {
            myLine = br.readLine();
            st = new StringTokenizer(myLine);
            double0 = Double.parseDouble(st.nextToken());
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture du r\351el au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            double0 = lireDouble();
        } catch (NumberFormatException numberformatexception) {
            System.out.println("Erreur lors de la lecture du r\351el au clavier.\nLe format du nombre lu ne semble pas correspondre \340 un r\351el de type (double).\nMessage d'erreur du syst\350me: " + numberformatexception + "\nVeuillez recommencer.");
            double0 = lireDouble();
        } catch (NoSuchElementException nosuchelementexception) {
            System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
            double0 = lireDouble();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            double0 = lireDouble();
        }

        return double0;
    }

    /**
     * Retourne en <code>char</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>char</code>.
     */
    public static char lireChar() {

        char char0;

        try {
            myLine = br.readLine();
            if (myLine.length() == 0) {
                System.out.println("Erreur lors de la saisie au clavier. Vous avez uniquement tap\351 sur 'enter'.\nVeuillez recommencer.");
                char0 = lireChar();
            }
            char0 = myLine.charAt(0);
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture du caract\350re au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            char0 = lireChar();
        } catch (StringIndexOutOfBoundsException stringindexoutofboundsexception) {
            System.out.println("Erreur lors de la lecture du caract\350re au clavier.\nMessage d'erreur du syst\350me: " + stringindexoutofboundsexception + "\nVeuillez recommencer.");
            char0 = lireChar();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            char0 = lireChar();
        }

        return char0;
    }

    /**
     * Retourne en <code>String</code> ce qui a été saisi au clavier.
     *
     * @return La valeur en <code>String</code>.
     */
    public static String lireString() {

        try {
            myLine = br.readLine();
        } catch (IOException ioexception) {
            System.out.println("Erreur lors de la lecture de la cha\356ne de caract\350res au clavier.\nVoici le message d'erreur du syst\350me: " + ioexception + "\nVeuillez recommencer.");
            myLine = lireString();
        } catch (Exception exception) {
            System.out.println("Erreur lors de la saisie au clavier.\nVoici le message d'erreur du syst\350me: " + exception + "\nVeuillez recommencer.");
            myLine = lireString();
        }

        return myLine;
    }

    static {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
}
