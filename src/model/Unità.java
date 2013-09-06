package model;

import java.awt.Image;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import controller.Dijkstra;
import controller.GameMode;

public abstract class Unità {
	protected int att; // attacco
	protected int dif; // difesa
	protected double esp; // esperienza valori da 0 a 1 con passo 0.1
	protected int passi; // passi rimanenti
	protected double bonus; // bonus territorio
	protected int numUnits; // numero di unità
	protected Esagono pos; // posizione sulla mappa
	protected int player; // 1=player 1 / 2=player 2
	protected BufferedImage bImg; // immagine unità (zoom in)
	protected BufferedImage xImage; // immagine unità (zoom out)
	protected LinkedList<Esagono> esagoniRaggiungibili; /*
														 * lista contenente gli
														 * esagoni che l'unità
														 * può raggiungere con i
														 * passi rimanenti
														 */
	protected boolean alreadyAttack; /*
									 * true = l'unità ha già attaccato durante
									 * il turno / false = l'unità non ha ancora
									 * attaccato durante il turno
									 */
	public static GameMode gameMode = GameMode.getGameMode();

	public static final int UNITACOMPRABILI = 10; /*
												 * numero minimo di unità
												 * comprabili
												 */

	public Unità(int n, int player) {
		if (player < 1 || player > 2) {
			throw new IllegalArgumentException("Si gioca in 2");
		}
		this.att = 0;
		this.dif = 0;
		this.passi = 0;
		this.esp = 0;
		this.numUnits = n;
		this.pos = null;
		this.bonus = 0;
		this.player = player;
		this.bImg = null;
		this.esagoniRaggiungibili = null;
		this.alreadyAttack = false;

		/*
		 * a seconda del player di appartenenza dell'unità ad essa viene
		 * associata un'immagine oppure un'altra in modo da identificarla
		 * facilmente
		 */
		if (this.player == 1) {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Xrossa.png");
				xImage = ImageIO.read(imgUrl);
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			gameMode.getPlayer(1).aggiungiUnità(this);
		} else {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Xblu.png");
				xImage = ImageIO.read(imgUrl);
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			gameMode.getPlayer(2).aggiungiUnità(this);
		}
	}

	public boolean hasAlreadyAttack() {
		return this.alreadyAttack;
	}

	public int getAtt() {
		return this.att;
	}

	public int getDef() {
		return this.dif;
	}

	public double getEsp() {
		return this.esp;
	}

	public int getPassi() {
		return this.passi;
	}

	public double getBonus() {
		return this.bonus;
	}

	public int getNumUnits() {
		return this.numUnits;
	}

	public Esagono getPos() {
		return this.pos;
	}

	public abstract String getNome();

	public int getPlayer() {
		return this.player;
	}

	public Image getImage() {
		if(gameMode.isZoomOutMode()){
			return this.xImage;
		}
		else{
			return this.bImg;
		}
	}

	public LinkedList<Esagono> getEsagoniRaggiungibili() {
		this.calcolaEsagoniRaggiungibili();
		return this.esagoniRaggiungibili;
	}

	protected void calcolaEsagoniRaggiungibili() {
		Mappa m = gameMode.getMappa();
		m.resetDistances();
		this.esagoniRaggiungibili = Dijkstra.shortestPath(m, pos, passi);
	}

	public void setEsp(double e) {
		if (e > 1) {
			throw new IllegalArgumentException("Valore massimo 1");
		}
		this.esp = e;
	}

	public void setAlreadyAttack(boolean value) {
		this.alreadyAttack = value;
	}

	public void setNumUnits(int n) {
		if (n < 0) {
			throw new IllegalArgumentException(
					"non ha senso avere numero di unità negative");
		}
		this.numUnits = n;
	}

	public void updateEsp() {
		if (this.esp + 0.1 <= 1)
			this.esp += 0.1;
		else
			this.esp = 1;
	}

	public void setPos(Esagono p) {
		this.pos = p;
		if (p.getTerritorio() != null) {
			this.bonus = p.getTerritorio().getBonus();
		}
	}

	public abstract void resetPassi();

	public abstract void setPassi(int passi);

	public boolean isSameUnitOf(Unità other){
		return this.getNome().equals(other.getNome());
	}
	
	/*
	 * il criterio di confronto è basato sulla posizione dell'unità in quanto
	 * non possono coesistere due unità diverse sullo stesso territorio
	 */
	public boolean equals(Unità other) {
		boolean ok = false;
		if (this.pos.equals(other.pos))
			ok = true;

		return ok;
	}
}
