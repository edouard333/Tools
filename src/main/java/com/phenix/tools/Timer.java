package com.phenix.tools;

/**
 * Chronomètre la durée que prend l'exécution d'un bout de code.
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 * @version 1.11.0
 */
public class Timer {

  /**
   * Heure de début.
   */
  private double temps;

  /**
   * Temps entre le start et le stop.
   */
  private double temps_totale;

  /**
   * Options.
   */
  private boolean reinitaliser = false;

  /**
   * Construit un <code>Timer</code>.
   */
  public Timer() {
  }

  /**
   * Construit un <code>Timer</code>.
   *
   * @param reinitaliser Indique si on réinitialise.
   */
  public Timer(boolean reinitaliser) {
    this.reinitaliser = reinitaliser;
  }

  /**
   * Lance le chronomètre.
   */
  public void start() {
    this.temps = System.currentTimeMillis();
  }

  /**
   * Arrête le temps.
   *
   * @return Temps écoulé en miliseconde.
   */
  public double stop() {
    return stop(this.reinitaliser);
  }

  /**
   * Arrêt le chronomètre.
   *
   * @param reinitaliser Si on réinitialise le temps apès le stop.
   *
   * @return Temps écoulé en miliseconde.
   */
  public double stop(boolean reinitaliser) {
    this.temps_totale = System.currentTimeMillis() - this.temps;
    System.out.println("Temps (Milliseconde):" + this.temps_totale);

    if (reinitaliser) {
      this.temps = System.currentTimeMillis();
    }

    return this.temps_totale;
  }

}
