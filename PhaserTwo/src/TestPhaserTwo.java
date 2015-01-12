import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2012-12-03
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class TestPhaserTwo {
	class SimpleTâche implements Callable<Integer> {
		private Phaser phaser;

		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		private int numero;

		SimpleTâche(int numero, Phaser phaser) {
			this.numero = numero;
			this.phaser = phaser;
		}

		@Override
		public Integer call() throws Exception {

			phaser.arriveAndAwaitAdvance();
			System.err.println("Tâche arrivée: " + numero);
			return this.getNumero();  //To change body of implemented methods use File | Settings | File Templates.
		}
	}

	private void doIt() {
		/*
					On prépare un ensenmble de tâches, la numéro 0 sera la lanterne rouge.
				 */
		Collection<Callable<Integer>> lesOperations = new ArrayList<Callable<Integer>>();
		final int nbTâches = 10;

		/*
					On prépare un synchroniseur à phases (Phaser), avec précompte des passages attendus
					(le nombre de tâches).
				 */
		Phaser phaser = new Phaser(nbTâches);
		ScheduledExecutorService service;
		{
			/*
						* Création des tâches (sauf la numéro 0) et liaison avec le Phaser
						*/
			for (int i = 1; i < nbTâches; i++) {
				lesOperations.add(new SimpleTâche(i, phaser));
			}
			;
			/*
						* Invocation en parallèle de ces tâches.
						* Elles vont se bloquer sur le phaser (car la tâche 0 est plus lente)
						*/
			service = Executors.newScheduledThreadPool(nbTâches);
			try {
				service.invokeAll(lesOperations, 1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
			;
		}


		/*
				 * Création de la lanterne rouge et invocation.
				 */
		service.schedule(new SimpleTâche(0, phaser), 5, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		TestPhaserTwo testPhaserTwo = new TestPhaserTwo();
		testPhaserTwo.doIt();

	}
}
