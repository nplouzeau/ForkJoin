import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2012-12-01
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class TestPhaser {
    public static void main(String args[]) {

        ForkJoinPool monPool = new ForkJoinPool();
        // Créer un phaser de diagnostic
        Phaser phaser = new Phaser();

        SimpleFork monForkDeBase;
        final int niveaux = 2;
        monForkDeBase = new SimpleFork(niveaux, 2, phaser);

        Ecouteur monEcouteur;
        monEcouteur = new Ecouteur(2 ^ niveaux, phaser);
        ExecutorService monSvc = Executors.newCachedThreadPool();
        monSvc.submit(monEcouteur);
        monPool.invoke(monForkDeBase);

    }
}
