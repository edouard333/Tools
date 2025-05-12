package com.phenix.tools;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Faire des conversions de base 2, 3, 4, ... jusqu'à la 10 en base 10 ou autre
 * jusqu'à la base 10.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class Base {

    /**
     * Base de départ.<br>
     * Par défaut c'est celle de 10.
     */
    private int baseDepart = 10;

    /**
     * Base à laquelle on veut arriver.
     */
    private int baseArrive;

    /**
     * La valeur d'origine.
     */
    private int valeurDepartDecimal;

    /**
     * La valeur après traitement.
     */
    private String valeurArrive = "0";

    /**
     * Définit les informations essentielles.
     *
     * @param valeurDepart Base à laquelle on veut arriver.
     */
    public Base(@NotNull @NotBlank String valeurDepart) {
        this(valeurDepart, 10);
    }

    /**
     * Définit les informations essentielles.
     *
     * @param baseDepart Base de départ.
     * @param valeurDepart Base à laquelle on veut arriver.
     */
    public Base(@NotNull @NotBlank String valeurDepart, int baseDepart) {
        this.baseDepart = baseDepart;
        this.baseArrive = this.baseDepart;

        this.valeurDepartDecimal = Integer.parseInt(valeurDepart, this.baseDepart);
    }

    /**
     * Retourne le résultat sur base des 3 informations : base initiale, base
     * finale et la valeur liée à la base initiale.<br>
     * Résultat en {@link String} pour gérer l'hexadécimal.
     *
     * @return Résultat en base finale.
     */
    @NotNull
    public String getValeur() {
        return Integer.toString(this.valeurDepartDecimal, this.baseArrive);
    }

    /**
     * Définit la base finale.
     *
     * @param baseArrive Base à laquelle on veut arriver.
     */
    public void setConvertirBase(int baseArrive) {
        this.baseArrive = baseArrive;
    }
}
