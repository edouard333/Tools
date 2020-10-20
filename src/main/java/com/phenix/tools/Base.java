package com.phenix.tools;


/**
 * Faire des conversions de base 2, 3, 4, ... jusque la 10 en base 10 ou autre jusque la base 10. Il ne permet que de
 * convertire d'une base ne celle de 10... Plus tard le contraire sera possible.
 *
 * @version 0.1.0
 * @author  Edouard JEANJEAN <edouard128@hotmail.com>
 */
public class Base {

    /**
     * TODO
     */
    private int base_depart = 10;

    /**
     * TODO
     */
    private int base_arrive = 10;

    /**
     * TODO
     */
    private String valeur_depart = "0";

    /**
     * TODO
     */
    private String valeur_arrive = "0";

    /**
     * TODO
     *
     * @param base_depart ...
     * @param valeur_depart ...
     */
    public Base(int base_depart, int valeur_depart) {
        this.base_depart = base_depart;

        if (base_depart < 10) {
            this.valeur_depart = Integer.toString(valeur_depart, this.base_depart);
        } else {
            this.valeur_depart = Integer.toString((0x00000010), this.base_depart);
        }
    }

    /**
     * TODO
     *
     * @param base_arrive ...
     */
    public void setConvertirBase(int base_arrive) {
        this.base_arrive = base_arrive;
    }

    /**
     * TODO
     *
     * @return ...
     */
    public String getValeur() {

        if (this.base_arrive < 10) {
            System.out.println("val depart: " + this.valeur_depart);
            valeur_arrive = Integer.toString(Integer.parseInt(this.valeur_depart), 2);
        } else {
            valeur_arrive = Integer.toString((0x00000010), 2);
        }

        return this.valeur_arrive;
    }

    /**
     * TODO
     *
     * @param args ...
     */
    public static void main(String[] args) {

        Base b = new Base(10, 10);
        b.setConvertirBase(2);

        System.out.println("Résultat (10 en base 10) vaut " + b.getValeur() + " en base 2");

    }

}
