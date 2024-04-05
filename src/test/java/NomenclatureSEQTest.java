
import com.phenix.tools.businesslayer.Nomenclature_SEQ;
import com.phenix.tools.businesslayer.Nomenclature_audio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class NomenclatureSEQTest {

    public NomenclatureSEQTest() {
    }

    /**
     * Ce qui se passe avant tous les tests.
     */
    @BeforeAll
    public static void setUpClass() {
    }

    /**
     * Ce qui se passe après tous les tests.
     */
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Exécuter avant chaque test.
     */
    @BeforeEach
    public void setUp() {
    }

    /**
     * Exécute après chaque test.
     */
    @AfterEach
    public void tearDown() {
    }

    /**
     * Tests qui se trouvaient dans un main.
     */
    @Test
    public void testMain() {
        Nomenclature_SEQ n = new Nomenclature_SEQ(/*"XXX_FTR_PR444_1080-239_50i_.mov"*/);
        n.setTitre("Moi-moche");

        n.setResolution("1920x1080");

        n.setRatio("2.39");

        n.setType("film");

        n.setCodec("Apple Pro Res 444");

        n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
        n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
        n.addAudio(new Nomenclature_audio("VO", "R128", "LtRt"));
        System.out.println(n.getNomFichier());
    }
}
