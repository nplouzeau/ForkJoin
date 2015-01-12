import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2012-12-02
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class Ecouteur implements Callable<Void> {

    private Phaser phaserAEcouter;
    private int compte;

    public Ecouteur(int compte, Phaser p) {
        this.phaserAEcouter = p;
        this.compte = compte;
    }

    @Override
    public Void call() throws Exception {
        System.err.println("Ecouteur registering for "+compte);
        phaserAEcouter.awaitAdvance(compte);
        System.err.println("Arrivé !");
        return null;
    }
}
