# Tools
Librairie contenant un ensemble d'outils commun aux projets Java.

# Comment l'utiliser ?
Beaucoup d'outil sont à disposition :

```java
import com.phenix.tools.Attend;
import com.phenix.tools.Clavier;

void main(String[] args) {
    // Met une attente de 2sec.
    Attend.delais(2000);

    // Lire une entrée au clavier via le terminal.
    String str = Clavier.lireString();
    
    // ...
}
```
