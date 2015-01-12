import java.util.concurrent.ForkJoinPool;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2012-12-01
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class TestForkJoin {
    public static void main(String args[]) {

        ForkJoinPool monPool = new ForkJoinPool();
        SimpleFork monForkDeBase = new SimpleFork(2);

        monPool.invoke(monForkDeBase);

    }
}
