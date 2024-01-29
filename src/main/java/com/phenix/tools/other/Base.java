package com.phenix.tools.other;

/**
 * Faire des conversions de base 2, 3, 4, ... jusqu'à la 10 en base 10 ou autre
 * jusqu'à la base 10.
 *
 * @author  <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 0.1.1
 */
public class Base {

    /**
     * Base de départ.<br>
     * Par défaut c'est celle de 10.
     */
    private int base_depart = 10;

    /**
     * Base à laquelle on veut arriver.
     */
    private int base_arrive;

    /**
     * La valeur d'origine.
     */
    private int valeur_depart_decimal;

    /**
     * La valeur après traitement.
     */
    private String valeur_arrive = "0";

    /**
     * Définit les informations essentielles.
     *
     * @param valeur_depart Base à laquelle on veut arriver.
     */
    public Base(String valeur_depart) {
        this(valeur_depart, 10);
    }

    /**
     * Définit les informations essentielles.
     *
     * @param base_depart Base de départ.
     * @param valeur_depart Base à laquelle on veut arriver.
     */
    public Base(String valeur_depart, int base_depart) {
        this.base_depart = base_depart;
        this.base_arrive = this.base_depart;

        this.valeur_depart_decimal = Integer.parseInt(valeur_depart, this.base_depart);
    }

    /**
     * Retourne le résultat sur base des 3 informations : base initiale, base
     * finale et la valeur liée à la base initiale.<br>
     * Résultat en {@code String} pour gérer l'hexadécimal.
     *
     * @return Résultat en base finale.
     */
    public String getValeur() {
        return Integer.toString(this.valeur_depart_decimal, this.base_arrive);
    }

    /**
     * Définit la base finale.
     *
     * @param base_arrive Base à laquelle on veut arriver.
     */
    public void setConvertirBase(int base_arrive) {
        this.base_arrive = base_arrive;
    }

}
