import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Phaser;
import java.util.concurrent.RecursiveAction;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2012-12-01
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFork extends RecursiveAction {

    private int niveau;
    private int nbLargeur;
    private Phaser phaser;

    public SimpleFork(int niveau, int nbLargeur, Phaser phaser) {
        this.niveau = niveau;
        this.nbLargeur = nbLargeur;
        this.phaser = phaser;
    }

    @Override
    protected void compute() {
        System.err.println("This is me, " + this + ", at level " + niveau);
        if (niveau > 0) {
            System.err.println("Starting subtasks for me," + this);
            Collection<RecursiveAction> subTasks = new ArrayList<RecursiveAction>();
            for (int i = 0; i < nbLargeur; i++) {
                subTasks.add(new SimpleFork(niveau - 1, nbLargeur, phaser));

            }


            invokeAll(subTasks);
            System.err.println("Subtasks for me," + this + " are done now.");
        } else {
            phaser.register();
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            // Niveau 0 : signaler l'arrivée au phaser
            phaser.arrive();
        }
    }
}
