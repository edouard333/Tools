package com.phenix.tools;

/**
 * Faire des conversions de base 2, 3, 4, ... jusque la 10 en base 10 ou autre
 * jusque la base 10.
 *
 * @author  <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 0.1.1
 */
public class Base {

  /**
   * Base de départ.<br>
   * Par-défaut c'est celle de 10.
   */
  private int base_depart = 10;

  /**
   * Base auquel on veut arriver.
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
   * Définit les informations essentiel.
   *
   * @param valeur_depart Base auquel on veut arriver.
   */
  public Base(String valeur_depart) {
    this(valeur_depart, 10);
  }

  /**
   * Définit les informations essentiel.
   *
   * @param base_depart Base de départ.
   * @param valeur_depart Base auquel on veut arriver.
   */
  public Base(String valeur_depart, int base_depart) {
    this.base_depart = base_depart;
    this.base_arrive = this.base_depart;

    this.valeur_depart_decimal = Integer.parseInt(valeur_depart, this.base_depart);
  }

  /**
   * Récupère le résultat sur base des 3 informations : base initiale, base
   * finale et la valeur lié à la base initiale.<br>
   * Résultat en <code>String</code> pour gérer le hexadécimal.
   *
   * @return Résultat en base finale.
   */
  public String getValeur() {
    System.out.println("val depart: " + this.valeur_depart_decimal);
    return Integer.toString(this.valeur_depart_decimal, this.base_arrive);
  }

  /**
   * Définit la base finale.
   *
   * @param base_arrive Base auquel on veut arriver.
   */
  public void setConvertirBase(int base_arrive) {
    this.base_arrive = base_arrive;
  }

  /**
   * TODO
   *
   * @param args Paramètres reçus.
   */
  public static void main(String[] args) {

    Base b = new Base("10");
    b.setConvertirBase(2);

    System.out.println("Résultat (2 en base 10) vaut " + b.getValeur() + " en base 2");

  }

}
