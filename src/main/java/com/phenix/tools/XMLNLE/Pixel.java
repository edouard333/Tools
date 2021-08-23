package com.phenix.tools.XMLNLE;

/**
 * Classe utilisé pour connaitre la position des pixels defecteux ou mort dans
 * l'image.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.0.0
 */
public class Pixel {

  /**
   * Coordonnée x.
   */
  private int x;

  /**
   * Coordonnée y.
   */
  private int y;

  /**
   * .Construit un pixel sur base de ses coordonnées x, y.
   *
   * @param x Coordonnée x.
   * @param y Coordonnée y.
   */
  public Pixel(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Récupère la coordonnée x.
   *
   * @return Coordonnée x.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Récupère la coordonnée y.
   *
   * @return Coordonnée y.
   */
  public int getY() {
    return this.y;
  }
}
