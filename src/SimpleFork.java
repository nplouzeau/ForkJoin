import java.util.concurrent.RecursiveAction;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2012-12-01
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFork extends RecursiveAction {

    private int niveau;

    public SimpleFork(int niveau) {
        this.niveau = niveau;
    }
    
    @Override
    protected void compute() {
        System.err.println("This is me, "+this+", at level "+niveau);
	    // Création récursive d'actions fork/join
        if(niveau > 0) {
            System.err.println("Starting subtasks for me, "+this);
            invokeAll(new SimpleFork(niveau - 1), new SimpleFork(niveau - 1)) ;
            System.err.println("Subtasks for me, "+this+" at level " + niveau + " are done now.");
        }
    }
}
