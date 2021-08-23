package com.phenix.tools;

/**
 * Il sert à calculer ce que dure un bout de code.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.11.0
 */
public class Timer {

  /**
   * TODO
   */
  private double temps;

  /**
   * TODO
   */
  private double temps_totale;

  /**
   * Options.
   */
  private boolean reinitaliser = false;

  /**
   * TODO
   */
  public Timer() {
  }

  /**
   * TODO
   *
   * @param reinitaliser
   */
  public Timer(boolean reinitaliser) {
    this.reinitaliser = reinitaliser;
  }

  /**
   * TODO
   */
  public void start() {
    temps = System.currentTimeMillis();
  }

  /**
   * TODO
   *
   * @return
   */
  public double stop() {
    return stop(this.reinitaliser);
  }

  /**
   * TODO
   *
   * @param reinitaliser
   * @return
   */
  public double stop(boolean reinitaliser) {
    temps_totale = System.currentTimeMillis() - temps;
    System.out.println("Temps (Milliseconde):" + temps_totale);

    if (reinitaliser) {
      temps = System.currentTimeMillis();
    }

    return temps_totale;
  }

}
